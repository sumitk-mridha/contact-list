package ca.sheridancollege.mridhasu.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import lombok.*;

import ca.sheridancollege.mridhasu.beans.Contact;
import ca.sheridancollege.mridhasu.repositories.ContactsRepository;

@Controller
@AllArgsConstructor
public class HomeController {
	private ContactsRepository cr;
	
	@GetMapping("/")
	public String root() {
		cr.testConnection();
		
		return "home.html";
	}
	
	@GetMapping("/add")
	public String add(Model model) {
		model.addAttribute("contactForm", new Contact());
		return "add.html";
	}
	
	@PostMapping("/add")
	public String addContact(@ModelAttribute Contact c) {
		cr.addContact(c);
		return "redirect:/add";
	}
	
	@GetMapping("/view")
	public String view(Model model) {
		model.addAttribute("contacts", cr.getContacts());
		return "view.html";
	}
	
	@GetMapping("/edit/{id}")
	public String edit(@PathVariable("id") int id, Model model) {
		model.addAttribute("contactForm", cr.getContactByID(id));
		return "edit.html";
	}
	
	@PostMapping("/edit/{id}")
	public String editDrink(@PathVariable("id") int id, @ModelAttribute Contact c) {
		cr.editContact(c);
		return "redirect:/view";
	}
	
	@GetMapping("/delete/{id}")
	public String deleteDrink(@PathVariable("id") int id) {
		cr.deleteContact(id);
		return "redirect:/view";
	}
}
