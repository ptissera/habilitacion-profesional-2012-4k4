package com.coming.cellprojectmanager.ws;

import android.content.Context;

public class GetTiposAcontecimientosWs extends GetWsBase {

	public GetTiposAcontecimientosWs(Context ctx) {
		super(ctx);
	}

	@Override
	protected boolean checkParamsOk(String... params) {
		boolean paramsAreOk = true;
		return paramsAreOk;
	}

	@Override
	protected String doFakeCall(String... params) {
		return "{" +
				"\"error\":{\"codigo\":0,\"descripcion\":\"Exito\"}," +
				"\"tipos\":[{\"id\":1,\"nombre\":\"Materiales cliente\"}," +
				"{\"id\":2,\"nombre\":\"Permiso acceso\"}," +
				"{\"id\":3,\"nombre\":\"Ingenieria incompeta\"}]" +
				"}";
	}

	@Override
	protected String buildUrl(String... params) {
		StringBuilder builder = new StringBuilder();
		builder.append(Common.WS_URL);
		builder.append(Common.WS_TIPOS_ACONTECIMIENTOS);
		return builder.toString();
	}
}