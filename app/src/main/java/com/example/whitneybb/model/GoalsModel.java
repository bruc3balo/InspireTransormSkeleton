package com.example.whitneybb.model;

import java.util.List;
import java.util.Map;

public class GoalsModel {

    private String goalId;
    private String goalExpiry; //time
    private String goalContent;
    private List<String> stepsToGoal; //path
    private List<String> goalSacrifices; //resistance to Change.
    private boolean goalAchieved;
    private String goalSetAt;
    private boolean goalPrivate;
    private List<String> reward; // what to achieve
    private Map<String,String > goalLimitations; //whatHindersYourGoal
    private boolean shortTerm; //true(1) or false (0)
    private String goalReview;
    private int goalExperienceRating;
    private String goalNotes; // about experience

    //Help in focusing
    // link experts who have been on that path
    // seek help
    // Get a reward

    public GoalsModel() {

    }
}
