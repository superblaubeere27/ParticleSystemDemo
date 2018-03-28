package me.superblaubeere27.util;

public class Color {

    public static java.awt.Color rainbow(float speed, float off) {

        double time = (double) System.currentTimeMillis() / speed;
        time += off;
        time %= 255.0f;
        return java.awt.Color.getHSBColor((float) (time / 255.0f), 1.0f, 1.0f);

    }

    public static void main(String[] args) {

        while(true) {

            System.out.println(rainbow(100.0f, 0.0f));

        }

    }

}
