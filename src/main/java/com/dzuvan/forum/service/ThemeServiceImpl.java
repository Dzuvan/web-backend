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

import com.dzuvan.forum.model.Theme;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import javax.ws.rs.core.Response;

/**
 *
 * @author dzuvan
 */
public class ThemeServiceImpl implements ThemeService {
    private static final String FILENAME = "themes.dat";
    private static final String DIRECTORY = System.getProperty("user.dir");
    
    private static ThemeServiceImpl instance = null;
    private ArrayList<Theme> themes;
    
    private ThemeServiceImpl() {
                themes =  new ArrayList<>();
                init();
    }
    
    /**
     * Singleton
     * @return instance
     */
    public static ThemeServiceImpl getInstance() {
        if(instance == null){
            instance = new ThemeServiceImpl();
        }
        return instance;
    }
    
    /**
     *
     * @return
     */
    public ArrayList<Theme> getThemes() {
        try {
            File file = new File(DIRECTORY, FILENAME);
            if (!file.exists()) {
                saveUserList(themes);
            } else {
                FileInputStream fis = new FileInputStream(file);
                try (ObjectInputStream ois = new ObjectInputStream(fis)) {
                    themes = (ArrayList<Theme>) ois.readObject();
                }
            }
        } catch (IOException | ClassNotFoundException ex) {}
        
        return themes;
    }
    
    /**
     *
     * @param themes 
     */
    public void saveUserList(ArrayList<Theme> themes) {
        try {
            File file = new File(DIRECTORY, FILENAME);
            FileOutputStream fos = new FileOutputStream(file);
            try (ObjectOutputStream oos = new ObjectOutputStream(fos)) {
                oos.writeObject(themes);
            }
        } catch (FileNotFoundException ex){
        } catch(IOException ex) {}
    }

    @Override
    public void delete(Theme o) {
        Theme foundTheme = getThemes().stream().filter(t->t.getTitle().equals(o.getTitle()))
                .findAny()
                .orElse(null);
        if (foundTheme != null) {
            getThemes().remove(foundTheme);
        } else {
            Response.noContent().build();
        }
    }

    @Override
    public Theme getByString(String s) {
        Theme foundTheme = getThemes().stream().filter(t->t.getTitle().equals(s))
                .findAny()
                .orElse(null);
        return (foundTheme!= null) ? foundTheme: null;
    }

    @Override
    public Theme getById(Integer id) {
        Theme foundTheme = getThemes().stream().filter(t->t.getId().equals(id))
                .findAny()
                .orElse(null);
        return (foundTheme!= null) ? foundTheme: null;
        
    }

    @Override
    public Theme edit(Theme theme, Integer id) {
        Theme foundTheme = getThemes().stream().filter(o->o.getId().equals((id)))
                .findAny()
                .orElse(null);
        return (foundTheme != null) ? getThemes().set(id, theme ) : null;
    }

    @Override
    public boolean addOne(Theme theme) {
        Theme foundTheme= getThemes().stream().filter(t->t.getId().equals(theme.getId()))
                .findAny()
                .orElse(null);
            if (foundTheme == null) {
                getThemes().add(theme);
                saveUserList(getThemes());
                return true;
            } else 
                return false;

    }

    @Override
    public ArrayList<Theme>getAll() {
        return getThemes();
    }

    // TODO: Popuniti.

    public final void init() {
        getThemes().add(new Theme());
        getThemes().add(new Theme());
        getThemes().add(new Theme());

    }
    
}
