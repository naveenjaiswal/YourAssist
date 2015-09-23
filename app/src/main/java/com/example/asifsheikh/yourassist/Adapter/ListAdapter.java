package com.example.asifsheikh.yourassist.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.asifsheikh.yourassist.R;

/**
 * Created by Admin on 9/23/2015.
 */
public class ListAdapter extends ArrayAdapter<String>{

        Context mContext;
    private String mNavTitles[]; // String Array to store the passed titles Value from MainActivity.java
    private int mIcons[];       // Int Array to store the passed icons resource value from MainActivity.java

    public ListAdapter(Context context, String Titles[], int Icons[]) {
        super(context,-1);
        mContext = context;
        mNavTitles = Titles;
        mIcons = Icons;
    }


   /* public static class ViewHolder implements View.OnClickListener {
        int Holderid;

        TextView textView;
        ImageView imageView;
        Context contxt;


        public ViewHolder(View itemView,Context c) {                 // Creating ViewHolder Constructor with View and viewType As a parameter
            contxt = c;
            textView = (TextView) itemView.findViewById(R.id.rowText); // Creating TextView object with the id of textView from item_row.xml
            imageView = (ImageView) itemView.findViewById(R.id.rowIcon);// Creating ImageView object with the id of ImageView from item_row.xml
            itemView.setOnClickListener(this);

        }

        @Override
        public void onClick(View v) {
            //Toast.makeText(contxt, "The Item Clicked is: " + getPosition(), Toast.LENGTH_SHORT).show();

        }
    }*/

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = (LayoutInflater) mContext
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View rowView = inflater.inflate(R.layout.list_row, parent, false);
        TextView textView = (TextView) rowView.findViewById(R.id.rowText);
        final int pos = position;
        ImageView imageView = (ImageView) rowView.findViewById(R.id.rowIcon);
        textView.setText(mNavTitles[position - 1]); // Setting the Text with the array of our Titles
        imageView.setImageResource(mIcons[position - 1]);// Settimg the image with array of our icons
        rowView.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Toast.makeText(mContext, "The Item Clicked is: " + pos, Toast.LENGTH_SHORT).show();
            }
        });
        return rowView;
    }


}
