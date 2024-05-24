package com.mycompany.api.resource;

import com.mycompany.api.dao.PrescriptionDAO;
import com.mycompany.api.model.Prescription;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

@Path("/prescriptions")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class PrescriptionResource {
    private PrescriptionDAO prescriptionDAO = new PrescriptionDAO();

    @GET
    public Response getAllPrescriptions() {
        List<Prescription> prescriptions = prescriptionDAO.getAllPrescriptions();
        return Response.ok(prescriptions).build();
    }

    @GET
    @Path("/{id}")
    public Response getPrescriptionById(@PathParam("id") int id) {
        Prescription prescription = prescriptionDAO.getPrescriptionById(id);
        if (prescription != null) {
            return Response.ok(prescription).build();
        } else {
            return Response.status(Response.Status.NOT_FOUND).entity("Prescription not found with ID: " + id).build();
        }
    }

    @POST
    public Response addPrescription(Prescription prescription) {
        prescriptionDAO.addPrescription(prescription);
        return Response.status(Response.Status.CREATED).entity("Prescription added").build();
    }

    @PUT
    @Path("/{id}")
    public Response updatePrescription(@PathParam("id") int id, Prescription updatedPrescription) {
        Prescription existingPrescription = prescriptionDAO.getPrescriptionById(id);
        if (existingPrescription != null) {
            updatedPrescription.setId(id); // Set the ID of the updated prescription
            prescriptionDAO.updatePrescription(id, updatedPrescription);
            return Response.ok().entity("Prescription updated").build();
        } else {
            return Response.status(Response.Status.NOT_FOUND).entity("Prescription not found with ID: " + id).build();
        }
    }

    @DELETE
    @Path("/{id}")
    public Response deletePrescription(@PathParam("id") int id) {
        prescriptionDAO.deletePrescription(id);
        return Response.ok().entity("Prescription deleted").build();
    }
}
