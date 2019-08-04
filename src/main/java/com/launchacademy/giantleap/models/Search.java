package com.launchacademy.giantleap.models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Component;

@Entity
@Getter
@Setter
@Table(name = "searches")
public class Search {
  @Id
  @SequenceGenerator(name="search_generator", sequenceName="searches_id_seq", allocationSize = 1)
  @GeneratedValue(strategy= GenerationType.SEQUENCE, generator="search_generator")
  private Integer id;

  @Column(name="search_string")
  private String searchString;

}
