package pl.partyka.shopapplication.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/")
public class NoResourceController {
    @GetMapping
    public String redirectToProductsList(){
        return "redirect:/products";
    }
}
