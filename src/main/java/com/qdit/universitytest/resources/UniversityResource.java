package com.qdit.universitytest.resources;

import com.qdit.universitytest.domain.University;
import com.qdit.universitytest.locdata.DataManager;

import javax.annotation.PostConstruct;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Path("/university")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class UniversityResource {

    @GET
    public Response findAllUniversities() {
        return Response.ok(DataManager.universities,MediaType.APPLICATION_JSON).build();
    }

    @POST
    public Response createUniversity(University pUniversity)
    {
        pUniversity.setId(DataManager.universities.size()+1);
        DataManager.universities.add(pUniversity);
        return Response.ok(pUniversity).build();
    }

    @PUT
    public Response updateUniversity(University university)
    {
        List<University> found = DataManager.universities.stream().filter(
                x->university.getId() == x.getId()
        ).collect(Collectors.toList());
        if(found.isEmpty())
        {
            return Response.status(Response.Status.BAD_REQUEST).entity("University not found").build();
        }
        University updateUniversity = found.get(0);
        updateUniversity.setName(university.getName());
        return Response.ok(updateUniversity).build();
    }

    @DELETE
    @Path("{universityID}")
    public Response deleteUser(@PathParam("universityID") long universityID){
        List<University> found = DataManager.universities.stream().filter(
                x->universityID == x.getId()
        ).collect(Collectors.toList());
        if(found.isEmpty())
        {
            return Response.status(Response.Status.BAD_REQUEST).entity("University not found").build();
        }
        University deleteUniversity = found.get(0);
        DataManager.universities.remove(deleteUniversity);
        return Response.noContent().build();
    }

    //children faculties of the current university

    @GET
    @Path("{id}/faculty")
    public FacultyResource getFacultyResource(@PathParam("id") long id)
    {
        return new FacultyResource(id);
    }

    @GET
    @Path("{id}/campus")
    public CampusResource getCampusResource(@PathParam("id") long id)
    {
        return new CampusResource(id);
    }


}