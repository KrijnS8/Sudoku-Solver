package com.company;

import java.util.Arrays;

public class Main {

    public static boolean solve(int[][] bo) {

        int[] pos = findEmpty(bo);

        if (pos.length == 0) {
            return true;
        }

        for (int num = 1; num < 10; num++) {
            if (valid(bo, pos, num)) {

                bo[pos[0]][pos[1]] = num;

                if (solve(bo)) {
                    return true;
                }

                bo[pos[0]][pos[1]] = 0;
            }
        }

        return false;
    }

    public static boolean valid(int[][] bo, int[] pos, int num) {

        // checks row
        for (int col = 0; col < bo.length; col++) {
            if (bo[pos[0]][col] == num) {
                return false;
            }
        }

        // checks column
        for (int[] ints : bo) {
            if (ints[pos[1]] == num) {
                return false;
            }
        }

        //checks box
        int posY = pos[0] / 3;
        int posX = pos[1] / 3;

        for (int y = posY*3; y < posY*3 + 3; y++) {
            for (int x = posX*3; x < posX*3 + 3; x++) {
                if(bo[y][x] == num) {
                    return false;
                }
            }
        }

        return true;
    }

    public static int[] findEmpty(int[][] bo) {

        for (int row = 0; row < bo.length; row++) {
            for (int col = 0; col < bo.length; col++) {
                if (bo[row][col] == 0) {
                    return new int[] {row, col};
                }
            }
        }
        return new int[0];
    }

    public static void main(String[] args) {

        int[][] board = {
                {7, 8, 0, 4, 0, 0, 1, 2, 0},
                {6, 0, 0, 0, 7, 5, 0, 0, 9},
                {0, 0, 0, 6, 0, 1, 0, 7, 8},
                {0, 0, 7, 0, 4, 0, 2, 6, 0},
                {0, 0, 1, 0, 5, 0, 9, 3, 0},
                {9, 0, 4, 0, 6, 0, 0, 0, 5},
                {0, 7, 0, 3, 0, 0, 0, 1, 2},
                {1, 2, 0, 0, 0, 7, 4, 0, 0},
                {0, 4, 9, 2, 0, 6, 0, 0, 7}
        };

        solve(board);

        for (int[] arr : board) {
            System.out.println(Arrays.toString(arr));
        }
    }
}
