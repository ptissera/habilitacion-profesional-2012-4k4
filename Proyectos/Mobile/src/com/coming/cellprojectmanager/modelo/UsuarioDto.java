package com.coming.cellprojectmanager.modelo;

import java.io.Serializable;

import com.google.gson.Gson;

public class UsuarioDto implements Serializable {
	private static final long serialVersionUID = -3794174841478569995L;
	public Long id;
	public String nombre;

	public static UsuarioDto fromJSon(String json) {
		UsuarioDto usu = null;
		Gson gson = new Gson();
		usu = (UsuarioDto)gson.fromJson(json.toString(), UsuarioDto.class);
		return usu;
	}
}
