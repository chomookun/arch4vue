package org.chomookun.vuejsexample.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class VueController {

    @RequestMapping({"/{path:^(?!.*\\.).*}", "/{path:^(?!.*\\.).*}/**"})
    public String forward() {
        return "forward:/index.html";
    }

//    @RequestMapping(value = "/{[path:[^\\.]*}")
//    public String forward() {
//        return "forward:/index.html";
//    }

}
