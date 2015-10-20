package com.example.asifsheikh.yourassist.fragments;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.example.asifsheikh.yourassist.Adapter.SubTaskCard_Adapter;
import com.example.asifsheikh.yourassist.AddTaskActivity;
import com.example.asifsheikh.yourassist.Database.FeedReaderSubTaskDbHelper;
import com.example.asifsheikh.yourassist.R;
import com.example.asifsheikh.yourassist.SubTaskAddActivity;
import com.example.asifsheikh.yourassist.SubTaskDashboard;
import com.example.asifsheikh.yourassist.model.SubTask;
import com.getbase.floatingactionbutton.FloatingActionButton;
import com.github.brnunes.swipeablerecyclerview.SwipeableRecyclerViewTouchListener;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by asifsheikh on 19/10/15.
 */
public class SubTaskDashBoardFragment extends Fragment {
    private RecyclerView mRecyclerView;
    Context thiscontext;
    RecyclerView.Adapter mAdapter;                        // Declaring Adapter For Recycler View
    RecyclerView.LayoutManager mLayoutManager;
    private List<SubTask> tasklist = new ArrayList<SubTask>();
    private FeedReaderSubTaskDbHelper mDbHelper ;
    private RelativeLayout SubTaskDashboard;

    private OnFragmentInteractionListener mListener;

    // TODO: Rename parameter arguments, choose names that match
    private static final String ARG_SECTION_NUMBER = "section_number";


    // TODO: Rename and change types of parameters
    private int mParam1;


    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     *
     * @return A new instance of fragment BlankFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static SubTaskDashBoardFragment newInstance(int section) {
        SubTaskDashBoardFragment fragment = new SubTaskDashBoardFragment();
        Bundle args = new Bundle();
        args.putInt(ARG_SECTION_NUMBER, section);
        fragment.setArguments(args);
        return fragment;
    }

    public SubTaskDashBoardFragment() {
        // Required empty public constructor
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getInt(ARG_SECTION_NUMBER);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        thiscontext = container.getContext();

        mDbHelper = new FeedReaderSubTaskDbHelper(thiscontext);
        SubTaskDashboard = (RelativeLayout) inflater.inflate(R.layout.activity_subtask_add, container, false);
        mRecyclerView = (RecyclerView) SubTaskDashboard.findViewById(R.id.CardRecyclerView);
        /*try {
            tasklist = mDbHelper.getAllTask();
        } catch (ParseException e) {
            e.printStackTrace();
        }
        if (tasklist.size() == 0) {
            mRecyclerView.setBackground(getResources().getDrawable(R.drawable.notasktodo));

        }*/

        final FloatingActionButton actionA = (FloatingActionButton) SubTaskDashboard.findViewById(R.id.FAB_create_subtask);
        actionA.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent mainIntent = new Intent(getContext(), SubTaskAddActivity.class);
                getContext().startActivity(mainIntent);
            }
        });

        mRecyclerView.setHasFixedSize(true);                            // Letting the system know that the list objects are of fixed size


        Log.e("Printing the all task ", "" + tasklist.size());
       refresh_subtask();

        //mAdapter = new Card_Adapter(tasklist,getActivity());      // Creating the Adapter of MyAdapter class(which we are going to see in a bit)

        SwipeableRecyclerViewTouchListener swipeTouchListener =
                new SwipeableRecyclerViewTouchListener(mRecyclerView,
                        new SwipeableRecyclerViewTouchListener.SwipeListener() {
                            @Override
                            public boolean canSwipe(int position) {
                                return true;
                            }

                            @Override
                            public void onDismissedBySwipeLeft(RecyclerView recyclerView, int[] reverseSortedPositions) {
                                for (int position : reverseSortedPositions) {
                                    SubTask t = tasklist.get(position);
                                    //mDbHelper.delete(t.getTask_id());
                                    mDbHelper.delete_subtask(t);
                                    Toast.makeText(getContext(), "SubTask Deleted..!!", Toast.LENGTH_LONG).show();
                                    refresh_subtask();
                                    /*YourAssistApp.getAppInstance().getMyList().remove(position);
                                    mAdapter.notifyItemRemoved(position);*/
                                }
                                // mAdapter.notifyDataSetChanged();
                            }

                            @Override
                            public void onDismissedBySwipeRight(RecyclerView recyclerView, int[] reverseSortedPositions) {
                                for (int position : reverseSortedPositions) {
                                    /*YourAssistApp.getAppInstance().getMyList().remove(position);
                                    mAdapter.notifyItemRemoved(position);*/
                                    SubTask t = tasklist.get(position);
                                    //mDbHelper.delete(t.getTask_id());
                                    mDbHelper.delete_subtask(t);
                                    Toast.makeText(getContext(), "SubTask Deleted..!!", Toast.LENGTH_LONG).show();
                                    refresh_subtask();
                                }
                                //mAdapter.notifyDataSetChanged();
                            }
                        });

        mRecyclerView.addOnItemTouchListener(swipeTouchListener);
        return SubTaskDashboard;

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


    public void refresh_subtask(){
        try {
            tasklist = mDbHelper.getAllSubTask(com.example.asifsheikh.yourassist.SubTaskDashboard.task.getTask_id());
        } catch (ParseException e) {
            e.printStackTrace();
        }
        if(tasklist.size() == 0){
            mRecyclerView.setBackground(getResources().getDrawable(R.drawable.notasktodo));

        }
        else
        {
            mRecyclerView.setBackground(getResources().getDrawable(R.color.background_grey));
        }
        mAdapter = new SubTaskCard_Adapter(tasklist,getActivity());      // Creating the Adapter of MyAdapter class(which we are going to see in a bit)
        mRecyclerView.setAdapter(mAdapter);                              // Setting the adapter to RecyclerView
        mLayoutManager = new LinearLayoutManager(getActivity());                 // Creating a layout Manager
        mRecyclerView.setLayoutManager(mLayoutManager);                 // Setting the layout Manager
        mAdapter.notifyDataSetChanged();
    }



}
