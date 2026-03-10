package project;


import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

public class Logger {
    public static String filePath = "log.txt";

    private static int accessCount = 0;
    private static int pageFaultCount = 0;

    public static void logAccess() {
        accessCount++;
    }

    public static void logPageFault() {
        pageFaultCount++;
    }

    public static void reset() {
        accessCount = 0;
        pageFaultCount = 0;
    }

    public static void writeToFile(int workingSetSize) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filePath))) {
            writer.write("Розмір робочого набору: " + workingSetSize + "\n");
            writer.write("Всього звернень до пам'яті: " + accessCount + "\n");
            writer.write("Всього сторінкових промахів: " + pageFaultCount + "\n");
            writer.write("Відсоток промахів: " + (pageFaultCount * 100 / accessCount) + "%");
        } catch (IOException ex) {
            throw new RuntimeException(ex);
        }
    }
}
