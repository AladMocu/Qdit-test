package com.qdit.universitytest.domain;

import java.util.ArrayList;

public class Faculty {
    private long id;
    private String name;
    private ArrayList<Program> programs;

    public Faculty(long id, String name) {
        this.id = id;
        this.name = name;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public ArrayList<Program> getPrograms() {
        return programs;
    }

    public void setPrograms(ArrayList<Program> programs) {
        this.programs = programs;
    }

    public void AddProgram(Program program){
        programs.add(program);
    }
}
