package com.yuer.dbutils.exception;

/**
 * 解析通用配置文件出现异常时可以抛出该异常。
 * 
 * @author <a href="mailto:joe.dengtao@gmail.com">DengTao</a>
 * @version 2.0
 * @since 2.0
 */
public class ConfigInvalidException extends Exception {

	private static final long serialVersionUID = -8544149255561306270L;

	public ConfigInvalidException() {
		super();
	}

	public ConfigInvalidException(String message, Throwable cause) {
		super(message, cause);
	}

	public ConfigInvalidException(String message) {
		super(message);
	}

	public ConfigInvalidException(Throwable cause) {
		super(cause);
	}

}
