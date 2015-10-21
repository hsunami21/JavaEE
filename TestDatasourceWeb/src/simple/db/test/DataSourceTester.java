package simple.db.test;

import java.sql.Connection;
import java.sql.SQLException;

import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

public class DataSourceTester {

	public DataSourceTester(String dsName) throws DataSourceException {
		getConnection(dsName);
	}

	private Connection getConnection (String dsName) throws DataSourceException {
			try {
			InitialContext ctx = new InitialContext();
			DataSource ds = (DataSource) ctx.lookup(dsName);
			return ds.getConnection();
		} catch (NamingException | SQLException e) {
			e.printStackTrace();
			throw new DataSourceException("Unable to connect to: " + dsName);
		}
	}

}
