package project3.yakdo.domain.BBS;

import org.springframework.stereotype.Component;
import org.springframework.web.util.UriComponents;
import org.springframework.web.util.UriComponentsBuilder;

import lombok.Data;
@Component
@Data
public class Criteria {
	private int pageNum;
	private int amount;
	private String type;
	private String keyword;
	
	//Criteria 생성자
	public Criteria(int pageNum, int amount) {
		this.pageNum = pageNum;
		this.amount = amount;
	}
	
	
	//Criteria 기본 생성자
	public Criteria() {
		this(1,10);
	}
	
	
	
	
}
