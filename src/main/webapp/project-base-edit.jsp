<%@ page language="java"  pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=gb2312">
<title>编辑项目信息</title>
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/skin/css/base.css">
	<script type="text/javascript" src="${pageContext.request.contextPath}/skin/js/frame/jquery-3.1.1.js"></script>
    <script type="text/javascript" src="${pageContext.request.contextPath}/My97DatePicker/WdatePicker.js"></script>
	<script type="text/javascript">
		function changeCom() {
            var b =$("#comname").val();
			$.ajax({
               type : "get",
               url  : "${pageContext.request.contextPath}/customer/findcompnameById",
               data : {"id":b},
               dataType : "json",
               success : function (resourt) {
                   $("#comper").val(resourt.companyperson);
               } 
            });
        }
        
        function commit() {
            $("#upateform").submit();
        }
	</script>
</head>
<body leftmargin="8" topmargin="8" background='${pageContext.request.contextPath}/skin/images/allbg.gif'>

<!--  快速转换位置按钮  -->
<table width="98%" border="0" cellpadding="0" cellspacing="1" bgcolor="#D1DDAA" align="center">
<tr>
 <td height="26" background="${pageContext.request.contextPath}/skin/images/newlinebg3.gif">
  <table width="58%" border="0" cellspacing="0" cellpadding="0">
  <tr>
  <td >
    当前位置:项目管理>>编辑项目基本信息
 </td>
 </tr>
</table>
</td>
</tr>
</table>

<form id="upateform" name="form2" action="${pageContext.request.contextPath}/project/update" method="post">

<table width="98%" border="0" cellpadding="2" cellspacing="1" bgcolor="#D1DDAA" align="center" style="margin-top:8px">
<tr bgcolor="#E7E7E7">
	<td height="24" colspan="12" background="${pageContext.request.contextPath}/skin/images/tbg.gif">&nbsp;编辑项目信息&nbsp;</td>
	<input type="hidden" name="pid" value="${project.pid}">
</tr>
<tr >
	<td align="right" bgcolor="#FAFAF1" height="22">项目名称：</td>
	<td align='left' bgcolor="#FFFFFF" onMouseMove="javascript:this.bgColor='#FCFDEE';" onMouseOut="javascript:this.bgColor='#FFFFFF';" height="22">
		<input name="pname" value="${project.pname}"/>
	</td>
	<td align="right" bgcolor="#FAFAF1" height="22">客户公司名称：</td>
	<td align='left' bgcolor="#FFFFFF" onMouseMove="javascript:this.bgColor='#FCFDEE';" onMouseOut="javascript:this.bgColor='#FFFFFF';" height="22">
		<select id="comname" onchange="changeCom()" name="comname">
			<c:forEach items="${customer}" var="cus">
				<option <c:if test="${project.customer.id == cus.id}">selected</c:if> value="${cus.id}">${cus.comname}</option>
			</c:forEach>
		</select>
	</td>
</tr>
<tr >
	<td align="right" bgcolor="#FAFAF1" height="22">客户方负责人：</td>
	<td align='left' bgcolor="#FFFFFF" onMouseMove="javascript:this.bgColor='#FCFDEE';" onMouseOut="javascript:this.bgColor='#FFFFFF';" height="22">
		<input id="comper" name="comper" readonly value="${project.customer.companyperson}"/>
	</td>
	<td align="right" bgcolor="#FAFAF1" height="22">项目经理：</td>
	<td align='left' bgcolor="#FFFFFF" onMouseMove="javascript:this.bgColor='#FCFDEE';" onMouseOut="javascript:this.bgColor='#FFFFFF';" height="22">
		<select name="empFk">
			<c:forEach items="${emp}" var="employee">
				<option <c:if test="${project.emp.eid == employee.eid}">selected</c:if> value="${employee.eid}">${employee.ename}</option>
			</c:forEach>
		</select>
	</td>
</tr>
<tr >
	<td align="right" bgcolor="#FAFAF1" height="22">开发人数：</td>
	<td align='left' bgcolor="#FFFFFF" onMouseMove="javascript:this.bgColor='#FCFDEE';" onMouseOut="javascript:this.bgColor='#FFFFFF';" height="22">
		<input name="empcount" value="${project.empcount}">人</td>
	<td align="right" bgcolor="#FAFAF1" height="22">开始时间：</td>
	<td align='left' bgcolor="#FFFFFF" onMouseMove="javascript:this.bgColor='#FCFDEE';" onMouseOut="javascript:this.bgColor='#FFFFFF';" height="22">
		<input name="starttime" value="<fmt:formatDate value="${project.starttime}" timeStyle="yy-MM-dd"></fmt:formatDate>" readonly onclick="WdatePicker()"/>
	</td>
</tr>
<tr >
	<td align="right" bgcolor="#FAFAF1" height="22">立项时间：</td>
	<td align='left' bgcolor="#FFFFFF" onMouseMove="javascript:this.bgColor='#FCFDEE';" onMouseOut="javascript:this.bgColor='#FFFFFF';" height="22">
		<input name="buidtime" value="<fmt:formatDate value="${project.buildtime}" timeStyle="yy-MM-dd"></fmt:formatDate>" readonly onclick="WdatePicker()"/></td>
	<td align="right" bgcolor="#FAFAF1" height="22">预估成本：</td>
	<td align='left' bgcolor="#FFFFFF" onMouseMove="javascript:this.bgColor='#FCFDEE';" onMouseOut="javascript:this.bgColor='#FFFFFF';" height="22">
		<input name="cost" value="${project.cost}"/>万</td>
</tr>
<tr >
	<td align="right" bgcolor="#FAFAF1" height="22">级别：</td>
	<td align='left' bgcolor="#FFFFFF" onMouseMove="javascript:this.bgColor='#FCFDEE';" onMouseOut="javascript:this.bgColor='#FFFFFF';" height="22">
		<select name="level">
			<option <c:if test="${project.level == 1}">selected</c:if> value=1>紧急</option>
			<option <c:if test="${project.level == 2}">selected</c:if> value=2>一般</option>
			<option <c:if test="${project.level == 3}">selected</c:if> value=3>暂缓</option>
		</select>
	</td>
	<td align="right" bgcolor="#FAFAF1" height="22">计划完成时间：</td>
	<td align='left' bgcolor="#FFFFFF" onMouseMove="javascript:this.bgColor='#FCFDEE';" onMouseOut="javascript:this.bgColor='#FFFFFF';" height="22">
		<input name="endtime" value="<fmt:formatDate value="${project.endtime}" timeStyle="yy-MM-dd"></fmt:formatDate>" readonly onclick="WdatePicker()"/></td>
</tr>

<tr >
	<td align="right" bgcolor="#FAFAF1" >备注：</td>
	<td colspan=3 align='left' bgcolor="#FFFFFF" onMouseMove="javascript:this.bgColor='#FCFDEE';" onMouseOut="javascript:this.bgColor='#FFFFFF';" >
		<textarea name="remark" rows=15 cols=130>${project.remark}</textarea>
	</td>
</tr>


<tr bgcolor="#FAFAF1">
<td height="28" colspan=4 align=center>
	&nbsp;
	<a href="javascript:commit()" class="coolbg">保存</a>
	<a href="javascript:history.go(-1)" class="coolbg">返回</a>
</td>
</tr>
</table>

</form>
  

</body>
</html>