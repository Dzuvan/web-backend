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

    public static CommentServiceImpl getInstance() {
        if (instance == null) {
            instance = new CommentServiceImpl();
            instance.initialiseComments();
        }
        return instance;
    }

    private CommentServiceImpl() {
    }

    private void saveComments(ArrayList<Comment> comments) {
        try {
            File file = new File(DIRECTORY, FILENAME);
            FileOutputStream fos = new FileOutputStream(file);
            try (ObjectOutputStream oos = new ObjectOutputStream(fos)) {
                oos.writeObject(comments);
            }
        } catch (FileNotFoundException ex) {
        } catch (IOException ex) {
        }
    }

    @Override
    public ArrayList<Comment> getAll() {
        return comments;
    }

    @Override
    public boolean addOne(Comment comment) {
        Comment foundComment = comments.stream().filter(c -> c.getId() == comment.getId())
                .findAny()
                .orElse(null);
        if (foundComment == null) {
            comments.add(comment);
            saveComments(comments);
            return true;
        } else {
            return false;
        }
    }

    @Override
    public void edit(Comment comment, Integer id) {
        Comment foundComment = comments.stream().filter(c -> c.getId() == comment.getId())
                .findAny()
                .orElse(null);
        if (foundComment != null) {
            comments.set(id, comment);
            saveComments(comments);
        }
    }

    @Override
    public Comment getById(Integer id) {
        Comment foundComment = comments.stream().filter(c -> c.getId() == id)
                .findAny()
                .orElse(null);
        return (foundComment != null) ? foundComment : null;
    }

    @Override
    public Comment getByString(String s) {
        Comment foundComment = comments.stream().filter(c -> c.getCommentText().equals(s))
                .findAny()
                .orElse(null);
        return (foundComment != null) ? foundComment : null;
    }

    @Override
    public void delete(Comment o) {
        Comment foundComment = comments.stream().filter(c -> c.getId() == o.getId())
                .findFirst()
                .orElse(null);
        if (foundComment != null) {
            comments.remove(foundComment);
            comments.stream().filter((c) -> (c.getId() > foundComment.getId())).forEachOrdered((c) -> {
                int i = c.getId();
                c.setId(i--);
            });
            saveComments(comments);
        }
    }

    private void initialiseComments() {
        comments = new ArrayList<>();

        try {
            File file = new File(DIRECTORY, FILENAME);

            if (!file.exists()) {
                saveComments(comments);
            } else {
                FileInputStream fis = new FileInputStream(file);
                try (ObjectInputStream ois = new ObjectInputStream(fis)) {
                    comments = (ArrayList<Comment>) ois.readObject();
                }
            }
        } catch (IOException | ClassNotFoundException ex) {
        }
    }
}
