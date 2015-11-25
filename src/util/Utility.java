package util;

import android.text.TextUtils;
import model.City;
import model.CoolWeatherDB;
import model.County;
import model.Province;

/**
 * 
 * ���ڷ��������ص�ʡ�������ݶ��ǡ�����|����,����|���С����ָ�ʽ
 * ��������������ṩһ���������������ʹ�����������
 * 
 *�����ṩ��handleProvincesResponse() ��handleCitiesResponse() ��handleCountiesResponse()������������
 *�ֱ����ڽ����ʹ�����������ص�ʡ�����м����ؼ����ݡ�
 *�����Ĺ�������Ȱ����ŷָ����ٰ������߷ָ������Ž������������������õ�ʵ�����У�
 *������CoolWeatherDB �е�����save()���������ݴ洢����Ӧ�ı��С�
 *
 *
 */ 

public class Utility {
	/**
	 * �����ʹ�����������ص�ʡ������
	 */
	public synchronized static boolean handleProvincesResponse(CoolWeatherDB coolWeatherDB,String response){
		if(!TextUtils.isEmpty(response)){
			String[] allProvinces =response.split(",");
			if(allProvinces!=null&&allProvinces.length>0){
				for(String p:allProvinces){
					String [] array =p.split("\\|");
					Province province =new Province();
					province.setProvinceCode(array[0]);
					province.setProvinceName(array[1]);
					//���������������ݴ洢��Province����
					coolWeatherDB.saveProvince(province);
				}
				return true;			
			}
		}	
		return false;
	}
	
	/**
	 * �����ʹ�����������ص��м�����
	 */
	public synchronized static boolean handleCitiesResponse(CoolWeatherDB coolWeatherDB,String response,int provinceId){
		if(!TextUtils.isEmpty(response)){
			String[] allCities =response.split(",");
			if(allCities!=null&&allCities.length>0){
				for(String p:allCities){
					String [] array =p.split("\\|");
					City city =new City();
					city.setCityCode(array[0]);
					city.setCityName(array[1]);
					city.setProvinceId(provinceId);
					//���������������ݴ洢��Province����
					coolWeatherDB.saveCity(city);
				}
				return true;			
			}
		}	
		return false;
	}
	/**
	* �����ʹ�����������ص��ؼ�����
	*/
	public static boolean handleCountiesResponse(CoolWeatherDB coolWeatherDB,String response, int cityId) {
		if (!TextUtils.isEmpty(response)) {
			String[] allCounties = response.split(",");
			if (allCounties != null && allCounties.length > 0) {
				for (String c : allCounties) {
					String[] array = c.split("\\|");
					County county = new County();
					county.setCountyCode(array[0]);
					county.setCountyName(array[1]);
					county.setCityId(cityId);
					// ���������������ݴ洢��County��
					coolWeatherDB.saveCounty(county);
				}
				return true;
			}
		}
		return false;
	}
	
}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	

