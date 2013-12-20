package io.explod.save4later.adapter;

import io.explod.android.app.view.group.EasyRelativeLayout;
import io.explod.save4later.R;
import io.explod.save4later.data.entry.Entry;
import android.content.Context;
import android.net.Uri;
import android.util.AttributeSet;
import android.view.View;
import android.widget.TextView;

public class EntryListView extends EasyRelativeLayout {

	public interface EntryListViewListener {
		public void clickDelete(EntryListView sender, Entry entry);

		public void click(EntryListView sender, Entry entry);
	}

	private Entry entry;
	private EntryListViewListener listener;
	private TextView uriTextView;

	public EntryListView(Context context) {
		super(context);
	}

	public EntryListView(Context context, AttributeSet attrs) {
		super(context, attrs);
	}

	public EntryListView(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
	}

	@Override
	protected void start(Context context) {
		super.start(context);

		this.uriTextView = (TextView) this.findViewById(R.id.text_uri);

		this.findViewById(R.id.button_delete).setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View view) {
				final EntryListView self = EntryListView.this;
				if (self.listener != null) {
					self.listener.clickDelete(self, self.entry);
				}
			}
		});
		this.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				final EntryListView self = EntryListView.this;
				if (self.listener != null) {
					self.listener.click(self, self.entry);
				}
			}
		});
	}

	public Entry getEntry() {
		return this.entry;
	}

	public void setEntry(Entry entry) {
		this.entry = entry;
		this.updateView();
	}

	public void setEntryListViewListener(EntryListViewListener listener) {
		this.listener = listener;
	}

	private void updateView() {
		final Entry entry = this.getEntry();
		final Uri uri = entry.getUri();
		final String url = uri.toString();
		this.uriTextView.setText(url);
	}

	@Override
	public int getLayoutId() {
		return R.layout.view_entrylist;
	}

	@Override
	public void setFonts(Context context) {
		// n/a
	}

}
