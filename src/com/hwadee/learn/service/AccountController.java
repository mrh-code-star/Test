package com.hwadee.learn.service;

import com.hwadee.learn.dao.AccountDao;
import com.hwadee.learn.dao.DaoFactory;
import com.hwadee.learn.dao.PersonDao;
import com.solidisc.web.ModuleController;
import com.hwadee.learn.pojo.Account;
import com.hwadee.learn.pojo.Person01;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.servlet.ServletException;
import java.io.IOException;
import java.util.List;


/**
 * Account 的服务器控制类。
 */
public class AccountController extends ModuleController {
    private static AccountDao ado= DaoFactory.createAccountDao();
    private static PersonDao pdo= DaoFactory.createPersonDao();

    /**
     * 完成用户登录的所有服务器端处理
     * @param request  内部request对象
     * @param response  内部response对象
     * @throws IOException 释放异常
     * @throws ServletException 释放servlet异常
     */
    private void actionLogon(HttpServletRequest request,
                                    HttpServletResponse response)
            throws IOException, ServletException {
        HttpSession session = request.getSession();
        String error = "";

        //读取用户登录信息
        String loginname;
        String passwd;
        loginname = request.getParameter("account");
        passwd = request.getParameter("passwd");
        //判断是否合法登录     { 入口1：通过login.jsp页面登录进来 }
        if((loginname==null || passwd==null) || (loginname.length()<3 || passwd.length()<3)) {
            //账号密码为空
            error = "用户名或密码为空！";
            request.setAttribute("error", error);
            //response.sendRedirect("/login.jsp");
            request.getRequestDispatcher("/login.jsp").forward(request,response);
            return;
        } else {
            //合法登录
            //通过login.jsp 账号登录进入
            Account account =new Account();
            account.setAccount(loginname);
            account.setPassword(passwd);
            account = ado.getAccountByUsrPwd(account);

            //判断是否合法账号
            if(account!=null){
                //合法账号
                session.setAttribute("token01", loginname);     //设置安全令牌到会话
                //查询列表页面需要的信息列表
                //List<Person> personList = getPersonList();
                List<Person01> personList = pdo.getPersonAll();
                session.setAttribute("personLst",personList);    //保存人员信息列表到会话
                response.sendRedirect("/home.jsp");
                return;
            }else{
                //非法账号
                error = "用户名或密码错误！";
                request.setAttribute("error", error);
                // response.sendRedirect("/login.jsp");
                request.getRequestDispatcher("/login.jsp").forward(request,response);
                return;
            }
        }

    }

    /**
     * 处理用户登出系统。
     * @param request  内部request对象
     * @param response  内部response对象
     * @throws IOException 释放异常
     * @throws ServletException 释放servlet异常
     */
    private void actionLogout(HttpServletRequest request,
                                    HttpServletResponse response)
            throws IOException,ServletException{
        HttpSession session = request.getSession();
        //清理session
        //session.removeAttribute("token01");
        //session.removeAttribute("personLst");
        session.invalidate();
        response.sendRedirect("/login.jsp");
    }

    /**
     * 模拟从数据库读取信息列表，并放到顺序列表ArrayList中
     * @return 人员列表到List 顺序列表
     */
    /*
    public List<Person> getPersonList(){
        //为主页面提供人员列表
        List <Person> personLst = new LinkedList<Person>();
        personLst.add(new Person(1,"张三","男",22,"足球，游泳","四川大学望江校区三号楼"));
        personLst.add(new Person(3,"李四","女",21,"羽毛球","四川大学望江校区五号楼"));
        personLst.add(new Person(4,"唐小林","男",23,"篮球","成都理工大学5号宿舍楼"));
        personLst.add(new Person(7,"王诗妤","女",22,"写作，绘画","四川师范大学2号楼"));
        return personLst;
    }
    */
}