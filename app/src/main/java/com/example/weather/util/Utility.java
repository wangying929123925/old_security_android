package com.example.weather.util;

import android.text.TextUtils;

import com.example.weather.db.City;
import com.example.weather.db.County;
import com.example.weather.db.Province;
import com.example.weather.gson.Weather;
import com.google.gson.Gson;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class Utility {
    //解析处理服务器返回的省级数据
    public static boolean handleProvinceResponse(String response){
        if(!TextUtils.isEmpty(response)){
                try {
                    JSONArray allProvinces =new JSONArray(response);
                   for(int i=0;i<allProvinces.length();i++) {
                       JSONObject provinceObject=allProvinces.getJSONObject(i);
                       Province province=new Province();
                       province.setProvinceName(provinceObject.getString("name"));
                       province.setProvinceCode(provinceObject.getInt("id"));
                       province.save();
                   }
                   return true;
                } catch (JSONException e) {
                    e.printStackTrace();
            }
        }
        return false;
    }

    //处理市级数据
    public static boolean handleCityResponse(String response,int provinceId){
        if(!TextUtils.isEmpty(response)){
            try {
                JSONArray allCites =new JSONArray(response);
                for(int i=0;i<allCites.length();i++) {
                    JSONObject provinceObject=allCites.getJSONObject(i);
                    City city=new City();
                   city.setCityName(provinceObject.getString("name"));
                   city.setCityCode(provinceObject.getInt("id"));
                   city.setProvinceId(provinceId);
                   city.save();
                }
                return true;
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
        return false;
    }

    //县级
    public static boolean handleCountyResponse(String response,int cityId){
        if(!TextUtils.isEmpty(response)){
            try {
                JSONArray allCountries =new JSONArray(response);
                for(int i=0;i<allCountries.length();i++) {
                    JSONObject countryObject=allCountries.getJSONObject(i);
                    County country=new County();
                    country.setCountryName(countryObject.getString("name"));
                    country.setWeatherId(countryObject.getString("weather_id"));
                    country.setCityId(cityId);
                    country.save();
                }
                return true;
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
        return false;
    }

    //Json解析成Weather实体类
    public static Weather handleWeatherResponse(String response){
        try {
            JSONObject jsonObject=new JSONObject(response);
            JSONArray jsonArray=jsonObject.getJSONArray("HeWeather");
            String weatherContent=jsonArray.getJSONObject(0).toString();
            return new Gson().fromJson(weatherContent,Weather.class);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
