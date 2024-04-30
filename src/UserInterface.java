import util.FileUtil;

import java.io.IOException;

public class UserInterface {
    private static final String FILE_DIRECTORY = "src/data/movies.json";
    private static final FileUtil fileUtil;

    static {
        FileUtil tempFileUtil = null;
        try {
            tempFileUtil = new FileUtil(FILE_DIRECTORY);
        } catch (IOException e) {
            e.printStackTrace();
        }
        fileUtil = tempFileUtil;
    }


    public void start() {
        java.util.Scanner scanner = new java.util.Scanner(System.in);
        System.out.println("Добро пожаловать в базу данных фильмов!");
        System.out.println("===========================================");

        while (true) {
            System.out.println("\nПожалуйста, выберите вариант:");
            System.out.println("1. Распечатать все фильмы");
            System.out.println("2. Вывести всех актеров и их роли");
            System.out.println("3. Поиск фильмов по названию");
            System.out.println("4. Поиск фильмов по актеру");
            System.out.println("5. Поиск фильмов по годам");
            System.out.println("6. Выход");

            int choice = scanner.nextInt();
            switch (choice) {
                case 1:
                    fileUtil.printAllMovies();
                    break;
                case 2:
                    fileUtil.printAllActorsAndRoles();
                    break;
                case 3:
                    System.out.print("Введите название фильма для поиска: ");
                    scanner.nextLine();
                    String title = scanner.nextLine();
                    fileUtil.searchMoviesByTitle(title);
                    break;
                case 4:
                    System.out.print("Введите имя актера для поиска: ");
                    scanner.nextLine();
                    String actorName = scanner.nextLine();
                    fileUtil.searchMoviesByActor(actorName);
                    break;
                case 5:
                    System.out.print("Введите год выпуска фильма: ");
                    int year = scanner.nextInt();
                    fileUtil.searchMoviesByYear(year);
                    break;
                case 6:
                    System.out.println("Подождите...");
                    return;
                default:
                    System.out.println("Неправильный ввод введите число от 1 до 6");
            }
        }

    }
}
