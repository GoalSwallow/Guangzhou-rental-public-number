package graduation;

import com.swallow.bean.menuBean.AccessToken;
import com.swallow.util.WeChatUtil;

import net.sf.json.JSONObject;

public class WeixinTest extends Thread {
	public static void main(String[] args) {
		try {

			AccessToken token = WeChatUtil.getAccessToken();
			System.out.println("票据" + token.getAccess_token());
			System.out.println("有效时间" + token.getExpires_in());
			String menu = JSONObject.fromObject(WeChatUtil.initMenu()).toString();
			int result = WeChatUtil.createMenu(menu, token.getAccess_token());
			if (result == 0) {
				System.err.println("创建菜单成功！");
			} else {
				System.err.println("错误码：" + result);

			}

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
