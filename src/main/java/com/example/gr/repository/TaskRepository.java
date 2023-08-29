package com.example.gr.repository;

import com.example.gr.entity.Task;
import com.example.gr.response.SendEmailRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.time.LocalDate;
import java.util.List;


public interface TaskRepository extends JpaRepository<Task, Long> {
    @Query("select s from Task s where s.user.userId = :user_id AND s.dateEnd >:today AND  s.state <> 'Done'")
    List<Task> findTaskwithEndDateAfterTodayAndNotDone(LocalDate today, Long user_id);

    @Query("select s from Task s where   s.state = 'Done'")
    List<Task> findTaskwithStateDone();

    @Query("select s from Task s where   s.isImportant = true")
    List<Task> findTaskwithStateImportant();

    @Query("SELECT t FROM Task t WHERE t.user.userId = :user_id AND t.dateStart >= :startDate AND t.state ='Todo'")
    List<Task> findTasksWithin7DaysTodo(LocalDate startDate, Long user_id);

    @Query("SELECT t FROM Task t WHERE t.user.userId = :user_id AND t.dateStart >= :startDate AND t.state ='Inprogress'")
    List<Task> findTasksWithin7DaysInprogress(LocalDate startDate, Long user_id);

    @Query("SELECT t FROM Task t WHERE t.user.userId = :user_id AND t.dateStart >= :startDate AND t.state ='Done'")
    List<Task> findTasksWithin7DaysDone(LocalDate startDate, Long user_id);

    @Query("SELECT t FROM Task t WHERE t.user.userId = :user_id AND (t.state = :state OR :state IS NULL) AND (t.dateStart = :date OR :date IS NULL)")
    List<Task> findTaskByFilter(String state, LocalDate date, Long user_id);

    @Query("SELECT t FROM Task t WHERE t.user.userId = :user_id ")
    List<Task> findTask(Long user_id);

    @Query("SELECT t FROM Task t WHERE t.id = :taskId ")
    Task findTaskById(Long taskId);


    @Query(value = """
            SELECT new com.example.gr.response.SendEmailRequest(u.email,t.taskName)
            from Task t
                     join User u on t.user.userId = u.userId
            where t.isNotify = true and t.state <> 'Done' and t.dateEnd = :tomorrow
            """)
    List<SendEmailRequest> findTaskExpire(LocalDate tomorrow);
}
