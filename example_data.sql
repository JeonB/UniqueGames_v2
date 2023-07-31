-- 회원 설정
INSERT INTO TB_MEMBER (MEMBER_ID, PASSWORD, NAME, EMAIL, ADDR, TEL, PHONE_NUM)
VALUES ('mtest', '123', '테스트', 'mtest@naver.com', '서울시 렛잇구', 'SKT', '010-1122-3344');
INSERT INTO TB_MEMBER (MEMBER_ID, PASSWORD, NAME, EMAIL, ADDR, TEL, PHONE_NUM)
VALUES ('admin', '123', '관리자', 'admin@naver.com', '판교', 'SKT', '010-1122-3344');
INSERT INTO TB_MEMBER (MEMBER_ID, PASSWORD, NAME, EMAIL, ADDR, TEL, PHONE_NUM)
VALUES ('hong', '123', '홍길동', 'hong@naver.com', '서울시 아버지를아버지라부르지못하구', 'SKT', '010-1111-7711');
INSERT INTO TB_MEMBER (MEMBER_ID, PASSWORD, NAME, EMAIL, ADDR, TEL, PHONE_NUM)
VALUES ('kim', '123', '김길동', 'kim@gmail.com', '인천 미추홀구', 'SKT', '010-8844-1111');
INSERT INTO TB_MEMBER (MEMBER_ID, PASSWORD, NAME, EMAIL, ADDR, TEL, PHONE_NUM)
VALUES ('lee', '123', '이길동', 'lee@gmail.com', '용인시', 'KT', '010-2299-1111');
INSERT INTO TB_MEMBER (MEMBER_ID, PASSWORD, NAME, EMAIL, ADDR, TEL, PHONE_NUM)
VALUES ('park', '123', '박길동', 'park@daum.net', '서울시 영등포구', 'SKT', '010-0088-1111');
INSERT INTO TB_MEMBER (MEMBER_ID, PASSWORD, NAME, EMAIL, ADDR, TEL, PHONE_NUM)
VALUES ('choi', '123', '최길동', 'choi@naver.com', '청주시 상당구', 'SKT', '010-7700-2222');
INSERT INTO TB_MEMBER (MEMBER_ID, PASSWORD, NAME, EMAIL, ADDR, TEL, PHONE_NUM)
VALUES ('song', '123', '송길동', 'song@naver.com', '대전', 'SKT', '010-7700-2222');
INSERT INTO TB_MEMBER (MEMBER_ID, PASSWORD, NAME, EMAIL, ADDR, TEL, PHONE_NUM)
VALUES ('jeon', '123', '전길동', 'jeon@naver.com', '부산', 'SKT', '010-7700-2222');
INSERT INTO TB_MEMBER (MEMBER_ID, PASSWORD, NAME, EMAIL, ADDR, TEL, PHONE_NUM)
VALUES ('min', '123', '민길동', 'min@naver.com', '속초', 'SKT', '010-7700-2222');
INSERT INTO TB_MEMBER (MEMBER_ID, PASSWORD, NAME, EMAIL, ADDR, TEL, PHONE_NUM)
VALUES ('son', '123', '손길동', 'son@naver.com', '대구', 'SKT', '010-7700-2222');
INSERT INTO TB_MEMBER (MEMBER_ID, PASSWORD, NAME, EMAIL, ADDR, TEL, PHONE_NUM)
VALUES ('DeletedMember', '123', '탈퇴회원정보저장', 'email', 'addr', 'ted', 'phone_num');


