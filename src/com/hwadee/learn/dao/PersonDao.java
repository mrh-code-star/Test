package com.hwadee.learn.dao;

import com.hwadee.learn.pojo.Person01;

import java.util.List;

/**
 * Created by Administrator on 2020/2/29.
 */
public interface PersonDao {
    /**
     * ���µ���Ϣ�޸ĵ����ݿ���
     * @param person ��Ҫ�޸ĵ��û���Ϣ
     * @return �޸ĳɹ�����Ϊ0 ���򷵻ط�0
     */
    public int updatePerson(Person01 person);

    /**
     * �����ݿ��л�ȡ������Ա����Ϣ�б�<BR>
     * ��ѯ�ɹ����� ��Ա��Ϣ�� ˳���б�
     * @return ��Ա��ϢArrayList
     */
    public List<Person01> getPersonAll();
}
