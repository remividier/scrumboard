package epsi.scrumboard.services.impl;

import com.mongodb.BasicDBObject;
import com.mongodb.DBObject;
import epsi.scrumboard.beans.UserStory;
import epsi.scrumboard.mongo.Connection;
import epsi.scrumboard.services.UserStoryService;

/**
 * Created by Pierre on 10/04/2015.
 */
public class UserStoryServiceImpl implements UserStoryService {

	@Override
    public void addUserStory(UserStory us) {

        DBObject projectForDB = new BasicDBObject();
        projectForDB.put("name",us.getName());
        projectForDB.put("te", us.getTechnicalEffort());
        projectForDB.put("bv", us.getBusinessValue());

        Connection.insert(projectForDB,"userStory");



    }
}
