package com.kattis;

import java.io.*;
import java.util.StringTokenizer;

public class Main {

    public final static Kattio io = new Kattio(System.in, System.out);

    public static void main(String[] args) {

        int M = io.getInt();
        int N = io.getInt();
        double R = io.getDouble();

        int a_x = io.getInt();
        int a_y = io.getInt();
        int b_x = io.getInt();
        int b_y = io.getInt();

        final double r = R / N;
        double R2 = r * Math.min(a_y, b_y);
        double via_origo = r * a_y + r * b_y;
        double not_origo = (R2 * Math.PI / M) * Math.abs(a_x - b_x) + r * Math.abs(a_y - b_y);
        System.out.println(Math.min(via_origo, not_origo));

    }
}


class Kattio extends PrintWriter {
    public Kattio(InputStream i) {
        super(new BufferedOutputStream(System.out));
        r = new BufferedReader(new InputStreamReader(i));
    }
    public Kattio(InputStream i, OutputStream o) {
        super(new BufferedOutputStream(o));
        r = new BufferedReader(new InputStreamReader(i));
    }

    public boolean hasMoreTokens() {
        return peekToken() != null;
    }

    public int getInt() {
        return Integer.parseInt(nextToken());
    }

    public double getDouble() {
        return Double.parseDouble(nextToken());
    }

    public long getLong() {
        return Long.parseLong(nextToken());
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
            } catch (IOException e) { }
        return token;
    }

    private String nextToken() {
        String ans = peekToken();
        token = null;
        return ans;
    }
}