-- 게임 등록
SELECT *FROM TB_GAME;
INSERT INTO TB_GAME (NAME, GAME_GENRE, DONATION_STATUS, DESCRIPTION)
VALUES('스트리트 파이터 6 디럭스',  '격투', 1,'시리즈 최신작 『Street Fighter 6(스트리트 파이터 6)』');
INSERT INTO TB_GAME (NAME,  GAME_GENRE, DONATION_STATUS, DESCRIPTION)
VALUES('뱀파이어: 마스커레이드',  '액션', 1,'월드 오브 다크니스에 오신 것을 환영합니다');
INSERT INTO TB_GAME (NAME, GAME_GENRE, DONATION_STATUS, DESCRIPTION)
VALUES('더 라스트 오브 어스 파트 1',  '액션', 1,'살기 위해 오늘도 길을 나선다 탐험 액션');
-- 게임 이미지 등록
INSERT INTO tb_game_image(G_ID, UPLOAD_IMG) VALUES (1,'img-game-deexit1.jpg');
INSERT INTO tb_game_image(G_ID, UPLOAD_IMG) VALUES (1,'img-game-deexit2.jpg');
INSERT INTO tb_game_image(G_ID, UPLOAD_IMG) VALUES (1,'img-game-deexit3.jpg');
INSERT INTO tb_game_image(G_ID, UPLOAD_IMG) VALUES (2,'img-game-street1.jpg');
INSERT INTO tb_game_image(G_ID, UPLOAD_IMG) VALUES (2,'img-game-street2.jpg');
INSERT INTO tb_game_image(G_ID, UPLOAD_IMG) VALUES (2,'img-game-street3.jpg');
INSERT INTO tb_game_image(G_ID, UPLOAD_IMG) VALUES (3,'img-game-vampire1.jpg');
INSERT INTO tb_game_image(G_ID, UPLOAD_IMG) VALUES (3,'img-game-vampire2.jpg');
INSERT INTO tb_game_image(G_ID, UPLOAD_IMG) VALUES (3,'img-game-vampire3.jpg');
INSERT INTO tb_game_image(G_ID, UPLOAD_IMG) VALUES (4,'img-game-last1.jpg');
INSERT INTO tb_game_image(G_ID, UPLOAD_IMG) VALUES (4,'img-game-last2.jpg');
INSERT INTO tb_game_image(G_ID, UPLOAD_IMG) VALUES (4,'img-game-last3.jpg');

-- 게임 이미지 등록
SELECT *FROM TB_GAME_IMAGE;
INSERT INTO tb_game_image(G_ID, UPLOAD_IMG)
VALUES (1, 'img-game-deexit1.jpg');
INSERT INTO tb_game_image(G_ID, UPLOAD_IMG)
VALUES (1, 'img-game-deexit2.jpg');
INSERT INTO tb_game_image(G_ID, UPLOAD_IMG)
VALUES (1, 'img-game-deexit3.jpg');
INSERT INTO tb_game_image(G_ID, UPLOAD_IMG)
VALUES (2, 'img-game-street1.jpg');
INSERT INTO tb_game_image(G_ID, UPLOAD_IMG)
VALUES (2, 'img-game-street2.jpg');
INSERT INTO tb_game_image(G_ID, UPLOAD_IMG)
VALUES (2, 'img-game-street3.jpg');
INSERT INTO tb_game_image(G_ID, UPLOAD_IMG)
VALUES (3, 'img-game-vampire1.jpg');
INSERT INTO tb_game_image(G_ID, UPLOAD_IMG)
VALUES (3, 'img-game-vampire2.jpg');
INSERT INTO tb_game_image(G_ID, UPLOAD_IMG)
VALUES (3, 'img-game-vampire3.jpg');
INSERT INTO tb_game_image(G_ID, UPLOAD_IMG)
VALUES (4, 'img-game-last1.jpg');
INSERT INTO tb_game_image(G_ID, UPLOAD_IMG)
VALUES (4, 'img-game-last2.jpg');
INSERT INTO tb_game_image(G_ID, UPLOAD_IMG)
VALUES (4, 'img-game-last3.jpg');

