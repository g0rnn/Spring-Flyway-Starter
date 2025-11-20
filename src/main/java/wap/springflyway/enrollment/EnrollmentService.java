package wap.springflyway.enrollment;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;
import wap.springflyway.course.Course;
import wap.springflyway.course.CourseService;
import wap.springflyway.student.Student;
import wap.springflyway.student.StudentService;

import java.util.List;

@Service
@Transactional(readOnly = true)
public class EnrollmentService {

    private final EnrollmentRepository enrollmentRepository;
    private final StudentService studentService;
    private final CourseService courseService;

    public EnrollmentService(EnrollmentRepository enrollmentRepository,
                             StudentService studentService,
                             CourseService courseService) {
        this.enrollmentRepository = enrollmentRepository;
        this.studentService = studentService;
        this.courseService = courseService;
    }

    @Transactional
    public Enrollment enroll(EnrollRequest request) {
        validateRequest(request);

        Student student = studentService.findById(request.studentId());
        Course course = courseService.findById(request.courseId());

        if (enrollmentRepository.existsByStudentIdAndCourseId(student.getId(), course.getId())) {
            throw new ResponseStatusException(HttpStatus.CONFLICT, "이미 수강 중입니다.");
        }

        long enrolledCount = enrollmentRepository.countByCourseId(course.getId());
        if (enrolledCount >= course.getCapacity()) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "강좌 정원이 가득 찼습니다.");
        }

        Enrollment enrollment = new Enrollment(student, course, EnrollmentStatus.ENROLLED);
        return enrollmentRepository.save(enrollment);
    }

    public List<Enrollment> findByStudent(Long studentId) {
        studentService.findById(studentId); // 존재 여부 확인
        return enrollmentRepository.findByStudentId(studentId);
    }

    private void validateRequest(EnrollRequest request) {
        if (request == null || request.studentId() == null) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "학생 ID는 필수입니다.");
        }

        if (request.courseId() == null) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "강좌 ID는 필수입니다.");
        }
    }
}

