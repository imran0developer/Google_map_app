package com.unitapplications.mapactivity;

import static com.unitapplications.mapactivity.LoginActivity.IS_USER;
import static com.unitapplications.mapactivity.LoginActivity.SHARED_PREFS;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;

import com.unitapplications.mapactivity.Adapters.Adapter;
import com.unitapplications.mapactivity.Api_Package.ApiClient;
import com.unitapplications.mapactivity.Api_Package.ApiSet;
import com.unitapplications.mapactivity.Models.ResponseModel;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class MainActivity extends AppCompatActivity {
    ApiSet apiSet;
    RecyclerView rv;
    Adapter adapter;
    List<ResponseModel> userList = new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        SharedPreferences sharedPreferences = getSharedPreferences(SHARED_PREFS,MODE_PRIVATE);
        if (!sharedPreferences.getBoolean(IS_USER, false)){
            startActivity(new Intent(MainActivity.this,LoginActivity.class));
        }


        rv=findViewById(R.id.rv);
        rv.setLayoutManager(new LinearLayoutManager(this));
        adapter = new Adapter(MainActivity.this,userList);
        rv.setAdapter(adapter);

        Retrofit retrofit = ApiClient.getClient();
      apiSet   = retrofit.create(ApiSet.class);

        makeApiCall();


    }
    public void makeApiCall(){
        apiSet.getUsers().enqueue(new Callback<List<ResponseModel>>() {
            @Override
            public void onResponse(Call<List<ResponseModel>> call, Response<List<ResponseModel>> response) {
                if (response.body()!=null){
                    setAdapter(response.body());
                }

            }

            @Override
            public void onFailure(Call<List<ResponseModel>> call, Throwable t) {

                Log.d("TAG2", "onFailure: "+t.getLocalizedMessage());

            }
        });

    }

    public void setAdapter(List<ResponseModel> memeModels){
        LinearLayoutManager layoutManager = new LinearLayoutManager(MainActivity.this);
        adapter = new Adapter(MainActivity.this,memeModels);
        rv.setLayoutManager(layoutManager);
        rv.setAdapter(adapter);
    }
}