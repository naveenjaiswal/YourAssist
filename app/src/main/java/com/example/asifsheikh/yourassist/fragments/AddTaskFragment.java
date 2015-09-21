package com.example.asifsheikh.yourassist.fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;

/**
 * Created by Admin on 9/22/2015.
 */
public class AddTaskFragment extends Fragment {

    public static AddTaskFragment newInstance(int section) {
        AddTaskFragment fragment = new AddTaskFragment();
        //fragment.setArguments(args);
        return fragment;
    }
}
