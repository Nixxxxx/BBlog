package com.jiang.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

/**
 * 注册激活码实体类
 * @author JH
 *
 */
@Entity
@Table(name = "t_code")
public class Code {

	/* 激活码id */
	private Integer id;
	/* 激活码字符 */
	private String code;
	
	public Code() {
		super();
	}
	
	public Code(Integer id, String code) {
		super();
		this.id = id;
		this.code = code;
	}

	@Id
	@Column(name = "id", nullable = false, unique = true)
	@GenericGenerator(name = "generator", strategy = "assigned")
	@GeneratedValue(generator = "generator")
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	
	@Column(name = "code", nullable = false, length = 255)
	public String getCode() {
		return code;
	}
	
	public void setCode(String code) {
		this.code = code;
	}

}
