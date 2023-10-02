import helper.FileHelper;
import org.junit.jupiter.api.*;

import java.io.File;
import java.io.IOException;
import java.net.URL;

@DisplayName("Проверка корректноси работы FileHelper")
public class FileHelperTest {

    @Test
    @DisplayName("Проверка создания URL через путь до файла")
    public void createULRThroughPathTest() throws IOException {
        URL url = FileHelper.convertToURL("src/test/java/test.txt");
        Assertions.assertEquals("file:/C:/Users/Пользователь/IdeaProjects/FileHelper/src/test/java/test.txt", url.toString());
    }

    @Test
    @DisplayName("Проверка создания URL через объект типа File")
    public void createULRThroughFileObjTest() throws IOException {
        URL url = FileHelper.convertToURL(new File("src/test/java/test.txt"));
        Assertions.assertEquals("file:/C:/Users/Пользователь/IdeaProjects/FileHelper/src/test/java/test.txt", url.toString());
    }

    @Test
    @DisplayName("Проверка создания файла")
    public void createFileTest() throws IOException {
        Assertions.assertDoesNotThrow( () -> {
            FileHelper.createFile("src/test/java/test.txt");
        });
    }

    @Test
    @DisplayName("Проверка удаления файла")
    public void deleteFileTest() {
        Assertions.assertDoesNotThrow( () -> {
            FileHelper.createFile("src/test/java/test.txt");
            FileHelper.deleteFile("src/test/java/test.txt");
        });
    }

    @Test
    @DisplayName("Проверка получения информации о файле")
    public void showInfoAboutFileTest() {
        Assertions.assertDoesNotThrow( () -> {
            FileHelper.createFile("src/test/java/test.txt");
            FileHelper.showInfoAboutFile("src/test/java/test.txt");
            FileHelper.deleteFile("src/test/java/test.txt");
        });
    }

    @Test
    @DisplayName("Проверка переименования файла")
    public void renameFileTest() {
        Assertions.assertDoesNotThrow( () -> {
            FileHelper.createFile("src/test/java/test.txt");
            FileHelper.renameFile("src/test/java/test.txt", "src/test/java/tset.txt");
            FileHelper.deleteFile("src/test/java/tset.txt");
        });
    }

    @Test
    @DisplayName("Проверка перемещнеия файла")
    public void movedFileTest() {
        Assertions.assertDoesNotThrow( () -> {
            FileHelper.createFile("src/test/java/test.txt");
            FileHelper.renameFile("src/test/java/test.txt", "test.txt");
            FileHelper.deleteFile("test.txt");
        });
    }

//    @Test
//    @DisplayName("Проверка открытия файла во внешнем приложении")
//    public void openFileTest() throws IOException {
//        FileHelper.createFile("src/test/java/test.html");
//    }
    @Test
    @DisplayName("Проверка открытия файла во внешнем приложении")
    public void openFileTest() throws IOException {
        Assertions.assertDoesNotThrow( () -> {
            FileHelper.openFile("\"src/test/java/test.html\"");
        });
    }

    @Test
    @DisplayName("Проверка удаления директории")
    public void deleteDirTest() throws IOException {
        Assertions.assertDoesNotThrow( () -> {
            FileHelper.deleteDir("src/test/test");
        });
    }






}
