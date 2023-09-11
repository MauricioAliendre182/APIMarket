package com.project.market.web.controller;

import com.project.market.domain.Purchase;
import com.project.market.domain.service.PurchaseService;
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

import java.util.List;

@RestController
@RequestMapping("/purchases")
@CrossOrigin(origins = "https://apimarket-production.up.railway.app/")
public class PurchaseController {
    @Autowired
    private PurchaseService purchaseService;

    @GetMapping("/all")
    @Operation(summary = "Get all supermarket purchases")
    @ApiResponse(responseCode = "200", description = "OK")
    public ResponseEntity<List<Purchase>> getAll() {
        return new ResponseEntity<>(purchaseService.getAll(), HttpStatus.OK);
    }

    @GetMapping("/client/{idClient}")
    @Operation(summary = "Search a purchase by client ID")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "OK"),
            @ApiResponse(responseCode = "404", description = "Purchase not found")
    })
    public ResponseEntity<List<Purchase>> getByClient(
            @Parameter(description = "The id of the client", required = true, example = "4546221")
            @PathVariable("idClient") String clientId
    ) {
        List<Purchase> purchases = purchaseService.getByClient(clientId).orElse(null);

        return purchases != null && !purchases.isEmpty() ?
                new ResponseEntity<>(purchases, HttpStatus.OK)
                : new ResponseEntity<List<Purchase>>(HttpStatus.NOT_FOUND);
    }

    @PostMapping("/save")
    @Operation(summary = "Create a purchase")
    @ApiResponse(responseCode = "201", description = "Created")
    public ResponseEntity<Purchase> save(@RequestBody Purchase purchase){
        return new ResponseEntity<>(purchaseService.save(purchase), HttpStatus.CREATED);
    }
}
