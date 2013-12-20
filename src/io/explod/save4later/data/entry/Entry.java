package io.explod.save4later.data.entry;

import java.util.Date;
import java.util.UUID;

import org.json.JSONException;
import org.json.JSONObject;

import android.net.Uri;

public class Entry implements IEntry {

	private UUID uuid;
	private Date date;
	private Uri uri;

	public Entry(Uri uri) {
		this.uri = uri;
		this.date = new Date();
		this.uuid = UUID.randomUUID();
	}

	Entry(JSONObject json) throws JSONException {
		final long milliseconds = json.getLong(kEntryJsonDate);
		this.date = new Date(milliseconds);

		final String url = json.getString(kEntryJsonUri);
		this.uri = Uri.parse(url);

		final String uid = json.getString(kEntryJsonUuid);
		this.uuid = UUID.fromString(uid);
	}

	JSONObject toJSON() throws JSONException {
		JSONObject json = new JSONObject();

		final Date date = this.getDate();
		final long milliseconds = date.getTime();
		json.put(kEntryJsonDate, milliseconds);

		final Uri uri = this.getUri();
		final String url = uri.toString();
		json.put(kEntryJsonUri, url);

		final UUID uuid = this.getUUID();
		final String uid = uuid.toString();
		json.put(kEntryJsonUuid, uid);

		return json;
	}

	public void setUUID(UUID uuid) {
		this.uuid = uuid;
	}

	public UUID getUUID() {
		return this.uuid;
	}

	public void setUri(Uri uri) {
		this.uri = uri;
	}

	public Uri getUri() {
		return this.uri;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public Date getDate() {
		return this.date;
	}

	@Override
	public boolean equals(Object o) {
		return o != null && o instanceof Entry && this.uuid != null && this.uuid.equals(((Entry) o).uuid);
	}
}
