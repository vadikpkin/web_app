package com.epam.rest;

import com.epam.models.Person;
import com.epam.services.PersonService;


import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("/person")
public class PersonRest {

    PersonService personService = new PersonService();

    @GET
    @Path("/{personId}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getPersonJson(@PathParam("personId") int id) {

        return personService.getPerson(id);

    }

    @GET
    @Path("/all")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getAll() {

        return personService.getAllPersons();

    }

    @POST
    @Path("/add")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response insertPerson(Person person) {

        return personService.insertPerson(person);

    }

    @DELETE
    @Path("/{personId}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response deletePerson(@PathParam("personId") int id) {

        return personService.deletePerson(id);

    }

}
