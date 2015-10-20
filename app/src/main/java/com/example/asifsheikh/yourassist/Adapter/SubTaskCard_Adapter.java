package com.example.asifsheikh.yourassist.Adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.asifsheikh.yourassist.Database.FeedReaderSubTaskDbHelper;
import com.example.asifsheikh.yourassist.R;
import com.example.asifsheikh.yourassist.SubTaskHomeActivity;
import com.example.asifsheikh.yourassist.model.SubTask;

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
    public void onBindViewHolder(final ViewHolder holder, int position) {
        Log.d(" " + subtask_list.size(), "Sub task list size");
        final SubTask current = subtask_list.get(position);
        new_subtask = current;
        Log.d(" " + current.getSubtask_name() + " " + current.getSubtask_description(), "task description");
        holder.subtask_header.setText(current.getSubtask_name());
        holder.subtask_desp.setText(current.getSubtask_description());
        holder.subtask_id.setText("" + current.getSubtask_id() + "");
        holder.task_id.setText("" + current.getTask_id() + "");
        if(current.getStatus() == 0){
            holder.ib_subtask.setBackgroundResource(R.drawable.ic_clear_black_48dp);
            holder.rl_subtask.setBackgroundColor(mContext.getResources().getColor(R.color.subtask_incomplete));
        }
        else{
            holder.ib_subtask.setBackgroundResource(R.drawable.ic_done_black_64dp_1x);
            holder.rl_subtask.setBackgroundColor( mContext.getResources().getColor(R.color.subtask_complete));
        }

        holder.ib_subtask.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FeedReaderSubTaskDbHelper mDbHelper = new FeedReaderSubTaskDbHelper(mContext);

                Toast.makeText(mContext, "Sub Task completed", Toast.LENGTH_LONG).show();
                v.setBackgroundResource(R.drawable.ic_clear_black_48dp);
                if (current.getStatus() == 1) {
                    current.setStatus(0);
                } else {
                    current.setStatus(1);
                }
                mDbHelper.update_subtask(current);
                notifyDataSetChanged();
            }
        });

    }

    @Override
    public int getItemCount() {
        return subtask_list.size();
    }


    public static class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        CardView cv;
        TextView subtask_id;
        TextView task_id;
        TextView subtask_header;
        TextView subtask_desp;
        RelativeLayout rl_subtask;
        ImageButton ib_subtask;
        SubTask msubtask;
        Context contxt;

        public ViewHolder(View itemView ,Context c, SubTask nsubtask) {
            super(itemView);
            this.contxt = c;
            msubtask = nsubtask;
            cv = (CardView)itemView.findViewById(R.id.cv);
            rl_subtask = (RelativeLayout) itemView.findViewById(R.id.rl_subtask);
            ib_subtask = (ImageButton) itemView.findViewById(R.id.ib_subtask_complete);
            task_id = (TextView) itemView.findViewById(R.id.tv_subtask_task_id);
            subtask_id = (TextView) itemView.findViewById(R.id.tv_subtask_id);
            subtask_header = (TextView)itemView.findViewById(R.id.tv_subtask_header);
            subtask_desp = (TextView)itemView.findViewById(R.id.tv_subtask_despcription);
            itemView.setOnClickListener(this);

        }


        @Override
        public void onClick(View v) {
            Intent mainIntent;
                    mainIntent = new Intent(contxt, SubTaskHomeActivity.class);
                    mainIntent.putExtra(SubTaskHomeActivity.ARG_TASK_DETAILS, task_id.getText());
                    mainIntent.putExtra(SubTaskHomeActivity.ARG_SUB_TASK_DETAILS, subtask_id.getText());
                    contxt.startActivity(mainIntent);
        }
    }


    public SubTaskCard_Adapter(List<SubTask> subtask_list,Context passedContext){ // MyAdapter Constructor with titles and icons parameter
        // titles, icons, name, email, profile pic are passed from the main activity as we

        this.mContext = passedContext;
        this.subtask_list = subtask_list;


    }



}
