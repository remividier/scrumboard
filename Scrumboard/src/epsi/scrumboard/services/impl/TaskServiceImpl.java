package epsi.scrumboard.services.impl;

import com.mongodb.BasicDBObject;
import com.mongodb.DBObject;
import epsi.scrumboard.beans.Task;
import epsi.scrumboard.mongo.Connection;
import epsi.scrumboard.services.TaskService;
import org.bson.types.ObjectId;

import java.util.List;

/**
 * Created by Pierre on 10/04/2015.
 */
public class TaskServiceImpl implements TaskService {


    @Override
    public void addTask(String idProject, String idSprint, Task task) {

        List<DBObject> projectForDB = Connection.find("projects", new BasicDBObject("_id", new ObjectId(idProject)), new BasicDBObject());
        DBObject project = projectForDB.get(0);




    }
}
