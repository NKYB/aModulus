__kernel void kernel_func(
    __global float* result)
{
    int gid = get_global_id(0);
    //for (int k=0; k < 1000000000; k=k+1) {
        for (int l=0; l < 1000000000; l=l+1) {
            for (int j=0; j < 1000000000; j=j+1) {
                for (int i=0; i < 1000000000; i=i+1) {
                    result[0] = result[0] + 1;
                }
            }
        }
    //}
    //result[0] = gid;
}