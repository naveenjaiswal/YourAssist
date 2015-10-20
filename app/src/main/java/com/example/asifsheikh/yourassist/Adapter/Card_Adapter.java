package com.example.asifsheikh.yourassist.Adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.asifsheikh.yourassist.AddTaskActivity;
import com.example.asifsheikh.yourassist.R;
import com.example.asifsheikh.yourassist.SubTaskDashboard;
import com.example.asifsheikh.yourassist.TaskHomeActivity;
import com.example.asifsheikh.yourassist.application.YourAssistApp;
import com.example.asifsheikh.yourassist.model.Task;
import com.example.asifsheikh.yourassist.utility.Utility;
import com.google.android.gms.plus.model.people.Person;

import java.util.List;

/**
 * Created by asifsheikh on 7/9/15.
 */
public class Card_Adapter extends RecyclerView.Adapter<Card_Adapter.ViewHolder> {

    List<Task> task_list;
    Task new_task;
    Context mContext;


    public static class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{

        CardView cv;
        TextView task_id;
        TextView task_header;
        TextView task_desp;
        TextView pending_status;
        TextView complete_percentage;
        ImageView task_icon;
        Task mtask;
        Button addsubtaskbutton;
        LinearLayout PriorityLayout;
        Context contxt;

        public ViewHolder(View itemView,Context c, Task task) {
            super(itemView);
            contxt = c;
            mtask = task;
            cv = (CardView)itemView.findViewById(R.id.cv);
            task_id = (TextView) itemView.findViewById(R.id.tv_task_id);
            task_header = (TextView)itemView.findViewById(R.id.tv_task_header);
            task_desp = (TextView)itemView.findViewById(R.id.tv_task_despcription);
            pending_status = (TextView) itemView.findViewById(R.id.tv_pending_count);
            complete_percentage = (TextView) itemView.findViewById(R.id.tv_percentage);
            addsubtaskbutton = (Button) itemView.findViewById(R.id.button_add_subtask);
            PriorityLayout = (LinearLayout) itemView.findViewById(R.id.ll_priority_indicator);
            addsubtaskbutton.setOnClickListener(this);
            task_icon = (ImageView)itemView.findViewById(R.id.taskIcon);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            Intent mainIntent;
            switch (v.getId()){
                case R.id.button_add_subtask:
                    mainIntent = new Intent(contxt, SubTaskDashboard.class);
                    mainIntent.putExtra(SubTaskDashboard.ARG_TASK_DETAILS,task_id.getText());
                    contxt.startActivity(mainIntent);
                    break;
                default:
                    mainIntent = new Intent(contxt, TaskHomeActivity.class);
                    mainIntent.putExtra(TaskHomeActivity.ARG_TASK_DETAILS, task_id.getText());
                    contxt.startActivity(mainIntent);
                    break;


            }


        }
    }


    public Card_Adapter(List<Task> task_list,Context passedContext){ // MyAdapter Constructor with titles and icons parameter
        // titles, icons, name, email, profile pic are passed from the main activity as we

        this.mContext = passedContext;
       this.task_list = task_list;


    }


    @Override
    public Card_Adapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.task_item_row,parent,false); //Inflating the layout

        ViewHolder vhItem = new ViewHolder(v,mContext,new_task); //Creating ViewHolder and passing the object of type view


        return vhItem; // Returning the created object

    }

    @Override
    public void onBindViewHolder(Card_Adapter.ViewHolder holder, int position) {
        Log.d(" " + task_list.size() ,"task list size");
        Task current = task_list.get(position);
        new_task = current;
        Log.d(" " + current.getTask_name() + " " + current.getTask_description(), "task description");
        holder.task_header.setText(current.getTask_name());
        holder.task_desp.setText(current.getTask_description());
        holder.task_id.setText(""+current.getTask_id()+"");
        holder.pending_status.setText("Pending SubTask : "+current.getComplete_subtask()+"/"+current.getTotal_number_of_subtask()+".");
        float percentage;
        int priority = Utility.getPriorityofTask(current.getPriority());
        if(priority == 0){
            holder.PriorityLayout.setBackgroundResource(R.color.priority_low);
        }
        else if(priority == 1){
            holder.PriorityLayout.setBackgroundResource(R.color.priority_medium);
        }
        else if(priority == 2){
            holder.PriorityLayout.setBackgroundResource(R.color.priority_high);
        }
        if(current.getTotal_number_of_subtask() == 0){
            percentage = 0;
        }
        else {
            percentage = current.getComplete_subtask() / (float)current.getTotal_number_of_subtask();
        }
        percentage = percentage*100;
        holder.complete_percentage.setText(""+percentage+"% done.");
        //holder.task_icon.

    }

    @Override
    public int getItemCount() {
        return task_list.size();
    }
}
