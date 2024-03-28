package edu.ntnu.entities;


import com.fasterxml.jackson.annotation.JsonIgnore;
import java.util.Date;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import java.util.List;


@Entity
@Table(name = "quizzes")
public class Quiz{
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)

  private Long quizId;
  private String quizName;
  private String quizDescription;



  @JsonIgnore
  @ManyToOne(cascade = CascadeType.ALL)
  @JoinColumn(name = "createdBy", referencedColumnName = "username")
  private User user;



  @ManyToMany
  @JoinTable(
      name = "quiz_tags",
      joinColumns = @JoinColumn(name = "quizId"),
      inverseJoinColumns = @JoinColumn(name = "tagId")
  )
  private List<Tag> tags;

  @Column(nullable = false)

  private Date quiz_creation_date;


  public Quiz() {
  }

}


