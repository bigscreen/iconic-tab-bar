package com.bigscreen.iconictabbar.view;

import android.annotation.TargetApi;
import android.content.Context;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.support.annotation.DrawableRes;
import android.support.annotation.StringRes;
import android.util.AttributeSet;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bigscreen.iconictabbar.R;

public class IconicTab extends LinearLayout {

    private LinearLayout tabItem;
    private ImageView tabIcon;
    private TextView tabText, tabBadge;

    private int tabPosition;
    private int badgeCount;

    private int tabDefaultColor;
    private int tabSelectedColor;

    private OnTabClickListener onTabClickListener;

    public IconicTab(Context context) {
        super(context);
        inflateView();
    }

    public IconicTab(Context context, AttributeSet attrs) {
        super(context, attrs);
        inflateView();
    }

    public IconicTab(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        inflateView();
    }

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    public IconicTab(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        inflateView();
    }

    private void inflateView() {
        inflate(getContext(), R.layout.layout_tab_item, this);

        tabItem = (LinearLayout) findViewById(R.id.tab_item);
        tabIcon = (ImageView) findViewById(R.id.tab_item_icon);
        tabText = (TextView) findViewById(R.id.tab_item_text);
        tabBadge = (TextView) findViewById(R.id.tab_item_badge);

        tabDefaultColor = getResources().getColor(R.color.defaultColor);
        tabSelectedColor = getResources().getColor(R.color.defaultSelectedColor);

        tabIcon.setColorFilter(tabDefaultColor);
        tabText.setTextColor(tabDefaultColor);

        tabText.setVisibility(GONE);
        tabBadge.setVisibility(GONE);
    }

    public void bindData(int position, @DrawableRes int iconResId, @StringRes int textResId) {
        tabPosition = position;
        tabIcon.setImageResource(iconResId);
        tabText.setText(textResId);
        setBadgeCount(0);
        onTabItemClick();
    }

    public void bindData(int position, @DrawableRes int iconResId, String text) {
        tabPosition = position;
        tabIcon.setImageResource(iconResId);
        tabText.setText(text);
        setBadgeCount(0);
        onTabItemClick();
    }

    public void bindData(int position, Drawable iconDrawable, String text) {
        tabPosition = position;
        tabIcon.setImageDrawable(iconDrawable);
        tabText.setText(text);
        setBadgeCount(0);
        onTabItemClick();
    }

    public void setTabIcon(@DrawableRes int iconResId) {
        tabIcon.setImageResource(iconResId);
    }

    public void setTabIcon(Drawable iconDrawable) {
        tabIcon.setImageDrawable(iconDrawable);
    }

    public void setTabText(@StringRes int stringResId) {
        tabText.setText(stringResId);
    }

    public void setTabText(String text) {
        tabText.setText(text);
    }

    public void setTabDefaultColor(int tabDefaultColor) {
        this.tabDefaultColor = tabDefaultColor;
    }

    public void setTabSelectedColor(int tabSelectedColor) {
        this.tabSelectedColor = tabSelectedColor;
    }

    public void setSelected() {
        tabIcon.setColorFilter(tabSelectedColor);
        tabText.setTextColor(tabSelectedColor);
        tabText.setVisibility(VISIBLE);
    }

    public void setUnselected() {
        tabIcon.setColorFilter(tabDefaultColor);
        tabText.setTextColor(tabDefaultColor);
        tabText.setVisibility(GONE);
    }

    public void setBadgeCount(int badgeCount) {
        this.badgeCount = badgeCount;
        if (badgeCount > 0) {
            tabBadge.setVisibility(VISIBLE);
            tabBadge.setText(String.format("%s", badgeCount));
        } else {
            tabBadge.setVisibility(GONE);
        }

    }

    public void setOnTabClickListener(OnTabClickListener onTabClickListener) {
        this.onTabClickListener = onTabClickListener;
    }

    private void onTabItemClick() {
        tabItem.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                if (onTabClickListener != null)
                    onTabClickListener.onTabClick(IconicTab.this, tabPosition);
            }
        });
    }

    public interface OnTabClickListener {
        void onTabClick(IconicTab tabBottomBar, int position);
    }
}
