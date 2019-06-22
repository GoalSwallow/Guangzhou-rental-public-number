package com.swallow.util;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

import com.swallow.bean.menuBean.Image;
import com.swallow.bean.menuBean.ImageMessage;
import com.swallow.bean.menuBean.Music;
import com.swallow.bean.menuBean.MusicMessage;
import com.swallow.bean.menuBean.News;
import com.swallow.bean.menuBean.NewsMessage;
import com.swallow.bean.menuBean.TextMessage;
import com.thoughtworks.xstream.XStream;

public class MessageUtil {
	public static final String MESSAGE_TEXT = "text";
	public static final String MESSAGE_IMAGE = "image";
	public static final String MESSAGE_VIOCE = "vioce";
	public static final String MESSAGE_VIDEO = "video";
	public static final String MESSAGE_MUSIC = "music";
	public static final String MESSAGE_NEWS = "news";
	public static final String MESSAGE_LINK = "link";
	public static final String MESSAGE_LOCATION = "location";
	public static final String MESSAGE_EVNET = "event";
	public static final String MESSAGE_SUBSCRIBE = "subscribe";
	public static final String MESSAGE_UNSUBSCRIBE = "unsubscribe";
	public static final String MESSAGE_CLICK = "CLICK";
	public static final String MESSAGE_VIEW = "VIEW";
	public static final String MESSAGE_SCANCODE = "scancode_push";

	public static Map<String, String> xmlToMap(HttpServletRequest request) throws IOException, DocumentException {
		Map<String, String> map = new HashMap<String, String>();
		// 从request中取得输入流
		InputStream inputStream = request.getInputStream();
		// 读取输入流     
		SAXReader reader = new SAXReader();
		// 生成document实体
		Document document = reader.read(inputStream);
		// 得到xml根元素       
		Element root = document.getRootElement();
		// 得到根元素的所有子节点
		List<Element> elementList = root.elements();
		// 遍历所有子节点
		for (Element e : elementList) {
			map.put(e.getName(), e.getText());
			System.out.println(e.getName() + ":" + e.getText());
		}
		// 释放资源
		inputStream.close();
		inputStream = null;
		return map;
	}

	/**
	 * 将类对象转化为XMl格式
	 * 
	 * @param message
	 * @return
	 */
	public static String textMessageToXml(Object message) {
		XStream xstream = new XStream();
		xstream.alias("xml", message.getClass());
		return xstream.toXML(message);
	}

	/**
	 * 封装文本信息
	 * 
	 * @param toUserName
	 * @param fromUserName
	 * @param content
	 * @return
	 */
	public static String initText(String toUserName, String fromUserName, String content) {
		TextMessage text = new TextMessage();
		text.setFromUserName(toUserName);
		text.setToUserName(fromUserName);
		text.setMsgType(MessageUtil.MESSAGE_TEXT);
		text.setCreateTime(new Date().getTime());
		text.setContent(content);
		return textMessageToXml(text);
	}

	/**
	 * 信息自动回复菜单列表
	 * 
	 * @return
	 */
	public static String menuText(String str) {
		StringBuffer sb = new StringBuffer();
		if(str.equals("help")){
			sb.append("本公众号有三个一级菜单，");
			sb.append("发布出租消息请点击出租，");
			sb.append("求租请点击租房，");
			sb.append("该公众号的所有房源信息都要通过审核才能发布，");
			sb.append("为避免信息无法发布，请按要求填写相关信息！");
			sb.append("\n\n");
			sb.append("如此指示无法解决您的问题，请点击下面的“帮助”链接！");
			sb.append("\n\n");
			sb.append("<a href='http://2p38972u71.qicp.vip/jump?url=help'>帮助</a>\n\n");
		}else{
			sb.append("感谢关注【广州租房小助手】无中介平台\n\n");
			sb.append("如果发布房源，请点击出租菜单\n\n");
			sb.append("如果想要租房，请点击租房菜单\n\n");
			sb.append("(注:需要帮忙请寻求帮助！)");
		}
		return sb.toString();
	}

	/**
	 * 当用户所发消息时包含租房发送
	 * 
	 * @return
	 */
	public static String listingMenu() {
		StringBuffer sb = new StringBuffer();
		sb.append("<a href='http://2p38972u71.qicp.vip/jump?url=listing'>出租</a>\n\n");
		sb.append("只有按要求填写相关信息才能通过信息审核发布哦！\n\n");
		return sb.toString();
	}

