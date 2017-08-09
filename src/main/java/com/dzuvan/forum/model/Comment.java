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

/**
 *
 * @author dzuvan
 */
public class Comment implements Serializable {

    private static final long serialVersionUID = -3812645247109314175L;

    private Integer id;
    private static Integer nextId = 1;

    private UserModel author;
    private LocalDate commentDate;
    private Comment parentComment;
    private String commentText;
    private Integer likes;
    private Integer dislikes;
    private boolean isEdited;

    /**
     *
     */
    public Comment() {
        id = nextId;
        nextId++;
    }

    /**
     *
     * @param author
     * @param commentDate
     * @param parentComment
     * @param commentText
     * @param likes
     * @param dislikes
     * @param isEdited
     */
    public Comment(UserModel author, LocalDate commentDate, Comment parentComment,
            String commentText, Integer likes, Integer dislikes,
            boolean isEdited) {
        this();
        this.author = author;
        this.commentDate = commentDate;
        this.parentComment = parentComment;
        this.commentText = commentText;
        this.likes = likes;
        this.dislikes = dislikes;
        this.isEdited = isEdited;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public static Integer getNextId() {
        return nextId;
    }

    public static void setNextId(Integer nextId) {
        Comment.nextId = nextId;
    }

    public boolean isIsEdited() {
        return isEdited;
    }

    public void setIsEdited(boolean isEdited) {
        this.isEdited = isEdited;
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
    public LocalDate getCommentDate() {
        return commentDate;
    }

    /**
     *
     * @param commentDate
     */
    public void setCommentDate(LocalDate commentDate) {
        this.commentDate = commentDate;
    }

    /**
     *
     * @return
     */
    public Comment getParentComment() {
        return parentComment;
    }

    /**
     *
     * @param parentComment
     */
    public void setParentComment(Comment parentComment) {
        this.parentComment = parentComment;
    }

    /**
     *
     * @return
     */
    public String getCommentText() {
        return commentText;
    }

    /**
     *
     * @param commentText
     */
    public void setCommentText(String commentText) {
        this.commentText = commentText;
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

    /**
     *
     * @return
     */
    public boolean isEdited() {
        return isEdited;
    }

    /**
     *
     * @param isEdited
     */
    public void setisEdited(boolean isEdited) {
        this.isEdited = isEdited;
    }

    @Override
    public String toString() {
        return "Comment{" + "id=" + id + ", author=" + author + ", commentDate=" + commentDate + ", parentComment=" + parentComment + ", commentText=" + commentText + ", likes=" + likes + ", dislikes=" + dislikes + ", isEdited=" + isEdited + '}';
    }

}
