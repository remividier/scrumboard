package epsi.scrumboard.services.impl;

import com.mongodb.BasicDBList;
import com.mongodb.BasicDBObject;
import com.mongodb.DBObject;
import epsi.scrumboard.beans.Sprint;
import epsi.scrumboard.beans.Task;
import epsi.scrumboard.beans.UserStory;
import epsi.scrumboard.mongo.Connection;
import epsi.scrumboard.services.UserStoryService;
import org.bson.types.ObjectId;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

/**
 * Created by Pierre on 10/04/2015.
 */
public class UserStoryServiceImpl implements UserStoryService {

    @Override
    public void addUserStory(UserStory us, String idProject) {
        List<DBObject> projectForDB = Connection.find("projects", new BasicDBObject("_id", new ObjectId(idProject)), new BasicDBObject());
        DBObject project = projectForDB.get(0);
        if( project.get("userStories") == null) {
            project.put("userStories", new BasicDBList());
        }
        BasicDBList userStories = (BasicDBList) project.get("userStories");
        String idUS = UUID.randomUUID().toString();
        BasicDBObject userStory = new BasicDBObject("name",us.getName())
                .append("te",us.getTechnicalEffort())
                .append("bv",us.getBusinessValue()).append("idUS",idUS);

        userStories.add(userStory);
        project.put("userStories",userStories);
        Connection.save("projects",project);
    }

    @Override
    public List<UserStory> getUerStories(String idProject) {
        ArrayList<UserStory> result = new ArrayList<>();
        List<DBObject> projectForDB = Connection.find("projects",new BasicDBObject("_id",new ObjectId(idProject)), new BasicDBObject());
        DBObject project = projectForDB.get(0);
        BasicDBList userStories = (BasicDBList) project.get("userStories");
        for (String s : userStories.keySet()) {
            DBObject USFromDb = (DBObject) userStories.get(s);
            UserStory  US = new UserStory();
            US.setId(USFromDb.get("idUS").toString());
            US.setName(USFromDb.get("name").toString());
            US.setBusinessValue(Integer.parseInt(USFromDb.get("bv").toString()));
            US.setTechnicalEffort(Integer.parseInt(USFromDb.get("te").toString()));
            result.add(US);
        }
        return result;
    }
}
