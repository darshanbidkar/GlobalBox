/**
 * 
 */
package com.globalbox.widgets;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.TextView;

/**
 * Custom text view for dialog
 * 
 * @author darshanbidkar
 * 
 */
public class DialogTextView extends TextView {

	/**
	 * constructor
	 * 
	 * @param context
	 */
	public DialogTextView(Context context) {
		super(context);
		// TODO Auto-generated constructor stub
	}

	/**
	 * constructor
	 * 
	 * @param context
	 * @param attrs
	 */
	public DialogTextView(Context context, AttributeSet attrs) {
		super(context, attrs);
		// TODO Auto-generated constructor stub
	}

	/**
	 * constructor
	 * 
	 * @param context
	 * @param attrs
	 * @param defStyle
	 */
	public DialogTextView(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void setSingleLine(boolean singleLine) {
		// TODO Auto-generated method stub
		super.setSingleLine(singleLine);
	}
}
