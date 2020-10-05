package com.hwadee.learn.dao;

import com.hwadee.learn.pojo.Person01;

import java.util.List;

/**
 * Created by Administrator on 2020/2/29.
 */
public interface PersonDao {
    /**
     * 将新的信息修改到数据库中
     * @param person 需要修改的用户信息
     * @return 修改成功返回为0 否则返回非0
     */
    public int updatePerson(Person01 person);

    /**
     * 从数据库中获取所有人员的信息列表<BR>
     * 查询成功返回 人员信息的 顺序列表
     * @return 人员信息ArrayList
     */
    public List<Person01> getPersonAll();
}
