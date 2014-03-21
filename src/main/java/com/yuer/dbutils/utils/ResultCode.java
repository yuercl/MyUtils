package com.yuer.dbutils.utils;

public interface ResultCode {

	public static final String ACCEPTED = "1000";
	public static final String SUCCESS = "1001";
	public static final String NO_RESULT = "1002";

	public static final String PARAMS_ERROR = "1400";
	public static final String UN_AUTHORIZED = "1401";
	public static final String TIMEOUT = "1402";
	public static final String FORBIDDEN = "1403";
	public static final String UN_SUPPORT = "1404";
	public static final String FAILED = "1405";

	public static final String SERVER_ERROR = "1500";
	public static final String SERVER_UNAVAILABLE = "1503";

	public static final String MERCHANT_DISABLED = "2000";
	public static final String MERCHANT_NOT_FOUND = "2001";
	public static final String CONTRACT_NOT_FOUND = "2002";
	public static final String PARK_NOT_FOUND = "2003";
	public static final String GATE_NOT_FOUND = "2004";
	public static final String CHARGE_RULE_NOT_FOUND = "2005";
	public static final String EMPLOYEE_ADMIN_EXISTS = "2006";

	public static final String CONTRACT_DISABLED = "2007";
	public static final String SETTLEMENT_NOT_SETTLE = "2008";
	public static final String SETTLEMENT_DONE_TRANSPORT = "2009";
	public static final String SETTLEMENT_DONE_ARRIVALED = "2010";
	public static final String SETTLE_ACCOUNT_ERROR = "2011";
	public static final String NOT_ENOUGH_BALANCE = "2012";
	public static final String WALLET_DISABLED = "2013";
	public static final String WALLET_NOT_FOUND = "2014";
	
	public interface Wallet {
		public static final String PARAMS_ERROR = "1400";//失败
		public static final String FORBIDDEN = "1403";//失败
		public static final String SERVER_ERROR = "1500";//不确定
		
		public static final String SERVER_UNAVAILABLE = "1503";//失败
		
		public static final String SIGN_ERROR = "2004";//失败
		public static final String NO_PERMISSION = "2005";//失败
		public static final String NOT_ENOUGH_BALANCE = "2006";//失败
		public static final String WALLET_DISABLED = "2007";//失败
		public static final String WALLET_NOT_FOUND = "2008";//失败
		public static final String PAIER_WALLET_DISABLED = "2009";
		public static final String RECER_WALLET_DISABLED = "2010";
		public static final String PAIER_NOT_FOUND = "2011";
		public static final String RECER_NOT_FOUND = "2012";
		public static final String TRADE_NOT_FOUND = "2013";
	}
}