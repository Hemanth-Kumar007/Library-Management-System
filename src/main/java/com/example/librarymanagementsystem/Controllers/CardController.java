package com.example.librarymanagementsystem.Controllers;

import com.example.librarymanagementsystem.Models.LibraryCard;
import com.example.librarymanagementsystem.Services.LibraryCardService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/card")
@Slf4j
public class CardController {

    @Autowired
    private LibraryCardService libraryCardService;
    @PostMapping("/create")
    public String addCard(@RequestBody LibraryCard card){
        return libraryCardService.addCard(card);
    }

    @PutMapping("/issueToStudent")
    public ResponseEntity issueToStudent(@RequestParam("cardId")Integer cardId, @RequestParam("rollNo")Integer rollNo){

        try{
            String result = libraryCardService.associateToStudent(cardId, rollNo);
            return new ResponseEntity(result, HttpStatus.OK);
        }catch(Exception e){
            log.error("Error in associating card to student", e.getMessage());
            return new ResponseEntity(e.getMessage(), HttpStatus.EXPECTATION_FAILED);
        }
    }
}
