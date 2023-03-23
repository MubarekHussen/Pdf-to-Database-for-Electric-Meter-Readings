package com.dventus;
// Generated Dec 8, 2022, 4:50:42 PM by Hibernate Tools 6.1.3.Final

/**
 * MeterTest generated by hbm2java
 */
public class MeterTest implements java.io.Serializable {

	private Long hjid;
	private Meter meter;
	private String comment;
	private String frontCoverSn;
	private String meterPosition;
	private String sampleBased;
	private String sessionId;
	private String testBenchName;
	private String testEndTime;
	private String testFileName;
	private String testPeronnel;
	private String testPlace;
	private String testStartTime;
	private String totalEvaluation;
	private String procurmentRefNo;
	private String reportedFor;
	private MeterAdj meterAdj;

	public MeterTest() {
	}

	public MeterTest(Meter meter, String comment, String frontCoverSn, String meterPosition, String sampleBased,
			String sessionId, String testBenchName, String testEndTime, String testFileName, String testPeronnel,
			String testPlace, String testStartTime, String totalEvaluation, String procurmentRefNo, String reportedFor,
			MeterAdj meterAdj) {
		this.meter = meter;
		this.comment = comment;
		this.frontCoverSn = frontCoverSn;
		this.meterPosition = meterPosition;
		this.sampleBased = sampleBased;
		this.sessionId = sessionId;
		this.testBenchName = testBenchName;
		this.testEndTime = testEndTime;
		this.testFileName = testFileName;
		this.testPeronnel = testPeronnel;
		this.testPlace = testPlace;
		this.testStartTime = testStartTime;
		this.totalEvaluation = totalEvaluation;
		this.procurmentRefNo = procurmentRefNo;
		this.reportedFor = reportedFor;
		this.meterAdj = meterAdj;
	}

	public Long getHjid() {
		return this.hjid;
	}

	public void setHjid(Long hjid) {
		this.hjid = hjid;
	}

	public Meter getMeter() {
		return this.meter;
	}

	public void setMeter(Meter meter) {
		this.meter = meter;
	}

	public String getComment() {
		return this.comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}

	public String getFrontCoverSn() {
		return this.frontCoverSn;
	}

	public void setFrontCoverSn(String frontCoverSn) {
		this.frontCoverSn = frontCoverSn;
	}

	public String getMeterPosition() {
		return this.meterPosition;
	}

	public void setMeterPosition(String meterPosition) {
		this.meterPosition = meterPosition;
	}

	public String getSampleBased() {
		return this.sampleBased;
	}

	public void setSampleBased(String sampleBased) {
		this.sampleBased = sampleBased;
	}

	public String getSessionId() {
		return this.sessionId;
	}

	public void setSessionId(String sessionId) {
		this.sessionId = sessionId;
	}

	public String getTestBenchName() {
		return this.testBenchName;
	}

	public void setTestBenchName(String testBenchName) {
		this.testBenchName = testBenchName;
	}

	public String getTestEndTime() {
		return this.testEndTime;
	}

	public void setTestEndTime(String testEndTime) {
		this.testEndTime = testEndTime;
	}

	public String getTestFileName() {
		return this.testFileName;
	}

	public void setTestFileName(String testFileName) {
		this.testFileName = testFileName;
	}

	public String getTestPeronnel() {
		return this.testPeronnel;
	}

	public void setTestPeronnel(String testPeronnel) {
		this.testPeronnel = testPeronnel;
	}

	public String getTestPlace() {
		return this.testPlace;
	}

	public void setTestPlace(String testPlace) {
		this.testPlace = testPlace;
	}

	public String getTestStartTime() {
		return this.testStartTime;
	}

	public void setTestStartTime(String testStartTime) {
		this.testStartTime = testStartTime;
	}

	public String getTotalEvaluation() {
		return this.totalEvaluation;
	}

	public void setTotalEvaluation(String totalEvaluation) {
		this.totalEvaluation = totalEvaluation;
	}

	public String getProcurmentRefNo() {
		return this.procurmentRefNo;
	}

	public void setProcurmentRefNo(String procurmentRefNo) {
		this.procurmentRefNo = procurmentRefNo;
	}

	public String getReportedFor() {
		return this.reportedFor;
	}

	public void setReportedFor(String reportedFor) {
		this.reportedFor = reportedFor;
	}

	public MeterAdj getMeterAdj() {
		return this.meterAdj;
	}

	public void setMeterAdj(MeterAdj meterAdj) {
		this.meterAdj = meterAdj;
	}

}