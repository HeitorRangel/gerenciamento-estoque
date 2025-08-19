package br.edu.iff.ccc.gerenciadorapp.controller.view;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;



@Controller
@RequestMapping(path = "/principal")
public class MainViewController {

    @GetMapping()
    public String getMethodName() {
        return "home.html";
    }    

    @GetMapping("/user/{id}")
    public String getUserById(@PathVariable("id")String id, @RequestParam("nome")String nome,@RequestParam("email")String email, Model model) {
        model.addAttribute("id", id);
        model.addAttribute("nome", nome);
        model.addAttribute("email", email);
        return "userDatailHome.html";
    }

}
