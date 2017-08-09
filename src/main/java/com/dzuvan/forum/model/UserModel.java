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
import java.util.List;
import javax.xml.bind.annotation.XmlRootElement;


/**
 *
 * @author dzuvan
 */
public class UserModel  implements Serializable{

    private static final long serialVersionUID = -4156591132476352712L;


    private Integer id;
    private static Integer nextId = 1;

    private String username;
    private String password;

    private String firstName;
    private String lastName;
    private Role role;
    private String phone;
    private String email;
    private LocalDate registrationDate;
    //TODO: Ovo bi možda moglo u neku drugu klasu kako bi ova bila isključivo za korisnike
    private List<Theme> followedThemes;
    private List<Subforum> followedSubforums;
    private List<Comment> savedComments;

    /**
     *
     */
    public UserModel() {
        id = nextId;
        nextId++;
    }

    /**
     *
     * @param username
     * @param password
     * @param firstName
     * @param role
     * @param lastName
     * @param phone
     * @param email
     * @param registrationDate
     * @param followedThemes
     * @param followedSubforums
     * @param savedComments
     */
    public UserModel(String username, String password, String firstName,
                       String lastName, Role role, String phone, String email,
                       LocalDate registrationDate, List<Theme> followedThemes,
                       List<Subforum> followedSubforums, List<Comment> savedComments) {
        this();
        this.username = username;
        this.password = password;
        this.firstName = firstName;
        this.lastName = lastName;
        this.role = role;
        this.phone = phone;
        this.email = email;
        this.registrationDate = registrationDate;
        this.followedThemes = followedThemes;
        this.followedSubforums = followedSubforums;
        this.savedComments = savedComments;
    }


    /**
     *
     * @return
     */
    public String getFirstName() {
        return firstName;
    }

    /**
     *
     * @param firstName
     */
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    /**
     *
     * @return
     */
    public String getLastName() {
        return lastName;
    }

    /**
     *
     * @param lastName
     */
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    /**
     *
     * @return
     */
    public LocalDate getRegistrationDate() {
        return registrationDate;
    }

    /**
     *
     * @param registrationDate
     */
    public void setRegistrationDate(LocalDate registrationDate) {
        this.registrationDate = registrationDate;
    }

    /**
     *
     * @return
     */
    public Integer getId() {
        return id;
    }

    /**
     *
     * @param id
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     *
     * @return
     */
    public String getUsername() {
        return username;
    }

    /**
     *
     * @param username
     */
    public void setUsername(String username) {
        this.username = username;
    }

    /**
     *
     * @return
     */
    public String getPassword() {
        return password;
    }

    /**
     *
     * @param password
     */

    public void setPassword(String password) {
        this.password = password;
    }

    /**
     *
     * @return
     */
    public Role getRole() {
        return role;
    }

    /**
     *
     * @param role
     */
    public void setRole(Role role) {
        this.role = role;
    }

    /**
     *
     * @return
     */
    public String getPhone() {
        return phone;
    }

    /**
     *
     * @param phone
     */
    public void setPhone(String phone) {
        this.phone = phone;
    }

    /**
     *
     * @return
     */
    public String getEmail() {
        return email;
    }

    /**
     *
     * @param email
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     *
     * @return
     */
    public List<Theme> getFollowedThemes() {
        return followedThemes;
    }

    /**
     *
     * @param followedThemes
     */
    public void setFollowedThemes(List<Theme> followedThemes) {
        this.followedThemes = followedThemes;
    }

    /**
     *
     * @return
     */
    public List<Subforum> getFollowedSubforums() {
        return followedSubforums;
    }

    /**
     *
     * @param followedSubforums
     */
    public void setFollowedSubforums(List<Subforum> followedSubforums) {
        this.followedSubforums = followedSubforums;
    }

    /**
     *
     * @return
     */
    public List<Comment> getSavedComments() {
        return savedComments;
    }

    /**
     *
     * @param savedComments
     */
    public void setSavedComments(List<Comment> savedComments) {
        this.savedComments = savedComments;
    }

    @Override
    public String toString() {
        return "UserDetails{" + "id=" + id + ", username=" + username + ", password=" + password + ", role=" + role + ", phone=" + phone + ", email=" + email + ", registrationDate=" + registrationDate + ", followedThemes=" + followedThemes + ", followedSubforums=" + followedSubforums + ", savedComments=" + savedComments + '}';
    }



}
