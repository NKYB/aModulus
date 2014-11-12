package com.amodulus.jocl.kernels;

import com.amodulus.jocl.utils.JOCLHelper;
import java.io.IOException;
import junit.framework.TestCase;
import org.jocl.Pointer;
import org.jocl.Sizeof;
import static org.jocl.CL.*;

public class kernel_func extends TestCase {
    
    public kernel_func(String testName) {
        super(testName);
    }

    public void testKernel() throws IOException {
        System.out.println("testKernel");
        
        String kernelName = "kernel_func";
        String programSourceFolder = "C:\\Users\\Administrator\\Documents\\GitHub\\aModulus\\src\\main\\java\\com\\amodulus\\jocl\\kernels\\";
        String programSource = JOCLHelper.readEntireFile(programSourceFolder + kernelName + ".cl");   
        int platformIndex = 1;
        int deviceIndex = 1;
        
        int n = 1;
        int dstArray[] = new int[n];
        Pointer dst = Pointer.to(dstArray);
        
        JOCLHelper joclHelper = new JOCLHelper();
        joclHelper.initDevice(n,n,platformIndex,deviceIndex);
        joclHelper.addVar(CL_MEM_READ_WRITE, Sizeof.cl_float * n, null);
        joclHelper.createKernel(kernelName, programSource);
        
        long startTime = System.currentTimeMillis();
        joclHelper.runKernel();
        joclHelper.loadOutput(dst, 1, n * Sizeof.cl_float);
        long endTime = System.currentTimeMillis();
        System.out.println("Time to run kernel in (millis): " + (endTime - startTime) );

        assertEquals(dstArray[0],100);
    }
}
