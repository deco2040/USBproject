package jptflog.model.week;

import java.sql.Connection;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;


public class WeekDAO {
	private WeekDAO() {}

	private static WeekDAO instance = new WeekDAO();

	public static WeekDAO getInstance() {
		return instance;
	}

	// 커넥션 풀 설정
	private static Connection getConnection() throws Exception {
		Context initContext = new InitialContext();
		Context envContext = (Context) initContext.lookup("java:/comp/env");
		DataSource ds = (DataSource) envContext.lookup("jdbc/myoracle");
		Connection conn = ds.getConnection();
		return conn;
	}
}
