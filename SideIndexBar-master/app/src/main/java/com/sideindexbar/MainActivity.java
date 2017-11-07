package com.sideindexbar;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ListView;
import android.widget.TextView;

import java.util.List;

public class MainActivity extends Activity {

	private TextView mText;
	private ListView listView;

	private String[] itemStrs;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		listView = (ListView) findViewById(R.id.list);

		mText = (TextView) findViewById(R.id.text_dialog);

		SideIndexBar indexBar = (SideIndexBar) findViewById(R.id.index_bar);
		indexBar.setLetters("ABCDEFHIJKLMOPQSTUVXYZ#");
		indexBar.setTextDialog(mText);
		indexBar.setOnLetterChangedListener(new SideIndexBar.OnLetterChangedListener() {
			@Override
			public void onChanged(String s, int position) {
				Log.e("SideIndexBar", s + " position:" + position);
				int currentPostion = getLetterPosition(itemStrs, s);
				Log.e("SideIndexBar", s + " currentPostion:" + currentPostion);
				//listView.setSelection的作用是移动position到顶部
				//but,如果把listView拉到底部了,position还没有达到顶部位置
				//那么该position的item就可能显示在中间
				listView.setSelection(currentPostion);
			}
		});

		itemStrs = getResources().getStringArray(R.array.list);


	}

	/**
	 * 获取所选中的索引在列表中的位置
	 *
	 * @param items
	 * @param letter
	 * @return
	 */
	public static int getLetterPosition(String[] items, String letter) {
		int position = -1;

		if (items != null && items.length>0 && !"".equals(letter)) {
			for (int i = 0; i < items.length; i++) {
				String itemStr = items[i];
				if (itemStr.toUpperCase().contains(letter)) {
					position = i;
					break;
				}
			}
		}
		return position;
	}
}
