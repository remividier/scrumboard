package epsi.scrumboard.beans;

import java.util.ArrayList;

/**
 * Created by eklektik on 26/03/2015.
 */
public class Sprint {

    private String id;
    private  String name;
    private ArrayList<Task> tasks = new ArrayList<Task>();


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

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