-- 게임사 등록
INSERT INTO TB_COMPANY (COMPANY_ID, PASSWORD, NAME, EMAIL, ADDR, TEL, PHONE_NUM, G_ID)
VALUES ('test', '123', 'WideScent Games', 'test@gmail.com', '판교', 'KT', '010-1111-2222', 1);
INSERT INTO TB_COMPANY (COMPANY_ID, PASSWORD, NAME, EMAIL, ADDR, TEL, PHONE_NUM, G_ID)
VALUES ('stest', '123', 'Retro Nostalgia', 'nexon@gmail.com', '판교', 'SKT', '010-3333-4444', 2);
INSERT INTO TB_COMPANY (COMPANY_ID, PASSWORD, NAME, EMAIL, ADDR, TEL, PHONE_NUM, G_ID)
VALUES ('dtest', '123', 'X - Interaction', 'net@gmail.com', '제주도', 'KT', '010-5555-6666', 3);
INSERT INTO TB_COMPANY (COMPANY_ID, PASSWORD, NAME, EMAIL, ADDR, TEL, PHONE_NUM, G_ID)
VALUES ('ftest', '123', 'EcoSync GamING', 'smilegate@gmail.com', '강남', 'SKT', '010-7777-8888', 4);
INSERT INTO TB_COMPANY (COMPANY_ID, PASSWORD, NAME, EMAIL, ADDR, TEL, PHONE_NUM)
VALUES ('crafton', '123', '크래프톤', 'crafton@gmail.com', '판교', 'SKT', '010-7777-8888');
INSERT INTO TB_COMPANY (COMPANY_ID, PASSWORD, NAME, EMAIL, ADDR, TEL, PHONE_NUM)
VALUES ('pearl', '123', '펄어비스', 'pearl@gmail.com', '강남', 'SKT', '010-7777-8888');
INSERT INTO TB_COMPANY (COMPANY_ID, PASSWORD, NAME, EMAIL, ADDR, TEL, PHONE_NUM)
VALUES ('com', '123', '컴투스', 'com@gmail.com', '판교', 'SKT', '010-7777-8888');
INSERT INTO TB_COMPANY (COMPANY_ID, PASSWORD, NAME, EMAIL, ADDR, TEL, PHONE_NUM)
VALUES ('kakao', '123', '카카오게임즈', 'kakao@gmail.com', '강남', 'SKT', '010-7777-8888');
INSERT INTO TB_COMPANY (COMPANY_ID, PASSWORD, NAME, EMAIL, ADDR, TEL, PHONE_NUM)
VALUES ('wow', '123', '개쩌는게임회사', 'wow@gmail.com', '판교', 'SKT', '010-7777-8888');
INSERT INTO TB_COMPANY (COMPANY_ID, PASSWORD, NAME, EMAIL, ADDR, TEL, PHONE_NUM)
VALUES ('money', '123', '연봉높은게임회사', 'money@gmail.com', '판교', 'SKT', '010-7777-8888');
INSERT INTO TB_COMPANY (COMPANY_ID, PASSWORD, NAME, EMAIL, ADDR, TEL, PHONE_NUM)
VALUES ('work', '123', '야근맨날해', 'work@gmail.com', '판교', 'SKT', '010-7777-8888');
INSERT INTO TB_COMPANY (COMPANY_ID, PASSWORD, NAME, EMAIL, ADDR, TEL, PHONE_NUM)
VALUES ('DeletedCompany', '123', '탈퇴한법인회원', 'email', 'addr', 'tel', 'phone_num');

-- mtest 장바구니 설정
INSERT INTO TB_ORDER (M_ID, C_ID, G_ID, AMOUNT)
VALUES ('mtest', 'test', 1, 10000);
INSERT INTO TB_ORDER (M_ID, C_ID, G_ID, AMOUNT)
VALUES ('mtest', 'nexon', 2, 30000);
INSERT INTO TB_ORDER (M_ID, C_ID, G_ID, AMOUNT)
VALUES ('mtest', 'net', 3, 50000);
INSERT INTO TB_ORDER (M_ID, C_ID, G_ID, AMOUNT)
VALUES ('mtest', 'smilegate', 4, 100000);

