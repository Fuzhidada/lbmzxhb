package com.epoch.dentsumcgb.util;

import com.epoch.dentsumcgb.entity.BaseData;
import com.epoch.dentsumcgb.entity.TD365ActCpsData;
import com.epoch.dentsumcgb.entity.TD365AglBsplData;
import org.apache.commons.lang3.StringUtils;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.math.BigDecimal;
import java.util.Map;

public class BeanUtil {
    private BeanUtil() {
    }

    public static BaseData revertInputData(String[] title, String[] data, Class<? extends BaseData> clz, Map<String, String> mappingMap)
            throws NoSuchFieldException, InvocationTargetException, IllegalAccessException, InstantiationException {
        int len = title.length;

        BaseData o= clz.newInstance();

        Method[] methods = clz.getMethods();
        for (int i = 0; i < len; i++) {
            Field field = clz.getDeclaredField(mappingMap.get(title[i]));
            for (Method m : methods) {
                if (m.getName().equalsIgnoreCase("set" + field.getName())) {
                    setField(field.getType(), m, o, data[i]);
                }
            }
        }
        return o;
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

    public static void main(String[] args) {
        Class t= TD365AglBsplData.class;
        for (Field s:t.getDeclaredFields()) {
            System.out.println(s.getName());
        }
    }

}
