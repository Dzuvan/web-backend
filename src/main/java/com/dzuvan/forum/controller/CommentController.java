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
import com.dzuvan.forum.model.UserModel;
import com.dzuvan.forum.service.CommentServiceImpl;
import com.dzuvan.forum.service.UserServiceImpl;
import java.time.LocalDate;
import java.util.ArrayList;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.OPTIONS;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

/**
 *
 * @author dzuvan
 */
@Path("/commentService")
public class CommentController {

    /**
     *
     * @return
     */
    @GET
    @Path("/comments")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getAllComments() {
        ArrayList<Comment> comments = CommentServiceImpl.getInstance().getAll();
        return Response.ok()
                .entity(comments)
                .header("Access-Control-Allow-Origin", "*")
                .header("Access-Control-Allow-Methods", "GET, POST, DELETE, PUT")
                .allow("OPTIONS")
                .build();
    }

    @GET
    @Path("/comments/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getCommentById(@PathParam("id") Integer id) {
        Comment comment = CommentServiceImpl.getInstance().getById(id);
        if (comment != null) {
            return Response.ok()
                    .entity(comment)
                    .header("Access-Control-Allow-Origin", "*")
                    .header("Access-Control-Allow-Methods", "GET, POST, DELETE, PUT")
                    .allow("OPTIONS")
                    .build();
        } else {
            return Response.noContent().build();
        }
    }

    /**
     *
     * @param text
     * @return
     */
    @GET
    @Path("/comments/content/{text}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getThemeByTitle(@PathParam("text") String text) {
        Comment comment = CommentServiceImpl.getInstance().getByString(text);
        if (comment != null) {
            return Response.ok()
                    .entity(comment)
                    .header("Access-Control-Allow-Origin", "*")
                    .header("Access-Control-Allow-Methods", "GET, POST, DELETE, PUT")
                    .allow("OPTIONS")
                    .build();
        } else {
            return Response.noContent().build();
        }
    }

    /**
     * Vrlo moguće da je suvišno.
     *
     * @return dozvoljene operacije za CRUD, jer CORS pravi problem.
     */
    @OPTIONS
    @Path("/comments")
    @Produces(MediaType.APPLICATION_JSON)
    public String getSupportedOperations() {
        return "<operations>GET, PUT, POST, DELETE</operations>";
    }

    /**
     *
     * @param author
     * @param parent
     * @param text
     * @param likes
     * @param dislikes
     * @param isEdited
     * @return
     */
    @POST
    @Path("/comments")
    @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
    @Produces(MediaType.APPLICATION_JSON)
    public Response addComment(@FormParam("author") String author,
            @FormParam("parent") String parent,
            @FormParam("text") String text,
            @FormParam("likes") Integer likes,
            @FormParam("dislikes") Integer dislikes,
            @FormParam("isEdited") boolean isEdited) {
        UserModel authorModel = UserServiceImpl.getInstance().getByString(author);
        Comment parentModel = CommentServiceImpl.getInstance().getByString(parent);
        Comment comment = new Comment(authorModel, LocalDate.now(), parentModel,
                text, likes, dislikes, isEdited);
        CommentServiceImpl.getInstance().addOne(comment);
        return Response.status(Response.Status.CREATED)
                .entity(comment)
                .header("Access-Control-Allow-Origin", "*")
                .header("Access-Control-Allow-Methods", "GET, POST, DELETE, PUT")
                .allow("OPTIONS")
                .build();
    }

    @PUT
    @Path("/comments")
    @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
    @Produces(MediaType.APPLICATION_JSON)
    public Response editComment(@FormParam("author") String author,
            @FormParam("parent") String parent,
            @FormParam("text") String text,
            @FormParam("likes") Integer likes,
            @FormParam("dislikes") Integer dislikes,
            @FormParam("isEdited") boolean isEdited) {

        UserModel authorModel = UserServiceImpl.getInstance().getByString(author);
        Comment parentModel = CommentServiceImpl.getInstance().getByString(parent);
        Comment comment = new Comment(authorModel, LocalDate.now(), parentModel,
                text, likes, dislikes, isEdited);

        if (comment.getId() == 0) {
            return Response.status(Response.Status.NO_CONTENT)
                    .entity(comment)
                    .header("Access-Control-Allow-Origin", "*")
                    .header("Access-Control-Allow-Methods", "GET, POST, DELETE, PUT")
                    .allow("OPTIONS")
                    .build();
        } else {
            CommentServiceImpl.getInstance().edit(comment, comment.getId()); // fishy
            return Response.status(Response.Status.CREATED)
                    .entity(comment)
                    .header("Access-Control-Allow-Origin", "*")
                    .header("Access-Control-Allow-Methods", "GET, POST, DELETE, PUT")
                    .allow("OPTIONS")
                    .build();
        }
    }

    @DELETE
    @Path("/comments/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response deleteComment(@PathParam("id") Integer id) {
        Comment comment = CommentServiceImpl.getInstance().getById(id);
        CommentServiceImpl.getInstance().delete(comment);

        return Response.status(Response.Status.OK)
                .entity(comment)
                .header("Access-Control-Allow-Origin", "*")
                .header("Access-Control-Allow-Methods", "GET, POST, DELETE, PUT")
                .allow("OPTIONS")
                .build();
    }

    @OPTIONS
    @Path("/comments/{id}")
    public Response options(@PathParam("id") int id) {
        return Response.ok(id)
                .header("Access-Control-Allow-Origin", "*")
                .header("Access-Control-Allow-Headers", "Origin, Content-Type, Accept, Authorization")
                .header("Access-Control-Allow-Credentials", "true")
                .header("Access-Control-Allow-Methods", "GET, POST, PUT, DELETE, OPTIONS, HEAD")
                .build();
    }
}
