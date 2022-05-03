package com.debamalya.routine.Model;

import lombok.Data;

import java.util.ArrayList;
import java.util.HashMap;

@Data
public class Routine {

    int duration;
    int classes;
    int days;
    int section;
    ArrayList<HashMap<String, ArrayList<String>>> subject;
//    HashMap<String, ArrayList<String>> subject;

}
