package com.dventus;
// Generated Dec 8, 2022, 4:50:42 PM by Hibernate Tools 6.1.3.Final

/**
 * EnergyAndTamperClearTest generated by hbm2java
 */
public class EnergyAndTamperClearTest implements java.io.Serializable {

	private long hjid;
	private TestBenchResults testBenchResults;
	private TestBase testBase;
	private Integer sv;
	private Float metertConstant;
	private Float URange;
	private Float IRange;
	private Float errorAtCapacitiveLoad;
	private String evaluationAtCapacitiveLoad;
	private Float errorAtInductiveLoad;
	private String evaluationAtInductiveLoad;
	private Float errorAtResistiveLoad;
	private String evaluationAtResistiveLoad;
	private String meterIdpasswordStatusText;
	private String meterState;
	public EnergyAndTamperClearTest() {
	}

	public EnergyAndTamperClearTest(TestBase testBase) {
		this.testBase = testBase;
	}

	public EnergyAndTamperClearTest(TestBenchResults testBenchResults, TestBase testBase, Integer sv,
			Float errorAtCapacitiveLoad, String evaluationAtCapacitiveLoad, Float errorAtInductiveLoad,
			String evaluationAtInductiveLoad, Float errorAtResistiveLoad, String evaluationAtResistiveLoad,
			String meterIdpasswordStatusText, String meterState, Float metertConstant, Float URange, Float IRange) {
		this.testBenchResults = testBenchResults;
		this.testBase = testBase;
		this.sv = sv;
		this.errorAtCapacitiveLoad = errorAtCapacitiveLoad;
		this.evaluationAtCapacitiveLoad = evaluationAtCapacitiveLoad;
		this.errorAtInductiveLoad = errorAtInductiveLoad;
		this.evaluationAtInductiveLoad = evaluationAtInductiveLoad;
		this.errorAtResistiveLoad = errorAtResistiveLoad;
		this.evaluationAtResistiveLoad = evaluationAtResistiveLoad;
		this.meterIdpasswordStatusText = meterIdpasswordStatusText;
		this.meterState = meterState;
		this.metertConstant = metertConstant;
		this.URange = URange;
		this.IRange = IRange;
	}

	
	public Float getMetertConstant() {
		return metertConstant;
	}

	public void setMetertConstant(Float metertConstant) {
		this.metertConstant = metertConstant;
	}

	public Float getURange() {
		return URange;
	}

	public void setURange(Float uRange) {
		URange = uRange;
	}

	public Float getIRange() {
		return IRange;
	}

	public void setIRange(Float iRange) {
		IRange = iRange;
	}

	public long getHjid() {
		return this.hjid;
	}

	public void setHjid(long hjid) {
		this.hjid = hjid;
	}

	public TestBenchResults getTestBenchResults() {
		return this.testBenchResults;
	}

	public void setTestBenchResults(TestBenchResults testBenchResults) {
		this.testBenchResults = testBenchResults;
	}

	public TestBase getTestBase() {
		return this.testBase;
	}

	public void setTestBase(TestBase testBase) {
		this.testBase = testBase;
	}

	public Integer getSv() {
		return this.sv;
	}

	public void setSv(Integer sv) {
		this.sv = sv;
	}

	public Float getErrorAtCapacitiveLoad() {
		return this.errorAtCapacitiveLoad;
	}

	public void setErrorAtCapacitiveLoad(Float errorAtCapacitiveLoad) {
		this.errorAtCapacitiveLoad = errorAtCapacitiveLoad;
	}

	public String getEvaluationAtCapacitiveLoad() {
		return this.evaluationAtCapacitiveLoad;
	}

	public void setEvaluationAtCapacitiveLoad(String evaluationAtCapacitiveLoad) {
		this.evaluationAtCapacitiveLoad = evaluationAtCapacitiveLoad;
	}

	public Float getErrorAtInductiveLoad() {
		return this.errorAtInductiveLoad;
	}

	public void setErrorAtInductiveLoad(Float errorAtInductiveLoad) {
		this.errorAtInductiveLoad = errorAtInductiveLoad;
	}

	public String getEvaluationAtInductiveLoad() {
		return this.evaluationAtInductiveLoad;
	}

	public void setEvaluationAtInductiveLoad(String evaluationAtInductiveLoad) {
		this.evaluationAtInductiveLoad = evaluationAtInductiveLoad;
	}

	public Float getErrorAtResistiveLoad() {
		return this.errorAtResistiveLoad;
	}

	public void setErrorAtResistiveLoad(Float errorAtResistiveLoad) {
		this.errorAtResistiveLoad = errorAtResistiveLoad;
	}

	public String getEvaluationAtResistiveLoad() {
		return this.evaluationAtResistiveLoad;
	}

	public void setEvaluationAtResistiveLoad(String evaluationAtResistiveLoad) {
		this.evaluationAtResistiveLoad = evaluationAtResistiveLoad;
	}

	public String getMeterIdpasswordStatusText() {
		return this.meterIdpasswordStatusText;
	}

	public void setMeterIdpasswordStatusText(String meterIdpasswordStatusText) {
		this.meterIdpasswordStatusText = meterIdpasswordStatusText;
	}

	public String getMeterState() {
		return this.meterState;
	}

	public void setMeterState(String meterState) {
		this.meterState = meterState;
	}

}
