package com.yuer.dbutils.utils;

import java.util.HashMap;
import java.util.Map;

public class Constants {

	public static String USER_SERVICE_NAME;
	public static String WALLET_SERVICE_NAME;
	public static String WALLET_SERVICE_MAC;
	public static long WITHDRAW_SERVICE_TIMEOUT;
	public static Map<String, String> BANK_NAME = new HashMap<String,String>();

	// Database
	public static final String TABLE_NAME_MERCHANT = "_merchant";
	public static final String TABLE_NAME_PARK = "_park";
	public static final String TABLE_NAME_BOOTH = "_booth";
	public static final String TABLE_NAME_FEEDBACK = "_feedback";
	public static final String TABLE_NAME_SPECIAL_DAY = "_special_day";
	public static final String TABLE_NAME_CONTRACT = "_contract";
	public static final String TABLE_NAME_GATE = "_gate";
	public static final String TABLE_NAME_PARK_CHARGE = "_park_charge";
	public static final String TABLE_NAME_EMPLOYEE = "_employee";
	public static final String TABLE_NAME_SETTLEMENT = "_settlement";

	/*
    public static final String TABLE_NAME_SETTLEMENT_DETAIL = "_settlement_detail";
    public static final String TABLE_NAME_PARK_CHARGE = "_park_charge";
    public static final String TABLE_NAME_PARK_USER = "_park_user";
    public static final String TABLE_NAME_PARK_FAVORABLE = "_park_favorable";
    public static final String TABLE_NAME_PARK_FAVORABLE_OFFER = "_favorable_offer";
    public static final String TABLE_NAME_PARK_FAVORABLE_COUPON = "_favorable_coupon";*/

}