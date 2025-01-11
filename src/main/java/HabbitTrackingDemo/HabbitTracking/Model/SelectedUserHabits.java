package HabbitTrackingDemo.HabbitTracking.Model;

import java.time.LocalDate;
import java.util.Set;
import jakarta.persistence.*;

@Entity
@Table(name = "selected_user_habits") // ✅ Correct table name
public class SelectedUserHabits {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne // ✅ Many users select habits, but one user per row
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @ManyToMany // ✅ Correct Many-to-Many mapping
    @JoinTable(
        name = "user_habit_mapping",
        joinColumns = @JoinColumn(name = "user_id"),
        inverseJoinColumns = @JoinColumn(name = "habit_id")
    )
    private Set<Habit> habits; 

    private LocalDate startDate = LocalDate.now();

    // ✅ Default constructor (JPA requires it)
    public SelectedUserHabits() {}

    // ✅ Getters and Setters
    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }

    public User getUser() {
        return user;
    }
    public void setUser(User user) {
        this.user = user;
    }

    public Set<Habit> getHabits() {
        return habits;
    }
    public void setHabits(Set<Habit> habits) {
        this.habits = habits;
    }

    public LocalDate getStartDate() {
        return startDate;
    }
    public void setStartDate(LocalDate startDate) {
        this.startDate = startDate;
    }

    @Override
    public String toString() {
        return "SelectedUserHabits [id=" + id + ", user=" + user + ", habits=" + habits + ", startDate=" + startDate + "]";
    }
}
