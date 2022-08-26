package cn.cvzhanshi.wechatpush.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author cVzhanshi
 * @create 2022-08-04 2215
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Weather {

    String area;
    String date;
    String week;
    String weather;
    String highest;
    String lowest;
    String sunset;
    String sunrise;
    String moonrise;
    String moondown;
    // 当前温度
    String real;
    // 风级大小
    String windsc;
    // 风向
    String wind;
    String pcpn;
    String pop;
    String humidity;


    String tips;


}
