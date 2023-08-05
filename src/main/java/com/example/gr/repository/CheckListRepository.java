package com.example.gr.repository;

import com.example.gr.entity.CheckList;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface CheckListRepository extends JpaRepository<CheckList , Long> {

    @Query("select c from CheckList c where c.task.id = :taskId")
    List<CheckList> findAllCheckList(Long taskId);
}
