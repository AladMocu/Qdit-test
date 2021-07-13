package com.qdit.universitytest.resources;

import com.qdit.universitytest.domain.Program;
import com.qdit.universitytest.locdata.DataManager;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;


public class ProgramResource {
    long fac_id;

    public ProgramResource(long fac_id) {
        this.fac_id = fac_id;
    }

    @GET
    public Response findAllPrograms() {
        return Response.ok(DataManager.programs,MediaType.APPLICATION_JSON).build();
    }

    @POST
    public Response createProgram(Program pProgram)
    {
        pProgram.setId(DataManager.programs.size()+1);
        DataManager.programs.add(pProgram);
        return Response.ok(pProgram).build();
    }

    @PUT
    public Response updateProgram(Program program)
    {
        List<Program> found = DataManager.programs.stream().filter(
                x->program.getId() == x.getId()
        ).collect(Collectors.toList());
        if(found.isEmpty())
        {
            return Response.status(Response.Status.BAD_REQUEST).entity("Program not found").build();
        }
        Program updateProgram = found.get(0);
        updateProgram.setName(program.getName());
        return Response.ok(updateProgram).build();
    }

    @DELETE
    @Path("{programID}")
    public Response deleteUser(@PathParam("programID") long programID){
        List<Program> found = DataManager.programs.stream().filter(
                x->programID == x.getId()
        ).collect(Collectors.toList());
        if(found.isEmpty())
        {
            return Response.status(Response.Status.BAD_REQUEST).entity("Program not found").build();
        }
        Program deleteProgram = found.get(0);
        DataManager.programs.remove(deleteProgram);
        return Response.noContent().build();
    }
}