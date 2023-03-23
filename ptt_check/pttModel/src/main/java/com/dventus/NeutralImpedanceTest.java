package com.dventus;
// Generated Dec 8, 2022, 4:50:42 PM by Hibernate Tools 6.1.3.Final

/**
 * NeutralImpedanceTest generated by hbm2java
 */
public class NeutralImpedanceTest implements java.io.Serializable {

	private long hjid;
	private TestBenchResults testBenchResults;
	private TestBase testBase;
	private Float resistanceNeutral;

	public NeutralImpedanceTest() {
	}

	public NeutralImpedanceTest(TestBase testBase) {
		this.testBase = testBase;
	}

	public NeutralImpedanceTest(TestBenchResults testBenchResults, TestBase testBase, Float resistanceNeutral) {
		this.testBenchResults = testBenchResults;
		this.testBase = testBase;
		this.resistanceNeutral = resistanceNeutral;
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

	public Float getResistanceNeutral() {
		return this.resistanceNeutral;
	}

	public void setResistanceNeutral(Float resistanceNeutral) {
		this.resistanceNeutral = resistanceNeutral;
	}

}