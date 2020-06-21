package service;

import javafx.fxml.FXML;
import model.User;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import exception.CouldNotWriteUsersException;
import exception.IncorrectPasswordException;
import exception.PasswordFieldEmptyException;
import exception.UserDoesNotExistException;
import exception.UsernameAlreadyExistsException;
import exception.UsernameFieldEmptyException;
import org.apache.commons.io.FileUtils;

import java.io.IOException;


import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import java.util.List;
import java.util.Objects;


public class UserService {

    private static Path USERS_PATH = FileSystemService.getPathToFile("config" ,"users.json");
    private static List<User> users;
    private static User connectedUser;

    public static List<User> getUsers() {
        return users;
    }

    public static User getConnectedUser() {
        return connectedUser;
    }
    public static void setConnectedUser(User connectedUser) {
        UserService.connectedUser = connectedUser;
    }

    public static User getUser(String username)
    {
        for(User u:users)
            if(u.getUsername().equals(username))
                return u;
        return null;
    }

    public static void loadUsersFromFile() throws IOException {
        if (!Files.exists(USERS_PATH)) {
            FileUtils.copyURLToFile(UserService.class.getClassLoader().getResource("json/users.json"), USERS_PATH.toFile());
        }
        ObjectMapper objectMapper = new ObjectMapper();
        users = objectMapper.readValue(USERS_PATH.toFile(), new TypeReference<List<User>>() {
        });
    }

    public static void addUser(String username, String password, String email,String role) throws UsernameAlreadyExistsException,UsernameFieldEmptyException,PasswordFieldEmptyException{
        checkUserFieldIsNotEmpty(username);
        checkPasswordFieldIsNotEmpty(password);
        checkUserDoesNotAlreadyExist(username);
        users.add(new User(username, encodePassword(username, password), email,role));
        persistUsers();
    }

    public static void removeUser(User u)
    {
        if(u.getRole().equals("uploader"))
            u.removeAllUploads();
        users.remove(u);
        persistUsers();
    }

    private static void checkUserDoesNotAlreadyExist(String username) throws UsernameAlreadyExistsException{
        for (User user : users) {
            if (Objects.equals(username, user.getUsername()))
                throw new UsernameAlreadyExistsException();
        }
    }
    private static void checkUserFieldIsNotEmpty(String username) throws UsernameFieldEmptyException{
        if(username.equals(""))
            throw new UsernameFieldEmptyException();
    }
    private static void checkPasswordFieldIsNotEmpty(String password) throws PasswordFieldEmptyException{
        if(password.equals(""))
            throw new PasswordFieldEmptyException();
    }

    private static void persistUsers() {
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            objectMapper.writerWithDefaultPrettyPrinter().writeValue(USERS_PATH.toFile(), users);
        } catch (IOException e) {
            throw new CouldNotWriteUsersException();
        }
    }

    public static String encodePassword(String salt, String password) {
        MessageDigest md = getMessageDigest();
        md.update(salt.getBytes(StandardCharsets.UTF_8));

        byte[] hashedPassword = md.digest(password.getBytes(StandardCharsets.UTF_8));

        return new String(hashedPassword, StandardCharsets.UTF_8)
                .replace("\"", "");
    }

    private static MessageDigest getMessageDigest() {
        MessageDigest md;
        try {
            md = MessageDigest.getInstance("SHA-512");
        } catch (NoSuchAlgorithmException e) {
            throw new IllegalStateException("SHA-512 does not exist!");
        }
        return md;
    }

    public static User checkUser(String username, String pass) throws UserDoesNotExistException, IncorrectPasswordException {

        for (User user : users) {
            if (Objects.equals(username, user.getUsername()))
            {
                if(Objects.equals(encodePassword(username,pass),user.getPassword()))

                    return user;
                else throw new IncorrectPasswordException();
            }

        }
        throw new UserDoesNotExistException();
    }

}