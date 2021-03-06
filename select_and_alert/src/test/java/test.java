import Utilities.ExcelUtils;
import Utilities.Utility;
import common_lib.browser;
import element.selectors;
import input.data;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
//import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.util.List;
import java.util.Set;

public class test {
    data d = new data();
    selectors se = new selectors();
    browser brw = new browser();
//    JavascriptExecutor js = (JavascriptExecutor) brw.driver;

    @BeforeClass
    public void openBrowser() {

        //BrowserSetup
        brw.chromeBrowser(d.baseUrl);

    }

    @Test(priority = 1, description = "test",enabled = false)
    public void selctors(){
        try {

            // Drop Down/Selector Handling
            WebElement sel_box = brw.driver.findElement(By.id(se.sel_id));
            Select objSelect = new Select(sel_box);

            List<WebElement> elementCount = objSelect.getOptions();
            int size = elementCount.size();
            System.out.println("\n=================\nAll Options \n===========");
            for (int i = 0; i < size; i++) {
                String options = elementCount.get(i).getText();
                int n = i + 1;
                System.out.println(n + "." + options);
            }
            objSelect.selectByVisibleText(se.sel_txt);
            objSelect.selectByValue(se.sel_val);
            objSelect.selectByIndex(se.sel_ind);
            String first_opt = objSelect.getFirstSelectedOption().getText();
            System.out.println("First selected option: " + first_opt);
            List<WebElement> all_opt = objSelect.getAllSelectedOptions();
            int size1 = all_opt.size();
            System.out.println("\n=================\nAll Selected Options \n===========");
            for (int i = 0; i < size1; i++) {
                String options = all_opt.get(i).getText();
                int n = i + 1;
                System.out.println(n + "." + options);
            }
//            Deselect options
            objSelect.deselectByVisibleText(se.sel_txt);
//            System.out.println("Done");
            objSelect.deselectByIndex(se.sel_ind);
//            System.out.println("Done");
            List<WebElement> all_opt_desel = objSelect.getAllSelectedOptions();
            int size2 = all_opt_desel.size();
            System.out.println("\n=================\nAll Selected Options after deselect \n===========");
            for (int i = 0; i < size2; i++) {
                String desel_options = all_opt_desel.get(i).getText();
                int n = i + 1;
                System.out.println(n + "." + desel_options);
            }
        }catch (Exception e){
            System.out.println("Exception found at Select:" +e);
        }
    }

    //      Popup Handling
    @Test(priority = 2, description = "Popup Handling",enabled = false)
    public void alerts() {
        try {
            // Prompt Handling
            System.out.println("++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++");
            WebElement prompt = brw.driver.findElement(By.xpath(se.promp));
            prompt.click();
            Alert prom = brw.driver.switchTo().alert();
            prom.sendKeys(d.al_mess);
            prom.accept();
            System.out.println("Prompt check success");
            //Alert Handling
            System.out.println("\n=================\nAlert Test \n=================");
            WebElement ale = brw.driver.findElement(By.xpath(se.aler));
            ale.click();
            Alert alert = brw.driver.switchTo().alert();
            String alrt_txt = alert.getText();
            System.out.println("Alert message is :" + alrt_txt);
            alert.accept();
            //Confirmation box handling
            System.out.println("\n============================\nConfirm Box Test \n==========================");
            WebElement cnf = brw.driver.findElement(By.xpath(se.conf));
            cnf.click();
            Alert confrm = brw.driver.switchTo().alert();
            String conf_txt = confrm.getText();
            System.out.println("Confirm message is :" + conf_txt);
            confrm.accept();
        } catch (Exception e) {
            System.out.println("Exception found :" + e);
        }
    }
    //Frame Handling
    @Test(priority = 3, description = "Frame Handling",enabled = false)
    public void frame(){
        try {
            System.out.println("+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++");
            WebElement ifram = brw.driver.findElement(By.xpath(se.frame_xpath));
            brw.driver.switchTo().frame(ifram);
            brw.driver.findElement(By.xpath(se.iframe_play)).click();
            System.out.println("Inside iframe");
            brw.driver.switchTo().defaultContent();
            System.out.println("Inside Mainframe");

        }catch (Exception e){
            System.out.println("Exception found at Popup:" +e);
        }
    }
    @Test(priority = 4, description = "Window Handling", enabled = false)
    public void window(){
        try {
            //Scroll
//            String home_window = brw.driver.getWindowHandle();
            WebElement nw_pg_link = brw.driver.findElement(By.linkText(se.new_page));
            nw_pg_link.click();
            Set<String> all_windows = brw.driver.getWindowHandles();
            for (String window : all_windows)
            {
                brw.driver.switchTo().window(window);
                if (brw.driver.getTitle().contains("page 3"))
                {
                    Utility.screenShot(brw.driver,"page_3");
                    WebElement content = brw.driver.findElement(By.xpath(se.page3_contnt));
                    System.out.println(content.getText());
                    System.out.println("Control in new window");
                }
            }
            for (String window : all_windows)
            {
                brw.driver.switchTo().window(window);
                if (brw.driver.getTitle().contains("Document"))
                {
                    System.out.println("Control back to home window");
                }
            }
        }catch (Exception e){
            System.out.println("Exception found at Windows:" +e);
        }
    }

    @Test(priority = 5, dataProvider = "Login_credentials", description = "DataProvider")
    public void data(String fname, String mname, String lname){

        try{

            brw.driver.navigate().to("https://www.jotform.com/build/212533869664466#preview");

            brw.driver.findElement(By.xpath(se.fname)).sendKeys(fname);
            brw.driver.findElement(By.xpath(se.mname)).sendKeys(mname);
            brw.driver.findElement(By.xpath(se.lname)).sendKeys(lname);
            Utility.screenShot(brw.driver,"page_3"+lname);

        }catch (Exception e){
            System.out.println("Exception found at Data Provider :" +e);
        }


    }

    @DataProvider(name = "names")
    public Object[][] data(){

        Object[][] names = new Object[2][3];

        names[0][0] = "qwe";
        names[0][1] = "rty";
        names[0][2] = "uio";

        names[1][0] = "asd";
        names[1][1] = "fgh";
        names[1][2] = "jkl";

        return names;

    }
    @DataProvider(name = "Login_credentials")
    public Object[][] methodName(){
        ExcelUtils excelUtils= new ExcelUtils("C:\\Users\\user\\IdeaProjects\\select_and_alert\\src\\main\\java\\testData\\TestData.xlsx"); // create an object of ExcelUtils class. ie, we created.
        int rows=excelUtils.getRowCount(0); // pass sheet index to get num of rows
        Object[][] data=new Object[rows][3]; // create an object array to values we read from excel sheet.
        for (int i=0;i<rows;i++)
        {
            data[i][0]=excelUtils.getData(0,i,0); // getData
            data[i][1]=excelUtils.getData(0,i, 1);
            data[i][2]=excelUtils.getData(0,i, 2);
        }
        return data;
    }

    @AfterClass
    public void closeBrowser(){

        brw.driver.close();
        brw.driver.quit();

    }
}
