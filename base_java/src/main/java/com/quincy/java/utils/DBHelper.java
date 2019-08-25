package com.quincy.java.utils;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;



public class DBHelper {
	
	private static Properties properties = new Properties();
	
	static{
		try {
			properties.load(DBHelper.class.getClassLoader().getResourceAsStream("db.properties"));
			Class.forName(properties.getProperty("driver"));
		} catch (ClassNotFoundException e) {
			//throw new JDBCRegClassDriverException("JDBC数据库驱动加载不成功！");
		} catch (IOException e) {
			//throw new InitPropertiesException("加载propertise配置文件不成功！", e);
		}
	}
	
	public static ResultSet executeQuery(String sql, Connection conn, Object...params){
		try {
			PreparedStatement ps = conn.prepareStatement(sql);
			if(params != null && params.length > 0){
				for(int i = 0; i < params.length; i ++){
					ps.setObject(i + 1, params[i]);
				}
			}
			
			return ps.executeQuery();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public static int executeUpdate(String sql, Object...params){
		Connection conn = null;
		PreparedStatement ps = null;
		try {
			conn = getConnection();
			ps = conn.prepareStatement(sql);
			if(params != null && params.length > 0){
				for(int i = 0; i < params.length; i ++){
					ps.setObject(i + 1, params[i]);
				}
			}
			return ps.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}finally{
			close(null, ps, conn);
		}
		return 0;
	}
	
	
	
	/**
	 * 获取数据库连接
	 * @return
	 * @throws SQLException
	 */
	public static Connection getConnection() throws SQLException{
		return DriverManager.getConnection(properties.getProperty("url"), properties.getProperty("name"), properties.getProperty("pwd"));
	}
	/**
	 * 释放数据库各种资源
	 * @param rs
	 * @param st
	 * @param cn
	 */
	public static void close(ResultSet rs, Statement st, Connection cn){
		try {
			if(rs != null){
				rs.close();
				rs = null;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally{
			try {
				if(st != null){
				   st.close();
				   st = null;
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}finally{
				try {
					if(cn != null){
						cn.close();
						cn = null;
					}
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
	}
}
