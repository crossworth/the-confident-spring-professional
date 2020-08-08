package br.dev.pedro.pdfinvoices.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class WebSiteController {

    @GetMapping("/")
    public String homepage() {
        return "index.html";
    }
}
