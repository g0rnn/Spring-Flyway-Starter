package wap.springflyway.course;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
@Transactional(readOnly = true)
public class CourseService {

    private final CourseRepository courseRepository;

    public CourseService(CourseRepository courseRepository) {
        this.courseRepository = courseRepository;
    }

    public List<Course> findAll() {
        return courseRepository.findAll();
    }

    public Course findById(Long id) {
        return courseRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "강좌를 찾을 수 없습니다."));
    }

    @Transactional
    public Course create(CreateCourseRequest request) {
        validateRequest(request);
        Course course = new Course(request.title(), request.description(), request.capacity());
        return courseRepository.save(course);
    }

    private void validateRequest(CreateCourseRequest request) {
        if (request == null || !StringUtils.hasText(request.title())) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "강좌 제목은 필수입니다.");
        }

        if (request.capacity() == null || request.capacity() < 1) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "강좌 정원은 1명 이상이어야 합니다.");
        }
    }
}

