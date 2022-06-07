package com.jh.boot3.cart;

import lombok.Data;

@Data
public class CartVO {

	private Long cartNum;
	private String id;
	private Long productNum;
	private Long count;
	private Data regDate;
}
