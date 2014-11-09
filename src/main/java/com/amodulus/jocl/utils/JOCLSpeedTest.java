package com.amodulus.jocl.utils;

import com.amodulus.jocl.utils.JOCLHelper;
import java.io.IOException;
import static org.jocl.CL.*;
import org.jocl.Pointer;
import org.jocl.Sizeof;

public class JOCLSpeedTest {
    public static void speed_test() throws IOException{
        String programSourceFolder = "C:\\temp\\amodulus\\src\\com\\amodulus\\jocl\\kernels\\";
        int platformIndex = 0;
        int deviceIndex = 0;
        
        String programSource = JOCLHelper.readEntireFile(programSourceFolder + "kernel_simple_test.cl");   

        for (int j=0; j<11; j++){
            for (int k=0; k<11; k++){
                int global_work_size = (int) Math.pow(2,k);
                int local_work_size = (int) Math.pow(2,j);
                
                int n = 3000000;
                float srcArrayA[] = new float[n];
                float srcArrayB[] = new float[n];
                float dstArray[] = new float[n];
                for (int i=0; i<n; i++)
                {
                    srcArrayA[i] = i;
                    srcArrayB[i] = i;
                }
                Pointer srcA = Pointer.to(srcArrayA);
                Pointer srcB = Pointer.to(srcArrayB);
                Pointer dst = Pointer.to(dstArray);

                JOCLHelper joclHelper = new JOCLHelper();
                joclHelper.initDevice(global_work_size,local_work_size,platformIndex,deviceIndex);
                joclHelper.addVar(CL_MEM_READ_ONLY | CL_MEM_COPY_HOST_PTR, Sizeof.cl_float * n, srcA);
                joclHelper.addVar(CL_MEM_READ_ONLY | CL_MEM_COPY_HOST_PTR, Sizeof.cl_float * n, srcB);
                joclHelper.addVar(CL_MEM_READ_WRITE, Sizeof.cl_float * n, null);
                

                joclHelper.createKernel("kernel_simple_test", programSource);
                joclHelper.runKernel();
long startTime = System.currentTimeMillis();
                joclHelper.loadOutput(dst, 3, n * Sizeof.cl_float);
long endTime = System.currentTimeMillis();

                System.out.print("global_work_size: " + global_work_size);
                System.out.print(" local_work_size: " + local_work_size);
                System.out.println(" time to run in secods: " + (endTime - startTime) );
            }
        }
    }
}

