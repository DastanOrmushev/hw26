import util.FileUtil;

import java.io.IOException;

public class Main {
    public static void main(String[] args) {
        String fileDirectory = "src/data/movies.json";
        try {
            FileUtil fileRead = new FileUtil(fileDirectory);
            fileRead.printAllMovies();
            System.out.println("==============================================================");
            //  fileRead.sortMoviesByDirector();
            System.out.println("==============================================================");
            fileRead.printAllActorsAndRoles();
        } catch (IOException io) {
            io.printStackTrace();
        }
    }
}