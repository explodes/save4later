package io.explod.save4later.data;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;

public class Preferences {

	private static final String PREFERENCES = "prefs";

	public static final SharedPreferences getPrivatePreferences(Context context) {
		return context.getSharedPreferences(PREFERENCES, Context.MODE_PRIVATE);
	}
	
	public static final Editor getPrivatePreferencesEditor(Context context) {
		final SharedPreferences preferences = getPrivatePreferences(context);
		return preferences.edit();
	}
}
