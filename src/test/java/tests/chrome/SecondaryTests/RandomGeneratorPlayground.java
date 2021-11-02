package tests.chrome.SecondaryTests;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.jupiter.api.Test;

public class RandomGeneratorPlayground {

    protected final Logger logger = LogManager.getLogger(getClass());

    @Test //
    public void randomString() {
        logger.info("TEST - '" + new Throwable().getStackTrace()[0].getMethodName() + "' started");
        utils.RandomGenerator randomGenerator = new utils.RandomGenerator();
        String randomString;
        randomString = randomGenerator.generateStringUpperCase(10);
        System.out.println(randomString);
        logger.info("TEST SUCCESSFULLY COMPLETED");

    }

    @Test
    public void randomNumber() {
        logger.info("TEST - '" + new Throwable().getStackTrace()[0].getMethodName() + "' started");
        utils.RandomGenerator randomGenerator = new utils.RandomGenerator();
        System.out.println(randomGenerator.generateNumber(9));
        logger.info("TEST SUCCESSFULLY COMPLETED");
    }

    @Test
    public void randomBoolean() {
        logger.info("TEST - '" + new Throwable().getStackTrace()[0].getMethodName() + "' started");
        utils.RandomGenerator randomGenerator = new utils.RandomGenerator();
        System.out.println(randomGenerator.generateBoolean());
        logger.info("TEST SUCCESSFULLY COMPLETED");

    }
}
