package net.csdcodes;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class StudentController {

    @Autowired
    private StudentRepository repository;

    @GetMapping("/")
    private String index(Model model){
        model.addAttribute("students", repository.findAll());
        return "student";
    }
    @PostMapping("/insert")
    private String insert(@ModelAttribute("student") Student student){
        repository.save(student);
        return "redirect:/";
    }
    @GetMapping("/updateForm")
    private String updateForm(@RequestParam("id") Long id, Model model){
        model.addAttribute("student", repository.findById(id));
        return "form";
    }

    @PostMapping("/update")
    private String update(@ModelAttribute("student") Student student){
        repository.update(student);
        return "redirect:/";
    }

    @GetMapping("/delete")
    private String delete(@RequestParam("id") Long id){
        repository.deleteById(id);
        return "redirect:/";
    }
}
