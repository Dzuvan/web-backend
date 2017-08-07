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
package com.dzuvan.forum.model;

import java.io.InputStream;
import java.io.Serializable;
import org.glassfish.jersey.media.multipart.FormDataContentDisposition;

/**
 *
 * @author dzuvan
 */
public class IconFile implements Serializable{

    private static final long serialVersionUID = 6986646189673888961L;
    
    private InputStream file;
    private FormDataContentDisposition fileData;

    public IconFile() {}

    public IconFile(InputStream file, FormDataContentDisposition fileData) {
        this.file = file;
        this.fileData = fileData;
    }

    public InputStream getFile() {
        return file;
    }

    public void setFile(InputStream file) {
        this.file = file;
    }

    public FormDataContentDisposition getFileData() {
        return fileData;
    }

    public void setFileData(FormDataContentDisposition fileData) {
        this.fileData = fileData;
    }

    @Override
    public String toString() {
        return "IconFile{" + "file=" + file + ", fileData=" + fileData + '}';
    }
}
