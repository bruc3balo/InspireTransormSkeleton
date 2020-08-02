package com.example.whitneybb.model;

import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.LinkedList;

@Entity(tableName = "objective_table")
public class ObjectiveModel {
    @PrimaryKey(autoGenerate = false)
    @NotNull
    public String objectiveId;
    public static final String OBJECTIVE_ID = "objectiveId";
    public String aboutObjective;
    public static final String ABOUT_OBJECTIVE = "aboutObjective";
    public String objectiveTitle;
    public static final String OBJECTIVE_TITLE = "objectiveTitle";
    public String objectiveExpiry;
    public static final String OBJECTIVE_EXPIRY = "objectiveExpiry";
    public String setAt;
    public String obj_notes;
    public static final String OBJ_NOTES = "obj_notes";
    public String updatedAt;
    public static final String TIMESTAMP = "timestamp";
    public int objectiveScore; // calculated by criteria
    public static final String OBJECTIVE_SCORE = "objectiveScore";
    public String objectiveLimits;
    public static final String OBJECTIVE_LIMITS = "objectiveLimits";
    public String objectiveSteps;
    public static final String OBJECTIVE_STEPS = "objectiveSteps";
    public String sacrificeObjectiveCost; //a.k.a. objectiveCost
    public static final String SACRIFICE_COST = "sacrificeObjectiveCost";
    public String objectiveReward;
    public static final String OBJECTIVE_REWARD = "objectiveReward";
    public String objectiveRemarks;
    public static final String OBJECTIVE_REMARK = "objectiveRemarks";
    public boolean objectiveAchieved;
    public static final String OBJECTIVE_ACHIEVED = "objectiveAchieved";
    public boolean extensionOfObjective; //if is new or progressing objective
    public static final String EXTENDING_OBJECTIVE = "extensionOfObjective";
    public String objectiveExtensionId;
    public static final String OBJ_EXTENSION_ID = "objectiveExtensionId";
    public String objectiveExtensionContent;
    public static final String OBJ_EXTENSION_CONTENT = "objectiveExtensionContent";
    public boolean quantifiable; //if it can be calculated
    public static final String OBJ_QUANTIFIABLE = "quantifiable";
    public int setObjectiveScore;
    public static final String SET_OBJECTIVE_SCORE = "setObjectiveScore";

    public ObjectiveModel() {
    }


    public ObjectiveModel(@NotNull String objectiveId, String aboutObjective, String objectiveTitle, String objectiveExpiry, String setAt, String obj_notes, String updatedAt, int objectiveScore, String objectiveLimits, String objectiveSteps, String sacrificeObjectiveCost, String objectiveReward, String objectiveRemarks, boolean objectiveAchieved, boolean extensionOfObjective, String objectiveExtensionId, String objectiveExtensionContent, boolean quantifiable, int setObjectiveScore) {
        this.objectiveId = objectiveId;
        this.aboutObjective = aboutObjective;
        this.objectiveTitle = objectiveTitle;
        this.objectiveExpiry = objectiveExpiry;
        this.setAt = setAt;
        this.obj_notes = obj_notes;
        this.updatedAt = updatedAt;
        this.objectiveScore = objectiveScore;
        this.objectiveLimits = objectiveLimits;
        this.objectiveSteps = objectiveSteps;
        this.sacrificeObjectiveCost = sacrificeObjectiveCost;
        this.objectiveReward = objectiveReward;
        this.objectiveRemarks = objectiveRemarks;
        this.objectiveAchieved = objectiveAchieved;
        this.extensionOfObjective = extensionOfObjective;
        this.objectiveExtensionId = objectiveExtensionId;
        this.objectiveExtensionContent = objectiveExtensionContent;
        this.quantifiable = quantifiable;
        this.setObjectiveScore = setObjectiveScore;
    }