-- mtest 결제내역 설정
INSERT INTO TB_ORDER (ID, M_ID, C_ID, G_ID, AMOUNT, PAYMENT_STATUS, ORDER_DATE)
VALUES (1, 'mtest', 'test', 1, 10000, 'COMPLETE', '2023-05-06');
INSERT INTO TB_ORDER (ID, M_ID, C_ID, G_ID, AMOUNT, PAYMENT_STATUS, ORDER_DATE)
VALUES (2, 'mtest', 'nexon', 2, 30000, 'COMPLETE', '2023-02-07');
INSERT INTO TB_ORDER (ID, M_ID, C_ID, G_ID, AMOUNT, PAYMENT_STATUS, ORDER_DATE)
VALUES (3, 'mtest', 'net', 3, 50000, 'COMPLETE', '2023-04-15');
INSERT INTO TB_ORDER (ID, M_ID, C_ID, G_ID, AMOUNT, PAYMENT_STATUS, ORDER_DATE)
VALUES (4, 'mtest', 'smilegate', 4, 20000, 'COMPLETE', '2023-02-09');
INSERT INTO TB_ORDER (ID, M_ID, C_ID, G_ID, AMOUNT, PAYMENT_STATUS, ORDER_DATE)
VALUES (5, 'mtest', 'test', 1, 100000, 'COMPLETE', '2023-03-24');
INSERT INTO TB_ORDER (ID, M_ID, C_ID, G_ID, AMOUNT, PAYMENT_STATUS, ORDER_DATE)
VALUES (6, 'mtest', 'net', 3, 50000, 'COMPLETE', '2023-04-30');
INSERT INTO TB_ORDER (ID, M_ID, C_ID, G_ID, AMOUNT, PAYMENT_STATUS, ORDER_DATE)
VALUES (7, 'mtest', 'net', 3, 50000, 'COMPLETE', '2023-06-01');
INSERT INTO TB_ORDER (ID, M_ID, C_ID, G_ID, AMOUNT, PAYMENT_STATUS, ORDER_DATE)
VALUES (8, 'mtest', 'test', 1, 20000, 'COMPLETE', '2023-06-18');
INSERT INTO TB_ORDER (M_ID, C_ID, G_ID, AMOUNT, PAYMENT_STATUS, ORDER_DATE)
VALUES ('mtest', 'test', 1, 30000, 'COMPLETE', '2023-03-01');
INSERT INTO TB_ORDER (M_ID, C_ID, G_ID, AMOUNT, PAYMENT_STATUS, ORDER_DATE)
VALUES ('mtest', 'net', 3, 100000, 'COMPLETE', '2023-04-10');
INSERT INTO TB_ORDER (M_ID, C_ID, G_ID, AMOUNT, PAYMENT_STATUS, ORDER_DATE)
VALUES ('mtest', 'net', 3, 50000, 'COMPLETE', '2023-06-16');
INSERT INTO TB_ORDER (M_ID, C_ID, G_ID, AMOUNT, PAYMENT_STATUS, ORDER_DATE)
VALUES ('mtest', 'test', 1, 10000, 'COMPLETE', '2023-06-21');


