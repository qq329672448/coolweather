package model;

import java.util.ArrayList;
import java.util.List;

import db.CoolWeatherOpenHelper;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

/**
 * 
 * CoolWeatherDB 是一个单例类，我们将它的构造方法私有化，并提供了一个getInstance()方法来获取CoolWeatherDB 的实例，
 * 这样就可以保证全局范围内只会有一个CoolWeatherDB 的实例。
 * 接下来我们在CoolWeatherDB 中提供了六组方法，saveProvince()、loadProvinces()、saveCity()、
 * loadCities()、saveCounty()、loadCounties()，分别用于存储省份数据、
 * 读取所有省份数据、存储城市数据、读取某省内所有城市数据、存储县数据、读取某市内所有县的数据。
 * 有了这几个方法，我们后面很多的数据库操作都将会变得非常简单。
 *
 */

public class CoolWeatherDB {
	/**
	 * 数据库名
	 */
	public static final String DB_NAME="cool_weather";
	/**
	 * 数据库版本
	 */
	public static final int VERSION =1;
	
	private static CoolWeatherDB coolWeatherDB;
	
	private SQLiteDatabase db;
	/**
	 * 构造方法私有化
	 */
	private CoolWeatherDB(Context context){
		CoolWeatherOpenHelper dbHelper =new CoolWeatherOpenHelper(context, DB_NAME, null, VERSION);
		db=dbHelper.getWritableDatabase();
	}
	/**
	 * 获取CoolWeatherDB实例
	 */
	public synchronized static CoolWeatherDB getInstance(Context context){
		if(coolWeatherDB==null){
			coolWeatherDB =new CoolWeatherDB(context);
		}
		return coolWeatherDB;
	}
	/**
	 * 将Province实例存储到数据库
	 */
	public void saveProvince(Province province){
		if(province!=null) {
			ContentValues values =new ContentValues();
			values.put("province_name",province.getProvinceName());
			values.put("province_code",province.getProvinceCode());
			db.insert("Province", null, values);
		}
	}
	/**
	 * query()方法参数                          对应SQL 部分                                                             描述
	       table             from table_name                 指定查询的表名
	      columns        select column1, column2                                   指定查询的列名
	     selection         where column = value           指定where 的约束条件
	    selectionArgs              -                 为where 中的占位符提供具体的值
	      groupBy            group by column              指定需要group by 的列
	      having           having column = value        对group by 后的结果进一步约束
	      orderBy        order by column1, column2                         指定查询结果的排序方式
	 * 
	 * 
	 * 从数据库读取全国所有的省份信息
	 */
	public List<Province> loadProvinces(){
		List<Province> list =new ArrayList<Province>();
		Cursor cursor =db.query("Province", null, null, null, null, null, null);
		if(cursor.moveToFirst()){
			do{
				Province province=new Province();
				province.setId(cursor.getInt(cursor.getColumnIndex("id")));
				province.setProvinceName(cursor.getString(cursor
						.getColumnIndex("province_name")));
				province.setProvinceCode(cursor.getString(cursor.getColumnIndex("province_code")));
				list.add(province);
			}while(cursor.moveToNext());
		}
		return list;
	}
	
	/**
	 *将City实例存储到数据库
	 */
	public void saveCity(City city){
		if(city!=null) {
			ContentValues values =new ContentValues();
			values.put("city_name",city.getCityName());
			values.put("city_code",city.getCityCode());
			values.put("province_id",city.getProvinceId());
			db.insert("City", null, values);
		}
	}
	
	/**
	 *从数据库读取某省下所有的城市信息
	 */
	public List<City> loadCities(int provinceId){
		List<City> list =new ArrayList<City>();
		Cursor cursor =db.query("City", null, "province_id=?", new String[]{String.valueOf(provinceId)}, null, null, null);
		if(cursor.moveToFirst()){
			do{
				City city=new City();
				city.setId(cursor.getInt(cursor.getColumnIndex("id")));
				city.setCityName(cursor.getString(cursor
						.getColumnIndex("city_name")));
				city.setCityCode(cursor.getString(cursor
						.getColumnIndex("city_code")));
				city.setProvinceId(provinceId);
				list.add(city);
			}while(cursor.moveToNext());
		}
		return list;
	}
	
	/**
	 *将County实例存储到数据库
	 */
	public void saveCounty(County county){
		if(county!=null) {
			ContentValues values =new ContentValues();
			values.put("county_name",county.getCountyName());
			values.put("county_code",county.getCountyCode());
			values.put("city_id",county.getCityId());
			db.insert("County", null, values);
		}
	}
	/**
	 * 从数据库读取某城市下所有的县信息
	 */
	public List<County> loadCounties(int cityId){
		List<County> list =new ArrayList<County>();
		Cursor cursor =db.query("County", null, "city_id=?", new String[]{String.valueOf(cityId)}, null, null, null);
		if(cursor.moveToFirst()){
			do{
				County county=new County();
				county.setId(cursor.getInt(cursor.getColumnIndex("id")));
				county.setCountyName(cursor.getString(cursor
						.getColumnIndex("city_name")));
				county.setCountyCode(cursor.getString(cursor
						.getColumnIndex("city_code")));
				county.setCityId(cityId);
				list.add(county);
			}while(cursor.moveToNext());
		}
		return list;
	}
}
