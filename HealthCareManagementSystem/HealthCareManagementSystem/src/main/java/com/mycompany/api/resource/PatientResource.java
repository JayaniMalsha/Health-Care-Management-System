package com.mycompany.api.resource;

import com.mycompany.api.dao.PatientDAO;
import com.mycompany.api.model.Patient;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

@Path("/patients")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class PatientResource {
    private PatientDAO patientDAO = new PatientDAO();

    @GET
    public Response getAllPatients() {
        List<Patient> patients = patientDAO.getAllPatients();
        return Response.ok(patients).build();
    }

    @GET
    @Path("/{id}")
    public Response getPatientById(@PathParam("id") int id) {
        Patient patient = patientDAO.getPatientById(id);
        if (patient != null) {
            return Response.ok(patient).build();
        } else {
            return Response.status(Response.Status.NOT_FOUND).entity("Patient not found with ID: " + id).build();
        }
    }

    @POST
    public Response addPatient(Patient patient) {
        patientDAO.addPatient(patient);
        return Response.status(Response.Status.CREATED).entity("Patient added").build();
    }

    @PUT
    @Path("/{id}")
    public Response updatePatient(@PathParam("id") int id, Patient updatedPatient) {
        Patient existingPatient = patientDAO.getPatientById(id);
        if (existingPatient != null) {
            updatedPatient.setId(id); // Set the ID of the updated patient
            patientDAO.updatePatient(id, updatedPatient); // Update the patient
            return Response.ok().entity("Patient updated").build();
        } else {
            return Response.status(Response.Status.NOT_FOUND).entity("Patient not found with ID: " + id).build();
        }
    }   


    @DELETE
    @Path("/{id}")
    public Response deletePatient(@PathParam("id") int id) {
        patientDAO.deletePatient(id);
        return Response.ok().entity("Patient deleted").build();
    }
}


