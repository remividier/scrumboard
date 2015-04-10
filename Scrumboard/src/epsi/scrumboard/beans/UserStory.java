package epsi.scrumboard.beans;

/**
 * Created by eklektik on 26/03/2015.
 */
public class UserStory {

    private String name;
    private int te;
    private int bv;
    
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
    
    public int getTechnicalEffort() {
        return te;
    }

    public void setTechnicalEffort(int technicalEffort) {
        this.te = technicalEffort;
    }
    
    public int getBusinessValue() {
        return bv;
    }

    public void setBusinessValue(int businessValue) {
        this.bv = businessValue;
    }

}
