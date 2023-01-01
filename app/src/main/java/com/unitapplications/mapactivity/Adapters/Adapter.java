package com.unitapplications.mapactivity.Adapters;

import static com.unitapplications.mapactivity.MapsActivity.LATITUDE;
import static com.unitapplications.mapactivity.MapsActivity.LONGITUDE;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.unitapplications.mapactivity.MapsActivity;
import com.unitapplications.mapactivity.Models.ResponseModel;
import com.unitapplications.mapactivity.R;

import java.util.List;

public class Adapter extends RecyclerView.Adapter<Adapter.myviewholder>{
    Context context;
    List<ResponseModel> userList;


    public Adapter(Context context, List<ResponseModel> userList) {
        this.context = context;
        this.userList = userList;
    }


    @NonNull
    @Override
    public myviewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.rv_item,parent,false);
        return new myviewholder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull myviewholder holder, int position) {
        ResponseModel model = userList.get(position);
        holder.name_str=model.getName();
        holder.username_str=model.getUsername();
        holder.email_str=model.getEmail();
        holder.street_str = model.getAddress().getStreet();
        holder.city_str = model.getAddress().getCity();
        holder.zip_str = model.getAddress().getZipcode();


        holder.name.setText("Name: "+holder.name_str);
        holder.username.setText("Username: "+holder.username_str);
        holder.email.setText("Email: "+holder.email_str);
        holder.street.setText("Street: "+holder.street_str);
        holder.city.setText("City: "+holder.city_str);
        holder.zip.setText("Zip-code: "+holder.zip_str);

        holder.location_bt.setOnClickListener(view -> {
            //Log.d("TAG2", "Latitude is  : "+model.getAddress().getGeo().getLat());
            //Log.d("TAG2", "Longitude is : "+model.getAddress().getGeo().getLng());
            holder.latitude = model.getAddress().getGeo().getLat();
            holder.longitude = model.getAddress().getGeo().getLng();
            Log.d("TAG2", "Latitude is  : "+holder.latitude);
            Log.d("TAG2", "longitude is  : "+holder.longitude);


            sendData(holder.latitude,holder.longitude);

        });

    }

    private void sendData(String latitude, String longitude) {
        Intent intent = new Intent(context, MapsActivity.class);
        intent.putExtra(LATITUDE,latitude)
                .putExtra(LONGITUDE,longitude);
        context.startActivity(intent);
    }

    @Override
    public int getItemCount() {
        if(this.userList!=null)
            return this.userList.size();
        else
            return 0;
    }



    public class myviewholder extends RecyclerView.ViewHolder{
      private TextView name,username,email,
                       street,city,zip,location_bt ;
      private String latitude,longitude,name_str,username_str,email_str,street_str,city_str,zip_str;


        public myviewholder(@NonNull View itemView) {
            super(itemView);
            name=itemView.findViewById(R.id.name_tv);
            username=itemView.findViewById(R.id.username_tv);
            email=itemView.findViewById(R.id.email_tv);
            street=itemView.findViewById(R.id.street_tv);
            city=itemView.findViewById(R.id.city_tv);
            zip=itemView.findViewById(R.id.zip_tv);
            location_bt = itemView.findViewById(R.id.location);

        }
    }
}