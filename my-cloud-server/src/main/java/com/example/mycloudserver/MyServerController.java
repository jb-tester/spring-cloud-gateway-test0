package com.example.mycloudserver;

import org.springframework.web.bind.annotation.*;

@RestController

class MyServerController {

    @GetMapping("/")
    public String home(){
        return "home";
    }

    @GetMapping("/test1")
   public String test1()
    {
        return "test1";
    }

    @GetMapping("/test2_redirected")
   public String test2()
    {
        return "test2 redirected";
    }
    @GetMapping("/myprefix/test4")
    public String test3()
    {
        return "test4 with prefix";
    }

    @GetMapping("/test56/{pathvar}")
    public String test4(@PathVariable String pathvar)
    {
        return "test5 or test6 with pathvar "+ pathvar;
    }

    @GetMapping("/test7")
    public String test7(@RequestParam("param1") String param)
    {
        return "test7 with parameter "+ param;
    }
}