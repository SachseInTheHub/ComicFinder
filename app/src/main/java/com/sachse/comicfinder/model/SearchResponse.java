package com.sachse.comicfinder.model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class SearchResponse {

    @SerializedName("status_code")
    private int statusCode;
    @SerializedName("number_of_total_results")
    private int totalResults;
    @SerializedName("number_of_page_results")
    private int pageResults;
    private String error;
    private int limit;
    private int offset;
    private List<Character> results;

    public List<Character> getCharacters() {
        return results;
    }
}
