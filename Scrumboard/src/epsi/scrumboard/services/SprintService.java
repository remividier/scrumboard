package epsi.scrumboard.services;

import epsi.scrumboard.beans.Sprint;
import java.util.ArrayList;

/**
 * Created by eklektik on 10/04/2015.
 */
public interface SprintService {


    public String getName();

    public void setName(String name);

    public ArrayList<Task> getTasks();

    public void setTasks(ArrayList<Task> tasks);


}