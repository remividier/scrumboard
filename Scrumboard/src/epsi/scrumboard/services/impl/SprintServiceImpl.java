package epsi.scrumboard.services.impl;

import com.mongodb.BasicDBObject;
import com.mongodb.DBObject;
import org.bson.types.ObjectId;
import epsi.scrumboard.beans.Sprint;
import epsi.scrumboard.mongo.Connection;
import epsi.scrumboard.services.SprintService;
import java.util.ArrayList;
import java.util.List;


/**
 * Created by Pierre on 10/04/2015.
 */
public class SprintServiceImpl implements SprintService {

    @Override
    public void addSprint(int idProject, Sprint sprint) {

        DBObject sprinttForDB = new BasicDBObject();

        if(sprinttForDB.get("sprint") == null){
            sprinttForDB.put("name",sprint.getName());
            sprinttForDB.put("sprint",sprint.getName());
        } else {
            sprinttForDB.put("name",sprint.getName());
        }
// To FINISH
        Connection.insert(sprinttForDB, "sprints");

    }

}



