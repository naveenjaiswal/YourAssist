package com.example.asifsheikh.yourassist.fragments;

import android.app.Activity;
import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.example.asifsheikh.yourassist.Database.FeedReaderSubTaskDbHelper;
import com.example.asifsheikh.yourassist.R;
import com.example.asifsheikh.yourassist.SubTaskHomeActivity;
import com.example.asifsheikh.yourassist.TaskHomeActivity;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by asifsheikh on 19/10/15.
 */
public class SubTaskHomeFragment extends Fragment implements View.OnClickListener{

    Context thiscontext;
    private RelativeLayout TaskDetailScreenLayout;
    private EditText ed_sub_task_name;
    private EditText ed_subtask_desp;
    private Button bt_save_button;
    private FeedReaderSubTaskDbHelper mDbHelper;
    private OnFragmentInteractionListener mListener;



    public static SubTaskHomeFragment newInstance(int section) {
        SubTaskHomeFragment fragment = new SubTaskHomeFragment();
        return fragment;
    }

    public SubTaskHomeFragment(){

    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            //mParam1 = getArguments().getInt(ARG_SECTION_NUMBER);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        thiscontext = container.getContext();
        mDbHelper = new FeedReaderSubTaskDbHelper(getActivity());
        TaskDetailScreenLayout = (RelativeLayout) inflater.inflate(R.layout.subtask_home_activity, container, false);
        ed_sub_task_name = (EditText) TaskDetailScreenLayout.findViewById(R.id.ed_sub_task_name);
        ed_subtask_desp = (EditText) TaskDetailScreenLayout.findViewById(R.id.subtask_description);
        bt_save_button = (Button) TaskDetailScreenLayout.findViewById(R.id.bt_subtask_save);
        bt_save_button.setOnClickListener(this);
        ed_sub_task_name.setText(SubTaskHomeActivity.msubtask.getSubtask_name());
        ed_subtask_desp.setText(SubTaskHomeActivity.msubtask.getSubtask_description());
        return TaskDetailScreenLayout;
    }


    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        try {
            mListener = (OnFragmentInteractionListener) activity;
        } catch (ClassCastException e) {
            throw new ClassCastException(activity.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.bt_subtask_save:
                SubTaskHomeActivity.msubtask.setSubtask_name(ed_sub_task_name.getText().toString());
                SubTaskHomeActivity.msubtask.setSubtask_description(ed_subtask_desp.getText().toString());
                mDbHelper.update_subtask(SubTaskHomeActivity.msubtask);
                Toast.makeText(getContext(), "Sub Task upadated", Toast.LENGTH_LONG).show();
                getActivity().onBackPressed();
        }

    }


    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p/>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        public void onFragmentInteraction(Uri uri);
    }
}
