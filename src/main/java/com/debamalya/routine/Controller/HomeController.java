package com.debamalya.routine.Controller;


import com.debamalya.routine.Model.Routine;
import com.debamalya.routine.Model.Subject;
import com.debamalya.routine.Service.RoutineGenerate;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.HashMap;

@RestController
@RequiredArgsConstructor
@Slf4j
@RequestMapping(value = "/home")
public class HomeController {

    @Autowired
    RoutineGenerate routineGenerate;


    @PostMapping(value = "/newRoutine", consumes= MediaType.APPLICATION_JSON_UTF8_VALUE, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public void generateRoutine(@RequestBody Routine routine) {
        System.out.println("getting the routine");
        int duration = routine.getDuration();
        int classes = routine.getClasses();
        int days = routine.getDays();
        int sections = routine.getSection();
        ArrayList<HashMap<String, ArrayList<String>>> subject = routine.getSubject();
//        System.out.println(subject);


        routineGenerate.generate(duration,classes,days,sections,subject);


    }
}
