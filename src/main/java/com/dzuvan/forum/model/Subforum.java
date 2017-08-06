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

import java.util.Arrays;
import java.util.List;
/**
 *
 * @author dzuvan
 */
public class Subforum {

    private Integer id;
    private static Integer nextId = 1;
    private String name;
    private String description;
    private byte[] icon;
    private List<String> rules;
    private UserModel responsibleModerator;
    private List<UserModel> moderators;

    public Subforum() {
        id = nextId;
        nextId++;
    }
    public Subforum(String name){
        this();  
        this.name = name;
    }

    /**
     *
     * @param name
     * @param description
     * @param icon
     * @param rules
     * @param responsibleModerator
     * @param moderators
     */
    public Subforum( String name, String description, byte[] icon,
                    List<String> rules, UserModel responsibleModerator,
                    List<UserModel> moderators) {
        this();
        this.name = name;
        this.description = description;
        this.icon = icon;
        this.rules = rules;
        this.responsibleModerator = responsibleModerator;
        this.moderators = moderators;
    }

    /**
     *
     * @return
     */
    public int getId() {
        return id;
    }

    /**
     *
     * @param id
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     *
     * @return
     */
    public String getName() {
        return name;
    }

    /**
     *
     * @param name
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     *
     * @return
     */
    public String getDescription() {
        return description;
    }

    /**
     *
     * @param description
     */
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     *
     * @return
     */
    public byte[] getIcon() {
        return icon;
    }

    /**
     *
     * @param icon
     */
    public void setIcon(byte[] icon) {
        this.icon = icon;
    }

    /**
     *
     * @return
     */
    public List<String> getRules() {
        return rules;
    }

    /**
     *
     * @param rules
     */
    public void setRules(List<String> rules) {
        this.rules = rules;
    }

    /**
     *
     * @return
     */
    public UserModel getResponsibleModerator() {
        return responsibleModerator;
    }

    /**
     *
     * @param responsibleModerator
     */
    public void setResponsibleModerator(UserModel responsibleModerator) {
        this.responsibleModerator = responsibleModerator;
    }

    /**
     *
     * @return
     */
    public List<UserModel> getModerators() {
        return moderators;
    }

    /**
     *
     * @param moderators
     */
    public void setModerators(List<UserModel> moderators) {
        this.moderators = moderators;
    }

    @Override
    public String toString() {
        return "Subforum{" + "id=" + id + ", name=" + name + ", description=" + description + ", icon=" + Arrays.toString(icon) + ", rules=" + rules + ", responsibleModerator=" + responsibleModerator + ", moderators=" + moderators + '}';
    }

}
