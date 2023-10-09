package helper;

import java.io.*;
import java.net.*;
import java.util.Date;

/**
 * Класс предназначен для удобной работы с файлами
 * Все методы статические
 */
public class FileHelper {
    private FileHelper() {
    }


    /**
     * Метод для конвертации пути файла в URL
     * Принимает на вход путь до файлв
     *
     * @param - путь до желаемого файла
     *
     * @return - URL
     *
     * @throws MalformedURLException
     */
    public static URL convertToURL(String path) throws MalformedURLException {
        File file = new File(path);
        try {
            return file.toURL();
        } catch (MalformedURLException mue) {
            throw new MalformedURLException("Невозможно преобразование");
        }

    }

    /**
     * Метод для конвертации пути файла в URL
     * Принимает на вход объект класса File
     *
     * @param - объект класса File
     *
     * @return - URL
     *
     * @throws MalformedURLException
     */
    public static URL convertToURL(File file) throws MalformedURLException {
        try {
            return file.toURL();
        } catch (MalformedURLException mue) {
            throw new MalformedURLException("Невозможно преобразование");
        }

    }

    /**
     * Метод для создания файла
     * Файл создается в корне проекта - если указать только имя файла
     * При необходимсоти помещать файл по указанному пути нужно указывать полный отностительный путь для файла как аргумент метода
     *
     * @param fileName - желаемое имя файла
     *
     * @throws IOException
     */
    public static void createFile(String fileName) throws IOException {
        try {
            File file = new File(fileName);

            if (file.createNewFile()) {
                System.out.println("Файл успешно создан");
            } else {
                System.out.println("Файл с таким именем уже существует");
            }
        } catch (IOException ioe) {
            throw new IOException("Невозможно создать файл");
        }
    }


    /**
     * Метод для удаления файлов
     * При необходимсоти удалить файл не в корневой папке - указыать отностительный полный путьдо файла
     *
     * @param fileName
     */
    public static void deleteFile(String fileName) throws IOException {
        deleteFile(new File(fileName));
    }

    /**
     * Метод для удаления файлов
     * При необходимсоти удалить файл не в корневой папке - указыать объект типа File
     *
     * @param file
     */
    public static void deleteFile(File file) {
        if (file.delete()) {
            System.out.println("Файл " + file.getName() + " успешно удален");
        } else {
            System.out.println("Файл " + file.getName() + " удалить не удалось.\nПроверьте корректность введеного имени файла и пути до него либо заблокированность файла.");
        }
    }

    /**
     * Метод для получения информации о файле
     *
     * @param fileName - имя файла
     */
    public static void showInfoAboutFile(String fileName) {
        File file = new File(fileName);
        long length = file.length();
        Date timeLastChange = new Date(file.lastModified());
        String absPath = file.getAbsoluteFile().getPath();

        if (file.exists()) {
            System.out.println("Длина файла в байтах : " + length);
            System.out.println("Время последнего изменения файла : " + timeLastChange);
            System.out.println("Абсолютный путь до файла : " + absPath);
        } else {
            System.out.println("Файл не обнаружен. Проверьте правильность полного имени файла");
        }
    }

    /**
     * Метод для переименовывания или перемещения файла в другую деректорию
     *
     * @param oldName - текущее имя файла
     * @param newName - желаемое имя файла
     */
    public static void renameFile(String oldName, String newName) {
        boolean isRenamed = new File(oldName).renameTo(new File(newName));

        if (isRenamed) {
            System.out.println("Файл " + oldName + " успешно переименован в " + newName + ".");
        } else {
            System.out.println("Переименовать или переместить файл не удалось");
        }
    }

    /**
     * Метод для открытия файлов во внешних приложениях
     * ВАЖНО
     * Указывать расширение и полный путь до файла
     *
     * @param fileName
     */
    //todo - не работает - починить!
    public static void openFile(String programName, String fileName) throws IOException {
        ProcessBuilder builder = new ProcessBuilder(programName, fileName);
        builder.start();
    }


    /**
     * Метод рекурсивного удаления директории
     * Проверяет наличие файлов или папок внутри папки у удаляет их рекурсивно
     *
     * @param dirPath
     */
    public static void deleteDir(String dirPath) throws IOException {
        //Удаляемая папка
        File dir = new File(dirPath);
        //Список удаляемых папок и файлов
        String[] dirList = dir.list();
        if (dirList == null) {
            System.out.println("Директории не существует.");
            return;
        }
        for (int i = 0; i < dirList.length; i++) {
            File file = new File(dirList[i]);
            //Рекурсивно пробегаемся по каталогу
            if (file.isDirectory()) {
                deleteDir(file.getPath());
                continue;
            }
            deleteFile(file);
        }
        if (dir.list() == null) {
            System.out.println("Директория успешно удалена.");
        } else {
            System.out.println("Директорию удалить не удалось.");
        }

    }


}
