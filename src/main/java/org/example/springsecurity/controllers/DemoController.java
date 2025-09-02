package org.example.springsecurity.controllers;

import org.springframework.security.access.prepost.PostAuthorize;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.access.prepost.PreFilter;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
public class DemoController {

    @GetMapping("/demo1")
    @PreAuthorize("hasAnyAuthority('read')")
    public String demo1() {
        return "demo 1";
    }

    @GetMapping("/demo2")
    @PreAuthorize("hasAnyAuthority('read', 'write')")
    public String demo2() {
        return "demo 2";
    }

    @GetMapping("/demo3/{smth}")
    @PreAuthorize(
            """
            (#something == authentication.name) or
             (hasAnyAuthority('write', 'read'))
            """
    )
    public String demo2(@PathVariable("smth") String something) {
        var a = SecurityContextHolder.getContext().getAuthentication();
        return "demo 3";
    }

    @GetMapping("/demo5")
    @PostAuthorize("returnObject != 'Demo 5'")
    public String demo5(){
        System.out.println(" :) ");
        return "Demo 5";
    }

    @GetMapping("/demo6")
    @PreFilter("filterObject.contains('a')")  // WORKS WITH EITHER ARRAY OR COLLECTION
    public List<String> demo6(@RequestBody List<String> values){
        List<String> stringList = new ArrayList<>();
        stringList.add("qwerty");
        stringList.add("abcde");
        stringList.add("mirabbos");
        stringList.add("merch");
        System.out.println("values : " + values);
        return values; //ALWAYS RETURN COLLECTION + LIST.OF() IS IMMUTABLE!

    }
}
