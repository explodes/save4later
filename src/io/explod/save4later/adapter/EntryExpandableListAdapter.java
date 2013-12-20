package io.explod.save4later.adapter;

import io.explod.android.app.view.adapter.NamedListExpandableListAdapter;
import io.explod.android.collections.NamedListList;
import io.explod.android.collections.NamedList;
import io.explod.save4later.adapter.EntryListView.EntryListViewListener;
import io.explod.save4later.data.entry.Entry;
import android.content.Context;
import android.widget.TextView;

public class EntryExpandableListAdapter extends NamedListExpandableListAdapter<Entry, TextView, EntryListView> {

	private EntryListViewListener listener;

	public EntryExpandableListAdapter(Context context, NamedListList<Entry> entries, EntryListViewListener listener) {
		super(context, entries);
		this.listener = listener;
	}

	@Override
	public TextView createGroupView(Context context) {
		final TextView text = new TextView(context);
		return text;
	}

	@Override
	public void updateGroupView(TextView view, NamedList<Entry> data, boolean isExpanded) {
		final String name = data.getName();
		view.setText(name);
	}

	@Override
	public EntryListView createChildView(Context context) {
		final EntryListView view = new EntryListView(context);
		view.setEntryListViewListener(this.listener);
		return view;
	}

	@Override
	public void updateChildView(EntryListView view, Entry item, boolean isLastChild) {
		view.setEntry(item);
	}

	@Override
	public boolean isChildSelectable(NamedList<Entry> data, Entry item) {
		return true;
	}

}
