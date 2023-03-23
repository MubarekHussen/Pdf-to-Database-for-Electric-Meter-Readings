package com.dventus.pdfextractor;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Set;

import org.apache.commons.io.FileUtils;
import org.hibernate.Session;
import org.hibernate.Transaction;

import com.dventus.CalibrationTest;
import com.dventus.EnergyAndTamperClearTest;
import com.dventus.EnergyRegisterTest;
import com.dventus.MeterAdj;
import com.dventus.MeterTest;
import com.dventus.NeutralImpedanceTest;
import com.dventus.NoLoadTest;
import com.dventus.OperatingCurrentTest;
import com.dventus.ScAdjustment;
import com.dventus.ShuntImpedanceTest;
import com.dventus.StartingCurrentTest;
import com.dventus.TestBase;
import com.dventus.TestFile;
import com.dventus.main.HibernateUtil;

public class InsertMain {

	private static List<String> identification = new ArrayList<String>(List.of("identifications"));
	private static List<String> capacitiveLoad = new ArrayList<String>(List.of("accuracy test capacitive load"));
	private static List<String> inductiveLoad = new ArrayList<String>(List.of("accuracy test inductive load"));
	private static List<String> activeLoad = new ArrayList<String>(List.of("accuracy test active load"));
	private static List<String> calibrationTest = new ArrayList<String>(List.of("meter calibration", "meter calibration sm"));
	private static List<String> activeEnergyRegisterTest = new ArrayList<String>(List.of("active energy register test", "energy register test", "active energy register test"));
	private static List<String> startingCurrentTest = new ArrayList<String>(List.of("starting test", "starting i test", "starting current test full"));
	private static List<String> noLoad = new ArrayList<String>(List.of("no-load test", "no load test", "noload test"));
	private static List<String> scAdjustmentTest = new ArrayList<String>(List.of("sc adjustment"));
	private static List<String> operatingCurrentTest = new ArrayList<String>(List.of("operating current test", "operating current"));
	private static List<String> currAndPfAt05 = new ArrayList<String>(List.of("10 a (ib) @ pf 0.5", "5 a (ib) @ pf 0.5","10a(ib) @ pf 0.5", "5a(ib) @ pf 0.5"));
	private static List<String> currAndPfAt1 = new ArrayList<String>(List.of("5 a (ib) @ pf 1", "10 a (ib) @ pf 1", "10a (ib) @ pf 1", "5a (ib) @ pf 1"));
	private static List<String> variationTests = new ArrayList<String>(List.of("variation tests", "variation tests variables initializer", "variation last result"));
	public static void insertData(String path, File pdfFile) {
		System.out.println(path + "*******************");

		byte[] pdfFIleInBytes;
		TestFile testfile = new TestFile();

		ReadExcelFile readExcelFile = new ReadExcelFile();
		ExtractData theData = new ExtractData();
		List<List<String>> data = readExcelFile.getOutputData(path);
		List<String> outputHeader = data.remove(data.size() - 1);
		List<List<String>> outputData = theData.extractData(data);

		List<String> MPS = theData.getMeterPositions(outputData);
		Collections.sort(MPS);
		TestBase[] testBase = new TestBase[MPS.size()];
		MeterTest[] meterTest = new MeterTest[MPS.size()];
		MeterAdj[] meterAdj = new MeterAdj[MPS.size()];
		CalibrationTest[] calibration = new CalibrationTest[MPS.size()];
		StartingCurrentTest[] startingCurrent = new StartingCurrentTest[MPS.size()];
		ShuntImpedanceTest[] shuntImpedance = new ShuntImpedanceTest[MPS.size()];
		NeutralImpedanceTest[] neutralImpedance = new NeutralImpedanceTest[MPS.size()];
		EnergyRegisterTest[] energyRegister = new EnergyRegisterTest[MPS.size()];
		ScAdjustment[] scAdjustment = new ScAdjustment[MPS.size()];
		EnergyAndTamperClearTest[] energyAndTamperClear = new EnergyAndTamperClearTest[MPS.size()];
		OperatingCurrentTest[] operatingCurrent = new OperatingCurrentTest[MPS.size()];
		NoLoadTest[] noLoadTest = new NoLoadTest[MPS.size()];
		try {
			pdfFIleInBytes = FileUtils.readFileToByteArray(pdfFile);
			String names[] = path.split("/");
			String fileID = outputHeader.get(1)+outputHeader.get(4);
			testfile.setFileName(names[names.length - 2]);
			testfile.setFileData(pdfFIleInBytes);
			testfile.setFileLength(BigInteger.valueOf(pdfFIleInBytes.length));
			testfile.setFileId(fileID);
		} catch (IOException e) {
			e.printStackTrace();
		}
		for (int j = 0; j < MPS.size(); j++) {
			testBase[j] = new TestBase();
			calibration[j] = new CalibrationTest(testBase[j]);
			startingCurrent[j] = new StartingCurrentTest(testBase[j]);
			shuntImpedance[j] = new ShuntImpedanceTest(testBase[j]);
			neutralImpedance[j] = new NeutralImpedanceTest(testBase[j]);
			energyRegister[j] = new EnergyRegisterTest(testBase[j]);
			scAdjustment[j] = new ScAdjustment(testBase[j]);
			energyAndTamperClear[j] = new EnergyAndTamperClearTest(testBase[j]);
			operatingCurrent[j] = new OperatingCurrentTest(testBase[j]);
		}
		int i = 0;
		for (int counter = 0; counter < MPS.size(); counter++) {
			while (i < outputData.size() && MPS.get(counter).equalsIgnoreCase(outputData.get(i).get(1)) ) {
				if (identification.contains(outputData.get(i).get(0).toLowerCase())) {
					if (outputData.get(i).get(2).equalsIgnoreCase("T1 Start")) {
						testBase[counter].setTestStartTime(outputData.get(i).get(3));
					}
					else if (outputData.get(i).get(2).equalsIgnoreCase("T1 End")) {
						testBase[counter].setTestEndTime(outputData.get(i).get(3));
					}
					else if (outputData.get(i).get(2).equalsIgnoreCase("Serial #")) {
						testBase[counter].setFrontCoverSn(outputData.get(i).get(3));
						meterTest[counter].setFrontCoverSn(outputData.get(i).get(3));
						testBase[counter].setMeterPosition(outputData.get(i).get(1));
						testBase[counter].setSessionId(outputHeader.get(1));
						meterTest[counter].setSessionId(outputHeader.get(1));
						testBase[counter].setReport(outputHeader.get(4));
						testBase[counter].setTestEndTime(outputHeader.get(10) + "  " + outputHeader.get(11));
						meterTest[counter].setTestEndTime(outputHeader.get(10) + "  " + outputHeader.get(11));
						testBase[counter].setTestStartTime(outputHeader.get(6) + "  " + outputHeader.get(7));
						meterTest[counter].setTestStartTime(outputHeader.get(6) + "  " + outputHeader.get(7));
						meterTest[counter].setProcurmentRefNo(outputHeader.get(12));
						//			            meterTest[counter].setReportedFor(outputHeader.get(2));
						//			            meterTest[counter].setMeterPosition(outputData.get(i).get(1));
						//			            meterAdj[counter].setNominalVoltage(outputHeader.get(21));
						//			            meterAdj[counter].setMaxCurrent(outputHeader.get(25));
						//			            meterAdj[counter].setSessionId(outputHeader.get(1));
						//			            meterAdj[counter].setReport(outputHeader.get(5));
						//			            meterAdj[counter].setConstant1(outputHeader.get(29));
						//			            meterAdj[counter].setMaxStartTime(outputHeader.get(34));
						//			            meterAdj[counter].setMaxCreepingTime(outputHeader.get(36));
						//			            meterAdj[counter].setBasicCurrent(outputHeader.get(23));
						//			            meterAdj[counter].set1st(outputHeader.get(27));
						//			            meterAdj[counter].setType(outputHeader.get(20));

					}
					else if (outputData.get(i).get(2).equalsIgnoreCase("Total Evaluation")) {
						testBase[counter].setEvaluation(outputData.get(i).get(3));
						meterTest[counter].setTotalEvaluation(outputData.get(i).get(3));
					}
				}

				else if (scAdjustmentTest.contains(outputData.get(i).get(0).toLowerCase())) {
					if (outputData.get(i).get(2).equalsIgnoreCase("Meter Constant")) {
						scAdjustment[counter].setMetertConstant(Float.parseFloat(outputData.get(i).get(3).replaceAll("[^0-9|.|-]", "")));
					}
					else if (outputData.get(i).get(2).equalsIgnoreCase("Percentage Error")) {
						if (outputData.get(i).get(3).equals("---")) {
							scAdjustment[counter].setPercentageError(null);
						}
						else {
							scAdjustment[counter].setPercentageError(Float.parseFloat(outputData.get(i).get(3).replaceAll("[^0-9|.|-]", "")));
						}
					}
					else if (outputData.get(i).get(2).equalsIgnoreCase("U-Range")) {
						scAdjustment[counter].setURange(Float.parseFloat(outputData.get(i).get(3).replaceAll("[^0-9|.|-]", "")));
					}
					else if (outputData.get(i).get(2).equalsIgnoreCase("I-Range")) {
						scAdjustment[counter].setIRange(Float.parseFloat(outputData.get(i).get(3).replaceAll("[^0-9|.|-]", "")));
					}
					else if (outputData.get(i).get(2).equalsIgnoreCase("Evaluation")) {
						scAdjustment[counter].setEvaluation(outputData.get(i).get(3));
					}
				}
				else if (operatingCurrentTest.contains(outputData.get(i).get(0).toLowerCase())) {
					if (outputData.get(i).get(2).equalsIgnoreCase("Meter Constant")) {
						operatingCurrent[counter].setMetertConstant(Float.parseFloat(outputData.get(i).get(3).replaceAll("[^0-9|.|-]", "")));
					}
					else if (outputData.get(i).get(2).equalsIgnoreCase("Percentage Error")) {
						if (outputData.get(i).get(3).equals("---")) {
							operatingCurrent[counter].setPercentageError(null);
						}
						else {
							operatingCurrent[counter].setPercentageError(Float.parseFloat(outputData.get(i).get(3).replaceAll("[^0-9|.|-]", "")));
						}
					}
					else if (outputData.get(i).get(2).equalsIgnoreCase("U-Range")) {
						operatingCurrent[counter].setURange(Float.parseFloat(outputData.get(i).get(3).replaceAll("[^0-9|.|-]", "")));
					}
					else if (outputData.get(i).get(2).equalsIgnoreCase("I-Range")) {
						operatingCurrent[counter].setIRange(Float.parseFloat(outputData.get(i).get(3).replaceAll("[^0-9|.|-]", "")));
					}
					else if(outputData.get(i).get(2).equalsIgnoreCase("Evaluation")) {
						operatingCurrent[counter].setEvaluation(outputData.get(i).get(3));
					}
				}

				else if (noLoad.contains(outputData.get(i).get(0).toLowerCase())) {
					if (outputData.get(i).get(2).equalsIgnoreCase("ALIR Typ")) {
						noLoadTest[counter].setAlirTyp(outputData.get(i).get(3));
					}
					else if (outputData.get(i).get(2).equalsIgnoreCase("Edges")) {
						noLoadTest[counter].setEdgesCountNoLoad(Integer.valueOf(outputData.get(i).get(3)));
					}
					else if (outputData.get(i).get(2).equalsIgnoreCase("Evaluation")) {
						noLoadTest[counter].setEvaluation(outputData.get(i).get(3));
					}
				}
				else if (startingCurrentTest.contains(outputData.get(i).get(0).toLowerCase())) {
					if (outputData.get(i).get(2).equalsIgnoreCase("ALIR Typ")) {
						startingCurrent[counter].setAlirTyp(outputData.get(i).get(3));
					}
					else if (outputData.get(i).get(2).equalsIgnoreCase("Edges")) {
						startingCurrent[counter].setEdgesCountStartingI(Integer.valueOf(outputData.get(i).get(3)));
					}
					else if (outputData.get(i).get(2).equalsIgnoreCase("Evaluation")) {
						startingCurrent[counter].setEvaluation(outputData.get(i).get(3));
					}
				}
				else if (calibrationTest.contains(outputData.get(i).get(0).toLowerCase())) {
					if (outputData.get(i).get(2).equalsIgnoreCase("Calibration Result")) {
						calibration[counter].setCalibrationState(outputData.get(i).get(3));
					}
					else if (outputData.get(i).get(2).equalsIgnoreCase("ENERGY_LO_VALUESPhase")) {
						calibration[counter].setElo(Integer.valueOf(outputData.get(i).get(3)));
					}
					else if (outputData.get(i).get(2).equalsIgnoreCase("ENERGY_HI_VALUESPhase")) {
						calibration[counter].setEhi(Integer.valueOf(outputData.get(i).get(3)));
					}
					else if (outputData.get(i).get(2).equalsIgnoreCase("GAIN_CORR_Phase")) {
						calibration[counter].setGco(Integer.valueOf(outputData.get(i).get(3)));
					}
					else if (outputData.get(i).get(2).equalsIgnoreCase("PowerOffset_Phase")) {
						calibration[counter].setPoff(Integer.valueOf(outputData.get(i).get(3)));
					}
					else if (outputData.get(i).get(2).equalsIgnoreCase("Phase_Correction_Phase")) {
						calibration[counter].setPhCo(Integer.valueOf(outputData.get(i).get(3)));
					}
					else if (outputData.get(i).get(2).equalsIgnoreCase("STARTING_CURRENT_INT")) {
						calibration[counter].setStCint(Integer.valueOf(outputData.get(i).get(3)));
					}
					else if (outputData.get(i).get(2).equalsIgnoreCase("STARTING_CURRENT_FRAC")) {
						calibration[counter].setStCfra(Integer.valueOf(outputData.get(i).get(3)));
					}
					else if (outputData.get(i).get(2).equalsIgnoreCase("VOLTAGE_RATIO")) {
						calibration[counter].setVr(Integer.valueOf(outputData.get(i).get(3)));
					}
					else if (outputData.get(i).get(2).equalsIgnoreCase("CURRENT_RATIO")) {
						calibration[counter].setCr(Integer.valueOf(outputData.get(i).get(3)));
					}
					else if (outputData.get(i).get(2).equalsIgnoreCase("PHASE_CORRECTION_Phase0")) {
						calibration[counter].setPhCo0(Integer.valueOf(outputData.get(i).get(3)));
					}
					else if (outputData.get(i).get(2).equalsIgnoreCase("PHASE_CORRECTION_Phase1")) {
						calibration[counter].setPhCo1(Integer.valueOf(outputData.get(i).get(3)));
					}
					else if (outputData.get(i).get(2).equalsIgnoreCase("PHASE_CORRECTION_Phase2")) {
						calibration[counter].setPhCo2(Integer.valueOf(outputData.get(i).get(3)));
					}
					else if (outputData.get(i).get(2).equalsIgnoreCase("PHASE_CORRECTION_Phase3")) {
						calibration[counter].setPhCo3(Integer.valueOf(outputData.get(i).get(3)));
					}
				}
				else if (variationTests.contains(outputData.get(i).get(0).toLowerCase())) {
					if (outputData.get(i).get(2).equalsIgnoreCase("% Error PF=1 60A 220V 50Hz")) {
						operatingCurrent[counter].setErrorPf160a220v50hz007(Float.valueOf(outputData.get(i).get(3)));
					}
					else if (outputData.get(i).get(2).equalsIgnoreCase("% Error PF=1 30A 220V 50Hz")) {
						operatingCurrent[counter].setErrorPf130a220v50hz010(Float.valueOf(outputData.get(i).get(3)));
					}
					else if (outputData.get(i).get(2).equalsIgnoreCase("% Error PF=1 20A 220V 50Hz")) {
						operatingCurrent[counter].setErrorPf120a220v50hz029(Float.valueOf(outputData.get(i).get(3)));
					}
					else if (outputData.get(i).get(2).equalsIgnoreCase("% Error PF=1 10A 220V 50Hz")) {
						operatingCurrent[counter].setErrorPf110a220v50hz022(Float.valueOf(outputData.get(i).get(3)));
					}
					else if (outputData.get(i).get(2).equalsIgnoreCase("% Error PF=1 5A 220V 50Hz")) {
						operatingCurrent[counter].setErrorPf15a220v50hz004(Float.valueOf(outputData.get(i).get(3)));
					}
					else if (outputData.get(i).get(2).equalsIgnoreCase("% Error PF=1 1A 220V 50Hz")) {
						operatingCurrent[counter].setErrorPf11a220v50hz008(Float.valueOf(outputData.get(i).get(3)));
					}
					else if (outputData.get(i).get(2).equalsIgnoreCase("% Error PF=1 0.5A 220V 50Hz")) {
						operatingCurrent[counter].setErrorPf105a220v50hz023(Float.valueOf(outputData.get(i).get(3)));
					}
					else if (outputData.get(i).get(2).equalsIgnoreCase("% Error PF=1 0.25A 220V 50Hz")) {
						operatingCurrent[counter].setErrorPf1025a220v50hz063(Float.valueOf(outputData.get(i).get(3)));
					}
					else if (outputData.get(i).get(2).equalsIgnoreCase("% Error PF=0.5 lag 60A 220V 50Hz")) {
						operatingCurrent[counter].setErrorPf05lag60a220v50hz0(Float.valueOf(outputData.get(i).get(3)));
					}
					else if (outputData.get(i).get(2).equalsIgnoreCase("% Error PF=0.5 lag 30A 220V 50Hz")) {
						operatingCurrent[counter].setErrorPf05lag30a220v50hz0(Float.valueOf(outputData.get(i).get(3)));
					}
					else if (outputData.get(i).get(2).equalsIgnoreCase("% Error PF=0.5 lag 20A 220V 50Hz")) {
						operatingCurrent[counter].setErrorPf05lag20a220v50hz0(Float.valueOf(outputData.get(i).get(3)));
					}
					else if (outputData.get(i).get(2).equalsIgnoreCase("% Error PF=0.5 lag 10A 220V 50Hz")) {
						operatingCurrent[counter].setErrorPf05lag10a220v50hz0(Float.valueOf(outputData.get(i).get(3)));
					}
					else if (outputData.get(i).get(2).equalsIgnoreCase("% Error PF=0.5 lag 5A 220V 50Hz")) {
						operatingCurrent[counter].setErrorPf05lag5a220v50hz0(Float.valueOf(outputData.get(i).get(3)));
					}
					else if (outputData.get(i).get(2).equalsIgnoreCase("% Error PF=0.5 lag 1A 220V 50Hz")) {
						operatingCurrent[counter].setErrorPf05lag1a220v50hz0(Float.valueOf(outputData.get(i).get(3)));
					}
					else if (outputData.get(i).get(2).equalsIgnoreCase("% Error PF=0.5 lag 0.5A 220V 50Hz")) {
						operatingCurrent[counter].setErrorPf05lag05a220v50hz0(Float.valueOf(outputData.get(i).get(3)));
					}
					else if (outputData.get(i).get(2).equalsIgnoreCase("% Error PF=0.8 lead 60A 220V 50Hz")) {
						operatingCurrent[counter].setErrorPf08lead60a220v50h0(Float.valueOf(outputData.get(i).get(3)));
					}
					else if (outputData.get(i).get(2).equalsIgnoreCase("% Error PF=0.8 lead 30A 220V 50Hz")) {
						operatingCurrent[counter].setErrorPf08lead30a220v50h0(Float.valueOf(outputData.get(i).get(3)));
					}
					else if (outputData.get(i).get(2).equalsIgnoreCase("% Error PF=0.8 lead 20A 220V 50Hz")) {
						operatingCurrent[counter].setErrorPf08lead20a220v50h0(Float.valueOf(outputData.get(i).get(3)));
					}
					else if (outputData.get(i).get(2).equalsIgnoreCase("% Error PF=0.8 lead 10A 220V 50Hz")) {
						operatingCurrent[counter].setErrorPf08lead10a220v50h0(Float.valueOf(outputData.get(i).get(3)));
					}
					else if (outputData.get(i).get(2).equalsIgnoreCase("% Error PF=0.8 lead 5A 220V 50Hz")) {
						operatingCurrent[counter].setErrorPf08lead5a220v50hz0(Float.valueOf(outputData.get(i).get(3)));
					}
					else if (outputData.get(i).get(2).equalsIgnoreCase("% Error PF=0.8 lead 1A 220V 50Hz")) {
						operatingCurrent[counter].setErrorPf08lead1a220v50hz0(Float.valueOf(outputData.get(i).get(3)));
					}
					else if (outputData.get(i).get(2).equalsIgnoreCase("% Error PF=0.8 lead 0.5A 220V 50Hz")) {
						operatingCurrent[counter].setErrorPf08lead05a220v50h0(Float.valueOf(outputData.get(i).get(3)));
					}
					else if (outputData.get(i).get(2).equalsIgnoreCase("Max-Error, 0.1Ib to Imax, PF=1")) {
						operatingCurrent[counter].setMaxError01ibToImaxPf1(Float.valueOf(outputData.get(i).get(3)));
					}
					else if (outputData.get(i).get(2).equalsIgnoreCase("Max-Error, 0.05Ib to 0.1Ib, PF=1")) {
						operatingCurrent[counter].setMaxError005ibTo01ibPf1(Float.valueOf(outputData.get(i).get(3)));
					}
					else if (outputData.get(i).get(2).equalsIgnoreCase("Max-Error, 0.2Ib to Imax, PF=0.5 Ind")) {
						operatingCurrent[counter].setMaxError02ibToImaxPf050(Float.valueOf(outputData.get(i).get(3)));
					}
					else if (outputData.get(i).get(2).equalsIgnoreCase("Max-Error, 0.1Ib to 0.2Ib, PF=0.5 Ind")) {
						operatingCurrent[counter].setMaxError01ibTo02ibPf050(Float.valueOf(outputData.get(i).get(3)));
					}
					else if (outputData.get(i).get(2).equalsIgnoreCase("Max-Error, 0.2Ib to Imax, PF=0.8 Cap")) {
						operatingCurrent[counter].setMaxError02ibToImaxPf080(Float.valueOf(outputData.get(i).get(3)));
					}
					else if (outputData.get(i).get(2).equalsIgnoreCase("Max-Error, 0.1Ib to 0.2Ib, PF=0.8 Cap")) {
						operatingCurrent[counter].setMaxError01ibTo02ibPf080(Float.valueOf(outputData.get(i).get(3)));
					}
					if (outputData.get(i).get(2).equalsIgnoreCase("Meter Constant")) {
						operatingCurrent[counter].setMetertConstant(Float.parseFloat(outputData.get(i).get(3).replaceAll("[^0-9|.|-]", "")));
					}
				else if (currAndPfAt05.contains(outputData.get(i).get(0).toLowerCase())) {
					if (outputData.get(i).get(2).equalsIgnoreCase("Meter Constant")) {
						operatingCurrent[counter].setMetertConstantPf05(Float.parseFloat(outputData.get(i).get(3).replaceAll("[^0-9|.|-]", "")));
					}
					else if (outputData.get(i).get(2).equalsIgnoreCase("Percentage Error")) {
						if (outputData.get(i).get(3).equals("---")) {
							operatingCurrent[counter].setPercentageErrorPf05(null);
						}
						else {
							operatingCurrent[counter].setPercentageErrorPf05(Float.parseFloat(outputData.get(i).get(3).replaceAll("[^0-9|.|-]", "")));
						}
					}
					else if (outputData.get(i).get(2).equalsIgnoreCase("U-Range")) {
						operatingCurrent[counter].setURangePf05(Float.parseFloat(outputData.get(i).get(3).replaceAll("[^0-9|.|-]", "")));
					}
					else if (outputData.get(i).get(2).equalsIgnoreCase("I-Range")) {
						operatingCurrent[counter].setIRangePf05(Float.parseFloat(outputData.get(i).get(3).replaceAll("[^0-9|.|-]", "")));
					}
					else if(outputData.get(i).get(2).equalsIgnoreCase("Evaluation")) {
						operatingCurrent[counter].setEvaluationPf05(outputData.get(i).get(3));
					}
				}
				else if (currAndPfAt1.contains(outputData.get(i).get(0).toLowerCase())) {
					if (outputData.get(i).get(2).equalsIgnoreCase("Meter Constant")) {
						operatingCurrent[counter].setMetertConstantPf1(Float.parseFloat(outputData.get(i).get(3).replaceAll("[^0-9|.|-]", "")));
					}
					else if (outputData.get(i).get(2).equalsIgnoreCase("Percentage Error")) {
						if (outputData.get(i).get(3).equals("---")) {
							operatingCurrent[counter].setPercentageErrorPf1(null);
						}
						else {
							operatingCurrent[counter].setPercentageErrorPf1(Float.parseFloat(outputData.get(i).get(3).replaceAll("[^0-9|.|-]", "")));
						}
					}
					else if (outputData.get(i).get(2).equalsIgnoreCase("U-Range")) {
						operatingCurrent[counter].setURangePf1(Float.parseFloat(outputData.get(i).get(3).replaceAll("[^0-9|.|-]", "")));
					}
					else if (outputData.get(i).get(2).equalsIgnoreCase("I-Range")) {
						operatingCurrent[counter].setIRangePf1(Float.parseFloat(outputData.get(i).get(3).replaceAll("[^0-9|.|-]", "")));
					}
					else if(outputData.get(i).get(2).equalsIgnoreCase("Evaluation")) {
						operatingCurrent[counter].setEvaluationPf1(outputData.get(i).get(3));
					}
				}
				else if (activeLoad.contains(outputData.get(i).get(0).toLowerCase())) {
					if (outputData.get(i).get(2).equalsIgnoreCase("Meter Constant")) {
						energyAndTamperClear[counter].setMetertConstant(Float.parseFloat(outputData.get(i).get(3).replaceAll("[^0-9|.|-]", "")));
					}
					else if (outputData.get(i).get(2).equalsIgnoreCase("U-Range")) {
						energyAndTamperClear[counter].setURange(Float.parseFloat(outputData.get(i).get(3).replaceAll("[^0-9|.|-]", "")));
					}
					else if (outputData.get(i).get(2).equalsIgnoreCase("I-Range")) {
						energyAndTamperClear[counter].setIRange(Float.parseFloat(outputData.get(i).get(3).replaceAll("[^0-9|.|-]", "")));
					}
					else if (outputData.get(i).get(2).equalsIgnoreCase("Percentage Error")) {
						if (outputData.get(i).get(3).equals("---")) {
							energyAndTamperClear[counter].setErrorAtResistiveLoad(null);
						}
						else {
							energyAndTamperClear[counter].setErrorAtResistiveLoad(Float.parseFloat(outputData.get(i).get(3).replaceAll("[^0-9|.|-]", "")));
						}
					}
					else if (outputData.get(i).get(2).equalsIgnoreCase("Evaluation")) {
						energyAndTamperClear[counter].setEvaluationAtResistiveLoad(outputData.get(i).get(3));
					}
				}
				else if (inductiveLoad.contains(outputData.get(i).get(0).toLowerCase())) {
					if (outputData.get(i).get(2).equalsIgnoreCase("Percentage Error")) {
						if (outputData.get(i).get(3).equals("---")) {
							energyAndTamperClear[counter].setErrorAtInductiveLoad(null);
						}
						else {
							energyAndTamperClear[counter].setErrorAtInductiveLoad(Float.parseFloat(outputData.get(i).get(3).replaceAll("[^0-9|.|-]", "")));						}
					}
					else if (outputData.get(i).get(2).equalsIgnoreCase("Evaluation")) {
						energyAndTamperClear[counter].setEvaluationAtInductiveLoad(outputData.get(i).get(3));
					}
				}
				else if (capacitiveLoad.contains(outputData.get(i).get(0).toLowerCase())) {
					if (outputData.get(i).get(2).equalsIgnoreCase("Percentage Error")) {
						if (outputData.get(i).get(3).equals("---")) {
							energyAndTamperClear[counter].setErrorAtCapacitiveLoad(null);
						}
						else {
							energyAndTamperClear[counter].setErrorAtCapacitiveLoad(Float.parseFloat(outputData.get(i).get(3).replaceAll("[^0-9|.|-]", "")));
						}
					}
					else if (outputData.get(i).get(2).equalsIgnoreCase("Evaluation")) {
						energyAndTamperClear[counter].setEvaluationAtCapacitiveLoad(outputData.get(i).get(3));
					}
				}
				i++;
			}
		}
		for (List<String> li: outputData) {
			System.out.println(li.toString());
		}
		persistData(testfile);
		for (int j = 0; j < MPS.size(); j++) {
			persistData(testBase[j]);
			persistData(meterTest[j]);
			persistData(meterAdj[j]);
			persistData(energyRegister[j]);
			persistData(calibration[j]);
			persistData(energyAndTamperClear[j]);
		}
	}

	public static void persistData(Object testBase) {
		Transaction transaction = null;
		try (Session session = HibernateUtil.getSessionFactory().openSession()) {
			// start a transaction
			transaction = session.beginTransaction();
			// save the student object
			session.persist(testBase);
			// commit transaction
			transaction.commit();
		} catch (Exception e) {
			if (transaction != null) {
				transaction.rollback();
			}
			e.printStackTrace();
		}
	}	
}
