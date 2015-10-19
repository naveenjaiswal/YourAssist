package com.example.asifsheikh.yourassist.Adapter;

import android.content.Context;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.asifsheikh.yourassist.R;
import com.example.asifsheikh.yourassist.model.SubTask;
import com.example.asifsheikh.yourassist.model.Task;

import java.util.List;

/**
 * Created by asifsheikh on 19/10/15.
 */
public class SubTaskCard_Adapter extends RecyclerView.Adapter<SubTaskCard_Adapter.ViewHolder> {

    List<SubTask> subtask_list;
    SubTask new_subtask;
    Context mContext;

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.subtask_item_row,parent,false); //Inflating the layout

        ViewHolder vhItem = new ViewHolder(v,mContext,new_subtask); //Creating ViewHolder and passing the object of type view


        return vhItem; // Returning the created object

    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        Log.d(" " + subtask_list.size(), "Sub task list size");
        SubTask current = subtask_list.get(position);
        new_subtask = current;
        Log.d(" " + current.getSubtask_name() + " " + current.getSubtask_description() ,"task description");
        holder.subtask_header.setText(current.getSubtask_name());
        holder.subtask_desp.setText(current.getSubtask_description());
        holder.subtask_id.setText(""+current.getSubtask_id()+"");

    }

    @Override
    public int getItemCount() {
        return subtask_list.size();
    }


    public static class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        CardView cv;
        TextView subtask_id;
        TextView subtask_header;
        TextView subtask_desp;
        SubTask msubtask;
        Context contxt;

        public ViewHolder(View itemView ,Context c, SubTask nsubtask) {
            super(itemView);
            this.contxt = c;
            msubtask = nsubtask;
            cv = (CardView)itemView.findViewById(R.id.cv);
            subtask_id = (TextView) itemView.findViewById(R.id.tv_subtask_id);
            subtask_header = (TextView)itemView.findViewById(R.id.tv_subtask_header);
            subtask_desp = (TextView)itemView.findViewById(R.id.tv_subtask_despcription);
            itemView.setOnClickListener(this);

        }


        @Override
        public void onClick(View v) {

        }
    }


    public SubTaskCard_Adapter(List<SubTask> subtask_list,Context passedContext){ // MyAdapter Constructor with titles and icons parameter
        // titles, icons, name, email, profile pic are passed from the main activity as we

        this.mContext = passedContext;
        this.subtask_list = subtask_list;


    }



}
