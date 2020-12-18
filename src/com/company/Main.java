package com.company;

import com.sun.deploy.util.StringUtils;
import gui.CFrame;

import javax.swing.*;
import java.io.File;

public class Main {

    public static void main(String[] args) {
//        CFrame frame = new CFrame("iNote");
//        frame.setVisible(true);
//        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
//        Admin admin = new Admin("admin");
//        Student student = new Student("tanya");
//        Teacher teacher = new Teacher("ostad");
//        LoginForm loginForm = new LoginForm();
//        loginForm.showGUI();
//        String str1 = "1223";
//        String str2 = "salam1";
//        System.out.println(isNumeric(str1));
//        String str = "salam    1";
//        String[] arrOfStr = str.split(" ", 5);
//        System.out.println(arrOfStr[0]);
//        Teacher teacher = new Teacher();
//        System.out.println("*****");
//        String CLASSES_PATH = ".\\classes\\";
//        File index = new File(CLASSES_PATH);
//                String[] entries = index.list();
//                assert entries != null;
//                for(String s: entries){
//                    if(s.contains("1"))
//                    {
//                        File currentFile = new File(index.getPath(),s);
//                        if(currentFile.delete())
//                            System.out.println("***");
//                    }
//                }
//        public boolean checkNumber(String str)
//        {
        String str = "s1ss";
            if(!(str.contains("0") || str.contains("1") || str.contains("2") ||
                    str.contains("3")|| str.contains("4") || str.contains("5") ||
                    str.contains("6")|| str.contains("7") || str.contains("8") || str.contains("9")))
            {
                System.out.println("%%%");
            }
            else
                System.out.println("_________");
//            return true;
//        }
    }
}
