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
import com.dzuvan.forum.model.UserModel;
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

@Path("/userService")
public class UserController {

    /**
     *
     * @return Response, cuz CORS

     */
    @GET
    @Path("/users")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getAllUsers(){
        ArrayList<UserModel> users = UserServiceImpl.getInstance().getAll();
        return  Response.ok()
                        .entity(users)
                        .header("Access-Control-Allow-Origin", "*")
                        .header("Access-Control-Allow-Methods", "GET, POST, DELETE, PUT")
                        .allow("OPTIONS")
                        .build();
    }

    /**
     *
     * @param id
     * @return Response, cuz CORS

     */
    @GET
    @Path("/users/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getUserById(@PathParam("id") Integer id) {
        UserModel user = UserServiceImpl.getInstance().getById(id);
        return Response.ok()
                        .entity(user)
                        .header("Access-Control-Allow-Origin", "*")
                        .header("Access-Control-Allow-Methods", "GET, POST, DELETE, PUT")
                        .allow("OPTIONS")
                        .build();
    }

    /**
     *
     * @param username
     * @return Response, cuz CORS

     */
    @GET
    @Path("/users/user/{username}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getUserByUsername(@PathParam("username") String username) {
        UserModel user = UserServiceImpl.getInstance().getByString(username);
        return Response.ok()
                        .entity(user)
                        .header("Access-Control-Allow-Origin", "*")
                        .header("Access-Control-Allow-Methods", "GET, POST, DELETE, PUT")
                        .allow("OPTIONS")
                        .build();
    }

    /**
     *
     * @param username
     * @param password
     * @param firstName
     * @param lastName
     * @param phone
     * @param email
     * @return Response, cuz CORS
     */
    @POST
    @Path("/users")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
    public Response addUser(@FormParam ("username") String username,
                            @FormParam ("password") String password,
                            @FormParam ("firstName") String firstName,
                            @FormParam ("lastName") String lastName,
                            @FormParam ("phone") String phone,
                            @FormParam ("email") String email) {
        UserModel user = new UserModel(username, password, firstName, lastName,
               Role.USER ,phone, email, LocalDate.now(), null, null, null);
        UserServiceImpl.getInstance().addOne(user);

        return Response.status(Response.Status.CREATED)
                        .entity(user)
                        .header("Access-Control-Allow-Origin", "*")
                        .header("Access-Control-Allow-Methods", "GET, POST, DELETE, PUT")
                        .allow("OPTIONS")
                        .build();
    }

    /**
     *
     * @param username
     * @param password
     * @param firstName
     * @param lastName
     * @param phone
     * @param email
     * @param role
     * @return Response cuz CORS
     */
    @PUT
    @Path("/users")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
    public Response updateUser( @FormParam ("username") String username,
                                @FormParam ("password") String password,
                                @FormParam ("firstName") String firstName,
                                @FormParam ("lastName") String lastName,
                                @FormParam ("phone") String phone,
                                @FormParam ("email") String email,
                                @FormParam ("role") Role role) {
        UserModel user = new UserModel(username, password, firstName, lastName,
              role ,phone, email, LocalDate.now(), null, null, null);
        
        if (user.getId() != -1) {
            UserServiceImpl.getInstance().edit(user, user.getId());
            return Response.status(Response.Status.OK)
                        .entity(user)
                        .header("Access-Control-Allow-Origin", "*")
                        .header("Access-Control-Allow-Methods", "GET, POST, DELETE, PUT")
                        .allow("OPTIONS")
                        .build();

        }   else {
                return Response.status(Response.Status.NO_CONTENT)
                            .header("Access-Control-Allow-Origin", "*")
                            .header("Access-Control-Allow-Methods", "GET, POST, DELETE, PUT")
                            .allow("OPTIONS")
                            .build();
            
            }
    }

    /**
     *
     * @param id
     * @return Response cuz CORS
     */
    @DELETE
    @Path("users/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response deleteUser(@PathParam("id") Integer id) {
    
        UserModel user = UserServiceImpl.getInstance().getById(id);
        if (user.getId() != -1) {
                UserServiceImpl.getInstance().delete(user);
                return Response.ok()
                        .header("Access-Control-Allow-Origin", "*")
                        .header("Access-Control-Allow-Methods", "GET, POST, DELETE, PUT")
                        .allow("OPTIONS")
                        .build();
        }   else {
             return Response.status(Response.Status.NO_CONTENT)
                            .header("Access-Control-Allow-Origin", "*")
                            .header("Access-Control-Allow-Methods", "GET, POST, DELETE, PUT")
                            .allow("OPTIONS")
                            .build();
        }
    }

    /**
     *
     * @param username
     * @param password
     * @return
     */
    @POST
    @Path("/login")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
    public Response login(@FormParam("username") String username,
                          @FormParam("password")String password) {
        UserModel user =  UserServiceImpl.getInstance().getByString(username);
        if (user != null && user.getPassword().equals(password)) {
            return Response.ok()
                        .entity(user)
                        .header("Access-Control-Allow-Origin", "*")
                        .header("Access-Control-Allow-Methods", "GET, POST, DELETE, PUT")
                        .allow("OPTIONS")
                        .build();
        }   else {
            return Response.status(Response.Status.UNAUTHORIZED)
                        .entity(user)
                        .header("Access-Control-Allow-Origin", "*")
                        .header("Access-Control-Allow-Methods", "GET, POST, DELETE, PUT")
                        .allow("OPTIONS")
                        .build();
        }
    }

    /**
     *Vrlo moguće da je suvišno.
     * @return
     */
    @OPTIONS
    @Path("/users")
    @Produces(MediaType.APPLICATION_JSON)
    public String getSupportedOperations () {
    return "<operations>GET, PUT, POST, DELETE</operations>";
    }
}
