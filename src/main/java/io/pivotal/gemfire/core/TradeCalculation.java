package io.pivotal.gemfire.core;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Component;

import com.gemstone.gemfire.cache.Cache;
import com.gemstone.gemfire.cache.Region;
import com.gemstone.gemfire.pdx.JSONFormatter;
import com.gemstone.gemfire.pdx.PdxInstance;

@Component(value="tradeCalculation")
public class TradeCalculation {

	public static final String FUNCTION_ID = "TradeCalculation";
	protected final Logger log = Logger.getLogger(getClass().getName());

	@Resource(name="gemfireCache")
	private Cache cache;

	public boolean calculate(String tradeData) {

		Region<String, PdxInstance> tradeRegion = cache.getRegion("trade");
		PdxInstance tradeDataPdx = JSONFormatter.fromJSON(tradeData);
		tradeRegion.put((String)tradeDataPdx.getField("id"), tradeDataPdx);

		return true;
	}


}
