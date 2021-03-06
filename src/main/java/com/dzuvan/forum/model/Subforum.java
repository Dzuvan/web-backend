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
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicLong;

/**
 *
 * @author dzuvan
 */
public class Subforum implements Serializable {

    private static final long serialVersionUID = 3261045213287698581L;
    public static AtomicLong nextId = new AtomicLong();

    private long id;
    private String name;
    private String description;
    private String icon;
    private String rules;
    private UserModel responsibleModerator;
    private List<Integer> moderators;

    public Subforum() {

    }

    /**
     *
     * @param name
     * @param description
     * @param rules
     * @param icon
     * @param responsibleModerator
     */
    public Subforum(String name, String description, String rules,
            String icon, UserModel responsibleModerator) {
        this.name = name;
        this.description = description;
        this.rules = rules;
        this.icon = icon;
        this.moderators = new ArrayList<>();
        this.responsibleModerator = responsibleModerator;
        this.id = nextId.incrementAndGet();
    }

    /**
     *
     * @return
     */
    public long getId() {
        return id;
    }

    /**
     *
     * @param id
     */
    public void setId(long id) {
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
    public String getIcon() {
        return icon;
    }

    /**
     *
     * @param icon
     */
    public void setIcon(String icon) {
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
    public List<Integer> getModerators() {
        return moderators;
    }

    /**
     *
     * @param moderators
     */
    public void setModerators(List<Integer> moderators) {
        this.moderators = moderators;
    }

    @Override
    public String toString() {
        return "Subforum{" + "id=" + id + ", name=" + name + ", description=" + description + ", icon=" + icon + ", rules=" + rules + ", responsibleModerator=" + responsibleModerator + ", moderators=" + moderators + '}';
    }
}
