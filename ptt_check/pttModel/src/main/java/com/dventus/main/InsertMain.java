package com.dventus.main;

import java.math.BigInteger;

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

public class InsertMain {

	public static void main(String[] args) {
		TestBase testBase = new TestBase();
		MeterTest meterTest = new MeterTest();
		MeterAdj meterAdj = new MeterAdj(meterTest);
		CalibrationTest calibration = new CalibrationTest(testBase);
		StartingCurrentTest startingCurrent = new StartingCurrentTest(testBase);
		ShuntImpedanceTest shuntImpedance = new ShuntImpedanceTest(testBase);
		NeutralImpedanceTest neutralImpedance = new NeutralImpedanceTest(testBase);
		EnergyRegisterTest energyRegister = new EnergyRegisterTest(testBase);
		ScAdjustment scAdjustment = new ScAdjustment(testBase);
		EnergyAndTamperClearTest energyAndTamperClear = new EnergyAndTamperClearTest(testBase);
		OperatingCurrentTest operatingCurrent = new OperatingCurrentTest(testBase);
		NoLoadTest noLoadTest = new NoLoadTest(testBase);
		
		calibration.setElo(172967);
		testBase.setTestEndTime("05/04/2022 11:36:50");
		testBase.setTestPeronnel("Yossef");
		persistData(testBase);
		persistData(calibration);
		persistData(startingCurrent);
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
