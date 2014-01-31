package io.explod.save4later.view;

import io.explod.android.app.view.group.EasyRelativeLayout;
import io.explod.save4later.R;
import io.explod.save4later.data.entry.Entry;
import android.content.Context;
import android.net.Uri;
import android.util.AttributeSet;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

public class EntryListItemView extends EasyRelativeLayout implements OnClickListener {

	// inner
	public interface EntryListItemViewListener {

		public void clickDelete(EntryListItemView sender);

		public void clickPreview(EntryListItemView sender);

		public void clickEntry(EntryListItemView sender);
	}

	// instance

	// data
	private Entry entry;
	private EntryListItemViewListener listener;
	// views
	private ImageButton delete;
	private TextView label;
	private ImageView preview;

	public EntryListItemView(Context context) {
		super(context);
	}

	public EntryListItemView(Context context, AttributeSet attrs) {
		super(context, attrs);
	}

	public EntryListItemView(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
	}

	@Override
	protected void start(Context context) {
		super.start(context);
		this.delete = (ImageButton) this.findViewById(R.id.button_delete);
		this.label = (TextView) this.findViewById(R.id.label_uri);
		this.preview = (ImageView) this.findViewById(R.id.image_preview);
		this.delete.setOnClickListener(this);
		this.preview.setOnClickListener(this);
		this.label.setOnClickListener(this);
		this.setOnClickListener(this);
	}

	@Override
	public int getLayoutId() {
		return R.layout.view_entrylist;
	}

	@Override
	public void setFonts(Context context) {
		// TODO Fonts
	}

	public void setEntry(Entry entry) {
		this.entry = entry;
		this.refreshEntry();
	}

	public Entry getEntry() {
		return this.entry;
	}

	public void setEntryListItemViewListener(EntryListItemViewListener listener) {
		this.listener = listener;
	}

	public void refreshEntry() {
		final Entry entry = this.getEntry();
		final Uri uri = entry.getUri();
		final String link = uri.toString();
		this.label.setText(link);
	}

	@Override
	public void onClick(View view) {
		if (this.listener != null) {
			if (view == this.delete) {
				this.listener.clickDelete(this);
			} else if (view == this.preview) {
				this.listener.clickPreview(this);
			} else {
				this.listener.clickEntry(this);
			}
		}
	}
}
