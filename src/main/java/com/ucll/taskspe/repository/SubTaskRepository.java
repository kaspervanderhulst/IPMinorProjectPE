package com.ucll.taskspe.repository;

import com.ucll.taskspe.domain.SubTask;
import com.ucll.taskspe.domain.Task;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SubTaskRepository extends JpaRepository<SubTask, Task> {
}
