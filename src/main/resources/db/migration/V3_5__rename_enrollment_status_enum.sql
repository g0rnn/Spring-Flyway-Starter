ALTER TABLE enrollments
    MODIFY COLUMN status VARCHAR(20) NOT NULL;

UPDATE enrollments SET status = 'ACTIVATE' WHERE status = 'ENROLLED';
UPDATE enrollments SET status = 'PENDING' WHERE status = 'WAITING';
UPDATE enrollments SET status = 'DROPPED' WHERE status = 'CANCELLED';

ALTER TABLE enrollments
    MODIFY COLUMN status ENUM('ACTIVATE', 'PENDING', 'DROPPED') NOT NULL;

