package com.mycompany.api.resource;

import com.mycompany.api.dao.DoctorDAO;
import com.mycompany.api.model.Doctor;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

@Path("/doctors")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class DoctorResource {
    private DoctorDAO doctorDAO = new DoctorDAO();

    @GET
    public Response getAllDoctors() {
        List<Doctor> doctors = doctorDAO.getAllDoctors();
        return Response.ok(doctors).build();
    }

    @GET
    @Path("/{id}")
    public Response getDoctorById(@PathParam("id") int id) {
        Doctor doctor = doctorDAO.getDoctorById(id);
        if (doctor != null) {
            return Response.ok(doctor).build();
        } else {
            return Response.status(Response.Status.NOT_FOUND).entity("Doctor not found with ID: " + id).build();
        }
    }

    @POST
    public Response addDoctor(Doctor doctor) {
        doctorDAO.addDoctor(doctor);
        return Response.status(Response.Status.CREATED).entity("Doctor added").build();
    }

    @PUT
    @Path("/{id}")
    public Response updateDoctor(@PathParam("id") int id, Doctor updatedDoctor) {
        Doctor existingDoctor = doctorDAO.getDoctorById(id);
        if (existingDoctor != null) {
            updatedDoctor.setId(id); // Set the ID of the updated doctor
            doctorDAO.updateDoctor(id, updatedDoctor); // Update the doctor
            return Response.ok().entity("Doctor updated").build();
        } else {
            return Response.status(Response.Status.NOT_FOUND).entity("Doctor not found with ID: " + id).build();
        }
    }

    @DELETE
    @Path("/{id}")
    public Response deleteDoctor(@PathParam("id") int id) {
        doctorDAO.deleteDoctor(id);
        return Response.ok().entity("Doctor deleted").build();
    }
}


