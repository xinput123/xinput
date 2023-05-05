package com.xinput.file.util;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import org.apache.commons.lang3.StringUtils;

import java.io.File;
import java.util.List;
import java.util.Map;

/**
 *
 * @author <a href="mailto:xinput.xx@gmail.com">xinput</a>
 * @since
 */
public class FileUtils {
    /**
     * 通过BufferedRandomAccessFile读取文件,推荐
     *
     * @param file     源文件
     * @param encoding 文件编码
     * @param pos      偏移量
     * @param num      读取量
     * @return pins文件内容，pos当前偏移量
     */
    public static Map<String, Object> bufferedRandomAccessFileReadLine(File file, String encoding, long pos, int num) {
        Map<String, Object> res = Maps.newHashMap();
        List<String> messages = Lists.newArrayList();
        res.put("messages", messages);

        try (BufferedRandomAccessFile reader = new BufferedRandomAccessFile(file, "r");) {
            reader.seek(pos);
            for (int i = 0; i < num; i++) {
                String message = reader.readLine();
                if (StringUtils.isBlank(message)) {
                    break;
                }
                // RandomAccessFile 读写文件时，不管文件中保存的数据编码格式是什么,
                // 使用 readLine()都读取到的都的是ISO-8859-1 编码的字符串，所以输出显示的时候要显示正常的话，
                // 必须把读取到ISO-8859-1 编码的字符串，转换成默认编码(或者指定编码)的字符串才能正常打印.
                messages.add(new String(message.getBytes("8859_1"), encoding));
            }
            res.put("pos", reader.getFilePointer());
        } catch (Exception e) {
            e.printStackTrace();
        }

        return res;
    }

}
