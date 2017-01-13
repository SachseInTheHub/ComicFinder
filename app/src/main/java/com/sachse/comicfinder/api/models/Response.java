package com.sachse.comicfinder.api.models;

import com.google.gson.annotations.SerializedName;

import com.sachse.comicfinder.model.Character;

public class Response {

    @SerializedName("status_code")
    private int statusCode;
    private String error;
    @SerializedName("number_of_total_results")
    private int totalResults;
    @SerializedName("number_of_page_results")
    private int pageResults;
    private int limit;
    private int offset;
    private Character results;

    public Character getResults(){
        return results;
    }
}
