package yj.dzc.love.autoTest;

import java.util.HashMap;

import org.json.JSONObject;

import com.baidu.aip.imageclassify.AipImageClassify;

public class BaiduTest {
	public static final String APP_ID = "10650265";
    public static final String API_KEY = "GOuK9z8clU9zKZGFsWs8vIOU";
    public static final String SECRET_KEY = "SObhFGbj34dHNYBxRT21OAnV5BMBlHxc";
	public static void main(String[] args) {
		// 初始化一个AipImageClassifyClient
        AipImageClassify client = new AipImageClassify(APP_ID, API_KEY, SECRET_KEY);

        // 可选：设置网络连接参数
        client.setConnectionTimeoutInMillis(2000);
        client.setSocketTimeoutInMillis(60000);

        // 调用接口
        String path = "000a921c5d426efc52b8b9e5be81d5e6.jpg";
        JSONObject res = client.objectDetect(path, new HashMap<String, String>());
        System.out.println(res.toString(2));
	}
}
