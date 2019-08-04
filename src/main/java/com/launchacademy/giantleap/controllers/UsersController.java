package com.launchacademy.giantleap.controllers;

import com.launchacademy.giantleap.models.Bar;
import com.launchacademy.giantleap.models.User;
import com.launchacademy.giantleap.repositories.BarRepository;
import com.launchacademy.giantleap.repositories.UserRepository;
import com.launchacademy.giantleap.security.SecurityService;
import com.launchacademy.giantleap.security.UserService;
import java.security.Principal;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class UsersController {
  private final UserRepository userRepo;

  @Autowired
  private UserService userService;

  @Autowired
  private SecurityService securityService;

  @Autowired
  private UsersController(UserRepository userRepo) {
    this.userRepo = userRepo;
  }

  @GetMapping("/registration")
  public String registration(Model model) {
    model.addAttribute("userForm", new User());
    Authentication auth = SecurityContextHolder.getContext().getAuthentication();
    return "security/registration";
  }

  @PostMapping("/registration")
  public String registration(@ModelAttribute("userForm") @Valid User userForm, BindingResult bindingResult) {
    if (bindingResult.hasErrors()) {
      return "security/registration";
    }

    if(userForm.getUserPic().trim().equals("")){
      userForm.setUserPic("https://cdn1.iconfinder.com/data/icons/ninja-things-1/720/ninja-background-512.png");
    }

    userService.save(userForm);
    securityService.autoLogin(userForm.getUsername(), userForm.getPassword());

    return "redirect:/welcome";
  }

  @GetMapping("/login")
  public String login(Model model, String error, String logout) {
    if (error != null)
      model.addAttribute("error", "Your username and password is invalid.");
    if (logout != null)
      model.addAttribute("message", "You have been logged out successfully.");

    model.addAttribute("userLogin", new User());
    return "security/login";
  }

  @GetMapping({"/welcome"})
  public String welcome(Model model, Authentication authentication, @AuthenticationPrincipal UserDetails currentUser) {
    User loggedUsed = userRepo.findByUsername(currentUser.getUsername());
    model.addAttribute("currentUser", loggedUsed);
    return "root/welcome";
  }

  @GetMapping("/editProfile")
  public String getNew(@ModelAttribute User user, Model model) {
    model.addAttribute("user", user);
    return "root/edit";
  }

}