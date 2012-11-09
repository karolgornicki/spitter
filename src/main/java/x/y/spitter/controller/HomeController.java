package x.y.spitter.controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import x.y.spitter.domain.Spitter;
import x.y.spitter.domain.Spittle;
import x.y.spitter.service.SpitterService;
import x.y.spitter.service.SpittleService;
import x.y.spitter.util.Tuple;

@Controller
public class HomeController {

	@Autowired
	private SpitterService spitterServiceImpl;
	@Autowired
	private SpittleService spittleServiceImpl;
	private int RANGE = 5;

	private void startUp(HttpSession session) {
		
		session.setAttribute("followersNumber", spitterServiceImpl.countFollowers());
		session.setAttribute("followingNumber", spitterServiceImpl.countFollowing());
	}
	
	@RequestMapping("/home")
	public String showHome(Model model, HttpSession session) {
		
		//Start up. Get number of followers and following and store in model.
		startUp(session);
				
		//Populate the session object with a currently logged Spitter.
		String loggedUsername = SecurityContextHolder.getContext().getAuthentication().getName();
		Spitter loggedSpitter = spitterServiceImpl.getSpitterByUsername(loggedUsername);
		session.setAttribute("loggedSpitter", loggedSpitter);
		
		//Spittle, so as the user can create a new message.
		model.addAttribute("spittle", new Spittle());
		
		//Get recent spittles from people you follow.
		List<Tuple> recentSpittles = spittleServiceImpl.getRecentSpittles(loggedSpitter);
		List<Tuple> shortListToShow = new ArrayList<>();
		if(recentSpittles.size() != 0) {
			if(recentSpittles.size() <= RANGE) {
				model.addAttribute("recentSpittles", recentSpittles);
				model.addAttribute("isNext", "no");
				model.addAttribute("anchorEnd", recentSpittles.size() - 1);
			} else {
				for(int i = 0; i < RANGE; i++) {
					shortListToShow.add(recentSpittles.get(i));
				}
				model.addAttribute("recentSpittles", shortListToShow);
				model.addAttribute("isNext", "yes");
				model.addAttribute("anchorEnd", 4);
			}
			//model.addAttribute("recentSpittles", recentSpittles);
		} else {
			//User without any spittles, for instance right after the registration.
			model.addAttribute("recentSpittles", recentSpittles);
			model.addAttribute("isNext", "no");
			model.addAttribute("anchorEnd", "0");
		}
		
		model.addAttribute("isPrevious", "no");
		model.addAttribute("anchorStart", "0");
		
		return "home";
	}
	
	@RequestMapping("/login")
	public String showLoginPage() {
		return "login";
	}
	
	@RequestMapping("/registration")
	public String showRegistrationForm(Model model) {
		model.addAttribute("spitter", new Spitter());
		
		return "registerNewUser";
	}
	
	@RequestMapping(value = "/registration", method = RequestMethod.POST)
	public String registerSpitter(@Valid Spitter spitter, BindingResult bindingResult, Model model) {
			if(bindingResult.hasErrors()) {
				return "registerNewUser";
			}
			
			//Check whether the entered username is available.
			if(!spitterServiceImpl.isUsernameAvailable(spitter.getUsername())) {
				bindingResult.addError(new FieldError(bindingResult.getObjectName(), "username", "Specified username is already taken."));
				return "registerNewUser";
			}
			spitterServiceImpl.addSpitter(spitter);
			model.addAttribute("username", spitter.getUsername());

			return "registrationCompleted";
	}
	
	@RequestMapping(value = "/home", method = RequestMethod.POST)
	public String updateHomePage(HttpSession session, Model model, @RequestParam String direction, 
			@RequestParam String anchorStart, @RequestParam String anchorEnd) {
		
		int start = Integer.parseInt(anchorStart);
		int end = Integer.parseInt(anchorEnd);
		
		List<Tuple> recentSpittles = spittleServiceImpl.getRecentSpittles((Spitter) session.getAttribute("loggedSpitter"));
		List<Tuple> listToShow = new ArrayList<>();
		if(direction.equals("next")) {
			if(recentSpittles.size() - 1 >= end + RANGE) {
				for(int i = end + 1; i <= end + RANGE; i++) {
					listToShow.add(recentSpittles.get(i));
				}
				start = end + 1;
				end = end + RANGE;
			} else {
				for(int i = end + 1; i < recentSpittles.size(); i++) {
					listToShow.add(recentSpittles.get(i));
				}
				start = end + 1;
				end = recentSpittles.size() - 1;
			}
		} else {
			//Direction equals "previous"
			for(int i = start - RANGE; i < start; i++) {
				System.out.println("i = " + i);
				listToShow.add(recentSpittles.get(i));
			}
			start = start - RANGE;
			end = start + RANGE - 1;
		}
		
		model.addAttribute("spittle", new Spittle());
		model.addAttribute("recentSpittles", listToShow);
		model.addAttribute("anchorStart", start);
		model.addAttribute("anchorEnd", end);
		if(start > 0) {
			model.addAttribute("isPrevious", "yes");
		} else {
			model.addAttribute("isPrevious", "no");
		}
		if(end < recentSpittles.size() - 1) {
			model.addAttribute("isNext", "yes");
		} else {
			model.addAttribute("isNext", "no");
		}
		
		return "home";
	}
}
