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
 * Person 的服务器控制类。
 */
public class PersonController extends ModuleController {
    private static PersonDao pdo= DaoFactory.createPersonDao();
    /**
     * 人员信息更新
     * @param request  内部request对象
     * @param response  内部response对象
     * @throws IOException 释放异常
     * @throws ServletException 释放servlet异常
     */
    private void actionUpdate(HttpServletRequest request,
                                    HttpServletResponse response)
            throws IOException, ServletException {
        HttpSession session = request.getSession();
        String error = "";
        String token = (String) session.getAttribute("token01");
        //判断是否非法入侵
        if (token == null) {
            //非法入侵
            error = "非法入侵！";
            request.setAttribute("error", error);
            //response.sendRedirect("/login.jsp");
            request.getRequestDispatcher("/login.jsp").forward(request, response);
            return;
        } else {
            //合法访问
            String sid = request.getParameter("id");
            //判断记录索引合法性
            if (sid == null || sid.length() < 1) {
                //索引非法
                response.sendRedirect("./home.jsp");
                //request.getRequestDispatcher("./home.jsp").forward(request,response);
                return;
            } else {
                //索引合法
                int pid = Integer.parseInt(sid);
                List<Person01> personList = (List<Person01>) session.getAttribute("personLst");
                if (pid >= personList.size()) {
                    //索引是否超过记录数量
                    response.sendRedirect("./home.jsp");
                    //request.getRequestDispatcher("./home.jsp").forward(request,response);
                    return;
                } else {
                    //获取指定的人员信息记录
                    Person01 person01 = personList.get(pid);
                    String mdName = request.getParameter("name");
                    String mdSex = request.getParameter("sex");
                    String mdAge = request.getParameter("age");
                    String mdPrefer = request.getParameter("prefer");
                    String mdAddress = request.getParameter("address");
                    //判断表单是否有空项
                    if (mdName == null || mdSex == null || mdAge == null || mdPrefer == null || mdAddress == null) {
                        //request.getRequestDispatcher("/modify.jsp?id="+sid).forward(request, response);
                        response.sendRedirect("/modify.jsp?id=" + sid);
                        return;
                    }

                    //修改相应条目，并重新设置列表
                    person01.setName(new String(mdName.getBytes("8859_1"), "gbk"));
                    person01.setSex(new String(mdSex.getBytes("8859_1"), "gbk"));
                    person01.setAge(Integer.parseInt(mdAge));
                    person01.setPrefer(new String(mdPrefer.getBytes("8859_1"), "gbk"));
                    person01.setAddress(new String(mdAddress.getBytes("8859_1"), "gbk"));

                    //修改数据库中的信息
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