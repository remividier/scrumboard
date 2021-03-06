package epsi.scrumboard.controllers.rest;

import epsi.scrumboard.beans.Sprint;
import epsi.scrumboard.services.SprintService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import sun.net.httpserver.HttpServerImpl;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * Created by Pierre on 10/04/2015.
 */
@Controller
public class SprintController {

    @Resource
    SprintService sprintService;

    @RequestMapping(value="/sprint", method= RequestMethod.POST)
    public @ResponseBody void addSprint (HttpServletRequest request, HttpServletResponse response) {
        String nameSprint = request.getParameter("name");
        Sprint sprint = new Sprint();
        String idProject = request.getParameter("idProject").toString();
        sprint.setName(nameSprint);
        sprintService.addSprint(idProject,sprint);
    }

    @RequestMapping(value="/sprint", method=RequestMethod.GET)
    public @ResponseBody
    List<Sprint> getSprints(HttpServletRequest request, HttpServletResponse response) {
        String idProject = request.getParameter("idProject").toString();
        return sprintService.getSprints(idProject);
    }









}
