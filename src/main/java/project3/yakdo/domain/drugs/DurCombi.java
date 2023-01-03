package project3.yakdo.domain.drugs;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class DurCombi {
	private String itemSeq; //약품일련번호
	private String ingrCode; // 성분코드
	private String ingrName; // 성분명
	private String mixIngrCode; // 병용금기 성분코드
	private String mixIngrName; // 병용금기 성분명

	
	public void allClear() {
		this.itemSeq = null;
		this.ingrCode = null;
		this.ingrName = null;
		this.mixIngrCode = null;
		this.mixIngrName = null;
	}
}
