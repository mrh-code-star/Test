package com.hwadee.learn.dao.text;

import com.hwadee.learn.dao.PersonDao;
import com.hwadee.learn.pojo.Person01;

import java.io.*;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by Administrator on 2020/3/1.
 */
public class PersonDaoTxtImpl implements PersonDao {
    private static String fileName="c://persondata.txt";

    private List <Person01> personList;

    public PersonDaoTxtImpl(){
        personList = loadFromFile();
    }

    private static List<Person01> loadFromFile(){
        List <Person01> personLst = new LinkedList<Person01>();

        File file = new File(fileName);
        String strIn;
        //
        try{
            //打开文件句柄
            FileReader fr = new FileReader(file);
            BufferedReader br = new BufferedReader(fr);
            //将文件内容读入字符串
            strIn=br.readLine();
            while(strIn!=null){
                //添加账号信息
                personLst.add(getPersonFromStr(strIn));
                strIn=br.readLine();
            }
            br.close();
            fr.close();
        }catch(FileNotFoundException ex){
            //ex.printStackTrace();
            //初始化 用户账号库文件
            personLst = createPersonList();
            writeToFile(personLst);
        }catch(IOException ex){
            ex.printStackTrace();
        }

        return  personLst;
    }

    /**
     * 从一行字符串中分离出账号信息
     * @param strIn 输入字符串
     * @return 处理的账号信息
     */
    private static Person01 getPersonFromStr(String strIn){
        Person01 person =null;
        int npos1;

        if(strIn!=null){
            String[] str = new String[8];
            int idx=0;
            npos1 = strIn.indexOf('|');
            while(npos1>0){
                str[idx]= strIn.substring(0,npos1);
                strIn = strIn.substring(npos1+1);
                idx++;
                npos1 = strIn.indexOf('|');
            }
            if(strIn.length()>0){
                str[idx]= strIn;
            }
            person = new Person01();
            person.setSnum(Integer.parseInt(str[0]));
            person.setName(str[1]);
            person.setSex(str[2]);
            person.setAge(Integer.parseInt(str[3]));
            person.setPrefer(str[4]);
            person.setAddress(str[5]);
        }
        return person;
    }

    private static void writeToFile(List<Person01> personLst){
        File file = new File(fileName);
        StringBuilder  str=new StringBuilder();

        try{
            //打开文件句柄
            FileWriter fw = new FileWriter(file);
            BufferedWriter bw = new BufferedWriter(fw);

            for(Person01 item:personLst) {
                //构造待写入的字符串
                str.append(item.getSnum()).append("|")
                        .append(item.getName()).append("|")
                        .append(item.getSex()).append("|")
                        .append(item.getAge()).append("|")
                        .append(item.getPrefer()).append("|")
                        .append(item.getAddress()).append("\r\n");
            }
            //将字符串写入文件中
            bw.write(str.toString());
            bw.flush();
            bw.close();
            fw.close();
        }catch(IOException ex){
            ex.printStackTrace();
        }
    }
    public static List<Person01> createPersonList(){
        //初始化人员列表
        List <Person01> personLst = new LinkedList<Person01>();
        personLst.add(new Person01(1,"张三","男",22,"足球，游泳","四川大学望江校区三号楼"));
        personLst.add(new Person01(3,"李四","女",21,"羽毛球","四川大学望江校区五号楼"));
        personLst.add(new Person01(4,"唐小林","男",23,"篮球","成都理工大学5号宿舍楼"));
        personLst.add(new Person01(7,"王诗妤","女",22,"写作，绘画","四川师范大学2号楼"));
        return personLst;
    }

    @Override
    public int updatePerson(Person01 person) {
        for(int i=0;i<personList.size();i++){
            Person01 item = personList.get(i);
            if(item.getSnum()==person.getSnum()){
                personList.set(i,person);
                break;
            }
        }
        writeToFile(personList);
        return 0;
    }

    @Override
    public List<Person01> getPersonAll() {
        personList = loadFromFile();
        return personList;
    }

    public static void main(String[] args) {
        PersonDaoTxtImpl pdi = new PersonDaoTxtImpl();
        List<Person01> personList = pdi.getPersonAll();

        for(Person01 item:personList){
            System.out.println("["+item.getName()+"] 性别："+item.getSex()+", 年龄："+item.getAge()+", 爱好："+item.getPrefer()+", 住址："+item.getAddress());
        }

        Person01 person = personList.get(0);
        System.out.println("["+person.getName()+"] 性别："+person.getSex()+", 年龄："+person.getAge()+", 爱好："+person.getPrefer()+", 住址："+person.getAddress());
        person.setAge(25);
        person.setPrefer("壁球，足球，排球");
        int res = pdi.updatePerson(person);
        System.out.println("查询结果："+res);

        personList = pdi.getPersonAll();

        for(Person01 item:personList){
            System.out.println("["+item.getName()+"] 性别："+item.getSex()+", 年龄："+item.getAge()+", 爱好："+item.getPrefer()+", 住址："+item.getAddress());
        }

    }
}
