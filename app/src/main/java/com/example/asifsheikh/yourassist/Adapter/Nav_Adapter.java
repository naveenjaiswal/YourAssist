package com.example.asifsheikh.yourassist.Adapter;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.SimpleAdapter;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.ImageRequest;
import com.example.asifsheikh.yourassist.AboutActivity;
import com.example.asifsheikh.yourassist.AddTaskActivity;
import com.example.asifsheikh.yourassist.HelpActivity;
import com.example.asifsheikh.yourassist.MainActivity;
import com.example.asifsheikh.yourassist.R;
import com.example.asifsheikh.yourassist.application.YourAssistApp;
import com.google.android.gms.plus.Plus;
import com.google.android.gms.plus.model.people.Person;

import java.io.InputStream;

/**
 * Created by asifsheikh on 30/8/15.
 */
public class Nav_Adapter extends RecyclerView.Adapter<Nav_Adapter.ViewHolder> {

    private static final int TYPE_HEADER = 0;  // Declaring Variable to Understand which View is being worked on
    // IF the view under inflation and population is header or Item
    private static final int TYPE_ITEM = 1;
    private Context mContext;

    private String mNavTitles[]; // String Array to store the passed titles Value from MainActivity.java
    private int mIcons[];       // Int Array to store the passed icons resource value from MainActivity.java

    private String name;        //String Resource for header View Name
    private Person.Image profile;        //int Resource for header view profile picture
    private String email;       //String Resource for header view email
    private Bitmap profile_image;




    public static class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        int Holderid;

        TextView textView;
        ImageView imageView;
        ImageView profile;
        TextView Name;
        TextView email;
        Context contxt;


        public ViewHolder(View itemView,int ViewType,Context c) {                 // Creating ViewHolder Constructor with View and viewType As a parameter
            super(itemView);
            contxt = c;

            // Here we set the appropriate view in accordance with the the view type as passed when the holder object is created

            if(ViewType == TYPE_ITEM) {
                textView = (TextView) itemView.findViewById(R.id.rowText); // Creating TextView object with the id of textView from item_row.xml
                imageView = (ImageView) itemView.findViewById(R.id.rowIcon);// Creating ImageView object with the id of ImageView from item_row.xml
                Holderid = 1;
                itemView.setClickable(true);
                itemView.setOnClickListener(this);// setting holder id as 1 as the object being populated are of type item row
            }
            else{


                Name = (TextView) itemView.findViewById(R.id.name);         // Creating Text View object from header.xml for name
                email = (TextView) itemView.findViewById(R.id.email);       // Creating Text View object from header.xml for email
                profile = (ImageView) itemView.findViewById(R.id.circleView);// Creating Image view object from header.xml for profile pic
                //itemView.setBackground();
                itemView.setClickable(false);
                //itemView.setOnClickListener(this);
                Holderid = 0;
            }


        }

