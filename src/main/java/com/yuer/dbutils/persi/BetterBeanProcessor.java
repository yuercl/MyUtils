package com.yuer.dbutils.persi;

import java.beans.PropertyDescriptor;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.Arrays;

import org.apache.commons.dbutils.BeanProcessor;

public class BetterBeanProcessor extends BeanProcessor {

	@Override
	protected int[] mapColumnsToProperties(ResultSetMetaData rsmd,
			PropertyDescriptor[] props) throws SQLException {
		int cols = rsmd.getColumnCount();
		int[] columnToProperty = new int[cols + 1];
		Arrays.fill(columnToProperty, PROPERTY_NOT_FOUND);
		for (int col = 1; col <= cols; col++) {
			String columnName = rsmd.getColumnLabel(col);
			if (null == columnName || 0 == columnName.length()) {
				columnName = rsmd.getColumnName(col);
			}
			for (int i = 0; i < props.length; i++) {
				if (convert(columnName).equalsIgnoreCase(props[i].getName())) {
					columnToProperty[col] = i;
					break;
				}
			}
		}
		return columnToProperty;
	}

	/** DATA_OBJECT_NAME -> dataObjectName */
	private String convert(String objName) {
		StringBuilder result = new StringBuilder();
		String[] tokens = objName.split("_");
		for (String token : tokens) {
			if (result.length() == 0)
				result.append(token);
			else
				result.append(capitalize(token));
		}
		return result.toString();
	}

	private String capitalize(String lowerCase) {
		char[] temp = lowerCase.toCharArray();
		temp[0] = Character.toUpperCase(temp[0]);
		return new String(temp);
	}
}
