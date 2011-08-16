package plotAFriend.PlotAFriendSaver.Model.Inference;

import java.io.File;

import org.simpleframework.xml.Serializer;
import org.simpleframework.xml.core.Persister;

import android.R;
import android.app.Activity;
import android.os.Bundle;

public class Agent extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
	}

	public Agent(String serviceName) {
		Service service = null;

		File xmlFile = new File("file:///service.xml");

		try {

			Serializer serializer = new Persister();

			service = serializer.read(Service.class, xmlFile, false);

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public Boolean useLocal() {
		// depending on the context of the device

		// get xml for this service

		// compare context with xml

		// cast xml to service of type weather
		Service service = new Service();

		// service.getBattery() > device.getbattery();

		return false;
	}
}
