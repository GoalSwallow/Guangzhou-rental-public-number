package com.swallow.bean.sqlBean;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.sql.Timestamp;

@AllArgsConstructor
@NoArgsConstructor
@Builder(toBuilder = true)
@Data
@Entity
public class Listing {
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Integer id;
	//微信openid
	private String openid;
	//出租方式
	private String rentalMethod;
	//区域
	private String location;
	//小区名称
	private String communityName;
	//户型
	private String houseType;
	//楼层
	private String floor;
	//建筑面积
	private String area;
	//租金
	private int rent;
	//标题
	private String title;
	//描述
	private String description;
	//用户姓名
	private String userName;
	//手机号
	private String phone;
	//发放标志 0为未处理，1为展示，2为废除
	private String status;
	//价格等级标志
	private String grade;
	//创建时间
	@CreationTimestamp
	private Timestamp createTime;
	//最近更新时间
	@UpdateTimestamp
	private Timestamp updateTime;
}
