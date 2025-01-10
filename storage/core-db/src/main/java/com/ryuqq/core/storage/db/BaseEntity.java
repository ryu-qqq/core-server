package com.ryuqq.core.storage.db;

import java.time.LocalDateTime;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import jakarta.persistence.Column;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.MappedSuperclass;

@MappedSuperclass
public abstract class BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    protected Long id;

    @Column(name = "DELETE_YN", nullable = false)
    protected boolean deleteYn;

    @CreationTimestamp
    @Column(name = "INSERT_TIME", nullable = false)
    private LocalDateTime insertTime;

    @UpdateTimestamp
    @Column(name = "UPDATE_TIME", nullable = false)
    private LocalDateTime updateTime;

    public Long getId() {
        return id;
    }

    public boolean isDeleteYn() {
        return deleteYn;
    }

    protected void delete(){
        this.deleteYn = true;
    }


}
