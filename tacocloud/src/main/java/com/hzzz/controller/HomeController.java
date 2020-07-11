package com.hzzz.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * Created by zhen.huaz on 2020/7/1.
 */
@Controller
public class HomeController {
    @GetMapping("/")
    public String home() {
        return "home";
    }

    @GetMapping("/gantt")
    public String gantt() {
        return "gantt";
    }
}
