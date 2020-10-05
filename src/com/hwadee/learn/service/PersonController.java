package com.hwadee.learn.service;

import com.hwadee.learn.dao.DaoFactory;
import com.hwadee.learn.dao.PersonDao;
import com.solidisc.web.ModuleController;
import com.hwadee.learn.pojo.Person01;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.ServletException;
import java.io.IOException;
import java.util.List;


/**
 * Person �ķ����������ࡣ
 */
public class PersonController extends ModuleController {
    private static PersonDao pdo= DaoFactory.createPersonDao();
    /**
     * ��Ա��Ϣ����
     * @param request  �ڲ�request����
     * @param response  �ڲ�response����
     * @throws IOException �ͷ��쳣
     * @throws ServletException �ͷ�servlet�쳣
     */
    private void actionUpdate(HttpServletRequest request,
                                    HttpServletResponse response)
            throws IOException, ServletException {
        HttpSession session = request.getSession();
        String error = "";
        String token = (String) session.getAttribute("token01");
        //�ж��Ƿ�Ƿ�����
        if (token == null) {
            //�Ƿ�����
            error = "�Ƿ����֣�";
            request.setAttribute("error", error);
            //response.sendRedirect("/login.jsp");
            request.getRequestDispatcher("/login.jsp").forward(request, response);
            return;
        } else {
            //�Ϸ�����
            String sid = request.getParameter("id");
            //�жϼ�¼�����Ϸ���
            if (sid == null || sid.length() < 1) {
                //�����Ƿ�
                response.sendRedirect("./home.jsp");
                //request.getRequestDispatcher("./home.jsp").forward(request,response);
                return;
            } else {
                //�����Ϸ�
                int pid = Integer.parseInt(sid);
                List<Person01> personList = (List<Person01>) session.getAttribute("personLst");
                if (pid >= personList.size()) {
                    //�����Ƿ񳬹���¼����
                    response.sendRedirect("./home.jsp");
                    //request.getRequestDispatcher("./home.jsp").forward(request,response);
                    return;
                } else {
                    //��ȡָ������Ա��Ϣ��¼
                    Person01 person01 = personList.get(pid);
                    String mdName = request.getParameter("name");
                    String mdSex = request.getParameter("sex");
                    String mdAge = request.getParameter("age");
                    String mdPrefer = request.getParameter("prefer");
                    String mdAddress = request.getParameter("address");
                    //�жϱ��Ƿ��п���
                    if (mdName == null || mdSex == null || mdAge == null || mdPrefer == null || mdAddress == null) {
                        //request.getRequestDispatcher("/modify.jsp?id="+sid).forward(request, response);
                        response.sendRedirect("/modify.jsp?id=" + sid);
                        return;
                    }

                    //�޸���Ӧ��Ŀ�������������б�
                    person01.setName(new String(mdName.getBytes("8859_1"), "gbk"));
                    person01.setSex(new String(mdSex.getBytes("8859_1"), "gbk"));
                    person01.setAge(Integer.parseInt(mdAge));
                    person01.setPrefer(new String(mdPrefer.getBytes("8859_1"), "gbk"));
                    person01.setAddress(new String(mdAddress.getBytes("8859_1"), "gbk"));

                    //�޸����ݿ��е���Ϣ
                    pdo.updatePerson(person01);

                    personList.set(pid, person01);
                    session.removeAttribute("personLst");
                    session.setAttribute("personLst", personList);
                    //request.getRequestDispatcher("/modify.jsp?id="+sid).forward(request, response);
                    response.sendRedirect("/detail.jsp?id=" + sid);
                }
            }
        }
    }
}