package webapp.shangru.project.com.websiteapp.adapter;

import android.content.Context;
import android.graphics.Bitmap;
import android.text.Html;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;
import com.nostra13.universalimageloader.core.assist.ImageScaleType;
import com.nostra13.universalimageloader.core.display.FadeInBitmapDisplayer;

import java.util.List;

import webapp.shangru.project.com.crawler.bean.Blog;
import webapp.shangru.project.com.websiteapp.R;

/**
 * Created by Shangru on 8/16/16.
 */
public class ContentAdapter extends BaseAdapter {
    private LayoutInflater mInflater;
    private List<Blog> mData;
    // Use open source library ImageLoader to load data
    private ImageLoader mImgLoader;
    private DisplayImageOptions mOptions;

    private final class ViewHolder {
        TextView mContent;
        ImageView mImg;
    }

    public ContentAdapter(Context context) {
        this.mInflater = LayoutInflater.from(context);
        this.mImgLoader.init(ImageLoaderConfiguration.createDefault(context));
        this.mOptions = new DisplayImageOptions.Builder().showStubImage(R.drawable.images)
                .showImageForEmptyUri(R.drawable.images).showImageOnFail(R.drawable.images).cacheInMemory()
                .cacheOnDisc().imageScaleType(ImageScaleType.EXACTLY).bitmapConfig(Bitmap.Config.RGB_565)
                .displayer(new FadeInBitmapDisplayer(300)).build();
    }

    public void addList(List<Blog> list) {
        mData.addAll(list);
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
    public int getItemViewType(int position) {
        switch (mData.get(position).getType()) {
            case Blog.BlogElement.TITLE:
                return 0;
            case Blog.BlogElement.CATEGORY:
                return 1;
            case Blog.BlogElement.CONTENT:
                return 2;
            case Blog.BlogElement.IMG:
                return 3;
            default:
                return -1;
        }
    }

    @Override
    public int getViewTypeCount() {
        return 4;
    }

    @Override
    public boolean isEnabled(int position) {
        if (mData.get(position).getType() == Blog.BlogElement.IMG) {
            return true;
        }
        return false;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        Blog blog = mData.get(position);
        Log.e("XXX", blog.toString());

        ViewHolder holder = null;
        if (convertView == null) {
            switch (blog.getType()) {
                case Blog.BlogElement.TITLE:
                    convertView = mInflater.inflate(R.layout.item_title, null);
                    holder.mContent = (TextView) convertView.findViewById(R.id.text);
                    break;
                case Blog.BlogElement.CATEGORY:
                    convertView = mInflater.inflate(R.layout.item_category, null);
                    holder.mContent = (TextView) convertView.findViewById(R.id.text);
                    break;
                case Blog.BlogElement.CONTENT:
                    convertView = mInflater.inflate(R.layout.item_content, null);
                    holder.mContent = (TextView) convertView.findViewById(R.id.text);
                    break;
                case Blog.BlogElement.IMG:
                    convertView = mInflater.inflate(R.layout.item_img, null);
                    holder.mImg = (ImageView) convertView.findViewById(R.id.imageView);
                    break;
            }
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }

        if (blog != null) {
            switch (blog.getType()) {
                case Blog.BlogElement.TITLE:
                    holder.mContent.setText(blog.getTitle());
                    break;
                case Blog.BlogElement.CATEGORY:
                    holder.mContent.setText(blog.getCategory());
                    break;
                case Blog.BlogElement.CONTENT:
                    holder.mContent.setText("\u3000\u3000" + Html.fromHtml(blog.getContent()));
                    break;
                case Blog.BlogElement.IMG:
                    mImgLoader.displayImage(blog.getImageLink(), holder.mImg, mOptions);
                    break;
                default:
                    break;
            }
        }

        return convertView;
    }
}
