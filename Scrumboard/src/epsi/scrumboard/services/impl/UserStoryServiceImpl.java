package epsi.scrumboard.services.impl;

import com.mongodb.BasicDBList;
import com.mongodb.BasicDBObject;
import com.mongodb.DBObject;
import epsi.scrumboard.beans.UserStory;
import epsi.scrumboard.mongo.Connection;
import epsi.scrumboard.services.UserStoryService;
import org.bson.types.ObjectId;

import java.util.List;

/**
 * Created by Pierre on 10/04/2015.
 */
public class UserStoryServiceImpl implements UserStoryService {

	@Override
    public void addUserStory(UserStory us, String idProject) {

        List<DBObject> projectForDB = Connection.find("projects",new BasicDBObject("_id",new ObjectId(idProject)), new BasicDBObject());


        DBObject project = projectForDB.get(0);


        DBObject userStories = (DBObject) project.get("userStories");
        if(userStories == null) {
            project.put("userStories", new BasicDBObject());
        }
        userStories = (DBObject) project.get("userStories");
       userStories.put("name",us.getName());
        userStories.put("te", us.getTechnicalEffort());
        userStories.put("bv", us.getBusinessValue());

        project.put("userStories",userStories);
        Connection.save("projects",project);




    }
}
