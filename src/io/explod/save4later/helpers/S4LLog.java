package io.explod.save4later.helpers;

import io.explod.save4later.Settings;
import android.content.Context;
import android.util.Log;
import android.widget.Toast;

public final class S4LLog {

	private static final boolean enabled() {
		return Settings.ENABLE_LOGGING;
	}

	private static final String makeTag(Object caller) {
		if (caller == null) {
			return "S4L";
		} else {
			final Class<?> klass = caller.getClass();
			final String tag = klass.getName();
			return tag;
		}
	}

	private static final void toastException(Context context, String msg, Throwable e) {
		if (e != null && context != null) {
			Toast toast = Toast.makeText(context, msg, Toast.LENGTH_SHORT);
			toast.show();
		}
	}

	public static final void v(Object caller, Context context, String msg) {
		if (enabled()) {
			final String tag = makeTag(caller);
			Log.v(tag, msg);
		}
	}

	public static final void v(Object caller, Context context, String msg, Throwable e) {
		if (enabled()) {
			final String tag = makeTag(caller);
			Log.v(tag, msg, e);
			toastException(context, msg, e);

		}
	}

	public static final void d(Object caller, Context context, String msg) {
		if (enabled()) {
			final String tag = makeTag(caller);
			Log.d(tag, msg);
		}
	}

	public static final void d(Object caller, Context context, String msg, Throwable e) {
		if (enabled()) {
			final String tag = makeTag(caller);
			Log.d(tag, msg, e);
			toastException(context, msg, e);
		}
	}

	public static final void i(Object caller, Context context, String msg) {
		if (enabled()) {
			final String tag = makeTag(caller);
			Log.i(tag, msg);
		}
	}

	public static final void i(Object caller, Context context, String msg, Throwable e) {
		if (enabled()) {
			final String tag = makeTag(caller);
			Log.i(tag, msg, e);
			toastException(context, msg, e);
		}
	}

	public static final void w(Object caller, Context context, String msg) {
		final String tag = makeTag(caller);
		Log.w(tag, msg);
		toastException(context, msg, null);
	}

	public static final void w(Object caller, Context context, String msg, Throwable e) {
		final String tag = makeTag(caller);
		Log.w(tag, msg, e);
		toastException(context, msg, e);
	}

	public static final void e(Object caller, Context context, String msg) {
		final String tag = makeTag(caller);
		Log.e(tag, msg);
		toastException(context, msg, null);
	}

	public static final void e(Object caller, Context context, String msg, Throwable e) {
		final String tag = makeTag(caller);
		Log.e(tag, msg, e);
		toastException(context, msg, e);
	}

}
