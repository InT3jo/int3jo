package project3.yakdo.domain.BBS;

public class SearchCriteria extends Criteria {

	 private String searchType ="";	//검색타입 - 제목/내용
	 private String keyword = "";	//검색어
	 
	 public String getSearchType() {
	  return searchType;
	 }
	 public void setSearchType(String searchType) {
	  this.searchType = searchType;
	 }
	 public String getKeyword() {
	  return keyword;
	 }
	 public void setKeyword(String keyword) {
	  this.keyword = keyword;
	 }
	 
	 @Override
	 public String toString() {
	  return super.toString() + " SearchCriteria [searchType=" + searchType + ", keyword=" + keyword + "]";
	 }
}
