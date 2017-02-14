package com.nexuslink.ui.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.nexuslink.R;
import com.nexuslink.ui.activity.AchievementActivity;
import com.nexuslink.ui.activity.AlterActivity;
import com.nexuslink.ui.activity.FriendActivity;
import com.nexuslink.ui.activity.RankActivity;
import com.nexuslink.ui.view.PersonInfoView;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by ASUS-NB on 2017/1/14.
 */

public class PersonInfoFragment extends Fragment implements PersonInfoView {


    @BindView(R.id.group_myfriend)
    LinearLayout groupMyfriend;
    @BindView(R.id.group_runningromm)
    LinearLayout groupRunningromm;
    @BindView(R.id.group_setting)
    LinearLayout groupSetting;
    @BindView(R.id.group_mytask)
    LinearLayout groupMytask;
    @BindView(R.id.group_achieve)
    LinearLayout groupAchieve;
    @BindView(R.id.group_rank)
    LinearLayout groupRank;

    public static PersonInfoFragment getInstance() {
        PersonInfoFragment fragment = new PersonInfoFragment();
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_personinfo, container, false);
        ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void showFriends() {
        Intent friendIntent = new Intent(getContext(), FriendActivity.class);
        startActivity(friendIntent);
    }

    @Override
    public void showRunningRoom() {

    }

    @Override
    public void showSetting() {
        Intent intent = new Intent(getContext(), AlterActivity.class);
        startActivity(intent);
    }

    @Override
    public void showTask() {

    }

    @Override
    public void showAchieve() {
        startActivity(new Intent(getContext(), AchievementActivity.class));
    }

    @Override
    public void showRank() {
        startActivity(new Intent(getContext(), RankActivity.class));
    }

    @OnClick({R.id.group_myfriend, R.id.group_runningromm, R.id.group_setting, R.id.group_mytask, R.id.group_achieve, R.id.group_rank})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.group_myfriend:
                showFriends();
                break;
            case R.id.group_runningromm:
                showRunningRoom();
                break;
            case R.id.group_setting:
                showSetting();
                break;
            case R.id.group_mytask:
                showTask();
                break;
            case R.id.group_achieve:
                showAchieve();
                break;
            case R.id.group_rank:
                showRank();
                break;
        }
    }
}
