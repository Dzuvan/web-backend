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

import com.dzuvan.forum.model.UserModel;

/**
 *
 * @author dzuvan
 */
public interface UserService {

    /**
     *
     * @param user
     */
    void delete(UserModel user);

    /**
     *
     * @param s
     * @return
     */
    UserModel getByString(String s);

    /**
     *
     * @param id
     * @return
     */
    UserModel getById(long id);

    /**
     *
     * @param user
     * @param id
     */
    void edit(UserModel user, long id);

    /**
     *
     * @param user
     */
    void addOne(UserModel user);

    /**
     *
     * @return
     */
    Iterable getAll();
}
