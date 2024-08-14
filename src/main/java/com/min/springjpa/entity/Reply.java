package com.min.springjpa.entity;

import jakarta.persistence.*;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

@Entity
@Setter
@Getter
@ToString
public class Reply {
	
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private int id;
  
  private String name;
  private String content;
  
  @ManyToOne(optional=false)
  @OnDelete(action = OnDeleteAction.CASCADE)
  @JoinColumn(name="refid")
  private Meeting refid;
  
}
