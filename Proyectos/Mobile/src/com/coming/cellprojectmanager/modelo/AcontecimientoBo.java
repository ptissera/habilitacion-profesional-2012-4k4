package com.coming.cellprojectmanager.modelo;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.coming.cellprojectmanager.utils.Utils;

public class AcontecimientoBo implements Serializable {
	private static final long serialVersionUID = 7765723390700152681L;
	private AcontecimientoDto dto;
	
	public static List<AcontecimientoBo> listaDesdeDtos(List<AcontecimientoDto> dtos) {
		if(dtos == null) {
			return new ArrayList<AcontecimientoBo>();
		}
		List<AcontecimientoBo> result = new ArrayList<AcontecimientoBo>(dtos.size());
		for(AcontecimientoDto dto : dtos) {
			AcontecimientoBo entity = new AcontecimientoBo(dto);
			result.add(entity);
		}
		return result;
	}
	
	public AcontecimientoBo() {
		this(new AcontecimientoDto());
	}
	
	public AcontecimientoBo(AcontecimientoDto dto) {
		this.dto = dto;
	}
	
	public Long getId() {
		return dto.id;
	}	
	
	public String getDescripcion() {
		return dto.descripcion;
	}
	
	public void setDescripcion(String descrpcion) {
		dto.descripcion = descrpcion;
	}
	
	public String getNombreTipoAcontecimiento() {
		return dto.nombreTipo;
	}

	public void setNombreTipoAcontecimeinto(String nombre) {
		dto.nombreTipo = nombre;
	}
	
	public Date getFechaCreacion() {
		return Utils.fechaFromBackendStringToDate(dto.fechaCreacion);		
	}
	
	public void setFechaCreacion(Date date) {
		String fecha = Utils.fechaToBackendString(date);
		dto.fechaCreacion = fecha;
	}
	
	public String getCreadoPor() {
		return dto.creadoPor;
	}
	
	public void setCradoPor(String creador) {
		dto.creadoPor = creador;
	}
	
	public void setTareaId(Long tareaId) {
		dto.tareaId = tareaId;
	}
	
	public String toJSon() {
		return dto.toJSon();		
	}
}
