package com.self.utils;

import java.io.*;

/**
 * 输入流：数据从硬盘到内存，通常被认为是输入流
 * 字节流：InputStream；字符流：Reader
 *
 * 输出流：数据从内存到硬盘，通常被认为是输出流
 * 字节流：OutputStream；字符流：Writer
 *
 * 输入、输出是站在内存的角度划分的
 *
 *
 * 序列化工具包
 * 序列化是指将对象的状态信息转换为可以存储或传输的形式（2进制数据）的过程。在序列化期间，
 * 对象将其当前状态写入到临时或持久性存储区。以后，可以通过从存储区中读取或反序列化对象的
 * 状态，重新创建该对象。
 *
 * 序列化的目的：
 * 1）永久的保存对象，保存对象的字节序列到本地文件中；
 * 2）通过序列化对象在网络中传递对象；
 * 3）通过序列化对象在进程间传递对象。
 */
public class SerializeUtils implements Serializable {

    /**
     * 序列化,将对象（字符流）转换成字节（字节流）
     * @param object 传入对象
     * @return 字节数组
     */
    static public byte[] transformObject2Byte(Object object) {
        // 新建输出流
        ByteArrayOutputStream baos = null;
        // 新建对象输出流
        ObjectOutputStream oos = null;
        try {
            baos = new ByteArrayOutputStream();
            oos = new ObjectOutputStream(baos);
            oos.writeObject(object);

            return baos.toByteArray();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (baos != null) {
                    baos.close();
                }
                if (oos != null) {
                    oos.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return null;
    }

    /**
     * 反序列化，将字节（字节流）转化成字符（字符流）
     * @param bytes 传入字节
     * @return 对象
     */
    static public Object transformByte2Object(byte[] bytes) {
        ByteArrayInputStream bais = null;
        ObjectInputStream ois = null;
        try {
            bais = new ByteArrayInputStream(bytes);
            ois = new ObjectInputStream(bais);
            return ois.readObject();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (bais != null) {
                    bais.close();
                }
                if (ois != null) {
                    ois.close();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return null;
    }

    public static void main(String[] args) {
        Object object = "select * from user where id = ?";
        byte[] bytes = transformObject2Byte(object);
        System.out.println("Object对象内容：" + object);
        System.out.println("序列化输出结果：" + bytes.length);

        Object newObject = transformByte2Object(bytes);
        System.out.println("反序列化输出结果：" + newObject);
    }

}
