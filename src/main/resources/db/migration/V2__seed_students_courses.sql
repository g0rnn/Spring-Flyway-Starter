INSERT INTO students (name, email) VALUES
('김철수', 'chulsoo@example.com'),
('이영희', 'younghee@example.com'),
('박민수', 'minsu@example.com');

INSERT INTO courses (title, description, capacity) VALUES
('자료구조', '기본적인 자료구조와 알고리즘을 다룹니다.', 30),
('데이터베이스', '관계형 DB와 SQL 기초를 학습합니다.', 25),
('웹프로그래밍', 'Spring 기반의 웹 개발을 실습합니다.', 20);

INSERT INTO enrollments (student_id, course_id, status) VALUES
(1, 1, 'ENROLLED'),
(1, 2, 'ENROLLED'),
(2, 2, 'ENROLLED'),
(3, 3, 'ENROLLED');

