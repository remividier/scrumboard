package epsi.scrumboard.controllers.rest;

import epsi.scrumboard.beans.Project;
import epsi.scrumboard.beans.UserStory;
import epsi.scrumboard.services.ProjectService;
import epsi.scrumboard.services.UserStoryService;
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

    @RequestMapping(value = "/addUserStory", method = RequestMethod.POST)
    public void addUserStory(HttpServletRequest request, HttpServletResponse response) {


        UserStory us = new UserStory();
        us.setName(request.getParameter("name").toString());

        String test = "5527e16926ff65a479687bc0";
        us.setTechnicalEffort(Integer.parseInt(request.getParameter("te")));
        us.setBusinessValue(Integer.parseInt(request.getParameter("bv")));




        userStoryService.addUserStory(us, test);

    }

}
