import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

public class FileOperations {
    /**
     * Запись файлов со списками победителей и выданных призов
     */
    protected static void createFile(List<String> dataList, String nameFile) {
        try (FileWriter writer = new FileWriter(nameFile)) {
            for (String text : dataList) {
                writer.write(text + "\n");
            }
        } catch (IOException e) {
            System.out.println("Ошибка с файлом" + e.getMessage());
        }
    }

    /**
     * Чтение файла и внесение в список
     */
    protected static void readerFileList(List<String> readerDatataList, String fileName) {
        try (BufferedReader file = new BufferedReader(new FileReader(fileName))) {
            String text;
            while ((text = file.readLine()) != null) {
                readerDatataList.add(text);
            }
        } catch (IOException e) {
            System.out.println("Ошибка с файлом " + e.getMessage());
        }
    }

    /**
     * Удаление файла с выданными призами
     */
    protected static void fileDelete() {
        try {
            Files.delete(Path.of(WinToys.prizeAwardFileName));
        } catch (IOException e) {
            System.out.println("Файл не существует");
        }
    }

    /**
     * Метод проверки наличия файла
     */
    protected static boolean checkingForFileAvailability(String fileName) {
        File f = new File(fileName);
        return f.exists();
    }
}
