__kernel void kernel_simple_test(
    __global const float *a,
    __global const float *b,
    __global float *c)
{
    int gid = get_global_id(0);
    //c[gid] = a[gid] * b[gid];
    
    for (int i=0; i < 1000000; i=i+1) {
        for (int h=0; h < 100; h=h+1) {
            //for (int j=0; j < 1000000; j=j+1) {
                c[gid] = a[gid] * b[gid];
            //}
        }
    }
}