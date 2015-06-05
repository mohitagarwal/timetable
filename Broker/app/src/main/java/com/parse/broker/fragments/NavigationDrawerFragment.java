package com.parse.broker.fragments;//package com.parse.broker.fragments;
//
//import android.app.Activity;
//import android.app.Fragment;
//import android.content.DialogInterface;
//import android.content.res.Configuration;
//import android.os.Bundle;
//import android.support.v4.view.GravityCompat;
//import android.support.v4.widget.DrawerLayout;
//import android.support.v7.app.ActionBarDrawerToggle;
//import android.view.LayoutInflater;
//import android.view.Menu;
//import android.view.MenuInflater;
//import android.view.MenuItem;
//import android.view.View;
//import android.view.ViewGroup;
//import android.widget.Toast;
//
//import com.android.volley.VolleyError;
//import com.flipkart.analytics.components.AnalyticsEvent;
//import com.flipkart.analytics.components.AnalyticsManager;
//import com.flipkart.logging.core.FkLogger;
//import com.flipkart.seller.R;
//import com.flipkart.seller.analytics.AnalyticsCategory;
//import com.flipkart.seller.analytics.AnalyticsScreen;
//import com.flipkart.seller.feedback.activity.FeedbackActivity;
//import com.flipkart.seller.fragments.NetworkBaseFragment;
//import com.flipkart.seller.managers.AccountManager;
//import com.flipkart.seller.managers.PreferenceManager;
//import com.flipkart.seller.models.AppScreen;
//import com.flipkart.seller.navdrawer.helper.NavigationDrawerLayoutHelper;
//import com.flipkart.seller.networking.FkErrorListener;
//import com.flipkart.seller.networking.FkErrorResponse;
//import com.flipkart.seller.networking.FkResponseListener;
//import com.flipkart.seller.networking.ResponseDeserializer;
//import com.flipkart.seller.networking.client.FkNetworkClient;
//import com.flipkart.seller.networking.requests.APIUtils;
//import com.flipkart.seller.networking.requests.FkRequest;
//import com.flipkart.seller.networking.responses.user.logout.LogoutResponse;
//import com.flipkart.seller.utils.FetchResources;
//import com.flipkart.seller.utils.UIUtils;
//import com.nispok.snackbar.SnackbarManager;
//
///**
// * Fragment used for managing interactions for and presentation of a navigation drawer.
// * See the <a href="https://developer.android.com/design/patterns/navigation-drawer.html#Interaction">
// * design guidelines</a> for a complete explanation of the behaviors implemented here.
// */
//public class NavigationDrawerFragment extends Fragment implements NavigationDrawerLayoutHelper.ItemSelectedInterface {
//
//    public static final String TAG = "NavigationDrawerFragment";
//    private static final String DIALOG_TAG = "Dialog " + TAG;
//
//    /**
//     * Remember the position of the selected item.
//     */
//    private static final String STATE_SELECTED_PAGE = "selected_navigation_drawer_page";
//
//    /**
//     * A pointer to the current callbacks instance (the Activity).
//     */
//    private NavigationDrawerCallbacks mCallbacks;
//
//    /**
//     * Helper component that ties the action bar to the navigation drawer.
//     */
//    private ActionBarDrawerToggle mDrawerToggle;
//
//    private NavigationDrawerLayoutHelper navigationDrawerLayoutHelper;
//
//    private DrawerLayout mDrawerLayout;
//    @SuppressWarnings("FieldCanBeLocal")
//    private View mDrawerView;
//    private View mFragmentContainerView;
//    private AppScreen mCurrentSelectedAppScreen;
//    private boolean mFromSavedInstanceState;
//    private boolean mUserLearnedDrawer;
//    private Activity activity;
//
//    public NavigationDrawerFragment() {
//
//    }
//
//    public static NavigationDrawerFragment newInstance() {
//        return new NavigationDrawerFragment();
//    }
//
//    @Override
//    public void onAttach(Activity activity) {
//        super.onAttach(activity);
//        this.activity = activity;
//        try {
//            mCallbacks = (NavigationDrawerCallbacks) activity;
//        } catch (ClassCastException e) {
//            throw new ClassCastException("Activity must implement NavigationDrawerCallbacks.");
//        }
//    }
//
//    @Override
//    public void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        navigationDrawerLayoutHelper = new NavigationDrawerLayoutHelper(this, activity);
//
//        // Read in the flag indicating whether or not the user has demonstrated awareness of the
//        // drawer. See PreferenceManager.PREF_USER_LEARNED_DRAWER for details.
//        mUserLearnedDrawer = PreferenceManager.getInstance().getPrefUserLearnedDrawer();
//
//        if (savedInstanceState != null && savedInstanceState.containsKey(STATE_SELECTED_PAGE)) {
//            mCurrentSelectedAppScreen = (AppScreen) savedInstanceState.getSerializable(STATE_SELECTED_PAGE);
//            mFromSavedInstanceState = true;
//        }
//    }
//
//    @Override
//    public void onActivityCreated(Bundle savedInstanceState) {
//        super.onActivityCreated(savedInstanceState);
//        // Indicate that this fragment would like to influence the set of actions in the action bar.
//        setHasOptionsMenu(true);
//    }
//
//    @Override
//    public View onCreateView(LayoutInflater inflater, ViewGroup container,
//                             Bundle savedInstanceState) {
//
//        mDrawerView = inflater.inflate(
//                R.layout.fragment_nav_drawer, container, false);
//
//        navigationDrawerLayoutHelper.initializeViews(mDrawerView);
//
//        selectItem(AppScreen.HOME);
//
//        return mDrawerView;
//    }
//
//    public boolean isDrawerOpen() {
//        return mDrawerLayout != null && mDrawerLayout.isDrawerOpen(mFragmentContainerView);
//    }
//
//    public void selectItem(AppScreen appScreen) {
//        navigationDrawerLayoutHelper.updateNavDrawerItemsUI(appScreen);
//        mCurrentSelectedAppScreen = appScreen;
//        closeDrawer();
//        if (mCallbacks != null) {
//            mCallbacks.onNavigationDrawerItemSelected(appScreen);
//        }
//    }
//
//    /**
//     * Users of this fragment must call this method to set up the navigation drawer interactions.
//     *
//     * @param fragmentId   The android:id of this fragment in its activity's layout.
//     * @param drawerLayout The DrawerLayout containing this fragment's UI.
//     */
//    public void setUp(int fragmentId, DrawerLayout drawerLayout) {
//        mFragmentContainerView = getActivity().findViewById(fragmentId);
//        mDrawerLayout = drawerLayout;
//
//        // set a custom shadow that overlays the main content when the drawer opens
//        mDrawerLayout.setDrawerShadow(R.drawable.drawer_shadow, GravityCompat.START);
//        // set up the drawer's list view with items and click listener
//
//        // ActionBarDrawerToggle ties together the the proper interactions
//        // between the navigation drawer and the action bar app icon.
//        mDrawerToggle = new ActionBarDrawerToggle(
//                getActivity(),                    /* host Activity */
//                mDrawerLayout,                    /* DrawerLayout object */
////                R.drawable.ic_drawer,             /* nav drawer image to replace 'Up' caret */
//                R.string.navigation_drawer_open,  /* "open drawer" description for accessibility */
//                R.string.navigation_drawer_close  /* "close drawer" description for accessibility */
//        ) {
//            @Override
//            public void onDrawerClosed(View drawerView) {
//                super.onDrawerClosed(drawerView);
//                if (!isAdded()) {
//                    return;
//                }
//                AnalyticsManager.getInstance().sendEvent(new AnalyticsEvent(mCurrentSelectedAppScreen.getAnalyticsCategory().getCategoryName(), "Menu close"));
//                getActivity().invalidateOptionsMenu(); // calls onPrepareOptionsMenu()
//            }
//
//            @Override
//            public void onDrawerOpened(View drawerView) {
//                super.onDrawerOpened(drawerView);
//                if (!isAdded()) {
//                    return;
//                }
//                AnalyticsManager.getInstance().sendEvent(new AnalyticsEvent(mCurrentSelectedAppScreen.getAnalyticsCategory().getCategoryName(), "Menu open"));
//                // hide snackbar if it was showing
//                SnackbarManager.dismiss();
//
//                if (!mUserLearnedDrawer) {
//                    // The user manually opened the drawer; store this flag to prevent auto-showing
//                    // the navigation drawer automatically in the future.
//                    mUserLearnedDrawer = true;
//                    PreferenceManager.getInstance().setPrefUserLearnedDrawer(true);
//                }
//                getActivity().invalidateOptionsMenu(); // calls onPrepareOptionsMenu()
//            }
//        };
//
//        // If the user hasn't 'learned' about the drawer, open it to introduce them to the drawer,
//        // per the navigation drawer design guidelines.
//        if (!mUserLearnedDrawer && !mFromSavedInstanceState) {
//            mDrawerLayout.openDrawer(mFragmentContainerView);
//        }
//
//        // Defer code dependent on restoration of previous instance state.
//        mDrawerLayout.post(new Runnable() {
//            @Override
//            public void run() {
//                mDrawerToggle.syncState();
//            }
//        });
//
//        mDrawerLayout.setDrawerListener(mDrawerToggle);
//    }
//
//    private void makeLogoutRequest() {
//        showProgressDialog(FetchResources.getString(R.string.logout_progress_dialog_text), DIALOG_TAG);
//        FkNetworkClient.getInstance().volleyRequest(APIUtils.logout(new FkResponseListener<LogoutResponse>() {
//            @Override
//            public void onResponse(LogoutResponse response) {
//                hideProgressDialog();
//                if (response != null && response.getCode() == null && response.getMessage() != null) {
//                    Toast.makeText(getActivity(), "Logged out Successfully", Toast.LENGTH_LONG).show();
//                    AccountManager.getInstance().onLogout();
//                }
//                UIUtils.redirectToLoginPage(getActivity(), false);
//            }
//
//            @Override
//            public void onCachedResponse(LogoutResponse response) {
//                onResponse(response);
//            }
//        }, new FkRequest.Parser<LogoutResponse, VolleyError>() {
//            @Override
//            public LogoutResponse parseResponse(String responseString) {
//                return ResponseDeserializer.fromJson(responseString, LogoutResponse.class);
//            }
//
//            @Override
//            public VolleyError parseResponseError(String errorString) {
//                return null;
//            }
//        }, new FkErrorListener() {
//
//            @Override
//            public void onErrorResponse(VolleyError error) {
//                // TODO Add analytics event
//                FkLogger.verbose(TAG, "Failure");
//                hideProgressDialog();
//                showInformationalSnackBar(FetchResources.getString(R.string.error_logging_out));
//            }
//
//            @Override
//            public void onFkErrorResponse(FkErrorResponse.FkErrorResponseType errorType, VolleyError error) {
//                hideProgressDialog();
//                UIUtils.showErrorDialog(getActivity(), errorType);
//            }
//        }), DIALOG_TAG);
//    }
//
//    @Override
//    public void onDetach() {
//        super.onDetach();
//        this.activity = null;
//        mCallbacks = null;
//    }
//
//    @Override
//    public void onSaveInstanceState(Bundle outState) {
//        super.onSaveInstanceState(outState);
//        outState.putSerializable(STATE_SELECTED_PAGE, mCurrentSelectedAppScreen);
//    }
//
//    @Override
//    public void onConfigurationChanged(Configuration newConfig) {
//        super.onConfigurationChanged(newConfig);
//        // Forward the new configuration the drawer toggle component.
//        mDrawerToggle.onConfigurationChanged(newConfig);
//    }
//
//    @Override
//    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
//        // If the drawer is open, show the global app actions in the action bar. See also
//        // showGlobalContextActionBar, which controls the top-left area of the action bar.
//        if (mDrawerLayout != null && isDrawerOpen()) {
//            inflater.inflate(R.menu.global, menu);
////            showGlobalContextActionBar();
//        }
//        super.onCreateOptionsMenu(menu, inflater);
//    }
//
//    @Override
//    public boolean onOptionsItemSelected(MenuItem item) {
//        return mDrawerToggle.onOptionsItemSelected(item) || super.onOptionsItemSelected(item);
//    }
//
////    /**
////     * Per the navigation drawer design guidelines, updates the action bar to show the global app
////     * 'context', rather than just what's in the current screen.
////     */
////    private void showGlobalContextActionBar() {
////        ActionBar actionBar = getActionBar();
//////        actionBar.setDisplayShowTitleEnabled(true);
//////        actionBar.setNavigationMode(ActionBar.NAVIGATION_MODE_STANDARD);
//////        actionBar.setTitle(R.string.app_name);
////    }
//
//    @Override
//    protected String getVolleyTag() {
//        return TAG;
//    }
//
//    @Override
//    protected String getScreenNameForAnalytics() {
//        return AnalyticsScreen.MENU.getScreenName();
//    }
//
//    public void closeDrawer() {
//        if (mDrawerLayout != null) {
//            mDrawerLayout.closeDrawer(mFragmentContainerView);
//        }
//    }
//
//    @Override
//    public void onItemSelected(AppScreen appScreen) {
//        selectItem(appScreen);
//    }
//
//    @Override
//    public void onLogoutSelected() {
//        AnalyticsManager.getInstance().sendEvent(new AnalyticsEvent(AnalyticsCategory.MENU.getCategoryName(), "Logout open"));
//        UIUtils.showLogoutConfirmationDialog(activity, new DialogInterface.OnClickListener() {
//            @Override
//            public void onClick(DialogInterface dialog, int which) {
//                AnalyticsManager.getInstance().sendEvent(new AnalyticsEvent(AnalyticsCategory.MENU.getCategoryName(), "Logout confirm"));
//                makeLogoutRequest();
//            }
//        }, new DialogInterface.OnClickListener() {
//            @Override
//            public void onClick(DialogInterface dialogInterface, int i) {
//                AnalyticsManager.getInstance().sendEvent(new AnalyticsEvent(AnalyticsCategory.MENU.getCategoryName(), "Logout cancel"));
//            }
//        });
//
//    }
//
//    @Override
//    public void onFeedbackSelected() {
//        startActivity(FeedbackActivity.getIntent(activity));
//        AnalyticsManager.getInstance().sendEvent(new AnalyticsEvent(AnalyticsCategory.MENU.getCategoryName(), "Feedback open"));
//        closeDrawer();
//    }
//
//    /**
//     * Callbacks interface that all activities using this fragment must implement.
//     */
//    public static interface NavigationDrawerCallbacks {
//        /**
//         * Called when an item in the navigation drawer is selected.
//         */
//        void onNavigationDrawerItemSelected(AppScreen appScreen);
//    }
//}
