package com.dongverine.wol.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.view.RedirectView;

@Controller
public class JspController {
    @GetMapping(value={"/wol"})
    public String wol(){
        return "index";//index.jsp
    }
    @GetMapping(value={"/"})
    public RedirectView index(){
        RedirectView redirectView = new RedirectView();
        redirectView.setUrl("/wol");
        return redirectView;
    }
}
