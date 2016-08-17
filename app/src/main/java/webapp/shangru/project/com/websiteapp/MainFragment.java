package webapp.shangru.project.com.websiteapp;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.*;

import me.maxwin.view.IXListViewRefreshListener;
import me.maxwin.view.XListView;
import webapp.shangru.project.com.crawler.bean.BaseItem;
import webapp.shangru.project.com.crawler.biz.ItemBiz;
import webapp.shangru.project.com.crawler.dukecssa.URLUtil;
import webapp.shangru.project.com.websiteapp.adapter.ItemAdapter;

/**
 * Created by Shangru on 8/3/16.
 */
@SuppressLint("ValidFragment")
public class MainFragment extends Fragment implements IXListViewRefreshListener, XListView.IXListViewListener {
    // Default news type
    private int newsType = URLUtil.NEWS_TYPE_NEWS;
    private ItemBiz mItemBiz;

    private XListView mXListView;
    private ItemAdapter mAdapter;
    private List<BaseItem> mData = new ArrayList<>();

    public MainFragment(int newsType) {
        this.newsType = newsType;
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.tab_item_fragment_main, null);
        TextView tip = (TextView) view.findViewById(R.id.id_tip);
        tip.setText(TabAdapter.TITLES[newsType]);

        return view;
    }

    @Override
    public void onRefresh() {

    }

    @Override
    public void onLoadMore() {

    }
}
