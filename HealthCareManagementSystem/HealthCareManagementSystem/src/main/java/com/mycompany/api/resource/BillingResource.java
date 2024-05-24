/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.api.resource;

import com.mycompany.api.dao.BillingDAO;
import com.mycompany.api.model.Billing;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

@Path("/bills")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class BillingResource {
    private BillingDAO billingDAO = new BillingDAO();

    @GET
    public Response getAllBills() {
        List<Billing> bills = billingDAO.getAllBills();
        return Response.ok(bills).build();
    }

    @GET
    @Path("/{id}")
    public Response getBillById(@PathParam("id") int id) {
        Billing bill = billingDAO.getBillById(id);
        if (bill != null) {
            return Response.ok(bill).build();
        } else {
            return Response.status(Response.Status.NOT_FOUND).entity("Bill not found with ID: " + id).build();
        }
    }

    @POST
    public Response addBill(Billing bill) {
        billingDAO.addBill(bill);
        return Response.status(Response.Status.CREATED).entity("Bill added").build();
    }

    @PUT
    @Path("/{id}")
    public Response updateBill(@PathParam("id") int id, Billing updatedBill) {
        Billing existingBill = billingDAO.getBillById(id);
        if (existingBill != null) {
            updatedBill.setId(id); // Set the ID of the updated bill
            billingDAO.updateBill(id, updatedBill);
            return Response.ok().entity("Bill updated").build();
        } else {
            return Response.status(Response.Status.NOT_FOUND).entity("Bill not found with ID: " + id).build();
        }
    }

    @DELETE
    @Path("/{id}")
    public Response deleteBill(@PathParam("id") int id) {
        billingDAO.deleteBill(id);
        return Response.ok().entity("Bill deleted").build();
    }

    @PUT
    @Path("/pay/{id}")
    public Response markBillAsPaid(@PathParam("id") int id) {
        billingDAO.markBillAsPaid(id);
        return Response.ok().entity("Bill marked as paid").build();
    }

    @PUT
    @Path("/unpay/{id}")
    public Response markBillAsUnpaid(@PathParam("id") int id) {
        billingDAO.markBillAsUnpaid(id);
        return Response.ok().entity("Bill marked as unpaid").build();
    }
}

