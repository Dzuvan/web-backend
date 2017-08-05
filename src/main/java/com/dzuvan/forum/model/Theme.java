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

import java.util.Date;
import java.util.List;

/**
 *
 * @author dzuvan
 */
public class Theme {
   
    private Subforum subforum;
    private String title;
    private ThemeType type;
    private UserModel author;
    private List<Comment> comments;
    private String content;
    private Date dateOfCreating;
    private Integer likes;
    private Integer dislikes;

    public Theme() {
    }

    /**
     *
     * @param subforum
     * @param title
     * @param type
     * @param author
     * @param comments
     * @param content
     * @param dateOfCreating
     * @param likes
     * @param dislikes
     */
    public Theme(Subforum subforum, String title, ThemeType type, 
                UserModel author, List<Comment> comments, String content, 
                Date dateOfCreating, Integer likes, Integer dislikes) {
        this.subforum = subforum;
        this.title = title;
        this.type = type;
        this.author = author;
        this.comments = comments;
        this.content = content;
        this.dateOfCreating = dateOfCreating;
        this.likes = likes;
        this.dislikes = dislikes;
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
    public Date getDateOfCreating() {
        return dateOfCreating;
    }

    /**
     *
     * @param dateOfCreating
     */
    public void setDateOfCreating(Date dateOfCreating) {
        this.dateOfCreating = dateOfCreating;
    }

    /**
     *
     * @return
     */
    public Integer getLikes() {
        return likes;
    }

    /**
     *
     * @param likes
     */
    public void setLikes(Integer likes) {
        this.likes = likes;
    }

    /**
     *
     * @return
     */
    public Integer getDislikes() {
        return dislikes;
    }

    /**
     *
     * @param dislikes
     */
    public void setDislikes(Integer dislikes) {
        this.dislikes = dislikes;
    }

    @Override
    public String toString() {
        return "Theme{" + "subforum=" + subforum + ", title=" + title + ", type=" + type + ", author=" + author + ", comments=" + comments + ", content=" + content + ", dateOfCreating=" + dateOfCreating + ", likes=" + likes + ", dislikes=" + dislikes + '}';
    }

    

    
}
