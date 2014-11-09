int rand(int* seed){
    int const a = 16807; 
    int const m = 2147483647; 

    long temp = *seed * a;
    temp = temp % m;
    *seed = temp;
    
    return(*seed);
}

int rand_between(int* seed, int min, int max){
    *seed = rand(seed);
    return (*seed % (max - min + 1)) + min;
}

__kernel void kernel_rnd_number_test(
    __global const float *a,
    __global const float *b,
    __global const float *c,
    __global const float *o,
    __global float *r)
{
    int gid = get_global_id(0);
    int seed = gid + 1;
    int test =  rand_between(&seed,5,10);
    r[gid] = test;
}


