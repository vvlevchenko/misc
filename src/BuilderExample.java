public class BuilderExample {
    final String msg;
    final int param;
    private BuilderExample(String msg, int p0) {
        this.msg = msg;
        this.param = p0;
    }

    public static Builder newBuilder() {
        return new Builder();
    }

    private static class Builder{
        private String msg = "Not initialized";
        private int param = 42;
        Builder msg(String p0){
            msg = p0;
            return this;
        }

        Builder param(int p0){
            param = p0;
            return this;
        }

        BuilderExample build() {
            return new BuilderExample(msg, param);
        }
    }

    public static void main(String... arg){
        BuilderExample.newBuilder().msg("Hello world").param(53).build();
    }
}
