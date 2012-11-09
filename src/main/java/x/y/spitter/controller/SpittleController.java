package x.y.spitter.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import x.y.spitter.domain.Spitter;
import x.y.spitter.domain.Spittle;
import x.y.spitter.service.SpitterService;
import x.y.spitter.service.SpittleService;

@Controller
@RequestMapping("spittle")
public class SpittleController {

	@Autowired 
	private SpittleService spittleServiceImpl;
	@Autowired 
	private SpitterService spitterServiceImpl; 

	@RequestMapping(method = RequestMethod.POST)
	public String addSpittle(@Valid Spittle spittle, BindingResult bindingResult) {
		if(bindingResult.hasErrors()) {
			return "home";
		}
		
		String loggedUsername = SecurityContextHolder.getContext().getAuthentication().getName();
		Spitter spitter = spitterServiceImpl.getSpitterByUsername(loggedUsername);
		
		spittleServiceImpl.addSpittle(spittle, spitter);
		
		return "redirect:home";
	}
	
	@RequestMapping(value = "/delete", method = RequestMethod.POST)
	public String deleteSpittle(@RequestParam long id) {

		spittleServiceImpl.deleteSpittle(id);
		
		return "redirect:/home";
	}
}
