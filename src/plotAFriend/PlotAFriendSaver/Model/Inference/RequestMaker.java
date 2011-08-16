package plotAFriend.PlotAFriendSaver.Model.Inference;

public class RequestMaker {

	public static RequestFactory getRequest(String service) {
		Agent agent = new Agent(service);
		
	
		if (agent.useLocal()) {
			return new LocalRequestFactory();
		} else {
			return new WSRequestFactory();
		}
	}

}
