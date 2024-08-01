package com.example.exam2.Controller;

import com.example.exam2.ApiResponse.SApiResponse;
import com.example.exam2.ApiResponse.TApiResponse;
import com.example.exam2.Model.Student;
import com.example.exam2.Model.Teacher;
import com.example.exam2.Service.TService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/teacher")
@RequiredArgsConstructor
public class TController {

    private final TService tService;

    @GetMapping("/get")
    public ResponseEntity getTeacher()
    {
        return ResponseEntity.status(200).body(tService.getTeachers());
    }
    @PostMapping("/add")
    public ResponseEntity addTeacher(@Valid @RequestBody Teacher teacher, Errors errors)
    {
        if(errors.hasErrors())
        {
            String message = errors.getFieldError().getDefaultMessage();
            return ResponseEntity.status(400).body(message);
        }
        tService.addTeacher(teacher);
        return ResponseEntity.status(200).body(new TApiResponse("student added"));
    }
    @PutMapping("/update/{id}")
    public ResponseEntity updateTeacher(@PathVariable String id,@Valid@RequestBody Teacher teacher,Errors errors)
    {
        if(errors.hasErrors())
        {
            String message = errors.getFieldError().getDefaultMessage();
            return ResponseEntity.status(400).body(message);
        }
        if(tService.updateTeacher(id, teacher))
        {
            return ResponseEntity.status(200).body(new TApiResponse("student updated"));
        }
        return ResponseEntity.status(400).body(new TApiResponse("not found"));
    }
    @DeleteMapping("/delete/{id}")
    public ResponseEntity deleteTeacher(@PathVariable String id)
    {
        if(tService.deleteTeacher(id))
        {
            return ResponseEntity.status(200).body(new TApiResponse("student deleted"));
        }
        return ResponseEntity.status(400).body(new TApiResponse("not found"));
    }
    @GetMapping("/get-by-id/{id}")
    public ResponseEntity getByID(@PathVariable String id)
    {
        if(tService.getByID(id) == null)
        {
            return ResponseEntity.status(400).body(new TApiResponse("not found"));
        }
        return ResponseEntity.status(200).body(tService.getByID(id));
    }
    @GetMapping("/get-by-salary/{salary}")
    public ResponseEntity getBySalary(@PathVariable double salary)
    {
        if(tService.getBySalary(salary).isEmpty())
        {
            return ResponseEntity.status(400).body(new TApiResponse("not found"));
        }
        return ResponseEntity.status(200).body(tService.getBySalary(salary));
    }
}
