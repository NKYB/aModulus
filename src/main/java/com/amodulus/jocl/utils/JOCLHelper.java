/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.amodulus.jocl.utils;

import java.io.FileReader;
import java.io.IOException;
import static org.jocl.CL.*;
import org.jocl.*;

public class JOCLHelper {
    
    public cl_device_id devices[];
    public cl_device_id device;
    public cl_context_properties contextProperties;
    public cl_context context;
    public cl_command_queue commandQueue;
    public cl_mem memObjects[];
    public cl_program program;
    public cl_kernel kernel;
    public long global_work_size[];
    public long local_work_size[];
    
    public void initDevice(int global_work_size, int local_work_size, int platformIndex, int deviceIndex) {
        this.global_work_size = new long[]{global_work_size};
        this.local_work_size = new long[]{local_work_size};
//        this.global_work_size = new long[2];
//        this.global_work_size[0] = 1024;
//        this.global_work_size[1] = 1024;
//        this.local_work_size = new long[2];
//        this.local_work_size[0] = 8;
//        this.local_work_size[1] = 8;
        
        // The platform, device type and device number
        // that will be used
        final long deviceType = CL_DEVICE_TYPE_ALL;

        // Enable exceptions and subsequently omit error checks in this sample
        CL.setExceptionsEnabled(true);

        // Obtain the number of platforms
        int numPlatformsArray[] = new int[1];
        clGetPlatformIDs(0, null, numPlatformsArray);
        int numPlatforms = numPlatformsArray[0];

        // Obtain a platform ID
        cl_platform_id platforms[] = new cl_platform_id[numPlatforms];
        clGetPlatformIDs(platforms.length, platforms, null);
        cl_platform_id platform = platforms[platformIndex];

        // Initialize the context properties
        this.contextProperties = new cl_context_properties();
        contextProperties.addProperty(CL_CONTEXT_PLATFORM, platform);
        
        // Obtain the number of devices for the platform
        int numDevicesArray[] = new int[1];
        clGetDeviceIDs(platform, deviceType, 0, null, numDevicesArray);
        int numDevices = numDevicesArray[0];
        
        // Obtain a device ID 
        this.devices = new cl_device_id[numDevices];
        clGetDeviceIDs(platform, deviceType, numDevices, devices, null);
        this.device = devices[deviceIndex];
        
        String deviceName = JOCLDeviceQuery.getString(this.device, CL_DEVICE_NAME);
        System.out.printf("CL_DEVICE_NAME: \t\t\t%s\n", deviceName);
        int maxComputeUnits = JOCLDeviceQuery.getInt(this.device, CL_DEVICE_MAX_COMPUTE_UNITS);
        System.out.printf("CL_DEVICE_MAX_COMPUTE_UNITS:\t\t%d\n", maxComputeUnits);
        
        // Create a context for the selected device
        this.context = clCreateContext(this.contextProperties, 1, new cl_device_id[]{this.device}, null, null, null);
        
        // Create a command-queue for the selected device
        this.commandQueue = clCreateCommandQueue(this.context, this.device, 0, null);
    }
    
    public void addVar(long flags, long size, Pointer host_ptr){
        if (memObjects == null){
            this.memObjects = new cl_mem[1];
        } else {
            cl_mem memObjectsNew[] = new cl_mem[this.memObjects.length + 1];
            for (int i=0; i < this.memObjects.length; i++){
                memObjectsNew[i] = this.memObjects[i];
            }
            this.memObjects = memObjectsNew;
        }
        this.memObjects[this.memObjects.length - 1] = clCreateBuffer(this.context, flags, size, host_ptr, null);
    }
    
    public void createKernel(String name,String programSource){
        // Create the program from the source code
        this.program = clCreateProgramWithSource(this.context, 1, new String[]{ programSource }, null, null);
        // Build the program
        clBuildProgram(program, 0, null, null, null, null);
        // Create the kernel
        this.kernel = clCreateKernel(program, name, null);
        
        for (int i=0; i < this.memObjects.length; i++){
            clSetKernelArg(this.kernel, i, Sizeof.cl_mem, Pointer.to(this.memObjects[i]));
        }
    }
    
    public void runKernel(){
        clEnqueueNDRangeKernel(this.commandQueue, this.kernel, 1, null, this.global_work_size, this.local_work_size, 0, null, null);
    }
    
    public void loadOutput(Pointer host_ptr, int var_num, long size){
        // Read the output data
        clEnqueueReadBuffer(this.commandQueue, this.memObjects[var_num - 1], CL_TRUE, 0, size, host_ptr, 0, null, null);
        
        // Release kernel, program, and memory objects
        for (int i=0; i < this.memObjects.length; i++){
            clReleaseMemObject(this.memObjects[i]);
        }
        clReleaseKernel(this.kernel);
        clReleaseProgram(this.program);
        clReleaseCommandQueue(this.commandQueue);
        clReleaseContext(this.context);
    }
    
    public static String readEntireFile(String filename) throws IOException {
        FileReader in = new FileReader(filename);
        StringBuilder contents = new StringBuilder();
        char[] buffer = new char[4096];
        int read = 0;
        do {
            contents.append(buffer, 0, read);
            read = in.read(buffer);
        } while (read >= 0);
        return contents.toString();
    }
}
