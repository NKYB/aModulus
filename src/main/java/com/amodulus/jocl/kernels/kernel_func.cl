__kernel void kernel_func(
    __global float* result)
{
    int gid = get_global_id(0);
    for (int i=0; i < 10000000000; i=i+1) {
        result[0] = i * i;
    }
    //result[0] = gid;
}