package guru.springframework.sfgpetclinic.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class OwnerController {

    @GetMapping({"/owners", "/owners/"})
    public String listOwners(Model model) {
//        model.addAttribute()
        return "owners/index";
    }
}
