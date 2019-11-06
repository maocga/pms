<%@ page language="java"  pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core_1_1" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=gb2312">
<title>客户信息管理</title>
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/skin/css/base.css">
</head>
<body leftmargin="8" topmargin="8" background='${pageContext.request.contextPath}/skin/images/allbg.gif'>

<!--  快速转换位置按钮  -->
<table width="98%" border="0" cellpadding="0" cellspacing="1" bgcolor="#D1DDAA" align="center">
<tr>
 <td height="26" background="${pageContext.request.contextPath}/skin/images/newlinebg3.gif">
  <table width="58%" border="0" cellspacing="0" cellpadding="0">
  <tr>
  <td >
    当前位置:客户信息管理>>客户信息
 </td>
  <td>
    <input type='button' class="coolbg np" onClick="location='${pageContext.request.contextPath}/customer-add.jsp';" value='添加客户信息' />
 </td>
 </tr>
</table>
</td>
</tr>
</table>

<!--  搜索表单  -->
<form name='form3' id="serachForm" action='${pageContext.request.contextPath}/customer/findCustOrder' method='post'>
    <input type='hidden' name='dopost' value='' />
    <input type='hidden' name='_method' value='put' />
<table width='98%'  border='0' cellpadding='1' cellspacing='1' bgcolor='#CBD8AC' align="center" style="margin-top:8px">
  <tr bgcolor='#EEF4EA'>
    <td background='${pageContext.request.contextPath}/skin/images/wbg.gif' align='center'>
      <table border='0' cellpadding='0' cellspacing='0'>
        <tr>
          <td width='90' align='center'>搜索条件：</td>
          <td width='160'>
          <select id="SerachClass" name='cid' style='width:150px;'>
          <option value='0'>选择类型...</option>
          	<option value='1'>客户所在公司名称</option>
          	<option value='2'>联系人姓名</option>
          </select>
        </td>
        <td width='70'>
          关键字：
        </td>
        <td width='160'>
          	<input id="keyword" type='text' name='keyword' value='' style='width:120px' />
        </td>
        <td width='110'>
    		<select id="orderClass" name='orderby' style='width:120px'>
            <option value='id'>排序...</option>
            <option value='positive'>正序</option>
            <option value='negative'>反序</option>
      	</select>
        </td>
        <td>
          &nbsp;&nbsp;&nbsp;<input onclick="serach()" name="imageField" type="image" src="${pageContext.request.contextPath}/skin/images/frame/search.gif" width="45" height="20" border="0" class="np" />
        </td>
       </tr>
      </table>
    </td>
  </tr>
</table>
</form>
<!--  内容列表   -->
<form name="form2">

<table id="listTab" width="98%" border="0" cellpadding="2" cellspacing="1" bgcolor="#D1DDAA" align="center" style="margin-top:8px">
<tr bgcolor="#E7E7E7">
	<td height="24" colspan="12" background="${pageContext.request.contextPath}/skin/images/tbg.gif">&nbsp;需求列表&nbsp;</td>
</tr>
<tr align="center" bgcolor="#FAFAF1" height="22">
	<td width="4%">选择</td>
	<td width="6%">序号</td>
	<td width="10%">联系人</td>
	<td width="10%">公司名称</td>
	<td width="8%">添加时间</td>
	<td width="8%">联系电话</td>
	<td width="10%">操作</td>
</tr>

    <c:forEach items="${customerList }" var="cust" varStatus="i">
        <tr align='center' bgcolor="#FFFFFF" onMouseMove="javascript:this.bgColor='#FCFDEE';" onMouseOut="javascript:this.bgColor='#FFFFFF';" height="22" >
            <td><input name="id" type="checkbox" id="id" value="${cust.id}" class="np"></td>
            <td>${i.count}</td>
            <td>${cust.companyperson}</td>
            <td align="center">${cust.comname}</td>
            <td><fmt:formatDate value="${cust.addtime}" timeStyle="yy-MM-dd"/> </td>
            <td>${cust.comphone}</td>
            <td><a href="${pageContext.request.contextPath}/customer/update/${cust.id}">编辑</a> | <a href="${pageContext.request.contextPath}/customer/detil/${cust.id}">查看详情</a></td>
        </tr>
    </c:forEach>

<tr bgcolor="#FAFAF1">
<td height="28" colspan="12">
	&nbsp;
	<a href="javascript:allElection()" class="coolbg">全选</a>
	<a href="javascript:reverseElection()" class="coolbg">反选</a>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
	<a href="javascript:del()" class="coolbg">&nbsp;删除&nbsp;</a>
	<a href="" class="coolbg">&nbsp;导出Excel&nbsp;</a>
</td>
</tr>
<tr align="right" bgcolor="#EEF4EA">
	<td height="36" colspan="12" align="center"><!--翻页代码 --></td>
</tr>
</table>

</form>
  

</body>
    <
<script type="text/javascript" src="${pageContext.request.contextPath}/skin/js/frame/jquery-3.1.1.js"></script>

<script type="text/javascript">
    function serach() {
        $("#serachForm").submit();
    }
    function allElection() {
        $("#listTab input").prop("checked","checked");
    }
    function reverseElection() {
        $("#listTab").find("input").each(function () {
            $(this).prop("checked",!$(this).prop("checked"));
        });
    }
    function del() {
        if(confirm("确认删除？")){
            var ids = [];
            $("#listTab").find("input:checked").each(function () {
                var id = $(this).val();
                ids.push(id);
            });

            $.ajax({
                url : "${pageContext.request.contextPath}/customer/delById",
                data : {"ids":ids,"_method":"delete"},
                type : "post",
                dataType:"json",
                success : function (map) {
                    if (map.hashCode == 200){
                        window.location = "${pageContext.request.contextPath}/customer/findAllCustomer";
                    }else{
                        alert(map.msg);
                    }
                }
            });
        }
    }
</script>
</html>