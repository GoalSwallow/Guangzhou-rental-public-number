package com.swallow.controller;


import java.io.*;
import java.util.*;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.swallow.jpa.ListingFacilityJpa;
import com.swallow.jpa.ListingImgJpa;
import com.swallow.jpa.ListingJpa;
import com.swallow.mapper.ListingFacilityMapper;
import com.swallow.mapper.ListingImgMapper;
import com.swallow.util.ImgUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import com.swallow.bean.sqlBean.Listing;
import com.swallow.bean.sqlBean.ListingFacility;
import com.swallow.bean.sqlBean.ListingImg;
import com.swallow.mapper.ListingMapper;
import com.swallow.service.ListingService;

/**
 * @author PC
 *
 */
@Slf4j
@Controller
@RequestMapping("/listing")
public class ListingController extends HttpServlet{
	
	/*
	 * @Autowired private WorkServiceImpl workImpl;
	 */

	@Autowired
	ListingMapper listingMapper;

	@Autowired
	ListingFacilityMapper facilityMapper;

	@Autowired
	ListingImgMapper imgMapper;

	@Autowired
	ListingJpa listingJpa;

	@Autowired
	ListingImgJpa imgJpa;

	@Autowired
	ListingFacilityJpa facilityJpa;
	
	@Autowired
	ListingService listingService;

	@PostMapping("/save")
	@ResponseBody
	public String form(HttpServletRequest request, HttpServletResponse response) {
		//上传时生成的临时文件保存目录
        String tempPath = request.getServletContext().getRealPath("/img/");
        File tmpFile = new File(tempPath);
                 
        if (!tmpFile.exists()) {
            //创建临时目录
            tmpFile.mkdir();
        }
        //openid
        String openid = request.getParameter("openid");
        //出租方式
        String rentalMethod = request.getParameter("rentalMethod");
        //所属区域
        String location = request.getParameter("location");
        //小区名称
        String communityName = request.getParameter("communityName");
        //户型
        String houseType = request.getParameter("houseType");
        //楼层
        String floor = request.getParameter("floor");
        //建筑面积
        String area = request.getParameter("area");
        //租金
        String rent = request.getParameter("rent");
        String grade = null;
        if(Double.parseDouble(rent) <= 1000){
			grade = "0";
		}else if(Double.parseDouble(rent) <= 2000){
        	grade = "1";
		}else if(Double.parseDouble(rent) <= 3000){
        	grade = "2";
		}else{
        	grade = "3";
		}
        //配套设施
        String[] facilities = request.getParameterValues("facility");
        //标题
        String title = request.getParameter("title");
        //描述
        String description = request.getParameter("description");
        //用户姓名
        String userName = request.getParameter("userName");
        //手机号
        String phone = request.getParameter("phone");
        //上传的文件base64路径
		String[] files = request.getParameterValues("file");
		//对租房数据进行分类封装
		Listing listing = Listing.builder()
						.openid(openid)
						.rentalMethod(rentalMethod)
						.communityName(communityName)
                        .location(location)
						.houseType(houseType)
						.floor(floor)
						.area(area)
						.rent(Integer.parseInt(rent))
						.title(title)
						.description(description)
						.userName(userName)
						.phone(phone)
						.grade(grade)
						.status("0")
						.build();
		
		ArrayList<ListingImg> imgs = new ArrayList<ListingImg>();
		ListingImg img = null;
		if(files != null){
			for (int i=0; i<files.length; i++) {
				//将base64信息生成文件并返回文件名
				img = new ListingImg();
				String fileName = ImgUtil.imgPath(files[i], tempPath);
				img.setUrl(fileName);
				imgs.add(img);
			}
		}
		ArrayList<ListingFacility> facilityList = new ArrayList<ListingFacility>();
		ListingFacility fa = null;
		if(facilities != null){
			for (int i=0; i<facilities.length; i++) {
				fa = new ListingFacility();
				fa.setFacility(facilities[i]);
				facilityList.add(fa);
			}
		}
		//存入数据库
		listingService.save(listing, facilityList, imgs);
		Date date = new Date();
		log.info("添加一条房源记录，日期为：" + date);
		return "上传成功，您的房源信息在通过管理员审核后才能发布，请耐心等待！";
	}
	


	/*@Cacheable(cacheNames = "rentInfo")*/
	@RequestMapping("/getRentInfo")
	@ResponseBody
	public List<Map<String, String>> getRentInfo(HttpServletRequest request, HttpServletResponse response) {
		response.setHeader("Access-Control-Allow-Origin", "*");
		response.setHeader("Cache-Control","no-cache");
		String location = request.getParameter("location");
		if(location != null && location.equals("11")){
			location = null;
		}
		String price = request.getParameter("price");
		if(price != null && price.equals("4")){
			price = null;
		}
		String houseType = request.getParameter("houseType");
		if(houseType != null && houseType.equals("6")){
			houseType = null;
		}
		List<Map<String, String>> listingList = listingMapper.getRentInfo(location, price, houseType);
		log.info("查找租房信息，参数（location：" + location + ",price：" + price + ",houseType：" + houseType + "，日期：" + new Date());
		return listingList;
	}

	/*@Cacheable(cacheNames="rentDetail")*/
	@GetMapping("/getRentDetail")
	public String getRentDetail(String id, Model model) {
		Listing listing = listingMapper.getListingInfo(id);
		List<ListingImg> files = imgJpa.findByPid(Integer.parseInt(id));
		List<Map<String, String>> facilities = facilityMapper.getFacilityByPid(id);
		model.addAttribute("listing", listing);
		model.addAttribute("files", files);
		model.addAttribute("facilities", facilities);
		return "detail";
	}
}
