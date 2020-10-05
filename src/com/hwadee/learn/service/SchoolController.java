package com.hwadee.learn.service;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(urlPatterns = "/gradeItemInfo")
public class SchoolController extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String gradeId = req.getParameter("gradeId");

        GradeService gradeService = new GradeService();
        Set<Grade> set = gradeService.getGradeList(Integer.parseInt(gradeId));
        req.setAttribute("gradeList",set);
        req.getRequestDispatcher("gradeInfo.jsp").forward(req,resp);
    }
}