package com.example.asifsheikh.yourassist.fragments;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.asifsheikh.yourassist.Adapter.Card_Adapter;
import com.example.asifsheikh.yourassist.AddTaskActivity;
import com.example.asifsheikh.yourassist.Database.FeedReaderDbHelper;
import com.example.asifsheikh.yourassist.R;
import com.example.asifsheikh.yourassist.TaskHomeActivity;
import com.example.asifsheikh.yourassist.application.YourAssistApp;
import com.example.asifsheikh.yourassist.utility.Utility;
import com.getbase.floatingactionbutton.FloatingActionButton;
import com.getbase.floatingactionbutton.FloatingActionsMenu;
import com.wdullaer.materialdatetimepicker.date.DatePickerDialog;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

/**
 * Created by Admin on 9/23/2015.
 */
public class TaskHomeFragment extends Fragment implements DatePickerDialog.OnDateSetListener , View.OnClickListener{

    Context thiscontext;
    private RelativeLayout TaskDetailScreenLayout;
    ImageView date_picker_image;
    EditText dateTextView;
    private EditText ed_task_name;
    private EditText ed_task_desp;
    private EditText ed_due_date;
    private Spinner prority_spinner;
    private String[] arraySpinner;
    private Button bt_save_button;
    private FeedReaderDbHelper mDbHelper;
    private RecyclerView mRecyclerView;
    RecyclerView.Adapter mAdapter;
    RecyclerView.LayoutManager mLayoutManager;
    private OnFragmentInteractionListener mListener;

    public static TaskHomeFragment newInstance(int section) {
        TaskHomeFragment fragment = new TaskHomeFragment();
        return fragment;
    }

    public TaskHomeFragment(){




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
        mDbHelper = new FeedReaderDbHelper(getActivity());
        TaskDetailScreenLayout = (RelativeLayout) inflater.inflate(R.layout.taskdetails_activity,container,false);
        ed_task_name = (EditText) TaskDetailScreenLayout.findViewById(R.id.ed_task_name);
        ed_task_desp = (EditText) TaskDetailScreenLayout.findViewById(R.id.task_description);
        ed_due_date = (EditText) TaskDetailScreenLayout.findViewById(R.id.date_textview);
        prority_spinner = (Spinner) TaskDetailScreenLayout.findViewById(R.id.spinner_priority);
        bt_save_button = (Button) TaskDetailScreenLayout.findViewById(R.id.bt_save);
        bt_save_button.setOnClickListener(this);
        date_picker_image = (ImageView) TaskDetailScreenLayout.findViewById(R.id.iv_datepicker);
        dateTextView = (EditText) TaskDetailScreenLayout.findViewById(R.id.date_textview);
        this.arraySpinner = new String[] {
                "low", "medium", "high"
        };
        Spinner s = (Spinner) TaskDetailScreenLayout.findViewById(R.id.spinner_priority);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_spinner_item, arraySpinner);
        s.setAdapter(adapter);
        Calendar now = Calendar.getInstance();
        final DatePickerDialog dpd = DatePickerDialog.newInstance(
                TaskHomeFragment.this,
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
        ed_task_name.setText(TaskHomeActivity.task.getTask_name());
        ed_task_desp.setText(TaskHomeActivity.task.getTask_description());
        ed_due_date.setText(Utility.getDate(TaskHomeActivity.task.getDue_date()));


        /*HomeScreenLayout = (RelativeLayout) inflater.inflate(R.layout.activity_main, container, false);
        mRecyclerView = (RecyclerView) HomeScreenLayout.findViewById(R.id.CardRecyclerView);

        mRecyclerView.addOnItemTouchListener(
                new RecyclerItemClickListener(getActivity(), new RecyclerItemClickListener.OnItemClickListener() {
                    @Override
                    public void onItemClick(View view, int position) {
                        // TODO Handle item click
                        Toast.makeText(getContext(), "this is my Toast message!!! =)",
                                Toast.LENGTH_LONG).show();
                    }
                })
        );



        final FloatingActionsMenu menuMultipleActions = (FloatingActionsMenu) HomeScreenLayout.findViewById(R.id.multiple_actions);
        final FloatingActionButton actionA = (FloatingActionButton) HomeScreenLayout.findViewById(R.id.action_a);
        final FloatingActionButton actionB = (FloatingActionButton) HomeScreenLayout.findViewById(R.id.action_b);
        final FloatingActionButton actionC = (FloatingActionButton) HomeScreenLayout.findViewById(R.id.action_c);
        actionA.setIcon(R.drawable.general_task);
        actionB.setIcon(R.drawable.project_task);
        actionC.setIcon(R.drawable.list_task);
        actionA.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent mainIntent = new Intent(getContext(), AddTaskActivity.class);
                getContext().startActivity(mainIntent);
                menuMultipleActions.collapse();
            }
        });

        *//*mDrawerRecyclerView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                selectItem(position);
            }
        });*//*
        mRecyclerView.setHasFixedSize(true);                            // Letting the system know that the list objects are of fixed size

        mAdapter = new Card_Adapter(YourAssistApp.getAppInstance().getMyList(),getActivity());      // Creating the Adapter of MyAdapter class(which we are going to see in a bit)
        mRecyclerView.setAdapter(mAdapter);                              // Setting the adapter to RecyclerView
        mLayoutManager = new LinearLayoutManager(getActivity());                 // Creating a layout Manager

        mRecyclerView.setLayoutManager(mLayoutManager);                 // Setting the layout Manager
*/
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
    public void onDateSet(DatePickerDialog view, int year, int monthOfYear, int dayOfMonth) {
        String date = dayOfMonth+"/"+(monthOfYear+1)+"/"+year;
        dateTextView.setText(date);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.bt_save:
                TaskHomeActivity.task.setTask_name(ed_task_name.getText().toString());
                TaskHomeActivity.task.setTask_description(ed_task_desp.getText().toString());
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
                    //new_task.setStart_date(current_date);
                    TaskHomeActivity.task.setDue_date(startDate);
                }  catch (ParseException e) {
                    e.printStackTrace();
                }
                TaskHomeActivity.task.setPriority(prority_spinner.getSelectedItem().toString());
                mDbHelper.updateTask(TaskHomeActivity.task);
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
