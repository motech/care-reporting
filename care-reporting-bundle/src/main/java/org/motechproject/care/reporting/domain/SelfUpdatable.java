package org.motechproject.care.reporting.domain;

import org.apache.commons.lang.StringUtils;

import java.lang.reflect.Field;
import java.util.List;

import static org.motechproject.care.reporting.utils.ReflectionUtils.updateValue;

public abstract class SelfUpdatable<T> {

    protected Boolean validateIfUpdatable(String thisId, String otherId) {
        if (StringUtils.equals(thisId, otherId))
            return true;
        throw new IllegalArgumentException(String.format("Cannot Update %s, Id does not match. Id for source %s, Id for updated %s", this.getClass().getName(), thisId, otherId));
    }

    protected void updateFields(T source, List<String> ignoredFields) {
        for (Field field : this.getClass().getDeclaredFields()) {
            if (ignoredFields.contains(field.getName())) continue;
            updateValue(field.getName(), source, this);
        }
        updateLastModifiedTime();
    }

    public abstract void updateToLatest(T object);

    protected abstract void updateLastModifiedTime();
}
