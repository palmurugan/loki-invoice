package com.way2invoice.bms.common.auditing.domain;

import com.way2invoice.bms.domain.Accounts;
import com.way2invoice.bms.domain.enumeration.Status;
import java.io.Serializable;
import java.time.LocalDateTime;
import javax.persistence.Column;
import javax.persistence.EntityListeners;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.MappedSuperclass;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

/**
 * @param <U>
 * @author palmuruganc
 * <p>
 * Auditing abstract class which holds basic auditing properties.
 */
@MappedSuperclass
@EntityListeners(AuditingEntityListener.class)
public abstract class Auditable<U> implements Serializable {

  private static final long serialVersionUID = 1L;

  @CreatedBy
  @Column(name = "created_by", nullable = false, updatable = false)
  private U createdBy;

  @CreatedDate
  @Column(name = "created_date", nullable = false, updatable = false)
  private LocalDateTime createdDate;

  @LastModifiedBy
  @Column(name = "updated_by")
  private U updatedBy;

  @LastModifiedDate
  @Column(name = "updated_date")
  private LocalDateTime updatedDate;

  @Enumerated(EnumType.STRING)
  @Column(name = "status", nullable = false)
  private Status status = Status.ACTIVE;

  @PrePersist
  public void prePersist() {
    if (this.status == null) {
      this.status = Status.ACTIVE;
    }
  }

  @PreUpdate
  public void preUpdate() {
    if (this.status == null) {
      this.status = Status.ACTIVE;
    }
  }

  public U getCreatedBy() {
    return createdBy;
  }

  public void setCreatedBy(U createdBy) {
    this.createdBy = createdBy;
  }

  public U getUpdatedBy() {
    return updatedBy;
  }

  public void setUpdatedBy(U updatedBy) {
    this.updatedBy = updatedBy;
  }

  public LocalDateTime getCreatedDate() {
    return createdDate;
  }

  public void setCreatedDate(LocalDateTime createdDate) {
    this.createdDate = createdDate;
  }

  public LocalDateTime getUpdatedDate() {
    return updatedDate;
  }

  public void setUpdatedDate(LocalDateTime updatedDate) {
    this.updatedDate = updatedDate;
  }

  public Status getStatus() {
    return status;
  }

  public void setStatus(Status status) {
    this.status = status;
  }

  @Override
  public String toString() {
    return "Auditable{" +
        "createdBy=" + createdBy +
        ", createdDate=" + createdDate +
        ", updatedBy=" + updatedBy +
        ", updatedDate=" + updatedDate +
        ", status=" + status +
        '}';
  }
}
