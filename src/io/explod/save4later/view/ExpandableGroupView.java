package io.explod.save4later.view;

import io.explod.android.app.view.group.EasyRelativeLayout;
import io.explod.save4later.R;
import android.content.Context;
import android.util.AttributeSet;
import android.widget.TextView;

public class ExpandableGroupView extends EasyRelativeLayout {

	private TextView label;

	public ExpandableGroupView(Context context) {
		super(context);
	}

	public ExpandableGroupView(Context context, AttributeSet attrs) {
		super(context, attrs);
	}

	public ExpandableGroupView(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
	}

	@Override
	protected void start(Context context) {
		super.start(context);
		this.label = (TextView) this.findViewById(R.id.label);
	}

	@Override
	public int getLayoutId() {
		return R.layout.view_expandablegroup;
	}

	@Override
	public void setFonts(Context context) {
		// TODO Fonts
	}

	public void setText(CharSequence text) {
		this.label.setText(text);
	}
}
