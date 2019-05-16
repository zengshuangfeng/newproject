<%@ page contentType="text/html;charset=utf-8" trimDirectiveWhitespaces="true"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>  
<%@ include file="/WEB-INF/views/common/header.jsp"%>
<link href="${ctx}/css/plugins/footable/footable.core.css"
	rel="stylesheet">
<div id="wrapper">
	<%@ include file="/WEB-INF/views/common/menu.jsp"%>
	<div id="page-wrapper" class="gray-bg">
		<%@ include file="/WEB-INF/views/common/top.jsp"%>
		<div class="row wrapper border-bottom white-bg page-heading">
			<div class="col-lg-3">
			<h2>会员管理</h2>
				<ol class="breadcrumb">
					<li><a href="#">会员管理</a></li>
					<li class="active"><strong>会员额度充值流水</strong></li>
				</ol>
			</div>
		</div>
		<div class="wrapper wrapper-content animated fadeInRight ecommerce">
			<c:set value="&begin_time=${begin_time}&end_time=${end_time}&operate_centerid=${operate_centerid}"
				var="query" />
			<div class="row">
				<div class="col-lg-12">
					<div class="tabs-container">
						<div class="tab-content">
							<div id="tab-1" class="tab-pane active">
								<div class="panel-body">
									<div class="m-b-sm">
										<form action="${BASE_PATH}/virtual/rechargequotarecord"
											autocomplete="off" method="get" id="search_form">
											
											<div class="row">												
												<div class="col-sm-2">
													<div class="form-group">
														<label class="control-label" for="date_added">开始日期</label>
														<div class="input-group date">
															<span class="input-group-addon"><i
																class="fa fa-calendar"></i></span><input type="text"
																class="form-control"  value="${begin_time}"
																name="begin_time" id="begin_time"
																onClick="WdatePicker({dateFmt:'yyyy-MM-dd',startDate:'%y-%M-%d'})">
														</div>
													</div>
												</div>
												<div class="col-sm-2">
													<div class="form-group">
														<label class="control-label" for="date_modified">结束日期</label>
														<div class="input-group date">
															<span class="input-group-addon"><i class="fa fa-calendar"></i></span>
															<input class="form-control" value="${end_time}"  name="end_time" id="end_time"
																onClick="WdatePicker({dateFmt:'yyyy-MM-dd',startDate:'%y-%M-%d'})"
																type="text">
														</div>
													</div>
												</div>
													<div class="col-sm-2">
													<div class="form-group">
														<label class="control-label">运营中心</label>
														<div class="input-group"> <select
															name="operate_centerid" class="form-control"
															onchange="$('#search_form').submit()">
															<option value="0">请选择运营中心</option>		
																<c:forEach items="${operate}" var="operate">
															<option value="${operate.id}"<c:if test="${operate.id==operate_centerid}"> selected="selected"</c:if>>${operate.name}</option>
															</c:forEach>
														</select>
														</div>
													</div>
												</div>	
												<div class="col-sm-1">
													<div class="form-group">
														<label class="control-label" for="status">&nbsp;</label>
														<div class="input-group">
															<span class="input-group-btn">
																<button type="submit" class="btn btn-primary">搜索</button>
															</span>
														</div>
													</div>
												</div>
													<div class="col-sm-1">
													<div class="form-group">
														<label class="control-label" for="status">&nbsp;</label> <label
															class="control-label" for="status">&nbsp;</label>
														<div class="input-group">
															<span class="input-group-btn"> <a
																class="btn btn-danger"
																href="javascript:exportExcel();">导出</a>
															</span>
														</div>
													</div>
												</div>
											</div>
										</form>
									</div>
									<div class="row">
										<div class="col-lg-12">
											<div class="ibox">
												<div class="ibox-content">
													<table id="cc"
														class="footable table table-stripped toggle-arrow-tiny no-paging footable-loaded default"
														data-page-size="15">
														<thead>
															<tr>														
															<th class="text-center">运营中心ID</th>														
															<th class="text-center">运营中心</th>															
															<th class="text-center">充值额度（钻）</th>													
															<th class="text-center">备注</th>											
															<th class="text-center">充值时间</th>														
															<th class="text-center">操作人</th>
															</tr>
														</thead>
														<tbody id="agent_table">
															<c:forEach items="${list}" var="list" varStatus="status">
																<tr class="text-center">
																	<td>${list.operate_center_id}</td>
																	<td>${list.operate_name}</td>
																	<td>${list.rechargequota }</td>
																	<td>${list.remark }</td>
																	<td>																																		
																	<fmt:formatDate value="${list.create_time }"  pattern="yyyy-MM-dd HH:mm:ss"></fmt:formatDate> 
																	</td>
																	<td>${list.w_name }</td>																
																</tr>
															</c:forEach>
														</tbody>
														<%@ include file="/WEB-INF/views/common/pagination.jsp"%>
													</table>
												</div>
											</div>
										</div>
									</div>
								
								</div>
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
</div>
<%@ include file="/WEB-INF/views/common/footer.jsp"%>
<script type="text/javascript" src="${ctx}/My97DatePicker/WdatePicker.js"></script>
<link href="${ctx}/css/plugins/sweetalert/sweetalert.css" rel="stylesheet">
<script src="${ctx}/js/plugins/sweetalert/sweetalert.min.js"></script>
<script src="${ctx}/js/plugins/morris/raphael-2.1.0.min.js"></script>
<script src="${ctx}/js/plugins/morris/morris.js"></script>
<script type="text/javascript">
function exportExcel()
{
	var sDate1 = $("input[name=begin_time]").val();
	var sDate2 = $("input[name=end_time]").val();
	if(sDate1==''||sDate2=='')
	{
    	swal({ 
			  title: "", 
			  text: "请选择时间", 
			  type:"success",
			  timer: 1500, 
			  showConfirmButton: false 
			});
	}
    else
    	window.location.href = "${BASE_PATH}/virtual/rechargequotarecordexcel?&begin_time=${begin_time}&end_time=${end_time}&operate_centerid=${operate_centerid}";
}
</script>
