package dev.knowledgecafe.asyncspringmvc;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

@Service
public class ProductService {

    private static final org.apache.logging.log4j.Logger asyncLogger = LogManager.getLogger(ProductService.class);
    private static final Logger syncLogger = LogManager.getLogger("syncLogger");

   private static Map<Integer, String> productMap = new HashMap<>();
   static {
       productMap.put(1, "Product one");
       productMap.put(2, "Apple Phone");
       productMap.put(3, "Samsung Phone");
       productMap.put(4, "Macbook pro laptop");
       productMap.put(5, "Macbook air laptop");
       productMap.put(6, "Lenovo pro laptop");
       productMap.put(7, "keyboard and mouse combo");
   }


    public String getProductName (Integer productID){
        syncLogger.info("Retrieving Product Name using ID "+ productID);
        //Adding sleep time to mimic DB reading time
        int sleepTime = 5;

        try {
            TimeUnit.MILLISECONDS.sleep(sleepTime);
        } catch (InterruptedException e) {
            syncLogger.error( e.getMessage());
        }
        String productName = productMap.get(productID);
        syncLogger.info("Returning product name " + productName);
        return productName;
    }

    public String getProductDetails (Integer productID){
        asyncLogger.info("Retrieving Product Details using ID " + productID);
        //Adding sleep time to mimic DB reading time
        int sleepTime = 5;

        try {
            TimeUnit.MILLISECONDS.sleep(sleepTime);
        } catch (InterruptedException e) {
            asyncLogger.error( e.getMessage());
        }
        String productDetails = productMap.get(productID);
        asyncLogger.info("Returning product Details "+  productDetails);
        return productDetails;
    }
}
