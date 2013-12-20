package io.explod.save4later.activity;

import io.explod.android.app.activity.EasyActivity;
import io.explod.save4later.R;
import io.explod.save4later.data.entry.EntryManager;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.widget.Toast;

public class SaveActivity extends EasyActivity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		final Intent intent = this.getIntent();

		if (intent != null) {
			final Uri uri = intent.getData();
			if (uri != null) {
				this.saveUri(uri);
			}
		}
		this.finish();
	}

	private void saveUri(Uri uri) {
		EntryManager.addEntry(this, uri);
		final Toast toast = Toast.makeText(this, R.string.saved_uri, Toast.LENGTH_SHORT);
		toast.show();
	}

	@Override
	public int getLayoutId() {
		return 0;
	}

	@Override
	public void setFonts(Context context) {
		// n/a
	}

	private void gotoMain() {
		final Intent intent = new Intent(this, MainActivity.class);
		this.startActivity(intent);
		this.finish();
	}

}
