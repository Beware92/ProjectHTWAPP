package com.gebaeudeplan;

import com.example.htw_app.MainActivity;
import com.example.htw_app.R;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageButton;
import android.widget.TextView;

public class SearchResultDetails extends Activity {
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_searchroomdetails);
        
        Bundle detailBundle = getIntent().getExtras();
        TextView title = (TextView) findViewById(R.id.textViewPopUpTitle);
        TextView content = (TextView) findViewById(R.id.textViewPopUpContent);
        
        title.setText(detailBundle.getCharSequence("title"));
        content.setText(detailBundle.getCharSequence("content"));
        final String phone = detailBundle.getCharSequence("phone").toString();
        final String mail = detailBundle.getCharSequence("mail").toString();
        
        
        ImageButton buttonPhone = (ImageButton) this.findViewById(R.id.imageButtonPopUpPhone);
        ImageButton buttonMail = (ImageButton) this.findViewById(R.id.imageButtonPopUpMail);
    	buttonPhone.setEnabled(false);
    	buttonMail.setEnabled(false);
        
        if (phone.length() > 1) {
        	buttonPhone.setEnabled(true);
        	
        }
        if (mail.length() > 4) {
        	buttonMail.setEnabled(true);
        }
        
        buttonPhone.setOnClickListener(new OnClickListener() {
			public void onClick(View arg0) {
	            Intent phoneIntent = new Intent(Intent.ACTION_DIAL,Uri.parse("tel:"+phone));
	            startActivity(phoneIntent);
	            
	            

			}
		});
        
        buttonMail.setOnClickListener(new OnClickListener() {
			public void onClick(View arg0) {
	            Intent mailIntent = new Intent(Intent.ACTION_SEND);
	            mailIntent.putExtra(Intent.EXTRA_EMAIL, new String[] { mail });

	 
	            // need this to prompts email client only
	            mailIntent.setType("message/rfc822");
	 
	            startActivity(Intent.createChooser(mailIntent, "E-Mail Client"));
	 
			}
		});
        
        
        
    }
}
