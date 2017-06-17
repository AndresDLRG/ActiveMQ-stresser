package andresdlrg.activemq.stresser.service.impl;

import java.util.Random;

import andresdlrg.activemq.stresser.service.ExtraParamService;

public class RandomNumberExtraParamServiceImpl implements ExtraParamService{
	
	private boolean useFloats;
	private long minLong;
	private long maxLong;
	
	private double minDouble;
	private double maxDouble;
	private Random r;
	
	public RandomNumberExtraParamServiceImpl(Number min, Number max ,boolean useFloats) {
		this.useFloats = useFloats;
		if(useFloats) {
			minDouble = (double)min;
			maxDouble = (double)max;
		} else {
			minLong = (long)min;
			maxLong = (long)max;
		}
		r = new Random();
	}

	@Override
	public Object getValue() {
		if (useFloats) {
			return r.nextDouble()*(maxDouble - minDouble) + minDouble;
		} else {
			return (int) (Math.random() * (maxLong - minLong)) + minLong;
		}
	}

}