package com.sachse.comicfinder.api.models;

public class ApiStatus {

	public int code;
	public String status;
	public String copyright;
	public String attributionText;
	public String attributionHTML;
	public ApiData data;

	public ApiStatus(ApiStatus body) {
		code = body.code;
		status = body.status;
		copyright = body.copyright;
		attributionHTML = body.attributionHTML;
		attributionText = body.attributionText;
		data = body.data;

	}
}
