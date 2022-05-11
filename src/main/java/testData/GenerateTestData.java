package testData;

import org.testng.annotations.Test;

public class GenerateTestData {

    @Test
    public void getTestData(){
        System.out.println(TestDataGenerator.generateRandomEmail(5));
        System.out.println(TestDataGenerator.generateRandomEmail(10));
    }
}

