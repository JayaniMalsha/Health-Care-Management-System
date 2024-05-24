package com.mycompany.api.resource;

import com.mycompany.api.dao.MedicalRecordDAO;
import com.mycompany.api.model.MedicalRecord;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

@Path("/medical-records")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class MedicalRecordResource {
    private MedicalRecordDAO medicalRecordDAO = new MedicalRecordDAO();

    @GET
    public Response getAllMedicalRecords() {
        List<MedicalRecord> medicalRecords = medicalRecordDAO.getAllMedicalRecords();
        return Response.ok(medicalRecords).build();
    }

    @GET
    @Path("/{id}")
    public Response getMedicalRecordById(@PathParam("id") int id) {
        MedicalRecord medicalRecord = medicalRecordDAO.getMedicalRecordById(id);
        if (medicalRecord != null) {
            return Response.ok(medicalRecord).build();
        } else {
            return Response.status(Response.Status.NOT_FOUND).entity("Medical record not found with ID: " + id).build();
        }
    }

    @POST
    public Response addMedicalRecord(MedicalRecord medicalRecord) {
        medicalRecordDAO.addMedicalRecord(medicalRecord);
        return Response.status(Response.Status.CREATED).entity("Medical record added").build();
    }

    @PUT
    @Path("/{id}")
    public Response updateMedicalRecord(@PathParam("id") int id, MedicalRecord updatedMedicalRecord) {
        MedicalRecord existingMedicalRecord = medicalRecordDAO.getMedicalRecordById(id);
        if (existingMedicalRecord != null) {
            updatedMedicalRecord.setId(id); // Set the ID of the updated medical record
            medicalRecordDAO.updateMedicalRecord(id, updatedMedicalRecord);
            return Response.ok().entity("Medical record updated").build();
        } else {
            return Response.status(Response.Status.NOT_FOUND).entity("Medical record not found with ID: " + id).build();
        }
    }

    @DELETE
    @Path("/{id}")
    public Response deleteMedicalRecord(@PathParam("id") int id) {
        medicalRecordDAO.deleteMedicalRecord(id);
        return Response.ok().entity("Medical record deleted").build();
    }
}


