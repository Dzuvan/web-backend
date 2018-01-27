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
import com.dzuvan.forum.model.Subforum;
import com.dzuvan.forum.model.UserModel;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

/**
 *
 * @author dzuvan
 */
public class SubforumServiceImpl implements SubforumService {

    private static final String FILENAME = "subforums.dat";
    private static final String DIRECTORY = System.getProperty("user.dir")+ File.separator + "data" + File.separator;

    private static SubforumServiceImpl instance = null;
    private ArrayList<Subforum> subforums;

    private SubforumServiceImpl() {
    }

    /**
     *
     * @return Singleton
     */
    public static SubforumServiceImpl getInstance() {
        if (instance == null) {
            instance = new SubforumServiceImpl();
            instance.initialiseSubforums();
        }
        return instance;
    }

    /**
     *
     * @param subforums
     */
    private void saveSubforumList(ArrayList<Subforum> subforums) {
        try {

            File file = new File(DIRECTORY, FILENAME);
            FileOutputStream fos = new FileOutputStream(file);

            try (ObjectOutputStream oos = new ObjectOutputStream(fos)) {
                oos.writeObject(subforums);
            }
        } catch (FileNotFoundException ex) {
        } catch (IOException ex) {
        }
    }

    @Override
    public void delete(Subforum subforum) {
        Subforum foundSubforum = subforums.stream().filter(s -> s.getName().equals(subforum.getName()))
                .findFirst()
                .orElse(null);
        if (foundSubforum != null) {
            subforums.remove(foundSubforum);
            for (Subforum s : subforums) {
                if (s.getId() > foundSubforum.getId()) {
                    s.setId(Subforum.nextId.decrementAndGet());
                }
            }
            Subforum.nextId.decrementAndGet();
            if (subforums.isEmpty()) {
                Subforum.nextId.getAndSet(0);
            }
            saveSubforumList(subforums);
        }
    }

    @Override
    public Subforum getByString(String name) {
        Subforum foundSubforum = subforums.stream().filter(s -> s.getName().equals(name))
                .findFirst()
                .orElse(null);
        return foundSubforum;
    }

    @Override
    public Subforum getById(long id) {
        Subforum foundSubforum;
        foundSubforum = subforums.stream().filter(s -> s.getId() == id)
                .findAny()
                .orElse(null);
        return foundSubforum;
    }

    @Override
    public void edit(Subforum subforum, long id) {
        Subforum s = getById(id);
        if (s.getId() == subforum.getId()) {
            int index = subforums.indexOf(s);
            subforums.set(index, subforum);
            saveSubforumList(subforums);
        }
    }

    @Override
    public void addOne(Subforum subforum) {
        subforums.add(subforum);
    }

    @Override
    public ArrayList<Subforum> getAll() {
        if (subforums.isEmpty()) {
            initialiseSubforums();
        }
        return subforums;
    }

    private void initialiseSubforums() {
        subforums = new ArrayList<>();

        UserModel moderator = UserServiceImpl.getInstance().getByRole(Role.MODERATOR);
        subforums.add(new Subforum("forum1", "description1", "samo jako", null, moderator));
        subforums.add(new Subforum("dva", "mlogo kul", "budite dobri", null, moderator));
        subforums.add(new Subforum("tri", "jako opasno", "dobri", null, moderator));

        try {
            File file = new File(DIRECTORY, FILENAME);

            if (!file.exists()) {
                saveSubforumList(subforums);
            } else {
                FileInputStream fis = new FileInputStream(file);
                try (ObjectInputStream ois = new ObjectInputStream(fis)) {
                    subforums = (ArrayList<Subforum>) ois.readObject();
                }
            }
        } catch (IOException | ClassNotFoundException ex) {
        }
    }
}
