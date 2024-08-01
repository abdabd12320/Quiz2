package com.example.exam2.Service;

import com.example.exam2.Model.Student;
import com.example.exam2.Model.Teacher;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class TService {

    ArrayList<Teacher> teachers = new ArrayList<>();

    public ArrayList<Teacher> getTeachers() {
        return teachers;
    }

    public void addTeacher(Teacher teacher)
    {
        teachers.add(teacher);
    }

    public boolean updateTeacher(String id,Teacher teacher)
    {
        for (int i = 0; i < teachers.size(); i++) {
            if(teachers.get(i).getId().equals(id))
            {
                teachers.set(i,teacher);
                return true;
            }
        }
        return false;
    }

    public boolean deleteTeacher(String id)
    {
        for (int i = 0; i < teachers.size(); i++) {
            if(teachers.get(i).getId().equals(id))
            {
                teachers.remove(i);
                return true;
            }
        }
        return false;
    }

    public Teacher getByID(String id)
    {
        for (int i = 0; i < teachers.size(); i++) {
            if(teachers.get(i).getId().equals(id))
            {
                return teachers.get(i);
            }
        }
        return null;
    }

    public ArrayList<Teacher> getBySalary(double salary)
    {
        ArrayList<Teacher> teachers1 = new ArrayList<>();
        for (int i = 0; i < teachers.size(); i++) {
            if(teachers.get(i).getSalary() >= salary)
            {
                teachers1.add(teachers.get(i));
            }
        }
        return teachers1;
    }
}
