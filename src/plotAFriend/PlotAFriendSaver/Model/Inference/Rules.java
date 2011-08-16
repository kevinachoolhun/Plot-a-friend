package plotAFriend.PlotAFriendSaver.Model.Inference;

import java.util.ArrayList;

import org.simpleframework.xml.ElementList;
import org.simpleframework.xml.Root;

@Root
public class Rules {

	@ElementList
	private ArrayList<Rule> rule;

	public void setRules(ArrayList<Rule> rule) {
		this.rule = rule;
	}

	public ArrayList<Rule> getRules() {
		return rule;
	}

	public Rule getServiceRule(String serviceName) {
		
		for (Rule rule : this.rule) {
			if (rule.getName() == serviceName) {
				return rule;
			}
		}
		
		return null;
	}
	
}
