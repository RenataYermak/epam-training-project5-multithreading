package by.epam.yermak.multithreading.util;

import java.util.ResourceBundle;

public class Resource {
    private static final ResourceBundle RESOURCE_BUNDLE = ResourceBundle.getBundle("matrix");
    private static final String N = RESOURCE_BUNDLE.getString("N");
    private static final String Y = RESOURCE_BUNDLE.getString("Y");
   private final int n = Integer.parseInt(N);
   private final int y = Integer.parseInt(Y);

    public int getN() {
        return n;
    }

    public int getY() {
        return y;
    }
}
