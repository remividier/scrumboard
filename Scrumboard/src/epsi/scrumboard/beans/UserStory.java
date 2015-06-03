package epsi.scrumboard.beans;

import org.joda.time.DateTime;
import org.springframework.core.StandardReflectionParameterNameDiscoverer;

/**
 * Created by eklektik on 26/03/2015.
 */
public class UserStory {

    private String name;
    private int te;
    private int bv;
    private String id;
    private String statut;

    private static String PRODUCT_BACKLOG = "product_backlog";
    private static String PRODUCT_DONE ="product_done";
    private static String LAUNCH_AREA = "launch_area";

    private DateTime dateEntryArea = null ;

    
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

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getStatut() {
        return statut;
    }

    public void setStatut(String statut) {
        this.statut = statut;
    }
}
