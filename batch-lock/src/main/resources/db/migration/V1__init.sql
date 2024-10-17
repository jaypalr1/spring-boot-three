-- CREATE SCHEMA IF NOT EXISTS shedlock_schema;

CREATE TABLE IF NOT EXISTS shedlock
(
    name       VARCHAR(255) PRIMARY KEY,
    lock_until TIMESTAMP(3) NULL,
    locked_at  TIMESTAMP(3) NULL,
    locked_by  VARCHAR(255)
)
