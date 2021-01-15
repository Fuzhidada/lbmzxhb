package com.epoch.dentsumcgb.util;

import org.apache.commons.lang3.StringUtils;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.math.BigDecimal;
import java.util.Map;

public class BeanUtil {
    private BeanUtil() {
    }

    public static void revertInputData(String[] title, String[] data, Object o, Map<String, String> mappingMap)
            throws NoSuchFieldException, InvocationTargetException, IllegalAccessException {

        int len = title.length;
        Class<?> c = o.getClass();
        Method[] methods = c.getMethods();
        for (int i = 0; i < len; i++) {
            Field field = c.getDeclaredField(mappingMap.get(title[i]));
//            field.setAccessible(true);
            for (Method m : methods) {
                if (m.getName().equalsIgnoreCase("set" + field.getName())) {
                    setField(field.getType(), m, o, data[i]);
                }
            }
        }
    }

    private static void setField(Class<?> type, Method m, Object o, String value)
            throws InvocationTargetException, IllegalAccessException {
        if (StringUtils.isBlank(value)) {
            return;
        }
        if (type.isAssignableFrom(String.class)) {
            m.invoke(o, value);
        }
        if (type.isAssignableFrom(BigDecimal.class)) {
            m.invoke(o, new BigDecimal(value));
        }
        if (type.isAssignableFrom(Integer.class)) {
            m.invoke(o, Integer.parseInt(value));
        }
    }

}
