package HabbitTrackingDemo.HabbitTracking.Service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import HabbitTrackingDemo.HabbitTracking.Model.Habit;
import HabbitTrackingDemo.HabbitTracking.Repo.HabbitSelectionRepo;
import HabbitTrackingDemo.HabbitTracking.enumclasses.HabitType;
import customeExeptions.response.LoginResponse;

@Service
public class HabbitSelectionService {

    @Autowired
    private HabbitSelectionRepo habitRepo;

    public List<String> findbytype(HabitType type) {
        List<String> categories = habitRepo.findbytype(type);
        if (categories.isEmpty()) {
            throw new RuntimeException("No categories found for the provided habit type: " + type);
        }
        return categories;
    }
    
    public List<String> findbycategory(String category){
    	List<String> habitname= habitRepo.findbycategory(category);
		
    	if(habitname.isEmpty()) {
    		throw new RuntimeException(" no habits found in the database for the provided type :"+category);
    	}
    	return habitname;
    	
    }
    
    
    
    public List<Habit> findhabitbyname(String habitname){
    	List<Habit> habit=habitRepo.findbyhabitname(habitname);
    	if(habit.isEmpty()) {
    		throw new RuntimeException("no habit found in database for the provided habit name");
    	}
    	return habit;
    }
    

    public Habit getHabitById(Long id) {
        return habitRepo.findById(id)
                .orElseThrow(() -> new RuntimeException("No habit found in database for the provided habit ID"));
    }

}
