package util;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import model.Cast;
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

    public void searchMoviesByActor(String actorName) {
        List<Movie> moviesWithActor = new ArrayList<>();
        for (Movie movie : movies) {
            for (Cast cast : movie.getCast()) {
                if (cast.getFullName().equalsIgnoreCase(actorName)) {
                    moviesWithActor.add(movie);
                    break;
                }
            }
        }
        for (Movie movie : moviesWithActor) {
            System.out.println(movie);
        }
    }

    public void searchMoviesByYear(int year) {
        List<Movie> moviesByYear = new ArrayList<>();
        for (Movie movie : movies) {
            if (movie.getYear() == year) {
                moviesByYear.add(movie);
            }
        }
        for (Movie movie : moviesByYear) {
            System.out.println(movie);
        }
    }

    public void searchMoviesAndRolesByActor(String actorName) {
        for (Movie movie : movies) {
            for (Cast cast : movie.getCast()) {
                if (cast.getFullName().equalsIgnoreCase(actorName)) {
                    System.out.print(movie.getName() + " - " + cast.getRole());
                }
            }
        }
    }

    public void printAllActorsAndRoles() {
        Map<String, Set<String>> actorsAndRoles = new TreeMap<>();
        for (Movie movie : movies) {
            for (Cast cast : movie.getCast()) {
                String fullName = cast.getFullName();
                String role = movie.getName() + " - " + cast.getRole();
                Set<String> roles = actorsAndRoles.get(fullName);
                if (roles == null) {
                    roles = new TreeSet<>();
                    actorsAndRoles.put(fullName, roles);
                }
                roles.add(role);
            }
        }
        for (Map.Entry<String, Set<String>> entry : actorsAndRoles.entrySet()) {
            String actor = entry.getKey();
            Set<String> roles = entry.getValue();
            StringBuilder roleString = new StringBuilder();
            for (String role : roles) {
                roleString.append(role).append(", ");
            }
            roleString.delete(roleString.length() - 2, roleString.length());
            System.out.println(actor + ": " + roleString);
        }
    }
}
