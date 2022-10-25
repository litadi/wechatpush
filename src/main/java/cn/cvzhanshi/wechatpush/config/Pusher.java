package cn.cvzhanshi.wechatpush.config;


import cn.cvzhanshi.wechatpush.entity.Weather;
import me.chanjar.weixin.mp.api.WxMpInMemoryConfigStorage;
import me.chanjar.weixin.mp.api.WxMpService;
import me.chanjar.weixin.mp.api.impl.WxMpServiceImpl;
import me.chanjar.weixin.mp.bean.template.WxMpTemplateData;
import me.chanjar.weixin.mp.bean.template.WxMpTemplateMessage;

import java.util.Map;

/**
 * @author cVzhanshi
 * @create 2022-08-04 21:09
 */
public class Pusher {

    public static void main(String[] args) {
        push();
    }
    private static String appId = "wx378f16155d3bb447";
    private static String secret = "a7e757e4752cc536babe56659f0afeaf";



    public static void push(){
        //1，配置
        WxMpInMemoryConfigStorage wxStorage = new WxMpInMemoryConfigStorage();
        wxStorage.setAppId("wx378f16155d3bb447");
        wxStorage.setSecret("a7e757e4752cc536babe56659f0afeaf");
        WxMpService wxMpService = new WxMpServiceImpl();
        wxMpService.setWxMpConfigStorage(wxStorage);
        //2,推送消息
        WxMpTemplateMessage templateMessage = WxMpTemplateMessage.builder()
                .toUser("oLlE563Pks2Dn4R_FyV8tnMNF_ag")
                .templateId("RZLnMykZtjnWgpTcDnp6pF9QtgBxJBHZmEsSsRdmNHk")
                .build();
        //3,如果是正式版发送模版消息，这里需要配置你的信息
        Weather weather = WeatherUtils.getWeather();


        templateMessage.addData(new WxMpTemplateData("area",weather.getArea(),"#42B857"));
        templateMessage.addData(new WxMpTemplateData("riqi",weather.getDate() + "  "+ weather.getWeek(),"#00BFFF"));
        templateMessage.addData(new WxMpTemplateData("tianqi",weather.getWeather(),"#00FFFF"));
        templateMessage.addData(new WxMpTemplateData("low",weather.getLowest() + "","#173177"));
        templateMessage.addData(new WxMpTemplateData("temp",weather.getReal() + "","#EE212D"));
        templateMessage.addData(new WxMpTemplateData("high",weather.getHighest()+ "","#FF6347" ));
        templateMessage.addData(new WxMpTemplateData("windclass",weather.getWindsc()+ "","#42B857" ));
        templateMessage.addData(new WxMpTemplateData("winddir",weather.getWind()+ "","#B95EA3" ));
        templateMessage.addData(new WxMpTemplateData("richu",weather.getSunrise() ,"#00BFFF"));
        templateMessage.addData(new WxMpTemplateData("riluo",weather.getSunset(),"#00FFFF"));
        templateMessage.addData(new WxMpTemplateData("tip",weather.getTips(),"#173177"));
        templateMessage.addData(new WxMpTemplateData("pop",weather.getPop(),"#EE212D"));
        templateMessage.addData(new WxMpTemplateData("pcpn",weather.getPcpn(),"#FF6347"));
        templateMessage.addData(new WxMpTemplateData("humidity",weather.getHumidity(),"#B95EA3"));

        templateMessage.addData(new WxMpTemplateData("lianai",JiNianRiUtils.getLianAi()+"","#FF1493"));
        templateMessage.addData(new WxMpTemplateData("shengri1",JiNianRiUtils.getBirthday_Hui()+"","#FFA500"));
        templateMessage.addData(new WxMpTemplateData("loveSay",weather.getLoveSay() ,"#f58f98"));

        String beizhu = "倩倩❤奥迪";
        if(JiNianRiUtils.getLianAi() % 365 == 0){
            beizhu = "今天是恋爱" + (JiNianRiUtils.getLianAi() / 365) + "周年纪念日！";
        }
        if(JiNianRiUtils.getLianAi() == 500){
            beizhu =  "今天是恋爱500天纪念日喔，不过这段话是我在10月25号写的呢，那咱就先说一下今天发生过的事情吧，" +
                    "今天老公查了六级成绩，好低啊，呜呜呜，不知道500天的时候下个六级成绩出来了没有，希望老婆保佑我，今天老婆大姨妈来了，很难受，我又不会哄人，老婆还生我气了" +
                    "不过还我 我哄回来了，现在正在趴着睡觉呢，嘻嘻；亲爱的老婆，不知不觉可就500天了，我得给老婆买个蛋糕" +
                    "仔细想来这应该是第二次给老婆买蛋糕了，上一次老婆还记得嘛，傻瓜，我就知道你忘了，你自己过生日那时候，时间好快啊，这么快就到500天了，我在提问一次老婆，咱俩闹得最严重的一次是啥时候，记得吗，" +
                    "老公在洛阳上班，然后老婆大早上就做高铁来找我了，老婆一直哄我，虽然老公很犟但是老公心理很舒服呢；陪伴老婆走过的500天很幸福很开心" +
                    "老公还想再来100个500天 好么，老公很是期待呢，咱俩要共同加油哦!!!";
        }
        if(JiNianRiUtils.getBirthday_Jo()  == 0){
            beizhu = "今天是臭宝贝生日，生日快乐呀！";
        }
        if(JiNianRiUtils.getBirthday_Hui()  == 0){
            beizhu = "今天是臭宝贝生日，生日快乐呀！";
        }
        templateMessage.addData(new WxMpTemplateData("beizhu",beizhu,"#FF0000"));

        try {
            System.out.println(wxMpService.getTemplateMsgService().sendTemplateMsg(templateMessage));
        } catch (Exception e) {
            System.out.println("推送失败：" + e.getMessage());
            e.printStackTrace();
        }
    }
}
