import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.LinkedHashMap;
import java.util.Map;

public class Main {
    private final static int FULL_WIDTH = 636;
    private final static int FULL_HEIGHT = 1166;
    private final static int OFFSET_X = 147;
    private final static int OFFSET_Y = 589;
    private final static int SHIFT_X = 72;
    private final static int RANK_WIDTH = 29;
    private final static int RANK_HEIGHT = 26;
    private final static int SUIT_OFFSET_X = 21;
    private final static int SUIT_OFFSET_Y = 46;

    private static final Map<String, int[]> RANKS = new LinkedHashMap<>();
    static {
        RANKS.put("2", new int[]{4, 6, 9, 11, 11, 11, 11, 10, 10, 10, 12, 14, 14, 13, 11, 6, 8, 11, 12, 10, 8, 5, 4, 4, 4, 4, 4, 5, 5, 5, 5, 5, 5, 5, 9, 16, 16, 11, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0});
        RANKS.put("3", new int[]{6, 8, 7, 7, 10, 10, 11, 12, 14, 15, 16, 17, 14, 12, 8, 14, 14, 11, 5, 5, 4, 4, 4, 4, 6, 8, 8, 5, 4, 4, 4, 6, 8, 12, 13, 10, 5, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0});
        RANKS.put("4", new int[]{3, 5, 6, 7, 8, 8, 8, 8, 8, 8, 8, 18, 22, 22, 22, 3, 3, 3, 4, 5, 5, 6, 7, 8, 8, 8, 8, 8, 8, 8, 8, 8, 18, 18, 18, 4, 4, 4, 4, 4, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0});
        RANKS.put("5", new int[]{3, 13, 16, 16, 13, 10, 9, 9, 10, 11, 12, 13, 14, 12, 8, 10, 13, 13, 7, 3, 3, 3, 3, 7, 11, 13, 11, 7, 4, 4, 4, 4, 6, 9, 13, 12, 9, 3, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0});
        RANKS.put("6", new int[]{9, 14, 17, 19, 14, 11, 10, 10, 9, 9, 10, 12, 13, 16, 13, 8, 6, 9, 11, 12, 9, 5, 4, 4, 6, 11, 14, 14, 11, 9, 8, 8, 8, 8, 9, 11, 12, 10, 5, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0});
        RANKS.put("7", new int[]{3, 4, 6, 7, 9, 11, 11, 11, 11, 11, 11, 10, 8, 6, 4, 11, 15, 15, 9, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 5, 4, 5, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0});
        RANKS.put("8", new int[]{4, 13, 17, 19, 15, 12, 10, 9, 9, 10, 11, 14, 18, 19, 15, 10, 6, 9, 12, 11, 8, 7, 7, 7, 8, 11, 11, 11, 12, 10, 8, 7, 7, 8, 9, 13, 13, 10, 3, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0});
        RANKS.put("9", new int[]{6, 11, 15, 16, 13, 11, 9, 9, 9, 10, 11, 12, 15, 19, 16, 12, 8, 8, 12, 12, 9, 8, 8, 8, 8, 8, 10, 12, 15, 13, 9, 4, 4, 4, 7, 11, 12, 10, 5, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0});
        RANKS.put("10", new int[]{3, 4, 3, 3, 10, 22, 22, 22, 7, 13, 16, 18, 13, 9, 8, 7, 6, 6, 6, 7, 8, 9, 13, 18, 15, 12, 9, 13, 19, 20, 17, 13, 12, 12, 11, 11, 11, 11, 11, 11, 11, 11, 12, 12, 13, 15, 15, 13, 7, 0, 0, 0, 0, 0, 0});
        RANKS.put("J", new int[]{3, 5, 4, 4, 4, 3, 3, 4, 4, 16, 21, 20, 19, 9, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 5, 6, 9, 11, 11, 8, 3, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0});
        RANKS.put("Q", new int[]{6, 11, 14, 16, 12, 10, 8, 8, 8, 6, 6, 7, 8, 11, 13, 12, 11, 11, 16, 18, 17, 13, 8, 10, 14, 14, 12, 10, 8, 8, 8, 8, 8, 8, 8, 10, 12, 13, 13, 11, 13, 16, 18, 14, 7, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0});
        RANKS.put("K", new int[]{22, 22, 22, 18, 4, 4, 4, 5, 8, 10, 11, 10, 11, 11, 11, 9, 6, 4, 5, 9, 9, 9, 9, 9, 9, 9, 8, 8, 9, 11, 11, 11, 10, 9, 8, 8, 8, 9, 8, 8, 4, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0});
        RANKS.put("A", new int[]{3, 5, 7, 9, 9, 9, 11, 13, 12, 11, 9, 10, 12, 12, 12, 10, 9, 9, 8, 6, 3, 3, 5, 5, 7, 7, 8, 7, 7, 8, 7, 8, 7, 8, 11, 16, 17, 17, 12, 7, 8, 8, 8, 4, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0});
    }

