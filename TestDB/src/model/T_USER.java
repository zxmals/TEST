package model;

public class T_USER {

	private String YHDM;
	private String DWDM;
	private String YHID;
	private String YHXM;
	private String YHKL;
	private String YHXB;
	private String YHBM;
	private String CSRQ;
	private String PXH;
	private String SFJY;
	private String BMMC;

	public T_USER(String yHDM, String dWDM, String yHID, String yHXM,
			String yHKL, String yHXB, String yHBM, String cSRQ, String pXH,
			String sFJY) {
		super();
		YHDM = yHDM;
		DWDM = dWDM;
		YHID = yHID;
		YHXM = yHXM;
		YHKL = yHKL;
		YHXB = yHXB;
		YHBM = yHBM;
		CSRQ = cSRQ;
		PXH = pXH;
		SFJY = sFJY;
	}

	public T_USER(String yHDM, String yHXM, String yHBM, String bMMC) {
		super();
		YHDM = yHDM;
		YHXM = yHXM;
		YHBM = yHBM;
		BMMC = bMMC;
	}

	public T_USER() {
		super();
		// TODO Auto-generated constructor stub
	}

	public String getBMMC() {
		return BMMC;
	}

	public void setBMMC(String bMMC) {
		BMMC = bMMC;
	}

	public String getYHDM() {
		return YHDM;
	}

	public void setYHDM(String yHDM) {
		YHDM = yHDM;
	}

	public String getDWDM() {
		return DWDM;
	}

	public void setDWDM(String dWDM) {
		DWDM = dWDM;
	}

	public String getYHID() {
		return YHID;
	}

	public void setYHID(String yHID) {
		YHID = yHID;
	}

	public String getYHXM() {
		return YHXM;
	}

	public void setYHXM(String yHXM) {
		YHXM = yHXM;
	}

	public String getYHKL() {
		return YHKL;
	}

	public void setYHKL(String yHKL) {
		YHKL = yHKL;
	}

	public String getYHXB() {
		return YHXB;
	}

	public void setYHXB(String yHXB) {
		YHXB = yHXB;
	}

	public String getYHBM() {
		return YHBM;
	}

	public void setYHBM(String yHBM) {
		YHBM = yHBM;
	}

	public String getCSRQ() {
		return CSRQ;
	}

	public void setCSRQ(String cSRQ) {
		CSRQ = cSRQ;
	}

	public String getPXH() {
		return PXH;
	}

	public void setPXH(String pXH) {
		PXH = pXH;
	}

	public String getSFJY() {
		return SFJY;
	}

	public void setSFJY(String sFJY) {
		SFJY = sFJY;
	}

}
