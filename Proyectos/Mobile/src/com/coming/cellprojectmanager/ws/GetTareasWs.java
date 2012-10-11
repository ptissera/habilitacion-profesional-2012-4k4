package com.coming.cellprojectmanager.ws;

import android.content.Context;
import android.util.Log;

public class GetTareasWs extends GetWsBase {

	public GetTareasWs(Context ctx) {
		super(ctx);
	}

	@Override
	protected boolean checkParamsOk(String... params) {
		boolean paramsAreOk = (params.length == 2);
		if(paramsAreOk == false) {
			String msg = "Parametros invalidos. Deben ser [0]: nombre usuario, [1]: sitio id";
			Log.e("TareasWs", msg);
		}
		return paramsAreOk;
	}

	@Override
	protected String doFakeCall(String... params) {
		String sitioId = params[1];
		if(sitioId.equalsIgnoreCase("1")) {
			return "{" +
					"\"error\":{\"codigo\":0,\"descripcion\":\"Exito\"}," +
					"\"tareas\":[{\"id\":1,\"nombreTipoTarea\":\"Tipo tarea 1\",\"fechaInicioEstimada\":\"01122012\",\"fechaFinEstimada\":\"02122012\",\"fechaInicioReal\":\"\",\"fechaFinReal\":\"\",\"estado\":\"Creada\"}," +
					"{\"id\":2,\"nombreTipoTarea\":\"Tipo tarea 2\",\"fechaInicioEstimada\":\"01122012\",\"fechaFinEstimada\":\"02122012\",\"fechaInicioReal\":\"01122012\",\"fechaFinReal\":\"\",\"estado\":\"En Ejecucion\"}," +
					"{\"id\":3,\"nombreTipoTarea\":\"Tipo tarea 3\",\"fechaInicioEstimada\":\"01122012\",\"fechaFinEstimada\":\"02122012\",\"fechaInicioReal\":\"01122012\",\"fechaFinReal\":\"\",\"estado\":\"Suspendida\"}," +
					"{\"id\":4,\"nombreTipoTarea\":\"Tipo tarea 4\",\"fechaInicioEstimada\":\"01122012\",\"fechaFinEstimada\":\"02122012\",\"fechaInicioReal\":\"01122012\",\"fechaFinReal\":\"02122012\",\"estado\":\"Resuelta\"}]" +
					"}";			
		} else if(sitioId.equalsIgnoreCase("2")) {
			return "{" +
					"\"error\":{\"codigo\":0,\"descripcion\":\"Exito\"}," +
					"\"tareas\":[{\"id\":1,\"nombreTipoTarea\":\"Tipo tarea 1\",\"fechaInicioEstimada\":\"01122012\",\"fechaFinEstimada\":\"02122012\",\"fechaInicioReal\":\"\",\"fechaFinReal\":\"\",\"estado\":\"Creada\"}," +
					"{\"id\":2,\"nombreTipoTarea\":\"Tipo tarea 2\",\"fechaInicioEstimada\":\"01122012\",\"fechaFinEstimada\":\"02122012\",\"fechaInicioReal\":\"01122012\",\"fechaFinReal\":\"\",\"estado\":\"En Ejecucion\"}," +
					"{\"id\":3,\"nombreTipoTarea\":\"Tipo tarea 3\",\"fechaInicioEstimada\":\"01122012\",\"fechaFinEstimada\":\"02122012\",\"fechaInicioReal\":\"01122012\",\"fechaFinReal\":\"\",\"estado\":\"Suspendida\"}," +
					"{\"id\":4,\"nombreTipoTarea\":\"Tipo tarea 4\",\"fechaInicioEstimada\":\"01122012\",\"fechaFinEstimada\":\"02122012\",\"fechaInicioReal\":\"01122012\",\"fechaFinReal\":\"02122012\",\"estado\":\"Resuelta\"}," +
					"{\"id\":5,\"nombreTipoTarea\":\"Tipo tarea 2\",\"fechaInicioEstimada\":\"01122012\",\"fechaFinEstimada\":\"02122012\",\"fechaInicioReal\":\"01122012\",\"fechaFinReal\":\"02122012\",\"estado\":\"Resuelta\"}," +
					"{\"id\":6,\"nombreTipoTarea\":\"Tipo tarea 3\",\"fechaInicioEstimada\":\"01122012\",\"fechaFinEstimada\":\"02122012\",\"fechaInicioReal\":\"01122012\",\"fechaFinReal\":\"02122012\",\"estado\":\"Resuelta\"}]" +
					"}";			
		} else {
			return "{" +
					"\"error\":{\"codigo\":1,\"descripcion\":\"No hay tareas para el sitio.\"}" +
					"}";			
		}
	}

	@Override
	protected String buildUrl(String... params) {
		String nombreUsuario = params[0];
		String sitioId = params[1];
		StringBuilder builder = new StringBuilder();
		builder.append(Common.WS_URL);
		builder.append(Common.WS_TAREAS);
		builder.append("/nombreUsuario/");
		builder.append(nombreUsuario);
		builder.append("/sitioId/");
		builder.append(sitioId);
		return builder.toString();
	}
}
