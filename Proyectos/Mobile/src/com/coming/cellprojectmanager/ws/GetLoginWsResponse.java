package com.coming.cellprojectmanager.ws;

import com.coming.cellprojectmanager.modelo.UsuarioDto;
import com.google.gson.Gson;

public class GetLoginWsResponse {
	public WsError error;
	public UsuarioDto usuario;
	
	public static GetLoginWsResponse fromJSon(String json) {
		Gson gson = new Gson();
		return (GetLoginWsResponse)gson.fromJson(json, GetLoginWsResponse.class);
	}

}
