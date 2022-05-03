package com.debamalya.routine.Service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.LocalTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

@Service
@RequiredArgsConstructor
@Slf4j
public class RoutineGenerate {

    public void generate(int duration, int noOfClasses, int noOfDays, int noOfSection, ArrayList<HashMap<String, ArrayList<String>>> subject){

        LocalTime startTime = LocalTime.of(9,30);
        LocalTime endTime = startTime.plusMinutes((long) duration *noOfClasses);

        HashMap<String,ArrayList<String>> subjectWithFaculty = new HashMap<>();
        for(HashMap<String,ArrayList<String>> i:subject){
            String subjectName="";
            if(i.containsKey("name")){
                subjectName = i.get("name").get(0);
            }
            if(i.containsKey("faculty")){
                subjectWithFaculty.put(subjectName,i.get("faculty"));
            }
        }
        System.out.println("Subject With Faculty "+subjectWithFaculty);
        HashMap<String,ArrayList<String>> slotWiseFaculty=new HashMap<>();
        ArrayList<String> slotsTime = new ArrayList<>();
        LocalTime classTime = startTime;
        for(int time = 0;time<noOfClasses;time++){
            slotWiseFaculty.put(classTime.toString(),new ArrayList<>());
            slotsTime.add(classTime.toString());
            classTime=classTime.plusMinutes(60);
        }
        System.out.println("Slot Wise time "+slotWiseFaculty);
        System.out.println("Slot time array "+slotsTime);
        ArrayList<ArrayList<String>> dayByDayRoutineList= new ArrayList<>();
        for(int section=0;section<noOfSection;section++){
            ArrayList<String> sectionSubjectList = new ArrayList<>();
            for(int clas=0;clas<noOfClasses;){
                for(Map.Entry subjectElement : subjectWithFaculty.entrySet()){
                    if(subjectElement==null){
                        
                    }
                    String ClassTiming = slotsTime.get(clas++);
                    String subjectName = (String) subjectElement.getKey();
                    ArrayList<String> facultyList = subjectWithFaculty.get(subjectName);
                    for(String faculty:facultyList) {
                            if (!slotWiseFaculty.get(ClassTiming).contains(faculty)) {
                                sectionSubjectList.add(subjectName + "-" + ClassTiming + "-" + faculty);
                                ArrayList<String> temp = slotWiseFaculty.get(ClassTiming);
                                temp.add(faculty);
                                slotWiseFaculty.put(ClassTiming, temp);
                                break;
                            }
                    }
                    continue;
                }
                break;
            }
            dayByDayRoutineList.add(sectionSubjectList);
        }
        System.out.println(dayByDayRoutineList);

    }

}
