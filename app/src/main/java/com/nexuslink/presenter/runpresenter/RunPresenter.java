package com.nexuslink.presenter.runpresenter;

import com.amap.api.location.AMapLocation;
import com.nexuslink.model.runmodel.RunModel;
import com.nexuslink.model.runmodel.RunModelImp;
import com.nexuslink.ui.view.RunView;

import java.util.List;
/**
 * Created by 猿人 on 2017/1/28.
 */

public class RunPresenter {
    RunView mRunView;
    RunModel mRunModel;

    public RunPresenter(RunView mRunView) {
        this.mRunView = mRunView;
        mRunModel = new RunModelImp();
    }
    public void saveRecord(List<AMapLocation> list, String date,int duration){
        mRunModel.setDuration(duration);
        mRunModel.saveRecord(list,date);
    }
    public void startRecord(long startTime){
        mRunModel.setStartTime(startTime);
    }
    public void refreshUI(List<AMapLocation> list){
        //distance 单位米
        float distance = mRunModel.getDistance(list);
        mRunModel.calculateCurrentTime();
        mRunView.setCurrentTime(mRunModel.getRealCurrentTime());
        mRunView.setCurrentDistance(mRunModel.getCurrentMiles(distance));
        mRunView.setCurrentSpeed(mRunModel.getCurrentAverage(distance));
        mRunView.setCurrentCol(mRunModel.getCurrentCol(distance));
        mRunView.setMaxSpeed(mRunModel.getMaxSpeed());
    }
}
