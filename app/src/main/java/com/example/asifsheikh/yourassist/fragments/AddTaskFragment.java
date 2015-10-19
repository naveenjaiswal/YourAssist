package com.example.asifsheikh.yourassist.fragments;

import android.app.Activity;
import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.Spinner;

import com.example.asifsheikh.yourassist.Database.FeedReaderDbHelper;
import com.example.asifsheikh.yourassist.R;
import com.example.asifsheikh.yourassist.model.Task;
import com.example.asifsheikh.yourassist.utility.Utility;
import com.wdullaer.materialdatetimepicker.date.DatePickerDialog;
import com.wdullaer.materialdatetimepicker.time.RadialPickerLayout;
import com.wdullaer.materialdatetimepicker.time.TimePickerDialog;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * Created by Admin on 9/22/2015.
 */
public class AddTaskFragment extends Fragment implements  TimePickerDialog.OnTimeSetListener, DatePickerDialog.OnDateSetListener{

    Context thiscontext;
    private RelativeLayout AddTakLayout;
    ImageView date_picker_image;
    EditText dateTextView;
    private FeedReaderDbHelper mDbHelper;
    private EditText ed_task_name;
    private EditText ed_task_desp;
    private EditText ed_due_date;
    private Spinner prority_spinner;
    private String[] arraySpinner;

    private OnFragmentInteractionListener mListener;

    public static AddTaskFragment newInstance(int section) {
        AddTaskFragment fragment = new AddTaskFragment();
        //fragment.setArguments(args);
        return fragment;
    }

    public AddTaskFragment(){


    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        thiscontext = container.getContext();
        AddTakLayout = (RelativeLayout) inflater.inflate(R.layout.activity_add_task, container, false);
        ed_task_name = (EditText) AddTakLayout.findViewById(R.id.ed_task_name);
        ed_task_desp = (EditText) AddTakLayout.findViewById(R.id.task_description);
        ed_due_date = (EditText) AddTakLayout.findViewById(R.id.date_textview);
        prority_spinner = (Spinner) AddTakLayout.findViewById(R.id.spinner_priority);
        date_picker_image = (ImageView) AddTakLayout.findViewById(R.id.iv_datepicker);
        dateTextView = (EditText) AddTakLayout.findViewById(R.id.date_textview);
        dateTextView.setText(Utility.getDate(new Date()));
        this.arraySpinner = new String[] {
                "low", "medium", "high"
        };
        Spinner s = (Spinner) AddTakLayout.findViewById(R.id.spinner_priority);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_spinner_item, arraySpinner);
        s.setAdapter(adapter);
        Calendar now = Calendar.getInstance();
        final DatePickerDialog dpd = DatePickerDialog.newInstance(
                this,
                now.get(Calendar.YEAR),
                now.get(Calendar.MONTH),
                now.get(Calendar.DAY_OF_MONTH)
        );

        date_picker_image.setOnClickListener(new View.OnClickListener() {


            @Override
            public void onClick(View v) {

                dpd.show(getActivity().getFragmentManager(), "Datepickerdialog");


            }
        });
        return AddTakLayout;
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

    public void date_picker(){
        Calendar now = Calendar.getInstance();
        DatePickerDialog dpd = DatePickerDialog.newInstance(
                this,
                now.get(Calendar.YEAR),
                now.get(Calendar.MONTH),
                now.get(Calendar.DAY_OF_MONTH)
        );
        dpd.show(getActivity().getFragmentManager(), "Datepickerdialog");
    }

    @Override
    public void onDateSet(DatePickerDialog datePickerDialog, int year, int monthOfYear, int dayOfMonth) {
        String date = dayOfMonth+"/"+(monthOfYear+1)+"/"+year;
        dateTextView.setText(date);
    }

    public Task get_task(){
        Task new_task = new Task();
        mDbHelper = new FeedReaderDbHelper(getActivity());
        //YourAssistApp.getAppInstance().incrementTasknumber();
        new_task.setTask_id(mDbHelper.getnextTaskId());
        new_task.setTask_name(ed_task_name.getText().toString());
        new_task.setTask_description(ed_task_desp.getText().toString());
        String startDateString = ed_due_date.getText().toString();
        DateFormat df = new SimpleDateFormat("MM/dd/yyyy");
        Date startDate;
        Date current_date = new Date();
        //Date dueDate;
        try {
            startDate = df.parse(startDateString);
            current_date = df.parse(startDateString);
            //dueDate = df.parse(startDateString);
            /*String newDateString = df.format(startDate);
            System.out.println(newDateString);*/
            new_task.setStart_date(current_date);
            new_task.setDue_date(startDate);
        }  catch (ParseException e) {
            e.printStackTrace();
        }
        new_task.setPriority(prority_spinner.getSelectedItem().toString());
        return new_task;
    }

    @Override
    public void onTimeSet(RadialPickerLayout radialPickerLayout, int i, int i1) {

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
