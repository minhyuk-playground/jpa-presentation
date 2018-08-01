package io.presentation.jpa.entitymapping.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created By Minhyuk Yoon on 2018. 7. 31.
 */
@RestController
public class HomeController {

    @RequestMapping("/")
    public String index() {
        return "index";
    }
}
