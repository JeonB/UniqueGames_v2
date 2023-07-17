-- 회원 설정
INSERT INTO tb_member (MEMBER_ID, PASSWORD, NAME, EMAIL, ADDR, TEL, PHONE_NUM)
VALUES('hong', '123', '홍길동', 'hong@naver.com', '서울시 강남구', 'SKT', '010-1111-7711');
INSERT INTO tb_member (MEMBER_ID, PASSWORD, NAME, EMAIL, ADDR, TEL, PHONE_NUM)
VALUES('kim', '123', '김길동', 'kim@gmail.com', '서울시 동작구', 'SKT', '010-8844-1111');
INSERT INTO tb_member (MEMBER_ID, PASSWORD, NAME, EMAIL, ADDR, TEL, PHONE_NUM)
VALUES('lee', '123', '이길동', 'lee@gmail.com', '서울시 강서구', 'KT', '010-2299-1111');
INSERT INTO tb_member (MEMBER_ID, PASSWORD, NAME, EMAIL, ADDR, TEL, PHONE_NUM)
VALUES('park', '123', '박길동', 'park@daum.net', '서울시 영등포구', 'SKT', '010-0088-1111');
INSERT INTO tb_member (MEMBER_ID, PASSWORD, NAME, EMAIL, ADDR, TEL, PHONE_NUM)
VALUES('choi', '123', '최길동', 'choi@naver.com', '서울시 렛잇구', 'SKT', '010-7700-2222');
INSERT INTO tb_member (MEMBER_ID, PASSWORD, NAME, EMAIL, ADDR, TEL, PHONE_NUM)
VALUES('mtest', '123', '엠길동', 'mtest@naver.com', '서울시 렛잇구', 'SKT', '010-1122-3344');

-- 게임 등록
INSERT INTO tb_game (NAME, IMAGE_PATH, GAME_GENRE, DONATION_STATUS, DESCRIPTION)
VALUES('디 엑시트 이터널 매터스 ', '../images/img-game-deexit4.jpg', '액션 어드벤처', 1,'죽음을 넘어선 생명을 느끼다');
INSERT INTO tb_game (NAME, IMAGE_PATH, GAME_GENRE, DONATION_STATUS, DESCRIPTION)
VALUES('스트리트 파이터 6 디럭스', '../images/img-game-street2.jpg', '격투', 1,'시리즈 최신작 『Street Fighter 6(스트리트 파이터 6)』');
INSERT INTO tb_game (NAME, IMAGE_PATH, GAME_GENRE, DONATION_STATUS, DESCRIPTION)
VALUES('뱀파이어: 마스커레이드', '../images/img-game-vampire2.jpg', '액션 어드벤처', 1,'월드 오브 다크니스에 오신 것을 환영합니다');
INSERT INTO tb_game (NAME, IMAGE_PATH, GAME_GENRE, DONATION_STATUS, DESCRIPTION)
VALUES('더 라스트 오브 어스 파트 1', '../images/img-game-last1.jpg', '액션', 1,'살기 위해 오늘도 길을 나선다 탐험 액션');

-- 게임사 등록
INSERT INTO tb_company (COMPANY_ID, PASSWORD, NAME, EMAIL, ADDR, TEL, PHONE_NUM, G_ID)
VALUES ('test', '123', 'TestGames', 'test@gmail.com', '판교', 'KT', '010-1111-2222', 1);
INSERT INTO tb_company (COMPANY_ID, PASSWORD, NAME, EMAIL, ADDR, TEL, PHONE_NUM, G_ID)
VALUES ('stest', '123', 'StreetGames', 'sgame@gmail.com', '판교', 'SKT', '010-3333-4444', 2);
INSERT INTO tb_company (COMPANY_ID, PASSWORD, NAME, EMAIL, ADDR, TEL, PHONE_NUM, G_ID)
VALUES ('dtest', '123', 'DuckGames', 'dgame@gmail.com', '강남', 'KT', '010-5555-6666', 3);
INSERT INTO tb_company (COMPANY_ID, PASSWORD, NAME, EMAIL, ADDR, TEL, PHONE_NUM, G_ID)
VALUES ('ftest', '123', 'FightingGames', 'fgame@gmail.com', '강남', 'SKT', '010-7777-8888', 4);

-- mtest 장바구니 설정
INSERT INTO tb_order (M_ID, C_ID, G_ID, AMOUNT)
VALUES('mtest', 'test', 1, 10000);
INSERT INTO tb_order (M_ID, C_ID, G_ID, AMOUNT)
VALUES('mtest', 'stest', 2, 30000);
INSERT INTO tb_order (M_ID, C_ID, G_ID, AMOUNT)
VALUES('mtest', 'dtest', 3, 50000);
INSERT INTO tb_order (M_ID, C_ID, G_ID, AMOUNT)
VALUES('mtest', 'ftest', 4, 100000);

-- mtest 결제내역 설정
INSERT INTO tb_order (M_ID, C_ID, G_ID, ORDER_DATE, AMOUNT, PAYMENT_STATUS)
VALUES('mtest', 'test', 1, '2023-05-06' ,10000, 'COMPLETE');

