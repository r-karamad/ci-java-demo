package cicd.githubactions.demo.controller;

import org.springframework.ui.Model;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class DemoController {

   @GetMapping("/")
   public String home(Model model) { 
      model.addAttribute("message", "🔔 This is coming from the controller class."); 
      model.addAttribute("title", "Home Page"); 
      return "index"; // Return the template name
   }

}