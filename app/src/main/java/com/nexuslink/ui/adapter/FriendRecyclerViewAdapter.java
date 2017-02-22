package com.nexuslink.ui.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.elvishew.xlog.XLog;
import com.nexuslink.R;
import com.nexuslink.config.Constants;
import com.nexuslink.model.data.SearchInfo;
import com.nexuslink.ui.activity.FriendActivity;
import com.nexuslink.ui.activity.FriendInfoActivity;
import com.nexuslink.util.CircleImageView;
import com.nexuslink.util.IdUtil;
import com.ufreedom.floatingview.Floating;
import com.ufreedom.floatingview.FloatingBuilder;
import com.ufreedom.floatingview.FloatingElement;
import com.ufreedom.floatingview.effect.TranslateFloatingTransition;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

/**
 * Created by ASUS-NB on 2017/1/16.
 */

public class FriendRecyclerViewAdapter extends RecyclerView.Adapter<FriendRecyclerViewAdapter.MyViewHodler>{
    private Context mContext;
    int mFollowedNum =-1;
    private String mFollowed[]=new String[20];
    private String nickName[]={"周家豪0","周家豪1","周家豪2","周家豪3","周家豪4","周家豪5","周家豪6","周家豪7","周家豪8","周家豪9","周家豪10",
            "周家豪11","周家豪12","周家豪13","周家豪14","周家豪15","周家豪16","周家豪17","周家豪18","周家豪19"};
    private SearchInfo searchInfo;
    public interface CallbackListener{
        void onItemClicked(int uId,int fId);
    }
    private static CallbackListener mListener;
    public static void setCallbackListener(CallbackListener listener){
        mListener  = listener;
    }
    public FriendRecyclerViewAdapter(Context mContext, SearchInfo searchInfo) {
        this.mContext = mContext;
        EventBus.getDefault().register(this);
    }


    @Override
    public MyViewHodler onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.fragment_alluser_item,parent,false);
        MyViewHodler viewHodler = new MyViewHodler(view);
        return viewHodler;
    }

    @Override
    public int getItemCount() {
        return searchInfo.getUsers().size();
    }

    @Override
    public void onBindViewHolder(MyViewHodler holder, int position) {
        Log.e(Constants.TAG,"onBindViewHodler");
        holder.tvName.setText(searchInfo.getUsers().get(position).getFName());
        Glide.with(mContext)
                .load(Constants.BASE_URL+searchInfo.getUsers().get(position).getFImg())
                .into(holder.imageHead);
        XLog.e(Constants.BASE_URL+searchInfo.getUsers().get(position).getFImg());
        holder.btnFollow.setText("关注");
        holder.btnFollow.setClickable(true);
        holder.btnFollow.setBackgroundResource(R.drawable.selector_button_follow);
         for(int j=0;j<=mFollowedNum;j++){
             if(holder.tvName.getText()==mFollowed[j]){
                 holder.btnFollow.setBackgroundResource(R.drawable.selector_button_followed);
                 holder.btnFollow.setText("已关注");
                 holder.btnFollow.setClickable(false);
             }
         }
    }

    public class MyViewHodler extends RecyclerView.ViewHolder{
        CircleImageView imageHead;
        TextView tvName;
        Button btnFollow;
        public MyViewHodler(View itemView) {
            super(itemView);
            imageHead = (CircleImageView) itemView.findViewById(R.id.image_head);
            tvName = (TextView) itemView.findViewById(R.id.tv_name);
            btnFollow = (Button) itemView.findViewById(R.id.btn_follow);
            imageHead.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent friendInfoIntent = new Intent(mContext, FriendInfoActivity.class);
                    if(friendInfoIntent.resolveActivity(mContext.getPackageManager())!=null){
                        mContext.startActivity(friendInfoIntent);
                    }
                }
            });
            btnFollow.setOnClickListener(new View.OnClickListener() {

                @Override
                public void onClick(View view) {
                    for(int j=0;j<=mFollowedNum;j++){
                        if(tvName.getText()==mFollowed[j]){
                            return;
                        }
                    }
                    mFollowedNum++;
                    mFollowed[mFollowedNum]=tvName.getText().toString();
                    ImageView imageView = new ImageView(mContext);
                    imageView.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT));
                    imageView.setImageResource(R.drawable.followimage);
                    FloatingElement element = new FloatingBuilder()
                            .anchorView(btnFollow)
                            .targetView(imageView)
                            .offsetX(0)
                            .offsetY(-40)
                            .floatingTransition(new TranslateFloatingTransition())
                            .build();
                    Floating floating = new Floating(FriendActivity.getFriendActivity());
                    floating.startFloating(element);
                    btnFollow.setBackgroundResource(R.drawable.selector_button_followed);
                    btnFollow.setText("已关注");
                    btnFollow.setClickable(false);
                    mListener.onItemClicked(8,0);
                    }
            });
        }
    }
    @Subscribe (threadMode = ThreadMode.MAIN)
    public void receiveSearchInfo(SearchInfo searchInfo){
        this.searchInfo = searchInfo;
        EventBus.getDefault().unregister(this);
    }
}
