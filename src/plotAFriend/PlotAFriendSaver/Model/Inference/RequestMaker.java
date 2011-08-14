package plotAFriend.PlotAFriendSaver.Model.Inference;

public class RequestMaker {

	public static Request getRequest() {
		Agent agent = new Agent();
		
	
		if (agent.useLocal()) {
			return new LocalRequest();
		} else {
			return new WSRequest();
		}
	}

}
