package com.xinput.file;

import com.xinput.file.util.BufferedRandomAccessFile;
import com.xinput.file.util.FileUtils;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.collections4.MapUtils;

import java.io.File;
import java.util.List;
import java.util.Map;

/**
 * {@link BufferedRandomAccessFile 读取文件 示例}
 *
 * @author <a href="mailto:xinput.xx@gmail.com">xinput</a>
 * @since
 */
public class BufferedRandomAccessFileDemo {
    private static final String ENCODING = "UTF-8";
    private static final int NUM = 5000;

    public static void main(String[] args) {
        File file = new File("/Users/yuanlai/Desktop/test.txt");

        long start = System.currentTimeMillis();
//
        System.out.println(file.exists());
        long pos = 0L;
        while (true) {
            Map<String, Object> res = FileUtils.bufferedRandomAccessFileReadLine(file, ENCODING, pos, NUM);
            // 如果返回结果为空结束循环
            if (MapUtils.isEmpty(res)) {
                break;
            }
            List<String> messages = (List<String>) res.get("messages");
            if (CollectionUtils.isNotEmpty(messages)) {
//        messages.forEach(message -> System.out.println(message));
                if (messages.size() < NUM) {
                    break;
                }
            } else {
                break;
            }
            pos = (Long) res.get("pos");
        }
        System.out.println(((System.currentTimeMillis() - start) / 1000));
    }
}
