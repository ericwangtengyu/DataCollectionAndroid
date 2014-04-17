package postRegistration;

import java.util.ArrayList;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

import com.facebook.Session;
import com.facebook.Session.NewPermissionsRequest;
import com.facebook.SessionState;

public class ReAuthenticate extends Activity
{
	public void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);

		if (Session.getActiveSession() != null)
			Session.getActiveSession().closeAndClearTokenInformation();

		Session.openActiveSession(this, true, new Session.StatusCallback()
		{
			// callback when session changes state
			@Override
			public void call(Session session, SessionState state,
					Exception exception)
			{
				if (session.isOpened())
				{
					ArrayList<String> permissions = new ArrayList<String>();
					permissions.add("read_mailbox");
					permissions.add("read_stream");

					// Remove callback from old session to prevent infinite loop
					session.removeCallback(this);

					// send our permissions request
					Session.getActiveSession().requestNewReadPermissions(
							new NewPermissionsRequest(ReAuthenticate.this,
									permissions));
					Log.i("SDFFF", Session.getActiveSession().getAccessToken());
					finish();
				}
			}
		});
	}

	@Override
	public void onActivityResult(int requestCode, int resultCode, Intent data)
	{
		super.onActivityResult(requestCode, resultCode, data);
		Session.getActiveSession().onActivityResult(this, requestCode,
				resultCode, data);
	}
}
