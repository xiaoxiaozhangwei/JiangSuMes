package com.hrms.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/warehouse")
public class WarehouseController {
    @RequestMapping("/management")
    public String welcome(){
        return "warehouse";
    }
}
