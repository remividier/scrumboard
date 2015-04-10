package epsi.scrumboard.services.impl;

import com.mongodb.BasicDBObject;
import com.mongodb.DBObject;
import epsi.scrumboard.beans.Project;
import epsi.scrumboard.mongo.Connection;
import epsi.scrumboard.services.ProjectService;

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
}
