package com.hwadee.learn.dao.text;

import com.hwadee.learn.dao.AccountDao;
import com.hwadee.learn.pojo.Account;

import java.io.*;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by Administrator on 2020/3/1.
 */
public class AccountDaoTxtImpl implements AccountDao {
    private static String fileName="c://accountdata.txt";

    private List <Account> accountList;

    public AccountDaoTxtImpl(){
        accountList = loadFromFile();
    }

    private static List<Account> createAccountList(){
        //��ʼ���˺��б�
        List <Account> accountLst = new LinkedList<Account>();
        Account account1 = new Account();
        account1.setAccount("zhangsan");
        account1.setPassword("123456");
        accountLst.add(account1);
        Account account2 = new Account();
        account2.setAccount("lisi");
        account2.setPassword("snksnk");
        accountLst.add(account2);
        return accountLst;
    }

    private static void writeToFile(List<Account> accountLst){
        File file = new File(fileName);
        StringBuilder  str=new StringBuilder();

        try{
            //���ļ����
            FileWriter fw = new FileWriter(file);
            BufferedWriter bw = new BufferedWriter(fw);

            for(Account item:accountLst) {
                //�����д����ַ���
                str.append(item.getAccount()).append("|")
                        .append(item.getPassword()).append("\r\n");

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

    private static List<Account> loadFromFile(){
        List <Account> accountLst = new LinkedList<Account>();

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
                accountLst.add(getAccoutFromStr(strIn));
                strIn=br.readLine();
            }
            br.close();
            fr.close();
        }catch(FileNotFoundException ex){
            //ex.printStackTrace();
            //��ʼ�� �û��˺ſ��ļ�
            accountLst = createAccountList();
            writeToFile(accountLst);
        }catch(IOException ex){
            ex.printStackTrace();
        }

        return  accountLst;
    }

    /**
     * ��һ���ַ����з�����˺���Ϣ
     * @param strIn �����ַ���
     * @return ������˺���Ϣ
     */
    private static Account getAccoutFromStr(String strIn){
        Account account =null;
        int npos1;

        if(strIn!=null){
            String[] str = new String[3];
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
            account = new Account();
            account.setAccount(str[0]);
            account.setPassword(str[1]);
        }
        return account;
    }

    @Override
    public Account getAccountByUsrPwd(Account account) {
        Account act=null;
        for(Account item:accountList ){
            if(item.getAccount().equals(account.getAccount()) && item.getPassword().equals(account.getPassword())){
                act = item;
                break;
            }
        }
        return act;
    }

    public static void main(String[] args) {
        AccountDaoTxtImpl adi = new AccountDaoTxtImpl();

        Account act = new Account();
        String username ="zhangsan";
        String password = "123456";
        act.setAccount(username);
        act.setPassword(password);
        act = adi.getAccountByUsrPwd(act);
        if(act==null){
            System.out.println("�����ڸ��û��˺ţ�");
        }else {
            System.out.println("�û���:" + act.getAccount() + "  ����:" + act.getPassword());
        }
    }
}
