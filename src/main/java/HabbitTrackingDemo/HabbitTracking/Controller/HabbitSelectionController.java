package HabbitTrackingDemo.HabbitTracking.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import HabbitTrackingDemo.HabbitTracking.Model.Habit;
import HabbitTrackingDemo.HabbitTracking.Service.HabbitSelectionService;
import HabbitTrackingDemo.HabbitTracking.enumclasses.HabitType;

@RestController
@RequestMapping("/users")
public class HabbitSelectionController {

    @Autowired
    private HabbitSelectionService habitSelectionService;

    @GetMapping("/habit")
    public ResponseEntity<?> getCategoriesByType(@RequestParam HabitType type) {
        try {
            // Pass the type directly as it matches the database format
            List<String> categoryList = habitSelectionService.findbytype(type);
            return ResponseEntity.ok(categoryList);
        } catch (Exception e) {
            return ResponseEntity.status(500).body("An error occurred: " + e.getMessage());
        }
    }
    @GetMapping("/habit/getbycategory")
    public ResponseEntity<?> habitlist(@RequestParam String category) {
        try {
            List<String> habitlist = habitSelectionService.findbycategory(category);
            return ResponseEntity.ok(habitlist);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                                 .body("Error occurred: " + e.getMessage());
        }
    }	
    
    
    @GetMapping("/habit/habitname")
    public ResponseEntity<?> habitbyname(@RequestParam String habitname){
		try {

	    	List<Habit> habit=habitSelectionService.findhabitbyname(habitname);
	    	return ResponseEntity.ok(habit);
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
					.body("Error occurred: " + e.getMessage());
		}
    	
    }
   
    
    
    
    
    
    }