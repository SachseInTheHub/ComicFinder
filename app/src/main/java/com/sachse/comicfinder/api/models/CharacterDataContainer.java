package com.sachse.comicfinder.api.models;

import com.sachse.comicfinder.model.Character;

import java.util.List;

public class CharacterDataContainer {

    public int offset;
    public int limit;
    public int total;
    public int count;
    public List<Character> results;
}
