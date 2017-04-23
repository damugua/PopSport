package com.nexuslink.presenter.runhousepresenter;

import com.nexuslink.model.CallBackListener;
import com.nexuslink.model.data.LoadRoomsResult;
import com.nexuslink.model.runhousemodel.RunHoseDetailModelImpl;
import com.nexuslink.model.runhousemodel.RunHouseDetailModel;
import com.nexuslink.ui.view.RunHouseDetailView;
import com.nexuslink.util.UserUtils;

/**
 * Created by 猿人 on 2017/2/25.
 */

public class RunHouseDetailPresenterImpl implements RunHouseDetailPresenter {
    /**
     * 持有view和model
     */
    private RunHouseDetailModel runHouseDetailModel;
    private RunHouseDetailView mView;

    public RunHouseDetailPresenterImpl(RunHouseDetailView mView) {
        this.mView = mView;
        runHouseDetailModel = new RunHoseDetailModelImpl();
    }

    @Override
    public void joinRoom(int rId) {
        mView.showProgress();
        runHouseDetailModel.joinRoom(rId, new CallBackListener() {
            @Override
            public void onFinish(Object o) {
                mView.hideProgress();
                mView.showSuccess();
                LoadRoomsResult.RoomBean room = (LoadRoomsResult.RoomBean) o;
                mView.setDatas(room.getUsers());
                //进行数据的插入
                mView.insertOneRoom();
            }

            @Override
            public void onError(Exception e) {
                mView.hideProgress();
                mView.showError();
            }
        });
    }

    @Override
    public void quitRoom(int rId) {
        mView.showProgress();
        runHouseDetailModel.quitRoom(rId, new CallBackListener() {
            @Override
            public void onFinish(Object o) {
                mView.hideProgress();
                mView.removeItem(UserUtils.getUserId());
            }

            @Override
            public void onError(Exception e) {
                e.printStackTrace();
                mView.showError();
            }
        });
    }
}
