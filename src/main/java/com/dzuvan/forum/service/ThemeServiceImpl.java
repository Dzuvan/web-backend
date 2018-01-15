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

import com.dzuvan.forum.model.Subforum;
import com.dzuvan.forum.model.Theme;
import com.dzuvan.forum.model.ThemeType;
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

/**
 *
 * @author dzuvan
 */
public class ThemeServiceImpl implements ThemeService {

    private static final String FILENAME = "themes.dat";
    private static final String DIRECTORY = System.getProperty("user.dir");

    private static ThemeServiceImpl instance = null;
    private ArrayList<Theme> themes;

    /**
     * Singleton
     *
     * @return instance
     */
    public static ThemeServiceImpl getInstance() {
        if (instance == null) {
            instance = new ThemeServiceImpl();
            instance.initialiseThemes();
        }
        return instance;
    }

    private ThemeServiceImpl() {
    }

    /**
     *
     * @param themes
     */
    private void saveThemeList(ArrayList<Theme> themes) {
        try {
            File file = new File(DIRECTORY, FILENAME);
            FileOutputStream fos = new FileOutputStream(file);

            try (ObjectOutputStream oos = new ObjectOutputStream(fos)) {
                oos.writeObject(themes);
            }
        } catch (FileNotFoundException ex) {
        } catch (IOException ex) {
        }
    }

    @Override
    public void delete(Theme o) {
        Theme foundTheme = themes.stream().filter(t -> t.getTitle().equals(o.getTitle()))
                .findAny()
                .orElse(null);
        if (foundTheme != null) {
            for (Theme s : themes) {
                if (s.getId() > foundTheme.getId()) {
                    //Theme.setNextId(Subforum.getNextId() - 1);
                    s.setId(s.getId() - 1);
                }
            }
            themes.remove(foundTheme);
            if (themes.isEmpty()) {
                Theme.setNextId(1);
            }
            saveThemeList(themes);
        }
    }

    @Override
    public Theme getByString(String s) {
        Theme foundTheme = themes.stream().filter(t -> t.getTitle().equals(s))
                .findAny()
                .orElse(null);
        return foundTheme;
    }

    @Override
    public Theme getById(Integer id) {
        Theme foundTheme = themes.stream().filter(t -> t.getId() == id)
                .findAny()
                .orElse(null);
        return foundTheme;

    }

    @Override
    public void edit(Theme theme, Integer id) {
        Theme foundTheme = themes.stream().filter(o -> o.getId() == (id))
                .findAny()
                .orElse(null);
        if (foundTheme != null) {
            themes.set(id, theme);
            saveThemeList(themes);
        }
    }

    @Override
    public void addOne(Theme theme) {
        themes.add(theme);
    }

    @Override
    public ArrayList<Theme> getAll() {
        return themes;
    }

    /**
     * Test data for themes.
     *
     */
    private void initialiseThemes() {
        themes = new ArrayList<>();
        Subforum first = SubforumServiceImpl.getInstance().getById(1);
        UserModel one = UserServiceImpl.getInstance().getById(1);

        themes.add(new Theme(first, "First theme", ThemeType.TEXT, one, null, "one two three", LocalDate.of(2017, 8, 8), 1, 0));
        themes.add(new Theme(first, "Second theme", ThemeType.TEXT, one, null, "tototot", LocalDate.of(2017, 8, 8), 5, 5));
        themes.add(new Theme(first, "Third theme", ThemeType.TEXT, one, null, "ajajajaj", LocalDate.of(2017, 8, 8), 0, 5));

        try {
            File file = new File(DIRECTORY, FILENAME);
            if (!file.exists()) {
                saveThemeList(themes);
            } else {
                FileInputStream fis = new FileInputStream(file);
                try (ObjectInputStream ois = new ObjectInputStream(fis)) {
                    themes = (ArrayList<Theme>) ois.readObject();
                }
            }
        } catch (IOException | ClassNotFoundException ex) {
        }
    }
}
