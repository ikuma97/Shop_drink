package com.example.demo.Controller;

import com.example.demo.Entity.PerSon;
import com.example.demo.Repository.PersonRepo;
import com.example.demo.Service.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
import java.util.Optional;

@Controller
public class PersonController {

    @Autowired
    PersonService personService;

  @GetMapping("/")
    public String personList(Model model){

      List<PerSon> perSonList = personService.getAllUser();
      model.addAttribute("perSonList", perSonList);
        return "indexTest";
  }
  @GetMapping("/admin/addPerson")
  public String addPerSon(Model model){
      model.addAttribute("person",new PerSon());
      return "addPerson";
  }
  @PostMapping("/admin/save")
  public String save(PerSon perSon) {
      personService.savePerSon(perSon);
      return "redirect:/admin";
  }
  @GetMapping("/admin/delete")
    public String deletePerson(@RequestParam("id") int id,Model model){
      personService.delete(id);
      return "redirect:/admin";
  }
  @GetMapping("/admin/edit")
    public String editPerson(@RequestParam("id") int id ,Model model){
      Optional<PerSon> perSon = personService.findPersonById(id);
      perSon.ifPresent(perSon1 -> model.addAttribute("person",perSon));
      return "editPerson";
  }
}
