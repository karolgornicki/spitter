package x.y.spitter.controller;

import java.io.File;
import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.apache.commons.io.FileUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import x.y.spitter.domain.Spitter;
import x.y.spitter.service.SpitterService;
import x.y.spitter.util.EditSpitter;
import x.y.spitter.util.ImageUploadException;
import x.y.spitter.util.ShortSpitter;

@Controller
@RequestMapping("spitters")
public class SpitterController {

	@Autowired
	private SpitterService spitterServiceImpl;
	
	@RequestMapping(value = "/search", method = RequestMethod.GET)
	public String showSearchResults(Model model, @RequestParam String query) {
		
		List<ShortSpitter> users = spitterServiceImpl.searchSpitters(query);
		model.addAttribute("spitters", users);
		
		return "spitters/search";
	}

	@RequestMapping(value = "/followers")
	public String showFollowers(Model model) {
		
		List<ShortSpitter> followersList = spitterServiceImpl.getSpittersWhoFollowMe();
		model.addAttribute("followers", followersList);
		
		return "spitters/followers";
	}
	
	@RequestMapping(value = "/following")
	public String showFollowing(Model model) {
		
		List<ShortSpitter> followingList = spitterServiceImpl.getSpittersIFollow();
		model.addAttribute("following", followingList);
		
		return "spitters/following";
	}
	
	@RequestMapping(method = RequestMethod.GET, params = "new")
	public String showRegistrationForm(Model model) {
		model.addAttribute("spitter", new Spitter());
		return "spitters/registrationForm";
	}

	@RequestMapping(value = "/edit", method = RequestMethod.GET) 
	public String showEdit(Model model, HttpSession session) {
		
		Spitter loggedSpitter = (Spitter) session.getAttribute("loggedSpitter");
		EditSpitter editSpitter = new EditSpitter();
		editSpitter.setFullName(loggedSpitter.getFullName());
		editSpitter.setEmail(loggedSpitter.getEmail());
		editSpitter.setDescription(loggedSpitter.getDescription());
		model.addAttribute("editSpitter", editSpitter);

		return "spitters/edit";
	}

	@RequestMapping(value = "/edit", method = RequestMethod.POST)
	public String editSpitter(@Valid EditSpitter editSpitter, 
			BindingResult bindingResult,
			@RequestParam(value="file", required=false) MultipartFile image, HttpSession session) {
		
		if(bindingResult.hasErrors()) {
			return "spitters/edit";
		}
		Spitter loggedSpitter = (Spitter) session.getAttribute("loggedSpitter");
		String realPath = session.getServletContext().getRealPath("/resources");
		
		try {
			if(!image.isEmpty()) {
				validateImage(image);
				
				saveImage(loggedSpitter.getId() + ".jpg", image, realPath);
			}
		} catch(ImageUploadException e) {
			bindingResult.reject(e.getMessage());
			return "spitters/edit";
		}

		spitterServiceImpl.updateSpitter(editSpitter);
		
		String username = loggedSpitter.getUsername();

		return "redirect:/spitters/" + username;
	}
	
	private void saveImage(String filename, MultipartFile image, String realPath) throws ImageUploadException {

		try {
			File file = new File(realPath + "/avatars/" + filename);
			FileUtils.writeByteArrayToFile(file, image.getBytes());
		} catch(IOException e) {
			throw new ImageUploadException("Unable to upload image", e);
		}
	}

	private void validateImage(MultipartFile image) throws ImageUploadException {
		if(!image.getContentType().equals("image/jpeg")) {
			throw new ImageUploadException("Only JPG images accepted.");
		}
	}

	@RequestMapping(value = "/follow/{username}", method = RequestMethod.GET)
	public String followSpitter(Model model, @PathVariable String username, 
			HttpSession session) {

		Spitter spitterToFollow = spitterServiceImpl.getSpitterByUsername(username);
		Spitter spitterMe = (Spitter) session.getAttribute("loggedSpitter");

		spitterServiceImpl.addSpitterToFollower(spitterMe, spitterToFollow);

		return "redirect:/spitters/" + username;
	}

	@RequestMapping(value = "unfollow/{username}", method = RequestMethod.GET)
	public String unfollowSpitter(Model model, @PathVariable String username, 
			HttpSession session) {

		Spitter spitterMe = (Spitter) session.getAttribute("loggedSpitter");

		spitterServiceImpl.removeSpitterFromFollowers(spitterMe, username);

		return "redirect:/spitters/" + username;
	}

	@RequestMapping(value = "/{username}", method = RequestMethod.GET)
	public String showSpitterProfile(@PathVariable String username, Model model, 
			HttpSession session) {

		Spitter spitter = spitterServiceImpl.getSpitterByUsername(username);
		if(spitter != null) {
			model.addAttribute("spitter", spitter);
			Spitter spitterMe = (Spitter) session.getAttribute("loggedSpitter");
	
			model.addAttribute("followAlready", 
					spitterServiceImpl.isSpitterFollowedBy(spitterMe, username)?true:false);
	
			return "spitters/view";
		} else {
			return "spitters/noProfile";
		}
	}
}
