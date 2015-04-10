package epsi.scrumboard.beans;

import java.util.ArrayList;

/**
 * Created by eklektik on 26/03/2015.
 */
public class Sprint {

    private  String name;
    private ArrayList<Task> tasks = new ArrayList<Task>();


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public ArrayList<Task> getTasks() {
        return tasks;
    }

    public void setTasks(ArrayList<Task> tasks) {
        this.tasks = tasks;
    }

}
