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

import com.dzuvan.forum.model.Theme;

/**
 *
 * @author dzuvan
 */
public interface ThemeService {

    /**
     *
     * @param theme
     */
    void delete(Theme theme);

    /**
     *
     * @param s
     * @return
     */
    Theme getByString(String s);

    /**
     *
     * @param id
     * @return
     */
    Theme getById(Integer id);

    /**
     *
     * @param theme
     * @param id
     */
    void edit(Theme theme, Integer id);

    /**
     *
     * @param theme
     * @return
     */
    boolean addOne(Theme theme);

    /**
     *
     * @return
     */
    Iterable getAll();
}
