package com.domain;

import java.util.Arrays;

/**
 * How to: Work at Google — Example Coding/Engineering Interview
 * https://www.youtube.com/watch?v=XKu_SEDAykw
 */

public class main {

    public static void main(String[] args) {
        /**
         * Question: Collection of numbers, find a matching pair that is equal to a given sum
         *
         * [1, 2, 3, 9]  Sum = 8  -> return false
         * [1, 2, 4, 4]  Sum = 8  -> return true
         */

        int[] row1 = { 1, 2, 3, 9 };
        int[] row2 = { 1, 2, 4, 4 };
        int sum = 8;

        // Option 1
        System.out.println("Option 1: ");
        System.out.println(option1(row1, sum));
        System.out.println(option1(row2, sum));

        // Option 2
        System.out.println("Option 2: ");
        System.out.println(option2(row1, sum));
        System.out.println(option2(row2, sum));

        // Option 3
        System.out.println("Option 3: ");
        System.out.println(option3(row1, sum));
        System.out.println(option3(row2, sum));
    }


    /**
     * Option 1: (most time consuming)
     */
    public static boolean option1(int[] row, int sum) {
        for (int i = 0; i < row.length - 1; i++) {
            if (row[i] + row[i + 1] == sum) return true;
        }
        return false;
    }

    /**
     * Option 2: Binary search;
     *   -> if first number on list is for example a 1, then go search for a 7 (if sum is 8)
     *   -> if first number is 2, then go search for a six (if sum is 8)
     *   etc etc
     */
    public static boolean option2(int[] row, int sum) {
        int i = 0;
        int x = sum - row[i];
        int retVal = 0;

        while (true) {
            retVal = Arrays.binarySearch(row, x);
            //System.out.println(retVal);
            if (retVal > 0 && retVal <= row.length && (row[i] + row[retVal]) == sum) return true;
            if (i >= row.length - 1) return false;
            i++;
            x = sum - row[i];
        }
    }

    /**
     * Option 3: (assuming the list is ordered to start with)
     */
    public static boolean option3(int[] row, int sum) {
        // To store indexes of the result pair
        int res_min = 0;
        int res_max = 0;

        // Initialize left (min) and right (max) indexes and defference between
        int min = 0;
        int max = row.length - 1;
        int dif = Integer.MAX_VALUE;

        // While there is an element between min and max (else they crossed and then there was no pair that is equals to the sum -> return false)
        while (max > min) {
            // Check if this pair is closer than the closest pair so far
            if (Math.abs(row[min] + row[max] - sum) < dif) {
                res_min = min;
                res_max = max;
                dif = Math.abs(row[min] + row[max] - sum);
            }

            // If this pair has more sum, move to smaller values, else move to greater values
            if (row[min] + row[max] > sum) {
                max--;
            }
            else {
                min++;
            }
        }
        return row[res_min] + row[res_max] == sum;
    }
}
