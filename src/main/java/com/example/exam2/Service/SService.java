package com.example.exam2.Service;

import com.example.exam2.Model.Student;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class SService {

    ArrayList<Student> students = new ArrayList<>();

    public ArrayList<Student> getStudents() {
        return students;
    }

    public void addStudent(Student student)
    {
        students.add(student);
    }

    public boolean updateStudent(String id,Student student)
    {
        for (int i = 0; i < students.size(); i++) {
            if(students.get(i).getId().equals(id))
            {
                students.set(i,student);
                return true;
            }
        }
        return false;
    }

    public boolean deleteStudent(String id)
    {
        for (int i = 0; i < students.size(); i++) {
            if(students.get(i).getId().equals(id))
            {
                students.remove(i);
                return true;
            }
        }
        return false;
    }

    public Student getByName(String name)
    {
        for (int i = 0; i < students.size(); i++) {
            if(students.get(i).getName().equalsIgnoreCase(name))
            {
                return students.get(i);
            }
        }
        return null;
    }

    public ArrayList<Student> getAllByMajor(String major)
    {
        ArrayList<Student> students1 = new ArrayList<>();
        for (int i = 0; i < students.size(); i++) {
            if(students.get(i).getMajor().equalsIgnoreCase(major))
            {
                students1.add(students.get(i));
            }
        }
        return students1;
    }
}
