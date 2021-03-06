package epsi.scrumboard.services.impl;

import com.mongodb.BasicDBList;
import com.mongodb.BasicDBObject;
import com.mongodb.DBObject;
import epsi.scrumboard.beans.Task;
import epsi.scrumboard.mongo.Connection;
import epsi.scrumboard.services.TaskService;
import org.bson.types.ObjectId;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

/**
 * Created by Pierre on 10/04/2015.
 */
public class TaskServiceImpl implements TaskService {


    @Override
    public void addTask(String idProject, String idSprint, String idUS, Task task) {

        List<DBObject> projectForDB = Connection.find("projects", new BasicDBObject("_id", new ObjectId(idProject)), new BasicDBObject());
        DBObject project = projectForDB.get(0);
        BasicDBList sprints = (BasicDBList) project.get("sprints");
        for (String s : sprints.keySet()) {
            DBObject sprintTemp = (DBObject) sprints.get(s);
            if (sprintTemp.get("idSprint").equals(idSprint)) {
                if(sprintTemp.get("tasks") == null ) {
                    sprintTemp.put("tasks", new BasicDBList());
                }
                BasicDBList tasks = (BasicDBList) sprintTemp.get("tasks");
                String idTask = UUID.randomUUID().toString();
                BasicDBObject taskForDB = new BasicDBObject("idTask",idTask).append("name",task.getName())
                        .append("idUS",idUS);
                tasks.add(taskForDB);
            }

        }
        Connection.save("projects",project);


    }

    @Override
    public List<Task> getTasks(String idProject, String idSprint) {
        List<Task> result = new ArrayList<>();
        List<DBObject> projectForDB = Connection.find("projects", new BasicDBObject("_id", new ObjectId(idProject)), new BasicDBObject());
        DBObject project = projectForDB.get(0);
        BasicDBList sprints = (BasicDBList) project.get("sprints");
        for (String s : sprints.keySet()) {
            DBObject sprintTemp = (DBObject) sprints.get(s);
            if (sprintTemp.get("idSprint").equals(idSprint)) {
                BasicDBList tasks = (BasicDBList) sprintTemp.get("tasks");
                if (tasks != null) {
                    for (String t : tasks.keySet()) {
                        DBObject taskFromDB = (DBObject) tasks.get(t);
                        Task task = new Task();
                        task.setId(taskFromDB.get("idTask").toString());
                        task.setName(taskFromDB.get("name").toString());
                        result.add(task);
                    }
                }
            }
        }
        return result;
    }
}
