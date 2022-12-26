package project3.yakdo.domain.drugs;

import java.io.StringReader;
import java.util.ArrayList;
import java.util.List;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
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
	
	public List<String> getEfcyQesitm(int List) {
		List<String> cDataList = getCData(this.efcyQesitm);
		return cDataList;
	}
	
	public List<String> getUseMethodQesitm(int List) {
		List<String> cDataList = getCData(this.useMethodQesitm);
		return cDataList;
	}

	public List<String> getAtpnQesitm(int List) {
		List<String> cDataList = getCData(this.atpnQesitm);
		return cDataList;
	}

	private List<String> getCData(String cData) { // API가 XML형식으로 넘어와서 파싱
		// TODO Auto-generated method stub
		List<String> cDataList = new ArrayList<>();
		try {
			DocumentBuilderFactory dbFactoty = DocumentBuilderFactory.newInstance();
			DocumentBuilder dBuilder = dbFactoty.newDocumentBuilder();
			Document doc = dBuilder.parse(new InputSource(new StringReader(cData)));
			NodeList nodeList = doc.getElementsByTagName("PARAGRAPH");
			int len = nodeList.getLength();
			for(int i=0;i<len;i++) {
				cDataList.add(nodeList.item(i).getTextContent());				
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return cDataList;
	}
}
