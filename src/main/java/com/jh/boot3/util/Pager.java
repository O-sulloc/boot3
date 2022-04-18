package com.jh.boot3.util;

import lombok.Data;

@Data
public class Pager {

	// 한 페이지당 글 몇 개씩 조회할 것인지
	// limit 0,10 이부분에서 10에 해당하는 부분
	private Integer perPage;

	// db에서 글 조회할 때 몇 번부터 조회할 것인지
	// limit 0,10 이 부분에서 0에 해당하는 부분
	private Integer startRow;

	// 하단 페이지 부분에 몇 페이지가 나왔으면 좋겠는지
	// 파라미터 값으로 받을 것임
	private Integer pn;

	// search
	private String search;
	private String kind;

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
		// pn:9, perPage:10, starrow:90
		// pn:10, perPage:10, starrow:100
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
