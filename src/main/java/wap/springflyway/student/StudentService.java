package wap.springflyway.student;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
@Transactional(readOnly = true)
public class StudentService {

    private final StudentRepository studentRepository;

    public StudentService(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    public List<Student> findAll() {
        return studentRepository.findAll();
    }

    public Student findById(Long id) {
        return studentRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "학생을 찾을 수 없습니다."));
    }

    @Transactional
    public Student create(CreateStudentRequest request) {
        validateRequest(request);

        if (studentRepository.existsByEmail(request.email())) {
            throw new ResponseStatusException(HttpStatus.CONFLICT, "이미 등록된 이메일입니다.");
        }

        Student student = new Student(request.name(), request.email());
        return studentRepository.save(student);
    }

    private void validateRequest(CreateStudentRequest request) {
        if (request == null || !StringUtils.hasText(request.name())) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "학생 이름은 필수입니다.");
        }

        if (!StringUtils.hasText(request.email())) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "학생 이메일은 필수입니다.");
        }
    }
}

