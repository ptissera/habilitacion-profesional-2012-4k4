package com.coming.cellprojectmanager.modelo;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;

public class SesionBo {
	public static String getUsuario(Context ctx) {
		SharedPreferences pref = ctx.getSharedPreferences("cellprojectmanagermobile", Context.MODE_PRIVATE);
    	String usuario = pref.getString("usuario", "");
    	return usuario;
	}
	
	public static void setUsuario(Context ctx, String usuario) {
		SharedPreferences pref = ctx.getSharedPreferences("cellprojectmanagermobile", Context.MODE_PRIVATE);
    	Editor editor = pref.edit();
    	editor.putString("usuario", usuario);
    	editor.commit();
	}
}
