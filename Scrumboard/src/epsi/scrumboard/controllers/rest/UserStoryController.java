package epsi.scrumboard.controllers.rest;

import epsi.scrumboard.beans.UserProject;
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
public class UserStoryController {

    @Resource
    UserStoryService userStoryService;

    @RequestMapping(value="/addUserStory", method= RequestMethod.GET)
    public void addUserStory(HttpServletRequest request, HttpServletResponse response) {


        UserStory us = new UserStory();
        us.setName(request.getParameter('name'));
        us.setTechnicalEffort(request.getParameter('technicalEffort'));
        us.setBusinessValue(request.getParameter('bv'));
        userStoryService.addUserStory(us);

    }

}
