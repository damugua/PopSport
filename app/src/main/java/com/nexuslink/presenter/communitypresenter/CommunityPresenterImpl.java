package com.nexuslink.presenter.communitypresenter;

import android.text.TextUtils;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.nexuslink.config.Constants;
import com.nexuslink.model.CallBackListener;
import com.nexuslink.model.communitymodel.CommunityModel;
import com.nexuslink.model.communitymodel.CommunityModelImpl;
import com.nexuslink.model.data.CommentInfo;
import com.nexuslink.model.data.CommentItemData;
import com.nexuslink.model.data.CommunityInfo;
import com.nexuslink.model.data.User1;
import com.nexuslink.ui.view.CommunityView;
import com.nexuslink.ui.view.linearlistview.LinearListView;
import com.nexuslink.util.UserUtils;
import com.wuxiaolong.androidutils.library.LogUtil;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by 猿人 on 2017/2/12.
 */

public class CommunityPresenterImpl implements CommunityPresenter {
    private CommunityModel mCommunity;
    private CommunityView mCommunityView;
    public CommunityPresenterImpl(CommunityView mCommunityView) {
        this.mCommunityView = mCommunityView;
        mCommunity = new CommunityModelImpl();
    }


    @Override
    public void postLike(int userId, int articleId) {
        mCommunity.postLike(userId, articleId, new CallBackListener() {
            @Override
            public void onFinish(Object obj) {
                mCommunityView.showSuccess("点赞成功");
            }

            @Override
            public void onError(Exception e) {
                mCommunityView.showError("点赞失败，请检查网络...");
            }
        });
    }

    @Override
    public void postDisLike(int userId, int articleId) {
        mCommunity.postDisLike(userId, articleId, new CallBackListener() {
            @Override
            public void onFinish(Object obj) {
                mCommunityView.showSuccess("成功取消");
            }

            @Override
            public void onError(Exception e) {
                mCommunityView.showError("取消失败，请重试");
            }
        });
    }

    @Override
    public void postComment(final int aId, final EditText input, final LinearLayout linearLayout, int userId, int articleId, final int pos)
    {
        if(!TextUtils.isEmpty(mCommunityView.getInputComment(input))){
            mCommunity.postComment(userId, articleId,mCommunityView.getInputComment(input), new CallBackListener() {
                @Override
                public void onFinish(Object obj) {
                    mCommunityView.addCommentNum(pos);
                    //getUserName 获取自己的昵称
                    mCommunityView.addOneComment(aId,UserUtils.getUserName(),mCommunityView.getInputComment(input));
                    mCommunityView.clearInput(linearLayout,input);
                }

                @Override
                public void onError(Exception e) {
                    mCommunityView.showError("评论时出错，请重试");
                }
            });
        }else{
            mCommunityView.showError("输入内容不能为空哦");
        }

    }

    /***
     * 刷新
     * @param userId
     */
    @Override
    public void onRefreshData(int userId) {

        mCommunity.getArticles(userId, new CallBackListener() {
            @Override
            public void onFinish(Object obj) {
                mCommunityView.showSuccess("刷新成功");
                mCommunityView.addMsgArticle((List<CommunityInfo.ArticlesBean>) obj);
            }

            @Override
            public void onError(Exception e) {
                mCommunityView.showError("刷新失败,请重试");
                e.printStackTrace();
            }
        });
    }

    /**
     * 加载用户信息
     * @param imageView
     * @param nameText
     * @param levelText
     * @param userId
     */
    @Override
    public void loadUserInfo(final ImageView imageView, final TextView nameText, final TextView levelText, int userId) {
        mCommunity.loadUserInfo(userId, null,new CallBackListener() {
            @Override
            public void onFinish(Object obj) {
                User1 user = (User1) obj;
                mCommunityView.loadUserInfo(imageView,nameText,levelText, Constants.PHOTO_BASE_URL+user.getUImg(),user.getUName()
                , UserUtils.getUserLevel());
            }

            @Override
            public void onError(Exception e) {
                e.printStackTrace();
            }
        });
    }

    /**
     * 加载评论
     */
    @Override
    public void loadComment(final LinearListView commentDetialLinear, final int articleId, final int pos) {
        mCommunity.getComments(articleId, new CallBackListener() {
            @Override
            public void onFinish(Object obj) {
                //根绝userId
                final List<CommentInfo.CommentsBean> commentsBean = (List<CommentInfo.CommentsBean>) obj;
                final List<CommentItemData> commentItemDatas = new ArrayList<CommentItemData>();
                //由于一个话题对应有多个comment 所以通过for循环进行加载
                for(int i =0; i < commentsBean.size();i++){
                    CommentInfo.CommentsBean commmentBean = commentsBean.get(i);
                    int userId = commmentBean.getUserId();
                    //网络请求获取用户名
                    mCommunity.loadUserInfo(userId, commmentBean.getCommentText(),new CallBackListener() {
                        @Override
                        public void onFinish(Object obj) {
                            CommentItemData data = (CommentItemData) obj;
                            //进行回调
//                            mCommunityView.addOneComment(commentDetialLinear,view,data.getUserName(),data.getCommentText());
                            commentItemDatas.add(data);

                            if(commentItemDatas.size() == commentsBean.size()){
                                LogUtil.i("评论已全部加载完成");
                                mCommunityView.setCommentAdapter(commentDetialLinear,articleId,commentItemDatas);
                            }

                        }

                        @Override
                        public void onError(Exception e) {
                            e.printStackTrace();
                        }
                    });
                }
            }

            @Override
            public void onError(Exception e) {
                e.printStackTrace();
            }

        });
    }


}
