//package com.example.expendlistview.Helper;
//
//
//
//
//import androidx.fragment.app.Fragment;
//
//import androidx.fragment.app.FragmentManager;
//import androidx.fragment.app.FragmentTransaction;
//
//import com.example.expendlistview.Fragments.FragmentContent;
//import com.example.expendlistview.Interface.NavigationManager;
//import com.example.expendlistview.MainActivity;
//import com.example.expendlistview.R;
//
//public class FragmentNavigationManager implements NavigationManager {
//    private static FragmentNavigationManager mInstance;
//    private FragmentManager mFragmentManager;
//    private MainActivity mainActivity;
//
//    public static FragmentNavigationManager getmInstance(MainActivity mainActivity){
//        if(mInstance==null)
//            mInstance = new FragmentNavigationManager();
//        mInstance.configure(mainActivity);
//        return  mInstance;
//    }
//
//    private void configure(MainActivity mainActivity) {
//        mainActivity = mainActivity;
//        mFragmentManager =mainActivity.getSupportFragmentManager();
//    }
//
//
//    @Override
//    public void showFragment(String title) {
//        showFragment(FragmentContent.newInstance(title),false);
//
//    }
//    private void showFragment(Fragment fragmentContent, boolean b){
//        FragmentManager fm = mFragmentManager;
//        FragmentTransaction ft = fm.beginTransaction().replace(R.id.container,fragmentContent);
//        ft.addToBackStack(null);
//        if(b)
//            ft.commitAllowingStateLoss();
//        else
//            ft.commit();
//        fm.executePendingTransactions();
//    }
//}