    private static String rank(BufferedImage img, final int offX, final int offY) {
        int[][] buff = new int[RANK_WIDTH][RANK_HEIGHT];
        int min = Integer.MAX_VALUE;
        int max = Integer.MIN_VALUE;
        for (int x = 0; x < RANK_WIDTH; x++) {
            for (int y = 0; y < RANK_HEIGHT; y++) {
                Color c = new Color(img.getRGB(offX + x, offY + y));
                int sum = c.getGreen() + c.getBlue();
                min = Math.min(sum, min);
                max = Math.max(sum, max);
                buff[x][y] = sum;
            }
        }
        int avg = (min + max) / 2;

        int[] vect = new int[RANK_WIDTH + RANK_HEIGHT];
        int idx = 0;

        for (int x = 0; x < RANK_WIDTH; x++) {
            int col = 0;
            for (int y = 0; y < RANK_HEIGHT; y++) {
                if (buff[x][y] < avg)
                    col++;
            }
            if (col > 1)
                vect[idx++] = col;
        }

        for (int y = 0; y < RANK_HEIGHT; y++) {
            int row = 0;
            for (int x = 0; x < RANK_WIDTH; x++) {
                if (buff[x][y] < avg)
                    row++;
            }
            if (row > 1)
                vect[idx++] = row;
        }

        String r = null;
        int ww = Integer.MAX_VALUE;
        for (Map.Entry<String, int[]> e : RANKS.entrySet()) {
            final int[] v = e.getValue();
            int w = 0;
            for (int i = 0; i < vect.length; i++) {
                int d = vect[i] - v[i];
                w += d * d;
            }
            if (w < ww) {
                ww = w;
                r = e.getKey();
            }
        }

        return r;
    }

    private static boolean isWhite(int i) {
        Color c = new Color(i);
        return c.getRed() > 60 && c.getGreen() > 60 && c.getBlue() > 60;
    }

    private static boolean isRed(int i) {
        Color c = new Color(i);
        return c.getRed() - c.getGreen() > 60 && c.getRed() - c.getBlue() > 60;
    }

    private static String card(BufferedImage img, final int offX, final int offY) {
        if (!isWhite(img.getRGB(offX + SUIT_OFFSET_X + 29, offY + SUIT_OFFSET_Y + 29)))
            return null;
        int c1 = img.getRGB(offX + SUIT_OFFSET_X + 16, offY + SUIT_OFFSET_Y + 3);
        int c2 = img.getRGB(offX + SUIT_OFFSET_X + 7, offY + SUIT_OFFSET_Y + 9);
        int c3 = img.getRGB(offX + SUIT_OFFSET_X + 23, offY + SUIT_OFFSET_Y + 9);
        return (isRed(c1) ? "d" /* черви */ : isWhite(c1) ? "h" /* бубны */ : (isWhite(c2) && isWhite(c3)) ? "c" /* крести */ : "s" /* пики */)
                        + rank(img, offX, offY);
    }

    private static String parse(File f) throws IOException {
        BufferedImage img = ImageIO.read(f);
        if(img == null)
            throw new IllegalArgumentException("file '" + f.getAbsolutePath() + "' is not an image");
        if (FULL_WIDTH != img.getWidth() || FULL_HEIGHT != img.getHeight())
            throw new IllegalArgumentException("image '" + f.getAbsolutePath() + "' has unexpected size");

        StringBuilder buff = new StringBuilder();
        for (int i = 0; ; i++) {
            String res = card(img, OFFSET_X + i * SHIFT_X, OFFSET_Y);
            if (res == null)
                break;
            buff.append(res);
        }
        return buff.toString();
    }

    public static void main(String[] args) {
        for (File f : (new File(args[0])).listFiles()) {
            try {
                System.out.println(f.getName() + " - " + parse(f));
            } catch (Exception ex) {
                System.err.println(ex);
            }
        }
    }
}
