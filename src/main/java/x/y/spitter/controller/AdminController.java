package x.y.spitter.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import x.y.spitter.service.SpitterService;
import x.y.spitter.util.ShortSpitter;

@Controller
@RequestMapping("admin")
public class AdminController {

	@Autowired
	SpitterService spitterServiceImpl;
	
	@RequestMapping(value = "/main")
	public String showAdminPanel() {
		return "admin/main";
	}
	
	@RequestMapping(value = "/users")
	public String showListOfUsers(Model model) {
		
		List<ShortSpitter> users = spitterServiceImpl.getAllUsers();
		model.addAttribute("users", users);
		
		return "admin/users";
	}
	
	@RequestMapping(value = "/search")
	public String showSearchPanel() {
		return "admin/search";
	}
	
	@RequestMapping(value = "/search", method = RequestMethod.POST)
	public String searchUsers(Model model, @RequestParam String query) {
		
		List<ShortSpitter> users = spitterServiceImpl.searchSpitters(query);
		model.addAttribute("users", users);
		model.addAttribute("query", query);
		
		return "admin/users";
	}
	
	@RequestMapping(value = "/delete_user", method = RequestMethod.POST)
	public String deleteUser(Model model, @RequestParam String username, @RequestParam(required = false) String query) {
		
		//Delete the user.
		spitterServiceImpl.deleteSpitter(username);
		
		if(query != null) {
			List<ShortSpitter> users = spitterServiceImpl.searchSpitters(query);
			model.addAttribute("users", users);
			model.addAttribute("query", query);
			
			return "admin/search_results";
		} else {
			return "redirect:/admin/search";
		}
	}
}
