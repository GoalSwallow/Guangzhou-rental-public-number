package com.swallow.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.dom4j.DocumentException;

import com.swallow.util.CheckUtil;
import com.swallow.util.MessageUtil;


@WebServlet("/check")
@SuppressWarnings("all")
public class WeChatServlet extends HttpServlet{
	 /**
	  * 验证微信开发服务器资源所用
	  */
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String signature = req.getParameter("signature");
		String timestamp = req.getParameter("timestamp");
		String nonce = req.getParameter("nonce");
		String echostr = req.getParameter("echostr");
		
		System.err.println("signature:" + signature);
		System.err.println("timestamp:" + timestamp);
		System.err.println("nonce:" + nonce);
		System.err.println("echostr:" + echostr);
		
		PrintWriter writer = resp.getWriter();
		if(CheckUtil.checkSignature(signature, timestamp, nonce)) {
			writer.print(echostr);
		}
	}
	
	/**
	 * 获取消息
	 */
	@Override
	protected void doPost(HttpServletRequest  req, HttpServletResponse resp) throws ServletException, IOException {
		resp.setCharacterEncoding("UTF-8");
		req.setCharacterEncoding("UTF-8");
		PrintWriter out = resp.getWriter();
		try {
			Map<String, String> map = MessageUtil.xmlToMap(req);
			String fromUserName = map.get("FromUserName");
			String toUserName = map.get("ToUserName");
			String msgType = map.get("MsgType");

			String message = null;
			if(MessageUtil.MESSAGE_TEXT.equals(msgType)) {
				String content = map.get("Content");
				if(content.contains("租房") || content.contains("求租") || content.contains("哪有房") || content.contains("住宿")) {
					message = MessageUtil.initText(toUserName, fromUserName, MessageUtil.rentMenu());
				}else if(content.contains("房源") || content.contains("出租") || content.contains("租客")) {
					message = MessageUtil.initText(toUserName, fromUserName, MessageUtil.listingMenu());
				}else{
					//无法识别用户请求
					message = MessageUtil.initText(toUserName, fromUserName, MessageUtil.notRecognized());
				}
			}else if(MessageUtil.MESSAGE_EVNET.equals(msgType)){
				String eventType = map.get("Event");
				if(MessageUtil.MESSAGE_SUBSCRIBE.equals(eventType)){
					message = MessageUtil.initText(toUserName, fromUserName, MessageUtil.menuText(""));
				}else if(MessageUtil.MESSAGE_CLICK.equals(eventType)){
					String eventKey = map.get("EventKey");
					message = MessageUtil.initText(toUserName, fromUserName, MessageUtil.menuText("help"));
				}else if(MessageUtil.MESSAGE_VIEW.equals(eventType)){
					String url = map.get("EventKey");
					message = MessageUtil.initText(toUserName, fromUserName, url);
				}else if(MessageUtil.MESSAGE_SCANCODE.equals(eventType)){
					String key = map.get("EventKey");
					message = MessageUtil.initText(toUserName, fromUserName, key);
				}
			}else if(MessageUtil.MESSAGE_LOCATION.equals(msgType)){
				String label = map.get("Label");
				message = MessageUtil.initText(toUserName, fromUserName, label);
			}else if(MessageUtil.MESSAGE_IMAGE.equals(msgType)) {
				String mediaId = map.get("MediaId");
				message = MessageUtil.initImageMessage(toUserName, fromUserName, mediaId);
			}
			System.out.println(message);
			out.print(message);
		} catch (DocumentException e) {
			e.printStackTrace();
		}finally {
			out.close();
		}
	}
}
