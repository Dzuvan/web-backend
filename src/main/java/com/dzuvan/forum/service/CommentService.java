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

/**
 *
 * @author dzuvan
 */
public interface CommentService {
    
    /**
     *
     * @param o
     */
    void delete(Comment o);

    /**
     *
     * @param s
     * @return
     */
    Comment getByString(String s);

    /**
     *
     * @param id
     * @return
     */
    Comment getById(Integer id);

    /**
     *
     * @param o
     * @param id
     */
    void edit(Comment o, Integer id);

    /**
     *
     * @param o
     * @return
     */
    boolean addOne(Comment o);

    /**
     *
     * @return
     */
    Iterable getAll();
}
