package com.coming.cellprojectmanager.modelo;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;

public class SesionBo {
	public static Long getUsuarioId(Context ctx) {
		SharedPreferences pref = ctx.getSharedPreferences("cellprojectmanagermobile", Context.MODE_PRIVATE);
    	Long usuarioId = pref.getLong("usuarioId", -1);
    	return usuarioId;
	}

	public static String getUsuarinombre(Context ctx) {
		SharedPreferences pref = ctx.getSharedPreferences("cellprojectmanagermobile", Context.MODE_PRIVATE);
    	String usuarioNombre = pref.getString("usuarioNombre", "");
    	return usuarioNombre;
	}
	
	public static void setUsuario(Context ctx, Long usuarioId, String usuarioNombre) {
		SharedPreferences pref = ctx.getSharedPreferences("cellprojectmanagermobile", Context.MODE_PRIVATE);
    	Editor editor = pref.edit();
    	editor.putLong("usuarioId", usuarioId);
    	editor.putString("usuarioNombre", usuarioNombre);
    	editor.commit();
	}
}
