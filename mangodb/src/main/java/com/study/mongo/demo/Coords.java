/**
 * @Title:  Coords.java
 * @Copyright (C) 2014-2015 by ywx.co.,ltd.All Rights Reserved.
 *  YWX CONFIDENTIAL AND TRADE SECRET
 * @author:  
 * @data:    
 */
package com.study.mongo.demo;

import java.math.BigDecimal;

/**
 * 经纬度包装类
 * @author fangyi
 */
public class Coords {
	//经度
	private BigDecimal longitude;
	//纬度
	private BigDecimal latitude;
	
	public BigDecimal getLongitude() {
		return longitude;
	}
	public void setLongitude(String longitude) {
		setLongitude(new BigDecimal(longitude)) ;
	}
	public void setLongitude(Double longitude) {
		setLongitude(new BigDecimal(longitude)) ;
	}
	
	public void setLongitude(BigDecimal longitude) {
		if(longitude != null){
			this.longitude = longitude.setScale(6, BigDecimal.ROUND_HALF_EVEN);
		}else{
			this.longitude = null;
		}
	}
	public BigDecimal getLatitude() {
		return latitude;
	}
	public void setLatitude(String latitude) {
		setLatitude(new BigDecimal(latitude)) ;
	}
	public void setLatitude(Double latitude) {
		setLatitude(new BigDecimal(latitude)) ;
	}
	public void setLatitude(BigDecimal latitude) {
		if(latitude != null){
			this.latitude = latitude.setScale(6, BigDecimal.ROUND_HALF_EVEN);
		}
		
	}
	@Override
	public String toString() {
		return "Coords [longitude=" + longitude + ", latitude=" + latitude
				+ "]";
	}
}
