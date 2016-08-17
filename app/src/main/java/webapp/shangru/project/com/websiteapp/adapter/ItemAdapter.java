package webapp.shangru.project.com.websiteapp.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;
import com.nostra13.universalimageloader.core.display.FadeInBitmapDisplayer;
import com.nostra13.universalimageloader.core.display.RoundedBitmapDisplayer;

import java.util.*;

import webapp.shangru.project.com.crawler.bean.BaseItem;
import webapp.shangru.project.com.crawler.bean.ImageItem;
import webapp.shangru.project.com.crawler.bean.NewsItem;
import webapp.shangru.project.com.crawler.dukecssa.DataUtil;
import webapp.shangru.project.com.websiteapp.R;

/**
 * Created by Shangru on 8/14/16.
 */
public class ItemAdapter extends BaseAdapter {
    private LayoutInflater mInflater;
    private List<BaseItem> mData;
    // Use open source library ImageLoader to load data
    private ImageLoader mImgLoader;
    private DisplayImageOptions mOptions;

    private final class ViewHolder {
        TextView mTitle;
        TextView mContent;
        TextView mDate;
        ImageView mImg;
    }

    public ItemAdapter(Context context, List<BaseItem> list) {
        this.mData = list;
        this.mInflater = LayoutInflater.from(context);
        this.mImgLoader = ImageLoader.getInstance();
        this.mImgLoader.init(ImageLoaderConfiguration.createDefault(context));
        this.mOptions = new DisplayImageOptions.Builder().showStubImage(R.drawable.images)
                .showImageForEmptyUri(R.drawable.images).showImageOnFail(R.drawable.images).cacheInMemory().cacheOnDisk(true)
                .displayer(new RoundedBitmapDisplayer(20)).displayer(new FadeInBitmapDisplayer(300)).build();
    }

    public void addAll(List<BaseItem> list) {
        this.mData.addAll(list);
    }

    public void setData(List<BaseItem> list) {
        this.mData.clear();
        this.mData.addAll(list);
    }

    @Override
    public int getCount() {
        return mData.size();
    }

    @Override
    public Object getItem(int position) {
        return mData.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder = null;

        if (convertView == null) {
            //convertView = mInflater.inflate(R.layout., null);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }

        BaseItem Item = mData.get(position);
        holder.mTitle.setText(DataUtil.ToDoubleByteChar(Item.getTitle()));
        holder.mContent.setText(Item.getContent());
        if (Item instanceof NewsItem) {
            holder.mDate.setText(((NewsItem) Item).getDate());
        }
        if (Item instanceof ImageItem) {
            if (((ImageItem) Item).getImgLink() != null) {
                holder.mImg.setVisibility(View.VISIBLE);
                mImgLoader.displayImage(((ImageItem) Item).getImgLink(), holder.mImg, mOptions);
            } else {
                holder.mImg.setVisibility(View.GONE);
            }
        }

        return convertView;
    }
}
