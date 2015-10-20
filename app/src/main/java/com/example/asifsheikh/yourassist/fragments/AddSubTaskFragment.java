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

import com.example.asifsheikh.yourassist.Database.FeedReaderSubTaskDbHelper;
import com.example.asifsheikh.yourassist.R;
import com.example.asifsheikh.yourassist.SubTaskDashboard;
import com.example.asifsheikh.yourassist.model.SubTask;

/**
 * Created by asifsheikh on 19/10/15.
 */
public class AddSubTaskFragment extends Fragment implements View.OnClickListener{


    Context thiscontext;
    private RelativeLayout AddSubTaskLayout;
    private FeedReaderSubTaskDbHelper mDbHelper;
    private EditText ed_task_name;
    private EditText ed_task_desp;


    private OnFragmentInteractionListener mListener;

    public static AddSubTaskFragment newInstance(int section) {
        AddSubTaskFragment fragment = new AddSubTaskFragment();
        return fragment;
    }

    public AddSubTaskFragment(){


    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        thiscontext = container.getContext();
        AddSubTaskLayout = (RelativeLayout) inflater.inflate(R.layout.activity_addsubtask, container, false);
        ed_task_name = (EditText) AddSubTaskLayout.findViewById(R.id.ed_sub_task_name);
        ed_task_desp = (EditText) AddSubTaskLayout.findViewById(R.id.subtask_description);
       /*bt_create_subtask = (Button) AddSubTaskLayout.findViewById(R.id.bt_subtask_create);
        bt_create_subtask.setOnClickListener(this);*/
        return AddSubTaskLayout;
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

    public SubTask get_tasksub(){
        SubTask new_task = new SubTask();
        mDbHelper = new FeedReaderSubTaskDbHelper(getActivity());
        //YourAssistApp.getAppInstance().incrementTasknumber();
        new_task.setSubtask_id(0);
        new_task.setTask_id(SubTaskDashboard.task.getTask_id());
        new_task.setSubtask_id(mDbHelper.getnextsubtask_id(SubTaskDashboard.task.getTask_id()));
        new_task.setSubtask_name(ed_task_name.getText().toString());
        new_task.setSubtask_description(ed_task_desp.getText().toString());
        return new_task;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            /*case R.id.bt_subtask_create:
                SubTask new_task = new SubTask();
                mDbHelper = new FeedReaderSubTaskDbHelper(getActivity());
                //YourAssistApp.getAppInstance().incrementTasknumber();
                new_task.setTask_id(SubTaskDashboard.task.getTask_id());
                new_task.setSubtask_id(mDbHelper.getnextsubtask_id(SubTaskDashboard.task.getTask_id()));
                new_task.setSubtask_name(ed_task_name.getText().toString());
                new_task.setSubtask_description(ed_task_desp.getText().toString());
                mDbHelper.inserttask(new_task);

                break;*/
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
