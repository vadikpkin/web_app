package com.epam.rest;

import com.epam.dao.PersonDao;
import com.epam.models.Person;
import com.epam.models.Type;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.Set;


@Path("/person")
public class PersonRest {

    @GET
    @Path("get/{personId}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getPerson(@PathParam("personId") int id) {

        Person person = new PersonDao().getPerson(id);

        ObjectMapper Obj = new ObjectMapper();

        try {
            String jsonStr = Obj.writeValueAsString(person);
            return Response.status(200).entity(jsonStr).build();
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }

        return null;
    }

    @GET
    @Path("/getall")
    @Produces(MediaType.APPLICATION_JSON)
    public String getAll() {

        PersonDao dao = new PersonDao();

        Set<Person> personSet = dao.getAllPersons();

        ObjectMapper Obj = new ObjectMapper();

        StringBuilder stringBuilder = new StringBuilder();

        try {
            for (Person person : personSet) {
                stringBuilder.append(Obj.writeValueAsString(person));
            }

            return stringBuilder.toString();
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }

        return null;
    }

    @GET
    @Path("/add")
    @Produces(MediaType.APPLICATION_JSON)
    public String insertPerson(@QueryParam("name") String name, @QueryParam("surname") String surname,
                               @QueryParam("email") String email, @QueryParam("type") String type) {

        Person person = new Person(name, surname, email, Type.valueOf(type));

        PersonDao dao = new PersonDao();

        boolean insert = dao.insertPerson(person);

        return insert ? "ADDED id: " + dao.getId(person) : "FAILED";
    }

    @GET
    @Path("delete/{personId}")
    @Produces(MediaType.APPLICATION_JSON)
    public String deletePerson(@PathParam("personId") int id) {

        PersonDao dao = new PersonDao();

        boolean isDeleted = dao.deletePerson(id);

        return String.valueOf(isDeleted);

    }
}
