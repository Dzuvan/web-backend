/*
* Copyright (C) 2017 dzuvan
*
* This program is free software: you can redistribute it and/or modify
* it under the terms of the GNU General Public License as published by
* the Free Software Foundation, either version 3 of the License, or
* (at your option) any later version.
*
* This program is distributed in the hope that it will be useful,
* but WITHOUT ANY WARRANTY; without even the implied warranty of
* MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
* GNU General Public License for more details.
*
* You should have received a copy of the GNU General Public License
* along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
package com.dzuvan.forum.service;

import com.dzuvan.forum.model.Role;
import com.dzuvan.forum.model.UserModel;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.time.LocalDate;
import java.util.ArrayList;
import javax.ws.rs.core.Response;

/**
 *
 * @author dzuvan
 */
public class UserServiceImpl implements UserService {

    private static final String FILENAME = "users.dat";
    private static final String DIRECTORY = System.getProperty("user.dir");

    private static UserServiceImpl instance = null;
    private ArrayList<UserModel> users;

    private UserServiceImpl() {
    }

    /**
     * Singleton
     *
     * @return instance
     */
    public static UserServiceImpl getInstance() {
        if (instance == null) {
            instance = new UserServiceImpl();
            instance.initialiseUsers();
        }
        return instance;
    }

    /**
     *
     * @param users
     */
    private void saveUserList(ArrayList<UserModel> users) {
        try {
            File file = new File(DIRECTORY, FILENAME);
            FileOutputStream fos = new FileOutputStream(file);
            try (ObjectOutputStream oos = new ObjectOutputStream(fos)) {
                oos.writeObject(users);
            }
        } catch (FileNotFoundException ex) {
        } catch (IOException ex) {
        }
    }

    @Override
    public void delete(UserModel user) {
        UserModel foundUser = users.stream().filter(u -> u.getUsername().equals(user.getUsername()))
                .findFirst()
                .orElse(null);

        if (foundUser != null) {
            users.remove(foundUser);
            users.stream().filter((u) -> (u.getId() > foundUser.getId())).forEachOrdered((u) -> {
                int i = u.getId();
                u.setId(i--);
            });
            saveUserList(users);
        } else {
            Response.noContent().build();
        }
    }

    @Override
    public UserModel getByString(String s) {
        UserModel foundUser;
        foundUser = users.stream().filter(u -> u.getUsername().equals(s))
                .findFirst()
                .orElse(null);
        return (foundUser != null) ? foundUser : null;
    }

    public UserModel getByRole(Role role) {
        UserModel foundUser;
        foundUser = users.stream().filter(r -> r.getRole().equals(role))
                .findFirst()
                .orElse(null);
        return (foundUser != null) ? foundUser : null;
    }

    @Override
    public UserModel getById(Integer id) {
        UserModel foundUser;
        foundUser = users.stream().filter(u -> (u.getId() == id))
                .findAny()
                .orElse(null);
        return (foundUser != null) ? foundUser : null;
    }

    @Override
    public void edit(UserModel user, Integer id) {
        UserModel foundUser = users.stream().filter(u -> (u.getId() == id))
                .findAny()
                .orElse(null);
        if (foundUser != null) {
            users.set(id, user);
            saveUserList(users);
        }
    }

    @Override
    public boolean addOne(UserModel user) {
        UserModel foundUser = users.stream().filter(u -> u.getUsername().equals(user.getUsername()))
                .findAny()
                .orElse(null);

        if (foundUser == null) {
            users.add(user);
            saveUserList(users);
            return true;
        } else {
            return false;
        }
    }

    @Override
    public ArrayList<UserModel> getAll() {
        return users;
    }

    private void initialiseUsers() {
        users = new ArrayList<>();

        users.add(new UserModel("admin", "admin", "marko", "markovic",
                Role.ADMINISTRATOR, "555-333", "marko@markovic.com", LocalDate.of(2017, 8, 10)));

        users.add(new UserModel("mode", "mode", "milan", "milankovic",
                Role.MODERATOR, "666-333", "milan@gmail.com", LocalDate.of(2017, 8, 18)));

        users.add(new UserModel("first", "users", "jovan", "jovannovci",
                Role.USER, "444-333", "jovan@gmail.com", LocalDate.of(2017, 9, 18)));

        try {
            File file = new File(DIRECTORY, FILENAME);

            if (!file.exists()) {
                saveUserList(users);
            } else {
                FileInputStream fis = new FileInputStream(file);
                try (ObjectInputStream ois = new ObjectInputStream(fis)) {
                    users = (ArrayList<UserModel>) ois.readObject();
                }
            }
        } catch (IOException | ClassNotFoundException ex) {
        }
    }
}
