--
-- Copyright (c) 2018, 2019 Oracle and/or its affiliates. All rights reserved.
--
-- This program and the accompanying materials are made available under the
-- terms of the Eclipse Public License v. 2.0 which is available at
-- http://www.eclipse.org/legal/epl-2.0,
-- or the Eclipse Distribution License v. 1.0 which is available at
-- http://www.eclipse.org/org/documents/edl-v10.php.
--
-- SPDX-License-Identifier: EPL-2.0 OR BSD-3-Clause
--

CREATE TABLE XR_ADDRESS (
    ADDRESS_ID NUMERIC(15) NOT NULL,
    COUNTRY VARCHAR(80),
    CITY VARCHAR(80),
    PROVINCE VARCHAR(80),
    STREET VARCHAR(80),
    P_CODE VARCHAR(20),
    PRIMARY KEY (ADDRESS_ID)
)|
CREATE TABLE XR_EMPLOYEE (
    EMP_ID NUMERIC(15) NOT NULL,
    F_NAME VARCHAR(40),
    L_NAME VARCHAR(40),
    VERSION NUMERIC(15),
    START_DATE DATE,
    END_TIME TIME,
    GENDER VARCHAR(1),
    END_DATE DATE,
    START_TIME TIME,
    ADDR_ID NUMERIC(15),
    PRIMARY KEY (EMP_ID),
    CONSTRAINT XR_EMPLOYEE_ADDRESS FOREIGN KEY (ADDR_ID) REFERENCES XR_ADDRESS (ADDRESS_ID)
)|
CREATE TABLE XR_RESPONS (
    EMP_ID NUMERIC(15) NOT NULL,
    DESCRIP VARCHAR(200) NOT NULL,
    PRIMARY KEY (EMP_ID, DESCRIP),
    CONSTRAINT XR_RESPONS_EMPLOYEE FOREIGN KEY (EMP_ID) REFERENCES XR_EMPLOYEE (EMP_ID)
)|
CREATE TABLE XR_SALARY (
    EMP_ID NUMERIC(15) NOT NULL,
    SALARY NUMERIC(15),
    PRIMARY KEY (EMP_ID),
    CONSTRAINT XR_EMPLOYEE_SALARY FOREIGN KEY (EMP_ID) REFERENCES XR_EMPLOYEE (EMP_ID)
)|
CREATE TABLE XR_PHONE (
    EMP_ID NUMERIC(15) NOT NULL,
    TYPE VARCHAR(15) NOT NULL,
    P_NUMBER VARCHAR(7),
    AREA_CODE VARCHAR(3),
    PRIMARY KEY (EMP_ID, TYPE),
    CONSTRAINT XR_PHONE_EMPLOYEE FOREIGN KEY (EMP_ID) REFERENCES XR_EMPLOYEE (EMP_ID)
)|
INSERT INTO XR_ADDRESS(ADDRESS_ID,CITY,COUNTRY,P_CODE,PROVINCE,STREET) VALUES (52,'Ottawa','Canada','K5J2B5','ONT','12 Merival Rd., suite 5')|
INSERT INTO XR_ADDRESS(ADDRESS_ID,CITY,COUNTRY,P_CODE,PROVINCE,STREET) VALUES (55,'Arnprior','Canada','W1A2B5','ONT','1 Nowhere Drive')|
INSERT INTO XR_ADDRESS(ADDRESS_ID,CITY,COUNTRY,P_CODE,PROVINCE,STREET) VALUES (61,'Calgary','Canada','J5J2B5','ALB','1111 Moose Rd.')|
INSERT INTO XR_ADDRESS(ADDRESS_ID,CITY,COUNTRY,P_CODE,PROVINCE,STREET) VALUES (60,'Yellow Knife','Canada','Y5J2N5','YK','1112 Gold Rush rd.')|
INSERT INTO XR_ADDRESS(ADDRESS_ID,CITY,COUNTRY,P_CODE,PROVINCE,STREET) VALUES (62,'Metcalfe','Canada','Y4F7V6','ONT','2 Anderson Rd.')|
INSERT INTO XR_ADDRESS(ADDRESS_ID,CITY,COUNTRY,P_CODE,PROVINCE,STREET) VALUES (53,'Vancouver','Canada','N5J2N5','BC','1111 Mountain Blvd. Floor 53, suite 6')|
INSERT INTO XR_ADDRESS(ADDRESS_ID,CITY,COUNTRY,P_CODE,PROVINCE,STREET) VALUES (63,'Victoria','Canada','Z5J2N5','BC','382 Hyde Park')|
INSERT INTO XR_ADDRESS(ADDRESS_ID,CITY,COUNTRY,P_CODE,PROVINCE,STREET) VALUES (54,'Ottawa','Canada','K5J2B5','ONT','12 Merival Rd., suite 5')|
INSERT INTO XR_ADDRESS(ADDRESS_ID,CITY,COUNTRY,P_CODE,PROVINCE,STREET) VALUES (57,'Toronto','Canada','L5J2B5','ONT','1450 Acme Cr., suite 4')|
INSERT INTO XR_ADDRESS(ADDRESS_ID,CITY,COUNTRY,P_CODE,PROVINCE,STREET) VALUES (58,'Perth','Canada','Y3Q2N9','ONT','234 I''m Lost Lane')|
INSERT INTO XR_ADDRESS(ADDRESS_ID,CITY,COUNTRY,P_CODE,PROVINCE,STREET) VALUES (59,'Montreal','Canada','Q2S5Z5','QUE','1 Habs Place')|
INSERT INTO XR_ADDRESS(ADDRESS_ID,CITY,COUNTRY,P_CODE,PROVINCE,STREET) VALUES (56,'Smith Falls','Canada','C6C6C6','ONT','1 Chocolate Drive')|
INSERT INTO XR_EMPLOYEE(ADDR_ID,EMP_ID,END_DATE,END_TIME,F_NAME,GENDER,L_NAME,START_DATE,START_TIME,VERSION) values (61,58,'2006-08-31','00:00:00','Jill','F','May','1991-11-11','00:00:00',1)|
INSERT INTO XR_EMPLOYEE(ADDR_ID,EMP_ID,END_DATE,END_TIME,F_NAME,GENDER,L_NAME,START_DATE,START_TIME,VERSION) values (60,63,'2001-12-31','00:00:00','Jim-bob','M','Jefferson','1995-01-12','00:00:00',1)|
INSERT INTO XR_EMPLOYEE(ADDR_ID,EMP_ID,END_DATE,END_TIME,F_NAME,GENDER,L_NAME,START_DATE,START_TIME,VERSION) values (54,62,'2006-08-31','00:00:00','John','M','Way','1991-11-11','00:00:00',1)|
INSERT INTO XR_EMPLOYEE(ADDR_ID,EMP_ID,END_DATE,END_TIME,F_NAME,GENDER,L_NAME,START_DATE,START_TIME,VERSION) values (57,61,'1996-01-01','00:00:00','Bob','M','Smith','1993-01-01','00:00:00',1)|
INSERT INTO XR_EMPLOYEE(ADDR_ID,EMP_ID,END_DATE,END_TIME,F_NAME,GENDER,L_NAME,START_DATE,START_TIME,VERSION) values (63,60,'2001-12-31','00:00:00','Fred','M','Jones','1995-01-01','00:00:00',1)|
INSERT INTO XR_EMPLOYEE(ADDR_ID,EMP_ID,END_DATE,END_TIME,F_NAME,GENDER,L_NAME,START_DATE,START_TIME,VERSION) values (55,56,'1996-01-01','00:00:00','Sarah-loo','F','Smitty','1993-01-01','00:00:00',1)|
INSERT INTO XR_EMPLOYEE(ADDR_ID,EMP_ID,END_DATE,END_TIME,F_NAME,GENDER,L_NAME,START_DATE,START_TIME,VERSION) values (62,57,'1996-01-01','00:00:00','Nancy','F','White','1993-01-01','00:00:00',1)|
INSERT INTO XR_EMPLOYEE(ADDR_ID,EMP_ID,END_DATE,END_TIME,F_NAME,GENDER,L_NAME,START_DATE,START_TIME,VERSION) values (53,54,'2001-12-31','00:00:00','Emanual','M','Smith','1995-01-01','00:00:00',1)|
INSERT INTO XR_EMPLOYEE(ADDR_ID,EMP_ID,END_DATE,END_TIME,F_NAME,GENDER,L_NAME,START_DATE,START_TIME,VERSION) values (52,53,'2001-07-31','00:00:00','Sarah','F','Way','1995-05-01','00:00:00',1)|
INSERT INTO XR_EMPLOYEE(ADDR_ID,EMP_ID,END_DATE,END_TIME,F_NAME,GENDER,L_NAME,START_DATE,START_TIME,VERSION) values (58,55,'2001-12-31','00:00:00','Marcus','M','Saunders','1995-01-12','00:00:00',1)|
INSERT INTO XR_EMPLOYEE(ADDR_ID,EMP_ID,END_DATE,END_TIME,F_NAME,GENDER,L_NAME,START_DATE,START_TIME,VERSION) values (56,52,'2001-12-31','00:00:00','Betty','F','Jones','1995-01-01','00:00:00',1)|
INSERT INTO XR_EMPLOYEE(ADDR_ID,EMP_ID,END_DATE,END_TIME,F_NAME,GENDER,L_NAME,START_DATE,START_TIME,VERSION) values (59,59,'2001-12-31','00:00:00','Charles','M','Chanley','1995-01-01','00:00:00',1)|
INSERT INTO XR_RESPONS(DESCRIP,EMP_ID) values ('Clean the kitchen.',61)|
INSERT INTO XR_RESPONS(DESCRIP,EMP_ID) values ('Fire people for goofing off.',62)|
INSERT INTO XR_RESPONS(DESCRIP,EMP_ID) values ('Have to fix the Database problem.',54)|
INSERT INTO XR_RESPONS(DESCRIP,EMP_ID) values ('Hire people when more people are required.',62)|
INSERT INTO XR_RESPONS(DESCRIP,EMP_ID) values ('Make the coffee.',61)|
INSERT INTO XR_RESPONS(DESCRIP,EMP_ID) values ('Write code documentation.',53)|
INSERT INTO XR_RESPONS(DESCRIP,EMP_ID) values ('Write lots of Java code.',59)|
INSERT INTO XR_RESPONS(DESCRIP,EMP_ID) values ('Write user specifications.',55)|
INSERT INTO XR_SALARY(EMP_ID,SALARY) values (58,56232)|
INSERT INTO XR_SALARY(EMP_ID,SALARY) values (63,50000)|
INSERT INTO XR_SALARY(EMP_ID,SALARY) values (62,53000)|
INSERT INTO XR_SALARY(EMP_ID,SALARY) values (61,35000)|
INSERT INTO XR_SALARY(EMP_ID,SALARY) values (60,500000)|
INSERT INTO XR_SALARY(EMP_ID,SALARY) values (56,75000)|
INSERT INTO XR_SALARY(EMP_ID,SALARY) values (57,31000)|
INSERT INTO XR_SALARY(EMP_ID,SALARY) values (54,49631)|
INSERT INTO XR_SALARY(EMP_ID,SALARY) values (53,87000)|
INSERT INTO XR_SALARY(EMP_ID,SALARY) values (55,54300)|
INSERT INTO XR_SALARY(EMP_ID,SALARY) values (52,500001)|
INSERT INTO XR_SALARY(EMP_ID,SALARY) values (59,43000)|
INSERT INTO XR_PHONE(AREA_CODE,EMP_ID,P_NUMBER,TYPE) values ('416',63,'5551111','Cellular')|
INSERT INTO XR_PHONE(AREA_CODE,EMP_ID,P_NUMBER,TYPE) values ('613',63,'5551234','Home')|
INSERT INTO XR_PHONE(AREA_CODE,EMP_ID,P_NUMBER,TYPE) values ('416',56,'5551111','Cellular')|
INSERT INTO XR_PHONE(AREA_CODE,EMP_ID,P_NUMBER,TYPE) values ('416',60,'5551111','Cellular')|
INSERT INTO XR_PHONE(AREA_CODE,EMP_ID,P_NUMBER,TYPE) values ('905',60,'5553691','ISDN')|
INSERT INTO XR_PHONE(AREA_CODE,EMP_ID,P_NUMBER,TYPE) values ('976',59,'5556666','Pager')|
INSERT INTO XR_PHONE(AREA_CODE,EMP_ID,P_NUMBER,TYPE) values ('613',58,'2258812','Work')|
INSERT INTO XR_PHONE(AREA_CODE,EMP_ID,P_NUMBER,TYPE) values ('905',55,'5553691','ISDN')|
INSERT INTO XR_PHONE(AREA_CODE,EMP_ID,P_NUMBER,TYPE) values ('613',55,'2258812','Work')|
INSERT INTO XR_PHONE(AREA_CODE,EMP_ID,P_NUMBER,TYPE) values ('613',62,'2258812','Work')|
INSERT INTO XR_PHONE(AREA_CODE,EMP_ID,P_NUMBER,TYPE) values ('613',54,'2255943','Work Fax')|
INSERT INTO XR_PHONE(AREA_CODE,EMP_ID,P_NUMBER,TYPE) values ('905',62,'5553691','ISDN')|
INSERT INTO XR_PHONE(AREA_CODE,EMP_ID,P_NUMBER,TYPE) values ('613',61,'2258812','Work')|
INSERT INTO XR_PHONE(AREA_CODE,EMP_ID,P_NUMBER,TYPE) values ('976',54,'5556666','Pager')|
INSERT INTO XR_PHONE(AREA_CODE,EMP_ID,P_NUMBER,TYPE) values ('416',54,'5551111','Cellular')|
INSERT INTO XR_PHONE(AREA_CODE,EMP_ID,P_NUMBER,TYPE) values ('905',54,'5553691','ISDN')|
INSERT INTO XR_PHONE(AREA_CODE,EMP_ID,P_NUMBER,TYPE) values ('613',52,'2258812','Work')|
INSERT INTO XR_PHONE(AREA_CODE,EMP_ID,P_NUMBER,TYPE) values ('613',56,'2255943','Work Fax')|
INSERT INTO XR_PHONE(AREA_CODE,EMP_ID,P_NUMBER,TYPE) values ('905',53,'5553691','ISDN')|
INSERT INTO XR_PHONE(AREA_CODE,EMP_ID,P_NUMBER,TYPE) values ('905',59,'5553691','ISDN')|
INSERT INTO XR_PHONE(AREA_CODE,EMP_ID,P_NUMBER,TYPE) values ('613',53,'5551234','Home')|
INSERT INTO XR_PHONE(AREA_CODE,EMP_ID,P_NUMBER,TYPE) values ('613',56,'5551234','Home')|
INSERT INTO XR_PHONE(AREA_CODE,EMP_ID,P_NUMBER,TYPE) values ('613',57,'5551234','Home')|
INSERT INTO XR_PHONE(AREA_CODE,EMP_ID,P_NUMBER,TYPE) values ('905',52,'5553691','ISDN')|
INSERT INTO XR_PHONE(AREA_CODE,EMP_ID,P_NUMBER,TYPE) values ('613',58,'2255943','Work Fax')|
INSERT INTO XR_PHONE(AREA_CODE,EMP_ID,P_NUMBER,TYPE) values ('613',53,'2258812','Work')|