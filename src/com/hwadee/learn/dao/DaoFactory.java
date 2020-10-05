package com.hwadee.learn.dao;


import com.hwadee.learn.dao.mybatis.AccountDaoImpl;
import com.hwadee.learn.dao.mybatis.PersonDaoImpl;
import com.hwadee.learn.dao.text.AccountDaoTxtImpl;
import com.hwadee.learn.dao.text.PersonDaoTxtImpl;

/**
 * DAO对象静态工厂类.
 */
public class DaoFactory {
    static int flag = 1;

    /**
     * 创建面向权限访问的Dao实例
     * @return Dao实例对象
     */
    public static AccountDao createAccountDao(){
        if(flag==1){
            AccountDao actDao =new AccountDaoTxtImpl();
            return actDao;
        }
        if(flag==2){
            AccountDao actDao =new AccountDaoImpl();
            return actDao;
        }
        return  null;
    }

    /**
     * 创建面向权限访问的Dao实例
     * @return Dao实例对象
     */
    public static PersonDao createPersonDao(){
        if(flag==1){
            PersonDao psnDao = new PersonDaoTxtImpl();
            return  psnDao;
        }
        if(flag==2){
            PersonDao psnDao = new PersonDaoImpl();
            return  psnDao;
        }
        return  null;
    }
}
