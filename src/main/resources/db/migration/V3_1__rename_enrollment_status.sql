UPDATE enrollments SET status = 'ACTIVE' WHERE status = 'ENROLLED';
UPDATE enrollments SET status = 'PENDING' WHERE status = 'WAITING';
UPDATE enrollments SET status = 'DROPPED' WHERE status = 'CANCELLED';

