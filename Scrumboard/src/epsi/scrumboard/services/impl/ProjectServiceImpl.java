package epsi.scrumboard.services.impl;

import com.mongodb.BasicDBObject;
import com.mongodb.DBObject;
import epsi.scrumboard.beans.Project;
import epsi.scrumboard.beans.Sprint;
import epsi.scrumboard.beans.UserStory;
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

            ArrayList<UserStory> userStories = new ArrayList<UserStory>();
            DBObject userStoriesFromDB = (DBObject) project.get("userStories");

            int teTotal = 0 ;
            int bvTotal = 0 ;

            int teDone = 0 ;
            int bvDone = 0 ;
            System.out.println(userStoriesFromDB);
            if (userStoriesFromDB != null) {
                for (String key : userStoriesFromDB.keySet()) {
                    DBObject userStory  = (DBObject) userStoriesFromDB.get(key);
                    Object statut = userStory.get("statut");
                    if (statut != null) {
                        String statutStr = statut.toString();
                        switch (statutStr) {
                            case "done" :
                                bvDone += Integer.parseInt(userStory.get("bv").toString());
                                teDone +=  Integer.parseInt(userStory.get("te").toString());
                                break;
                            default:
                                teTotal += Integer.parseInt(userStory.get("te").toString());
                                bvTotal += Integer.parseInt(userStory.get("bv").toString());
                                break;
                        }
                    }

                }
            }


            temp.setBvDone(bvDone);
            temp.setTeDone(teDone);

            temp.setBvTotal(bvTotal);
            temp.setTeTotal(teTotal);

        }
        return result;
    }

    @Override
    public Project getProject(String idProject) {

        List<DBObject> projectForDB = Connection.find("projects",new BasicDBObject("_id",new ObjectId(idProject)), new BasicDBObject());

        DBObject project = projectForDB.get(0);

        Project result = new Project();
        ObjectId idProjectFromDB = (ObjectId) project.get("_id");
        String idProjectFromDBStr = idProjectFromDB.toHexString();
        result.setId(idProjectFromDBStr);
        result.setDod(project.get("dod").toString());
        result.setName(project.get("name").toString());

        ArrayList<UserStory> userStories = new ArrayList<UserStory>();
        DBObject userStoriesFromDB = (DBObject) project.get("userStories");

        int teTotal = 0 ;
        int bvTotal = 0 ;

        for (String key : userStoriesFromDB.keySet()) {
            DBObject userStory  = (DBObject) userStoriesFromDB.get(key);
            UserStory userStoryTemp = new UserStory();
            userStoryTemp.setName(userStory.get("name").toString());
            userStoryTemp.setBusinessValue(Integer.parseInt(userStory.get("bv").toString()));
            userStoryTemp.setTechnicalEffort(Integer.parseInt(userStory.get("te").toString()));
            userStories.add(userStoryTemp);
            System.out.println(Integer.parseInt(userStory.get("te").toString()));
            teTotal += Integer.parseInt(userStory.get("te").toString());
            bvTotal += Integer.parseInt(userStory.get("bv").toString());

        }

        result.setUserStories(userStories);
        result.setBvTotal(bvTotal);
        result.setTeTotal(teTotal);


        ArrayList<Sprint> sprints = new ArrayList<Sprint>();
        DBObject sprintsFromDb = (DBObject) project.get("sprints");
        for (String key : sprintsFromDb.keySet()) {
            DBObject sprint  = (DBObject) sprintsFromDb.get(key);
            Sprint sprintTemp = new Sprint();
            sprintTemp.setName(sprint.get("name").toString());
            sprintTemp.setId(sprint.get("idSprint").toString());
            sprints.add(sprintTemp);
        }
        result.setSprints(sprints);
        return result;
    }
}
