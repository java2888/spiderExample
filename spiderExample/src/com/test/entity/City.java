package com.test.entity;

/*
	城市信息的实体类
*/
public class City {
	int cityId;
	String cityName;
	String head_pinyin;
	String cityPinyin;

	public City(int cityId, String cityName, String head_pinyin, String cityPinyin) {
		this.cityId = cityId;
		this.cityName = cityName;
		this.head_pinyin = head_pinyin;
		this.cityPinyin = cityPinyin;
	}

	public City(String cityId, String cityName, String head_pinyin, String cityPinyin) {
		this.cityId = Integer.parseInt(cityId) ;
		this.cityName = cityName;
		this.head_pinyin = head_pinyin;
		this.cityPinyin = cityPinyin;
	}
	public int getCityId() {
		return cityId;
	}

	public void setCityId(int cityId) {
		this.cityId = cityId;
	}

	public String getCityName() {
		return cityName;
	}

	public void setCityName(String cityName) {
		this.cityName = cityName;
	}

	public String getHead_pinyin() {
		return head_pinyin;
	}

	public void setHead_pinyin(String head_pinyin) {
		this.head_pinyin = head_pinyin;
	}

	public String getCityPinyin() {
		return cityPinyin;
	}

	public void setCityPinyin(String cityPinyin) {
		this.cityPinyin = cityPinyin;
	}

}
