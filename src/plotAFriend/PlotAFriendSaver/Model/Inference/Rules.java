package plotAFriend.PlotAFriendSaver.Model.Inference;

import java.util.ArrayList;

import org.simpleframework.xml.ElementList;
import org.simpleframework.xml.Root;

@Root(name="rules")
public class Rules {

	@ElementList(inline=true)
	private ArrayList<Rule> rule;

	public void setRules(ArrayList<Rule> rule) {
		this.rule = rule;
	}

	public ArrayList<Rule> getRules() {
		return rule;
	}

	public Rule getServiceRule(String serviceName) {
		
		for (Rule rule : this.rule) {
			if (rule.getService() == serviceName) {
				return rule;
			}
		}
		
		return null;
	}
	
}
