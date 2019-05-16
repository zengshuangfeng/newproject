package com.meisui.manage.dao;

import java.util.Date;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.meisui.manage.entity.WxShare;

@Repository
public interface IwxshareDao {

	WxShare getShareMsg();

	int save(@Param("name")String name, @Param("title")String title, @Param("icon")String icon, @Param("url")String url, @Param("w_name")String w_name, @Param("date")Date date);

	int update(@Param("id")int id, @Param("name")String name, @Param("title")String title, @Param("icon")String icon, @Param("url")String url, @Param("w_name")String w_name, @Param("date")Date date);

}
