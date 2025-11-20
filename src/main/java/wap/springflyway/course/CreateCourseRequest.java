package wap.springflyway.course;

public record CreateCourseRequest(
        String title,
        String description,
        Integer capacity
) {
}