-- 게임사 후원내역 설정 : test
INSERT INTO TB_ORDER (ID, M_ID, C_ID, G_ID, AMOUNT, PAYMENT_STATUS, ORDER_DATE)
VALUES (11, 'lee', 'test', 1, 10000, 'COMPLETE', '2023-02-06');
INSERT INTO TB_ORDER (ID, M_ID, C_ID, G_ID, AMOUNT, PAYMENT_STATUS, ORDER_DATE)
VALUES (12, 'hong', 'test', 1, 100000, 'COMPLETE', '2023-02-07');
INSERT INTO TB_ORDER (ID, M_ID, C_ID, G_ID, AMOUNT, PAYMENT_STATUS, ORDER_DATE)
VALUES (13, 'song', 'test', 1, 50000, 'COMPLETE', '2023-02-07');
INSERT INTO TB_ORDER (ID, M_ID, C_ID, G_ID, AMOUNT, PAYMENT_STATUS, ORDER_DATE)
VALUES (14, 'kim', 'test', 1, 30000, 'COMPLETE', '2023-03-07');
INSERT INTO TB_ORDER (ID, M_ID, C_ID, G_ID, AMOUNT, PAYMENT_STATUS, ORDER_DATE)
VALUES (15, 'choi', 'test', 1, 20000, 'COMPLETE', '2023-03-06');
INSERT INTO TB_ORDER (ID, M_ID, C_ID, G_ID, AMOUNT, PAYMENT_STATUS, ORDER_DATE)
VALUES (16, 'joen', 'test', 1, 150000, 'COMPLETE', '2023-03-07');
INSERT INTO TB_ORDER (ID, M_ID, C_ID, G_ID, AMOUNT, PAYMENT_STATUS, ORDER_DATE)
VALUES (17, 'min', 'test', 1, 10000, 'COMPLETE', '2023-04-07');
INSERT INTO TB_ORDER (ID, M_ID, C_ID, G_ID, AMOUNT, PAYMENT_STATUS, ORDER_DATE)
VALUES (18, 'kim', 'test', 1, 30000, 'COMPLETE', '2023-04-07');
INSERT INTO TB_ORDER (ID, M_ID, C_ID, G_ID, AMOUNT, PAYMENT_STATUS, ORDER_DATE)
VALUES (19, 'lee', 'test', 1, 100000, 'COMPLETE', '2023-04-06');
INSERT INTO TB_ORDER (ID, M_ID, C_ID, G_ID, AMOUNT, PAYMENT_STATUS, ORDER_DATE)
VALUES (20, 'hong', 'test', 1, 300000, 'COMPLETE', '2023-05-07');
INSERT INTO TB_ORDER (ID, M_ID, C_ID, G_ID, AMOUNT, PAYMENT_STATUS, ORDER_DATE)
VALUES (21, 'son', 'test', 1, 70000, 'COMPLETE', '2023-05-07');
INSERT INTO TB_ORDER (ID, M_ID, C_ID, G_ID, AMOUNT, PAYMENT_STATUS, ORDER_DATE)
VALUES (22, 'kim', 'test', 1, 20000, 'COMPLETE', '2023-05-07');
INSERT INTO TB_ORDER (ID, M_ID, C_ID, G_ID, AMOUNT, PAYMENT_STATUS, ORDER_DATE)
VALUES (23, 'hong', 'test', 1, 250000, 'COMPLETE', '2023-06-07');
INSERT INTO TB_ORDER (ID, M_ID, C_ID, G_ID, AMOUNT, PAYMENT_STATUS, ORDER_DATE)
VALUES (24, 'park', 'test', 1, 10000, 'COMPLETE', '2023-06-07');
INSERT INTO TB_ORDER (ID, M_ID, C_ID, G_ID, AMOUNT, PAYMENT_STATUS, ORDER_DATE)
VALUES (25, 'kim', 'test', 1, 10000, 'COMPLETE', '2023-06-07');