    public int calculateObjectiveScore(int result) {

        //Per­for­mance man­age­ment soft­ware can be used with great effect to ensure both par­ties are hap­py and agree to the objec­tives. This same soft­ware can also be used to set and track objectives.

        String S = ""; // Spe­cif­ic and Stretch­ing. The clear objec­tive needs to be as pre­cise as pos­si­ble. Avoid any ambi­gu­i­ties or con­fu­sion.
        String M = ""; //Mea­sur­able goals give you some­thing to mea­sure your progress against. This will make it eas­i­er to deter­mine whether or not it has been ach
        String A = ""; //objec­tives should all be real­is­ti­cal­ly achiev­able
        String R = ""; // With­out con­sid­er­ing rel­e­vance, even objec­tives that are achieved may have no impact on the per­for­mance of the organ­i­sa­tion,
        String T = "";

        setObjectiveScore = result;
        return setObjectiveScore;
    }

    public String getSetAt() {
        return setAt;
    }

    public void setSetAt(String setAt) {
        this.setAt = setAt;
    }

    public String getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(String updatedAt) {
        this.updatedAt = updatedAt;
    }

    public String getObjectiveLimits() {
        return objectiveLimits;
    }

    public void setObjectiveLimits(String objectiveLimits) {
        this.objectiveLimits = objectiveLimits;
    }

    public String getObjectiveSteps() {
        return objectiveSteps;
    }

    public void setObjectiveSteps(String objectiveSteps) {
        this.objectiveSteps = objectiveSteps;
    }

    public String getSacrificeObjectiveCost() {
        return sacrificeObjectiveCost;
    }

    public void setSacrificeObjectiveCost(String sacrificeObjectiveCost) {
        this.sacrificeObjectiveCost = sacrificeObjectiveCost;
    }

    public String getObjectiveReward() {
        return objectiveReward;
    }

    public void setObjectiveReward(String objectiveReward) {
        this.objectiveReward = objectiveReward;
    }

    public String getObjectiveTitle() {
        return objectiveTitle;
    }

    public String getObjectiveExpiry() {
        return objectiveExpiry;
    }


    public int getObjectiveScore() {
        return objectiveScore;
    }


    public String getObjectiveRemarks() {
        return objectiveRemarks;
    }

    public boolean isObjectiveAchieved() {
        return objectiveAchieved;
    }

    public boolean isExtensionOfObjective() {
        return extensionOfObjective;
    }

    public String getObjectiveExtensionId() {
        return objectiveExtensionId;
    }

    public String getObjectiveExtensionContent() {
        return objectiveExtensionContent;
    }

    public boolean isQuantifiable() {
        return quantifiable;
    }

    public int getSetObjectiveScore() {
        return setObjectiveScore;
    }

    @NotNull
    public String getObjectiveId() {
        return objectiveId;
    }

    public void setObjectiveId(@NotNull String objectiveId) {
        this.objectiveId = objectiveId;
    }

    public String getAboutObjective() {
        return aboutObjective;
    }

    public void setAboutObjective(String aboutObjective) {
        this.aboutObjective = aboutObjective;
    }

    public void setObjectiveTitle(String objectiveTitle) {
        this.objectiveTitle = objectiveTitle;
    }

    public void setObjectiveExpiry(String objectiveExpiry) {
        this.objectiveExpiry = objectiveExpiry;
    }


    public void setObjectiveScore(int objectiveScore) {
        this.objectiveScore = objectiveScore;
    }


    public void setObjectiveRemarks(String objectiveRemarks) {
        this.objectiveRemarks = objectiveRemarks;
    }

    public void setObjectiveAchieved(boolean objectiveAchieved) {
        this.objectiveAchieved = objectiveAchieved;
    }

    public void setExtensionOfObjective(boolean extensionOfObjective) {
        this.extensionOfObjective = extensionOfObjective;
    }

    public void setObjectiveExtensionId(String objectiveExtensionId) {
        this.objectiveExtensionId = objectiveExtensionId;
    }

    public void setObjectiveExtensionContent(String objectiveExtensionContent) {
        this.objectiveExtensionContent = objectiveExtensionContent;
    }

    public void setQuantifiable(boolean quantifiable) {
        this.quantifiable = quantifiable;
    }

    public void setSetObjectiveScore(int setObjectiveScore) {
        this.setObjectiveScore = setObjectiveScore;
    }

    public String getObj_notes() {
        return obj_notes;
    }

    public void setObj_notes(String obj_notes) {
        this.obj_notes = obj_notes;
    }
}
