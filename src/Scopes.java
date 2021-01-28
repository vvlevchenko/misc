import com.sun.source.tree.Scope;

public class Scopes {
    {
        System.out.println("Scopes::initializator");
    }
    Scopes(){
        System.out.println("Scopes::<init>");
    }

    public static void main(String... arg){

        {
            int a = 5;
            System.out.println("five is " + a);
        }

        {
            String a = "five";
            System.out.println(a + " is 5");
        }

        new Scopes();
        new Scopes();
        new A();
        new A();
    }
}

class A extends Scopes{
    {
        System.out.println("A::initializator");
    }

    A(){
        System.out.println("A::<init>");
    }
}
