package com.test.snatch;

import java.io.BufferedWriter;
import java.io.Console;
import java.io.FileWriter;
import java.io.IOException;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import com.test.database.DbOperation;
import com.test.entity.City;

public class CityDataGet {
/*
   初始化table
	*/
	private void init_Table() {
		String strSqlDropTable = "DROP TABLE IF EXISTS tbl_city";
		DbOperation.executeSql(strSqlDropTable);

		String strSqlCreateTable = "create table if not exists tbl_city (id integer primary key auto_increment, city_id integer not null, city_name varchar(255) not null, head_pinyin varchar(80) not null, pinyin varchar(255) not null)";
		DbOperation.executeSql(strSqlCreateTable);

	}
	
/*
	把 城市信息CityData 添加到数据库中
	*/
	private void AddCityDataToDataBase(City city) {
		StringBuffer insert_sql = new StringBuffer();
		insert_sql.append("INSERT INTO tbl_city(city_id,city_name,	head_pinyin, pinyin) VALUES(");
		insert_sql.append(city.getCityId() + ",'");
		insert_sql.append(city.getCityName() + "','");
		insert_sql.append(city.getHead_pinyin() + "','");
		insert_sql.append(city.getCityPinyin() + "');");
		// System.out.println(insert_sql);
		DbOperation.executeSql(insert_sql.toString());
	}
	
/*
 *  urlTarget是要抓取的城市信息所在的网页
	urlTarget = "http://hotels.ctrip.com/domestic-city-hotel.html";
	利用第三方开源的java html解析器库jsoup(https://jsoup.org/)来解析目标数据
	*/
	public void getData() {
		Document doc = null;

		init_Table();

		try {
			String urlTarget = "http://hotels.ctrip.com/domestic-city-hotel.html";
			doc = Jsoup.connect(urlTarget).get();

			// System.out.println(doc);
			/*
			 * BufferedWriter out=new BufferedWriter(new
			 * FileWriter("D:\\ctrip.html")); out.write(doc.toString());
			 * out.flush(); out.close();
			 */

			Elements targetAll_CityData = doc.getElementsByClass("pinyin_filter_detail layoutfix");
			Element targetCityData = targetAll_CityData.first();
			Elements all_Pinyin = targetCityData.getElementsByTag("dt");
			Elements all_Tag_dd_element = targetCityData.getElementsByTag("dd");
			for (int i = 0; i < all_Pinyin.size(); i++) {
				// for (int i = 0; i < 1; i++) {
				Element pinyin = all_Pinyin.get(i);
				String head_pinyin = pinyin.text();
				System.out.println(pinyin.toString());
				Element Tag_dd_element = all_Tag_dd_element.get(i);
				Elements pinyin_Links = Tag_dd_element.getElementsByTag("a");
				for (Element link : pinyin_Links) {
					// System.out.println(link.toString());
					String cityName = link.text();
					String href = link.attr("href");
					// String prefix = "/hotel/";
					// String cityPinyin_Id=href.substring(prefix.length());
					String cityPinyin_Id = href.replaceAll("[/hotel/]", "");
					String cityId = cityPinyin_Id.replaceAll("[^0-9]", "");
					String cityPinyin = cityPinyin_Id.replaceAll("[^a-zA-Z]", "");
					// System.out.println(cityName + "; " + cityPinyin+ "; " +
					// cityId);
					City city = new City(cityId, cityName, head_pinyin, cityPinyin);
					AddCityDataToDataBase(city);

				}
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		 
	}
}
