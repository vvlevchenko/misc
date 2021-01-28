import com.google.gson.*;
import com.google.gson.annotations.Expose;

import java.io.File;
import java.lang.reflect.Type;

public class GsonTest {
    static public class Test{
        @Expose
        public String name;
        @Expose
        public File file;

        @Override
        public String toString() {
            return "Test{" +
                    "name='" + name + '\'' +
                    ", file=" + file +
                    '}';
        }
    }
    static public class FileSerializer implements JsonSerializer<File>, JsonDeserializer<File> {

        @Override
        public File deserialize(JsonElement jsonElement, Type type, JsonDeserializationContext jsonDeserializationContext) throws JsonParseException {
            String path = jsonElement.getAsJsonObject().get("path").getAsString();
            return new File(path);
        }

        @Override
        public JsonElement serialize(File file, Type type, JsonSerializationContext jsonSerializationContext) {
            JsonObject obj = new JsonObject();
            obj.add("path", new JsonPrimitive(file.getPath()));
            return obj;
        }
    }

    public static void main(String... arg) {

        Gson gson = new GsonBuilder().setPrettyPrinting().excludeFieldsWithoutExposeAnnotation().registerTypeAdapter(File.class, new FileSerializer()).create();

        Test test = new Test();
        test.name = "foo";
        test.file = new File("/tmp/long/long/path/foo.txt");
        String str = gson.toJson(test);
        System.out.println(str);
        Test t = gson.fromJson(str, Test.class);
        System.out.println(t);
    }

}
