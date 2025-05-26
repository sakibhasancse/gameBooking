package com.seu.javaFriday;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class StudentService {

    @Autowired
    private StudentRepo studentRepo;

    public Response createStudent(StudentCreateRequest request) {
        Student student = new Student();
        student.setName(request.getName());
        student.setEmail(request.getEmail());
//        student.setDateOfBirth(request.getDateOfBirth());
        student.setPhone(request.getPhone());
        student.setAddress(request.getAddress());
        student.setGender(request.getGender());
        student.setDepartment(request.getDepartment());
        student.setRegistrationNumber(request.getRegistrationNumber());

        Student savedStudent = studentRepo.save(student);

        Response response = new Response();
        if (savedStudent != null && savedStudent.getId() > 0) {
            response.setCode("0");
            response.setMsg("SUCCESS");
            response.setData(savedStudent);
        } else {
            response.setCode("1");
            response.setMsg("FAILED TO SAVE STUDENT");
        }

        return response;
    }

    public Response getStudentDetails(String registrationNumber) {
        Response response = new Response();
        Student student = studentRepo.findByRegistrationNumber(registrationNumber);

        if (student != null) {
            response.setCode("0");
            response.setMsg("SUCCESS");
            response.setData(student);
        } else {
            response.setCode("1");
            response.setMsg("STUDENT NOT FOUND");
        }

        return response;
    }

    public Response getAllStudents() {
        List<Student> students = studentRepo.findAll();
        Response response = new Response();

        if (students != null && !students.isEmpty()) {
            response.setCode("0");
            response.setMsg("SUCCESS");
            response.setData(students);
        } else {
            response.setCode("1");
            response.setMsg("NO STUDENTS FOUND");
        }

        return response;
    }

    public  Student readStudent(int id ) {
        Optional<Student> student = studentRepo.findById(id);
        if (student.isPresent()) {
            return  student.get();
        }
        return null;
    }

}
