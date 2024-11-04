import java.io.IOException;
import java.util.Scanner;
import java.io.File;

public class Main {
    public static void main(String[] args) {
        //Ввод данных с клавиатуры
        Scanner sc = new Scanner(System.in);
        System.out.println("ВВОД\nВведите имя файла (если он находится в папке с программой)\nили\nВведите полный путь к файлу (если он находится вне)");
        File file_in = new File(sc.nextLine());
        System.out.println("ВЫВОД\nВведите имя файла (если он находится в папке с программой)\nили\nВведите полный путь к файлу (если он находится вне)");
        File file_out = new File(sc.nextLine());

        //Выполнение функции
        try {
            Functions.frequency_count(file_in, file_out);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
