package com.howietian.jianshi.constants;

import com.howietian.jianshi.entities.Flaw;

import java.util.ArrayList;

/**
 * Created by HowieTian on 2017/11/11 0011.
 */

public class FlawDb {
    private ArrayList<Flaw> list = new ArrayList<>();
    public FlawDb(){
        if(list!=null){
            list.clear();
        }
        Flaw flaw1 = new Flaw("Schneider Electric Trio TView Software存在dll劫持漏洞","中","Schneider Electric Trio TView Software是一款虚拟诊断软件。\n" +
                "Schneider Electric Trio TView Software存在dll劫持漏洞。该漏洞是由于Trio TView Software应用包含的DLL未能指定绝对路径所致，允许攻击者利用漏洞构建恶意应用，置放在特定路径中，使应用恶意加载DLL并执行。","Schneider Electric Trio TView Software 3.29.0",1);
        Flaw flaw2 = new Flaw("Saia Burgess Controls PCD Controllers信息泄露漏洞","中","Saia Burgess Controls PCD Controllers <1.28.16\n" +
                "Saia Burgess Controls PCD Controllers <1.24.69","PCD Controller是瑞士Saia Burgess Controls公司的一款用于测量、调节和控制任务的可编程控制器系列产品。 \n" +
                "Saia Burgess Controls PCD Controllers 存在信息泄露漏洞，攻击者可利用漏洞在内存中获取敏感信息。",1);
        Flaw flaw3 = new Flaw("Schneider Electric InduSoft Web Studio和InTouch Machine Edition远程代码执行漏洞","高","Schneider Electric InduSoft Web Studio <=8.0 SP2\n" +
                "Schneider Electric InTouch Machine Edition <=8.0 SP2","PCD Controller是瑞士Saia Burgess Controls公司的一款用于测量、调节和控制任务的可编程控制器系列产品。 \n" +
                "Saia Burgess Controls PCD Controllers 存在信息泄露漏洞，攻击者可利用漏洞在内存中获取敏感信息。",1);
        Flaw flaw4 = new Flaw("Digium Asterisk GUI OS命令注入漏洞","高","Ctek SkyRouter Series 4200 <6.00.11\n" +
                "Ctek SkyRouter Series 4400 <6.00.11","SkyRouter是瑞典CTEK公司的一款用于管理无线IP连接的产品。\n" +
                "Ctek SkyRouter存在身份验证绕过漏洞，通过访问Web服务器上的特定统一资源定位符（URL），攻击者可利用漏洞绕过身份验证限制进而访问应用程序。",1);
        Flaw flaw5 = new Flaw("Digium Asterisk GUI OS命令注入漏洞","高","Digium Asterisk GUI <=2.1.0","Asterisk GUI是一款用于配置图形用户界面的框架。\n" +
                "Digium Asterisk GUI存在OS命令注入漏洞，攻击者可通过在程序的URL请求中注入OS命令，从而在系统上执行任意代码。",1);
        Flaw flaw6 = new Flaw("SBrother DCP-J132W拒绝服务漏洞","高","Brother DCP-J132W是Brother推出的打印机。","Brother DCP-J132W",2);
        Flaw flaw7 = new Flaw("Siemens 300/400系列PLC远程控制漏洞","中","Saia Burgess Controls PCD Controllers <1.28.16\n" +
                "Saia Burgess Controls PCD Controllers <1.24.69","PCD Controller是瑞士Saia Burgess Controls公司的一款用于测量、调节和控制任务的可编程控制器系列产品。 \n" +
                "Saia Burgess Controls PCD Controllers 存在信息泄露漏洞，攻击者可利用漏洞在内存中获取敏感信息。",2);

        this.list.add(flaw1);
        this.list.add(flaw2);
        this.list.add(flaw3);
        this.list.add(flaw4);
        this.list.add(flaw5);
        this.list.add(flaw6);
        this.list.add(flaw7);
    }

    public ArrayList<Flaw> queryTag(){
        ArrayList<Flaw> mList = new ArrayList<>();
        if(mList!=null){
            mList.clear();
        }
       for(int i = 0;i<list.size();i++){
               mList.add(list.get(i));
      }
        return mList;
    }
}
