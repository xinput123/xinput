package com.xinput.easyexcel;

import com.alibaba.excel.EasyExcel;
import com.alibaba.excel.ExcelWriter;
import com.alibaba.excel.write.metadata.WriteSheet;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.xinput.domain.Person;
import com.xinput.domain.RandomUtils;
import org.junit.Test;

import java.util.Collection;
import java.util.List;
import java.util.Map;

/**
 * easyexcel 使用示例
 */
public class EasyexcelDemo {
    public static void main(String[] args) {
        long start = System.currentTimeMillis();
        String day = "2020-01-01";
        List<Person> persons = Lists.newArrayListWithExpectedSize(1000000);
        for (int i = 1; i <= 30; i++) {
            Person person = new Person();
            person.setId(i);
            person.setName(RandomUtils.getRandomName());
            person.setAge(RandomUtils.getRandomAge());
            person.setBirthday(day);
            person.setSex(RandomUtils.getRandomGender());
            persons.add(person);
        }

        List<String> head = Lists.newArrayList("id", "name", "age", "birthday", "sex");
        exportOrder("/Users/momo/Desktop/1.xlsx", head, persons);
        System.out.println("耗时: " + (System.currentTimeMillis() - start));
    }

    public static <T> void exportOrder(String fileName, List<String> headNames, List<Person> persons) {
//        try (FileOutputStream fos = new FileOutputStream(fileName)) {
//            EasyExcel.write(fos).needHead(true)
//                    .head(getHead(headNames))
//                    .excelType(ExcelTypeEnum.XLSX)
//                    .sheet(0)
//                    .doWrite(persons);
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
        EasyExcel.write(fileName, Person.class).sheet(0).doWrite(persons);
    }

    // 表头，即第一行名称
    public static List<List<String>> getHead(Collection<String> headNames) {
        List<List<String>> head = Lists.newArrayListWithExpectedSize(headNames.size());
        for (String headName : headNames) {
            head.add(Lists.newArrayList(headName));
        }
        return head;
    }

    /**
     * 向同一个sheet多次写入
     */
    @Test
    public void test01() {
        String fileName = "/Users/momo/Desktop/test01.xlsx";
        long start = System.currentTimeMillis();
        try (ExcelWriter excelWriter = EasyExcel.write(fileName, Person.class).build()) {
            // 同一个sheet
            WriteSheet writeSheet = EasyExcel.writerSheet("sheet1").build();
            long lastId = 0L;
            int size = 200;
            while (true) {
                List<Person> personList = getData(lastId, size);
                for (Person person : personList) {
                    lastId = person.getId();
                }

                excelWriter.write(personList, writeSheet);
                if (personList.size() < size) {
                    break;
                }
            }

            System.out.println("读取数据结束");
        }
        System.out.println("耗时: " + (System.currentTimeMillis() - start));
    }

    /**
     * 写入到不同的sheet
     */
    @Test
    public void test02() {
        String fileName = "/Users/momo/Desktop/test02.xlsx";
        long start = System.currentTimeMillis();
        try (ExcelWriter excelWriter = EasyExcel.write(fileName, Person.class).build()) {
            long lastId = 0L;
            int size = 10000;
            long count = 0;
            Map<Integer, WriteSheet> writeSheetMap = Maps.newHashMap();
            while (true) {
                int sheetIndex = Math.toIntExact((count / 1000000) + 1);
                WriteSheet writeSheet = writeSheetMap.get(sheetIndex);
                if (writeSheet == null) {
                    writeSheet = EasyExcel.writerSheet("sheet" + sheetIndex).build();
                    writeSheetMap.put(sheetIndex, writeSheet);
                }

                List<Person> personList = getData(lastId, size);
                for (Person person : personList) {
                    lastId = person.getId();
                }

                excelWriter.write(personList, writeSheet);
                System.out.println(lastId);
                count = count + personList.size();
                if (personList.size() < size) {
                    break;
                }
            }
            System.out.println("读取数据结束");
        }
        System.out.println("耗时: " + (System.currentTimeMillis() - start));
    }

    public List<Person> getData(long start, int size) {
//        try {
//            TimeUnit.SECONDS.sleep(1);
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }
        String day = "2020-01-01";
        List<Person> persons = Lists.newArrayListWithExpectedSize(size);
        for (long i = start + 1; i <= start + size; i++) {
            Person person = new Person();
            person.setId(i);
            person.setName(RandomUtils.getRandomName());
            person.setAge(RandomUtils.getRandomAge());
            person.setBirthday(day);
            person.setSex(RandomUtils.getRandomGender());

            if (person.getId() > 3100000) {
                break;
            }
            persons.add(person);
        }
        return persons;
    }
}
