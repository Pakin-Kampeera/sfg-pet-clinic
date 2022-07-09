package guru.springframework.sfgpetclinic.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class VetController {

    @GetMapping({"/vets", "/vets/"})
    public String listVets(Model model) {
//        model.addAttribute()
        return "vets/index";
    }
}
