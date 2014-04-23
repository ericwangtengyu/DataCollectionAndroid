package main;

import makeUser.MakeUser;
import postRegistration.SecondaryActivity;
import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;

public class MainActivity extends Activity
{
	public static final String BASE_URL = "http://128.255.45.52:7777/DataCollection";
	public static final String ANDROID_UPLOAD_URL = BASE_URL + "/postandroid/";
	public static final String POST_TOKEN_URL = BASE_URL + "/newtoken/";
	public static final String SURVEY_URL = BASE_URL + "/servey/"; // TODO: Fix
																	// path on
																	// server
	public static final String CREATE_USER_URL = BASE_URL + "/makeuser/";
	public static final String GET_HELP_URL = BASE_URL + "/gethelp/";
	public static final String REPORT_BULLYING_URL = BASE_URL
			+ "/reportbullying/";

	/*
	 * Gets tokens, saves phone number and starts a alarm that will run sms/mms
	 * service once a day.
	 */
	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);

		SharedPreferences sharedPref = this.getApplicationContext()
				.getSharedPreferences("mypref", 0);
		if (sharedPref.getString("phone_number", null) != null)
		{
			// Is registered
			Intent intent = new Intent(this, SecondaryActivity.class);
			startActivity(intent);
			finish();
		}
		else
		{
			// needs to register
			Intent intent = new Intent(this, MakeUser.class);
			startActivity(intent);
			finish();
		}
	}
}
