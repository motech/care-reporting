package org.motechproject.care.reporting.utils;

import java.util.List;

public class ListUtils {
    public static <T> T safeGet(List<T> list, int index) {
        return list.size() > index ? list.get(index) : null;
    }
}
