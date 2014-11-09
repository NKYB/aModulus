/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.amodulus.jocl.utils;

import junit.framework.TestCase;
import org.jocl.cl_device_id;
import org.jocl.cl_platform_id;

/**
 *
 * @author Administrator
 */
public class JOCLDeviceQueryTest extends TestCase {
    
    public JOCLDeviceQueryTest(String testName) {
        super(testName);
    }

    /**
     * Test of printDeviceInfo method, of class JOCLDeviceQuery.
     */
    public void testPrintDeviceInfo() {
        System.out.println("printDeviceInfo");
        JOCLDeviceQuery.printDeviceInfo();
        // TODO review the generated test code and remove the default call to fail.
        assertTrue(true);
    }
}
