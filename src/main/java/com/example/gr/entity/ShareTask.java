package com.example.gr.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name="share_task")
public class ShareTask {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "share_id", nullable = false)
    private Long share_id;

    @ManyToOne
    @JoinColumn(name = "id", referencedColumnName = "id")
    private Task task;

    @Column(name = "control")
    private Integer control;

    @Column(name = "shared_user_id")
    private Long shareUserId;


    public ShareTask(Task task, Long shareUserId, Integer control) {
        this.task = task;
        this.shareUserId = shareUserId;
        this.control = control;
    }
}
