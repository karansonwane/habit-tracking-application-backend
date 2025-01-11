package HabbitTrackingDemo.HabbitTracking.Model;

import java.time.LocalDate;
import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "habit_logs")
public class HabitLogs {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	
	@OneToMany
	@JoinColumn(name = "user_habit_id")
	private List<SelectedUserHabits> selectedhabits;
	
	private LocalDate logDate;
	
	private String status;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public List<SelectedUserHabits> getSelectedhabits() {
		return selectedhabits;
	}

	public void setSelectedhabits(List<SelectedUserHabits> selectedhabits) {
		this.selectedhabits = selectedhabits;
	}

	public LocalDate getLogDate() {
		return logDate;
	}

	public void setLogDate(LocalDate logDate) {
		this.logDate = logDate;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	@Override
	public String toString() {
		return "HabitLogs [id=" + id + ", selectedhabits=" + selectedhabits + ", logDate=" + logDate + ", status="
				+ status + "]";
	}
}
