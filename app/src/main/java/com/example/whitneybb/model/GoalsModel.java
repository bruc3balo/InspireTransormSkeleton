package com.example.whitneybb.model;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

import java.util.List;
import java.util.Map;

@Entity(tableName = "goal_table")
public class GoalsModel {
    @PrimaryKey(autoGenerate = false)
    @NonNull
    //todo changeID toString
    private int goalId;
    private String goalExpiry; //time
    private String goalContent;
    @Ignore
    private List<String> stepsToGoal; //path
    @Ignore
    private List<String> goalSacrifices; //resistance to Change.
    private boolean goalAchieved;
    private String goalSetAt;
    private boolean goalPrivate;
    @Ignore
    private List<String> reward; // what to achieve
    @Ignore
    private Map<String, String> goalLimitations; //whatHindersYourGoal
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

    public void setGoalId(int goalId) {
        this.goalId = goalId;
    }

    public void setGoalExpiry(String goalExpiry) {
        this.goalExpiry = goalExpiry;
    }

    public void setGoalContent(String goalContent) {
        this.goalContent = goalContent;
    }

    public void setStepsToGoal(List<String> stepsToGoal) {
        this.stepsToGoal = stepsToGoal;
    }

    public void setGoalSacrifices(List<String> goalSacrifices) {
        this.goalSacrifices = goalSacrifices;
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

    public void setReward(List<String> reward) {
        this.reward = reward;
    }

    public void setGoalLimitations(Map<String, String> goalLimitations) {
        this.goalLimitations = goalLimitations;
    }

    public void setShortTerm(boolean shortTerm) {
        this.shortTerm = shortTerm;
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

    public int getGoalId() {
        return goalId;
    }

    public String getGoalExpiry() {
        return goalExpiry;
    }

    public String getGoalContent() {
        return goalContent;
    }

    public List<String> getStepsToGoal() {
        return stepsToGoal;
    }

    public List<String> getGoalSacrifices() {
        return goalSacrifices;
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

    public List<String> getReward() {
        return reward;
    }

    public Map<String, String> getGoalLimitations() {
        return goalLimitations;
    }

    public boolean isShortTerm() {
        return shortTerm;
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
