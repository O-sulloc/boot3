package com.jh.boot3.product;

import static org.junit.jupiter.api.Assertions.*;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.jh.boot3.util.Pager;

@SpringBootTest
class ProductMapperTest {

	@Autowired
	private ProductMapper productMapper;

	//@Test
	void setFileAdd() throws Exception{
		ProductFilesVO productFilesVO = new ProductFilesVO();
		productFilesVO.setProductNum(2L);
		productFilesVO.setFileName("file2");
		productFilesVO.setFileNum(2L);
		productFilesVO.setOriName("oriro");
		
		int result = productMapper.setFileAdd(productFilesVO);
		
		assertEquals(1, result);
	}
	
	//@Test
	void setAdd() throws Exception{
		ProductVO productVO = new ProductVO();
		productVO.setProductNum(2L);
		productVO.setProductName("2n");
		productVO.setProductPrice(2000L);
		productVO.setProductDetail("2d");
		productVO.setProductCount(3L);
		
		int result = productMapper.setAdd(productVO);
		
		assertEquals(1, result);
	}
	
	@Test
	void getList() throws Exception{
		Pager pager = new Pager();
		pager.makeRow();
		
		List<ProductVO> ar = productMapper.getList(pager);
		
		assertEquals(2, ar.size());
	}
}
