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

import com.dzuvan.forum.model.Subforum;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

/**
 *
 * @author dzuvan
 */
public class SubforumServiceImpl implements SubforumService {
    private static final String FILENAME = "subforums.dat";
    private static final String DIRECTORY = System.getProperty("user.dir");
    
    private ArrayList<Subforum> subforums;
    private static SubforumServiceImpl instance = null;
    
    private SubforumServiceImpl(){
        subforums = new ArrayList<>();
        init();
    }
    
    /**
     *
     * @return Singleton
     */
    public static SubforumServiceImpl getInstance() {
        if(instance == null) {
            instance = new SubforumServiceImpl();
        }
        return instance;
    }
    
    /**
     *
     * @return
     */
    public ArrayList<Subforum> getSubforums() {
        try {
            File file = new File(DIRECTORY, FILENAME);
            
            if (!file.exists()) {
                saveSubforumList(subforums);
            } else {
                FileInputStream fis = new FileInputStream(file);
                try ( ObjectInputStream ois = new ObjectInputStream(fis)) {
                    subforums = (ArrayList<Subforum>)ois.readObject();
                }
            }

        } catch(IOException | ClassNotFoundException ex) {}
        return subforums;
    }

    /**
     *
     * @param subforums
     */
    public void saveSubforumList(ArrayList<Subforum> subforums) {
       try {
           File file = new File(DIRECTORY, FILENAME);
           FileOutputStream fos = new FileOutputStream(file);

           try (ObjectOutputStream oos = new ObjectOutputStream(fos)) {
               oos.writeObject(subforums);
           }
       }  catch(FileNotFoundException ex) {}
        catch(IOException ex) {}
    }
    
    @Override
    public void delete(Subforum subforum) {
        Subforum foundSubforum = getSubforums().stream().filter(s->s.getName().equals(subforum.getName()))
                .findFirst()
                .get();
        if (foundSubforum != null) 
            subforums.remove(foundSubforum);
    }
    
    @Override
    public Subforum getByString(String name) {
        Subforum foundSubforum = getSubforums().stream().filter(s->s.getName().equals(name))
                .findFirst()
                .orElse(null);
        return (foundSubforum != null) ? foundSubforum : null;
    }
    
    @Override
    public Subforum getById(Integer id) {
        Subforum foundSubforum;
        foundSubforum = getSubforums().stream().filter(s->s.getId() == id)
                        .findFirst()
                        .orElse(null);
        return(foundSubforum != null) ? foundSubforum : null;
    }
    
    @Override
    public Subforum edit(Subforum subforum, Integer id) {
        subforum = getSubforums().stream().filter(s->s.getId() == id)
                .findFirst()
                .orElse(null);
        return (subforum != null) ? subforums.set(id, subforum) : null;
    }
    
    @Override
    public boolean addOne(Subforum subforum) {
        ArrayList<Subforum> sf = getSubforums();
        Subforum foundSubforum;
        foundSubforum = sf.stream().filter(s->s.getName().equals(subforum.getName()))
                                  .findFirst()
                                  .orElse(null);
        if (!foundSubforum.getName().equals(subforum.getName()))  {
                sf.add(subforum);
                saveSubforumList(subforums);
                return true;
        }
        else {

            return false;
        }
    }
    
    @Override
    public ArrayList<Subforum> getAll() {
        return getSubforums();
    }
    
    public final void init() {
        subforums.add(new Subforum("forum1", "description1", null, null,
                UserServiceImpl.getInstance().getByString("mode"), null)) ;
    }
}
