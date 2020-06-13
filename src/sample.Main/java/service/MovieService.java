package service;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import exception.*;
import model.Movie;
import model.User;

import java.io.IOException;
import java.nio.file.Paths;
import java.util.List;


public class MovieService {

    private static List<Movie> movies;
    private static User connectedUser;



    public static void loadMoviesFromFile() throws IOException {
        ObjectMapper objectMapper = new ObjectMapper();
        movies = objectMapper.readValue(Paths.get("src/sample.Main/java/sample/json/movies.json").toFile(), new TypeReference<List<Movie>>() {
        });
    }

    //public static void addMovie(String username, String password, String email,String role) throws UsernameAlreadyExistsException,UsernameFieldEmptyException,PasswordFieldEmptyException{
        /*checkUserFieldIsNotEmpty(username);
        checkPasswordFieldIsNotEmpty(password);
        checkUserDoesNotAlreadyExist(username);
        users.add(new User(username, encodePassword(username, password), email,role));
        persistUsers();*/  // to be implemented
    //}

    public static void removeAllMovies(final String username)
    {
        while(movies.remove(new Movie(username,"","","","", "")));
        persistMovies();
    }

    /*private static void checkMovieDoesNotAlreadyExist(String title) throws MovieAlreadyExistsException{
        for (Movie m : movies) {
            if (Objects.equals(title, m.getTitle()))
                throw new MovieAlreadyExistsException();
        }
    }
    private static void checkTitleFieldIsNotEmpty(String title) throws TitleFieldEmptyException{
        if(title.equals(""))
            throw new TitleFieldEmptyException();
    }
    */
    private static void persistMovies() {
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            objectMapper.writerWithDefaultPrettyPrinter().writeValue(Paths.get("src/sample.Main/java/sample/json/movies.json").toFile(), movies);
        } catch (IOException e) {
            throw new CouldNotWriteMoviesException();
        }
    }

}