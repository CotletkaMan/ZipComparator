import zipcompare.report.Report;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;


public class ReportTest {
    private String[] testData;
    private Report report;

    @Before
    public void beginTest(){
        testData = new String[] {"One" , "Two" , "Third"};
    }

    @Test
    public void checkWorkReportTest(){
        report = new Report("TestMe");
        for(String msg : testData)
            report.addMessage(msg);
        Assert.assertTrue(report.size() == testData.length);
        Assert.assertTrue(report.get(1).equals(testData[1]));
    }
}
