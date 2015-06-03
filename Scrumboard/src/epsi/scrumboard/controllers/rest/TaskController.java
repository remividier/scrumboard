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
import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * Created by Pierre on 10/04/2015.
 */
@Controller
public class TaskController {

    @Resource
    TaskService taskService;

    @RequestMapping(value="/task", method= RequestMethod.POST)
    public @ResponseBody
    void addTaskToSprint(HttpServletRequest request, HttpServletRequest response) {

        Task task = new Task();
        String nameTask = request.getParameter("name");
        task.setName(nameTask);

        String idProject = request.getParameter("idProject").toString();
        String idSprint =  request.getParameter("idSprint").toString();
        String idUS = request.getParameter("idUserStory").toString();
        taskService.addTask(idProject,idSprint, idUS,task);



    }

    @RequestMapping(value="/task",method=RequestMethod.GET)
    public @ResponseBody
    List<Task> getTqsks ( HttpServletRequest request, HttpServletResponse response ) {

        String idPorjtect = request.getParameter("idProject").toString();
        String idSprint = request.getParameter("idSprint").toString();
        return taskService.getTasks(idPorjtect, idSprint);

    }
}
