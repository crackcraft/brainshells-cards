import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.Map;

public class Calc {
    private final static int FULL_WIDTH = 636;
    private final static int FULL_HEIGHT = 1166;
    private final static int OFFSET_X = 147;
    private final static int OFFSET_Y = 589;
    private final static int SHIFT_X = 72;
    private final static int RANK_W = 29;
    private final static int RANK_H = 26;

    private final static int SUIT_OFFSET_X = 21;
    private final static int SUIT_OFFSET_Y = 46;
    private final static int SUIT_W = 32;
    private final static int SUIT_H = 32;
    private final static int MAX_CARDS = 5;
    private static long idx = System.currentTimeMillis();


    private static int[] vect(BufferedImage img) throws IOException {
        int offX = 0; int offY = 0;
        int[][] buff = new int[RANK_W][RANK_H];
        int min = Integer.MAX_VALUE;
        int max = Integer.MIN_VALUE;
        for (int x = 0; x < RANK_W; x++) {
            for (int y = 0; y < RANK_H; y++) {
                Color c = new Color(img.getRGB(offX + x, offY + y));
                int sum = c.getGreen() + c.getBlue();
                min = Math.min(sum, min);
                max = Math.max(sum, max);
                buff[x][y] = sum;
            }
        }
        int avg = (min + max) / 2;

        int z = 0;

        int[] vect = new int[RANK_W + RANK_H];
        int skip = 0;


        for (int x = 0; x < RANK_W; x++) {
            int col = 0;
            for (int y = 0; y < RANK_H; y++) {
                int b = (buff[x][y] < avg) ? 1 : 0;
                buff[x][y] = b;
                z += b;
                col += b;
            }
            if (col > 1) {
                vect[x - skip] = col;
            } else {
                skip++;
            }
        }

        String r = (z < 120) ? "J" :
                (z < 142) ? "7" :
                        (z > 263) ? "10" :
                                (z > 227) ? "Q" :
                                        null; // 4 K 5 3 2 A 6 9 8

//        if (r != null) {
//            throw new IllegalArgumentException(r);
//        }

        for (int y = 0; y < RANK_H; y++) {
            int row = 0;
            for (int x = 0; x < RANK_W; x++) {
                row += buff[x][y];
            }
            if (row > 1) {
                vect[RANK_W + y - skip] = row;
            } else {
                skip++;
            }
        }
        return vect;
    }


    public static void main(String[] args) throws IOException {

        int n = 0;
        double[] v = new double[RANK_W+RANK_H];
        for (File f : (new File("./rank/J")).listFiles()) {
            int i=0;
            for (int x : vect(ImageIO.read(f))) {
                v[i++] += x;
            }
            n++;
        }
        for (double x : v) {
            System.out.printf("%.1f, ", x/n);
        }


    }
}
