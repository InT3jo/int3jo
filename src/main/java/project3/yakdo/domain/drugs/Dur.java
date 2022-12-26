package project3.yakdo.domain.drugs;

import lombok.Data;

@Data
public class Dur {
	private String itemSeq; //약품일련번호
	private String ingrCode; // 성분코드
	private String ingrName; // 성분명
	private String typeName; // 금기타입
	
	public Dur() {} // 기본생성자
	
	public void allClear() {
		this.itemSeq = null;
		this.ingrCode = null;
		this.ingrName = null;
		this.typeName = null;
	}
}
