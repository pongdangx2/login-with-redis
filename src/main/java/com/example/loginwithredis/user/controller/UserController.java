package com.example.loginwithredis.user.controller;

import com.example.loginwithredis.common.ErrorCode;
import com.example.loginwithredis.user.service.UserService;
import com.example.loginwithredis.user.vo.CommonResponse;
import com.example.loginwithredis.user.vo.UserVO;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/user")
public class UserController {

    private final UserService userService;

    public UserController(UserService redisUserService) {
        this.userService = redisUserService;
    }

    // 2023.07.15 이경훈: Client Side Rendering 으로 변경
    // ===================================== for web start =====================================
    @RequestMapping("/login.do")
    public String loginPage(){
        return "login.html";
    }
    /*
    public ModelAndView loginPage(@RequestParam(required = false) String resultMessage) {
        ModelAndView mv = new ModelAndView();
        if(resultMessage != null)
            mv.addObject("resultMessage", resultMessage);
        mv.setViewName("Login.html");
        return mv;
    }*/

    /*
    @RequestMapping("/signIn.do")
    public ModelAndView signIn(@RequestParam(value = "id") String id, @RequestParam("password") String password) {
        ModelAndView mv = new ModelAndView();

        UserVO userVO = new UserVO(id, password);
        String resultMessage = "";
        try {
            userService.signIn(userVO);
            resultMessage = "로그인 성공";
        }catch(LkhException lkhException){
            resultMessage = lkhException.getErrorCode().getMessage();
        }
        mv.addObject("resultMessage", resultMessage);
        mv.setViewName("redirect:/user/login.do");
        return mv;
    }

    @RequestMapping("/join.do")
    public ModelAndView join(Model model, @RequestParam("id") String id, @RequestParam("password") String password) {
        ModelAndView mv = new ModelAndView();

        UserVO userVO = new UserVO(id, password);

        String resultMessage = "";
        try {
            userService.join(userVO);
            resultMessage = "회원가입 성공";
        }catch(LkhException lkhException){
            resultMessage = lkhException.getErrorCode().getMessage();
        }

        mv.addObject("resultMessage", resultMessage);
        mv.setViewName("redirect:/user/login.do");
        return mv;
    }
    */
    // ===================================== for web end =====================================

    // ===================================== for api start =====================================
    @PostMapping("/signIn")
    @ResponseBody
    public CommonResponse signInApi(@RequestBody UserVO userVO){
        userService.signIn(userVO);
        return new CommonResponse(ErrorCode.SUCCESS);
    }

    @PostMapping("/join")
    @ResponseBody
    public CommonResponse joinApi(@RequestBody UserVO userVO){
        userService.join(userVO);
        return new CommonResponse(ErrorCode.SUCCESS);
    }
    // ===================================== for api end =====================================
}
