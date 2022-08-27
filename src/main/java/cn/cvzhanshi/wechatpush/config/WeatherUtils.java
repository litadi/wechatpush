package cn.cvzhanshi.wechatpush.config;

import cn.cvzhanshi.wechatpush.entity.Weather;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.web.client.RestTemplate;

import java.nio.charset.Charset;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author cVzhanshi
 * @create 2022-08-04 22:02
 */
public class WeatherUtils {
    public static void main(String[] args) {
        System.out.println(getWeather());
    }
    public static Weather getWeather(){
        RestTemplate restTemplate = new RestTemplate();
        Map<String,String> map = new HashMap<String,String>();
        map.put("district_id","450300"); // 桂林行政代码
        map.put("data_type","all");//这个是数据类型;
        String res = restTemplate.getForObject(
                "http://api.tianapi.com/tianqi/index?key=2a188098e8adcdadad4e8dec043191e4&city=桂林市",
                String.class,
                map);
        String loveLanguage = restTemplate.getForObject(
                "https://api.dzzui.com/api/qinghua?format=json",
                String.class);
        JSONObject json = JSONObject.parseObject(res);
        JSONObject loveLanguageJson = JSONObject.parseObject(loveLanguage);
        JSONArray forecasts = json.getJSONArray("newslist");
        List<Weather> weathers = forecasts.toJavaList(Weather.class);
        JSONObject now = forecasts.getJSONObject(0);
        Weather weather = weathers.get(0);
        weather.setLoveSay(loveLanguageJson.getString("text"));
        weather.setPcpn(now.getString("pcpn"));
        weather.setPop(now.getString("pop"));
        weather.setHumidity(now.getString("humidity"));
        weather.setArea(now.getString("area"));
        weather.setDate(now.getString("date"));
        weather.setWeek(now.getString("week"));
        weather.setWeather(now.getString("weather"));
        weather.setHighest(now.getString("highest"));
        weather.setSunrise(now.getString("sunrise"));
        weather.setMoonrise(now.getString("moonrise"));
        weather.setMoondown(now.getString("moondown"));
        weather.setReal(now.getString("real"));
        weather.setWindsc(now.getString("windsc"));
        weather.setWind(now.getString("wind"));
        weather.setTips(now.getString("tips"));
        return weather;
    }
}
