public class Test1 {
    static void foo(String[] arg) {
        System.out.println(arg[1]);
    }
    public static void main(String... arg) {
        foo(new String[]{"String", "v1"});
    }
}
