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

import com.dzuvan.forum.model.Role;
import com.dzuvan.forum.model.Subforum;
import com.dzuvan.forum.model.UserModel;
import com.dzuvan.forum.service.SubforumServiceImpl;
import com.dzuvan.forum.service.UserServiceImpl;
import com.dzuvan.util.Serializer;
import java.io.InputStream;
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
@Path("/subforumService")
public class SubforumController {

    /**
     *
     * @return Response, CORS issues
     */
    @GET
    @Path("/subforums")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getAllSubofums() {
        ArrayList<Subforum> subforums = SubforumServiceImpl.getInstance().getAll();
        return Response.ok()
                .entity(subforums)
                .header("Access-Control-Allow-Origin", "*")
                .header("Access-Control-Allow-Methods", "GET, POST, DELETE, PUT")
                .header("Access-Control-Allow-Headers", "origin, content-type, accept, authorization")
                .allow("OPTIONS")
                .build();
    }

    /**
     *
     * @param id
     * @return
     */
    @GET
    @Path("/subforums/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getSubforumById(@PathParam("id") int id) {
        Subforum subforum = SubforumServiceImpl.getInstance().getById(id);
        if (subforum != null) {
            return Response.ok()
                    .entity(subforum)
                    .header("Access-Control-Allow-Origin", "*")
                    .header("Access-Control-Allow-Methods", "GET, POST, DELETE, PUT")
                    .header("Access-Control-Allow-Headers", "origin, content-type, accept, authorization")
                    .allow("OPTIONS")
                    .build();
        } else {
            return Response.status(404).entity(subforum).build();
        }
    }

    /**
     *
     * @param name
     * @return
     */
    @GET
    @Path("/subforums/subforum/{name}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getSubforumByName(@PathParam("name") String name) {
        Subforum subforum = SubforumServiceImpl.getInstance().getByString(name);

        if (subforum != null) {
            return Response.ok()
                    .entity(subforum)
                    .header("Access-Control-Allow-Origin", "*")
                    .header("Access-Control-Allow-Methods", "GET, POST, DELETE, PUT")
                    .header("Access-Control-Allow-Headers", "origin, content-type, accept, authorization")
                    .allow("OPTIONS")
                    .build();
        } else {
            return Response.status(404).entity(subforum).build();
        }
    }

    /**
     *
     * @param name
     * @param description
     * @param file
     * @param rules
     * @param fileData
     * @return
     */
    @POST
    @Path("/subforums")
    @Consumes(MediaType.MULTIPART_FORM_DATA)
    @Produces(MediaType.APPLICATION_JSON)
    public Response addSubforum(@FormDataParam("name") String name,
            @FormDataParam("description") String description,
            @FormDataParam("rules") String rules,
            @FormDataParam("icon") InputStream file,
            @FormDataParam("icon") FormDataContentDisposition fileData) {

        String location = "/home/dzuvan/NetBeansProjects/front-end/dist/" + fileData.getFileName();
         UserModel sender = UserServiceImpl.getInstance().getById(2);
         if (sender.getRole() == Role.MODERATOR || sender.getRole() == Role.ADMINISTRATOR) {
            Subforum subforum = new Subforum(name, description, rules, fileData.getFileName(), sender);
            Serializer.writeToFile(file, location);
            SubforumServiceImpl.getInstance().addOne(subforum);
            return Response.status(Response.Status.OK)
                    .entity(subforum)
                    .header("Access-Control-Allow-Origin", "*")
                    .header("Access-Control-Allow-Methods", "GET, POST, DELETE, PUT")
                    .header("Access-Control-Allow-Headers", "origin, content-type, accept, authorization")
                    .allow("OPTIONS")
                    .build();
        } else {
            return Response.status(Response.Status.UNAUTHORIZED).build();
        }
    }

    /**
     *
     * @param name
     * @param description
     * @param rules
     * @param file
     * @param fileData
     * @param id
     * @return
     */
    @PUT
    @Path("/subforums")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.MULTIPART_FORM_DATA)
    public Response updateSubforum(@FormDataParam("name") String name,
            @FormDataParam("description") String description,
            @FormDataParam("rules") String rules,
            @FormDataParam("icon") InputStream file,
            @FormDataParam("icon") FormDataContentDisposition fileData,
            @FormDataParam("moderator") int id) {

        String location = System.getProperty("user.dir") + fileData.getFileName();
        UserModel sentUser = UserServiceImpl.getInstance().getById(id);
        if (sentUser.getRole() == Role.MODERATOR || sentUser.getRole() == Role.ADMINISTRATOR) {
            Subforum subforum = new Subforum(name, description, rules, location, sentUser);

            SubforumServiceImpl.getInstance().edit(subforum, subforum.getId());
            return Response.status(Response.Status.OK)
                    .entity(subforum)
                    .header("Access-Control-Allow-Origin", "*")
                    .header("Access-Control-Allow-Methods", "GET, POST, DELETE, PUT")
                    .header("Access-Control-Allow-Headers", "origin, content-type, accept, authorization")
                    .allow("OPTIONS")
                    .build();
        } else {
            return Response.status(Response.Status.UNAUTHORIZED).build();
        }
    }

    /**
     *
     * @param id
     * @return
     */
    @DELETE
    @Path("/subforums/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response deleteSubforum(@PathParam("id") int id) {
        System.out.println("Here " + id);
        Subforum subforum = SubforumServiceImpl.getInstance().getById(id);
        System.out.println(subforum.getName());
        if (subforum.getId() != 0) {
            SubforumServiceImpl.getInstance().delete(subforum);
            return Response.status(Response.Status.OK)
                    .entity(subforum)
                    .header("Access-Control-Allow-Origin", "*")
                    .header("Access-Control-Allow-Methods", "GET, POST, DELETE, PUT")
                    .header("Access-Control-Allow-Headers", "Origin, Content-Type, Accept, Authorization")
                    .allow("OPTIONS")
                    .build();
        } else {
            return Response.status(404).entity(subforum).build();
        }
    }

    @OPTIONS
    @Path("/subforums/{id}")
    public Response options(@PathParam("id") int id) {
        return Response.ok(id)
            .header("Access-Control-Allow-Origin", "*")
            .header("Access-Control-Allow-Headers", "Origin, Content-Type, Accept, Authorization")
            .header("Access-Control-Allow-Credentials", "true")
            .header("Access-Control-Allow-Methods", "GET, POST, PUT, DELETE, OPTIONS, HEAD")
            .build();
    }
}
