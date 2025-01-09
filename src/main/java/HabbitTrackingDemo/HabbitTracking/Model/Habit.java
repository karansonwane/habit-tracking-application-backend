package HabbitTrackingDemo.HabbitTracking.Model;

import HabbitTrackingDemo.HabbitTracking.enumclasses.HabitType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Data
@Entity
@Table(name = "habit")
public class Habit {
	  @Id
	    @GeneratedValue(strategy = GenerationType.IDENTITY)
	    @Column(name = "habit_id")
	    private Long id;

	    @Column(nullable = false)
	    private String name;

	    
	    @Enumerated(EnumType.STRING)
	    @Column(nullable = false)
	    private HabitType type; // Enum to specify whether it's "good" or "bad"

	    @Column(length = 500) // Adjust length as needed
	    private String plan; // Plan to achieve the goal

	    @Column(nullable = false)
	    private String category; // Category of the habit, e.g., "Learning", "Exercise"

}
