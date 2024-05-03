package banspec.config.models;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Config {

	private Map<String,List<String>> banGroups = new HashMap<>();
	
	public Config() {
		banGroups.put("pika", Arrays.asList("pikachu","raichu"));
	}

	public Map<String, List<String>> getBanGroups() {
		return banGroups;
	}

}
