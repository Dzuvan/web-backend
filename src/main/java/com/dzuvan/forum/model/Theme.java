/*
 * Copyright (C) 2017 Pivotal Software, Inc.
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
package com.dzuvan.forum.model;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicLong;

/**
 *
 * @author dzuvan
 */
public class Theme implements Serializable {

    private static final long serialVersionUID = -9143705498330171960L;
    public static AtomicLong nextId = new AtomicLong();

    private long id;
    private Subforum subforum;
    private String title;
    private ThemeType type;
    private UserModel author;
    private List<Comment> comments;
    private String content;
    private LocalDate dateOfCreating;
    private int likes;
    private int dislikes;

    public Theme() {
    }

    /**
     *
     * @param subforum
     * @param title
     * @param type
     * @param author
     * @param content
     * @param dateOfCreating
     */
    public Theme(Subforum subforum, String title, ThemeType type,
            UserModel author, String content, LocalDate dateOfCreating) {
        this.subforum = subforum;
        this.title = title;
        this.type = type;
        this.author = author;
        this.comments = new ArrayList<>();
        this.content = content;
        this.dateOfCreating = dateOfCreating;
        this.likes = 0;
        this.dislikes = 0;
        this.id = nextId.incrementAndGet();
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    /**
     *
     * @return
     */
    public Subforum getSubforum() {
        return subforum;
    }

    /**
     *
     * @param subforum
     */
    public void setSubforum(Subforum subforum) {
        this.subforum = subforum;
    }

    /**
     *
     * @return
     */
    public String getTitle() {
        return title;
    }

    /**
     *
     * @param title
     */
    public void setTitle(String title) {
        this.title = title;
    }

    /**
     *
     * @return
     */
    public ThemeType getType() {
        return type;
    }

    /**
     *
     * @param type
     */
    public void setType(ThemeType type) {
        this.type = type;
    }

    /**
     *
     * @return
     */
    public UserModel getAuthor() {
        return author;
    }

    /**
     *
     * @param author
     */
    public void setAuthor(UserModel author) {
        this.author = author;
    }

    /**
     *
     * @return
     */
    public List<Comment> getComments() {
        return comments;
    }

    /**
     *
     * @param comments
     */
    public void setComments(List<Comment> comments) {
        this.comments = comments;
    }

    /**
     *
     * @return
     */
    public String getContent() {
        return content;
    }

    /**
     *
     * @param content
     */
    public void setContent(String content) {
        this.content = content;
    }

    /**
     *
     * @return
     */
    public LocalDate getDateOfCreating() {
        return dateOfCreating;
    }

    /**
     *
     * @param dateOfCreating
     */
    public void set(LocalDate dateOfCreating) {
        this.dateOfCreating = dateOfCreating;
    }

    /**
     *
     * @return
     */
    public int getLikes() {
        return likes;
    }

    /**
     *
     * @param likes
     */
    public void setLikes(int likes) {
        this.likes = likes;
    }

    /**
     *
     * @return
     */
    public int getDislikes() {
        return dislikes;
    }

    /**
     *
     * @param dislikes
     */
    public void setDislikes(int dislikes) {
        this.dislikes = dislikes;
    }

    @Override
    public String toString() {
        return "Theme{" + "id=" + id + ", subforum=" + subforum + ", title=" + title + ", type=" + type + ", author=" + author + ", comments=" + comments + ", content=" + content + ", dateOfCreating=" + dateOfCreating + ", likes=" + likes + ", dislikes=" + dislikes + '}';
    }
}
