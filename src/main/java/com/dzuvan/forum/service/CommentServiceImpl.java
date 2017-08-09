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

import com.dzuvan.forum.model.Comment;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;


public class CommentServiceImpl implements CommentService {

    private static final String FILENAME = "comments.dat";
    private static final String DIRECTORY = System.getProperty("user.dir");

    private static CommentServiceImpl instance = null;
    private ArrayList<Comment> comments;

    private CommentServiceImpl() {
        comments = new ArrayList<>();
        init();
    }

    public static CommentServiceImpl getInstance() {
        if (instance == null) {
            instance = new CommentServiceImpl();
        }
        return instance;
    }

    public ArrayList<Comment> getComments() {
        try {
            File file = new File(DIRECTORY, FILENAME);
            
            if (!file.exists()){
                saveComments(comments);
            } else {
                FileInputStream fis = new FileInputStream(file);
                try (ObjectInputStream ois = new ObjectInputStream(fis)) {
                    comments = (ArrayList<Comment>)ois.readObject();
                }
            }
        } catch (IOException | ClassNotFoundException ex) {}
        return comments;
    }

    public  void saveComments(ArrayList<Comment> comments) {
        try {
            File file = new File(DIRECTORY, FILENAME);
            FileOutputStream fos = new FileOutputStream(file);
            try (ObjectOutputStream oos = new ObjectOutputStream(fos)) {
                oos.writeObject(comments);
            }
        } catch (FileNotFoundException ex){
        } catch(IOException ex) {}
    }

    @Override
    public ArrayList<Comment> getAll() {
        return getComments();     
    }

    @Override
    public boolean addOne(Comment comment) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Comment edit(Comment o, Integer id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Comment getById(Integer id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Comment getByString(String s) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void delete(Comment o) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public final void init() {

    }
    
}
