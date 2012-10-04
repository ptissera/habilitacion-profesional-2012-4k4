package com.coming.cellprojectmanager.modelo;

import java.io.Serializable;

import com.google.gson.Gson;

public class TareaDto  implements Serializable {
	private static final long serialVersionUID = -5398815335363693889L;
	public Long id;
	public String nombreTipoTarea;
	public String fechaInicioEstimada;
	public String fechaFinEstimada;
	public String fechaInicioReal;
	public String fechaFinReal;
	public String estado;
	
	public static TareaDto fromJSon(String json) {
		TareaDto tarea = null;
		Gson gson = new Gson();
		tarea = (TareaDto)gson.fromJson(json.toString(), TareaDto.class);
		return tarea;
	}
	
	public String toJSon() {
		String json = "";
		Gson gson = new Gson();
		json = gson.toJson(this);
		return json;		
	}
	
	public boolean equals(Object o) {
		if(o == this) {
			return true;
		}
		
		if(!(o instanceof TareaDto)) {
			return false;
		}
		
		return (((TareaDto)o).id.equals(id));
	}
}
