package epsi.scrumboard.controllers.rest;

import epsi.scrumboard.beans.Project;
import epsi.scrumboard.beans.UserStory;
import epsi.scrumboard.services.ProjectService;
import epsi.scrumboard.services.UserStoryService;
import org.json.JSONObject;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * Created by Pierre on 10/04/2015.
 */

@Controller
public class UserStoryController {

    @Resource
    UserStoryService userStoryService;

    @RequestMapping(value = "/userStory", method = RequestMethod.POST)
    public void addUserStory(HttpServletRequest request, HttpServletResponse response) {


        UserStory us = new UserStory();

        // ATTENTION A L'ID EN BASE
        String idProject = (request.getParameter("idProject").toString());

        try {
            us.setName(request.getParameter("name").toString());
            us.setTechnicalEffort(Integer.parseInt(request.getParameter("te")));
            us.setBusinessValue(Integer.parseInt(request.getParameter("bv")));
        } catch (NumberFormatException e) {
            System.out.println("coucou");
            us.setBusinessValue(0);
            us.setTechnicalEffort(0);
        }


        userStoryService.addUserStory(us, idProject);

    }

    @RequestMapping(value="/userStories",method=RequestMethod.GET)
    public @ResponseBody
    List<UserStory> getUserStories (HttpServletRequest request, HttpServletResponse response) {
        String idProject = request.getParameter("idProject");
        return userStoryService.getUerStories(idProject);
    }


}
