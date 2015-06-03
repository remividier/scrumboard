package epsi.scrumboard.beans;

import java.util.ArrayList;

/**
 * Created by eklektik on 26/03/2015.
 */
public class Project {

    private String id;
    private  String name;
    private   String dod;
    private ArrayList<UserStory> userStories = new ArrayList<UserStory>();
    private ArrayList<Sprint> sprints = new ArrayList<Sprint>();
    private int teTotal ;
    private int bvTotal ;
    private int teDone;
    private int bvDone;

    public int getTeTotal() {
        return teTotal;
    }

    public void setTeTotal(int teTotal) {
        this.teTotal = teTotal;
    }

    public int getBvTotal() {
        return bvTotal;
    }

    public void setBvTotal(int bvTotal) {
        this.bvTotal = bvTotal;
    }

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

    public ArrayList<Sprint> getSprints() {
        return sprints;
    }

    public void setSprints(ArrayList<Sprint> sprints) {
        this.sprints = sprints;
    }

    public int getTeDone() {
        return teDone;
    }

    public void setTeDone(int teDone) {
        this.teDone = teDone;
    }

    public int getBvDone() {
        return bvDone;
    }

    public void setBvDone(int bvDone) {
        this.bvDone = bvDone;
    }
}
