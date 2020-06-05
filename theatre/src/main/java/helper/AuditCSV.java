package helper;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.sql.Timestamp;

public class AuditCSV {
    private static AuditCSV auditCSV;

    private AuditCSV() {

    }

    public static AuditCSV getInstance() {
        if (auditCSV == null) {
            auditCSV = new AuditCSV();
        }

        return auditCSV;
    }

    // write data in a csv: action and timestamp
    public void write (String action, String threadName) {
        try {
            Path path =  Paths.get("/data").toAbsolutePath();
            String filePath = "C:\\Users\\Mihaela\\Desktop\\paoProjectFinal\\theatre\\src\\main\\java\\data";

            File file = new File(filePath+ "/audit.csv");
            FileWriter fileWriter = new FileWriter(file, true);
            BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);

            Timestamp timestamp = new Timestamp(System.currentTimeMillis());

            bufferedWriter.write(action + ',' + timestamp.toString() + "," + threadName + "\n");
            bufferedWriter.flush();
            bufferedWriter.close();

        } catch (Exception e) {
            System.out.println("Something went wrong...");
            e.printStackTrace();
        }
    }
}
