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

/**
 *
 * @author dzuvan
 */
public class Message {

    //TODO(Jovan): ID.
    private UserModel Sender;
    private UserModel Reciever;
    private String content;
    private boolean isRead;

    /**
     *
     */
    public Message() {
    }

    /**
     *
     * @param Sender
     * @param Reciever
     * @param content
     * @param isRead
     */
    public Message(UserModel Sender, UserModel Reciever, String content,
            boolean isRead) {
        this.Sender = Sender;
        this.Reciever = Reciever;
        this.content = content;
        this.isRead = isRead;
    }

    /**
     *
     * @return
     */
    public UserModel getSender() {
        return Sender;
    }

    /**
     *
     * @param Sender
     */
    public void setSender(UserModel Sender) {
        this.Sender = Sender;
    }

    /**
     *
     * @return
     */
    public UserModel getReciever() {
        return Reciever;
    }

    /**
     *
     * @param Reciever
     */
    public void setReciever(UserModel Reciever) {
        this.Reciever = Reciever;
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
    public boolean isIsRead() {
        return isRead;
    }

    /**
     *
     * @param isRead
     */
    public void setIsRead(boolean isRead) {
        this.isRead = isRead;
    }

    @Override
    public String toString() {
        return "Message{" + "Sender=" + Sender + ", Reciever=" + Reciever + ", content=" + content + ", isRead=" + isRead + '}';
    }

}
