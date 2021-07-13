package com.qdit.universitytest.locdata;

import com.qdit.universitytest.domain.Campus;
import com.qdit.universitytest.domain.Faculty;
import com.qdit.universitytest.domain.Program;
import com.qdit.universitytest.domain.University;

import javax.ws.rs.core.Response;
import javax.xml.crypto.Data;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class DataManager {
    public static ArrayList<University> universities;

    public static ArrayList<Campus> campuses;

    public static ArrayList<Faculty> faculties;

    public static ArrayList<Program> programs;

    public static void AddFacultyToUniversity(Faculty faculty, long universityID){
        List<University> found = universities.stream().filter(
                x->universityID == x.getId()
        ).collect(Collectors.toList());
        University university = found.get(0);
        university.getFaculties().add(faculty);
    }

    public static void AddProgramToFaculty(Program program, long facultyID){
        List<Faculty> found = faculties.stream().filter(
                x->facultyID == x.getId()
        ).collect(Collectors.toList());
        Faculty university = found.get(0);
        university.getPrograms().add(program);
    }


    public static ArrayList<Campus> getCampusByUniversityId(long id){
        List<University> found = universities.stream().filter(
                x->id == x.getId()
        ).collect(Collectors.toList());
        return found.get(0).getCampuses();
    }

    public static ArrayList<Faculty> getFacultyByUniversityId(long id){
        List<University> found = universities.stream().filter(
                x->id == x.getId()
        ).collect(Collectors.toList());
        return found.get(0).getFaculties();
    }

    public static ArrayList<Program> getProgramByFacultyId(long id){
        List<Faculty> found = faculties.stream().filter(
                x->id == x.getId()
        ).collect(Collectors.toList());
        return found.get(0).getPrograms();
    }

    static {
        //mock universities
        universities.add(new University(1,"Universidad de los Andes"));
        universities.add(new University(1,"Universidad Santo Tomas"));

        //mock faculties


    }
}
