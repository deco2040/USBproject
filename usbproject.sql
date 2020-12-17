-- 유저생성은 관리자 계정에서 실행 할 것!!
create user usbpoject IDENTIFIED by 1234;
grant connect,dba,resource to usbpoject;


create table JPTquestion(
    qidx number PRIMARY KEY not null,
    part number not null,
    question varchar2(1000) not null,
    choice varchar2(100),
    answer varchar2(2) not null,
    commentary varchar2(100),
    difficulty number not null,
    weekpoint number not null,
    favorites number default 0,
    responses number default 0,
    CONSTRAINT FK_WEEK FOREIGN KEY(weekpoint)
    REFERENCES Week(weekID)
);
create sequence jpt_qidx_seq;

create table Week(
    weekID number PRIMARY KEY,
    weekpoint varchar2(20) not null
);

create table memberINFO(
    idx number PRIMARY KEY,
    memberid varchar2(20) UNIQUE not null,
    name varchar2(20) not null,
    birth varchar2(13) not null,
    gender varchar2(3),
    password varchar2(30) not null,
    address1 varchar2(100) not null,
    address2 varchar2(100) not null,
    email varchar2(150) not null,
    tel varchar2(20),
    CONSTRAINT fk_member_week FOREIGN KEY(idx)
    REFERENCES MemberWeekINFO(idx)
);
create sequence member_seq;

create table JPTchoiceINFO(
    qidx number default 0,
    answerA number default 0, --각 선택항목을 선택한 사람 수
    answerB number default 0,
    answerC number default 0,
    answerD number default 0,
    incorrectA number default 0,  -- 각 항목을 선택해서 틀린 사람 수
    incorrectB number default 0,
    incorrectC number default 0,
    incorrectD number default 0,
    responses number default 0,
    CONSTRAINT FK_QIDX FOREIGN KEY(qidx) 
    REFERENCES JPTquestion(qidx)
);

insert into week values(01,'어휘력');
insert into week values(02,'숙어력');
insert into week values(03,'속담력');
insert into week values(04,'한자력');
insert into week values(05,'문법력');
insert into week values(06,'독해력');
insert into week values(07,'정보력');
insert into week values(08,'문장력');

create table MemberWeekINFO(
    idx number not null,
    memberid varchar2(20) not null,
    voca number default 0,
    idiom number default 0,
    proverb number default 0,
    grammer number default 0,
    reading number default 0,
    info number default 0,
    kanji number default 0,
    sentence number default 0,
    totcorrect number default 0,
    totincorrect number default 0
);
