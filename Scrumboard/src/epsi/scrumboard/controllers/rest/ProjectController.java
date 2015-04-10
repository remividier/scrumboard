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

    @RequestMapping(value="/addProject", method= RequestMethod.POST)
    public void addProject(HttpServletRequest request, HttpServletResponse response) {



        String name = request.getParameter("name");
        String dod = request.getParameter("dod") ;

        Project project = new Project();

        project.setName(name);
        project.setDod(dod);
        projectService.addProject(project);



    }

}
