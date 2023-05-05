package com.xinput.poiexcel;

import com.xinput.domain.Person;
import org.apache.poi.xssf.streaming.SXSSFSheet;
import org.apache.poi.xssf.streaming.SXSSFWorkbook;

import java.io.IOException;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 * Apache POI操作Excel对象
 * HSSF：操作Excel 2007之前版本(.xls)格式,生成的EXCEL不经过压缩直接导出
 * XSSF：操作Excel 2007及之后版本(.xlsx)格式,内存占用高于HSSF
 * SXSSF:从POI3.8 beta3开始支持,基于XSSF,低内存占用,专门处理大数据量(建议)。
 * <p>
 * 注意:
 * 值得注意的是SXSSFWorkbook只能写(导出)不能读(导入)
 * <p>
 * 说明:
 * .xls格式的excel(最大行数65536行,最大列数256列)
 * .xlsx格式的excel(最大行数1048576行,最大列数16384列)
 */
public class PoiExcelUtils {

    /**
     * 默认日期格式
     */
    public static final String DEFAULT_DATE_PATTERN = "yyyy年MM月dd日";

    /**
     * 默认列宽
     */
    public static final int DEFAULT_COLUMN_WIDTH = 17;


    /**
     * 导出Excel(.xlsx)格式
     *
     * @param titleList 表格头信息集合
     * @param persons   数据数组
     * @param os        文件输出流
     */
    public static void exportExcel(ArrayList<LinkedHashMap> titleList, List<Person> persons, OutputStream os) {
        String datePattern = DEFAULT_DATE_PATTERN;
        int minBytes = DEFAULT_COLUMN_WIDTH;

        /**
         * 声明一个工作薄
         */
        SXSSFWorkbook workbook = new SXSSFWorkbook(1000);// 大于1000行时会把之前的行写入硬盘
        // 压缩临时文件，很重要，否则磁盘很快就会被写满
        workbook.setCompressTempFiles(true);


        LinkedHashMap<String, String> headMap = titleList.get(1);

        /**
         * 生成一个(带名称)表格
         */
        SXSSFSheet sheet = workbook.createSheet();
//        sheet.createFreezePane(0, 3, 0, 1);// (单独)冻结第一行

        /**
         * 生成head相关信息+设置每列宽度
         */
        int[] colWidthArr = new int[headMap.size()];// 列宽数组
        String[] headKeyArr = new String[headMap.size()];// headKey数组
        String[] headValArr = new String[headMap.size()];// headVal数组
        int i = 0;
        for (Map.Entry<String, String> entry : headMap.entrySet()) {
            headKeyArr[i] = entry.getKey();
            headValArr[i] = entry.getValue();

            int bytes = headKeyArr[i].getBytes().length;
            colWidthArr[i] = bytes < minBytes ? minBytes : bytes;
            sheet.setColumnWidth(i, colWidthArr[i] * 256);// 设置列宽
            i++;
        }

        try {
            workbook.write(os);
            os.flush();// 刷新此输出流并强制将所有缓冲的输出字节写出
            os.close();// 关闭流
            workbook.dispose();// 释放workbook所占用的所有windows资源
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
