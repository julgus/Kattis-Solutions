package com.kattis;

import java.io.*;
import java.util.StringTokenizer;

public class Main {

    private final static Kattio io = new Kattio(System.in, System.out);
    private final static int BOARD_SIZE = 8;
    private final static String SUCCESS_MSG = "Diamond!";
    private final static String ERROR_MSG = "Bug!";
    private final static char DIAMOND = 'D';
    private final static char ICE = 'I';
    private final static char ROCK = 'C';
    private final static char EMPTY = '.';

    private static int x,y;
    private static DIRECTION facing;
    private static String[] board;

    private enum DIRECTION {UP, DOWN, RIGHT, LEFT};

    public static void main(String[] args) {

        facing = DIRECTION.RIGHT;
        x = 0;
        y = 7;

        board = new String[BOARD_SIZE];

        for (int i = 0; i < 8; i++) {
            board[i] = io.getWord();
        }

        String commands = io.getWord();

        for (int j = 0; j < commands.length(); j++) {
            char command = commands.charAt(j);

            switch (command) {
                case 'F':
                    if (checkForward(EMPTY) || checkForward(DIAMOND)) {
                        move();
                    } else {
                        System.out.println(ERROR_MSG);
                        System.exit(0);
                    }
                    break;
                case 'R':
                    turnRight();
                    break;
                case 'L':
                    turnLeft();
                    break;
                case 'X':
                    fireWeapon();
                    break;
                default: break;
            }

        }
        System.out.println(square(x, y) == DIAMOND ? SUCCESS_MSG : ERROR_MSG);
    }

    private static void move() {
        switch (facing) {
            case UP: y--; break;
            case DOWN: y++; break;
            case LEFT: x--; break;
            case RIGHT: x++; break;
            default: break;
        }
    }

    private static void turnRight() {
        switch (facing) {
            case UP:
                facing = DIRECTION.RIGHT;
                break;
            case DOWN:
                facing = DIRECTION.LEFT;
                break;
            case LEFT:
                facing = DIRECTION.UP;
                break;
            case RIGHT:
                facing = DIRECTION.DOWN;
                break;
            default: break;
        }
    }

    private static void turnLeft() {
        switch (facing) {
            case UP:
                facing = DIRECTION.LEFT;
                break;
            case DOWN:
                facing = DIRECTION.RIGHT;
                break;
            case LEFT:
                facing = DIRECTION.DOWN;
                break;
            case RIGHT:
                facing = DIRECTION.UP;
                break;
            default: break;
        }
    }

    private static void fireWeapon() {
        if (checkForward(ICE)) {
            switch (facing) {
                case UP:
                    replaceSquare(x, y-1, EMPTY); break;
                case DOWN:
                    replaceSquare(x, y+1, EMPTY); break;
                case LEFT:
                    replaceSquare(x-1, y, EMPTY); break;
                case RIGHT:
                    replaceSquare(x+1, y, EMPTY); break;
                default: break;
            }
        } else {
            System.out.println(ERROR_MSG);
            System.exit(0);
        }
    }

    private static void replaceSquare(int x, int y, char c) {
        char[] squares = board[y].toCharArray();
        squares[x] = c;
        board[y] = String.valueOf(squares);
    }

    private static boolean checkForward(char tile) {
        switch (facing) {
            case UP:
                return (square(x, y-1) == tile);
            case DOWN:
                return (square(x, y+1) == tile);
            case LEFT:
                return (square(x-1, y) == tile);
            case RIGHT:
                return (square(x+1, y) == tile);
            default: return false;
        }
    }

    private static char square(int x, int y) {
        if (x > 7 || x < 0 || y > 7 || y < 0) {
            return 'e'; // Out of bounds
        }
        return board[y].charAt(x);
    }

}


final class Kattio extends PrintWriter {
    public Kattio(InputStream i) {
        super(new BufferedOutputStream(System.out));
        r = new BufferedReader(new InputStreamReader(i));
    }

    public Kattio(InputStream i, OutputStream o) {
        super(new BufferedOutputStream(o));
        r = new BufferedReader(new InputStreamReader(i));
    }


    public String getWord() {
        return nextToken();
    }

    private BufferedReader r;
    private String line;
    private StringTokenizer st;
    private String token;

    private String peekToken() {
        if (token == null)
            try {
                while (st == null || !st.hasMoreTokens()) {
                    line = r.readLine();
                    if (line == null) return null;
                    st = new StringTokenizer(line);
                }
                token = st.nextToken();
            } catch (IOException e) {
            }
        return token;
    }

    private String nextToken() {
        String ans = peekToken();
        token = null;
        return ans;
    }
}