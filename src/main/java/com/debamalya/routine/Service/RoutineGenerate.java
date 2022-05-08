package com.debamalya.routine.Service;

import com.debamalya.routine.Model.Period;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.security.auth.Subject;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

@Service
@RequiredArgsConstructor
@Slf4j
public class RoutineGenerate {

    public ArrayList<ArrayList<ArrayList<Period>>> generate(int duration, int noOfClasses, int noOfDays, int noOfSection,
                                                 ArrayList<HashMap<String, ArrayList<String>>> subject){

        LocalTime startTime = LocalTime.of(9,30);
        LocalTime endTime = startTime.plusMinutes((long) duration *noOfClasses);
        ArrayList<String> subjectList = new ArrayList<>();

        HashMap<String,ArrayList<String>> subjectWithFaculty = new HashMap<>();
        for(HashMap<String,ArrayList<String>> i:subject){
            String subjectName="";
            if(i.containsKey("name")){
                subjectName = i.get("name").get(0);
            }
            if(i.containsKey("faculty")){
                subjectWithFaculty.put(subjectName,i.get("faculty"));
                subjectList.add(subjectName);
            }
        }
        System.out.println("subjectList "+subjectList);
        System.out.println("Subject With Faculty "+subjectWithFaculty);
        HashMap<String,ArrayList<String>> slotWiseFaculty=new HashMap<>();
        ArrayList<String> slotsTime = new ArrayList<>();
        LocalTime classTime = startTime;
        for(int time = 0;time<noOfClasses;time++){
            slotWiseFaculty.put(classTime.toString(),new ArrayList<>());
            slotsTime.add(classTime.toString());
            classTime=classTime.plusMinutes(60);
        }
//        System.out.println("Slot Wise time "+slotWiseFaculty);
        System.out.println("Period times "+slotsTime);
        ArrayList<ArrayList<Period>> dayByDayRoutineList= new ArrayList<>();
        ArrayList<ArrayList<ArrayList<Period>>> weeklyRoutine = new ArrayList<>();
        for( int d = 1;d<=noOfDays;d++){
            dayByDayRoutineList=new ArrayList<>();
        for(int section=0;section<noOfSection;section++){
            ArrayList<Period> sectionSubjectList = new ArrayList<>();
            for(int clas=0;clas<noOfClasses-1;){
                for(String subjectElement : subjectList){
                    String ClassTiming = slotsTime.get(clas++);
//                    String subjectName = subjectElement;
                    ArrayList<String> facultyList = subjectWithFaculty.get(subjectElement);
                    System.out.println("facultyList "+facultyList);
                    System.out.println("slotWiseFaculty "+slotWiseFaculty);
                    for(String faculty:facultyList) {
                        if (!slotWiseFaculty.get(ClassTiming).contains(faculty)) {
                            Period period = new Period();
                            period.setFName(faculty);
                            period.setTime(ClassTiming);
                            period.setSName(subjectElement);
                            sectionSubjectList.add(period);
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
            if (sectionSubjectList.size()==noOfClasses){
                dayByDayRoutineList.add(sectionSubjectList);
            }
            else{
                int index = noOfClasses-(noOfClasses-sectionSubjectList.size());
                int size = sectionSubjectList.size();
                while(size<noOfClasses){
                    sectionSubjectList = getSubjects(index,noOfClasses,subjectWithFaculty,slotsTime,slotWiseFaculty
                            ,sectionSubjectList);
                    size=sectionSubjectList.size();
                }
                dayByDayRoutineList.add(sectionSubjectList);
            }

        }
            weeklyRoutine.add(dayByDayRoutineList);
        for(Map.Entry slotFacultList:slotWiseFaculty.entrySet()){
            slotFacultList.setValue(new ArrayList<>());
        }
            System.out.println("after clearing slotWiseFaculty "+slotWiseFaculty);
        Collections.shuffle(subjectList);
        }
//        weeklyRoutine.add(dayByDayRoutineList);
        System.out.println("dayByDayRoutineList "+dayByDayRoutineList);
        System.out.println("weeklyRoutine "+weeklyRoutine);
        return  weeklyRoutine;

    }

    private ArrayList<Period> getSubjects(int index,int noOfClasses, HashMap<String, ArrayList<String>> subjectWithFaculty,
                                          ArrayList<String> slotsTime,
                                          HashMap<String, ArrayList<String>> slotWiseFaculty,
                                          ArrayList<Period> sectionSubjectList) {
//        System.out.println("index "+index);
//        System.out.println("noOfClasses "+noOfClasses);
//        index--;
        for(int clas=index;clas<noOfClasses;){
            for(Map.Entry subjectElement : subjectWithFaculty.entrySet()) {
                if (clas < noOfClasses) {
//                System.out.println("clas " + clas);
                String ClassTiming = slotsTime.get(clas);
//                System.out.println("ClassTiming " + ClassTiming);
                    clas++;
//                System.out.println("clas "+clas);
                String subjectName = (String) subjectElement.getKey();
                ArrayList<String> facultyList = subjectWithFaculty.get(subjectName);
                for (String faculty : facultyList) {

                    if (!slotWiseFaculty.get(ClassTiming).contains(faculty)) {
                        Period period = new Period();
                        period.setFName(faculty);
                        period.setTime(ClassTiming);
                        period.setSName(subjectName);
                        sectionSubjectList.add(period);
//                        sectionSubjectList.add(subjectName + "-" + ClassTiming + "-" + faculty);
                        ArrayList<String> temp = slotWiseFaculty.get(ClassTiming);
                        temp.add(faculty);
                        slotWiseFaculty.put(ClassTiming, temp);
                        break;
                    }
                }
                continue;
            }
            }
            break;
        }
        return sectionSubjectList;
    }


}