	/**
	 * 当用户所发消息时包含求租发送
	 * 
	 * @return
	 */
	public static String rentMenu() {
		StringBuffer sb = new StringBuffer();
		sb.append("<a href='http://2p38972u71.qicp.vip/jump?url=rent'>租房</a>\n\n");
		sb.append("这里有海量房源哦！\n\n");
		return sb.toString();
	}

	/**
	 * 当用户所发消息无法识别时发送
	 * 
	 * @return
	 */
	public static String notRecognized() {
		StringBuffer sb = new StringBuffer();
		sb.append("感谢您的留言~~\r\n"
				+ "如需租房请发送含有租房关键字消息\r\n"
				+ "\r\n" + "如需出租请发送含有房源或出租关键字消息");
		return sb.toString();
	}
	
	/**
	 * 图文信息格式转化
	 * @param newsMessage
	 * @return
	 */
	public static String newsMessageToXml(NewsMessage newsMessage){
		XStream xstream = new XStream();
		xstream.alias("xml", newsMessage.getClass());
		xstream.alias("item", new News().getClass());
		return xstream.toXML(newsMessage);
	}
	
	/**
	 * 图片信息格式转化
	 * @param imageMessage
	 * @return
	 */
	public static String imageMessageToXml(ImageMessage imageMessage){
		XStream xstream = new XStream();
		xstream.alias("xml", imageMessage.getClass());
		return xstream.toXML(imageMessage);
	}
	
	/**
	 * 音乐信息格式转化
	 * @param musicMessage
	 * @return
	 */
	public static String musicMessageToXml(MusicMessage musicMessage){
		XStream xstream = new XStream();
		xstream.alias("xml", musicMessage.getClass());
		return xstream.toXML(musicMessage);
	}
	/**
	 * 初始化图文信息
	 * @param toUserName
	 * @param fromUserName
	 * @return
	 */
	public static String initNewsMessage(String toUserName,String fromUserName){
		String message = null;
		List<News> newsList = new ArrayList<News>();
		NewsMessage newsMessage = new NewsMessage();
		
		News news = new News();
		news.setTitle("imooc");
		news.setDescription("慕课网");
		news.setPicUrl("http://zapper.tunnel.mobi/Weixin/image/imooc.jpg");
		news.setUrl("www.imooc.com");
		
		newsList.add(news);
		
		newsMessage.setToUserName(fromUserName);
		newsMessage.setFromUserName(toUserName);
		newsMessage.setCreateTime(new Date().getTime());
		newsMessage.setMsgType(MESSAGE_NEWS);
		newsMessage.setArticles(newsList);
		newsMessage.setArticleCount(newsList.size());
		
		message = newsMessageToXml(newsMessage);
		return message;
	}
	
	/**
	 * 初始化图片信息
	 * @param toUserName
	 * @param fromUserName
	 * @return
	 */
	public static String initImageMessage(String toUserName,String fromUserName, String mediaId){
		String message = null;
		Image image = new Image();
		image.setMediaId(mediaId);
		ImageMessage imageMessage = new ImageMessage();
		imageMessage.setFromUserName(toUserName);
		imageMessage.setToUserName(fromUserName);
		imageMessage.setMsgType(MESSAGE_IMAGE);
		imageMessage.setCreateTime(new Date().getTime());
		imageMessage.setImage(image);
		message = imageMessageToXml(imageMessage);
		return message;
	}
	
	/**
	 * 初始化音乐信息
	 * @param toUserName
	 * @param fromUserName
	 * @return
	 */
	public static String initMusicMessage(String toUserName,String fromUserName){
		String message = null;
		Music music = new Music();
		music.setThumbMediaId("WsHCQr1ftJQwmGUGhCP8gZ13a77XVg5Ah_uHPHVEAQuRE5FEjn-DsZJzFZqZFeFk");
		music.setTitle("see you again");
		music.setDescription("速7插片曲");
		music.setMusicUrl("http://zapper.tunnel.mobi/Weixin/resource/See You Again.mp3");
		music.setHQMusicUrl("http://zapper.tunnel.mobi/Weixin/resource/See You Again.mp3");
		
		MusicMessage musicMessage = new MusicMessage();
		musicMessage.setFromUserName(toUserName);
		musicMessage.setToUserName(fromUserName);
		musicMessage.setMsgType(MESSAGE_MUSIC);
		musicMessage.setCreateTime(new Date().getTime());
		musicMessage.setMusic(music);
		message = musicMessageToXml(musicMessage);
		return message;
	}
}
