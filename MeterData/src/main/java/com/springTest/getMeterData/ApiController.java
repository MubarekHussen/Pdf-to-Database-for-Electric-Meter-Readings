package com.springTest.getMeterData;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin
@RestController
public class ApiController {
    @Autowired
    private TestBaseRepo testbase;
    @Autowired
    private NoLoadRepo noload;
    @Autowired
    private MeterTestRepo meterTest;
    @Autowired
    private OperatingCurrentRepo operating;
    @Autowired
    private EnergyRegisterRepo energy;
    @Autowired
    private StartingCurrentRepo starting;
    @Autowired
    private ScAdjustmentRepo scadj;
    @Autowired
    private TestFileRepo tetsFile;
    @Autowired
    private CalibrationTestRepo calibration;
    // Home Page
    @GetMapping("/")
    public String welcome()
    {
        return "<html><body>"
            + "<h1>WELCOME</h1>"
            + "</body></html>";
    }
  
    // Get All TestBase
    @GetMapping("/TestBase")
    public List<TestBase> getAllTestBase()
    {
        return testbase.findAll();
    }
    
    @GetMapping("/TestBase/frontCoverSn/{frontCoverSn}")
    public Optional<TestBase> findByLastName(@PathVariable String frontCoverSn) {
        return testbase.findByfrontCoverSn(frontCoverSn);
    }
    
    // Get All NoLoad
    @GetMapping("/NoLoad")
    public List<NoLoadTest> getAllNoLoad()
    {
        return noload.findAll();
    }
    
    @GetMapping("/NoLoad/hjid/{hjid}")
    public Optional<NoLoadTest> findByNoLoadhjid(@PathVariable Long hjid) {
        return noload.findByhjid(hjid);
    }
    
    // Get All MeterTest
    @GetMapping("/MeterTest")
    public List<MeterTest> getAllMeterTest()
    {
        return meterTest.findAll();
    }
    
    @GetMapping("/MeterTest/hjid/{hjid}")
    public Optional<MeterTest> findByMeterTesthjid(@PathVariable Long hjid) {
        return meterTest.findByhjid(hjid);
    }
    
    // Get All EnergyRegisterTest
    @GetMapping("/EnergyRegisterTest")
    public List<EnergyRegisterTest> getAllEnergyRegisterTest()
    {
        return energy.findAll();
    }
    
    @GetMapping("/EnergyRegisterTest/hjid/{hjid}")
    public Optional<EnergyRegisterTest> findByEnergyRegisterhjid(@PathVariable Long hjid) {
        return energy.findByhjid(hjid);
    }
    
    // Get All OperatingCurrent
    @GetMapping("/OperatingCurrent")
    public List<OperatingCurrentTest> getAllOperatingCurrent()
    {
        return operating.findAll();
    }
    
    @GetMapping("/OperatingCurrent/hjid/{hjid}")
    public Optional<OperatingCurrentTest> findByOperatingCurrenthjid(@PathVariable Long hjid) {
        return operating.findByhjid(hjid);
    }
    
 // Get All StartingCurrent
    @GetMapping("/StartingCurrent")
    public List<StartingCurrentTest> getAllStartingCurrent()
    {
        return starting.findAll();
    }
    
    @GetMapping("/StartingCurrent/hjid/{hjid}")
    public Optional<StartingCurrentTest> findByStartingCurrenthjid(@PathVariable Long hjid) {
        return starting.findByhjid(hjid);
    }
    
    // Get All ScAdjustment
    @GetMapping("/ScAdjustment")
    public List<ScAdjustment> getAllScAdjustment()
    {
        return scadj.findAll();
    }
    
    @GetMapping("/ScAdjustment/hjid/{hjid}")
    public Optional<ScAdjustment> findByScAdjustmenthjid(@PathVariable Long hjid) {
        return scadj.findByhjid(hjid);
    }
    
    // Get All TestFile
    @GetMapping("/TestFile")
    public List<TestFile> getAllTestFile()
    {
        return tetsFile.findAll();
    }
    
    @GetMapping("/TestFile/fileId/{fileId}")
    public Optional<TestFile> findBytetsFilehjid(@PathVariable String fileId) {
        return tetsFile.findByfileId(fileId);
    }
    
    // Get All CalibrationTest
    @GetMapping("/CalibrationTest")
    public List<CalibrationTest> getAllCalibrationTest()
    {
        return calibration.findAll();
    }
    
    @GetMapping("/CalibrationTest/hjid/{hjid}")
    public Optional<CalibrationTest> findByCalibrationTesthjid(@PathVariable Long hjid) {
        return calibration.findByhjid(hjid);
    }
    
}