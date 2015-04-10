package epsi.scrumboard.services;

import epsi.scrumboard.beans.Task;

/**
 * Created by Pierre on 10/04/2015.
 */
public interface TaskService {

    void addTask(String idProject, String idSprint, Task task);

}