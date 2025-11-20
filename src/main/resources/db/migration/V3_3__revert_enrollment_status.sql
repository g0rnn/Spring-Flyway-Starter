ALTER TABLE enrollments
    MODIFY COLUMN status VARCHAR(20) NOT NULL;

UPDATE enrollments SET status = 'ENROLLED' WHERE status = 'ACTIVE';
UPDATE enrollments SET status = 'WAITING' WHERE status = 'PENDING';
UPDATE enrollments SET status = 'CANCELLED' WHERE status = 'DROPPED';

