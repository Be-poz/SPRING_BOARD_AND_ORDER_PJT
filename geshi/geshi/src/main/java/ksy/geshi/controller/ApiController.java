package ksy.geshi.controller;

import ksy.geshi.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@Slf4j
public class ApiController {
    private final UserService userService;

    @GetMapping("/user/checkUserIdExist/{userId}")
    public String checkUserIdExist(@PathVariable String userId) {
        log.info("in");
        boolean chk=userService.checkUserIdExist(userId);
        return chk+"";
    }

}
