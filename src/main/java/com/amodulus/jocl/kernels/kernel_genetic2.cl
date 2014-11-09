void generate_next_rnd_equation(int* phrase, int index, int num_columns){
    int max = 5;
    if (index % 2 == 0)
        max = num_columns;
    if (phrase[index] + 1 == max){
        generate_next_rnd_equation(phrase,index+1,num_columns);
    } else {
        for (int i=0; i < index; i=i+1) {
            phrase[i]=0;
        }
        phrase[index]++;
    }
}

float calculate_score(int* phrase, float* input, int num_slots){
    float sum = input[phrase[0]];
    for (int i=2; i < num_slots; i=i+1) {
        if (i % 2 == 0){
            if (phrase[i-1] == 1)
                sum = sum + input[phrase[i]];
            if (phrase[i-1] == 2)
                sum = sum - input[phrase[i]];
            if (phrase[i-1] == 3)
                sum = sum * input[phrase[i]];
            if (phrase[i-1] == 4){
                if (input[phrase[i]] > 0){
                    sum = sum / input[phrase[i]];
                } else {
                    sum = sum + 10000;
                }
            }
        }
    }
    return sum;
}

float calculate_avg_score(int* phrase, global const float* input, global const float* target, int num_slots, int num_rows){
    int num_columns = (num_slots + 1) / 2;
    float score = 0;
    float abs_diff_total = 0;
    for (int i=0; i < num_rows; i=i+1) {
        float sub_input[sizeof(num_slots)];
        for (int j=0; j < num_columns; j=j+1) {
            sub_input[j] = input[i*num_columns+j];
        }
        score = calculate_score(phrase,sub_input,num_slots);
        if (score > target[i]){
            abs_diff_total += score - target[i];
        } else {
            abs_diff_total += target[i] - score;
        }
    }
    return abs_diff_total;
}

int debug_result(global float* result, int* phrase, int current_index, int num_slots, float score, float winner){
    for (int i=0; i < num_slots; i=i+1) {
        result[current_index + i] = phrase[i];
    }
    result[current_index + num_slots] = score;
    result[current_index + num_slots + 1] = winner;
    return current_index + num_slots + 2;
}

__kernel void kernel_genetic2(
    __global const float* config,
    __global const float* input,
    __global const float* target,
    __global float* result)
{
    /*
     *  Initialize configuration
     */
    int gid             =       get_global_id(0);
    int iterations      =       (int) config[0];
    int num_columns     =       (int) config[1];
    int num_rows        =       (int) config[2];
    int num_slots       =       (int) config[3];
    float winner        =       10000000;
    int phrase[9];
    for (int i=0; i < 9; i=i+1)
        phrase[i] = 0;
        
    
    /*
     * - Loop through all combinations looking for best score
     * - Result contains all combinations
     */
    int result_index = 0;
    for (int i=0; i < iterations; i=i+1) {
        float score = calculate_avg_score(phrase, input, target, num_slots, num_rows);
        result_index = debug_result(result, phrase, result_index, num_slots, score, winner);
        if (score < winner){
            winner = score;
        }
        generate_next_rnd_equation(phrase,0,num_columns);
    }
}

