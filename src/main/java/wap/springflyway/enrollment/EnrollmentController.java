package wap.springflyway.enrollment;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/enrollments")
public class EnrollmentController {

    private final EnrollmentService enrollmentService;

    public EnrollmentController(EnrollmentService enrollmentService) {
        this.enrollmentService = enrollmentService;
    }

    @PostMapping
    public ResponseEntity<EnrollmentResponse> enroll(@RequestBody EnrollRequest request) {
        Enrollment enrollment = enrollmentService.enroll(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(EnrollmentResponse.from(enrollment));
    }

    @GetMapping("/student/{studentId}")
    public List<EnrollmentResponse> getEnrollments(@PathVariable Long studentId) {
        return enrollmentService.findByStudent(studentId)
                .stream()
                .map(EnrollmentResponse::from)
                .toList();
    }
}

