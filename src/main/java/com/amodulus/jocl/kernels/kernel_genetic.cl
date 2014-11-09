__kernel void kernel_genetic(
    __global const float *target,
    __global int *result)
{
    int gid = get_global_id(0);
    float temp_result = 0;
    for (int i=0; i < 100; i=i+1) {
        for (int j=0; j < 100; j=j+1) {
            for (int k=0; k < 100; k=k+1) {
                for (int l=0; l < 100; l=l+1) {
                    
                    for (int m=0; m < 3; m=m+1) {
                        for (int n=0; n < 3; n=n+1) {
                            for (int p=0; p < 3; p=p+1) {
                                
                                if (m==0 && n==0 && p == 0) temp_result = i + j + k + l;
                                if (m==0 && n==0 && p == 1) temp_result = i + j + k - l;
                                if (m==0 && n==0 && p == 2) temp_result = i + j + k * l;
                                if (m==0 && n==1 && p == 0) temp_result = i + j - k + l;
                                if (m==0 && n==1 && p == 1) temp_result = i + j - k - l;
                                if (m==0 && n==1 && p == 2) temp_result = i + j - k * l;
                                if (m==0 && n==2 && p == 0) temp_result = i + j * k + l;
                                if (m==0 && n==2 && p == 1) temp_result = i + j * k - l;
                                if (m==0 && n==2 && p == 2) temp_result = i + j * k * l;
                                
                                if (m==1 && n==0 && p == 0) temp_result = i - j + k + l;
                                if (m==1 && n==0 && p == 1) temp_result = i - j + k - l;
                                if (m==1 && n==0 && p == 2) temp_result = i - j + k * l;
                                if (m==1 && n==1 && p == 0) temp_result = i - j - k + l;
                                if (m==1 && n==1 && p == 1) temp_result = i - j - k - l;
                                if (m==1 && n==1 && p == 2) temp_result = i - j - k * l;
                                if (m==1 && n==2 && p == 0) temp_result = i - j * k + l;
                                if (m==1 && n==2 && p == 1) temp_result = i - j * k - l;
                                if (m==1 && n==2 && p == 2) temp_result = i - j * k * l;
                                
                                if (m==2 && n==0 && p == 0) temp_result = i * j + k + l;
                                if (m==2 && n==0 && p == 1) temp_result = i * j + k - l;
                                if (m==2 && n==0 && p == 2) temp_result = i * j + k * l;
                                if (m==2 && n==1 && p == 0) temp_result = i * j - k + l;
                                if (m==2 && n==1 && p == 1) temp_result = i * j - k - l;
                                if (m==2 && n==1 && p == 2) temp_result = i * j - k * l;
                                if (m==2 && n==2 && p == 0) temp_result = i * j * k + l;
                                if (m==2 && n==2 && p == 1) temp_result = i * j * k - l;
                                if (m==2 && n==2 && p == 2) temp_result = i * j * k * l;
                                
                                if (temp_result == target[gid]){
                                    result[gid * 7] = i;
                                    result[gid * 7 + 1] = m;
                                    result[gid * 7 + 2] = j;
                                    result[gid * 7 + 3] = n;
                                    result[gid * 7 + 4] = k;
                                    result[gid * 7 + 5] = p;
                                    result[gid * 7 + 6] = l;
                                    return;
                                }
                            }
                        }
                    }    
                    

                }
            }
        }    
    }
}