public class ScopeF {
    String name;

    {
        System.out.println(name);
    }

    public ScopeF(String name) {
        this.name = name;
    }
}
