package com.unitapplications.mapactivity.Api_Package;


import com.unitapplications.mapactivity.Models.ResponseModel;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;


public interface ApiSet {
    @GET("/users")
    Call<List<ResponseModel>> getUsers();

}