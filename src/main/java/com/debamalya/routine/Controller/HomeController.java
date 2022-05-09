package com.debamalya.routine.Controller;


import com.debamalya.routine.Model.Period;
import com.debamalya.routine.Model.Routine;
import com.debamalya.routine.Service.RoutineGenerate;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashMap;

//@CrossOrigin
@RestController
@RequiredArgsConstructor
@Slf4j
@RequestMapping(value = "/home")
public class HomeController {

    @Autowired
    RoutineGenerate routineGenerate;


//    @CrossOrigin(origins = "https://koderbyte.com/routinue-generator")
    @PostMapping(value = "/newRoutine", consumes= MediaType.APPLICATION_JSON_UTF8_VALUE, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<ArrayList<ArrayList<ArrayList<Period>>>> generateRoutine(@RequestBody Routine routine) {
        System.out.println("getting the routine");
        int duration = routine.getDuration();
        int classes = routine.getClasses();
        int days = routine.getDays();
        int sections = routine.getSection();
        ArrayList<HashMap<String, ArrayList<String>>> subject = routine.getSubject();

        ArrayList<ArrayList<ArrayList<Period>>> dayByDayRoutineList= routineGenerate.generate(duration,classes,days,sections,subject);

        return ResponseEntity.ok(dayByDayRoutineList);
    }
}
