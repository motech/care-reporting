package org.motechproject.care.reporting.ft.utils;

import org.joda.time.DateTime;

import java.lang.reflect.Field;
import java.util.List;

import static java.lang.String.format;
import static java.util.Arrays.asList;

public class ReflectionUtils {
    public static String reflectionSerialize(Object object, String path) {
        StringBuilder result = new StringBuilder();
        Field[] fields = object.getClass().getDeclaredFields();
        for (Field field : fields) {
            if (java.lang.reflect.Modifier.isStatic(field.getModifiers())) continue;
            field.setAccessible(true);
            Object value = null;

            try {
                value = field.get(object);
            } catch (IllegalAccessException e) {
                throw new RuntimeException(e);
            }

            String fieldName = field.getName();

            if(value == null)
                result.append(format("%s.%s=<null>\n", path, fieldName));
            else if (asList(String.class, Integer.class, Boolean.class, DateTime.class).contains(value.getClass()))
                result.append(format("%s.%s=%s\n", path, fieldName, value));
            else if (List.class.isAssignableFrom(value.getClass())) {
                List list = (List) value;
                for (int i = 0; i < list.size(); i++) {
                    Object o = list.get(i);
                    result.append(reflectionSerialize(o, format("%s.%s[%d]", path, fieldName, i)));
                }
            } else {
//                System.out.println(format("Going in:%s.%s", path, fieldName));
                result.append(reflectionSerialize(value, format("%s.%s", path, fieldName)));
            }
        }
        return result.toString();
    }
}
