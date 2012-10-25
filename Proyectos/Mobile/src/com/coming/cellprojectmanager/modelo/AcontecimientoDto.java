package com.coming.cellprojectmanager.modelo;

import java.io.Serializable;

import com.google.gson.Gson;

public class AcontecimientoDto implements Serializable {
	private static final long serialVersionUID = -2962360381279895282L;
	public Long id;
	public Long tareaId;
	public String nombreTipo;
	public Long usuarioId;
	public String fechaCreacion;
	public String descripcion;
	
	public static AcontecimientoDto fromJSon(String json) {
		AcontecimientoDto acontecimiento = null;
		Gson gson = new Gson();
		acontecimiento = (AcontecimientoDto)gson.fromJson(json.toString(), AcontecimientoDto.class);
		return acontecimiento;
	}	
	
	public String toJSon() {
		String json = "";
		Gson gson = new Gson();
		json = gson.toJson(this);
		return json;		
	}
}
