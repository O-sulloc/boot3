package com.jh.boot3.util;

import lombok.Data;

@Data
public class Pager {

	// 한 페이지당 글 몇 개씩 조회할 것인지
	// limit 0,10 이부분에서 10에 해당하는 부분
	// 파라미터 값으로 받아도 되긴 함.
	private Integer perPage;

	// page: 밑에 뿌려줄 페이지 번호
	private Integer page;
	
	private Long startNum;
	private Long lastNum;
	private boolean pre;
	private boolean next;
	
	// db에서 글 조회할 때 몇 번부터 조회할 것인지
	// limit 0,10 이 부분에서 0에 해당하는 부분
	private Integer startRow;

	// 하단 페이지 부분에 몇 페이지가 나왔으면 좋겠는지
	// 파라미터 값으로 받을 것임
	private Integer pn;

	// search
	// 파라미터 값으로 받을 것임
	private String search;
	private String kind;
	
	public void makeNum(Long totalCount) {
		//1. 총 글의 개수 구하기
		
		//2. 전체 page의 수 구하기 =totalPage
		//(한 페이지에 글 10개씩(perPage) 불러오면 몇 페이지가 필요한가)
		Long totalPage = totalCount/this.getPerPage();
		
		//(총 글의 개수 / 10개씩(perPage) 했을 떄 나머지가 0이 아니면, page++)
		//글이 121개 있는데 페이지당 글10개씩 보여줘. 그럼 13페이지가 있어야겠지
		if(totalCount%this.getPerPage()!=0) {
			totalPage++;
		}
		
		Long perBlock=10L;
		
		Long totalBlock = totalPage/perBlock;
		if(totalPage%perBlock !=0) {
			totalBlock++;
		}
		
		Long curBlock = this.getPn()/perBlock;
		
		if(this.getPn()%perBlock !=0) {
			curBlock++;
		}
		
		this.startNum=(curBlock-1)*perBlock+1;
		this.lastNum=curBlock*perBlock;
		
		if(curBlock>1) {
			this.pre=true;
		}
		
		if(totalBlock>curBlock) {
			this.next=true;
		}
		
		if(curBlock == totalBlock) {
			this.lastNum=totalPage;
		}
		
		if(totalCount==0) {
			this.lastNum=0L;
		}
	}
	
	public String getSearch() {
		if (this.search == null) {
			// search에 null값이 오면
			this.search = "";
			// 공백을 검색해
		}
		return search;
	}

	public void makeRow() {
		// pn:1, perPage:10, starrow:0
		// pn:2, perPage:10, starrow:10
		// pn:3, perPage:10, starrow:20
		// pn:9, perPage:10, starrow:80
		// pn:10, perPage:10, starrow:90
		// (pn - 1) * 10
		this.startRow = (this.getPn() - 1) * this.getPerPage();
		// pn에 1보다 작은 수가 들어가면 -1이 되면서 이상한 값이 도출됨.
		// 따라서 아래 getPn으로 처리.
	}

	public Integer getPn() {
		if (this.pn == null || this.pn < 1) {
			this.pn = 1;
		}
		return pn;
	}

	public Integer getPerPage() {
		// lombok으로 getter, setter를 만들긴 했지만, 내가 값을 따로 지정해줘야할 때는
		// 이렇게 직접 getter,setter를 만들 수 있다.
		if (this.perPage == null || this.perPage < 1) {
			this.perPage = 10;
		}
		return this.perPage;
	}
}
