package com.example.gr.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import java.sql.Date;
import java.time.Instant;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name="task")
public class Task {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @Column(name = "task_name", length = 70)
    private String taskName;

    @Column(name = "state")
    private String state;

    @Column(name = "description")
    private String description;

    @Column(name = "date_start")
    private Date dateStart;

    @Column(name = "date_end")
    private Date dateEnd;

    @Column(name = "is_notify")
    private Boolean isNotify;

    @Column(name = "is_important")
    private Boolean isImportant;

    @Column(name = "control")
    private Integer control;

    @Column(name = "create_at")
    @CreatedDate
    private Instant createAt;

    @Column(name = "update_at")
    @LastModifiedDate
    private Instant updateAt;


    public Task( User user, String taskName, String state, String description, Date dateStart, Date dateEnd, Boolean isNotify, Boolean isImportant, Integer control) {
        this.user = user;
        this.taskName = taskName;
        this.state = state;
        this.description = description;
        this.dateStart = dateStart;
        this.dateEnd = dateEnd;
        this.control = control;
        this.isImportant = isImportant;
        this.isNotify = isNotify;
    }
}
