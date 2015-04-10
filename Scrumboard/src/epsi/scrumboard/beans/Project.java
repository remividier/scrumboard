package epsi.scrumboard.beans;

import java.util.ArrayList;

/**
 * Created by eklektik on 26/03/2015.
 */
public class Project {

    private  String name;
    private   String dod;
    private ArrayList<UserStory> userStories = new ArrayList<UserStory>();


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDod() {
        return dod;
    }

    public void setDod(String dod) {
        this.dod = dod;
    }

    public ArrayList<UserStory> getUserStories() {
        return userStories;
    }

    public void setUserStories(ArrayList<UserStory> userStories) {
        this.userStories = userStories;
    }
}
