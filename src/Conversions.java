import java.awt.desktop.SystemEventListener;

public class Conversions {

    public static void main(String... args) {
        double a = 123.00001;
        int b = (int)a;
        System.out.println("a: " + a + ", b: " + b);
    }
}
