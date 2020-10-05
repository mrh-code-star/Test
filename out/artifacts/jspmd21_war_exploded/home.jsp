<%@ page import="com.hwadee.learn.domain.Person" %>
<%@ page import="java.util.List" %>
<%@ page import="java.text.SimpleDateFormat" %>
<%@ page contentType="text/html;charset=gb2312" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<%
    String error = "";
    String token = (String)session.getAttribute("token01");
    List<Person> personList;

    //�ж��Ƿ�Ϸ���¼     {���1��ͨ��login.jspҳ���¼������  ���2��������ҳ��Ϸ���ת����
    if(token==null) {
        //�Ƿ�����
        error = "�Ƿ����֣�";
        request.setAttribute("error", error);
        //response.sendRedirect("/login.jsp");
        request.getRequestDispatcher("/login.jsp").forward(request,response);
        return;
    } else {
        //personList = (List<Person>)session.getAttribute("personLst");
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String time = df.format(System.currentTimeMillis());
%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=gb2312">
<title>��Ա�б�</title></head>


<body>
<table width="90%" border="0" align="center" cellpadding="0" cellspacing="0">
  <tr>
    <td height="40" colspan="6"><div align="center"><H2>��Ա�б�</H2>&nbsp;</div></td>
  </tr><tr align="left">
    <td>&nbsp;&nbsp;</td>
    <td>&nbsp;</td>
    <td colspan="3">�鿴��Ա��ϸ����</td><td align="right">��ǰʱ�� <%=time%>&nbsp;&nbsp;<input type="button" name="logout" value="�� ��" onClick="JavaScript:window.location.href='./accountctl?command=Logout'"></td>
    </tr>
  <tr>
    <td height="2" colspan="6"><hr width="100%" class="hr" /></td>
  </tr>
</table>
  <br>
  <table width="75%" border="1" align="center" cellpadding="0" cellspacing="0">
  <tr align="left">
    <td width="59"><div align="center"><strong>���</strong></div></td>
    <td width="122"><div align="center"><strong>����</strong></div></td>
    <td width="63"><div align="center"><strong>�Ա�</strong></div></td>
    <td width="126"><div align="center"><strong>����</strong></div></td>
    <td width="66"><div align="center"><strong>����</strong></div></td>
  </tr>
      <%
//          int i=0;
//          for(Person person:personList){
//              pageContext.setAttribute("person", person);//page��
      %>
      <c:forEach items="${personLst}" var="person" varStatus="status">
  <tr align="left">
    <td><div align="center">${status.count}</div></td>
    <td><div align="center">${person.name}</div></td>
    <td><div align="center">${person.sex}</div></td>
    <td><div align="center">${person.age}</div></td>
    <td><div align="center"><a href="detail.jsp?id=${status.count-1}" target="_blank">����</a>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<a href="modify.jsp?id=${status.count-1}" target="_blank">�޸�</a></div></td>
  </tr>
      </c:forEach>
      <%
//              i++;
//          }
      %>
</table>
</body>
</html>

<%
    }
%>


