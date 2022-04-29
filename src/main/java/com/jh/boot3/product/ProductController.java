package com.jh.boot3.product;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.jh.boot3.util.Pager;

@Controller
@RequestMapping("/product/*")
public class ProductController {

	@Autowired
	private ProductService productService;
	
	@ModelAttribute
	public String getBoard() {
		return "product";
	}
	
	@GetMapping("ajaxList")
	public ModelAndView getAjaxList(Pager pager) throws Exception{
		//여기에 데이터를 받아서 페이지에 보내줘야하는거니까
		System.out.println("list를 보내줄게");
		ModelAndView mv = new ModelAndView();
		List<ProductVO> ar = productService.getList(pager);
		mv.setViewName("common/productList");
		//productList에서 테이블 만드는 태그 보낼거야.
		mv.addObject("list", ar);
		mv.addObject("pager", pager);
		
		return mv;
		
	}
	
	@PostMapping("add")
	public ModelAndView setAdd(ProductVO productVO, MultipartFile [] files) throws Exception{
													//add jsp에서도 files로 파라미터 보내주고 있어.
		ModelAndView mv = new ModelAndView();
		for(MultipartFile f:files) {
			System.out.println(f.getOriginalFilename());
			System.out.println(f.getSize());
		}
		
		int result =productService.setAdd(productVO, files);
		
		mv.setViewName("common/result");
		//비동기 ajax로 글을 우다다 작성하고 싶은데, 작성할때마다 product/list로 가는게 맘에 안듬
		//그래서 등록할 때마다 메세지로 '등록되었습니다'를 보여주고 싶음
		//그래서 common/result로 보내주려고 (거기에서 메세지 만들거임)
		mv.addObject("result", result);
		//db에 제대로 들어갔는지 알 수 있는 방법은? result가 1이면 됨.
		//그래서 이걸 요청했던 add page로 돌려주게
		
		return mv;
	}
	
	@GetMapping("add")
	public void setAdd() throws Exception{
		
	}
	
	@GetMapping("list")
	public ModelAndView getList(Pager pager) throws Exception{
		ModelAndView mv = new ModelAndView();
		
		List<ProductVO> ar = productService.getList(pager);
		mv.setViewName("product/list");
		mv.addObject("list", ar);
		mv.addObject("pager", pager);
		
		return mv;
	}
}
