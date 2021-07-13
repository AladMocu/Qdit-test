package com.qdit.universitytest.resources;

import com.qdit.universitytest.domain.Campus;
import com.qdit.universitytest.locdata.DataManager;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;


public class CampusResource {

    long uni_id;

    public CampusResource(long id) {
        this.uni_id=id;
    }

    @GET
    public Response findAllCampuses() {
        return Response.ok(DataManager.campuses,MediaType.APPLICATION_JSON).build();
    }

    @POST
    public Response createCampus(Campus pCampus)
    {
        pCampus.setId(DataManager.campuses.size()+1);
        DataManager.campuses.add(pCampus);
        return Response.ok(pCampus).build();
    }

    @PUT
    public Response updateCampus(Campus campus)
    {
        List<Campus> found = DataManager.campuses.stream().filter(
                x->campus.getId() == x.getId()
        ).collect(Collectors.toList());
        if(found.isEmpty())
        {
            return Response.status(Response.Status.BAD_REQUEST).entity("Campus not found").build();
        }
        Campus updateCampus = found.get(0);
        updateCampus.setName(campus.getName());
        return Response.ok(updateCampus).build();
    }

    @DELETE
    @Path("{campusID}")
    public Response deleteUser(@PathParam("campusID") long campusID){
        List<Campus> found = DataManager.campuses.stream().filter(
                x->campusID == x.getId()
        ).collect(Collectors.toList());
        if(found.isEmpty())
        {
            return Response.status(Response.Status.BAD_REQUEST).entity("Campus not found").build();
        }
        Campus deleteCampus = found.get(0);
        DataManager.campuses.remove(deleteCampus);
        return Response.noContent().build();
    }
}