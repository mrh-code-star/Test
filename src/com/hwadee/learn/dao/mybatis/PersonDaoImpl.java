package com.hwadee.learn.dao.mybatis;

import com.hwadee.learn.common.MybatisUtil;
import com.hwadee.learn.dao.PersonDao;
import com.hwadee.learn.pojo.Person01;
import org.apache.ibatis.session.SqlSession;

import java.util.LinkedList;
import java.util.List;

/**
 * Created by Administrator on 2020/3/1.
 */
public class PersonDaoImpl implements PersonDao {
    @Override
    public int updatePerson(Person01 person) {
        SqlSession sqlSession = null;
        int result =0;
        try {
            sqlSession = MybatisUtil.getSqlSession();
            result = sqlSession.update("updatePerson",person);
        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            MybatisUtil.closeSqlSession();
        }
        return result;
    }

    @Override
    public List<Person01> getPersonAll() {
        SqlSession sqlSession = null;
        List result=new LinkedList();
        try {
            sqlSession = MybatisUtil.getSqlSession();
            result = sqlSession.selectList("getPersonList");
        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            MybatisUtil.closeSqlSession();
        }
        return result;
    }

    public static void main(String[] args) {
        PersonDaoImpl pdi = new PersonDaoImpl();
        List<Person01> personList = pdi.getPersonAll();

        for(Person01 item:personList){
            System.out.println("["+item.getName()+"] 性别："+item.getSex()+", 年龄："+item.getAge()+", 爱好："+item.getPrefer()+", 住址："+item.getAddress());
        }

        Person01 person = personList.get(0);
        System.out.println("["+person.getName()+"] 性别："+person.getSex()+", 年龄："+person.getAge()+", 爱好："+person.getPrefer()+", 住址："+person.getAddress());
        person.setAge(21);
        person.setPrefer("足球，游泳");
        int res = pdi.updatePerson(person);
        System.out.println("查询结果："+res);

        personList = pdi.getPersonAll();

        for(Person01 item:personList){
            System.out.println("["+item.getName()+"] 性别："+item.getSex()+", 年龄："+item.getAge()+", 爱好："+item.getPrefer()+", 住址："+item.getAddress());
        }
    }
}
