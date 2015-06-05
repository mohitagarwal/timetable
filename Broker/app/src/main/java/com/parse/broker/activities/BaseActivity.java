package com.parse.broker.activities;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.ListView;

import com.parse.broker.R;
import com.parse.broker.interfaces.FragmentAttachedInterface;

import java.util.ArrayList;

/**
 * Created by mohit.tibrewal on 25/11/14.
 */
public abstract class BaseActivity extends AppCompatActivity implements FragmentAttachedInterface {

    public static final String TAG = "BaseActivity";

    // Primary toolbar
    private Toolbar mActionBarToolbar;

    private int mThemedStatusBarColor;
    private boolean isActionBarRestoreNeeded = false;
    private CharSequence mTitle;

    private int mActionBarAutoHideSensivity = 0;
    private int mActionBarAutoHideMinY = 0;
    private int mActionBarAutoHideSignal = 0;
    private boolean mActionBarShown = true;

    // When set, these components will be shown/hidden in sync with the action bar
    // to implement the "quick recall" effect (the Action Bar and the header views disappear
    // when you scroll down a list, and reappear quickly when you scroll up).
    private ArrayList<View> mHideableHeaderViews = new ArrayList<View>();

    // Durations for certain animations we use:
    private static final int HEADER_HIDE_ANIM_DURATION = 300;
    private View toolbarContentView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mTitle = getTitle();

        mThemedStatusBarColor = getResources().getColor(R.color.theme_primary_dark);
    }

    @Override
    public void setContentView(int layoutResID) {
        super.setContentView(layoutResID);
        getActionBarToolbar();
    }

    protected void addViewToToolbar(@NonNull int viewRes, ViewGroup rootView) {
        toolbarContentView = getLayoutInflater().inflate(viewRes, null);
        rootView.addView(toolbarContentView);
    }

    public View getToolbarContentView() {
        return toolbarContentView;
    }

    public boolean removeToolbarContentView(ViewGroup rootView) {
        if (toolbarContentView != null && rootView.indexOfChild(toolbarContentView) != -1) {
            rootView.removeView(toolbarContentView);
            return true;
        }
        return false;
    }

    // Set up Swipe Refresh if using

    /**
     * Initializes the Action Bar auto-hide (aka Quick Recall) effect.
     */
    private void initActionBarAutoHide() {
        mActionBarAutoHideMinY = getResources().getDimensionPixelSize(
                R.dimen.action_bar_auto_hide_min_y);
        mActionBarAutoHideSensivity = getResources().getDimensionPixelSize(
                R.dimen.action_bar_auto_hide_sensivity);
    }

    /**
     * Indicates that the main content has scrolled (for the purposes of showing/hiding
     * the action bar for the "action bar auto hide" effect). currentY and deltaY may be exact
     * (if the underlying view supports it) or may be approximate indications:
     * deltaY may be INT_MAX to mean "scrolled forward indeterminately" and INT_MIN to mean
     * "scrolled backward indeterminately".  currentY may be 0 to mean "somewhere close to the
     * start of the list" and INT_MAX to mean "we don't know, but not at the start of the list"
     */
    private void onMainContentScrolled(int currentY, int deltaY) {
        if (deltaY > mActionBarAutoHideSensivity) {
            deltaY = mActionBarAutoHideSensivity;
        } else if (deltaY < -mActionBarAutoHideSensivity) {
            deltaY = -mActionBarAutoHideSensivity;
        }

        if (Math.signum(deltaY) * Math.signum(mActionBarAutoHideSignal) < 0) {
            // deltaY is a motion opposite to the accumulated signal, so reset signal
            mActionBarAutoHideSignal = deltaY;
        } else {
            // add to accumulated signal
            mActionBarAutoHideSignal += deltaY;
        }

        boolean shouldShow = currentY < mActionBarAutoHideMinY ||
                (mActionBarAutoHideSignal <= -mActionBarAutoHideSensivity);
        autoShowOrHideActionBar(shouldShow);
    }

    protected Toolbar getActionBarToolbar() {
        if (mActionBarToolbar == null) {
            mActionBarToolbar = (Toolbar) findViewById(R.id.toolbar_actionbar);
            if (mActionBarToolbar != null) {
                setSupportActionBar(mActionBarToolbar);
            }
        }

        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }
        return mActionBarToolbar;
    }

    protected void autoShowOrHideActionBar(boolean show) {
        if (show == mActionBarShown) {
            return;
        }

        mActionBarShown = show;
        onActionBarAutoShowOrHide(show);
    }

    protected void enableActionBarAutoHide(final ListView listView) {
        initActionBarAutoHide();
        listView.setOnScrollListener(new AbsListView.OnScrollListener() {
            final static int ITEMS_THRESHOLD = 3;
            int lastFvi = 0;

            @Override
            public void onScrollStateChanged(AbsListView view, int scrollState) {
            }

            @Override
            public void onScroll(AbsListView view, int firstVisibleItem, int visibleItemCount, int totalItemCount) {
                onMainContentScrolled(firstVisibleItem <= ITEMS_THRESHOLD ? 0 : Integer.MAX_VALUE,
                        lastFvi - firstVisibleItem > 0 ? Integer.MIN_VALUE :
                                lastFvi == firstVisibleItem ? 0 : Integer.MAX_VALUE
                );
                lastFvi = firstVisibleItem;
            }
        });
    }

    protected void registerHideableHeaderView(View hideableHeaderView) {
        if (!mHideableHeaderViews.contains(hideableHeaderView)) {
            mHideableHeaderViews.add(hideableHeaderView);
        }
    }

    protected void deregisterHideableHeaderView(View hideableHeaderView) {
        if (mHideableHeaderViews.contains(hideableHeaderView)) {
            mHideableHeaderViews.remove(hideableHeaderView);
        }
    }

    protected void onActionBarAutoShowOrHide(boolean shown) {
        ActionBar ab = getSupportActionBar();
//        for (final View view : mHideableHeaderViews) {
        if (shown) {
            if (!ab.isShowing()) {
//                    getActionBarToolbar().animate().translationY(0).setInterpolator(new DecelerateInterpolator()).start();
                ab.show();
            }
        } else {
            if (ab.isShowing()) {
//                    getActionBarToolbar().animate().translationY(-getActionBarToolbar().getHeight()).setInterpolator(new DecelerateInterpolator()).start();
                ab.hide();
            }
        }
//    }
    }

    @Override
    protected void onStart() {
        super.onStart();
        if (isActionBarRestoreNeeded) {
            setActionBartitle(mTitle);
        }
    }

    protected void setActionBartitle(CharSequence title) {
        ActionBar actionBar = getSupportActionBar();
        if (actionBar == null) {
            isActionBarRestoreNeeded = true;
            mTitle = title;
        } else {
            actionBar.setDisplayHomeAsUpEnabled(true);
            actionBar.setTitle(title);
            isActionBarRestoreNeeded = false;
            setTitle(title);
        }
    }

    public int getThemedStatusBarColor() {
        return mThemedStatusBarColor;
    }

    @Override
    public void onSectionAttached(CharSequence title) {
        if (title != null) {
            setActionBartitle(title);
        }
    }

}
