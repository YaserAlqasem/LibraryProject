package controller;

import business.Author;
import business.Role;
import business.User;
import dataaccess.DataAccess;
import dataaccess.DataAccessFacade;

import java.util.List;

public class SystemController {

    private static SystemController instance;
    private DataAccess dataAccess = new DataAccessFacade();
    private List<Author> authorsList;

    private SystemController() {
    }

    public static SystemController getInstance() {
        if (instance == null) instance = new SystemController();
        return instance;
    }

    public User login(String id, String password) {
        User user = dataAccess.verifyUser(id, password);
        if (user == null)
            return null;

        return user;
    }
}
