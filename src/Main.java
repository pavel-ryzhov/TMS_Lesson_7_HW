import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.Scanner;

/**
 * Допустим есть файл с номерами документов.
 * Номером документа является строка, состоящая из букв и цифр(без служебных символов).
 * Пусть этот файл содержит каждый номер документа с новой строки и в строке никакой другой информации, только номер документа.
 * Валидный номер документа должен иметь длину 15 символов и начинаться с последовательности docnum(далее любая последовательность букв/цифр) или kontract(далее любая последовательность букв/цифр).
 * Написать программу для чтения информации из входного файла - путь к входному файлу должен задаваться через консоль.
 * Программа должна проверять номера документов на валидность.
 * Валидные номера документов следует записать в один файл-отчет.
 * Невалидные номера документов следует записать в другой файл-отчет, но после номеров документов следует добавить информацию о том, почему этот документ невалиден.
 */
public class Main {
    public static void main(String[] args) {
        //C:\Users\Amigo\OneDrive\Рабочий стол\documentNumbers.txt
        System.out.print("Enter path to file: ");
        String path = new Scanner(System.in).nextLine();

        List<String> docnums;
        try {
            docnums = Files.readAllLines(Paths.get(path));
        } catch (IOException e) {
            System.out.println("Read error");
            return;
        }

        StringBuilder validNumbers = new StringBuilder();
        StringBuilder invalidNumbers = new StringBuilder();
        for (String str : docnums) {
            boolean correctLength = str.length() == 15;
            boolean correctStart = str.startsWith("docnum") || str.startsWith("kontract");

            if (correctLength && correctStart) {
                validNumbers.append(str + "\n");
            }else {
                invalidNumbers.append(str);
                if (!correctLength){
                    invalidNumbers.append(" wrong length");
                }
                if (!correctStart){
                    if (!correctLength) invalidNumbers.append(",");
                    invalidNumbers.append(" number doesn't start with \"docnum\" or \"kontract\"");
                }
                invalidNumbers.append("\n");
            }

        }

        try {
            Files.write(Paths.get("validNumbers.txt"), validNumbers.toString().getBytes());
            Files.write(Paths.get("invalidNumbers.txt"), invalidNumbers.toString().getBytes());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
