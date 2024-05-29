package com.temxa.rehber;
	
	import android.app.Dialog;
	import android.content.Context;
	import android.graphics.drawable.ColorDrawable;
	import android.text.method.ScrollingMovementMethod;
	import android.view.View;
	import android.view.Window;
	import android.widget.TextView;

	public class SonucDialog extends Dialog {
		TextView sonucText;

		public SonucDialog(Context context) {
			super(context);
			requestWindowFeature(Window.FEATURE_NO_TITLE);

			setContentView(R.layout.dialog_sonuc);
			getWindow().setBackgroundDrawable(new ColorDrawable(0));

			findViewById(R.id.kapat).setOnClickListener(new View.OnClickListener() {

				@Override
				public void onClick(View v) {
					dismiss();
				}
			});

			sonucText = (TextView) findViewById(R.id.text_sonuc);
			sonucText.setMovementMethod(new ScrollingMovementMethod());
		}

		public void setSonuc(String sonuc) {
			sonucText.setText(sonuc);
		}
	}

