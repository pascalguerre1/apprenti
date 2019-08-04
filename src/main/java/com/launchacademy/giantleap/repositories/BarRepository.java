package com.launchacademy.giantleap.repositories;

import com.launchacademy.giantleap.models.Bar;
import java.util.List;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;

public interface BarRepository extends PagingAndSortingRepository<Bar, Integer> {

  @Query("SELECT b FROM Bar b WHERE b.barName LIKE CONCAT('%',:searchString,'%')")
  public List<Bar> findAllByBarName(@Param("searchString") String searchString);

}
