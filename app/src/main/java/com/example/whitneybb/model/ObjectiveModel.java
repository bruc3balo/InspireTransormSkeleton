package com.example.whitneybb.model;

import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;

@Entity(tableName = "objective_table")
public class ObjectiveModel {
    @PrimaryKey(autoGenerate = false)
    @NotNull
    public int objectiveId;
    public String objectiveContent;
    public String objectiveTitle;
    public String objectiveExpiry;
    public String timestamp;
    public int objectiveScore; // calculated by criteria
    public String objectiveLimits;
    @Ignore
    public ArrayList<String> objectiveSteps;
    @Ignore
    public ArrayList<String> sacrificeObjectiveCost; //a.k.a. objectiveCost
    public String objectiveReward;
    public String objectiveRemarks;
    public boolean objectiveAchieved;
    public boolean extensionOfObjective; //if is new or progressing objective
    public String objectiveExtensionId;
    public String objectiveExtensionContent;
    public boolean quantifiable; //if it can be calculated
    public int setObjectiveScore;

    public ObjectiveModel() {
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

    public int getObjectiveId() {
        return objectiveId;
    }

    public String getObjectiveContent() {
        return objectiveContent;
    }

    public String getObjectiveTitle() {
        return objectiveTitle;
    }

    public String getObjectiveExpiry() {
        return objectiveExpiry;
    }

    public String getTimestamp() {
        return timestamp;
    }

    public int getObjectiveScore() {
        return objectiveScore;
    }

    public String getObjectiveLimits() {
        return objectiveLimits;
    }

    public ArrayList<String> getObjectiveSteps() {
        return objectiveSteps;
    }

    public ArrayList<String> getSacrificeObjectiveCost() {
        return sacrificeObjectiveCost;
    }

    public String getObjectiveReward() {
        return objectiveReward;
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
}
