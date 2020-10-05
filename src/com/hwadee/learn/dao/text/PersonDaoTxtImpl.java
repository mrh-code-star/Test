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
            //���ļ����
            FileReader fr = new FileReader(file);
            BufferedReader br = new BufferedReader(fr);
            //���ļ����ݶ����ַ���
            strIn=br.readLine();
            while(strIn!=null){
                //����˺���Ϣ
                personLst.add(getPersonFromStr(strIn));
                strIn=br.readLine();
            }
            br.close();
            fr.close();
        }catch(FileNotFoundException ex){
            //ex.printStackTrace();
            //��ʼ�� �û��˺ſ��ļ�
            personLst = createPersonList();
            writeToFile(personLst);
        }catch(IOException ex){
            ex.printStackTrace();
        }

        return  personLst;
    }

    /**
     * ��һ���ַ����з�����˺���Ϣ
     * @param strIn �����ַ���
     * @return ������˺���Ϣ
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
            //���ļ����
            FileWriter fw = new FileWriter(file);
            BufferedWriter bw = new BufferedWriter(fw);

            for(Person01 item:personLst) {
                //�����д����ַ���
                str.append(item.getSnum()).append("|")
                        .append(item.getName()).append("|")
                        .append(item.getSex()).append("|")
                        .append(item.getAge()).append("|")
                        .append(item.getPrefer()).append("|")
                        .append(item.getAddress()).append("\r\n");
            }
            //���ַ���д���ļ���
            bw.write(str.toString());
            bw.flush();
            bw.close();
            fw.close();
        }catch(IOException ex){
            ex.printStackTrace();
        }
    }
    public static List<Person01> createPersonList(){
        //��ʼ����Ա�б�
        List <Person01> personLst = new LinkedList<Person01>();
        personLst.add(new Person01(1,"����","��",22,"������Ӿ","�Ĵ���ѧ����У������¥"));
        personLst.add(new Person01(3,"����","Ů",21,"��ë��","�Ĵ���ѧ����У�����¥"));
        personLst.add(new Person01(4,"��С��","��",23,"����","�ɶ�����ѧ5������¥"));
        personLst.add(new Person01(7,"��ʫ�","Ů",22,"д�����滭","�Ĵ�ʦ����ѧ2��¥"));
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
            System.out.println("["+item.getName()+"] �Ա�"+item.getSex()+", ���䣺"+item.getAge()+", ���ã�"+item.getPrefer()+", סַ��"+item.getAddress());
        }

        Person01 person = personList.get(0);
        System.out.println("["+person.getName()+"] �Ա�"+person.getSex()+", ���䣺"+person.getAge()+", ���ã�"+person.getPrefer()+", סַ��"+person.getAddress());
        person.setAge(25);
        person.setPrefer("������������");
        int res = pdi.updatePerson(person);
        System.out.println("��ѯ�����"+res);

        personList = pdi.getPersonAll();

        for(Person01 item:personList){
            System.out.println("["+item.getName()+"] �Ա�"+item.getSex()+", ���䣺"+item.getAge()+", ���ã�"+item.getPrefer()+", סַ��"+item.getAddress());
        }

    }
}
