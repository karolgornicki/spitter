package x.y.spitter.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import x.y.spitter.service.SpitterService;

@Controller
@RequestMapping("account")
public class AccountController {

	@Autowired
	private SpitterService spitterServiceImpl;
	
	@RequestMapping(value = "/resendPassword")
	public String showRemindPassword() {
		return "account/resendPassword";
	}
	
	@RequestMapping(value = "/resendPassword", method = RequestMethod.POST)
	public String sendEmail(@RequestParam String username, 
			@RequestParam String email, Model model) {
				
		if(spitterServiceImpl.confirmUserDetails(username, email)) {
			spitterServiceImpl.remindPassword(username, email);
		} else {
			//Provided username and email don't match.
			model.addAttribute("errorMessage", "Provided username or email address are incorrect.");
			return "account/resendPassword";
		}
		
		return "account/confirmation";
	}

}
