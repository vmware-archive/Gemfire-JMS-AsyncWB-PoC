package io.pivotal.gemfire.core;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;
import java.util.Properties;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementSetter;

import com.gemstone.gemfire.cache.Declarable;
import com.gemstone.gemfire.cache.asyncqueue.AsyncEvent;
import com.gemstone.gemfire.cache.asyncqueue.AsyncEventListener;
import com.gemstone.gemfire.pdx.JSONFormatter;
import com.gemstone.gemfire.pdx.PdxInstance;

public class WriteBackAsyncEventListener implements AsyncEventListener, Declarable {

	protected final Logger logger = Logger.getLogger(getClass().getName());

	@Resource(name="jdbcTemplate")
	JdbcTemplate jdbcTemplate;

	public void close() {
        // TODO Auto-generated method stub

    }

    public void init(Properties arg0) {
        // TODO Auto-generated method stub

    }

    private void updateDB(PdxInstance pdxInstance) {

    	String sqlInsert = "INSERT INTO trade (id, symbol, quantity, amount) " +
    						"VALUES (?, ?, ?, ?)";

    	String id = (String) pdxInstance.getField("id");
    	String symbol = (String) pdxInstance.getField("sym");

    	String tempValue = (String) pdxInstance.getField("qnt");
    	Integer quantity = Integer.parseInt(tempValue);

    	tempValue = (String) pdxInstance.getField("amt");
    	Double amount = Double.parseDouble(tempValue);

   // 	jdbcTemplate.update(sqlInsert, id, symbol, quantity, amount);

    	jdbcTemplate.update(sqlInsert, new PreparedStatementSetter() {
    	      public void setValues(PreparedStatement ps) throws SQLException {
    	    	  ps.setString(1, id);
    	    	  ps.setString(2, symbol);
    	    	  ps.setInt(3, quantity);
    	    	  ps.setDouble(4, amount);
    	      }
    	});

    }

    @SuppressWarnings("rawtypes")
	public boolean processEvents(List<AsyncEvent> entries) {

        logger.info(String.format("TestAsyncEventListener : Size of List<AsyncEvent> = %s", entries.size()));

        // process the events here, could write to a database etc
        for (AsyncEvent ge: entries) {

             PdxInstance pdxInstance = (PdxInstance) ge.getDeserializedValue();
             String jsonData = JSONFormatter.toJSON(pdxInstance);

             logger.info("Received JSON data on async queue: " + jsonData);
             updateDB(pdxInstance);
        }

        return true;
    }

}
