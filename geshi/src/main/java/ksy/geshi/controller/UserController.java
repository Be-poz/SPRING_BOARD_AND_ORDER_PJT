package ksy.geshi.controller;

import ksy.geshi.domain.MemberEntity;
import ksy.geshi.form.MemberForm;
import ksy.geshi.service.UserService;
import ksy.geshi.validator.PasswordValidator;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

@Controller
@RequestMapping("/user")
@Slf4j
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @GetMapping("/login")
    public String login(@RequestParam(value = "error",defaultValue = "false")String error,Model model) {
        model.addAttribute("error",error);
        return "user/user_login";
    }

    @GetMapping("/register")
    public String register(@ModelAttribute("memberForm")MemberForm memberForm) {
        return "user/user_register";
    }

    @PostMapping("/register")
    public String create(@Valid @ModelAttribute("memberForm")MemberForm memberForm, BindingResult result){

        if (result.hasErrors()) {
            return "user/user_register";
        }else{
            MemberEntity member=new MemberEntity();
            member.memberSetting(memberForm.getUserId(),memberForm.getUserName(),passwordEncoder.encode(memberForm.getPassword()));
            userService.userRegister(member);
            return "user/register_success";
        }
    }

    @GetMapping("/modify")
    public String modify(Model model,HttpServletRequest request) {
        HttpSession session=request.getSession();
        String userId = (String) session.getAttribute("userId");

        MemberForm memberForm = userService.findUser(userId);
        model.addAttribute("userName",memberForm.getUserName());
        model.addAttribute("userId",userId);
        model.addAttribute("memberForm",memberForm);
        log.info(memberForm.getUserId()+" "+memberForm.getUserName()+" "+memberForm.getPassword());
        return "user/user_modify";
    }

    @PostMapping("/modify")
    public String modify_pro(@ModelAttribute("memberForm")MemberForm memberForm,BindingResult result){
        if(result.hasErrors()){
            return "user/user_modify";
        }else{
            userService.updateMember(memberForm);
            return "user/modify_success";
        }
    }


    @InitBinder
    public void initBinder(WebDataBinder binder) {
        PasswordValidator validator=new PasswordValidator();
        binder.addValidators(validator);
    }

}
