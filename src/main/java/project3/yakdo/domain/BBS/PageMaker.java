package project3.yakdo.domain.BBS;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

import org.springframework.web.util.UriComponents;
import org.springframework.web.util.UriComponentsBuilder;

//페이지 번호를 만들고 출력해주기 위해 필요 
public class PageMaker {

	/*
	 * totalCount : 게시물의 총 갯수 
	 * startPage : 현제 페이지의 시작 번호 (1, 11, 21 등등) 
	 * endPage : 현제 페이지의 끝 번호 (10, 20, 30 등등) 
	 * prev : 이전 페이지로 이동하는 링크의 존재 여부 
	 * next : 다음 페이지로 이동하는 링크의 존재 여부
	 */
	
	private int totalCount;
	private int startPage;
	private int endPage;
	private boolean prev;
	private boolean next;
	private int displayPageNum = 10;
	private Criteria cri;

	public void setCri(Criteria cri) {
		this.cri = cri;
	}

	public void setTotalCount(int totalCount) {
		this.totalCount = totalCount;
		calcData();
	}

	public int getTotalCount() {
		return totalCount;
	}

	public int getStartPage() {
		return startPage;
	}

	public int getEndPage() {
		return endPage;
	}

	public boolean isPrev() {
		return prev;
	}

	public boolean isNext() {
		return next;
	}

	public int getDisplayPageNum() {
		return displayPageNum;
	}

	public Criteria getCri() {
		return cri;
	}

	private void calcData() {
		endPage = (int) (Math.ceil(cri.getPage() / (double) displayPageNum) * displayPageNum);
		startPage = (endPage - displayPageNum) + 1;

		int tempEndPage = (int) (Math.ceil(totalCount / (double) cri.getPerPageNum()));
		if (endPage > tempEndPage) {
			endPage = tempEndPage;
		}
		prev = startPage == 1 ? false : true;
		next = endPage * cri.getPerPageNum() >= totalCount ? false : true;
	}

	//페이징 uri 중 perPageNum때문에 추가 
	public String makeQuery(int page) {//page와 perPageNum의 파라미터와 값을 설정하여 URI를 생성
		UriComponents uriComponents = UriComponentsBuilder.newInstance()//UriComponents는 URI를 생성해주는 클래스
				.queryParam("page", page)
				.queryParam("perPageNum", cri.getPerPageNum())
				.build();

		return uriComponents.toUriString();
	}
	
	//검색 기능 때문에 추가함 
	public String makeSearch(int page)
	{
	    
	   UriComponents uriComponents =
	            UriComponentsBuilder.newInstance()
	            .queryParam("page", page)
	            .queryParam("perPageNum", cri.getPerPageNum())
	            .queryParam("searchType", ((SearchCriteria)cri).getSearchType())
	            .queryParam("keyword", encoding(((SearchCriteria)cri).getKeyword()))
	            .build();   
	   return uriComponents.toUriString();    
	}
	//검색 기능 때문에 추가함 (keyword는 인코딩에 따라 의도한것과 다른 결과가 나올 수 있기 때문에 인코딩 기능을 추가)
	private String encoding(String keyword) {
	   if(keyword == null || keyword.trim().length() == 0)
	   { return ""; }
	   
	   try   {
	    return URLEncoder.encode(keyword, "UTF-8");
	   } catch(UnsupportedEncodingException e)
	   { return ""; }
	}
	
	
	
}
