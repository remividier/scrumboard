package epsi.scrumboard.controllers.rest;

import epsi.scrumboard.beans.Project;
import epsi.scrumboard.services.ProjectService;
import org.json.JSONObject;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created by Pierre on 10/04/2015.
 */

@Controller
public class ProjectController {

    @Resource
    ProjectService projectService;

    @RequestMapping(value="/addProject", method= RequestMethod.GET)
    public void addProject(HttpServletRequest request, HttpServletResponse response) {


        Project project = new Project();
        project.setName("Test nom projet");
        project.setDod("Test dod");
        projectService.addProject(project);



    }

}
