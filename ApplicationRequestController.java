
package com.example.askIt.controller;

import com.example.askIt.model.ApplicationRequest;
import com.example.askIt.service.ApplicationRequestService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/requests")
public class ApplicationRequestController {

    private final ApplicationRequestService service;

    public ApplicationRequestController(ApplicationRequestService service) {
        this.service = service;
    }

    @GetMapping
    public String listAll(Model model) {
        model.addAttribute("requests", service.findAll());
        model.addAttribute("activeTab", "all");
        return "index";
    }

    @GetMapping("/pending")
    public String listPending(Model model) {
        model.addAttribute("requests", service.findByHandled(false));
        model.addAttribute("activeTab", "pending");
        return "index";
    }

    @GetMapping("/processed")
    public String listProcessed(Model model) {
        model.addAttribute("requests", service.findByHandled(true));
        model.addAttribute("activeTab", "processed");
        return "index";
    }

    @GetMapping("/add")
    public String addForm(Model model) {
        model.addAttribute("request", new ApplicationRequest());
        return "add";
    }

    @PostMapping
    public String create(@ModelAttribute ApplicationRequest request, RedirectAttributes ra) {
        service.save(request);
        ra.addFlashAttribute("msg", "Request added");
        return "redirect:/requests";
    }

    @GetMapping("/{id}")
    public String details(@PathVariable Long id, Model model) {
        ApplicationRequest req = service.findById(id).orElse(null);
        model.addAttribute("req", req);
        return "details";
    }

    @PostMapping("/{id}/process")
    public String process(@PathVariable Long id, @RequestParam(defaultValue = "true") boolean handled, RedirectAttributes ra) {
        service.setHandled(id, handled);
        ra.addFlashAttribute("msg", handled ? "Marked as processed" : "Marked as pending");
        return "redirect:/requests";
    }

    @PostMapping("/{id}/delete")
    public String delete(@PathVariable Long id, RedirectAttributes ra) {
        service.deleteById(id);
        ra.addFlashAttribute("msg", "Deleted");
        return "redirect:/requests";
    }
}
