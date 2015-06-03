package epsi.scrumboard.services;

import epsi.scrumboard.beans.Task;

import java.util.List;

/**
 * Created by Pierre on 10/04/2015.
 */
public interface TaskService {

    void addTask(String idProject, String idSprint, String idUS, Task task);

    List<Task> getTasks (String idProject, String idSprint);

}