package epsi.scrumboard.services;

import epsi.scrumboard.beans.Project;

import java.util.List;

/**
 * Created by Pierre on 10/04/2015.
 */
public interface ProjectService {


    void addProject (Project project);


    List<Project> getProjects ();


}
