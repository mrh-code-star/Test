package com.hwadee.learn.dao.mybatis;

import com.hwadee.learn.common.MybatisUtil;
import com.hwadee.learn.dao.AccountDao;
import com.hwadee.learn.pojo.Account;
import org.apache.ibatis.session.SqlSession;

import java.util.LinkedList;
import java.util.List;

/**
 * Created by Administrator on 2020/3/1.
 */
public class AccountDaoImpl implements AccountDao {
    @Override
    public Account getAccountByUsrPwd(Account account) {
        Account act =null;
        SqlSession sqlSession = null;
        List result=new LinkedList();
        try {
            sqlSession = MybatisUtil.getSqlSession();
            act = sqlSession.selectOne("getActByUsrPwd",account);
        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            MybatisUtil.closeSqlSession();
        }
        return act;
    }

    public static void main(String[] args) {
        AccountDaoImpl adi = new AccountDaoImpl();
        Account act = new Account();
        String username ="lisi";
        String password = "snksnk";
        act.setAccount(username);
        act.setPassword(password);
        act = adi.getAccountByUsrPwd(act);
        if(act==null){
            System.out.println("�����ڸ��û��˺ţ�");
        }else {
            System.out.println("�û���:" + act.getAccount() + "  ����:" + act.getPassword());
        }

    }
}
