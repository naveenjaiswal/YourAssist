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
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.example.asifsheikh.yourassist.Adapter.Card_Adapter;
import com.example.asifsheikh.yourassist.AddTaskActivity;
import com.example.asifsheikh.yourassist.R;
import com.example.asifsheikh.yourassist.application.YourAssistApp;
import com.getbase.floatingactionbutton.FloatingActionButton;
import com.getbase.floatingactionbutton.FloatingActionsMenu;

/**
 * Created by Admin on 9/23/2015.
 */
public class TaskHomeFragment extends Fragment {

    Context thiscontext;
    private RelativeLayout HomeScreenLayout;
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
        HomeScreenLayout = (RelativeLayout) inflater.inflate(R.layout.activity_main, container, false);
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

        /*mDrawerRecyclerView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                selectItem(position);
            }
        });*/
        mRecyclerView.setHasFixedSize(true);                            // Letting the system know that the list objects are of fixed size

        mAdapter = new Card_Adapter(YourAssistApp.getAppInstance().getMyList(),getActivity());      // Creating the Adapter of MyAdapter class(which we are going to see in a bit)
        mRecyclerView.setAdapter(mAdapter);                              // Setting the adapter to RecyclerView
        mLayoutManager = new LinearLayoutManager(getActivity());                 // Creating a layout Manager

        mRecyclerView.setLayoutManager(mLayoutManager);                 // Setting the layout Manager

        return HomeScreenLayout;
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

}
