package com.example.gr.repository;

import com.example.gr.entity.ShareTask;
import com.example.gr.entity.Task;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ShareTaskRepository extends JpaRepository<ShareTask, Long> {
    @Query("SELECT s.shareUserId  FROM ShareTask s WHERE s.task.id = :taskId ")
    List<Long> getShareUserByTaskId(Long taskId);

}
