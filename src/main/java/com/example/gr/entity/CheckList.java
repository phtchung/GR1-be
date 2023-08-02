package com.example.gr.entity;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name="checklist")
public class CheckList {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "checklist_id", nullable = false)
    private Long checkListId;

    @ManyToOne
    @JoinColumn(name = "task_id")
    private Task task;

    @Column(name = "title", length = 70)
    private String title;

    @Column(name = "progress")
    private Integer progress;

    @Column(name = "date_end")
    private java.util.Date dateEnd;

    @Column(name = "note")
    private String note;

    public CheckList(String title, Date dateEnd, Integer progress, String note, Task task) {
        this.title = title;
        this.dateEnd = dateEnd;
        this.note = note;
        this.progress = progress;
        this.task = task;
    }
}
