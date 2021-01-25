 -- 유저생성은 관리자 계정에서 실행 할 것!!
create user usbproject IDENTIFIED by 1234;
grant connect,dba,resource to usbproject;


create table JPTquestion(
    qidx number PRIMARY KEY,
    part number not null,
    question varchar2(1000) not null,
    choice varchar2(1000),
    answer varchar2(2) not null,
    commentary varchar2(100),
    difficulty number not null,
    weekpoint number not null,
    favorites number default 0,
    responses number default 0,
    reading varchar2(50),
    CONSTRAINT FK_WEEK FOREIGN KEY(weekpoint)
    REFERENCES Week(weekID)
);
create sequence jpt_qidx_seq;

create table Week(
    weekID number PRIMARY KEY,
    weekpoint varchar2(20) not null
);

create table MemberWeekINFO(
    idx number PRIMARY KEY,
    email varchar2(150) UNIQUE not null,
    voca number default 0,
    idiom number default 0,
    proverb number default 0,
    grammer number default 0,
    reading number default 0,
    info number default 0,
    kanji number default 0,
    sentence number default 0,
    totcorrect number default 0,
    totincorrect number default 0,
    CONSTRAINT fk_member_week FOREIGN KEY(idx)
    REFERENCES memberINFO(idx)
);


  CREATE TABLE MEMBERGOAL
   (	
    IDX NUMBER NOT NULL, 
	EMAIL VARCHAR2(50) NOT NULL, 
	GOAL NUMBER, 
	DAY1 DATE, 
	DAY2 DATE, 
	DAY3 DATE, 
	DAY4 DATE, 
	DAY5 DATE, 
	DAY6 DATE, 
    DAY7 DATE, 
	DAY8 DATE, 
	DAY9 DATE, 
	DAY10 DATE, 
	GRADE1 NUMBER, 
	GRADE2 NUMBER, 
	GRADE3 NUMBER, 
    GRADE4 NUMBER, 
	GRADE5 NUMBER, 
	GRADE6 NUMBER, 
	GRADE7 NUMBER, 
	GRADE8 NUMBER, 
	GRADE9 NUMBER, 
	GRADE10 NUMBER, 
    CONSTRAINT MEMBERGOAL_PK PRIMARY KEY (IDX, EMAIL),
    CONSTRAINT MEMBERGOAL_FK FOREIGN KEY (EMAIL)
    REFERENCES MEMBERINFO (EMAIL)
   );



create table memberINFO(
    idx number PRIMARY KEY,
    email varchar2(150) UNIQUE not null,
    gender varchar2(3),
    password varchar2(30) not null,
    address1 varchar2(500) not null,
    address2 varchar2(500) not null,
    tel varchar2(20)
);
create sequence member_seq;

create table JPTchoiceINFO(
    qidx number PRIMARY KEY,
    answerA number default 0, --각 선택항목을 선택한 사람 수
    answerB number default 0,
    answerC number default 0,
    answerD number default 0,
    CONSTRAINT FK_QIDX FOREIGN KEY(qidx) 
    REFERENCES JPTquestion(qidx)
);

create table slovedquestion(
    idx number primary key,
    email varchar2(300) not null unique,
    correctquestions varchar2(4000),
    incorrectquestions varchar2(4000),
    favorite varchar2(4000),
    CONSTRAINT FK_IDXS FOREIGN KEY(idx) 
    REFERENCES memberINFO(idx)
);


insert into week values(01,'어휘력', 'VOCA');
insert into week values(02,'숙어력', 'IDIOM');
insert into week values(03,'속담력', 'PROVERB');
insert into week values(04,'한자력', 'KANJI');
insert into week values(05,'문법력', 'GRAMMER');
insert into week values(06,'독해력', 'READING');
insert into week values(07,'정보력', 'INFO');
insert into week values(08,'문장력', 'SENTENCE');


CREATE TABLE EXPECTATIONQUESTION (
    PART VARCHAR2(10), 
	QUESTION VARCHAR2(1000), 
	CHOICE VARCHAR2(1000), 
	ANSWER VARCHAR2(10)
) 

CREATE TABLE EXPECTTABLE(	
    EXPECTGRADE VARCHAR2(10), 
	Q1 VARCHAR2(20), 
	Q2 VARCHAR2(20), 
	Q3 VARCHAR2(20), 
	Q4 VARCHAR2(20), 
	Q5 VARCHAR2(20), 
	Q6 VARCHAR2(20), 
	Q7 VARCHAR2(20), 
	Q8 VARCHAR2(20), 
	Q9 VARCHAR2(20), 
	Q10 VARCHAR2(20), 
	Q11 VARCHAR2(20), 
	Q12 VARCHAR2(20), 
	Q13 VARCHAR2(20), 
	Q14 VARCHAR2(20), 
	Q15 VARCHAR2(20)
   )
   
   
   
   
   
