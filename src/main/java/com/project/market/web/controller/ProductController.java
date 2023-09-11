package com.project.market.web.controller;

import com.project.market.domain.dto.Product;
import com.project.market.domain.service.ProductService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.DeleteMapping;

import java.util.List;

@RestController
@RequestMapping("/products")
@CrossOrigin(originPatterns = "https://apimarket-production.up.railway.app/*")
public class ProductController {
    @Autowired
    private ProductService productService;

    @GetMapping()
    @Operation(summary = "Get all supermarket products")
    @ApiResponse(responseCode = "200", description = "OK")
    public ResponseEntity<List<Product>> getAll() {
        return new ResponseEntity<>(productService.getAll(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    @Operation(summary = "Search a product with ID")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "OK"),
            @ApiResponse(responseCode = "404", description = "Product not found")
    })
    public ResponseEntity<Product> getProduct(
            @Parameter(description = "The id of the product", required = true, example = "7")
            @PathVariable("id") int productId
    ) {
        return productService.getProduct(productId)
                .map(product -> new ResponseEntity<>(product, HttpStatus.OK))
                .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @GetMapping("/category/{categoryId}")
    @Operation(summary = "Search a product by the category ID")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "OK"),
            @ApiResponse(responseCode = "404", description = "Product not found")
    })
    public ResponseEntity<List<Product>> getByCategory(
            @Parameter(description = "The id of the category", required = true, example = "3")
            @PathVariable("categoryId") int categoryId
    ) {
        List<Product> products = productService.getByCategory(categoryId).orElse(null);

        return products != null && !products.isEmpty() ?
                new ResponseEntity<>(products, HttpStatus.OK)
                : new ResponseEntity<List<Product>>(HttpStatus.NOT_FOUND);
    }
//        return productService.getByCategory(categoryId)
//                .map(products -> new ResponseEntity<>(products, HttpStatus.OK))
//                .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
//    }

    @PostMapping("/save")
    @Operation(summary = "Create a product")
    @ApiResponse(responseCode = "201", description = "Created")
    public ResponseEntity<Product> save(
            @RequestBody Product product
    ) {
        return new ResponseEntity<>(productService.save(product), HttpStatus.CREATED);
    }

    @DeleteMapping("/delete/{id}")
    @Operation(summary = "Delete a product by ID")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "OK"),
            @ApiResponse(responseCode = "404", description = "Product not found")
    })
    public ResponseEntity delete(
           @Parameter(description = "The id of the product", required = true, example = "7")
           @PathVariable("id") int productId
    ) {
        if (productService.delete(productId)) {
            return new ResponseEntity(HttpStatus.OK);
        } else {
            return new ResponseEntity(HttpStatus.NOT_FOUND);
        }
    }
}
