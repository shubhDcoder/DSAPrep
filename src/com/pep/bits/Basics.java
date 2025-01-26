package com.pep.bits;

public class Basics {
    public static void main(String[] args) {
//        basics_1();
//        basics_2();
        kernighansAlgo();
    }

    // test how many set bits are there
    public static void kernighansAlgo() {
        int count = 0;
        int number = 7854;
        System.out.println(Integer.toBinaryString(number));
        while (number != 0) {
            count += 1;
            number = (number & (~(number & -number)));
        }

        System.out.println("number of set bits are " + count);
    }

    public static void basics_2() {
        int number = 156;
        int bitToCheck = 4;
        int mask = 1 << bitToCheck;
        System.out.println("number              " + Integer.toBinaryString(number));
        System.out.println("checking bit at position is on or not : " + bitToCheck + " = " + ((mask & number) > 0));
        if ((mask & number) == 0) number = (number | mask);
        else number = (~mask & number);
        System.out.println("number_1            " + Integer.toBinaryString(number));
        System.out.println("right most set bit  " + Integer.toBinaryString(number & (~(number - 1))));
        System.out.println("right most set bit  " + Integer.toBinaryString(number & (-number)));
    }

    public static void basics_1() {
        byte testDigit = 110;
        byte result = (byte) (testDigit + 17);
        System.out.println(result);
        result = (byte) (testDigit + 18);
        System.out.println(result);
        result = (byte) (testDigit + 19);
        System.out.println(result);
        result = (byte) (testDigit + 20);
        System.out.println(result);
        result = (byte) (testDigit + 20 + 256);
        System.out.println(result);
        result = (byte) (testDigit + 20 + 256 + 256 + 256 + 256 + 256 + 256 + 256 + 256 + 256 + 256 + 256);
        System.out.println(result);
        System.out.println((byte) 156);
    }
}
