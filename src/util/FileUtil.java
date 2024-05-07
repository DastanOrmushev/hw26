package util;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import model.Movie;

import java.io.FileReader;
import java.io.IOException;
import java.lang.reflect.Type;
import java.util.*;

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
        movies.forEach(movie -> {
            if (movie.getName().toLowerCase().contains(title.toLowerCase())) {
                System.out.println(movie);
            }
        });
    }

    public void sortMoviesByYear() {
        movies.sort(new Comparator<Movie>() {
            @Override
            public int compare(Movie m1, Movie m2) {
                return Integer.compare(m1.getYear(), m2.getYear());
            }
        });
        printAllMovies();
    }

    public void sortMoviesByYearDescending() {
        movies.sort(new Comparator<Movie>() {
            @Override
            public int compare(Movie m1, Movie m2) {
                return Integer.compare(m2.getYear(), m1.getYear());
            }
        });
        printAllMovies();
    }

    public void sortMoviesByName() {
        movies.sort(new Comparator<Movie>() {
            @Override
            public int compare(Movie m1, Movie m2) {
                return m1.getName().compareToIgnoreCase(m2.getName());
            }
        });
        printAllMovies();
    }

    public void sortMoviesByNameDescending() {
        movies.sort(new Comparator<Movie>() {
            @Override
            public int compare(Movie m1, Movie m2) {
                return m2.getName().compareToIgnoreCase(m1.getName());
            }
        });
        printAllMovies();
    }

    public void sortMoviesByDirector() {
        movies.sort(Comparator.comparing(movie -> movie.getDirector().getFullName().toLowerCase()));
        printAllMovies();
    }

    public void sortMoviesByDirectorDescending() {
        movies.sort(Comparator.comparing((Movie movie) -> movie.getDirector().getFullName().toLowerCase()).reversed());
        printAllMovies();
    }

    public void searchMoviesByActor(String actorName) {
        movies.forEach(movie -> {
            movie.getCast().forEach(cast -> {
                if (cast.getFullName().equalsIgnoreCase(actorName)) {
                    System.out.println(movie);
                }
            });
        });
    }

    public void searchMoviesByYear(int year) {
        movies.forEach(movie -> {
            if (movie.getYear() == year) {
                System.out.println(movie);
            }
        });
    }

    public void searchMoviesAndRolesByActor(String actorName) {
        movies.forEach(movie -> {
            movie.getCast().forEach(cast -> {
                if (cast.getFullName().equalsIgnoreCase(actorName)) {
                    System.out.println(movie.getName() + " - " + cast.getRole());
                }
            });
        });
    }

    public void printAllActorsAndRoles() {
        Map<String, Set<String>> actorsAndRoles = new TreeMap<>();
        movies.forEach(movie -> {
            movie.getCast().forEach(cast -> {
                String fullName = cast.getFullName();
                String role = movie.getName() + " - " + cast.getRole();
                Set<String> roles = actorsAndRoles.computeIfAbsent(fullName, k -> new TreeSet<>());
                roles.add(role);
            });
        });

        actorsAndRoles.forEach((actor, roles) -> {
            StringBuilder roleString = new StringBuilder();
            roles.forEach(role -> roleString.append(role).append(", "));
            roleString.delete(roleString.length() - 2, roleString.length());
            System.out.println(actor + ": " + roleString);
        });
    }
}
