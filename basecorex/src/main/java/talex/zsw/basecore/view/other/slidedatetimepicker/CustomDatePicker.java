package talex.zsw.basecore.view.other.slidedatetimepicker;

import android.content.Context;
import android.graphics.drawable.ColorDrawable;
import android.util.AttributeSet;
import android.view.View;
import android.widget.DatePicker;
import android.widget.NumberPicker;

import java.lang.reflect.Field;

import talex.zsw.basecore.R;
import talex.zsw.basecore.util.LogTool;

/**
 * A subclass of {@link DatePicker} that uses
 * reflection to allow for customization of the default blue
 * dividers.
 *
 * @author jjobes
 */
public class CustomDatePicker extends DatePicker
{
	private static final String TAG = "BaseCore";

	Class<?> idClass = null;
	Class<?> numberPickerClass = null;
	Field selectionDividerField = null;
	Field monthField = null;
	Field dayField = null;
	Field yearField = null;
	NumberPicker monthNumberPicker = null;
	NumberPicker dayNumberPicker = null;
	NumberPicker yearNumberPicker = null;

	public CustomDatePicker(Context context, AttributeSet attrs)
	{
		super(context, attrs);
		try
		{
			// Create an instance of the id class
			idClass = Class.forName("com.android.internal.R$id");

			// Get the fields that store the resource IDs for the month, day and year NumberPickers
			monthField = idClass.getField("month");
			dayField = idClass.getField("day");
			yearField = idClass.getField("year");

			// Use the resource IDs to get references to the month, day and year NumberPickers
			monthNumberPicker = (NumberPicker) findViewById(monthField.getInt(null));
			dayNumberPicker = (NumberPicker) findViewById(dayField.getInt(null));
			yearNumberPicker = (NumberPicker) findViewById(yearField.getInt(null));

			numberPickerClass = Class.forName("android.widget.NumberPicker");

			// Set the value of the mSelectionDivider field in the month, day and year NumberPickers
			// to refer to our custom drawables
			selectionDividerField = numberPickerClass.getDeclaredField("mSelectionDivider");
			selectionDividerField.setAccessible(true);
			selectionDividerField.set(monthNumberPicker, getResources().getDrawable(R.drawable.selection_divider));
			selectionDividerField.set(dayNumberPicker, getResources().getDrawable(R.drawable.selection_divider));
			selectionDividerField.set(yearNumberPicker, getResources().getDrawable(R.drawable.selection_divider));
		}
		catch(ClassNotFoundException e)
		{
			LogTool.e(TAG, "ClassNotFoundException in CustomDatePicker", e);
		}
		catch(NoSuchFieldException e)
		{
			LogTool.e(TAG, "NoSuchFieldException in CustomDatePicker", e);
		}
		catch(IllegalAccessException e)
		{
			LogTool.e(TAG, "IllegalAccessException in CustomDatePicker", e);
		}
		catch(IllegalArgumentException e)
		{
			LogTool.e(TAG, "IllegalArgumentException in CustomDatePicker", e);
		}
	}

	public void setColor(int color)
	{
		try
		{
			if(selectionDividerField != null)
			{
				selectionDividerField.setAccessible(true);
				selectionDividerField.set(monthNumberPicker, new ColorDrawable(color));
				selectionDividerField.set(dayNumberPicker, new ColorDrawable(color));
				selectionDividerField.set(yearNumberPicker, new ColorDrawable(color));
			}
		}
		catch(IllegalAccessException e)
		{
			LogTool.e(TAG, "IllegalAccessException in CustomDatePicker", e);
		}
		catch(IllegalArgumentException e)
		{
			LogTool.e(TAG, "IllegalArgumentException in CustomDatePicker", e);
		}
	}

	public void showDayPicker(boolean show)
	{
		if(dayNumberPicker!=null)
		{
			if(show)
			{
				dayNumberPicker.setVisibility(View.VISIBLE);
			}
			else
			{
				dayNumberPicker.setVisibility(View.GONE);
			}
		}
	}
}
