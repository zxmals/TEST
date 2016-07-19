package model;

public class T_DEPART {

	private String BMDM;
	private String DWDM;
	private String BMID;
	private String BMMC;
	private String SFJY;
	private String PXH;
	
	public T_DEPART() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	public T_DEPART(String bMDM, String dWDM, String bMID, String bMMC,
			String sFJY, String pXH) {
		super();
		BMDM = bMDM;
		DWDM = dWDM;
		BMID = bMID;
		BMMC = bMMC;
		SFJY = sFJY;
		PXH = pXH;
	}

	public String getBMDM() {
		return BMDM;
	}

	public void setBMDM(String bMDM) {
		BMDM = bMDM;
	}

	public String getDWDM() {
		return DWDM;
	}

	public void setDWDM(String dWDM) {
		DWDM = dWDM;
	}

	public String getBMID() {
		return BMID;
	}

	public void setBMID(String bMID) {
		BMID = bMID;
	}

	public String getBMMC() {
		return BMMC;
	}

	public void setBMMC(String bMMC) {
		BMMC = bMMC;
	}

	public String getSFJY() {
		return SFJY;
	}

	public void setSFJY(String sFJY) {
		SFJY = sFJY;
	}

	public String getPXH() {
		return PXH;
	}

	public void setPXH(String pXH) {
		PXH = pXH;
	}
	
	
	
}
