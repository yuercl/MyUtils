package com.yuer.dbutils.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.sql.DataSource;

import org.apache.commons.dbutils.BasicRowProcessor;
import org.apache.commons.dbutils.DbUtils;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.yuer.dbutils.persi.BetterBeanProcessor;
import com.yuer.dbutils.persi.DataSourceProvider;
import com.yuer.dbutils.utils.ClassUtils;

/**
 * Basic Databse Access Object
 * @author <a href="mailto:joe.dengtao@gmail.com">DengTao</a>
 * @version 1.0
 * @since 1.0
 * @param <T>
 */
abstract class BaseDao<T> {

	protected Logger LOGGER = LoggerFactory.getLogger(this.getClass());
	
	private DataSource dataSource = DataSourceProvider.getDataSource();
	@SuppressWarnings("unchecked")
	private Class<T> persistentClass = (Class<T>) ClassUtils.getGenericClass(this);
	
	/**
	 * Executes the given SELECT SQL query and returns a result list object. 
	 * @param sql sql for create PreparedStatement
	 * @param params PreparedStatement params
	 * @return find result list or null on failure.
	 */
	protected List<T> find(String sql, Object... params) {
		
		// Create a QueryRunner that will use connections from
		// the given DataSource
		QueryRunner run = new QueryRunner( dataSource );
		try {
			// Execute the query and get the results back from the handler
			List<T> result = run.query(sql, new BeanListHandler<T>(persistentClass, new BasicRowProcessor(new BetterBeanProcessor())), params);
			LOGGER.debug("[FIND  ][" + sql + "] " + result + "!");
			return result;
		} catch(SQLException e) {
			LOGGER.error("[FIND  ][" + sql + "] error occurs!", e);
		    // Handle it
			return null;
		}
	}
	
	/**
	 * Executes the given SELECT SQL query and returns a result object. 
	 * @param sql sql for create PreparedStatement
	 * @param params PreparedStatement params
	 * @return get result object or null on failure.
	 */
	protected T get(String sql, Object... params) {
		// Create a QueryRunner that will use connections from
		// the given DataSource
		QueryRunner run = new QueryRunner(dataSource);
		
		try {
			// Execute the query and get the results back from the handler
			T result = run.query(sql, new BeanHandler<T>(persistentClass, new BasicRowProcessor(new BetterBeanProcessor())), params);
			LOGGER.debug("[GET   ][" + sql + "] " + result + "!");
			return result;
		} catch (SQLException e) {
			LOGGER.error("[GET   ][" + sql + "] error occurs!", e);
			// Handle it
			return null;
		}
	}
	
	/**
	 * Executes the given UPDATE SQL statement.
	 * @param sql sql for create PreparedStatement
	 * @param params PreparedStatement params
	 * @return The number of rows updated or -1 on failure.
	 */
	protected int update(String sql, Object... params) {
		// Create a QueryRunner that will use connections from
		// the given DataSource
		QueryRunner run = new QueryRunner( dataSource );
		try {
		    // Now it's time to rise to the occation...
		    int updates = run.update( sql, params);
			LOGGER.debug("[UPDATE][" + sql + "] " + updates + " affects!");
		    // So does the line above
		    return updates;
		} catch(SQLException e) {
			LOGGER.error("[UPDATE][" + sql + "] error occurs!", e);
		    // Handle it
			return -1;
		}
	}
	
	/**
	 * Executes the given INSERT SQL statement. 
	 * @param sql sql for create PreparedStatement
	 * @param params PreparedStatement params
	 * @return trur on success or false on failure.
	 */
	protected boolean insert(String sql, Object... params) {
		// Create a QueryRunner that will use connections from
		// the given DataSource
		QueryRunner run = new QueryRunner(dataSource);
		try {
			// Execute the SQL update statement and return the number of
		    // inserts that were made
			int inserts = run.update(sql, params);
			LOGGER.debug("[INSERT][" + sql + "] " + inserts + " affects!");
			return true;
		} catch (Exception e) {
			LOGGER.error("[INSERT][" + sql + "] error occurs!", e);
			return false;
		}
	}
	
	/**
	 * Executes the given INSERT SQL statement. 
	 * @param sql sql for create PreparedStatement
	 * @param params PreparedStatement params
	 * @return generated key or -1 on failure or 0 for none keys was generated.
	 */
	protected Object save(String sql, Object... params) {
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		try {
			conn = dataSource.getConnection();
			stmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
			// No DataSource so we must handle Connections manually
			QueryRunner run = new QueryRunner();
			run.fillStatement(stmt, params);
			stmt.executeUpdate();
			rs = stmt.getGeneratedKeys();
			if (rs.next()){
				Object key = rs.getObject(1);
				LOGGER.debug("[SAVE  ][" + sql + "] " + key + " is generated!");
				return rs.getObject(1);
	        }
		    return 0;
		} catch(SQLException e) {
			LOGGER.error("[SAVE  ][" + sql + "] error occurs!", e);
			return -1L;
		} finally {
			DbUtils.closeQuietly(rs);
			DbUtils.closeQuietly(stmt);
			DbUtils.closeQuietly(conn);
		}
	}
	
	protected List<Map<String, Object>> executeQuery(String sql, Object... params) throws SQLException {
	    Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        try {
            List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
            conn = dataSource.getConnection();
            stmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            LOGGER.debug("[QUERY ][" + sql + "]");
            // No DataSource so we must handle Connections manually
            QueryRunner run = new QueryRunner();
            run.fillStatement(stmt, params);
            rs = stmt.executeQuery();
            ResultSetMetaData rsmd = rs.getMetaData();
            int count = rsmd.getColumnCount();
            while (rs.next()) {
                Map<String, Object> map = new HashMap<String, Object>();
                for (int i = 1; i <= count; i++) {
                    String columnName = rsmd.getColumnName(i);
                    map.put(columnName, rs.getObject(columnName));
                }
                list.add(map);
            }
            return list;
        } catch(SQLException e) {
            throw e;
        } finally {
            DbUtils.closeQuietly(rs);
            DbUtils.closeQuietly(stmt);
            DbUtils.closeQuietly(conn);
        }
    }
}
