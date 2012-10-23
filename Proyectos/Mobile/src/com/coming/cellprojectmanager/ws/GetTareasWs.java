package com.coming.cellprojectmanager.ws;

import android.content.Context;
import android.util.Log;

public class GetTareasWs extends GetWsBase {

	public GetTareasWs(Context ctx) {
		super(ctx);
	}

	@Override
	protected boolean checkParamsOk(String... params) {
		boolean paramsAreOk = (params.length == 1);
		if(paramsAreOk == false) {
			String msg = "Parametros invalidos. Deben ser [0]: usuarioId";
			Log.e("TareasWs", msg);
		}
		return paramsAreOk;
	}

	@Override
	protected String doFakeCall(String... params) {
		return "{" +
				"\"error\":{\"codigo\":0,\"descripcion\":\"Exito\"}," +
				"\"tareas\":[{\"id\":1,\"nombreTipoTarea\":\"Tipo tarea 1\",\"nombreSitio\":\"Sitio 1\",\"fechaInicioEstimada\":\"01122012\",\"fechaFinEstimada\":\"02122012\",\"fechaInicioReal\":\"\",\"fechaFinReal\":\"\",\"estado\":\"Creada\",\"descripcion\":\"Saltar sobre el pie de la antena hasta que se caiga. Saltar sobre el pie de la antena hasta que se caiga. Saltar sobre el pie de la antena hasta que se caiga. Saltar sobre el pie de la antena hasta que se caiga. Saltar sobre el pie de la antena hasta que se caiga. Saltar sobre el pie de la antena hasta que se caiga.\"}," +
				"{\"id\":2,\"nombreTipoTarea\":\"Tipo tarea 2\",\"nombreSitio\":\"Sitio 1\",\"fechaInicioEstimada\":\"01122012\",\"fechaFinEstimada\":\"02122012\",\"fechaInicioReal\":\"01122012\",\"fechaFinReal\":\"\",\"estado\":\"En Ejecucion\",\"descripcion\":\"Ver sistema de cableado sobre tapia derecha.\"}," +
				"{\"id\":3,\"nombreTipoTarea\":\"Tipo tarea 3\",\"nombreSitio\":\"Sitio 2\",\"fechaInicioEstimada\":\"01122012\",\"fechaFinEstimada\":\"02122012\",\"fechaInicioReal\":\"01122012\",\"fechaFinReal\":\"\",\"estado\":\"Suspendida\",\"descripcion\":\"Reemplazo de conductores apocrifos.\"}," +
				"{\"id\":4,\"nombreTipoTarea\":\"Tipo tarea 4\",\"nombreSitio\":\"Sitio 1\",\"fechaInicioEstimada\":\"01122012\",\"fechaFinEstimada\":\"02122012\",\"fechaInicioReal\":\"01122012\",\"fechaFinReal\":\"02122012\",\"estado\":\"Resuelta\",\"descripcion\":\"\"}]" +
				"}";
	}

	@Override
	protected String buildUrl(String... params) {
		String usuarioId = params[0];
		StringBuilder builder = new StringBuilder();
		builder.append(Common.WS_URL);
		builder.append(Common.WS_TAREAS);
		builder.append(usuarioId);
		return builder.toString();
	}

	@Override
	protected boolean validResponseCode(int responseCode) {
		return (responseCode == 200);
	}
}
