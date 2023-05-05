package com.xinput.poiexcel;

import com.github.xinput.commons.date.LocalDateTimeUtils;
import com.google.common.collect.Lists;
import com.xinput.domain.Person;
import com.xinput.domain.RandomUtils;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;

public class PoiExcelUtilsDemo {
    public static void main(String[] args) throws IOException {
        // 模拟100W条数据
//        int count = 1000000;
        long count = 100;
        List<Person> persons = Lists.newArrayListWithCapacity((int) count);
        for (long i = 0; i < count; i++) {
            Person s = new Person();
            s.setId(i);
            s.setName("POI" + i);
            s.setAge((int) (i % 100 + 1));
            s.setBirthday(LocalDateTimeUtils.formatDay(LocalDateTime.now()));
            s.setSex(RandomUtils.getRandomGender());
            persons.add(s);
        }

        LinkedHashMap<String, String> headMap = new LinkedHashMap();
        headMap.put("id", "用户ID");
        headMap.put("name", "姓名");
        headMap.put("age", "年龄");
        headMap.put("birthday", "生日");
        headMap.put("sex", "性别");

        ArrayList<LinkedHashMap> titleList = new ArrayList();
        titleList.add(headMap);

        File file = new File("ExcelExportDemo/");
        if (!file.exists()) {
            // 创建该文件夹目录
            file.mkdir();
        }

        long startTime = System.currentTimeMillis();

        try (OutputStream os = new FileOutputStream(file.getAbsolutePath() + "/" + startTime + ".xlsx")) {
            // .xlsx格式
            System.out.println("正在导出xlsx...");
//            PoiExcelUtils.exportExcel(titleList, persons, os);
            System.out.println("导出完成...共" + count + "条数据,用时" + (System.currentTimeMillis() - startTime) + "ms");
            System.out.println("文件路径：" + file.getAbsolutePath() + "/" + startTime + ".xlsx");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
