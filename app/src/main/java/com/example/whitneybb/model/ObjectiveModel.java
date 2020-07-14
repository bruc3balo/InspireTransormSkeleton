package com.example.whitneybb.model;

import java.util.ArrayList;

public class ObjectiveModel {

    String objectiveId;
    String objectiveContent;
    String objectiveTitle;
    String objectiveExpiry;
    String timestamp;
    int objectiveScore; // calculated by criteria
    String objectiveLimits;
    ArrayList<String> objectiveSteps;
    ArrayList<String> sacrificeObjectiveCost; //a.k.a. objectiveCost
    String objectiveReward;
    String objectiveRemarks;
    boolean objectiveAchieved;
    boolean extensionOfObjective; //if is new or progressing objective
    String objectiveExtensionId;
    String objectiveExtensionContent;
    boolean quantifiable; //if it can be calculated
    int setObjectiveScore;

    public ObjectiveModel() {
    }

    public int calculateObjectiveScore (int result) {

        //Per­for­mance man­age­ment soft­ware can be used with great effect to ensure both par­ties are hap­py and agree to the objec­tives. This same soft­ware can also be used to set and track objectives.

        String S = ""; // Spe­cif­ic and Stretch­ing. The clear objec­tive needs to be as pre­cise as pos­si­ble. Avoid any ambi­gu­i­ties or con­fu­sion.
        String M = ""; //Mea­sur­able goals give you some­thing to mea­sure your progress against. This will make it eas­i­er to deter­mine whether or not it has been ach
        String A = ""; //objec­tives should all be real­is­ti­cal­ly achiev­able
        String R = ""; // With­out con­sid­er­ing rel­e­vance, even objec­tives that are achieved may have no impact on the per­for­mance of the organ­i­sa­tion,
        String T = "";

        setObjectiveScore = result;
        return setObjectiveScore;
    }



}
