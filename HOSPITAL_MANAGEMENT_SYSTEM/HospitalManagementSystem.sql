create database HospitalManagementSystem;
use HospitalManagementSystem;

create table patients(
	patientId int primary key auto_increment,
    firstName varchar(100) not null,
    lastName varchar(100) not null,
    dateOfBirth date not null,
    gender enum('Male','Female','Other') not null,
    contactNumber varchar(15) not null,
    address varchar(255) not null
    );
    
create table doctors(
	doctorId int primary key auto_increment,
    firstName varchar(100) not null,
    lastName varchar(100)not null,
    specialization varchar(200) not null,
    contactNumber varchar(200) not null
);

create table appointments(
	appointmentId int primary key auto_increment,
    patientId int not null,
    doctorId int not null,
    appointmentDate date not null,
    description text,
    foreign key (patientId) references patients(patientId) on delete cascade,
    foreign key (doctorId) references doctors(doctorId) on delete cascade
);
insert into patients(firstName, lastName, dateOfBirth, gender, contactNumber, address) values
('Arjun', 'Nair', '1985-05-15', 'Male', '987-654-3210', '123 MG Road, Chennai'),
('Lakshmi', 'Reddy', '1990-08-20', 'Female', '987-123-4567', '456 Jayanagar, Bangalore'),
('Karthik', 'Iyer', '1975-12-30', 'Male', '555-123-4567', '789 Malleswaram, Bangalore'),
('Ananya', 'Krishna', '1988-03-10', 'Female', '999-888-7777', '321 T Nagar, Chennai'),
('Vikram', 'Shetty', '1995-07-25', 'Male', '888-777-6666', '654 Koramangala, Bangalore');

insert into doctors(firstName,lastName,specialization,contactNumber) values
('Dr. Priya', 'Nambiar', 'Cardiology', '999-111-2222'),
('Dr. Ravi', 'Kumar', 'Neurology', '888-222-3333'),
('Dr. Sneha', 'Menon', 'Pediatrics', '777-333-4444'),
('Dr. Suresh', 'Babu', 'Orthopedics', '666-444-5555'),
('Dr. Kavita', 'Rao', 'Gynecology', '555-555-6666');

insert into appointments(patientId,doctorId,appointmentDate,description)values
( 1, 1, '2023-10-01', 'Routine check-up'),
( 2, 2, '2023-10-02', 'Neurological assessment'),
( 3, 3, '2023-10-03', 'Pediatric consultation'),
( 4, 4, '2023-10-04', 'Orthopedic evaluation'),
( 5, 5, '2023-10-05', 'Gynecological check-up');

