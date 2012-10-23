package com.coming.cellprojectmanager.modelo;

import java.util.ArrayList;
import java.util.List;

public class TipoAcontecimientoBo {
	private TipoAcontecimientoDto dto;
		
	public static List<TipoAcontecimientoBo> listaDesdeDtos(List<TipoAcontecimientoDto> dtos) {
		if(dtos == null) {
			return new ArrayList<TipoAcontecimientoBo>();
		}
		List<TipoAcontecimientoBo> result = new ArrayList<TipoAcontecimientoBo>(dtos.size());
		for(TipoAcontecimientoDto dto : dtos) {
			TipoAcontecimientoBo entity = new TipoAcontecimientoBo(dto);
			result.add(entity);
		}
		return result;
	}
	
	public TipoAcontecimientoBo() {
		this(null);
	}
	
	public TipoAcontecimientoBo(TipoAcontecimientoDto dto) {
		this.dto = dto;
	}
	
	public String getNombre() {
		return dto.nombre;
	}
	
	@Override
	public boolean equals(Object o) {
		if(o == this) {
			return true;
		}
		
		if(!(o instanceof TipoAcontecimientoBo)) {
			return false;
		}
		
		return (((TipoAcontecimientoBo)o).dto.nombre.equals(dto.nombre));
	}
	
	@Override
	public int hashCode() {
		return dto.nombre.hashCode();
	}
	
	public String toString() {
		return getNombre();
	}
}
