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
package com.dzuvan.forum.controller;

import com.dzuvan.forum.model.Comment;
import com.dzuvan.forum.model.Subforum;
import com.dzuvan.forum.model.Theme;
import com.dzuvan.forum.model.ThemeType;
import com.dzuvan.forum.model.UserModel;
import com.dzuvan.forum.service.SubforumServiceImpl;
import com.dzuvan.forum.service.ThemeServiceImpl;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.time.LocalDate;
import java.util.ArrayList;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.OPTIONS;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import org.glassfish.jersey.media.multipart.FormDataContentDisposition;
import org.glassfish.jersey.media.multipart.FormDataParam;

/**
 *
 * @author dzuvan
 */

@Path("/themeService")
public class ThemeController {

    /**
     *
     * @return
     */
    @GET
    @Path("/themes")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getAllThemes() {
        ArrayList<Theme> themes = ThemeServiceImpl.getInstance().getAll();
        return  Response.ok()
                        .entity(themes)
                        .header("Access-Control-Allow-Origin", "*")
                        .header("Access-Control-Allow-Methods", "GET, POST, DELETE, PUT")
                        .allow("OPTIONS")
                        .build();
    }

    @GET
    @Path("/themes/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getThemeById(@PathParam("id") Integer id) {
        Theme theme = ThemeServiceImpl.getInstance().getById(id);
        if (theme != null) {
            return  Response.ok()
                            .entity(theme)
                            .header("Access-Control-Allow-Origin", "*")
                            .header("Access-Control-Allow-Methods", "GET, POST, DELETE, PUT")
                            .allow("OPTIONS")
                            .build();
        }   else {
            return Response.noContent().build();
            }
    }
    
    /**
     *
     * @param title
     * @return
     */
    @GET
    @Path("/themes/name/{title}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getThemeByTitle(@PathParam("title") String title) {
        Theme theme = ThemeServiceImpl.getInstance().getByString(title);
        if (theme != null) {
            return  Response.ok()
                            .entity(theme)
                            .header("Access-Control-Allow-Origin", "*")
                            .header("Access-Control-Allow-Methods", "GET, POST, DELETE, PUT")
                            .allow("OPTIONS")
                            .build();
        }   else {
            return Response.noContent().build();
            }
    }

    
    /**
     *Vrlo moguće da je suvišno.
     * @return dozvoljene operacije za CRUD, jer CORS pravi problem.
     */
    @OPTIONS
    @Path("/themes")
    @Produces(MediaType.APPLICATION_JSON)
    public String getSupportedOperations () {
    return "<operations>GET, PUT, POST, DELETE</operations>";
    }

    @POST
    @Path("/themes")
    @Consumes(MediaType.MULTIPART_FORM_DATA)
    @Produces(MediaType.APPLICATION_JSON)
    public Response addTheme(   @FormDataParam("title") String title,
                                @FormDataParam("author") UserModel author,
                                @FormDataParam("type") ThemeType type,
                                @FormDataParam("subforum") Subforum subforum,
                                @FormDataParam("comments") ArrayList<Comment> comments,
                                @FormDataParam("content") InputStream file,
                                @FormDataParam("content") FormDataContentDisposition fileData,
                                @FormDataParam("likes") Integer likes, 
                                @FormDataParam("dislikes") Integer dislikes) {

        String location = System.getProperty("user.dir") + fileData.getFileName();
        Theme theme= new Theme(subforum,title,type, author, comments, location,
                LocalDate.now(), likes, dislikes);

        writeToFile(file, location);
        if (theme.getId() == 0) {
            return Response.status(Response.Status.NO_CONTENT)
                    .entity(theme)
                    .header("Access-Control-Allow-Origin", "*")
                    .header("Access-Control-Allow-Methods", "GET, POST, DELETE, PUT")
                    .allow("OPTIONS")
                    .build();
        }   else {
            ThemeServiceImpl.getInstance().addOne(theme);
            return  Response.status(Response.Status.CREATED)
                        .entity(theme)
                        .header("Access-Control-Allow-Origin", "*")
                        .header("Access-Control-Allow-Methods", "GET, POST, DELETE, PUT")
                        .allow("OPTIONS")
                        .build();
            }
    }

    @PUT
    @Path("/themes")
    @Consumes(MediaType.MULTIPART_FORM_DATA)
    @Produces(MediaType.APPLICATION_JSON)
    public Response editTheme(  @FormDataParam("title") String title,
                                @FormDataParam("author") UserModel author,
                                @FormDataParam("type") ThemeType type,
                                @FormDataParam("subforum") Subforum subforum,
                                @FormDataParam("comments") ArrayList<Comment> comments,
                                @FormDataParam("content") InputStream file,
                                @FormDataParam("content") FormDataContentDisposition fileData,
                                @FormDataParam("likes") Integer likes, 
                                @FormDataParam("dislikes") Integer dislikes) {

        String location = System.getProperty("user.dir") + fileData.getFileName();
        Theme theme= new Theme(subforum,title,type, author, comments, location,
                LocalDate.now(), likes, dislikes);
        writeToFile(file, location);

        if(theme.getId() == 0){
            return Response.status(Response.Status.NO_CONTENT)
                    .entity(subforum)
                    .header("Access-Control-Allow-Origin", "*")
                    .header("Access-Control-Allow-Methods", "GET, POST, DELETE, PUT")
                    .allow("OPTIONS")
                    .build();
        }   else {
            ThemeServiceImpl.getInstance().edit(theme, theme.getId()); // fishy
            return  Response.status(Response.Status.CREATED)
                    .entity(theme)
                    .header("Access-Control-Allow-Origin", "*")
                    .header("Access-Control-Allow-Methods", "GET, POST, DELETE, PUT")
                    .allow("OPTIONS")
                    .build();
            }
        }

    @DELETE
    @Path("/themes/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response deleteTheme(@PathParam("id") Integer id) {
        Theme theme= ThemeServiceImpl.getInstance().getById(id);

        if(theme.getId() == 0) {
            return Response.status(Response.Status.NO_CONTENT)
                    .entity(theme)
                    .header("Access-Control-Allow-Origin", "*")
                    .header("Access-Control-Allow-Methods", "GET, POST, DELETE, PUT")
                    .allow("OPTIONS")
                    .build();
        }   else {
            ThemeServiceImpl.getInstance().delete(theme);
            return Response.status(Response.Status.OK)
                    .entity(theme)
                    .header("Access-Control-Allow-Origin", "*")
                    .header("Access-Control-Allow-Methods", "GET, POST, DELETE, PUT")
                    .allow("OPTIONS")
                    .build();
        }
            
    }

    private void writeToFile(InputStream uploadedInputStream, String uploadedFileLocation) {
		try {
			OutputStream out;
			int read;
			byte[] bytes = new byte[10240];

			out = new FileOutputStream(new File(uploadedFileLocation));
			while ((read = uploadedInputStream.read(bytes)) != -1) {
				out.write(bytes, 0, read);
			}
			out.flush();
			out.close();
		} catch (IOException e) {}
        
	}
 
}
