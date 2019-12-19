package com.epam.rest;

import com.epam.dao.PersonDao;
import com.epam.models.Person;
import com.epam.models.Type;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.Set;
//1. Создать слой для сервиса, который будет дергать бд, дергать бд из реста - плохо
//2. Попробывать создать с помощью реализации класса Апликейшн(Создаешь новый класс и экспендишь его, и web.
// xml прописываешь path до твоего класса и в нем path)
// 3. Если что то не так - выбрасывать 404 или 401....
// 4. Попробывать сет, которывый возвращаю обернут в responceEntity и возвращать его + разобраться с методом getallv2!
// log4j , slforg юзать

@Path("/person")
public class PersonRest {

    private PersonDao dao = new PersonDao();

    @GET
    @Path("/{personId}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getPersonJson(@PathParam("personId") int id) {

        if (dao.getPerson(id) == null)

            return Response.status(404).entity("no such person").build();

        return Response.status(200).entity(dao.getPerson(id)).build();

    }

    @GET
    @Path("/all")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getAll() {

        return Response.status(200).entity(dao.getAllPersons()).build();

    }

    @POST
    @Path("/add")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.TEXT_PLAIN)
    public Response insertPerson(Person person) {

        boolean isInserted = dao.insertPerson(person);

        return isInserted ? Response.status(201).entity("added").build() :
                Response.status(400).entity("failed").build();
    }

    @DELETE
    @Path("delete/{personId}")
    @Produces(MediaType.TEXT_PLAIN)
    public Response deletePerson(@PathParam("personId") int id) {

        boolean isDeleted = dao.deletePerson(id);

        return isDeleted ? Response.status(200).entity("deleted").build() :
                Response.status(404).entity("failed").build();

    }
}
