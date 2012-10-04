package com.coming.cellprojectmanager.modelo;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import android.location.Location;

public class SitioBo implements Serializable {
	private static final long serialVersionUID = 2823183802673688497L;
	private SitioDto dto;
	private float radioEnMts;

	public static List<SitioBo> listaDesdeDtos(List<SitioDto> dtos) {
		List<SitioBo> result = new ArrayList<SitioBo>(dtos.size());
		for(SitioDto dto : dtos) {
			SitioBo entity = new SitioBo(dto);
			result.add(entity);
		}
		return result;
	}
	
	public SitioBo() {
		this(null);
	}
	
	public SitioBo(SitioDto dto) {
		this.dto = dto;
		radioEnMts = 500;
	}
	
	public Long getId() {
		return dto.id;
	}

	public String getNombre() {
		return dto.nombre;
	}

	public String getProvincia() {
		return dto.provincia;
	}

	public Double getLatitud() {
		return dto.latitud;
	}
	
	public Double getLongitud() {
		return dto.longitud;
	}
	
	public boolean estaCercaDeCoordenada(double latitud, double longitud) {
		float[] results = new float[3];
		Location.distanceBetween(dto.latitud, dto.longitud, latitud, longitud, results);
		return (results[0] <= radioEnMts);
	}
}
