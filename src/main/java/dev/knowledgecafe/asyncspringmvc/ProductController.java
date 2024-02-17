package dev.knowledgecafe.asyncspringmvc;

import io.micrometer.core.annotation.Timed;
import io.micrometer.core.instrument.MeterRegistry;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/")
public class ProductController {

    private final ProductService productService;

    private final MeterRegistry registry;

    public ProductController(MeterRegistry registry, ProductService productService) {
        this.registry = registry;
        this.productService = productService;
    }

    @Timed(value="getProductName.duration", description="Response time of get Product Name endpoint")
    @GetMapping("/product")
    public String getProductName(@RequestParam("id") Integer id){
        String productName = productService.getProductName(id);
        return ((productName != null) ? productName : "No Product Found");
    }

    @Timed(value="getProductDetails.duration", description="Response time of get Product Details endpoint")
    @GetMapping("/productasync")
    public String getProductDetails(@RequestParam("id") Integer id){
        String productDetails = productService.getProductDetails(id);
        return ((productDetails != null) ? productDetails : "No Product Found");
    }

}
