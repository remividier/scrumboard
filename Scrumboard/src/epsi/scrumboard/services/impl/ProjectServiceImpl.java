package epsi.scrumboard.services.impl;

import com.mongodb.BasicDBObject;
import com.mongodb.DBObject;
import epsi.scrumboard.beans.Project;
import epsi.scrumboard.mongo.Connection;
import epsi.scrumboard.services.ProjectService;
import org.bson.types.ObjectId;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Pierre on 10/04/2015.
 */
public class ProjectServiceImpl implements ProjectService {


    @Override
    public void addProject(Project project) {

        DBObject projectForDB = new BasicDBObject();
        projectForDB.put("name",project.getName());
        projectForDB.put("dod", project.getDod());

        Connection.insert(projectForDB,"projects");

    }

    @Override
    public List<Project> getProjects() {

        List<Project> result = new ArrayList<Project>();
        List<DBObject> projectsFromDB = Connection.find("projects",new BasicDBObject(), new BasicDBObject());
        for (DBObject project : projectsFromDB) {
            Project temp = new Project();
            temp.setName(project.get("name").toString());
            ObjectId idProject = (ObjectId) project.get("_id");
            String objectIdForProject  = idProject.toHexString();
            temp.setId(objectIdForProject);
            result.add(temp);
        }
        return result;

    }
}
