package com.parse.broker.helper;//package com.parse.broker.helper;
//
//import android.app.Activity;
//import android.view.View;
//import android.widget.LinearLayout;
//import android.widget.TextView;
//
//import com.flipkart.analytics.components.AnalyticsEvent;
//import com.flipkart.analytics.components.AnalyticsManager;
//import com.flipkart.seller.R;
//import com.flipkart.seller.analytics.AnalyticsCategory;
//import com.flipkart.seller.database.DatabaseManager;
//import com.flipkart.seller.database.dao.SellerProfileTbl;
//import com.flipkart.seller.models.AppScreen;
//
///**
// * Created by shikharshrivastav on 18/02/15.
// */
//public class NavigationDrawerLayoutHelper {
//
//    private final ItemSelectedInterface itemSelectedInterface;
//    private final Activity activity;
//    private LinearLayout home;
//    @SuppressWarnings("FieldCanBeLocal")
//    private View listing;
//    private View orders;
//    private View payments;
//    private View returns;
//    private View account;
////    @SuppressWarnings("FieldCanBeLocal")
////    private View mFaq;
//    @SuppressWarnings("FieldCanBeLocal")
////    private View mReportIssue;
////    private View rateApp;
//    private View feedback;
////    private View sellerLearningCentre;
//    private View logout;
//
////    private TextView profileImage;
//    private TextView profileEmail;
//    private TextView profileName;
//
//    public NavigationDrawerLayoutHelper(ItemSelectedInterface itemSelectedInterface, Activity activity) {
//        this.itemSelectedInterface = itemSelectedInterface;
//        this.activity = activity;
//    }
//
//    public void initializeViews(View view) {
//
//        home = (LinearLayout) view.findViewById(R.id.drawer_home);
//        listing = view.findViewById(R.id.drawer_listing);
//        orders = view.findViewById(R.id.drawer_orders);
//        payments = view.findViewById(R.id.drawer_payments);
//        returns = view.findViewById(R.id.drawer_returns);
//        account = view.findViewById(R.id.chosen_account_view);
//
////        rateApp = view.findViewById(R.id.drawer_rate_app);
//        feedback = view.findViewById(R.id.drawer_feedback);
////        sellerLearningCentre = view.findViewById(R.id.drawer_seller_learning_center);
////        mFaq = view.findViewById(R.id.drawer_faq);
////        mReportIssue = view.findViewById(R.id.drawer_report_issue);
//
//        profileEmail = (TextView) view.findViewById(R.id.profile_email_text);
//        profileName = (TextView) view.findViewById(R.id.profile_name_text);
////        profileImage = (TextView) view.findViewById(R.id.profile_image);
//        logout = view.findViewById(R.id.drawer_logout);
//
//        // TODO Set listeners after creating navigation drawer layout
//        setListeners();
//
//        setUpProfile();
//    }
//
//    private void setUpProfile() {
//        SellerProfileTbl lSellerProfile = DatabaseManager.getInstance().getSellerDetails();
//        if (lSellerProfile != null) {
//            profileName.setText(lSellerProfile.getRegisteredUsername());
//            profileEmail.setText(lSellerProfile.getRegisteredEmailId());
////            profileImage.setText(lSellerProfile.getPrimaryContactName().subSequence(0, 1));
//        }
//    }
//
//    private void setListeners() {
//        home.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                // Attach home page and remove back stack
////                headerSelected(Header.Home);
//                giveSelectionCallback(AppScreen.HOME);
//            }
//        });
//
//        listing.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                giveSelectionCallback(AppScreen.LISTINGS);
//            }
//        });
//
//        orders.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                giveSelectionCallback(AppScreen.ORDERS);
//            }
//        });
//
//        payments.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                giveSelectionCallback(AppScreen.PAYMENTS);
//            }
//        });
//
//        returns.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                giveSelectionCallback(AppScreen.RETURNS);
//            }
//        });
//
//        account.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                // Attach home page and remove back stack
//                giveSelectionCallback(AppScreen.ACCOUNT);
////                headerSelected(Header.Settings);
//            }
//        });
//
////        mReportIssue.setOnClickListener(new View.OnClickListener() {
////            @Override
////            public void onClick(View view) {
////                //Start helpshift here
////                ErrorReporter.getInstance().showGrievancesScreen(activity);
////            }
////        });
//
////        mFaq.setOnClickListener(new View.OnClickListener() {
////            @Override
////            public void onClick(View view) {
////                //
////                ErrorReporter.getInstance().showFAQScreen(activity);
////            }
////        });
//
////        rateApp.setOnClickListener(new View.OnClickListener() {
////            @Override
////            public void onClick(View view) {
////                UIUtils.goToPlayStore(activity);
////            }
////        });
//
////        sellerLearningCentre.setOnClickListener(new View.OnClickListener() {
////            @Override
////            public void onClick(View view) {
////                UIUtils.goToPlayStore(activity);
////            }
////        });
//
//        feedback.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
////                UIUtils.goToPlayStore(activity);
//                if (itemSelectedInterface != null)
//                    itemSelectedInterface.onFeedbackSelected();
//            }
//        });
//
//
//        logout.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                sendLogoutRequest();
//            }
//        });
//    }
//
//    public void updateNavDrawerItemsUI(AppScreen appScreen) {
//        deselectAllOptions();
//        switch (appScreen) {
//            case HOME:
//                home.setActivated(true);
//                break;
//            case LISTINGS:
//                listing.setActivated(true);
//                break;
//            case ORDERS:
//                orders.setActivated(true);
//                break;
//            case PAYMENTS:
//                payments.setActivated(true);
//                break;
//            case RETURNS:
//                returns.setActivated(true);
//                break;
//            case ACCOUNT:
////                account.setActivated(true);
//                break;
//        }
//    }
//
//    private void deselectAllOptions() {
//        home.setActivated(false);
//        listing.setActivated(false);
//        orders.setActivated(false);
//        payments.setActivated(false);
//        returns.setActivated(false);
////        account.setActivated(false);
//    }
//
//    private void giveSelectionCallback(AppScreen appScreen) {
//        if (itemSelectedInterface != null) {
//            itemSelectedInterface.onItemSelected(appScreen);
//        }
//    }
//
//    private void sendLogoutRequest() {
//        if (itemSelectedInterface != null)
//            itemSelectedInterface.onLogoutSelected();
//    }
//
//    /**
//     * Interface to communicate with navdrawer actions
//     */
//    public interface ItemSelectedInterface {
//        public void onItemSelected(AppScreen appScreen);
//
//        public void onLogoutSelected();
//
//        public void onFeedbackSelected();
//    }
//}
