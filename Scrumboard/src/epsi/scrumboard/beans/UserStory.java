package epsi.scrumboard.beans;

/**
 * Created by eklektik on 26/03/2015.
 */
public class UserStory {

    private String name;
    private int technicalEffort;
    private int businessValue;
    
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
    
    public int getTechnicalEffort() {
        return effortTechnique;
    }

    public void setTechnicalEffort(int technicalEffort) {
        this.technicalEffort = technicalEffort;
    }
    
    public int getBusinessValue() {
        return businessValue;
    }

    public void setBusinessValue(int businessValue) {
        this.businessValue = businessValue;
    }

}
