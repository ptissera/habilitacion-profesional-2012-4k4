package com.coming.cellprojectmanager.modelo;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import android.util.Log;

import com.coming.cellprojectmanager.utils.Utils;

public class TareaBo implements Serializable {
	private static final long serialVersionUID = 1680283877800310913L;
	private TareaDto dto;
	
	public static List<TareaBo> listaDesdeDtos(List<TareaDto> dtos) {
		if(dtos == null) {
			return new ArrayList<TareaBo>();
		}
		List<TareaBo> result = new ArrayList<TareaBo>(dtos.size());
		for(TareaDto dto : dtos) {
			TareaBo entity = new TareaBo(dto);
			result.add(entity);
		}
		return result;
	}
	
	public TareaBo() {
		this(null);
	}
	
	public TareaBo(TareaDto dto) {
		this.dto = dto;
	}

	public Long getId() {
		return dto.id;
	}

	public String getNombreTipoTarea() {
		return dto.nombreTipoTarea;
	}
	
	public Date getFechaInicioEstimada() {
		return Utils.fechaFromBackendStringToDate(dto.fechaInicioEstimada);		
	}
		
	public Date getFechaFinEstimada() {
		return Utils.fechaFromBackendStringToDate(dto.fechaFinEstimada);
	}
	
	public Date getFechaInicioReal() {
		return Utils.fechaFromBackendStringToDate(dto.fechaInicioReal);		
	}
	
	public void setFechaInicioReal(Date date) {
		String fecha = Utils.fechaToBackendString(date);
		dto.fechaInicioReal = fecha;
	}
	
	public Date getFechaFinReal() {
		return Utils.fechaFromBackendStringToDate(dto.fechaFinReal);
	}

	public void setFechaFinReal(Date date) {
		String fecha = Utils.fechaToBackendString(date);
		dto.fechaFinReal = fecha;
	}
	
	public String getEstado() {
		return dto.estado;
	}
	
	public void setEstado(String estado) {
		if(!transicionValida(estado)) {
			throw new IllegalArgumentException("Estado no valido para esta tarea: " + estado + 
					". Los estados posibles son: " + getTransicionesValidas(false).toString());
		}
		dto.estado = estado;
		if(dto.estado.equalsIgnoreCase("en ejecucion")) {
			setFechaFinReal(null);
			setFechaInicioReal(Calendar.getInstance().getTime());
		} else if(dto.estado.equalsIgnoreCase("resuelta")) {
			setFechaFinReal(Calendar.getInstance().getTime());
		}
	}
	
	public List<String> getTransicionesValidas(boolean incluirEsadoActual) {
		List<String> result = new ArrayList<String>();
		String estado = getEstado();
		if(incluirEsadoActual) {
			result.add(estado);
		}		
		if(estado.equalsIgnoreCase("creada")) {
			result.add("En ejecucion");
		} else if(estado.equalsIgnoreCase("en ejecucion")) {
			result.add("Suspendida");
			result.add("Resuelta");
		} else if(estado.equalsIgnoreCase("suspendida")) {
			result.add("En ejecucion");
		} else if(estado.equalsIgnoreCase("resuelta")) {
			result.add("En ejecucion");
		} else {
			Log.e("TareaBo", "Estado no reconocido.");
		}
		return result;
	}
	
	@Override
	public boolean equals(Object o) {
		if(o == this) {
			return true;
		}
		
		if(!(o instanceof TareaBo)) {
			return false;
		}
		
		return (((TareaBo)o).dto.id.equals(dto.id));
	}
	
	@Override
	public int hashCode() {
		return dto.id.hashCode();
	}
	
	private boolean transicionValida(String nuevoEstado) {
		List<String> transicionesValidas = getTransicionesValidas(false);
		return transicionesValidas.contains(nuevoEstado);
	}
}
