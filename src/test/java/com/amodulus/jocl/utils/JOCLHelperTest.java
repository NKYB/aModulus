package com.amodulus.jocl.utils;

import junit.framework.TestCase;
import org.jocl.Pointer;

public class JOCLHelperTest extends TestCase {
    
    public JOCLHelperTest(String testName) {
        super(testName);
    }

    /**
     * Test of initDevice method, of class JOCLHelper.
     */
    public void testInitDevice() {
        System.out.println("initDevice");
        int global_work_size = 0;
        int local_work_size = 0;
        int platformIndex = 0;
        int deviceIndex = 0;
        JOCLHelper instance = new JOCLHelper();
        instance.initDevice(global_work_size, local_work_size, platformIndex, deviceIndex);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of addVar method, of class JOCLHelper.
     */
    public void testAddVar() {
        System.out.println("addVar");
        long flags = 0L;
        long size = 0L;
        Pointer host_ptr = null;
        JOCLHelper instance = new JOCLHelper();
        instance.addVar(flags, size, host_ptr);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of createKernel method, of class JOCLHelper.
     */
    public void testCreateKernel() {
        System.out.println("createKernel");
        String name = "";
        String programSource = "";
        JOCLHelper instance = new JOCLHelper();
        instance.createKernel(name, programSource);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of runKernel method, of class JOCLHelper.
     */
    public void testRunKernel() {
        System.out.println("runKernel");
        JOCLHelper instance = new JOCLHelper();
        instance.runKernel();
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of loadOutput method, of class JOCLHelper.
     */
    public void testLoadOutput() {
        System.out.println("loadOutput");
        Pointer host_ptr = null;
        int var_num = 0;
        long size = 0L;
        JOCLHelper instance = new JOCLHelper();
        instance.loadOutput(host_ptr, var_num, size);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of readEntireFile method, of class JOCLHelper.
     */
    public void testReadEntireFile() throws Exception {
        System.out.println("readEntireFile");
        String filename = "";
        String expResult = "";
        String result = JOCLHelper.readEntireFile(filename);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }
}
