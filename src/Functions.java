import java.io.*;
import java.util.Arrays;
import java.util.Objects;
import java.util.Scanner;

public class Functions {

    /**
     * Проверяет возможность чтения данных из введенного файла
     *
     * @param file Имя файла в текущей директории или полный путь к файлу
     * @throws IOException Если файла нет вообще, не является файлом, нет доступа на чтение
     */
    public static void file_in_right(File file) throws IOException{
        if (!file.exists()){throw new IOException ("ВВОД: Ошибка! Файла не существует");}
        if (!file.isFile()){throw new IOException ("ВВОД: Ошибка! Не является файлом");}
        if (!file.canRead()){throw new IOException ("ВВОД: Ошибка! Нет доступа на чтение файла");}
    }

    /**
     * Проверяет возможность записи данных в введенный файл
     *
     * @param file Имя файла в текущей директории или полный путь к файлу
     * @throws IOException Если файла нет вообще, не является файлом, нет доступа на запись
     */
    public static void file_out_right(File file) throws IOException{
        if (!file.exists()){throw new IOException ("ВЫВОД: Ошибка! Файла не существует");}
        if (!file.isFile()){throw new IOException ("ВЫВОД: Ошибка! Не является файлом");}
        if (!file.canWrite()){throw new IOException ("ВЫВОД: Ошибка! Нет доступа на запись файла");}
        //необязательная проверка, но я подумала, что стоит её добавить
        // (потому что удаляются данные пользователя)
        BufferedReader br = new BufferedReader(new FileReader(file));
        if (br.readLine() != null) {
            System.out.println("Внимание! При записи все предыдущие данные в файле будут удалены.\nДля подтверждения напишите 'ДА' капсом и без кавычек");
            Scanner sc = new Scanner(System.in);
            String answer = sc.nextLine();
            if (!Objects.equals(answer, "ДА")){throw new IOException("ВЫВОД: Ошибка! Файл непустой");}
        }
    }

    /**
     * Считает количество использования в файле разных символов английского алфавита
     * и записывает результаты в указанный файл
     *
     * @param file_in Имя файла/путь к файлу, в котором считаются символы
     * @param file_out Имя файла/путь к файлу, в который записывается результат
     * @throws IOException Если возникнет ошибка при считывании или записи
     */
    public static void frequency_count(File file_in, File file_out) throws IOException {
        //проверка правильности введенных файлов
        file_in_right(file_in);
        file_out_right(file_out);

        //инициализация массива пар (буква:количество её появлений)
        Pair[] arr = new Pair[26];
        for (int i = 65; i <= 90; i++){
            arr[i - 65] = new Pair((char) i);
        }

        //чтение файла и подсчет использований букв
        BufferedReader BR = null;
        try {
            BR = new BufferedReader(new FileReader(file_in));
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
        int symbol;
        while (true){
            try {
                if ((symbol = BR.read()) == -1) break;
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            if ((symbol >= 65) && (symbol <= 90)){arr[symbol - 65].addFrequency();}
            if ((symbol >= 97) && (symbol <= 122)){arr[symbol - 97].addFrequency();}
        }

        //запись результатов в файл
        PrintWriter writer = null;
        try {
            writer = new PrintWriter(new FileWriter(file_out));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        writer.write(Arrays.toString(arr));
        writer.close();
    }
}
