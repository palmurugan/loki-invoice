package com.way2invoice.bms.domain;

import com.way2invoice.bms.domain.enumeration.SequenceType;
import java.io.Serializable;
import java.util.Objects;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

/**
 * A Sequence Domain
 *
 * @author palmuruganc
 */
@Entity
@Table(name = "sequence")
public class Sequence implements Serializable {

  private static final long serialVersionUID = 1L;

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @NotNull
  @Column(name = "client_id", nullable = false)
  private Long clientId;

  @NotNull
  @Enumerated(EnumType.STRING)
  @Column(name = "type", nullable = false)
  private SequenceType type;

  @NotNull
  @Column(name = "sequence_id")
  private Long sequenceId;

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public Long getClientId() {
    return clientId;
  }

  public void setClientId(Long clientId) {
    this.clientId = clientId;
  }

  public SequenceType getType() {
    return type;
  }

  public void setType(SequenceType type) {
    this.type = type;
  }

  public Long getSequenceId() {
    return sequenceId;
  }

  public void setSequenceId(Long sequenceId) {
    this.sequenceId = sequenceId;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    Sequence sequence = (Sequence) o;
    return Objects.equals(id, sequence.id) &&
        Objects.equals(clientId, sequence.clientId) &&
        Objects.equals(type, sequence.type) &&
        Objects.equals(sequenceId, sequence.sequenceId);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id, clientId, type, sequenceId);
  }

  @Override
  public String toString() {
    return "Sequence{" +
        "id=" + id +
        ", clientId=" + clientId +
        ", type='" + type + '\'' +
        ", sequenceId=" + sequenceId +
        '}';
  }
}
