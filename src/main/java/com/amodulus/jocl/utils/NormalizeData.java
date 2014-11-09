package com.symcor.jocl.utils;

public class NormalizeData {
    public static float[] normalize_float_array(float values[]){
        float[] results = new float[values.length];
        float sum =0;
        float max =0;
        float min =0;
        float val =0;
        for (int i=0; i<values.length; i++){
            val = values[i];
            sum += val;
            if (i == 0){
                min = val;
                max = val;
            }
            if (val < min)
                min = val;
            if (val > max)
                max = val;  
            System.out.println(i + " val: " + val);
        }
        float diff = max - min;
        System.out.println("diff: " + diff);
        
        float scale = 1;
        if (diff > 0)
            scale = 1 / diff;
        System.out.println("scale: " + scale);
        
        
        for (int i=0; i<values.length; i++){
            val = values[i];
            results[i] = (val - min) * scale;
            System.out.println(i + " result: " + results[i]);
        }
        System.out.println("getMean: " + getMean(results));
        System.out.println("getVariance: " + getVariance(results));
        System.out.println("getStdDev: " + getStdDev(results));
        return results;
    }
    
    public static float getMean(float data[]){
        int size = data.length;
        float sum = 0;
        for(float a : data)
            sum += a;
            return sum/size;
    }

    public static float getVariance(float data[]){
        int size = data.length;
        float mean = getMean(data);
        float temp = 0;
        for(double a : data)
            temp += (mean-a)*(mean-a);
            return temp/size;
    }

    public static float getStdDev(float data[]){
        return (float) Math.sqrt(getVariance(data));
    }
}
