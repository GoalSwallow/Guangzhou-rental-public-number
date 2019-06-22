package com.swallow.mapper;


import com.swallow.bean.sqlBean.Listing;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.List;
import java.util.Map;

@Mapper
public interface AdminMapper {

    @Select("select convert(l.id,char(11)) id, " +
            "l.openid," +
            "date_format(l.create_time, '%Y-%m-%d') createTime," +
            "date_format(l.update_time, '%Y-%m-%d') updateTime," +
            "(select value from dictionary d where d.type='rentalMethod' and d.key=l.rental_method) rentalMethod, " +
            "(select value from dictionary d where d.type='houseType' and d.key=l.house_type) houseType, " +
            "(select value from dictionary d where d.type='location' and d.key=l.location) location, " +
            "(select value from dictionary d where d.type='status' and d.key=l.status) status, " +
            "l.community_name communityName, " +
            "l.floor, " +
            "l.area, " +
            "convert(l.rent,char(11)) rent, " +
            "l.title, l.description, " +
            "l.user_name, l.phone " +
            "from listing l " +
            "order by l.create_time desc,l.update_time desc")
    List<Map<String, String>> getStatusList();

    @Select("select convert(l.id,char(11)) id," +
            "l.openid," +
            "date_format(l.create_time, '%Y-%m-%d') time," +
            "(select value from dictionary d where d.type='rentalMethod' and d.key=l.rental_method) rentalMethod," +
            "(select value from dictionary d where d.type='houseType' and d.key=l.house_type) houseType," +
            "(select value from dictionary d where d.type='location' and d.key=l.location) location," +
            "(select value from dictionary d where d.type='status' and d.key=l.status) status," +
            "l.community_name communityName," +
            "l.floor," +
            "l.area," +
            "convert(l.rent,char(11)) rent," +
            "l.title, l.description," +
            "l.user_name, l.phone " +
            "from listing l " +
            "where l.id=#{id}")
    Listing getStatus(String id);

   /* update 表名 set  属性1=value1,属性2=value2 where 限定条件*/
    @Update("update listing set status = #{status} where id = #{id}")
    boolean changeStatus(String id, String status);
}
