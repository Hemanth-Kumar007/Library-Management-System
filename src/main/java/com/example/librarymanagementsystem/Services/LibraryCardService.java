package com.example.librarymanagementsystem.Services;

import com.example.librarymanagementsystem.Models.LibraryCard;
import com.example.librarymanagementsystem.Models.Student;
import com.example.librarymanagementsystem.Repositories.CardRepository;
import com.example.librarymanagementsystem.Repositories.StudentRepository;
import org.springframework.beans.factory.ObjectProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class LibraryCardService {

    @Autowired
    private CardRepository cardRepository;

    @Autowired
    private StudentRepository studentRepository;
    public String addCard(LibraryCard libraryCard){

        cardRepository.save(libraryCard);
        return "Card has been successfully added to the database";
    }

    public String associateToStudent(Integer cardNo, Integer rollNo) throws Exception{

        //validations
        //is student exist
        if(!studentRepository.existsById(rollNo)){
            throw new Exception("Student Id is invalid");
        }

        //is card exist
        if(!cardRepository.existsById(cardNo)){
            throw new Exception("Card No is invalid");
        }

        //setting foreign key variables
        Optional<Student> optional = studentRepository.findById(rollNo);
        Student student = optional.get();

        Optional<LibraryCard> optionalLibraryCard = cardRepository.findById(cardNo);
        LibraryCard libraryCard = optionalLibraryCard.get();

        //set the student object in card object
        libraryCard.setStudent(student);

        //since it is a bidirectional mapping
        //In the student object also we need to set the libraryCard object
        student.setCard(libraryCard);

        //any object that has been updated has to be saved --> Y/N

        studentRepository.save(student);

        //Card repository need not be saved
        //because student save will automatically trigger the card save function

        return "Student and card associated successfully";
    }
}
