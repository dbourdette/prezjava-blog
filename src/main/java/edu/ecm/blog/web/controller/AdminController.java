package edu.ecm.blog.web.controller;

import java.text.SimpleDateFormat;
import java.util.Date;

import javax.inject.Inject;
import javax.validation.Valid;

import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import edu.ecm.blog.domain.Post;
import edu.ecm.blog.service.PostService;

@Controller
public class AdminController {
    @Inject
    private PostService postService;

    @InitBinder
    public void binder(WebDataBinder binder) {
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm");

        binder.registerCustomEditor(Date.class, new CustomDateEditor(sdf, true));
    }

    @RequestMapping("/admin/posts")
    public String posts(Model model) {
        model.addAttribute("posts", postService.find(0, postService.count()));

        return "admin/posts";
    }

    @RequestMapping("/admin/post")
    public String post(Model model) {
        // on injecte un Post vierge dans le modèle
        model.addAttribute("post", new Post());

        return "admin/post";
    }

    @RequestMapping("/admin/post/{id}")
    public String post(@PathVariable Long id, Model model) {
        // on injecte le post
        model.addAttribute("post", postService.findById(id));

        return "admin/post";
    }

    @RequestMapping(value = "/admin/post", method = RequestMethod.POST)
    public String post(@ModelAttribute("post") @Valid Post post, BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
            return "admin/post";
        }

        postService.save(post);

        return "redirect:/admin/posts";
    }

    @RequestMapping("/admin/post/{id}/delete")
    public String delete(@PathVariable Long id, Model model) {
        postService.delete(id);

        return "redirect:/admin/posts";
    }
}
