package com.ucll.taskspe.repository;

import com.ucll.taskspe.domain.Task;
import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.JpaRepository;


@Repository
public interface TaskRepository extends JpaRepository<Task, Long> {
}
