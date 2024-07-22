CREATE SCHEMA `bancoteste` ;
use bancoteste;
CREATE TABLE pacientes (
id BIGINT AUTO_INCREMENT Primary key,
name varchar(255),
date date,
adress varchar(255),
obs varchar(255));
