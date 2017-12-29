--THIS SECTION CREATES THE NECESSARY TABLES AND CONSTRAINTS
CREATE TABLE ROLE_TYPE(
 RT_ID NUMBER,
 RT_NAME VARCHAR2(500),
 PRIMARY KEY(RT_ID)
);
CREATE TABLE STATUS_TYPE(
 ST_ID NUMBER,
 ST_NAME VARCHAR2(500),
 PRIMARY KEY(ST_ID)
);
CREATE TABLE REIMBURSEMENT_TYPE(
 RBT_ID NUMBER,
 RBT_NAME VARCHAR2(500),
 PRIMARY KEY(RBT_ID)
);
CREATE TABLE ERS_USER(
 ERS_ID NUMBER,
 ERS_FN VARCHAR2(500),
 ERS_LN VARCHAR2(500),
 ERS_USERNAME VARCHAR2(500),
 ERS_PASSWORD VARCHAR2(500),
 ERS_RT_ID NUMBER,
 ERS_EMAIL VARCHAR2(500),
 PRIMARY KEY(ERS_ID),
 FOREIGN KEY(ERS_RT_ID) REFERENCES ROLE_TYPE(RT_ID)
);
CREATE TABLE REIMBURSEMENT(
 RB_ID NUMBER,
 RB_ERS_ID NUMBER,
 RB_ST_ID NUMBER,
 RB_AMOUNT NUMBER,
 RB_RECEIPT BLOB,
 RB_MANAGER_ID NUMBER,
 SUBMITTED TIMESTAMP,
 RESOLVED TIMESTAMP,
 RB_DESCRIPTION VARCHAR2(4000),
 RB_RBT_ID NUMBER,
 PRIMARY KEY(RB_ID),
 FOREIGN KEY(RB_ERS_ID) REFERENCES ERS_USER(ERS_ID),
 FOREIGN KEY(RB_ST_ID) REFERENCES STATUS_TYPE(ST_ID),
 FOREIGN KEY(RB_RBT_ID) REFERENCES REIMBURSEMENT_TYPE(RBT_ID)
);
-------------------------------------------------------------------------------
--THIS SECTION CREATES THE TRIGGERS USED TO AUTO INCREMENT THE TABLES
create or replace TRIGGER ers_user_auto_increment
  BEFORE INSERT ON ers_user 
  FOR EACH ROW 
    BEGIN
      IF :new.ers_id IS NULL THEN 
        SELECT ers_user_increment_sequence.nextval INTO :new.ers_id FROM DUAL;
      END IF;
    END;
create or replace TRIGGER reimbursement_auto_increment
  BEFORE INSERT ON reimbursement 
  FOR EACH ROW 
    BEGIN
      IF :new.rb_id IS NULL THEN 
        SELECT reimbursement_incr_sequence.nextval INTO :new.rb_id FROM DUAL;
      END IF;
    END;
create or replace TRIGGER reimbursement_type_auto_inc
  BEFORE INSERT ON reimbursement_type 
  FOR EACH ROW 
    BEGIN
      IF :new.rbt_id IS NULL THEN 
        SELECT reimbursement_type_inc_seq.nextval INTO :new.rbt_id FROM DUAL;
      END IF;
    END;
create or replace TRIGGER role_type_auto_increment
  BEFORE INSERT ON role_type 
  FOR EACH ROW 
    BEGIN
      IF :new.rt_id IS NULL THEN 
        SELECT role_type_increment_sequence.nextval INTO :new.rt_id FROM DUAL;
      END IF;
    END;
create or replace TRIGGER status_type_auto_increment
  BEFORE INSERT ON status_type 
  FOR EACH ROW 
    BEGIN
      IF :new.st_id IS NULL THEN 
        SELECT status_type_increment_sequence.nextval INTO :new.st_id FROM DUAL;
      END IF;
    END;    
-------------------------------------------------------------------------------
--THIS SECTION CREATES THE SEQUENCES FOR THE AUTO-INCREMENT FUNCTIONALITY
CREATE SEQUENCE ERS_USER_INCREMENT_SEQ
  START WITH 1
  INCREMENT BY 1;
CREATE SEQUENCE REIMBURSEMENT_INCR_SEQ
  START WITH 1
  INCREMENT BY 1;
CREATE SEQUENCE REIMBURSEMENT_TYPE_INC_SEQ
  START WITH 1
  INCREMENT BY 1;
CREATE SEQUENCE ROLE_TYPE_INCREMENT_SEQ
  START WITH 1
  INCREMENT BY 1;
CREATE SEQUENCE STATUS_TYPE_INCREMENT_SEQUENCE
  START WITH 1
  INCREMENT BY 1;