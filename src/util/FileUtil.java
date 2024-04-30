package util;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import model.Movie;

import java.io.FileReader;
import java.io.IOException;
import java.lang.reflect.Type;
import java.util.Comparator;
import java.util.List;

public class FileUtil {
    private List<Movie> movies;

    public FileUtil(String filePath) throws IOException {
        Gson gson = new Gson();
        Type movieListType = new TypeToken<List<Movie>>() {
        }.getType();
        movies = gson.fromJson(new FileReader(filePath), movieListType);
    }

    public void printAllMovies() {
        for (Movie movie : movies) {
            System.out.println(movie);
        }
    }

    public void searchMoviesByTitle(String title) {
        for (Movie movie : movies) {
            if (movie.getName().toLowerCase().contains(title.toLowerCase())) {
                System.out.println(movie);
            }
        }
    }

    public void sortMoviesByYear() {
        movies.sort(Comparator.comparingInt(Movie::getYear));
        printAllMovies();
    }

    public void sortMoviesByYearDescending() {
        movies.sort(Comparator.comparingInt(Movie::getYear).reversed());
        printAllMovies();
    }


    public void sortMoviesByName() {
        movies.sort(Comparator.comparing(Movie::getName, String.CASE_INSENSITIVE_ORDER));
        printAllMovies();
    }

    public void sortMoviesByNameDescending() {
        movies.sort(Comparator.comparing(Movie::getName, String.CASE_INSENSITIVE_ORDER).reversed());
        printAllMovies();
    }


    public void sortMoviesByDirector() {
        movies.sort(new Comparator<Movie>() {
            @Override
            public int compare(Movie m1, Movie m2) {
                return m1.getDirector().getFullName().compareToIgnoreCase(m2.getDirector().getFullName());
            }
        });

        printAllMovies();
    }

    public void sortMoviesByDirectorDescending() {
        movies.sort(new Comparator<Movie>() {
            @Override
            public int compare(Movie m1, Movie m2) {
                return m2.getDirector().getFullName().compareToIgnoreCase(m1.getDirector().getFullName());
            }
        });

        printAllMovies();
    }




}
