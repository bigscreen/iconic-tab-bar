package com.bigscreen.iconictabbar.view;

import android.annotation.TargetApi;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.support.annotation.ColorInt;
import android.support.annotation.DrawableRes;
import android.support.annotation.MenuRes;
import android.support.v4.content.ContextCompat;
import android.util.AttributeSet;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.PopupMenu;

import com.bigscreen.iconictabbar.R;
import com.bigscreen.iconictabbar.helper.ConfigHelper;

import java.util.ArrayList;
import java.util.List;


public class IconicTabBar extends FrameLayout implements IconicTab.OnTabClickListener {

    public static final int MAX_TAB = 5;

    private AttributeSet attributeSet;

    private FrameLayout frameTabs;
    private LinearLayout llTabs;

    private List<IconicTab> tabs;

    private int selectedTabPosition;
    private int unSelectedTabPosition;

    private int backgroundColor;
    private int tabDefaultColor;
    private int tabSelectedColor;

    private IconicTab selectedTab;
    private IconicTab unSelectedTab;

    private OnTabSelectedListener onTabSelectedListener;

    public IconicTabBar(Context context) {
        super(context);
        inflateView();
    }

    public IconicTabBar(Context context, AttributeSet attrs) {
        super(context, attrs);
        attributeSet = attrs;
        inflateView();
    }

    public IconicTabBar(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        attributeSet = attrs;
        inflateView();
    }

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    public IconicTabBar(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        attributeSet = attrs;
        inflateView();
    }

    private void inflateView() {
        inflate(getContext(), R.layout.layout_tab_bar, this);
        frameTabs = (FrameLayout) findViewById(R.id.frame_tabs);
        llTabs = (LinearLayout) findViewById(R.id.ll_tabs);
        initStyle();
    }

    private void initStyle() {
        TypedArray styledAttributes = getContext().obtainStyledAttributes(attributeSet, R.styleable.IconicTabBar);
        int menuResId = styledAttributes.getResourceId(R.styleable.IconicTabBar_tabFromMenu, -1);
        backgroundColor = styledAttributes.getColor(R.styleable.IconicTabBar_barBackground, ContextCompat.getColor(getContext(), R.color.white));
        tabDefaultColor = styledAttributes.getColor(R.styleable.IconicTabBar_tabDefaultColor, ContextCompat.getColor(getContext(), R.color.defaultColor));
        tabSelectedColor = styledAttributes.getColor(R.styleable.IconicTabBar_tabSelectedColor, ContextCompat.getColor(getContext(), R.color.defaultSelectedColor));

        frameTabs.setBackgroundColor(backgroundColor);
        if (menuResId != -1) {
            addTabFromMenu(menuResId);
        }

        styledAttributes.recycle();
    }

    public void addTab(@DrawableRes int iconResId, CharSequence text) {
        addTab(0, iconResId, null, text);
    }

    public void addTab(int viewId, @DrawableRes int iconResId, CharSequence text) {
        addTab(viewId, iconResId, null, text);
    }

    public void addTab(Drawable iconDrawable, CharSequence text) {
        addTab(0, 0, iconDrawable, text);
    }

    public void addTab(int viewId, Drawable iconDrawable, CharSequence text) {
        addTab(viewId, 0, iconDrawable, text);
    }

    private void addTab(int viewId, @DrawableRes int iconResId, Drawable iconDrawable, CharSequence text) {
        if (tabs == null) tabs = new ArrayList<>();
        int latestTabsSize = tabs.size();
        if (latestTabsSize > MAX_TAB) throw new UnsupportedOperationException("Cannot add more than 5 tabs.");
        IconicTab tab = new IconicTab(getContext());
        tab.setId(viewId);
        tab.setTabDefaultColor(tabDefaultColor);
        tab.setTabSelectedColor(tabSelectedColor);
        if (iconResId == 0 && iconDrawable != null) {
            tab.bindData(latestTabsSize, iconDrawable, text);
        } else if (iconResId != 0 && iconDrawable == null) {
            tab.bindData(latestTabsSize, iconResId, text);
        } else {
            throw new NullPointerException("Tab icon was not specified.");
        }
        tab.setOnTabClickListener(this);
        tabs.add(tab);
        llTabs.addView(tab);
        initTabSize();
        if (latestTabsSize == 0) {
            selectedTabPosition = 0;
            selectedTab = tab;
            tab.setSelected();
        }
    }

