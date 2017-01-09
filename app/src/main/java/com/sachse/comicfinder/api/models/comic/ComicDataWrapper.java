package com.sachse.comicfinder.api.models.comic;

public class ComicDataWrapper {

    private int code;
    private String status;
    private String copyright;
    private String attributionText;
    private String attributionHTML;
    private ComicDataContainer data;

    public ComicDataWrapper(final ComicDataWrapper body) {
        code = body.code;
        status = body.status;
        copyright = body.copyright;
        attributionHTML = body.attributionHTML;
        attributionText = body.attributionText;
        data = body.data;
    }
}
