package com.once.test.fragment;

import android.app.Fragment;
import android.content.Context;
import android.content.res.Configuration;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.once.test.R;

//import android.support.v4.app.Fragment;不支持在布局文件xml中使用标签fragment
//import android.app.Fragment;支持使用fragment标签

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link TestFragment1#newInstance} factory method to
 * create an instance of this fragment.
 */
public class TestFragment1 extends Fragment {

    private final String TAG = "TestFragment1";

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public TestFragment1() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment TestFragment1.
     */
    // TODO: Rename and change types and number of parameters
    public static TestFragment1 newInstance(String param1, String param2) {
        TestFragment1 fragment = new TestFragment1();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        Log.i(TAG,"[onAttach]");
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.i(TAG,"[onCreate]");
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        Log.i(TAG,"[onCreateView]");
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_test1, container, false);
    }



    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        Log.i(TAG,"[onActivityCreated]");
    }

    @Override
    public void onStart() {
        super.onStart();
        Log.i(TAG,"[onStart]");
    }

    @Override
    public void onResume() {
        super.onResume();
        Log.i(TAG,"[onResume]");
    }

    @Override
    public void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        Log.i(TAG,"[onSaveInstanceState]");
    }

    @Override
    public void onPause() {
        super.onPause();
        Log.i(TAG,"[onPause]");
    }

    @Override
    public void onStop() {
        super.onStop();
        Log.i(TAG,"[onStop]");
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        Log.i(TAG,"[onDestroyView]");
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.i(TAG,"[onDestroy]");
    }

    @Override
    public void onDetach() {
        super.onDetach();
        Log.i(TAG,"[onDetach]");
    }


    /*************************************************************************/
    @Override
    public void onHiddenChanged(boolean hidden) {
        super.onHiddenChanged(hidden);
        Log.i(TAG,"[onHiddenChanged]");
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        Log.i(TAG,"[onViewCreated]");
    }

    @Override
    public void onViewStateRestored(Bundle savedInstanceState) {
        super.onViewStateRestored(savedInstanceState);
        Log.i(TAG,"[onViewStateRestored]");
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        Log.i(TAG,"[onConfigurationChanged]");
    }

    @Override
    public void onAttachFragment(Fragment childFragment) {
        super.onAttachFragment(childFragment);
    }


}