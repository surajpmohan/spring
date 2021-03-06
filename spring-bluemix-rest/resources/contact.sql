--<ScriptOptions statementTerminator=";"/>

CREATE SCHEMA SPRING AUTHORIZATION DB2ADMIN;

CREATE TABLE SPRING.CONTACT (
		ID INTEGER NOT NULL GENERATED ALWAYS AS IDENTITY ( START WITH 1 INCREMENT BY 1 MINVALUE 1 MAXVALUE 2147483647 NO CYCLE CACHE 20 NO ORDER ), 
		FIRST_NAME VARCHAR(50) NOT NULL, 
		LAST_NAME VARCHAR(50) NOT NULL, 
		DOB DATE NOT NULL, 
		EMAIL VARCHAR(50) NOT NULL, 
		PHONE VARCHAR(10) NOT NULL
	)
	DATA CAPTURE NONE 
	IN USERSPACE1
	COMPRESS NO;

ALTER TABLE SPRING.CONTACT ADD CONSTRAINT SPRING_PK PRIMARY KEY
	(ID);

