__kernel void kernel_func(
    __global int* result)
{
    int gid = get_global_id(0);
    int total = 0;
    for (int j=0; j < 100000000; j=j+1) {
        for (int i=0; i < 100000000; i=i+1) {
            total = total + 1;
        }
    }
    result[gid] = total;
}