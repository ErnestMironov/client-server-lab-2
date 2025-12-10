package ru.mironov.dataservice.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;
import ru.mironov.data.api.StudentDataApi;
import ru.mironov.data.model.StudentDataCreateRequest;
import ru.mironov.data.model.StudentDataResponse;
import ru.mironov.dataservice.data.Student;
import ru.mironov.dataservice.data.StudentRepository;

@RestController
@RequiredArgsConstructor
public class StudentController implements StudentDataApi {

    private final StudentRepository studentRepository;

    @Override
    public ResponseEntity<StudentDataResponse> createStudentDataInData(StudentDataCreateRequest request) {
        Student student = new Student();
        student.setName(request.getFullName());
        student.setPassport(request.getPassport());
        studentRepository.save(student);

        StudentDataResponse response = new StudentDataResponse();
        response.setId(student.getId());
        response.setFullName(student.getName());
        response.setPassport(student.getPassport());

        return ResponseEntity.status(200).body(response);
    }

    @Override
    public ResponseEntity<StudentDataResponse> getStudentDataByIdFromData(Long id) {
        return studentRepository.findById(id)
                .map(student -> {
                    StudentDataResponse response = new StudentDataResponse();
                    response.setId(student.getId());
                    response.setFullName(student.getName());
                    response.setPassport(student.getPassport());
                    return ResponseEntity.status(200).body(response);
                })
                .orElse(ResponseEntity.notFound().build());
    }
}
