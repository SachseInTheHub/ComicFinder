package com.sachse.comicfinder.api.models.comic;

import java.util.List;

public class ComicDataContainer {

    public int offset;
    public int limit;
    public int total;
    public int count;
    public List<Comic> results;
}
