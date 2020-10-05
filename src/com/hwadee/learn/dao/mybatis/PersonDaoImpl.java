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
            System.out.println("["+item.getName()+"] �Ա�"+item.getSex()+", ���䣺"+item.getAge()+", ���ã�"+item.getPrefer()+", סַ��"+item.getAddress());
        }

        Person01 person = personList.get(0);
        System.out.println("["+person.getName()+"] �Ա�"+person.getSex()+", ���䣺"+person.getAge()+", ���ã�"+person.getPrefer()+", סַ��"+person.getAddress());
        person.setAge(21);
        person.setPrefer("������Ӿ");
        int res = pdi.updatePerson(person);
        System.out.println("��ѯ�����"+res);

        personList = pdi.getPersonAll();

        for(Person01 item:personList){
            System.out.println("["+item.getName()+"] �Ա�"+item.getSex()+", ���䣺"+item.getAge()+", ���ã�"+item.getPrefer()+", סַ��"+item.getAddress());
        }
    }
}
