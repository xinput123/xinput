package com.xinput.es.util;

import com.github.xinput.commons.JsonHelper;
import com.google.common.collect.Lists;
import com.xinput.es.entity.User;
import org.apache.commons.collections4.CollectionUtils;

import java.util.List;
import java.util.Map;

public class EsUtils {

    // java 对象转 map
    public static Map<String, Object> toSource(Object obj) {
        return JsonHelper.toMap(JsonHelper.toJsonString(obj), Object.class);
    }

    /**
     * 将批量数据转换成es参数数组
     *
     * @param objects
     * @return
     */
    public static Object[] toSourceList(List<?> objects) {
        if (CollectionUtils.isEmpty(objects)) {
            throw new RuntimeException("批量操作数据不可为空 !");
        }

        List<Map<String, Object>> sourceList = Lists.newArrayList();
        for (Object object : objects) {
            sourceList.add(toSource(object));
        }

        return sourceList.toArray();
    }

    public static void main(String[] args) {
        User user = new User("xinput", 1);
        Map<String, Object> map = toSource(user);
        for (Map.Entry<String, Object> entry : map.entrySet()) {
            System.out.println(entry.getKey() + ":" + entry.getValue());
        }
    }
}
