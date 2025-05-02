package tpi.dgrv4.dpaa.vo;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

public class DPB9915Trunc {
	private String val;
	private Boolean t;
	@JsonInclude(Include.NON_NULL)
	private String ori;
	
	public void setVal(String val) {
		this.val = val;
	}
	
	public void setT(Boolean t) {
		this.t = t;
	}
	public String getOri() {
		return ori;
	}
	public void setOri(String ori) {
		this.ori = ori;
	}
	
	public Boolean getT() {
		return t;
	}
	
	public String getVal() {
		return val;
	}
}
