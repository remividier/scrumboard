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

        if( project.get("userStories") == null) {
            project.put("userStories", new BasicDBList());
        }
        BasicDBList userStories = (BasicDBList) project.get("userStories");
        BasicDBObject userStory = new BasicDBObject("name",us.getName())
                .append("te",us.getTechnicalEffort())
                .append("bv",us.getBusinessValue());

        userStories.add(userStory);

        project.put("userStories",userStories);
        Connection.save("projects",project);




    }
}
