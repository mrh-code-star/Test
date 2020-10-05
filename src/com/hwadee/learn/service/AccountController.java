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
 * Account �ķ����������ࡣ
 */
public class AccountController extends ModuleController {
    private static AccountDao ado= DaoFactory.createAccountDao();
    private static PersonDao pdo= DaoFactory.createPersonDao();

    /**
     * ����û���¼�����з������˴���
     * @param request  �ڲ�request����
     * @param response  �ڲ�response����
     * @throws IOException �ͷ��쳣
     * @throws ServletException �ͷ�servlet�쳣
     */
    private void actionLogon(HttpServletRequest request,
                                    HttpServletResponse response)
            throws IOException, ServletException {
        HttpSession session = request.getSession();
        String error = "";

        //��ȡ�û���¼��Ϣ
        String loginname;
        String passwd;
        loginname = request.getParameter("account");
        passwd = request.getParameter("passwd");
        //�ж��Ƿ�Ϸ���¼     { ���1��ͨ��login.jspҳ���¼���� }
        if((loginname==null || passwd==null) || (loginname.length()<3 || passwd.length()<3)) {
            //�˺�����Ϊ��
            error = "�û���������Ϊ�գ�";
            request.setAttribute("error", error);
            //response.sendRedirect("/login.jsp");
            request.getRequestDispatcher("/login.jsp").forward(request,response);
            return;
        } else {
            //�Ϸ���¼
            //ͨ��login.jsp �˺ŵ�¼����
            Account account =new Account();
            account.setAccount(loginname);
            account.setPassword(passwd);
            account = ado.getAccountByUsrPwd(account);

            //�ж��Ƿ�Ϸ��˺�
            if(account!=null){
                //�Ϸ��˺�
                session.setAttribute("token01", loginname);     //���ð�ȫ���Ƶ��Ự
                //��ѯ�б�ҳ����Ҫ����Ϣ�б�
                //List<Person> personList = getPersonList();
                List<Person01> personList = pdo.getPersonAll();
                session.setAttribute("personLst",personList);    //������Ա��Ϣ�б��Ự
                response.sendRedirect("/home.jsp");
                return;
            }else{
                //�Ƿ��˺�
                error = "�û������������";
                request.setAttribute("error", error);
                // response.sendRedirect("/login.jsp");
                request.getRequestDispatcher("/login.jsp").forward(request,response);
                return;
            }
        }

    }

    /**
     * �����û��ǳ�ϵͳ��
     * @param request  �ڲ�request����
     * @param response  �ڲ�response����
     * @throws IOException �ͷ��쳣
     * @throws ServletException �ͷ�servlet�쳣
     */
    private void actionLogout(HttpServletRequest request,
                                    HttpServletResponse response)
            throws IOException,ServletException{
        HttpSession session = request.getSession();
        //����session
        //session.removeAttribute("token01");
        //session.removeAttribute("personLst");
        session.invalidate();
        response.sendRedirect("/login.jsp");
    }

    /**
     * ģ������ݿ��ȡ��Ϣ�б����ŵ�˳���б�ArrayList��
     * @return ��Ա�б�List ˳���б�
     */
    /*
    public List<Person> getPersonList(){
        //Ϊ��ҳ���ṩ��Ա�б�
        List <Person> personLst = new LinkedList<Person>();
        personLst.add(new Person(1,"����","��",22,"������Ӿ","�Ĵ���ѧ����У������¥"));
        personLst.add(new Person(3,"����","Ů",21,"��ë��","�Ĵ���ѧ����У�����¥"));
        personLst.add(new Person(4,"��С��","��",23,"����","�ɶ�����ѧ5������¥"));
        personLst.add(new Person(7,"��ʫ�","Ů",22,"д�����滭","�Ĵ�ʦ����ѧ2��¥"));
        return personLst;
    }
    */
}