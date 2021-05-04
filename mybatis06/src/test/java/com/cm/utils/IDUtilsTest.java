package com.cm.utils;

import org.junit.Test;

public class IDUtilsTest {
    @Test
    public void testGetId() {
        String id = IDUtils.genId();
        System.out.println(id);
    }
}
