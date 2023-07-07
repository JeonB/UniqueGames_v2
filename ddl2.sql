DROP TABLE IF EXISTS `MEMBER`;
CREATE TABLE MEMBER (
    -- 회원 아이디(PK), 비밀번호, 이름, 이메일, 주소, 통신사, 전화번호
                        MEMBER_ID VARCHAR(15) NOT NULL PRIMARY KEY,
                        PASSWORD VARCHAR(15) NOT NULL,
                        NAME VARCHAR(15) NOT NULL,
                        EMAIL VARCHAR(30) NOT NULL,
                        ADDR VARCHAR(100),
                        TEL VARCHAR(20),
                        PHONE_NUM VARCHAR(20),
                        PROFILE_PIC VARCHAR(200),
                        INDEX idx_member_id (MEMBER_ID)
);

DROP TABLE IF EXISTS `COMPANY` ;
CREATE TABLE COMPANY (
    -- 회사 아이디(PK), 비밀번호, 회사명, 이메일, 주소, 통신사, 전화번호, G_ID(FK)
                         COMPANY_ID VARCHAR(15) NOT NULL PRIMARY KEY,
                         PASSWORD VARCHAR(15) NOT NULL,
                         NAME VARCHAR(15) NOT NULL,
                         EMAIL VARCHAR(30) NOT NULL,
                         ADDR VARCHAR(100),
                         TEL VARCHAR(10),
                         PHONE_NUM VARCHAR(20),
                         PROFILE_PIC VARCHAR(200),
                         G_ID INT UNSIGNED,

                         CONSTRAINT FK_COMPANY_GAME FOREIGN KEY (G_ID) REFERENCES GAME (ID)  ON UPDATE CASCADE ON DELETE CASCADE,

                         INDEX idx_company_id(COMPANY_ID),
                         INDEX idx_company_game(G_ID),
                         INDEX idx_company_intro(NAME)
);