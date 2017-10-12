package andresdlrg.activemq.stresser.service.impl;

import java.util.Map;

import andresdlrg.activemq.stresser.service.ExtraParamService;

public class MapExtraParamServiceImpl<K, V> implements ExtraParamService{

	private Map<K,V> value;

	public MapExtraParamServiceImpl(Map<K,V> value) {
		this.value = value;
	}
	
	@Override
	public Object getValue() {
		return value;
	}

}
