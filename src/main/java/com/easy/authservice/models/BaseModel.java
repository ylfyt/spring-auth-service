package com.easy.authservice.models;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import com.fasterxml.jackson.annotation.JsonProperty;

import javax.persistence.*;
import java.util.Date;

@MappedSuperclass
public class BaseModel {
  @Id
  @JsonProperty
  @GeneratedValue(strategy = GenerationType.AUTO)
  private Long id;

  @JsonProperty
  @Column(updatable = false)
  @CreationTimestamp
  private Date createdAt;

  @JsonProperty
  @UpdateTimestamp
  private Date updatedAt;
}
