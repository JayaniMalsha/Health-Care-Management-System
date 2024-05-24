/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.api.resource;

import com.mycompany.api.dao.AppointmentDAO;
import com.mycompany.api.model.Appointment;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

@Path("/appointments")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class AppointmentResource {
    private AppointmentDAO appointmentDAO = new AppointmentDAO();

    @GET
    public Response getAllAppointments() {
        List<Appointment> appointments = appointmentDAO.getAllAppointments();
        return Response.ok(appointments).build();
    }

    @GET
    @Path("/{id}")
    public Response getAppointmentById(@PathParam("id") int id) {
        Appointment appointment = appointmentDAO.getAppointmentById(id);
        if (appointment != null) {
            return Response.ok(appointment).build();
        } else {
            return Response.status(Response.Status.NOT_FOUND).entity("Appointment not found with ID: " + id).build();
        }
    }

    @POST
    public Response addAppointment(Appointment appointment) {
        appointmentDAO.addAppointment(appointment);
        return Response.status(Response.Status.CREATED).entity("Appointment added").build();
    }

    @PUT
    @Path("/{id}")
    public Response updateAppointment(@PathParam("id") int id, Appointment updatedAppointment) {
        Appointment existingAppointment = appointmentDAO.getAppointmentById(id);
        if (existingAppointment != null) {
            updatedAppointment.setId(id); // Set the ID of the updated appointment
            appointmentDAO.updateAppointment(id, updatedAppointment);
            return Response.ok().entity("Appointment updated").build();
        } else {
            return Response.status(Response.Status.NOT_FOUND).entity("Appointment not found with ID: " + id).build();
        }
    }

    @DELETE
    @Path("/{id}")
    public Response deleteAppointment(@PathParam("id") int id) {
        appointmentDAO.deleteAppointment(id);
        return Response.ok().entity("Appointment deleted").build();
    }
}
