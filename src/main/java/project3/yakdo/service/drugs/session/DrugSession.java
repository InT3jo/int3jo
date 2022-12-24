package project3.yakdo.service.drugs.session;

import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Component;

@Component
public class DrugSession {
	private  Map<String, Object> information = new HashMap<>();

	public Object getInformation(String key) {
		return information.get(key);
	}
	public void addInformation(String key, Object value) {
		information.put(key, value);
	}
	public void removeInformation(String key) {
		information.remove(key);
	}
	
}
