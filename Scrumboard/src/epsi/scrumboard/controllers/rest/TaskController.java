package epsi.scrumboard.controllers.rest;

import epsi.scrumboard.beans.Task;
import epsi.scrumboard.services.TaskService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

/**
 * Created by Pierre on 10/04/2015.
 */
@Controller
public class TaskController {

    @Resource
    TaskService taskService;

    @RequestMapping(value="/task/{idProject}/{idSprint}", method= RequestMethod.GET)
    public @ResponseBody
    void addTaskToSprint(@PathVariable("idProject") String idProject, @PathVariable("idSprint") String idSprint, HttpServletRequest request, HttpServletRequest response) {

        Task task = new Task();
        String nameTask = request.getParameter("name");
        task.setNomTache(nameTask);


    }



}