INSERT INTO tb_order (M_ID, C_ID, G_ID, ORDER_DATE, AMOUNT, PAYMENT_STATUS)
VALUES('mtest', 'stest', 2,'2023-02-07', 30000, 'COMPLETE');
INSERT INTO tb_order (M_ID, C_ID, G_ID, ORDER_DATE, AMOUNT, PAYMENT_STATUS)
VALUES('mtest', 'dtest', 3, '2023-04-15',50000, 'COMPLETE');
INSERT INTO tb_order (M_ID, C_ID, G_ID, ORDER_DATE, AMOUNT, PAYMENT_STATUS)
VALUES('mtest', 'ftest', 4,'2023-02-09', 20000,'COMPLETE');
INSERT INTO tb_order (M_ID, C_ID, G_ID, ORDER_DATE, AMOUNT, PAYMENT_STATUS)
VALUES('mtest', 'test', 1,'2023-03-24', 100000, 'COMPLETE');
INSERT INTO tb_order (M_ID, C_ID, G_ID, ORDER_DATE, AMOUNT, PAYMENT_STATUS)
VALUES('mtest', 'dtest', 3, '2023-04-30',50000, 'COMPLETE');
INSERT INTO tb_order (M_ID, C_ID, G_ID, ORDER_DATE, AMOUNT, PAYMENT_STATUS)
VALUES('mtest', 'dtest', 3,'2023-06-01', 50000, 'COMPLETE');
INSERT INTO tb_order (M_ID, C_ID, G_ID, ORDER_DATE, AMOUNT, PAYMENT_STATUS)
VALUES('mtest', 'test', 1,'2023-06-18', 20000, 'COMPLETE');


-- test 게임사 후원내역 설정
INSERT INTO tb_order (M_ID, C_ID, G_ID, ORDER_DATE, AMOUNT, PAYMENT_STATUS)
VALUES('lee', 'test', 1,'2023-02-06', 10000, 'COMPLETE');
INSERT INTO tb_order (M_ID, C_ID, G_ID, ORDER_DATE, AMOUNT, PAYMENT_STATUS)
VALUES('hong', 'test', 1, '2023-02-07', 100000, 'COMPLETE');
INSERT INTO tb_order (M_ID, C_ID, G_ID, ORDER_DATE, AMOUNT, PAYMENT_STATUS)
VALUES('mtest', 'test', 1, '2023-02-07', 50000, 'COMPLETE');
INSERT INTO tb_order (M_ID, C_ID, G_ID, ORDER_DATE, AMOUNT, PAYMENT_STATUS)
VALUES('kim', 'test', 1, '2023-03-07', 30000, 'COMPLETE');
INSERT INTO tb_order (M_ID, C_ID, G_ID, ORDER_DATE, AMOUNT, PAYMENT_STATUS)
VALUES('choi', 'test', 1, '2023-03-06', 20000, 'COMPLETE');
INSERT INTO tb_order (M_ID, C_ID, G_ID, ORDER_DATE, AMOUNT, PAYMENT_STATUS)
VALUES('choi', 'test', 1, '2023-03-07', 150000, 'COMPLETE');
INSERT INTO tb_order (M_ID, C_ID, G_ID, ORDER_DATE, AMOUNT, PAYMENT_STATUS)
VALUES('park', 'test', 1, '2023-04-07', 10000, 'COMPLETE');
INSERT INTO tb_order (M_ID, C_ID, G_ID, ORDER_DATE, AMOUNT, PAYMENT_STATUS)
VALUES('kim', 'test', 1, '2023-04-07', 30000, 'COMPLETE');
INSERT INTO tb_order (M_ID, C_ID, G_ID, ORDER_DATE, AMOUNT, PAYMENT_STATUS)
VALUES('lee', 'test', 1, '2023-04-06' ,100000, 'COMPLETE');
INSERT INTO tb_order (M_ID, C_ID, G_ID, ORDER_DATE, AMOUNT, PAYMENT_STATUS)
VALUES('hong', 'test', 1, '2023-05-07', 300000, 'COMPLETE');
INSERT INTO tb_order (M_ID, C_ID, G_ID, ORDER_DATE, AMOUNT, PAYMENT_STATUS)
VALUES('hong', 'test', 1, '2023-05-07', 70000, 'COMPLETE');
INSERT INTO tb_order (M_ID, C_ID, G_ID, ORDER_DATE, AMOUNT, PAYMENT_STATUS)
VALUES('kim', 'test', 1, '2023-05-07', 20000, 'COMPLETE');
INSERT INTO tb_order (M_ID, C_ID, G_ID, ORDER_DATE, AMOUNT, PAYMENT_STATUS)
VALUES('hong', 'test', 1, '2023-06-07', 250000, 'COMPLETE');
INSERT INTO tb_order (M_ID, C_ID, G_ID, ORDER_DATE, AMOUNT, PAYMENT_STATUS)
VALUES('hong', 'test', 1, '2023-06-07', 10000, 'COMPLETE');
INSERT INTO tb_order (M_ID, C_ID, G_ID, ORDER_DATE, AMOUNT, PAYMENT_STATUS)
VALUES('kim', 'test', 1, '2023-06-07', 10000, 'COMPLETE');