package org.motechproject.care.reporting.utils;

import org.junit.Test;

import static junit.framework.Assert.assertEquals;

public class ReflectionUtilsTest {
    @Test
    public void shouldUpdateValueOfAField(){
        TestClass toBeUpdated = new TestClass("oldValue");
        ReflectionUtils.updateValue("field", new TestClass("newValue"), toBeUpdated);

        assertEquals("newValue", toBeUpdated.getField());
    }

    private class TestClass {
        private String field;

        public TestClass(String field) {
            this.field = field;
        }

        public String getField() {
            return field;
        }
    }
}
