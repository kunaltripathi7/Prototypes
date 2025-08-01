package com.crypt.SpringCoreDemo.rest;

import com.crypt.SpringCoreDemo.common.Coach;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class DemoController {

    private  Coach coach;

//    @Autowired
//    public DemoController(Coach theCoach) {
//        coach = theCoach;
//    }

    @Autowired
    public void setCoach(Coach theCoach) {
        coach = theCoach;
    }

    @GetMapping("/workout")
    public String getDailyWorkout() {
        return coach.getDailyWorkout();
    }
}
