package helper;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;


// singleton class with generic functions for manipulating a csv file (read/write)
public class CSVRepository {
    // reference to the unique object of the class
    private static CSVRepository instance;

    // private constructor
    private CSVRepository() {

    }

    // function that returns a reference to the unique object of the class
    public static CSVRepository getInstance() {
        if (instance == null) {
            instance = new CSVRepository();
        }

        return instance;
    }

    // write data in a csv
    public <T> void writeCSV(List<T> records, String path) throws Exception {
        Field[] fields = records.get(0).getClass().getDeclaredFields();
        for (Field field : fields) {
            field.setAccessible(true);
        }

        try {
            File file = new File(path);
            FileWriter fileWriter = new FileWriter(file, true);
            BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
            for (T record : records) {
                for (int i = 0; i < fields.length - 1; i++) {
                    Object value = null;
                    if (fields[i].getType().toString().contains("theatre") || fields[i].getType().toString().contains("interface")) {
                       continue;
                    }

                    else {
                        value = fields[i].get(record);
                    }

                    if (value != null) {
                        bufferedWriter.write(value.toString());
                        bufferedWriter.write(",");
                    }
                }

                Object value = fields[fields.length - 1].get(record);
                if (value != null) {
                    bufferedWriter.write(value.toString() + "\n");
                }
            }

            bufferedWriter.flush();
            bufferedWriter.close();

        } catch (Exception e) {
            System.out.println("Something went wrong...");
            e.printStackTrace();
        }

        for (Field field : fields) {
            field.setAccessible(false);
        }
    }

    // set a field
    private void setField (Object object, Field field, String text) throws Exception {
        // check if the given text is valid (not empty)
        if (text.isEmpty()) {
            return;
        }

        Object value = null;
        if (String.class.equals(field.getType())) {
            value = text;
        }

        else {
            String name = Character.toUpperCase(field.getType().getSimpleName().charAt(0)) +
                    field.getType().getSimpleName().substring(1);
            if (text.equals("false") || text.equals("true")) {
                name = "Boolean";
            }

            if (!name.equals("List") && !name.equals("Play") && !name.equals("Ticket") && !name.equals("Hall")) {
                Class<?> type = Class.forName("java.lang." + name + (int.class.equals(field.getType()) && !name.equals("Boolean") ? "eger" : ""));
                Method method = type.getDeclaredMethod("parse" + name, String.class);
                value = method.invoke(null, text);
            }
        }

        field.set(object, value);
    }

    // read data from a csv
    @SuppressWarnings("unchecked")
    public <T> List<T> readCSV(String path, Class<T> type) throws Exception {
        List<T> records = new ArrayList<>();
        Constructor<T> constructor = type.getDeclaredConstructor();
        Field[] fields = type.getDeclaredFields();
        constructor.setAccessible(true);
        for (Field field : fields) {
            field.setAccessible(true);
        }

        for (String line : Files.readAllLines((Paths.get(path)))) {
            T record = constructor.newInstance();
            String[] values = line.split(",");

            for (int i = 0; i < values.length; i++) {
                if (!fields[i].getType().toString().contains("theatre") || !fields[i].getType().toString().contains("interface")) {
                    setField(record, fields[i], values[i]);
                }
            }
        records.add(record);
        }

        constructor.setAccessible(false);
        for (Field field : fields) {
            field.setAccessible(false);
        }

    return records;
    }

}
