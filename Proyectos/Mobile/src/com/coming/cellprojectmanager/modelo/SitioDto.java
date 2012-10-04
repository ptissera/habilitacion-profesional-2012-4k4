package com.coming.cellprojectmanager.modelo;

import java.io.Serializable;

import com.google.gson.Gson;

public class SitioDto  implements Serializable {
	private static final long serialVersionUID = 1088023582347907949L;
	public Long id;
	public String nombre;
	public String provincia;
	public Double latitud;
	public Double longitud;
	
	public static SitioDto fromJSon(String json) {
		SitioDto sitio = null;
		Gson gson = new Gson();
		sitio = (SitioDto)gson.fromJson(json.toString(), SitioDto.class);
		return sitio;
	}
}
