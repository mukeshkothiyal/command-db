CREATE TABLE Departments
(
    Code   INTEGER PRIMARY KEY NOT NULL,
    Name   NVARCHAR            NOT NULL,
    Budget REAL                NOT NULL
);

CREATE TABLE Employees
(
    SSN        INTEGER PRIMARY KEY NOT NULL,
    Name       TEXT                NOT NULL,
    LastName   VARCHAR             NOT NULL,
    Department INTEGER             NOT NULL,
    CONSTRAINT fk_Departments_Code FOREIGN KEY (Department)
        REFERENCES Departments (Code)
);