package com.hwadee.learn.pojo;

/**
 * Created by IntelliJ IDEA.
 * User: hdren
 * Date: 2013-8-15
 * Time: 21:43:52
 * To change this template use File | Settings | File Templates.
 */
public class Person01 {
    private int  snum = 1;                                     //���к�
    private String name="����";                          //����
    private String sex="Male";                             //�Ա�
    private int age=22;                                         //����
    private String prefer="������Ӿ";              //����
    private String address="�Ĵ���ѧ����¥";    //סַ

    public Person01(){

    }

    public Person01(int snum, String name, String sex, int age, String prefer, String address){
        this.snum = snum;
        this.name = name;
        this.sex = sex;
        this.age = age;
        this.prefer =prefer;
        this.address = address;
    }

    public int getSnum(){
        return snum;
    }

    public  void setSnum(int snum){
        this.snum = snum;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getPrefer() {
        return prefer;
    }

    public void setPrefer(String prefer) {
        this.prefer = prefer;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }


}
