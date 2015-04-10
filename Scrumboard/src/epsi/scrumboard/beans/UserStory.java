package epsi.scrumboard.beans;

/**
 * Created by eklektik on 26/03/2015.
 */
public class UserStory {

    private String name;
    private int effortTechnique;
    private int valeurMetier;
    
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
    
    public int getEffortTechnique() {
        return effortTechnique;
    }

    public void setEffortTechnique(int effortTechnique) {
        this.effortTechnique = effortTechnique;
    }
    
    public int getValeurMetier() {
        return valeurMetier;
    }

    public void setValeurMetier(int valeurMetier) {
        this.valeurMetier = valeurMetier;
    }

}
