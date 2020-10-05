package com.hwadee.learn.dao;

import com.hwadee.learn.pojo.Account;

/**
 * Created by Administrator on 2020/2/29.
 */
public interface AccountDao {
    /**
     * 根据账号、密码 从数据库中获取登录账号的信息
     * 如果返回null，说明该账号密码错误
     * @param account 包含了 登录名、口令的 账号信息
     * @return 匹配账号&密码的用户详情  如果不符合返回NULL
     */
    public Account getAccountByUsrPwd(Account account);
}
