<%@ page contentType="text/html;charset=gb2312" language="java" %>
<%
    String error = (String)request.getAttribute("error");
    if(error==null){
        error = "";
    }
%>

<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=gb2312">
<title>�û���¼</title>
</head>

<body>
<br>
<br>
<br>

<form action="./accountctl?command=Logon" method="post" name="login" target="_self" id="login">
<table width="300" border="1" align="center">
  <tr>
    <td width="63">�� ��</td>
    <td >&nbsp;<input type="text" name="account">    </td>
  </tr>
  <tr>
    <td>�� ��</td>
    <td>&nbsp;<input type="password" name="passwd"></td>
  </tr>
</table>

<center>
    <p>&nbsp;<%=error%></p>
  <input type="submit" name="Submit" value="�� ��">  &nbsp;&nbsp;&nbsp;
  
  <input name="Closed" type="button" id="Closed" value="�� ��">
</center>
</form>
</body>
</html>
