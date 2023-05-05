package com.xinput.file;

import java.io.FileInputStream;
import java.io.InputStream;

/**
 * {@link FileInputStream} 读取文件 示例
 *
 * @author <a href="mailto:xinput.xx@gmail.com">xinput</a>
 * @since
 */
public class FileInputStreamDemo {
    public static void main(String[] args) {
        long start = System.currentTimeMillis();
        String file = "/Users/yuanlai/Desktop/test.txt";
        try (InputStream is = new FileInputStream(file)) {
            byte[] buff = new byte[4096];
            long counts = 0;
            int offset = 0;
            while ((offset = is.read(buff)) != -1) {
                counts = counts + offset;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        long end = System.currentTimeMillis();
        System.out.println("耗时 : " + (end - start) / 1000);
    }
}