-- 게임사 후원내역 설정 : etc
INSERT INTO TB_ORDER (ID, M_ID, C_ID, G_ID, AMOUNT, PAYMENT_STATUS, ORDER_DATE)
VALUES (31, 'lee', 'nexon', 2, 1200000, 'COMPLETE', '2023-05-10');
INSERT INTO TB_ORDER (ID, M_ID, C_ID, G_ID, AMOUNT, PAYMENT_STATUS, ORDER_DATE)
VALUES (32, 'hong', 'nexon', 2, 900000, 'COMPLETE', '2023-06-10');
INSERT INTO TB_ORDER (ID, M_ID, C_ID, G_ID, AMOUNT, PAYMENT_STATUS, ORDER_DATE)
VALUES (33, 'song', 'nexon', 2, 450000, 'COMPLETE', '2023-07-10');
INSERT INTO TB_ORDER (ID, M_ID, C_ID, G_ID, AMOUNT, PAYMENT_STATUS, ORDER_DATE)
VALUES (34, 'kim', 'net', 3, 840000, 'COMPLETE', '2023-05-10');
INSERT INTO TB_ORDER (ID, M_ID, C_ID, G_ID, AMOUNT, PAYMENT_STATUS, ORDER_DATE)
VALUES (35, 'choi', 'net', 3, 720000, 'COMPLETE', '2023-06-10');
INSERT INTO TB_ORDER (ID, M_ID, C_ID, G_ID, AMOUNT, PAYMENT_STATUS, ORDER_DATE)
VALUES (36, 'choi', 'net', 3, 170000, 'COMPLETE', '2023-07-10');
INSERT INTO TB_ORDER (ID, M_ID, C_ID, G_ID, AMOUNT, PAYMENT_STATUS, ORDER_DATE)
VALUES (37, 'joen', 'smilegate', 4, 510000, 'COMPLETE', '2023-05-10');
INSERT INTO TB_ORDER (ID, M_ID, C_ID, G_ID, AMOUNT, PAYMENT_STATUS, ORDER_DATE)
VALUES (38, 'min', 'smilegate', 4, 680000, 'COMPLETE', '2023-06-10');
INSERT INTO TB_ORDER (ID, M_ID, C_ID, G_ID, AMOUNT, PAYMENT_STATUS, ORDER_DATE)
VALUES (39, 'min', 'smilegate', 4, 250000, 'COMPLETE', '2023-07-10');

-- 회사 소개글
INSERT INTO TB_INTRO(C_ID, NAME, CONTENT) VALUES('test', 'WideScent Games', '저희는 현실에서 벗어나 새로운 세계를 탐험하고자 하는 이들을 위한 게임사입니다. 우리의 컨셉은 답답한 일상에서 해방되고자 하는 욕구를 만족시키기 위해 끊임없이 새로운 게임을 제작하는 것입니다. 함께 뛰어난 창의력과 혁신적인 기술을 통해 즐거움과 흥미로운 경험을 선사할 것입니다.
');
INSERT INTO TB_INTRO(C_ID, NAME, CONTENT) VALUES('stest', 'Retro Nostalgia', '1980년대의 향수와 감성을 그리워하는 분들을 위한 특별한 게임사입니다.
우리는 과거의 클래식한 게임들과 현대적인 기술을 융합하여 독특하고 애착있는 게임들을
만들어냅니다. 지금까지 느껴보지 못한 옛날의 감성을 느낄 수 있는 특별한 경험을 선사합니다.
');
INSERT INTO TB_INTRO(C_ID, NAME, CONTENT) VALUES('dtest', 'X - Interaction', '쾌감과 액션을 극대화시킨 게임을 제작하는 전문 회사입니다.
우리의 컨셉은 플레이어들이 게임을 통해 진정한 즐거움과 감동을 느끼도록 하는 것입니다.
탁월한 게임 디자인과 역동적인 게임플레이로 인해 플레이어들은 게임 속 세계에 몰입하여
매 순간을 즐길 수 있습니다.
');
INSERT INTO TB_INTRO(C_ID, NAME, CONTENT) VALUES('ftest', 'EcoSync GamING', '자연과 하나로 어우러지는 게임을 제작하는 게임사입니다.
우리는 자연의 아름다움과 조화를 게임 속에 담아냄으로써 플레이어들에게 평온하고
감동적인 경험을 선사합니다. 최첨단 기술과 창의력을 통해 플레이어들이 자연과 함께하는
환상적인 여정을 떠날 수 있도록 지원합니다.
');
