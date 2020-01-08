package com.epam.services;

import com.epam.dao.PersonDao;
import com.epam.models.Person;

import javax.ws.rs.core.Response;

public class PersonService {

    PersonDao dao = new PersonDao();

    public Response insertPerson(Person person) {

        boolean isInserted = dao.insertPerson(person);

        if (isInserted) person.setId(dao.getId(person));

        return isInserted ? Response.status(201).entity(person).build() :
                Response.status(400).build();
    }

    public Response getPerson(int id) {

        if (dao.getPerson(id) == null)

            return Response.status(404).build();

        return Response.status(200).entity(dao.getPerson(id)).build();
    }

    public Response getAllPersons() {

        if (dao.getAllPersons().isEmpty()) return Response.status(404).build();

        return Response.status(200).entity(dao.getAllPersons()).build();

    }

    public Response deletePerson(int id) {

        boolean isDeleted = dao.deletePerson(id);

        return isDeleted ? Response.status(204).build() :
                Response.status(400).build();
    }


}
