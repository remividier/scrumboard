package epsi.scrumboard.services;

import epsi.scrumboard.beans.Sprint;

import java.util.List;

/**
 * Created by eklektik on 10/04/2015.
 */
public interface SprintService {

    void addSprint(String idProject, Sprint sprint);


    List<Sprint> getSprints(String idProject);
}