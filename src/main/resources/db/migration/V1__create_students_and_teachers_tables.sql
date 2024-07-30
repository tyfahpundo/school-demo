-- Create students table
CREATE TABLE students
(
    id                 BIGINT AUTO_INCREMENT PRIMARY KEY,
    uuid               VARCHAR(255) NOT NULL,
    first_name         VARCHAR(255) NOT NULL,
    last_name          VARCHAR(255) NOT NULL,
    age                INT          NOT NULL,
    sex                VARCHAR(50)  NOT NULL,
    created_by         VARCHAR(255),
    last_modified_by   VARCHAR(255),
    last_modified_date TIMESTAMP,
    created_date       TIMESTAMP,
    version            BIGINT,
    CONSTRAINT unique_student_uuid UNIQUE (uuid)
);

-- Create teachers table
CREATE TABLE teachers
(
    id                 BIGINT AUTO_INCREMENT PRIMARY KEY,
    uuid               VARCHAR(255) NOT NULL,
    first_name         VARCHAR(255) NOT NULL,
    last_name          VARCHAR(255) NOT NULL,
    age                INT          NOT NULL,
    sex                VARCHAR(50)  NOT NULL,
    created_by         VARCHAR(255),
    last_modified_by   VARCHAR(255),
    last_modified_date TIMESTAMP,
    created_date       TIMESTAMP,
    version            BIGINT,
    CONSTRAINT unique_teacher_uuid UNIQUE (uuid)
);
