package wap.springflyway.enrollment;

public record EnrollRequest(
        Long studentId,
        Long courseId
) {
}