        @Override
        public void onClick(View v) {
            if(getPosition() == 1){
                Intent mainIntent = new Intent(contxt, MainActivity.class);
                contxt.startActivity(mainIntent);
            }
            if(getPosition() == 2){
                /*String TITLES[] = {"Genreal Task","Project Task","List Task"};
                int ICONS[] = {R.drawable.general_task,R.drawable.project_task,R.drawable.list_task};*/
                Intent mainIntent = new Intent(contxt, AddTaskActivity.class);
                contxt.startActivity(mainIntent);


            }
            if(getPosition() == 4){
                Intent mainIntent = new Intent(contxt, HelpActivity.class);
                contxt.startActivity(mainIntent);

            }

            if(getPosition() == 5){
                Intent mainIntent = new Intent(contxt, AboutActivity.class);
                contxt.startActivity(mainIntent);
            }
            if(getPosition() == 6){
                if(YourAssistApp.getAppInstance().getmGoogleApiClient().isConnected())
                {
                    Plus.AccountApi.clearDefaultAccount(YourAssistApp.getAppInstance().getmGoogleApiClient());
                    Plus.AccountApi.revokeAccessAndDisconnect(YourAssistApp.getAppInstance().getmGoogleApiClient());
                    YourAssistApp.getAppInstance().getmGoogleApiClient().disconnect();

                }
            }
            else{
                Toast.makeText(contxt,"The Item Clicked is: "+getPosition(), Toast.LENGTH_SHORT).show();
            }

        }
    }

    public Nav_Adapter(String Titles[], int Icons[], String Name, String Email, Person.Image Profile,Context passedContext){ // MyAdapter Constructor with titles and icons parameter
        // titles, icons, name, email, profile pic are passed from the main activity as we
        mNavTitles = Titles;                //have seen earlier
        mIcons = Icons;
        this.mContext = passedContext;
        name = Name;
        email = Email;
        profile = Profile;   //here we assign those passed values to the values we declared here
        //get_profile_image(profile.getUrl().substring(0,profile.getUrl().length() -2) + "70");

    }


    @Override
    public Nav_Adapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if (viewType == TYPE_ITEM) {
            View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.nav_item_row,parent,false); //Inflating the layout

            ViewHolder vhItem = new ViewHolder(v,viewType,mContext); //Creating ViewHolder and passing the object of type view


            return vhItem; // Returning the created object

            //inflate your layout and pass it to view holder

        } else if (viewType == TYPE_HEADER) {

            View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.nav_header,parent,false); //Inflating the layout

            ViewHolder vhHeader = new ViewHolder(v,viewType,mContext); //Creating ViewHolder and passing the object of type view


            return vhHeader; //returning the object created


        }
        return null;
    }

    //Next we override a method which is called when the item in a row is needed to be displayed, here the int position
    // Tells us item at which position is being constructed to be displayed and the holder id of the holder object tell us
    // which view type is being created 1 for item row

    @Override
    public void onBindViewHolder(Nav_Adapter.ViewHolder holder, int position) {
        if(holder.Holderid ==1) {                              // as the list view is going to be called after the header view so we decrement the
            // position by 1 and pass it to the holder while setting the text and image
            holder.textView.setText(mNavTitles[position - 1]); // Setting the Text with the array of our Titles
            holder.imageView.setImageResource(mIcons[position -1]);// Settimg the image with array of our icons

        }
        else{

           // holder.profile.setImageBitmap(profile_image);           // Similarly we set the resources for header view
            new LoadProfileImage(holder.profile).execute(profile.getUrl().substring(0,profile.getUrl().length() -2) + "70");
            holder.Name.setText(name);
            holder.email.setText(email);



        }

    }

    /**
     * Background Async task to load user profile picture from url
     * */
    private class LoadProfileImage extends AsyncTask<String, Void, Bitmap> {
        ImageView bmImage;

        public LoadProfileImage(ImageView bmImage) {
            this.bmImage = bmImage;
        }

        protected Bitmap doInBackground(String... urls) {
            String urldisplay = urls[0];
            Bitmap mIcon11 = null;
            try {
                InputStream in = new java.net.URL(urldisplay).openStream();
                mIcon11 = BitmapFactory.decodeStream(in);
            } catch (Exception e) {
                Log.e("Error", e.getMessage());
                e.printStackTrace();
            }
            return mIcon11;
        }

        protected void onPostExecute(Bitmap result) {
            bmImage.setImageBitmap(result);
        }
    }

    private void get_profile_image(String url){
        // Retrieves an image specified by the URL, displays it in the UI.
        ImageRequest request = new ImageRequest(url,
                new Response.Listener<Bitmap>() {
                    @Override
                    public void onResponse(Bitmap bitmap) {
                        profile_image = bitmap;
                    }
                }, 0, 0, null,
                new Response.ErrorListener() {
                    public void onErrorResponse(VolleyError error) {
                        //mImageView.setImageResource(R.drawable.image_load_error);
                    }
                });
// Access the RequestQueue through your singleton class.
        YourAssistApp.getAppInstance().addToRequestQueue(request);

    }

    @Override
    public int getItemCount() {
        return mNavTitles.length+1; // the number of items in the list will be +1 the titles including the header view.
    }

    // Witht the following method we check what type of view is being passed
    @Override
    public int getItemViewType(int position) {
        if (isPositionHeader(position))
            return TYPE_HEADER;

        return TYPE_ITEM;
    }

    private boolean isPositionHeader(int position) {
        return position == 0;
    }


}
