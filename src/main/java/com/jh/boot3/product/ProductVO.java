package com.jh.boot3.product;

import java.util.List;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Range;

import lombok.Data;

@Data
public class ProductVO {

	private Long productNum;
	
	@NotBlank
	private String productName;
	
	@Min(value=100)
	private Long productPrice;
	
	@Range(min=1, max=1000)
	private Long productCount;
	
	@NotBlank
	private String productDetail;

	private List<ProductFilesVO> productFilesVOs;
	
	@NotNull
	private Integer sale;
	
	private String id;
}
