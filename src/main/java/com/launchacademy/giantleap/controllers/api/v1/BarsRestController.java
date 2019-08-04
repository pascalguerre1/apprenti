package com.launchacademy.giantleap.controllers.api.v1;

import com.launchacademy.giantleap.models.Bar;
import com.launchacademy.giantleap.models.Review;
import com.launchacademy.giantleap.models.Search;
import com.launchacademy.giantleap.repositories.BarRepository;
import com.launchacademy.giantleap.repositories.SearchRepository;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping
public class BarsRestController {
  private final BarRepository barRepo;
  private final SearchRepository searchRepo;

  @Autowired
  private BarsRestController(BarRepository barRepo, SearchRepository searchRepo) {
    this.barRepo = barRepo;
    this.searchRepo = searchRepo;
  }

  @GetMapping("/api/v1/bars")
  public Iterable<Bar> getAll() {
    return barRepo.findAll();
  }

  @GetMapping("/api/v1/bars/{id}")
  public Optional<Bar> getOne(@PathVariable Integer id) {
    return barRepo.findById(id);
  }

  @NoArgsConstructor
  private class BarObjectNotFoundException extends RuntimeException {};
  @ControllerAdvice
  private class BarObjectNotFoundAdvice {
    @ResponseBody
    @ExceptionHandler(BarObjectNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    String barObjectNotFoundHandler(BarObjectNotFoundException ex) {
      return ex.getMessage();
    }
  }

  @NoArgsConstructor
  private class SearchObjectNotFoundException extends RuntimeException {};
  @ControllerAdvice
  private class SearchObjectNotFoundAdvice {
    @ResponseBody
    @ExceptionHandler(SearchObjectNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    String searchObjectNotFoundHandler(SearchObjectNotFoundException ex) {
      return ex.getMessage();
    }
  }

  @PostMapping("/api/v1/bars/remove/{id}")
  public void removeOne(@PathVariable Integer id) {
    Bar bar = barRepo.findById(id).orElseThrow(() -> new BarObjectNotFoundException());
    barRepo.delete(bar);
  }

  @GetMapping("/api/v1/bars/searchString")
  public List<Bar> searchResultBars() {
    Search search = searchRepo.findById(1).orElseThrow(() -> new SearchObjectNotFoundException());
    List<Bar> barResults = barRepo.findAllByBarName(search.getSearchString());
    return barResults;
  }

  @PostMapping("/api/v1/bars/searchString")
  public void searchBars(@RequestBody Search searchString) {
    Search search = searchRepo.findById(1).orElseThrow(() -> new SearchObjectNotFoundException());
    search.setSearchString(searchString.getSearchString());
    searchRepo.save(search);
  }

  @GetMapping("/api/v1/search")
  public Iterable<Search> searches() {
    return searchRepo.findAll();
  }

}