    private void addTabFromMenu(@MenuRes int menuRes) {
        PopupMenu popupMenu = new PopupMenu(getContext(), null);
        Menu menu = popupMenu.getMenu();
        MenuInflater menuInflater = new MenuInflater(getContext());
        menuInflater.inflate(menuRes, menu);
        for (int i = 0; i < menu.size(); i++) {
            MenuItem item = menu.getItem(i);
            addTab(item.getItemId(), item.getIcon(), item.getTitle().toString());
        }
    }

    public IconicTab getTab(int position) {
        return tabs.get(position);
    }

    public void setTabBadge(int tabPosition, int badgeCount) {
        getTab(tabPosition).setBadgeCount(badgeCount);
    }

    public void setOnTabSelectedListener(OnTabSelectedListener onTabSelectedListener) {
        this.onTabSelectedListener = onTabSelectedListener;
    }

    @Override
    public void setBackgroundColor(@ColorInt int backgroundColor) {
        this.backgroundColor = backgroundColor;
        frameTabs.setBackgroundColor(backgroundColor);
    }

    public void setTabDefaultColor(@ColorInt int tabDefaultColor) {
        this.tabDefaultColor = tabDefaultColor;
        for (IconicTab tab : tabs) {
            tab.setTabDefaultColor(tabDefaultColor);
        }
    }

    public void setTabSelectedColor(@ColorInt int tabSelectedColor) {
        this.tabSelectedColor = tabSelectedColor;
        for (IconicTab tab : tabs) {
            tab.setTabSelectedColor(tabSelectedColor);
        }
    }

    @Override
    public void onTabClick(IconicTab tabBottomBar, int position) {
        if (tabBottomBar.equals(selectedTab)) return;
        setUnselectedTab(selectedTab, selectedTabPosition);
        setSelectedTab(tabBottomBar, position);
    }

    private void setSelectedTab(IconicTab tab, int tabPosition) {
        selectedTab = tab;
        selectedTabPosition = tabPosition;
        if (onTabSelectedListener != null) onTabSelectedListener.onSelected(selectedTab, selectedTabPosition);
        selectedTab.setSelectedWithAnimation();
    }

    private void setUnselectedTab(IconicTab tab, int tabPosition) {
        unSelectedTab = tab;
        unSelectedTabPosition = tabPosition;
        if (onTabSelectedListener != null) onTabSelectedListener.onUnselected(unSelectedTab, unSelectedTabPosition);
        unSelectedTab.setUnselectedWithAnimation();
    }

    private void initTabSize() {
        int tabCount = tabs.size();
        int screenWidth = ConfigHelper.getScreenWidth(getContext());
        int tabMaxWidth = ConfigHelper.getPxFromDimenRes(R.dimen.max_tab_width, getContext());
        if ((tabMaxWidth * tabCount) > screenWidth) {
            llTabs.setLayoutParams(new FrameLayout.LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT));
            for (IconicTab tab : tabs) {
                tab.setLayoutParams(new LinearLayout.LayoutParams(0, LayoutParams.MATCH_PARENT, 1f));
                tab.setGravity(Gravity.CENTER);
            }
        } else {
            llTabs.setLayoutParams(new FrameLayout.LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.MATCH_PARENT,
                    Gravity.CENTER_HORIZONTAL));
            for (IconicTab tab : tabs) {
                tab.setLayoutParams(new LinearLayout.LayoutParams(tabMaxWidth, LayoutParams.MATCH_PARENT));
                tab.setGravity(Gravity.CENTER);
            }
        }
    }

    public interface OnTabSelectedListener {
        void onSelected(IconicTab tab, int position);

        void onUnselected(IconicTab tab, int position);
    }

}
