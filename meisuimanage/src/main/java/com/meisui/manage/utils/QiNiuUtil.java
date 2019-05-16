package com.meisui.manage.utils;

import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.UUID;

import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.web.multipart.MultipartFile;

import com.forman.log4j.Log4jHandel;
import com.qiniu.common.QiniuException;
import com.qiniu.common.Zone;
import com.qiniu.http.Response;
import com.qiniu.processing.OperationManager;
import com.qiniu.storage.BucketManager;
import com.qiniu.storage.Configuration;
import com.qiniu.storage.UploadManager;
import com.qiniu.util.Auth;
import com.qiniu.util.StringMap;
import com.qiniu.util.UrlSafeBase64;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

public class QiNiuUtil {
	private static Logger log = Logger.getLogger(QiNiuUtil.class.getClass());

	public static final String ACCESS_KEY = PropertyUtil.getValue("access_key");

	public static final String SECRET_KEY = PropertyUtil.getValue("secret_key");

	private String token = "";
	private Auth auth = Auth.create(ACCESS_KEY, SECRET_KEY);
	private UploadManager uploadManager = null;
	Configuration cfg = new Configuration(Zone.autoZone());

	public QiNiuUtil() {

		uploadManager = new UploadManager(cfg);
	}

	public String getToken(String key) {
		return auth.uploadToken(key);
	}

	public Response upLoadFile(String key, MultipartFile file, String bucket) throws Exception {
		Log4jHandel.myinfo("ACCESS_KEY:" + ACCESS_KEY);
		Log4jHandel.myinfo("SECRET_KEY:" + SECRET_KEY);
		Log4jHandel.myinfo("key:" + key);
		token = getToken("meisui");
		Response response = null;
		try {
			response = uploadManager.put(file.getBytes(), key, token);
		} catch (Exception e) {
			Log4jHandel.myerror("xxx", e);
		}

		return response;
	}

	public String getImageSize(String url) {
		StringBuilder stringBuilder = new StringBuilder();
		try {
			URL urlObject = new URL(url.concat("?imageInfo"));
			URLConnection uc = urlObject.openConnection();
			BufferedReader in = new BufferedReader(new InputStreamReader(uc.getInputStream()));
			String inputLine = null;
			while ((inputLine = in.readLine()) != null) {
				stringBuilder.append(inputLine);
			}
			in.close();
			JSONObject jsonObject = JSONObject.fromObject(stringBuilder.toString());
			if (jsonObject != null) {
				return jsonObject.get("width") + "x" + jsonObject.get("height");
			}
		} catch (MalformedURLException e) {
			log.error("获取七牛图片大小异常", e);
			e.printStackTrace();
		} catch (IOException e) {
			log.error("获取七牛图片大小异常", e);
			e.printStackTrace();
		}
		return "";
	}

	public String getVideoWidthHeight(String url) {
		StringBuilder stringBuilder = new StringBuilder();
		try {
			URL urlObject = new URL(url.concat("?avinfo"));
			URLConnection uc = urlObject.openConnection();
			BufferedReader in = new BufferedReader(new InputStreamReader(uc.getInputStream()));
			String inputLine = null;
			while ((inputLine = in.readLine()) != null) {
				stringBuilder.append(inputLine);
			}
			in.close();
			JSONObject jsonObject = JSONObject.fromObject(stringBuilder.toString());
			if (jsonObject != null && jsonObject.containsKey("streams")) {
				JSONArray jsonArray = jsonObject.getJSONArray("streams");
				if (jsonArray.getJSONObject(0).containsKey("width")) {
					return jsonArray.getJSONObject(0).get("width") + "x" + jsonArray.getJSONObject(0).get("height");
				}
			}
		} catch (MalformedURLException e) {
			log.error("获取七牛视频高宽异常", e);
			e.printStackTrace();
		} catch (IOException e) {
			log.error("获取七牛视频高宽异常", e);
			e.printStackTrace();
		}
		return "";
	}

	public Response upLoadFile(String key, byte[] data, String bucket) throws Exception {
		token = getToken(bucket);
		Response response = uploadManager.put(data, key, token);
		return response;
	}

	public void renameFile(String bucket, String key, String targetKey) throws QiniuException {
		BucketManager bucketManager = new BucketManager(auth, cfg);
		bucketManager.rename(bucket, key, targetKey);
	}

	public String upload(String folder, String imageUrl) {

		if (!StringUtils.isBlank(imageUrl)) {
			HttpURLConnection conn = null;
			InputStream inStream = null;
			ByteArrayOutputStream outStream = null;
			try {
				if (imageUrl.startsWith(PropertyUtil.getValue("meisui_pic_url"))) {
					return imageUrl.replace(PropertyUtil.getValue("meisui_pic_url"), "");
				}
				String newFilename = UUID.randomUUID().toString().replace("-", "") + getExt(imageUrl);
				URL url = new URL(imageUrl);
				conn = (HttpURLConnection) url.openConnection();
				conn.setRequestMethod("GET");
				conn.setRequestProperty("User-agent",
						"Mozilla/5.0 (Windows NT 6.1; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/50.0.2661.94 Safari/537.36");
				inStream = conn.getInputStream();
				outStream = new ByteArrayOutputStream();
				byte[] buffer = new byte[1024];
				int len = 0;
				while ((len = inStream.read(buffer)) != -1) {
					outStream.write(buffer, 0, len);
				}

				Response response = upLoadFile(newFilename, outStream.toByteArray(), folder);
				if (response.statusCode == 200) {
					return newFilename;
				} else {
					return imageUrl;
				}
			} catch (Exception e) {
				log.error("上传图片异常", e);
			} finally {
				try {
					if (conn != null) {
						conn.disconnect();
						conn = null;

					}
				} catch (Exception e2) {
					// TODO: handle exception
				}
				try {
					if (inStream != null) {
						inStream.close();
						inStream = null;
					}
				} catch (Exception e2) {
					// TODO: handle exception
				}
				try {
					if (outStream != null) {
						outStream.close();
						outStream = null;
					}
				} catch (Exception e2) {
					// TODO: handle exception
				}

			}
		}

		return imageUrl;
	}

