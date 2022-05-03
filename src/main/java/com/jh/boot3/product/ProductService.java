package com.jh.boot3.product;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import com.jh.boot3.util.FileManager;
import com.jh.boot3.util.Pager;

@Service
@Transactional(rollbackFor = Exception.class)
public class ProductService {

	@Autowired
	private ProductMapper productMapper;
	
	@Autowired
	private FileManager fileManager;
	
	public ProductVO getDetail(ProductVO productVO) throws Exception{
		return productMapper.getDetail(productVO);
	}
	
	public List<ProductVO> getList(Pager pager) throws Exception{
		pager.makeRow();
		pager.makeNum(productMapper.getTotalCount(pager));
		return productMapper.getList(pager);
	}
	
	public int setAdd(ProductVO productVO, MultipartFile [] files) throws Exception{
		int result = productMapper.setAdd(productVO);
		//이게 먼저 들어가야 파일이 프로덕트넘을 참조하지
		
		if(files != null) {
			//만약 파일이 넘어오지 않았다면
			for(MultipartFile mf : files) {
				if(mf.isEmpty()) {
					continue;
				}
				
				//1. 파일들을 HDD에 저장한다.
				String fileName=fileManager.fileSave(mf, "resources/upload/product/");
				//resources의~product폴더 밑에 파일을 저장해줘.
				//그리고 그걸 fileName이라고 할래
				System.out.println(fileName);
				//파일이 어떤 이름으로 들어갔는지 궁금하니까 출력해줘
				
				//2. 저장된 파일의 정보를 DB에도 저장한다.
				ProductFilesVO productFilesVO = new ProductFilesVO();
				productFilesVO.setProductNum(productVO.getProductNum());
				//상품 먼저 등록돼야 상품사진에 productnum을 줄 수 있음.
				//그래서 usegenereated어쩌구로 미리 보내놓음
				
				productFilesVO.setFileName(fileName);
				productFilesVO.setOriName(mf.getOriginalFilename());
				result = productMapper.setFileAdd(productFilesVO); 
				
			}//for문 종료
			
		}//if문 종료
		
		
		return result;
		
	}//메서드 종료
}//클래스 종료
