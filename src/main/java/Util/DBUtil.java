package Util;

import java.sql.Connection;
import java.util.Properties;
import org.apache.commons.dbcp.BasicDataSource;



/**
 * 数据库连接管理
 */

public class DBUtil {
	//数据库连接池
	private static BasicDataSource ds;
	
	
	static {
		try {
			//加载config.properties
			Properties prop=new Properties();
			/*
			 * 获得DBUtil类加载器
			 * 类加载器的getResourceAsStream方法，依据类路径查找.properties文件
			 * 然后返回一个InputStream
			 */
			prop.load(DBUtil.class.getClassLoader().getResourceAsStream("config.properties"));
			//获取信息，初始化属性
			String drivername=prop.getProperty("drivername");
			String url=prop.getProperty("url");
			String username=prop.getProperty("username");
			String password=prop.getProperty("password");
			int maxActive=Integer.parseInt(prop.getProperty("maxactive"));
			int maxWait=Integer.parseInt(prop.getProperty("maxwait"));
			
			//初始化连接池
			ds=new BasicDataSource();
			//DriverManager.getConnection()
			ds.setDriverClassName(drivername);
			ds.setUrl(url);
			ds.setUsername(username);
			ds.setPassword(password);
			
			//最大连接数
			ds.setMaxActive(maxActive);
			//最大等待时间
			ds.setMaxWait(maxWait);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	/*
	 * 数据库链接
	 */
	public static Connection getConnection() throws Exception {
		try {
			/*
			 * 连接池提供了获取连接的方法
			 * Connection getConnection()
			 * 该方法会将连接池中的空闲链接返回，
			 * 若当前连接池中没有空闲链接，那么
			 * 该方法会阻塞，阻塞时间由maxwait指定值决定
			 * 在阻塞期间若有空闲链接可用则会立刻返回该链接，
			 * 若超时后仍没有可用链接，那么该方法会抛出异常。
			 */
			return ds.getConnection();
		} catch (Exception e) {
			throw e;
		}
	}
}
