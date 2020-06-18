package service;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import exception.*;
import model.Movie;
import model.Request;
import model.User;

import java.io.IOException;
import java.nio.file.Paths;
import java.util.List;
import java.util.Objects;

import static controller.UploaderController.getSelectedMovie;


public class MovieService {

    private static List<Movie> movies;
    private static List<Request> requests;
    private static User connectedUser;

    public static List<Movie> getMovies() {
        return movies;
    }

    public static void loadMoviesFromFile() throws IOException {
        ObjectMapper objectMapper = new ObjectMapper();
        movies = objectMapper.readValue(Paths.get("src/sample.Main/java/sample/json/movies.json").toFile(), new TypeReference<List<Movie>>() {
        });
    }

    public static void loadRequestsFromFile() throws IOException {
        ObjectMapper objectMapper = new ObjectMapper();
        requests = objectMapper.readValue(Paths.get("src/sample.Main/java/sample/json/requests.json").toFile(), new TypeReference<List<Request>>() {
        });
    }

    public static void addMovie(String title, String url, String genre,String description, String uploader, String year) throws TitleFieldEmptyException, UrlFieldEmptyException, MovieAlreadyExistsException {
        checkTitleFieldIsNotEmpty(title);
        checkUrlFieldIsNotEmpty(url);
        checkMovieDoesNotAlreadyExist(title,null);
        movies.add(new Movie(uploader,title,year,genre,url,description));
        persistMovies();
    }

    public static void addMovieRequest(String title, String viewer, String year) throws TitleFieldEmptyException, MovieAlreadyExistsException {
        checkTitleFieldIsNotEmpty(title);
        checkMovieNotAlreadyExistOrRequested(title);
        requests.add(new Request(viewer,title,year));
        persistRequests();
    }


    public static void editMovie(Movie movie, String description, String title, String url, String year, String genre) throws TitleFieldEmptyException, UrlFieldEmptyException, MovieAlreadyExistsException {
        checkTitleFieldIsNotEmpty(title);
        checkUrlFieldIsNotEmpty(url);
        checkMovieDoesNotAlreadyExist(title,title);
        movie.setDescription(description);
        movie.setTitle(title);
        movie.setUrl(url);
        movie.setLaunchyear(year);
        movie.setGenre(genre);
        persistMovies();
    }
    public static void removeAllMovies(final String username)
    {
        while(movies.remove(new Movie(username,"","","","", "")));
        persistMovies();
    }

    private static void checkMovieDoesNotAlreadyExist(String title, String oldTitle) throws MovieAlreadyExistsException{ //used for movies
        for (Movie m : movies) {
            if (Objects.equals(title, m.getTitle()))
                if(!title.equals(oldTitle))
                    throw new MovieAlreadyExistsException();
        }
    }
    private static void checkMovieNotAlreadyExistOrRequested(String title) throws MovieAlreadyExistsException{ //used for requests
        for (Movie m : movies) {
            if (Objects.equals(title, m.getTitle()))
                    throw new MovieAlreadyExistsException();
        }
        for (Request r : requests) {
            if (Objects.equals(title, r.getTitle()))
                throw new MovieAlreadyExistsException();
        }
    }

    private static void checkTitleFieldIsNotEmpty(String title) throws TitleFieldEmptyException{
        if(title.equals(""))
            throw new TitleFieldEmptyException();
    }

    private static void checkUrlFieldIsNotEmpty(String url) throws UrlFieldEmptyException{
        if(url.equals(""))
            throw new UrlFieldEmptyException();
    }

    private static void persistMovies() {
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            objectMapper.writerWithDefaultPrettyPrinter().writeValue(Paths.get("src/sample.Main/java/sample/json/movies.json").toFile(), movies);
        } catch (IOException e) {
            throw new CouldNotWriteMoviesException();
        }
    }
    private static void persistRequests() {
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            objectMapper.writerWithDefaultPrettyPrinter().writeValue(Paths.get("src/sample.Main/java/sample/json/requests.json").toFile(), requests);
        } catch (IOException e) {
            throw new CouldNotWriteMoviesException();
        }
    }

}