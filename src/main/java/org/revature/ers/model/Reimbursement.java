package org.revature.ers.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.*;

import java.util.Date;
import java.util.UUID;

@Entity
@Table
@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
@ToString
public class Reimbursement {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID reimbId;

    @Column(name = "description")
    private String description;

    @Column(name = "amount")
    private double amount;

    @Column(name = "status")
    private String status;

    @Column(updatable = false)
    private Date createdOn;

    @JsonBackReference
    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @PrePersist
    protected void onCreate() {
        status = "PENDING";
        createdOn = new Date();
    }
}
