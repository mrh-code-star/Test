<%@ page import="com.hwadee.learn.pojo.Person01" %>
<%@ page import="java.util.List" %>
<%@ page contentType="text/html;charset=gb2312" language="java" %>

<%
    String error ="";
    String token = (String)session.getAttribute("token01");
    //判断是否非法入侵
    if(token==null ) {
        //非法入侵
        error = "非法入侵！";
        request.setAttribute("error", error);
        //response.sendRedirect("/login.jsp");
        request.getRequestDispatcher("/login.jsp").forward(request,response);
        return;
    } else {
        //合法访问
        String sid= request.getParameter("id");
        //判断记录索引合法性
        if(sid==null || sid.length()<1){
            //索引非法
            response.sendRedirect("./home.jsp");
            //request.getRequestDispatcher("./home.jsp").forward(request,response);
            return;
        }else{
            //索引合法
            int pid = Integer.parseInt(sid);
            List<Person01> personList = (List<Person01>)session.getAttribute("personLst");
            if(pid>=personList.size()){
                //索引是否超过记录数量
                response.sendRedirect("./home.jsp");
                //request.getRequestDispatcher("./home.jsp").forward(request,response);
                return;
            }else{
                Person01 person01 = personList.get(pid);
                pageContext.setAttribute("person", person01);//page域
%>

<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=gb2312">
<title>修改用户资料</title>
</head>


<body>
<br>
<br>
<br>
<form action="./personctl?command=Update" method="post" name="login" target="_self" id="modify">
<table width="300" border="1" align="center" cellspacing="1" bordercolor="#336699">
  <tr>
    <th width="59" scope="row">姓名</th>
    <td width="228">&nbsp;<input type="text" name="name" value="${person.name}"></td>
  </tr>
  <tr>
    <th scope="row">性别</th>
    <td>&nbsp;<input type="text" name="sex" value="${person.sex}"></td>
  </tr>
  <tr>
    <th scope="row">年龄</th>
    <td>&nbsp;<input type="text" name="age" value="${person.age}"></td>
  </tr>
  <tr>
    <th scope="row">爱好</th>
    <td>&nbsp;<input type="text" name="prefer" value="${person.prefer}"></td>
  </tr>
  <tr>
    <th scope="row">地址</th>
    <td>&nbsp;<input type="text" name="address" value="${person.address}"></td>
  </tr>
</table>
<p>&nbsp;</p>
<center>
  <input type="hidden" name="id" value="<%=sid%>">
  <input type="submit" name="modify" value="确 认">&nbsp;&nbsp;&nbsp;
  <input type="button" name="logout" value="关闭" onClick="JavaScript:window.open('', '_self');window.close();">
</center>
</form>
</body>
</html>
<%
            }
        }
    }
%>