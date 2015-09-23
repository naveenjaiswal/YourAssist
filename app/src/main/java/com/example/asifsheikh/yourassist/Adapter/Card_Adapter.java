package com.example.asifsheikh.yourassist.Adapter;

import android.content.Context;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.asifsheikh.yourassist.R;
import com.example.asifsheikh.yourassist.application.YourAssistApp;
import com.example.asifsheikh.yourassist.model.Task;
import com.google.android.gms.plus.model.people.Person;

import java.util.List;

/**
 * Created by asifsheikh on 7/9/15.
 */
public class Card_Adapter extends RecyclerView.Adapter<Card_Adapter.ViewHolder> {

    List<Task> task_list;
    Context mContext;


    public static class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{

        CardView cv;
        TextView task_header;
        TextView task_desp;
        ImageView task_icon;
        Context contxt;

        public ViewHolder(View itemView,Context c) {
            super(itemView);
            contxt = c;
            cv = (CardView)itemView.findViewById(R.id.cv);
            task_header = (TextView)itemView.findViewById(R.id.tv_task_header);
            task_desp = (TextView)itemView.findViewById(R.id.tv_task_despcription);
            task_icon = (ImageView)itemView.findViewById(R.id.taskIcon);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {

        }
    }


    public Card_Adapter(List<Task> task_list,Context passedContext){ // MyAdapter Constructor with titles and icons parameter
        // titles, icons, name, email, profile pic are passed from the main activity as we

        this.mContext = passedContext;
       this.task_list = YourAssistApp.getAppInstance().getMyList();


    }


    @Override
    public Card_Adapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.task_item_row,parent,false); //Inflating the layout

        ViewHolder vhItem = new ViewHolder(v,mContext); //Creating ViewHolder and passing the object of type view


        return vhItem; // Returning the created object

    }

    @Override
    public void onBindViewHolder(Card_Adapter.ViewHolder holder, int position) {
        Log.d(" " + task_list.size() ,"task list size");
        Task current = task_list.get(position);

        Log.d(" " + current.getTask_name() + " " + current.getTask_description() ,"task description");
        holder.task_header.setText(current.getTask_name());
        holder.task_desp.setText(current.getTask_description());
        //holder.task_icon.

    }

    @Override
    public int getItemCount() {
        return task_list.size();
    }
}
