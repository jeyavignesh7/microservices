package com.jv.demo.trade.backend.service.authapi.user.domain.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.util.Date;

@Entity
@Table(name = "tn_user_cred")
@Data
public class UserCredEntity {
    @Id
    private String userId;

    private String password;

    private String emailId;
    private String createdBy;

    @CreationTimestamp
    @Column(updatable = false, name = "created_on")
    private Date createdOn;

    private String modifiedBy;
    @UpdateTimestamp
    @Column(name = "modified_on")
    private String modifiedOn;

}
