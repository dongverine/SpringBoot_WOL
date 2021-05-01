package com.dongverine.wol.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class JspController {
    @GetMapping("/")
    public String index(){
        return "index";//index.jsp
    }
}
