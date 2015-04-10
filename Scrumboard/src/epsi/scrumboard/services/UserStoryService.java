package epsi.scrumboard.services;

import epsi.scrumboard.beans.UserStory;

/**
 * Created by Pierre on 10/04/2015.
 */
public interface UserStoryService {
	
	void addUserStory (UserStory us, String idProject);

}
