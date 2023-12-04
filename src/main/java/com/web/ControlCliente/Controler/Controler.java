package com.web.ControlCliente.Controler;

import com.web.ControlCliente.domain.IPerson;
import com.web.ControlCliente.service.IPersonService;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;

import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.Collections;

@Controller
@Slf4j
public class Controler {
    @Autowired
    private IPersonService iPersonService;

    @GetMapping("/")
    public String index(Model model, @AuthenticationPrincipal User user) {
        var persons = iPersonService.getPersons();
        log.info("Get persons by controler:" + persons);
        //usuario que hizo login con @Auth y User
        log.info("User login: ", user);
        model.addAttribute("persons", persons);
        return "index";
    }

    @GetMapping("/addPerson")
    public String addPerson(Model model) {
        model.addAttribute("iPerson", new IPerson());
        return "updated";
    }

    @PostMapping("/savePerson")
    public String save(@Valid IPerson iPerson, BindingResult result) { //@Valid indica que debo validar el caso

        if (result.hasErrors()) {
            //model.addAttribute("iPerson", iPerson);
            return "updated";
        }
        iPersonService.savePerson(iPerson);
        return "redirect:/";
    }

    @GetMapping("updated/{id}")//pathParameter
    public String updated(IPerson iPerson, Model model, BindingResult result) {

        if (result.hasErrors()) {
            return "updated";
        }
        iPerson = iPersonService.getPerson(iPerson);
        model.addAttribute("iPerson", iPerson);
        return "updated";
    }

    @GetMapping("deleted")//queryParams
    public String deleted(IPerson iPerson) {
        iPersonService.deletePerson(iPerson);
        return "redirect:/";
    }

}
