package com.nexuslink.ui.activity;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;

import com.nexuslink.R;
import com.nexuslink.ui.fragment.AppointmentFragment;
import com.nexuslink.ui.fragment.CommunityFragment;
import com.nexuslink.ui.fragment.PersonInfoFragment;
import com.nexuslink.ui.fragment.StepAndRunFragment;
import com.ycl.tabview.library.TabView;
import com.ycl.tabview.library.TabViewChild;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainViewActivity extends AppCompatActivity {

    //===============================================常量
    private static String TAG = "MainViewActivity";
    @BindView(R.id.tav_view)
    TabView tavView;
    //===============================================view相关
    private List<TabViewChild> tabViewChildList = new ArrayList<>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_view);
        ButterKnife.bind(this);
        initView();

    }

    private void initView() {
        Fragment stepAndRunFragment = new StepAndRunFragment();
        Fragment appointFragment = new AppointmentFragment();
        Fragment communityFragment = new CommunityFragment();
        Fragment personalInfoFragment = PersonInfoFragment.getInstance();

        TabViewChild stepAndRun = new TabViewChild(R.drawable.step_press, R.drawable.step_normal, "运动", stepAndRunFragment);
        TabViewChild appoint = new TabViewChild(R.drawable.appoint_press, R.drawable.appoint_normal, "约跑", appointFragment);
        TabViewChild community = new TabViewChild(R.drawable.community_press, R.drawable.compress_normal, "社区", communityFragment);
        TabViewChild personinfo = new TabViewChild(R.drawable.person_press, R.drawable.person_normal, "我的", personalInfoFragment);

        tabViewChildList.add(stepAndRun);
        tabViewChildList.add(appoint);
        tabViewChildList.add(community);
        tabViewChildList.add(personinfo);

        tavView.setTabViewChild(tabViewChildList,getSupportFragmentManager());


    }
}
