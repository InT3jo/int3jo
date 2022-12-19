package project3.yakdo.service.drugs.api.dto;

import lombok.Data;

@Data
public class DrugInfo1 {
	private String itemSeq; // 품목일련번호
	private String itemName; // 제품명
	private String itemImage; // 제품 이미지경로
	private String entpName; // 제조사명
	private String efcyQesitm; // 효능 
	private String useMethodQesitm; // 용법
	private String atpnWarnQesitm; // 경고
	private String atpnQesitm; // 주의사항
	private String intrcQesitm; // 상호작용
	private String seQesitm; // 부작용
	private String depositMethodQesitm; // 보관법 
	
}
