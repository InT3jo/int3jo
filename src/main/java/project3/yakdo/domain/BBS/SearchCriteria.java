package project3.yakdo.domain.BBS;

import lombok.Data;

@Data
public class SearchCriteria extends Criteria {

	private String searchType = ""; // 검색타입 - 제목/내용
	private String keyword = ""; // 검색어

	@Override
	public String toString() {
		return super.toString() + " SearchCriteria [searchType=" + searchType + ", keyword=" + keyword + "]";
	}
}
