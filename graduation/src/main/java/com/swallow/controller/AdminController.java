package com.swallow.controller;


import com.swallow.bean.sqlBean.Listing;
import com.swallow.bean.sqlBean.ListingFacility;
import com.swallow.bean.sqlBean.ListingImg;
import com.swallow.jpa.ListingFacilityJpa;
import com.swallow.jpa.ListingImgJpa;
import com.swallow.jpa.ListingJpa;
import com.swallow.mapper.AdminMapper;
import com.swallow.mapper.ListingFacilityMapper;
import com.swallow.util.ImgUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


@Slf4j
@Controller
@RequestMapping("/admin")
public class AdminController extends HttpServlet {

    @Autowired
    AdminMapper adminMapper;

    @Autowired
    ListingFacilityMapper facilityMapper;

    @Autowired
    ListingJpa listingJpa;

    @Autowired
    ListingImgJpa imgJpa;

    @Autowired
    ListingFacilityJpa facilityJpa;

    @RequestMapping("/getStatusList")
    @ResponseBody
    public List<Map<String, String>> getStatusList(HttpServletRequest request, HttpServletResponse response) {
        List<Map<String, String>> statusList = adminMapper.getStatusList();
        return statusList;
    }

    @GetMapping("/getStatus")
    public  String getStatus(String id, Model model){
        Listing listing = adminMapper.getStatus(id);
        List<ListingImg> files = imgJpa.findByPid(Integer.parseInt(id));
        List<Map<String, String>> facilities = facilityMapper.getFacilityByPid(id);
        model.addAttribute("listing", listing);
        model.addAttribute("files", files);
        model.addAttribute("facilities", facilities);
        return "status";
    }

    /*@CacheEvict(value = "user")*/
    @GetMapping("/changeStatus")
    @ResponseBody
    public String changeStatus(HttpServletRequest request, HttpServletResponse response){
        response.setHeader("Access-Control-Allow-Origin", "*");
        response.setHeader("Cache-Control","no-cache");
        String id = request.getParameter("id");
        String status = request.getParameter("status");
        boolean b = adminMapper.changeStatus(id, status);
        if(b) {
            log.info("修改状态成功，日期：" + new Date());
            return "ok";
        }
        else{
            log.error("修改状态出错， 日期：" + new Date());
            return "error";
        }
    }
}
