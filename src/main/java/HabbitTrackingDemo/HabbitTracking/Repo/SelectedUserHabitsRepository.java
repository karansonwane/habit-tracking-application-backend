package HabbitTrackingDemo.HabbitTracking.Repo;

import org.springframework.data.jpa.repository.JpaRepository;

import HabbitTrackingDemo.HabbitTracking.Model.SelectedUserHabits;

public interface SelectedUserHabitsRepository extends JpaRepository<SelectedUserHabits, Long> { }

