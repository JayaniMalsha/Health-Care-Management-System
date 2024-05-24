package com.mycompany.api.resource;

import com.mycompany.api.dao.PersonDAO;
import com.mycompany.api.model.Person;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

@Path("/persons")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class PersonResource {
    private PersonDAO personDAO = new PersonDAO();

    @GET
    public Response getAllPersons() {
        List<Person> persons = personDAO.getAllPersons();
        return Response.ok(persons).build();
    }

    @GET
    @Path("/{id}")
    public Response getPersonById(@PathParam("id") int id) {
        Person person = personDAO.getPersonById(id);
        if (person != null) {
            return Response.ok(person).build();
        } else {
            return Response.status(Response.Status.NOT_FOUND).entity("Person not found with ID: " + id).build();
        }
    }

    @POST
    public Response addPerson(Person person) {
        personDAO.addPerson(person);
        return Response.status(Response.Status.CREATED).entity("Person added").build();
    }

    @PUT
    @Path("/{id}")
    public Response updatePerson(@PathParam("id") int id, Person updatedPerson) {
        Person existingPerson = personDAO.getPersonById(id);
        if (existingPerson != null) {
            updatedPerson.setId(id);
            personDAO.updatePerson(id, updatedPerson);
            return Response.ok().entity("Person updated").build();
        } else {
            return Response.status(Response.Status.NOT_FOUND).entity("Person not found with ID: " + id).build();
        }
    }

    @DELETE
    @Path("/{id}")
    public Response deletePerson(@PathParam("id") int id) {
        Person existingPerson = personDAO.getPersonById(id);
        if (existingPerson != null) {
            personDAO.deletePerson(id);
            return Response.ok().entity("Person deleted").build();
        } else {
            return Response.status(Response.Status.NOT_FOUND).entity("Person not found with ID: " + id).build();
        }
    }
}


