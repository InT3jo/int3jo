package project3.yakdo.domain.drugs;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
public class DrugInfo {
	private String itemSeq; // 품목일련번호
	private String itemName; // 제품명
	private String itemEngName; // 제품명(영)
	private String itemImage; // 제품 이미지경로
	private String classNo; // 분류번호
	private String className; // 분류명
	private String chart; // 성상
	private String etcOtcName; // 전문/일반
	private String entpName; // 제조사명
	private String efcyQesitm; // 효능 
	private String useMethodQesitm; // 용법
	private String atpnWarnQesitm; // 경고
	private String atpnQesitm; // 주의사항
	private String intrcQesitm; // 상호작용
	private String seQesitm; // 부작용
	private String depositMethodQesitm; // 보관법 
	private String ediCode; // 보험코드
	private String narcotic; // 마약여부
	private List<String> ingrNameList; // 성분명
	
	public DrugInfo() {} // 기본생성자
	
	public void allClear() {
		this.itemSeq = null;
		this.itemName = null;
		this.itemEngName = null;
		this.itemImage = null;
		this.classNo = null;
		this.className = null;
		this.chart = null;
		this.etcOtcName = null;
		this.entpName = null;
		this.efcyQesitm = null;
		this.useMethodQesitm = null;
		this.atpnWarnQesitm = null;
		this.atpnQesitm = null;
		this.intrcQesitm = null;
		this.seQesitm = null;
		this.depositMethodQesitm = null;
		this.ediCode = null;
		this.narcotic = null;
	}
}
