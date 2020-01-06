package com.it.test;

import javassist.*;

import java.io.IOException;
import java.lang.instrument.ClassFileTransformer;
import java.lang.instrument.IllegalClassFormatException;
import java.security.ProtectionDomain;

public class ClassFileTransformerImp implements ClassFileTransformer {
    public byte[] transform(ClassLoader loader, String className, Class<?> classBeingRedefined, ProtectionDomain protectionDomain, byte[] classfileBuffer) throws IllegalClassFormatException {
//        System.out.println("进入ClassFileTransformerImp");
        if (className.equals("com/it/test/HttpSend")) {
            System.out.println("已经进入方法了");
            try {
                System.out.println("类名:" + className);
                ClassPool cPool = new ClassPool(true);
                //设置class文件的位置，实际运用时应替换为相对路径
//                cPool.insertClassPath("D:\\gittest_pro\\iiAccount\\iiAccount-adapter\\target\\classNameses");
//                cPool.insertClassPath("D:\\javaproject\\HTTP-Agent-demo\\target\\classes\\HttpSend.class");
                                cPool.insertClassPath("D:\\javaproject\\HTTP-Agent-demo\\target\\classes");

                //获取该class对象
                CtClass cClass = cPool.get("com.it.test.HttpSend");
                //获取到对应的方法
                CtMethod cMethod = cClass.getDeclaredMethod("test1");
                //通过insertAt可引用局部变量。
//                cMethod.insertAt(11, "{url = \"http:///www.baidu.com\";}");
                cMethod.insertAfter("{i = \"http:///www.baidu.com\";}");
                //替换原有的文件，实际运用时应替换为相对路径
                cClass.writeFile("D:\\javaproject\\HTTP-Agent-demo\\target\\classes");
                System.out.println("=======修改完成=========");
            } catch (NotFoundException e) {
                e.printStackTrace();
            } catch (CannotCompileException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return null;
    }
    }
