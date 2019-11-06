<%@ page language="java"  pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"  %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=gb2312">
<title>附件管理</title>
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/skin/css/base.css">
	<script type="text/javascript" src="${pageContext.request.contextPath}/skin/js/frame/jquery-3.1.1.js"></script>
	<script type="text/javascript">
		function search(pageNum,pages) {
		    var cid = $("#cid").val();
		    var keyword = $("#keyword").val();
			alert(pages);
	    	window.location = "${pageContext.request.contextPath}/baoxiao/mybaoxiaoList/" + pageNum
						+ "?pages="+ pages +"&searchCriteria="+cid+"&keyword="+keyword;

        }
        function subimiFormdata() {
			$("#serForm").submit();
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
    当前位置:个人报销管理>>报销列表
 </td>
  <td>
    <input type='button' class="coolbg np" onClick="location='${pageContext.request.contextPath}/mybaoxiao-add.jsp';" value='添加报销' />
 </td>
 </tr>
</table>
</td>
</tr>
</table>
<%--搜索表单--%>
<form name='form3' id="serForm" method="get" action="${pageContext.request.contextPath}/baoxiao/mybaoxiaoList/1">
	<input type='hidden' name='pages' value='-1' />
	<table width='98%'   border='0' cellpadding='1' cellspacing='1' bgcolor='#CBD8AC' align="center" style="margin-top:8px">
		<tr bgcolor='#EEF4EA'>
			<td background='${pageContext.request.contextPath}/skin/images/wbg.gif' align='center'>
				<table border='0' cellpadding='0' cellspacing='0'>
					<tr>
						<td width='90' align='center'>搜索条件：</td>
						<td width='160'>
							<select id="cid" name='searchCriteria' style='width:150px;'>
								<option value='0'>选择类型...</option>
								<option value='1'>支出类型</option>
								<option value='2'>备注信息</option>
							</select>
						</td>
						<td width='70'>
							关键字：
						</td>
						<td width='160'>
							<input id="keyword" type='text' VALUE="" name='keyword' style='width:120px' />
						</td>

						<td>
							&nbsp;&nbsp;&nbsp;<input onclick="subimiFormdata()" name="imageField" type="image" src="${pageContext.request.contextPath}/skin/images/frame/search.gif" width="45" height="20" border="0" class="np" />
						</td>
					</tr>
				</table>
			</td>
		</tr>
	</table>
</form>

<!--  内容列表   -->
<form name="form2">

<table width="98%"  cellpadding="2" cellspacing="1" bgcolor="#D1DDAA" align="center" style="margin-top:8px">
<tr bgcolor="#E7E7E7">
	<td height="24" colspan="12" background="${pageContext.request.contextPath}/skin/images/tbg.gif">&nbsp;附件列表&nbsp;</td>
</tr>
<tr align="center" bgcolor="#FAFAF1" height="22">
	<td width="4%">选择</td>
	<td width="20%">编号</td>
	<td width="6%">总金额</td>
	<td width="10%">使用时间</td>
	<td width="6%">支出类型</td>
	<td width="34%">备注信息</td>
	<td width="10%">审批状态</td>
	<td width="10%">操作</td>
</tr>

	<c:forEach items="${baoxiao}" var="bs">
		<tr align='center' bgcolor="#FFFFFF" onMouseMove="javascript:this.bgColor='#FCFDEE';" onMouseOut="javascript:this.bgColor='#FFFFFF';" height="22" >
			<td><input name="id" type="checkbox" id="id" value="101" class="np"></td>
			<td>${bs.bxid}</td>
			<td>${bs.totalmoney}</td>
			<td align="center"><a href=''><u> <fmt:formatDate value="${bs.bxtime}" pattern="yyyy-MM-dd"></fmt:formatDate> </u></a></td>
			<td>${bs.paymode}</td>
			<td>${bs.bxremark}</td>

			<c:if test="${bs.bxstatus == 0}">
				<td>未审批</td>
				<td><a href="mybaoxiao-edit.jsp?bxid=${bs.bxid}">编辑</a> </td>
			</c:if>
			<c:if test="${bs.bxstatus == 1}">
				<td>审批通过</td>
				<td><a href="javascript:void(0)" style="pointer-events:none;color: grey" >编辑</a> </td>
			</c:if>
			<c:if test="${bs.bxstatus == 2}">
				<td>驳回</td>
				<td><a href="mybaoxiao-edit.jsp?bxid=${bs.bxid}">编辑</a> </td>
			</c:if>
			<c:if test="${bs.bxstatus == 3}">
				<td>审批并付款</td>
				<td><a href="javascript:void(0)" style="pointer-events:none;color: grey" >编辑</a> </td>
			</c:if>

		</tr>
	</c:forEach>


<tr align="right" bgcolor="#EEF4EA">
	<td height="36" colspan="12" align="center">
		<a href="javascript:search(1,'${pages}')" class="coolbg">首页</a>
		<a href="javascript:search('${pageNum - 1}','${pages}')" class="coolbg">上一页</a>
		<c:forEach items="${navigatePages}" var="np">
			<a href="javascript:search('${np}','${pages}')"
			   <c:if test="${np == pageNum}">class="coolbg2"</c:if>
			   <c:if test="${np != pageNum}">class="coolbg"</c:if>>
					${np}
			</a>
		</c:forEach>
		<a href="javascript:search('${pageNum + 1}','${pages}')" class="coolbg">下一页</a>
		<a href="javascript:search('${pageNum}','${pages}')" class="coolbg">尾页</a>

	</td>
</tr>
</table>

</form>
  

</body>
</html>