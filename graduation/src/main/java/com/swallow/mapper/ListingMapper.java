package com.swallow.mapper;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import com.swallow.bean.sqlBean.Listing;

import java.util.List;
import java.util.Map;

@Mapper
public interface ListingMapper {

    @Select({"<script>",
            "select convert(l.id,char(11)) id,",
            "l.openid,",
            "date_format(l.create_time, '%Y-%m-%d') time,",
            "(select value from dictionary d where d.type='rentalMethod' and d.key=l.rental_method) rentalMethod,",
            "(select value from dictionary d where d.type='houseType' and d.key=l.house_type) houseType,",
            "(select value from dictionary d where d.type='location' and d.key=l.location) location,",
            "l.community_name communityName,",
            "l.floor,",
            "l.area,",
            "convert(l.rent,char(11)) rent,",
            "l.title, l.description,",
            "l.user_name, l.phone,",
            "( select url from listing_img i where i.pid = l.id order by i.id limit 1 ) url ",
            "from listing l ",
            "where l.status=1 ",
            "<when test='location!=null'>" ,
                "and l.location=#{location} " ,
            "</when>",
            "<when test='price!=null'>" ,
                "and l.grade=#{price} " ,
            "</when>",
            "<when test='houseType!=null'>",
                "and l.house_type=#{houseType} " ,
            "</when>",
             "order by l.id,l.create_time desc,l.update_time desc",
            "</script>"})
    List<Map<String, String>> getRentInfo(String location, String price, String houseType);

    @Select("select convert(l.id,char(11)) id," +
            "l.openid," +
            "date_format(l.create_time, '%Y-%m-%d') time," +
            "(select value from dictionary d where d.type='rentalMethod' and d.key=l.rental_method) rentalMethod," +
            "(select value from dictionary d where d.type='houseType' and d.key=l.house_type) houseType," +
            "(select value from dictionary d where d.type='location' and d.key=l.location) location," +
            "l.community_name communityName," +
            "l.floor," +
            "l.area," +
            "convert(l.rent,char(11)) rent," +
            "l.title, l.description," +
            "l.user_name, l.phone " +
            "from listing l " +
            "where l.status=1 and l.id=#{id} " +
            "order by l.create_time desc,l.update_time desc")
    Listing getListingInfo(String id);

}

