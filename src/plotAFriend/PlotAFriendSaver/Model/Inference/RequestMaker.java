package plotAFriend.PlotAFriendSaver.Model.Inference;

public class RequestMaker {

	public static RequestFactory getRequest() {
		Agent agent = new Agent();
		
	
		if (agent.useLocal()) {
			return new LocalRequest();
		} else {
			return new WSRequest();
		}
	}

}
