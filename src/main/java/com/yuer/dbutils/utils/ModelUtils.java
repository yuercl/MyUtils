package com.yuer.dbutils.utils;


public class ModelUtils {

    public static String trimRightZero(String num) {
        if (StringUtils.isBlank(num)) return num;
        int l = 0;
        for (int i = num.length() - 1; i >= 0; i--) {
            if (num.charAt(i) == '0') l ++;
            else break;
        }
        return num.substring(0, num.length() - l);
    }
    
    /*public static String checkParams(Contract contract) {
        if (StringUtils.isBlank(contract.getEffectiveTime())) return "contract.effective_time";
        if (StringUtils.isBlank(contract.getExpiresTime())) return "contract.expires_time";
        //if (StringUtils.isBlank(contract.getPartyA())) return "contract.party_a";
        //if (StringUtils.isBlank(contract.getPartyB())) return "contract.party_b";
        return null;
    }
    */
    /*public static String checkParams(Park park) {
        if (StringUtils.isBlank(park.getName())) return "park.name";
        if (StringUtils.isBlank(park.getAddress())) return "park.address";
        if (StringUtils.isBlank(park.getArea())) return "park.area";
        if (!GeoUtils.isLatitude(park.getLatitude())) return "park.latitude";
        if (!GeoUtils.isLongitude(park.getLongitude())) return "park.longitude";
        return null;
    }

    public static String checkParams(Settlement settlement) {
        if (settlement.getType() != Settlement.Type.DAY && settlement.getType() != Settlement.Type.VALUE) return "settlement.type";
        if (settlement.getRates() < 0 || settlement.getRates() > 1000) return "settlement.retes";
        if (settlement.getType() == Settlement.Type.DAY) {
            if (settlement.getSettleDay() < 0) return "settlement.day";
        } else {
            if (settlement.getSettleValue() < 0) return "settlement.value";
        }
        return null;
    }*/
    
}