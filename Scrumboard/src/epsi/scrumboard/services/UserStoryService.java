package epsi.scrumboard.services;

import epsi.scrumboard.beans.Sprint;
import epsi.scrumboard.beans.UserStory;

import java.util.List;

/**
 * Created by Pierre on 10/04/2015.
 */
public interface UserStoryService {
	
	void addUserStory (UserStory us, String idProject);

	List<UserStory> getUerStories(String idProject);


}
