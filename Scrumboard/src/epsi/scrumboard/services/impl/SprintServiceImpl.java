package epsi.scrumboard.services.impl;

import com.mongodb.BasicDBList;
import com.mongodb.BasicDBObject;
import com.mongodb.DBObject;
import epsi.scrumboard.beans.Task;
import org.bson.types.ObjectId;
import epsi.scrumboard.beans.Sprint;
import epsi.scrumboard.mongo.Connection;
import epsi.scrumboard.services.SprintService;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;


/**
 * Created by Pierre on 10/04/2015.
 */
public class SprintServiceImpl implements SprintService {
    @Override
    public void addSprint(String idProject, Sprint sprint) {
        List<DBObject> projectForDB = Connection.find("projects", new BasicDBObject("_id", new ObjectId(idProject)), new BasicDBObject());
        DBObject project = projectForDB.get(0);
        if(project.get("sprints") == null) {
            project.put("sprints", new BasicDBList());
        }
        BasicDBList sprints = (BasicDBList) project.get("sprints");
        String idSprint = UUID.randomUUID().toString();
        BasicDBObject sprintForDB = new BasicDBObject("name",sprint.getName()).append("idSprint",idSprint);
        sprints.add(sprintForDB);
        project.put("sprints",sprints);
        Connection.save("projects",project);
    }

    @Override
    public List<Sprint> getSprints(String idProject) {
        List<Sprint> result = new ArrayList<Sprint>();
        List<DBObject> projectForDB = Connection.find("projects",new BasicDBObject("_id",new ObjectId(idProject)), new BasicDBObject());
        DBObject project = projectForDB.get(0);
        BasicDBList sprints = (BasicDBList) project.get("sprints");
        if (sprints != null) {
            for (String s : sprints.keySet()) {
                DBObject sprintFromDB = (DBObject) sprints.get(s);
                Sprint sprint = new Sprint();
                sprint.setId(sprintFromDB.get("idSprint").toString());
                sprint.setName(sprintFromDB.get("name").toString());
                ArrayList<Task> tasksOfSprint = new ArrayList<Task>();
                BasicDBList listTasks = (BasicDBList) sprintFromDB.get("tasks");
                if ( listTasks != null) {
                    for (String t : listTasks.keySet()) {
                        DBObject taskFromDB = (DBObject)listTasks.get(t);
                        Task task = new Task();
                        task.setId(taskFromDB.get("idTask").toString());
                        task.setName(taskFromDB.get("name").toString());
                        tasksOfSprint.add(task);
                    }
                    sprint.setTasks(tasksOfSprint);
                }

                result.add(sprint);
            }
        }


        return result;
    }



}



