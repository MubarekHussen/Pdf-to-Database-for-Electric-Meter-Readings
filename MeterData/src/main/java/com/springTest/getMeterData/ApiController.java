package com.springTest.getMeterData;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

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
    
    // Get All NoLoadTestBase
    @GetMapping("/NoLoad")
    public List<NoLoadTest> getAllNoLoad()
    {
        return noload.findAll();
    }
    
    // Get All MeterTest
    @GetMapping("/MeterTest")
    public List<MeterTest> getAllMeterTest()
    {
        return meterTest.findAll();
    }
    
    // Get All EnergyRegisterTest
    @GetMapping("/EnergyRegisterTest")
    public List<EnergyRegisterTest> getAllEnergyRegisterTest()
    {
        return energy.findAll();
    }
    
    // Get All OperatingCurrent
    @GetMapping("/OperatingCurrent")
    public List<OperatingCurrentTest> getAllOperatingCurrent()
    {
        return operating.findAll();
    }
    
 // Get All StartingCurrent
    @GetMapping("/StartingCurrent")
    public List<StartingCurrentTest> getAllStartingCurrent()
    {
        return starting.findAll();
    }
    
    // Get All ScAdjustment
    @GetMapping("/ScAdjustment")
    public List<ScAdjustment> getAllScAdjustment()
    {
        return scadj.findAll();
    }
    
    // Get All TestFile
    @GetMapping("/TestFile")
    public List<TestFile> getAllTestFile()
    {
        return tetsFile.findAll();
    }
    
}