package com.qdit.universitytest.resources;

import com.qdit.universitytest.domain.Faculty;
import com.qdit.universitytest.locdata.DataManager;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class FacultyResource {

    long uni_id;

    public FacultyResource(long id) {
        this.uni_id=id;
    }

    @GET
    public Response findAllFaculties() {
        return Response.ok(DataManager.faculties,MediaType.APPLICATION_JSON).build();
    }

    @POST
    public Response createFaculty(Faculty pFaculty)
    {
        pFaculty.setId(DataManager.faculties.size()+1);
        DataManager.faculties.add(pFaculty);
        return Response.ok(pFaculty).build();
    }

    @PUT
    public Response updateFaculty(Faculty faculty)
    {
        List<Faculty> found = DataManager.faculties.stream().filter(
                x->faculty.getId() == x.getId()
        ).collect(Collectors.toList());
        if(found.isEmpty())
        {
            return Response.status(Response.Status.BAD_REQUEST).entity("Faculty not found").build();
        }
        Faculty updateFaculty = found.get(0);
        updateFaculty.setName(faculty.getName());
        return Response.ok(updateFaculty).build();
    }

    @DELETE
    @Path("{facultyID}")
    public Response deleteUser(@PathParam("facultyID") long facultyID){
        List<Faculty> found = DataManager.faculties.stream().filter(
                x->facultyID == x.getId()
        ).collect(Collectors.toList());
        if(found.isEmpty())
        {
            return Response.status(Response.Status.BAD_REQUEST).entity("Faculty not found").build();
        }
        Faculty deleteFaculty = found.get(0);
        DataManager.faculties.remove(deleteFaculty);
        return Response.noContent().build();
    }

    //child programs of the faculty
    @GET
    @Path("{id}/faculty")
    public FacultyResource getFacultyResource(@PathParam("id") long id)
    {
        return new FacultyResource(id);
    }
}