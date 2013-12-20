package io.explod.save4later.data.entry;

import io.explod.save4later.data.Preferences;
import io.explod.save4later.helpers.S4LLog;

import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.net.Uri;

public class EntryManager implements IEntry {

	public static final List<Entry> getEntries(Context context) {

		final SharedPreferences preferences = Preferences.getPrivatePreferences(context);
		final String in = preferences.getString(PREFS_ENTRIES, null);
		final List<Entry> entries = new ArrayList<Entry>();
		if (in != null) {
			try {
				final JSONArray arr = new JSONArray(in);
				final int N = arr.length();
				for (int index = 0; index < N; index++) {
					final JSONObject json = arr.getJSONObject(index);
					final Entry entry = new Entry(json);
					entries.add(entry);
				}
			} catch (JSONException error) {
				setEntries(context, null);
				S4LLog.e(null, context, "Error getting entries, all entries have been lost", error);
			}
		}
		return entries;
	}

	public static final void setEntries(Context context, List<Entry> entries) {
		JSONArray arr = new JSONArray();
		if (entries != null && entries.size() != 0) {
			try {
				for (final Entry entry : entries) {
					final JSONObject json = entry.toJSON();
					arr.put(json);
				}
			} catch (JSONException error) {
				S4LLog.e(null, context, "Error saving entries", error);
			}
		}
		final Editor editor = Preferences.getPrivatePreferencesEditor(context);
		final String out = arr.toString();
		editor.putString(PREFS_ENTRIES, out);
		editor.apply();
	}

	public static final void addEntry(Context context, Uri uri) {
		final Entry entry = new Entry(uri);
		final List<Entry> entries = getEntries(context);
		entries.add(entry);
		setEntries(context, entries);
	}

	public static final boolean removeEntry(Context context, Entry entry) {
		final List<Entry> entries = getEntries(context);
		boolean removed = entries.remove(entry);
		setEntries(context, entries);
		return removed;
	}

}
