package com.hwadee.learn.dao;

import com.hwadee.learn.pojo.Account;

/**
 * Created by Administrator on 2020/2/29.
 */
public interface AccountDao {
    /**
     * �����˺š����� �����ݿ��л�ȡ��¼�˺ŵ���Ϣ
     * �������null��˵�����˺��������
     * @param account ������ ��¼��������� �˺���Ϣ
     * @return ƥ���˺�&������û�����  ��������Ϸ���NULL
     */
    public Account getAccountByUsrPwd(Account account);
}
