package com.jh.boot3.cart;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface CartMapper {

	public List<CartVO> getList(CartVO cartVO) throws Exception;
	
	public int setAdd(CartVO cartVO) throws Exception;
	
}
