package org.motechproject.care.reporting.utils;

import org.apache.commons.collections.CollectionUtils;

import java.util.List;

public class ListUtils {
    public static <T> T safeGet(List<T> list, int index) {
        return CollectionUtils.isNotEmpty(list) && list.size() > index ? list.get(index) : null;
    }
}
