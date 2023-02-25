package app.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/")
public class mainController {

    @GetMapping
    public ModelAndView mainPage() {
        ModelAndView modelAndView =new ModelAndView();
        modelAndView.setViewName("index");
        return  modelAndView;
    }
}
