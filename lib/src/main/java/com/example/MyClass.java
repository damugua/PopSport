package com.example;

import de.greenrobot.daogenerator.DaoGenerator;
import de.greenrobot.daogenerator.Entity;
import de.greenrobot.daogenerator.Schema;

public class MyClass {
    public static void main(String []args){
        Schema schema = new Schema(1,"com.nexuslink");
        createTable(schema);
        try {
            new DaoGenerator().generateAll(schema, "./app/src/main/java-gen");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public static void createTable(Schema schema){

        //�Ʋ���
        Entity steps = schema.addEntity("Steps");
        steps.addIdProperty().primaryKey();
        steps.addIntProperty("uStep");
        steps.addStringProperty("date");

        //�ܲ���
        Entity run = schema.addEntity("Run");
        run.addIdProperty().primaryKey();
        run.addStringProperty("uMileage");
        run.addStringProperty("duration");
        run.addStringProperty("averageSpeed");
        run.addStringProperty("pathLine");
        run.addStringProperty("startPoint");
        run.addStringProperty("endPoint");
        run.addStringProperty("date");
        run.addFloatProperty("cal");

        //�û���
        Entity user = schema.addEntity("User");
        user.addIdProperty().primaryKey();
        user.addIntProperty("uid");
        user.addStringProperty("uName");
        user.addStringProperty("uGender");
        user.addStringProperty("uImg");
        user.addFloatProperty("uHeight");
        user.addFloatProperty("uWeight");
        user.addIntProperty("uFansNum");
        user.addIntProperty("uExp");
        user.addLongProperty("uHistoryStep");
        user.addLongProperty("uHistoryMileage");
        user.addLongProperty("uBestRecordStep");
        user.addLongProperty("uBestRecordMileage");
        user.addStringProperty("uAchievements");
        user.addIntProperty("uFollowedNum");

        //ÿ����������
        Entity taskSteps = schema.addEntity("TaskSteps");
        taskSteps.addIdProperty().primaryKey();
        taskSteps.addStringProperty("TaskStepsName");
        taskSteps.addIntProperty("taskFlag");
        taskSteps.addIntProperty("taskNum");

        //ÿ�������������
        Entity taskMileages = schema.addEntity("TaskMileages");
        taskMileages.addIdProperty().primaryKey();
        taskMileages.addStringProperty("TaskMileagesName");
        taskMileages.addIntProperty("taskFlag");
        taskMileages.addIntProperty("taskNum");
    }
}
