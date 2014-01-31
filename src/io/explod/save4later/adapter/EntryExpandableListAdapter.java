package io.explod.save4later.adapter;

import io.explod.android.app.view.adapter.NamedListExpandableListAdapter;
import io.explod.android.collections.NamedList;
import io.explod.android.collections.NamedListList;
import io.explod.save4later.data.entry.Entry;
import io.explod.save4later.view.EntryListItemView;
import io.explod.save4later.view.EntryListItemView.EntryListItemViewListener;
import io.explod.save4later.view.ExpandableGroupView;
import android.content.Context;

public class EntryExpandableListAdapter extends NamedListExpandableListAdapter<Entry, ExpandableGroupView, EntryListItemView> {

	private EntryListItemViewListener listener;

	public EntryExpandableListAdapter(Context context, NamedListList<Entry> entries, EntryListItemViewListener listener) {
		super(context, entries);
		this.listener = listener;
	}

	@Override
	public ExpandableGroupView createGroupView(Context context) {
		final ExpandableGroupView view = new ExpandableGroupView(context);
		return view;
	}

	@Override
	public void updateGroupView(ExpandableGroupView view, NamedList<Entry> data, boolean isExpanded) {
		final String name = data.getName();
		view.setText(name);
	}

	@Override
	public EntryListItemView createChildView(Context context) {
		final EntryListItemView view = new EntryListItemView(context);
		view.setEntryListItemViewListener(this.listener);
		return view;
	}

	@Override
	public void updateChildView(EntryListItemView view, Entry item, boolean isLastChild) {
		view.setEntry(item);
	}

	@Override
	public boolean isChildSelectable(NamedList<Entry> data, Entry item) {
		return true;
	}

}
