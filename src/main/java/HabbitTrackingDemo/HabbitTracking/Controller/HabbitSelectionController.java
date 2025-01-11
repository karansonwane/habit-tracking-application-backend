package HabbitTrackingDemo.HabbitTracking.Controller;

import java.util.HashSet;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import HabbitTrackingDemo.HabbitTracking.Model.Habit;
import HabbitTrackingDemo.HabbitTracking.Model.SelectedUserHabits;
import HabbitTrackingDemo.HabbitTracking.Model.User;
import HabbitTrackingDemo.HabbitTracking.Repo.SelectedUserHabitsRepository;
import HabbitTrackingDemo.HabbitTracking.Service.HabbitSelectionService;
import HabbitTrackingDemo.HabbitTracking.Service.userService;
import HabbitTrackingDemo.HabbitTracking.enumclasses.HabitType;

@RestController
@RequestMapping("/users")
public class HabbitSelectionController {
	
	@Autowired
	private userService userService;
	
	@Autowired 
	
	private SelectedUserHabitsRepository selectedUserHabitsRepository;

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
   
    @PostMapping("/{userId}/select")
    public ResponseEntity<String> selectHabit(@PathVariable long userId, @RequestBody Habit habit) {
        try {
            // Fetch user from DB
            User user = userService.getUserById(userId);
            if (user == null) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("User not found");
            }

            // Validate habit ID before fetching
            if (habit.getId() == null) {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Habit ID cannot be null");
            }

            // Fetch habit from DB (Ensure getHabitById returns a single Habit)
            Habit existingHabit = habitSelectionService.getHabitById(habit.getId());
            if (existingHabit == null) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Habit not found");
            }

            // Create SelectedUserHabits object
            SelectedUserHabits userHabits = new SelectedUserHabits();
            userHabits.setUser(user);
            userHabits.setHabits(new HashSet<>(List.of(existingHabit)));

            // Save to DB
            selectedUserHabitsRepository.save(userHabits);

            return ResponseEntity.ok("Habit selected successfully!");

        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Error occurred: " + e.getMessage());
        }
    } 
    
    
    
    
    }