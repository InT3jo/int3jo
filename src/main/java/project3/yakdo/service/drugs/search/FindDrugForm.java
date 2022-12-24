package project3.yakdo.service.drugs.search;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class FindDrugForm {
	private String itemName; // 제품명
	private String ingrName; // 성분명
	private String entpName; // 제조사명
	private String DrugShape; // 의약품모양
	private String drugColor; // 색상 앞,뒤
	private String drugPrint; // 표시 앞,뒤
	private String drugLine; // 분할선 앞,뒤
	private String drugMark; // 마크 (코드,문자,이미지)(앞,뒤)
}