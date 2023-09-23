package com.project.market.web.controller;

import com.project.market.domain.service.UserService;
import com.project.market.persistence.entities.DomainUser;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user")
public class UserController {
    @Autowired
    private UserService userService;

    @GetMapping("/{id}")
    @Operation(summary = "Search a user with ID")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "OK"),
            @ApiResponse(responseCode = "404", description = "User not found")
    })
    public ResponseEntity<DomainUser> getClient(
            @Parameter(description = "The id of the user", required = true, example = "2")
            @PathVariable("id") Integer idUser
    ) {
        return userService.getUser(idUser)
                .map(client -> new ResponseEntity<>(client, HttpStatus.OK))
                .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @PostMapping("/signup")
    @Operation(summary = "Create a user")
    @ApiResponse(responseCode = "201", description = "Created")
    public ResponseEntity<DomainUser> save(
            @RequestBody DomainUser domainUser
    ) {
        return new ResponseEntity<>(userService.save(domainUser), HttpStatus.CREATED);
    }

    @DeleteMapping("/delete/{id}")
    @Operation(summary = "Delete a user with ID")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "OK"),
            @ApiResponse(responseCode = "404", description = "User not found")
    })
    public ResponseEntity delete(
            @Parameter(description = "The id of the user", required = true, example = "2")
            @PathVariable("id") Integer idUser
    ) {
        if (userService.delete(idUser)) {
            return new ResponseEntity(HttpStatus.OK);
        } else {
            return new ResponseEntity(HttpStatus.NOT_FOUND);
        }
    }
}
