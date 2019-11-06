package com.kattis;

import java.io.*;
import java.util.StringTokenizer;

public class Main {

    public static final Kattio io = new Kattio(System.in, System.out);

    public static void main(String[] args) {

        final double L = Math.pow(2, -5.0/4.0) * 2;
        final double S = Math.pow(2, -3.0/4.0);
        final double A1_area = L * S;
        double tapeLength = 0.0;

        final int smallestSize = io.getInt();
        double[] longSides = new double[smallestSize + 1];

        longSides[0] = 2*S;
        longSides[1] = L;
        for (int j = 2; j < longSides.length; j++) {
            longSides[j] += j % 2 == 0 ? S / Math.pow(2, (j / 2) - 1) : L / Math.pow(2, j / 2);
        }

        double[] areas = new double[smallestSize + 1];

        areas[0] = 2 * A1_area;
        areas[1] = A1_area;
        for (int j = 2; j < areas.length; j++) {
            areas[j] = areas[j-1] / 2;
        }

        int[] papers = new int[smallestSize + 1];
        papers[0] = 0;
        papers[1] = 0;
        int i = 2;

        while (io.hasMoreTokens()) {
            papers[i] = io.getInt();
            i++;
        }

        i = 1;

        double totalArea = 0.0;
        while (totalArea < A1_area && i < smallestSize) {
            i++;
            totalArea += areas[i] * papers[i];
        }

        if(totalArea >= A1_area) {
            double areaFromSmallestSize = A1_area - (totalArea - areas[i] * papers[i]);

            double nrOfSmallestSize = areaFromSmallestSize / areas[i];
            papers[i - 1] += nrOfSmallestSize/2;
            tapeLength += nrOfSmallestSize/2 * longSides[i];

            int currentSize = i-1;

            while(currentSize > 1) {
                int tapes = papers[currentSize] / 2;
                papers[currentSize - 1] += tapes;
                tapeLength += currentSize % 2 == 0 ? S * tapes / Math.pow(2, (currentSize / 2) - 1) : L * tapes / Math.pow(2, currentSize / 2);
                currentSize--;
            }
        } else {
            System.out.println("impossible");
            System.exit(0);
        }

        System.out.println(tapeLength);
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
