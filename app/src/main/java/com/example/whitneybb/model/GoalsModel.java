package com.example.whitneybb.model;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

import java.util.List;
import java.util.Map;

@Entity(tableName = "goal_table")
public class GoalsModel {
    @PrimaryKey
    @NonNull
    //todo changeID toString
    private String goalId;
    public static final String GOAL_ID = "goalId";
    //private String goalExpiry; //time
   // public static final String GOAL_EXPIRY = "goalExpiry";
    private String goalContent;
    public static final String GOAL_CONTENT = "goalContent";
    private String stepsToGoal; //path
    public static final String GOAL_STEPS = "stepsToGoal";
    private String goalSacrifices; //resistance to Change.
    public static final String GOAL_SACRIFICE = "goalSacrifices";
    private boolean goalAchieved;
    public static final String GOAL_ACHIEVED = "goalAchieved";
    private String goalSetAt;
    private String goalUpdatedAt;
    private boolean goalPrivate;
    public static final String GOAL_PRIVATE = "goalPrivate";
    private String reward; // what to achieve
    public static final String GOAL_REWARD = "reward";
    private String goalLimitations; //whatHindersYourGoal
    public static final String GOAL_LIMITATIONS = "goalLimitations";
    private String goalReview;
    public static final String GOAL_REVIEW = "goalReview";
    private String goalTerm;
    public static final String GOAL_TERM = "goalTerm";
    private int goalExperienceRating;
    public static final String GOAL_XP = "goalExperienceRating";
    private String goalNotes; // about experience
    public static final String GOAL_NOTES = "goalNotes";


    public static final String SHORT_TERM = "Short Term",MID_TERM = "Middle Term",LONG_TERM = "Long Term";

    //Help in focusing
    // link experts who have been on that path
    // seek help
    // Get a reward

    public GoalsModel() {

    }


    public GoalsModel(@NonNull String goalId, String goalContent, String stepsToGoal, String goalSacrifices, boolean goalAchieved, String goalSetAt, String goalUpdatedAt, boolean goalPrivate, String reward, String goalLimitations, String goalReview, String goalTerm, int goalExperienceRating, String goalNotes) {
        this.goalId = goalId;
        this.goalContent = goalContent;
        this.stepsToGoal = stepsToGoal;
        this.goalSacrifices = goalSacrifices;
        this.goalAchieved = goalAchieved;
        this.goalSetAt = goalSetAt;
        this.goalUpdatedAt = goalUpdatedAt;
        this.goalPrivate = goalPrivate;
        this.reward = reward;
        this.goalLimitations = goalLimitations;
        this.goalReview = goalReview;
        this.goalTerm = goalTerm;
        this.goalExperienceRating = goalExperienceRating;
        this.goalNotes = goalNotes;
    }

    @NonNull
    public String getGoalId() {
        return goalId;
    }

    public void setGoalId(@NonNull String goalId) {
        this.goalId = goalId;
    }

    public String getStepsToGoal() {
        return stepsToGoal;
    }

    public void setStepsToGoal(String stepsToGoal) {
        this.stepsToGoal = stepsToGoal;
    }

    public String getGoalSacrifices() {
        return goalSacrifices;
    }

    public void setGoalSacrifices(String goalSacrifices) {
        this.goalSacrifices = goalSacrifices;
    }

    public String getReward() {
        return reward;
    }

    public void setReward(String reward) {
        this.reward = reward;
    }

    public void setGoalContent(String goalContent) {
        this.goalContent = goalContent;
    }

    public void setGoalAchieved(boolean goalAchieved) {
        this.goalAchieved = goalAchieved;
    }

    public void setGoalSetAt(String goalSetAt) {
        this.goalSetAt = goalSetAt;
    }

    public void setGoalPrivate(boolean goalPrivate) {
        this.goalPrivate = goalPrivate;
    }

    public String getGoalLimitations() {
        return goalLimitations;
    }

    public void setGoalLimitations(String goalLimitations) {
        this.goalLimitations = goalLimitations;
    }

    public void setGoalReview(String goalReview) {
        this.goalReview = goalReview;
    }

    public void setGoalExperienceRating(int goalExperienceRating) {
        this.goalExperienceRating = goalExperienceRating;
    }

    public void setGoalNotes(String goalNotes) {
        this.goalNotes = goalNotes;
    }


    public String getGoalContent() {
        return goalContent;
    }

    public boolean isGoalAchieved() {
        return goalAchieved;
    }

    public String getGoalSetAt() {
        return goalSetAt;
    }

    public boolean isGoalPrivate() {
        return goalPrivate;
    }

    public String getGoalUpdatedAt() {
        return goalUpdatedAt;
    }

    public void setGoalUpdatedAt(String goalUpdatedAt) {
        this.goalUpdatedAt = goalUpdatedAt;
    }

    public String getGoalTerm() {
        return goalTerm;
    }

    public void setGoalTerm(String goalTerm) {
        this.goalTerm = goalTerm;
    }

    public String getGoalReview() {
        return goalReview;
    }

    public int getGoalExperienceRating() {
        return goalExperienceRating;
    }

    public String getGoalNotes() {
        return goalNotes;
    }
}