	public String getExt(String url) {
		String[] exts = new String[] { ".gif", ".png", ".jpg", ".jpeg", ".bmp", ".mp4", ".avi", ".mov" };
		for (String ext : exts) {
			if (url.contains(ext))
				return ext;
			else if (url.contains(ext.toUpperCase()))
				return ext.toUpperCase();
		}
		return "";
	}

	/*
	 * public String getAvSize(String url) { StringBuilder stringBuilder = new
	 * StringBuilder(); try { String iString3=""; URL urlObject = new
	 * URL(url.concat("?avinfo")); URLConnection uc = urlObject.openConnection();
	 * BufferedReader in = new BufferedReader(new
	 * InputStreamReader(uc.getInputStream())); String inputLine = null; while (
	 * (inputLine = in.readLine()) != null) { stringBuilder.append(inputLine); }
	 * in.close(); JSONObject jsonObject =
	 * JSONObject.fromObject(stringBuilder.toString()); if(jsonObject!=null) {
	 * JSONArray jsonArray=JSONArray.fromObject(jsonObject.get("streams")); for
	 * (Object object : jsonArray) { JSONObject jsonObject3 =
	 * JSONObject.fromObject(object.toString()); if
	 * (jsonObject3.containsKey("width")) { iString3= jsonObject3.get("width") + "x"
	 * + jsonObject3.get("height"); } } return iString3; } } catch
	 * (MalformedURLException e) { log.error("获取七牛视频大小异常", e); e.printStackTrace();
	 * } catch (IOException e) { log.error("获取七牛视频大小异常", e); e.printStackTrace(); }
	 * return ""; }
	 */
	public int getVideoSecond(String url) {
		StringBuilder stringBuilder = new StringBuilder();
		try {
			URL urlObject = new URL(url.concat("?avinfo"));
			URLConnection uc = urlObject.openConnection();
			BufferedReader in = new BufferedReader(new InputStreamReader(uc.getInputStream()));
			String inputLine = null;
			while ((inputLine = in.readLine()) != null) {
				stringBuilder.append(inputLine);
			}
			in.close();
			JSONObject jsonObject = JSONObject.fromObject(stringBuilder.toString());
			if (jsonObject != null) {
				JSONObject jsonObject2 = jsonObject.getJSONObject("format");
				String duration = jsonObject2.getString("duration");
				return (int) Math.floor(Double.parseDouble(duration));
			}
		} catch (MalformedURLException e) {
			log.error("获取七牛视频秒数异常", e);
			e.printStackTrace();
		} catch (IOException e) {
			log.error("获取七牛视频秒数异常", e);
			e.printStackTrace();
		}
		return 0;
	}

	public double getVideoSize(String url) {
		StringBuilder stringBuilder = new StringBuilder();
		try {
			URL urlObject = new URL(url.concat("?avinfo"));
			URLConnection uc = urlObject.openConnection();
			BufferedReader in = new BufferedReader(new InputStreamReader(uc.getInputStream()));
			String inputLine = null;
			while ((inputLine = in.readLine()) != null) {
				stringBuilder.append(inputLine);
			}
			in.close();
			JSONObject jsonObject = JSONObject.fromObject(stringBuilder.toString());
			if (jsonObject != null) {
				JSONObject jsonObject2 = jsonObject.getJSONObject("format");
				String size = jsonObject2.getString("size");
				return Double.parseDouble(size);
			}
		} catch (MalformedURLException e) {
			log.error("获取七牛视频大小异常", e);
			e.printStackTrace();
		} catch (IOException e) {
			log.error("获取七牛视频大小异常", e);
			e.printStackTrace();
		}
		return 0;
	}

	public void setWaterMark(String file_name) {
		OperationManager operater = new OperationManager(auth, cfg);
		String bucket = "picbptgb";
		String key = file_name;
		// 需要添加水印的图片UrlSafeBase64,可以参考http://developer.qiniu.com/code/v6/api/dora-api/av/video-watermark.html
		String pictureurl = UrlSafeBase64.encodeToString("http://oi64tg8v4.bkt.clouddn.com/old-driver-one.png");
		// 设置转码操作参数
		String fops = "avthumb/mp4/wmImage/" + pictureurl + "/wmGravity/NorthEast/wmOffsetX/0/wmOffsetY/0";
		// 设置转码的队列
		String pipeline = "";
		// 可以对转码后的文件进行使用saveas参数自定义命名，当然也可以不指定文件会默认命名并保存在当前空间。
		String urlbase64 = UrlSafeBase64.encodeToString("peipei:12344.mp4");
		String pfops = fops + "|saveas/" + urlbase64;
		// 设置pipeline参数
		StringMap params = new StringMap().putWhen("force", 1, true).putNotEmpty("pipeline", pipeline);
		try {
			String persistid = operater.pfop(bucket, key, pfops, params);
			// 打印返回的persistid
			System.out.println(persistid);
		} catch (QiniuException e) {
			// 捕获异常信息
			Response r = e.response;
			// 请求失败时简单状态信息
			System.out.println(r.toString());
			try {
				// 响应的文本信息
				System.out.println(r.bodyString());
			} catch (QiniuException e1) {
				// ignore
			}
		}
	}
}
