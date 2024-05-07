package com.xinput.domain;

import java.util.concurrent.ThreadLocalRandom;

public class RandomHelper {

  private static final String[] nameDocs = {"朝歌晚酒", "都怪时光太动听", "笑我孤陋", "水墨青花", "时光清浅", "草帽撸夫", "江山如画",
      "热度不够", "盏茶浅抿", "把酒临风", "且听风吟", "梦忆笙歌", "倾城月下", "清风墨竹", "自愈心暖", "几许轻唱",
      "平凡之路", "半夏倾城", "南栀倾寒", "孤君独战", "温酒杯暖", "眉目亦如画", "旧雪烹茶", "律断华章", "清酒暖风",
      "清羽墨安", "一夕夙愿", "南顾春衫", "和云相伴", "夕颜若雪", "时城旧巷", "梦屿千寻"};

  private static final String[] genderDocs = {"0", "1"};

  private static final int[] ageDocs = {16, 17, 18, 19, 20, 21, 22, 23, 24, 25, 26, 27, 28, 29, 30};


  // 随机获取名称
  public static String getRandomName() {
    int index = ThreadLocalRandom.current().nextInt(nameDocs.length);
    return nameDocs[index];
  }

  /**
   * 性别随机
   */
  public static String getRandomGender() {
    int index = ThreadLocalRandom.current().nextInt(genderDocs.length);
    return genderDocs[index];
  }

  /**
   * 年龄随机
   */
  public static int getRandomAge() {
    int index = ThreadLocalRandom.current().nextInt(ageDocs.length);
    return ageDocs[index];
  }

}
