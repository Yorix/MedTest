package org.yorix.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.HashMap;
import java.util.Map;

@Controller
@RequestMapping("/")
public class IndexController {
    @RequestMapping(method = RequestMethod.GET)
    public String index(ModelMap model) {
        Map<String, String> attributes = new HashMap<>();
        attributes.put("headText", "Medtest");
        attributes.put("developer", "© Yorix");
        attributes.put("leftMenuIndexHeader", "Загрузка тестов");
        model.addAllAttributes(attributes);
        return "index";
    }
}
