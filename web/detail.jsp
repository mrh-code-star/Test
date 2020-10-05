<%@ page import="com.hwadee.learn.pojo.Person01" %>
<%@ page import="java.util.List" %>
<%@ page contentType="text/html;charset=gb2312" language="java" %>

<%
    String error ="";
    String token = (String)session.getAttribute("token01");
    //�ж��Ƿ�Ƿ�����
    if(token==null ) {
        //�Ƿ�����
        error = "�Ƿ����֣�";
        request.setAttribute("error", error);
        //response.sendRedirect("/login.jsp");
        request.getRequestDispatcher("/login.jsp").forward(request,response);
        return;
    } else {
        //�Ϸ�����
        String sid= request.getParameter("id");
        //�жϼ�¼�����Ϸ���
        if(sid==null || sid.length()<1){
            //�����Ƿ�
            response.sendRedirect("./home.jsp");
            //request.getRequestDispatcher("./home.jsp").forward(request,response);
            return;
        }else{
            //�����Ϸ�
            int pid = Integer.parseInt(sid);
            List<Person01> personList = (List<Person01>)session.getAttribute("personLst");
            if(pid>=personList.size()){
                //�����Ƿ񳬹���¼����
                response.sendRedirect("./home.jsp");
                //request.getRequestDispatcher("./home.jsp").forward(request,response);
                return;
            }else{
                Person01 person = personList.get(pid);
                pageContext.setAttribute("person", person);//page��
%>

<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=gb2312">
<title>�û�����</title>
</head>


<body onload="window.opener.location.href='home.jsp'">
<br>
<br>
<br>
<table width="300" border="1" align="center" cellspacing="1" bordercolor="#336699">
  <tr>
    <th width="59" scope="row">����</th>
    <td width="228">&nbsp;<%=person.getName()%></td>
  </tr>
  <tr>
    <th scope="row">�Ա�</th>
    <td>&nbsp;<%=person.getSex()%></td>
  </tr>
  <tr>
    <th scope="row">����</th>
    <td>&nbsp;${person.age}</td>
  </tr>
  <tr>
    <th scope="row">����</th>
    <td>&nbsp;${person.prefer}</td>
  </tr>
  <tr>
    <th scope="row">��ַ</th>
    <td>&nbsp;${person.address}</td>
  </tr>
</table>
<p>&nbsp;</p>
<center>
  <input type="button" name="modify" value="�� ��" onClick="JavaScript:window.location.href='./modify.jsp?id=<%=sid%>'">&nbsp;&nbsp;&nbsp;
  <input type="button" name="logout" value="�ر�" onClick="JavaScript:window.open('', '_self');window.close();">
</center>
</body>
</html>
<%
    }
    }
    }
%>


