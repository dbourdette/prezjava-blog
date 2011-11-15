package edu.ecm.blog.web.controller;

import javax.inject.Inject;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import edu.ecm.blog.ejb.MoreSeriousEJB;

@Controller
public class HelloController {
    @Inject
    private MoreSeriousEJB moreSeriousEJB;

    @RequestMapping("/hello")
    @ResponseBody
	public String hello(Model model) {
		return moreSeriousEJB.hello("Steven from Controller");
	}
}
