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
import java.util.List;
/**
 *
 * @author dzuvan
 */
public class Subforum implements Serializable {

    private static final long serialVersionUID = 3261045213287698581L;

    private Integer id;
    private static Integer nextId = 1;

    private String name;
    private String description;
    private transient IconFile icon;
    private String rules;
    private UserModel responsibleModerator;
    private List<UserModel> moderators;

    public Subforum() {
        id = nextId;
        nextId++;
    }

    /**
     *
     * @param name
     * @param description
     * @param rules
     */
    public Subforum (String name, String description, String rules) { 
        this();
        this.name = name;
        this.description = description;
        this.rules = rules;
    }

    /**
     *
     * @param name
     * @param description
     * @param rules
     * @param icon
     */
    public Subforum (String name, String description, String rules,
            IconFile icon) { 
        this();
        this.name = name;
        this.description = description;
        this.rules = rules;
        this.icon = icon;
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
    public IconFile getIcon() {
        return icon;
    }

    /**
     *
     * @param icon
     */
    public void setIcon(IconFile icon) {
        this.icon = icon;
    }

    /**
     *
     * @return
     */
    public String getRules() {
        return rules;
    }

    /**
     *
     * @param rules
     */
    public void setRules(String rules) {
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
        return "Subforum{" + "id=" + id + ", name=" + name + ", description=" + description + ", icon=" + icon + ", rules=" + rules + ", responsibleModerator=" + responsibleModerator + ", moderators=" + moderators + '}';
    }


}
