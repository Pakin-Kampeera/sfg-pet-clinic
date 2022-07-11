package guru.springframework.sfgpetclinic.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
@RequiredArgsConstructor
public class IndexController {

    @GetMapping({"", "/", "/index","/index.html"})
    public String index(Model model){
//        model.addAttribute("");
        return "index";
    }
}
