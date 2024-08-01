package com.example.exam2.Controller;

import com.example.exam2.ApiResponse.SApiResponse;
import com.example.exam2.Model.Student;
import com.example.exam2.Service.SService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/student")
@RequiredArgsConstructor
public class SController {

    private final SService sService;

    @GetMapping("/get")
    public ResponseEntity getStudents()
    {
        return ResponseEntity.status(200).body(sService.getStudents());
    }
    @PostMapping("/add")
    public ResponseEntity addStudent(@Valid@RequestBody Student student, Errors errors)
    {
        if(errors.hasErrors())
        {
            String message = errors.getFieldError().getDefaultMessage();
            return ResponseEntity.status(400).body(message);
        }
        sService.addStudent(student);
        return ResponseEntity.status(200).body(new SApiResponse("student added"));
    }
    @PutMapping("/update/{id}")
    public ResponseEntity updateStudent(@PathVariable String id,@Valid@RequestBody Student student,Errors errors)
    {
        if(errors.hasErrors())
        {
            String message = errors.getFieldError().getDefaultMessage();
            return ResponseEntity.status(400).body(message);
        }
        if(sService.updateStudent(id, student))
        {
            return ResponseEntity.status(200).body(new SApiResponse("student updated"));
        }
        return ResponseEntity.status(400).body(new SApiResponse("not found"));
    }
    @DeleteMapping("/delete/{id}")
    public ResponseEntity deleteStudent(@PathVariable String id)
    {
        if(sService.deleteStudent(id))
        {
            return ResponseEntity.status(200).body(new SApiResponse("student deleted"));
        }
        return ResponseEntity.status(400).body(new SApiResponse("not found"));
    }
    @GetMapping("/get-by-name/{name}")
    public ResponseEntity getByName(@PathVariable String name)
    {
        if(sService.getByName(name) == null)
        {
            return ResponseEntity.status(400).body(new SApiResponse("not found"));
        }
        return ResponseEntity.status(200).body(sService.getByName(name));
    }
    @GetMapping("/get-by-major/{major}")
    public ResponseEntity getByMajor(@PathVariable String major)
    {
        if(sService.getAllByMajor(major).isEmpty())
        {
            return ResponseEntity.status(400).body(new SApiResponse("not found"));
        }
        return ResponseEntity.status(200).body(sService.getAllByMajor(major));
    }
}