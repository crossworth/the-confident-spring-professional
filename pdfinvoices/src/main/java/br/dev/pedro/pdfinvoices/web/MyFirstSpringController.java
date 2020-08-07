package br.dev.pedro.pdfinvoices.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller // tell spring that this is a controller class
public class MyFirstSpringController {

    @GetMapping("/") // this is a GET request on / path
    @ResponseBody
    public String index() {
        return "Hello world!";
    }
}
