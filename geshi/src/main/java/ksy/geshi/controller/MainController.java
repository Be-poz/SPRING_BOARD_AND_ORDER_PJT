package ksy.geshi.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@Controller
@Slf4j
public class MainController {

    @GetMapping("/")
    public String main(HttpServletRequest request,Model model) {
        HttpSession session=request.getSession();
        String greeting = (String) session.getAttribute("greeting");
        model.addAttribute("greeting",greeting);
        return "main";
    }

    @GetMapping("/user_denied")
    public String denied(){
        return "user_denied";
    }
}
