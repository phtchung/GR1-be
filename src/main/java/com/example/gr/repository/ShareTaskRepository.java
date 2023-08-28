package com.example.gr.repository;

import com.example.gr.entity.ShareTask;
import com.example.gr.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface ShareTaskRepository extends JpaRepository<ShareTask, Long> {
    @Query("SELECT s.shareUserId  FROM ShareTask s WHERE s.task.id = :taskId ")
    List<Long> getShareUserByTaskId(Long taskId);

    @Query(value = "DELETE FROM share_task s WHERE s.id = ?1 AND s.shared_user_id = ?2", nativeQuery = true)
    @Transactional
    @Modifying
    void deleteSharedUser(Long taskId , Long userId);

}
