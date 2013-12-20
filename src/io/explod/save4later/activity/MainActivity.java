package io.explod.save4later.activity;

import io.explod.android.app.activity.EasyActivity;
import io.explod.android.collections.NamedList;
import io.explod.android.collections.NamedListList;
import io.explod.save4later.R;
import io.explod.save4later.adapter.EntryExpandableListAdapter;
import io.explod.save4later.adapter.EntryListView;
import io.explod.save4later.adapter.EntryListView.EntryListViewListener;
import io.explod.save4later.data.entry.Entry;
import io.explod.save4later.data.entry.EntryManager;

import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.widget.ExpandableListView;

public class MainActivity extends EasyActivity implements EntryListViewListener {

	private ExpandableListView list;
	private NamedListList<Entry> data;
	private EntryExpandableListAdapter adapter;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		this.list = (ExpandableListView) this.findViewById(R.id.list_saves);
	}

	@Override
	protected void onResume() {
		super.onResume();
		this.updateData();
	}

	private void updateData() {
		this.data = this.createData();
		this.adapter = new EntryExpandableListAdapter(this, this.data, this);
		this.list.setAdapter(this.adapter);
	}

	private NamedListList<Entry> createData() {
		final List<Entry> entries = EntryManager.getEntries(this);
		final Map<String, NamedList<Entry>> map = new HashMap<String, NamedList<Entry>>();
		final NamedListList<Entry> data = new NamedListList<Entry>();
		for (final Entry entry : entries) {
			final Uri uri = entry.getUri();
			final String name = uri.getHost();
			NamedList<Entry> list = map.get(name);
			if (list == null) {
				list = new NamedList<Entry>(name);
				map.put(name, list);
				data.add(list);
			}
			list.add(entry);
		}
		this.sortData(data);
		return data;
	}

	private void sortData(List<NamedList<Entry>> data) {
		Collections.sort(data, new Comparator<NamedList<Entry>>() {
			@Override
			public int compare(NamedList<Entry> lhs, NamedList<Entry> rhs) {
				final String lhsName = lhs.getName();
				final String rhsName = rhs.getName();
				return lhsName.compareTo(rhsName);
			}
		});
		final Comparator<Entry> entryComparator = new Comparator<Entry>() {
			@Override
			public int compare(Entry lhs, Entry rhs) {
				final Date lhsDate = lhs.getDate();
				final Date rhsDate = rhs.getDate();
				return lhsDate.compareTo(rhsDate);
			}
		};
		for (final NamedList<Entry> list : data) {
			Collections.sort(list, entryComparator);
		}
	}

	@Override
	public int getLayoutId() {
		return R.layout.activity_main;
	}

	@Override
	public void setFonts(Context context) {
		// n/a
	}

	@Override
	public void clickDelete(EntryListView sender, Entry entry) {
		EntryManager.removeEntry(this, entry);
		boolean updated = this.data.removeAny(entry);
		updated |= this.data.removeEmptyLists();
		if (updated) {
			this.adapter.notifyDataSetChanged();
		}
	}

	@Override
	public void click(EntryListView sender, Entry entry) {
		final Intent intent = new Intent(Intent.ACTION_VIEW);
		final Uri uri = entry.getUri();
		intent.setData(uri);
		this.startActivity(intent);
	}

}
