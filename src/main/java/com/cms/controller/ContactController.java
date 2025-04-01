package com.cms.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import com.cms.model.Contact;
import com.cms.service.ContactService;

@Controller
@RequestMapping("/contacts")
public class ContactController {

    @Autowired
    private ContactService contactService;

    @GetMapping
    public String viewContacts(Model model) {
        model.addAttribute("contacts", contactService.getAllContacts());
        return "contact/contact-list";
    }

    @GetMapping("/new")
    public String showCreateForm(Model model) {
        model.addAttribute("contact", new Contact());
        return "contact/contact-form";
    }

    @PostMapping("/save")
    public String saveContact(@ModelAttribute Contact contact) {
        // If the contact has an ID, it's an update, otherwise it's a new contact
        contactService.saveContact(contact);
        return "redirect:/contacts";
    }

    @GetMapping("/edit/{id}")
    public String showEditForm(@PathVariable Long id, Model model) {
        Contact contact = contactService.getContactById(id);
        if (contact != null) {
            model.addAttribute("contact", contact);
            return "contact/contact-form";
        }
        return "redirect:/contacts";
    }

    @GetMapping("/delete/{id}")
    public String deleteContact(@PathVariable Long id) {
        contactService.deleteContact(id);
        return "redirect:/contacts";
    }

    @GetMapping("/search")
    public String searchContacts(@RequestParam("keyword") String keyword, Model model) {
        model.addAttribute("contacts", contactService.searchContacts(keyword));
        return "contact/contact-list";
    }
}
