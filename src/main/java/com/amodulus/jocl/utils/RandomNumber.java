package com.symcor.jocl.utils;

public class RandomNumber {
    public static int getRandomNumber(int Min, int Max){
        return Min + (int)(Math.random() * ((Max - Min) + 1));
    }
}
