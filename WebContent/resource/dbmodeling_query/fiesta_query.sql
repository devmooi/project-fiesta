-- 테이블 전체 삭제
DROP TABLE wish; -- 찜
DROP TABLE review; -- 리뷰
DROP TABLE orderdetail; -- 고객 의뢰 완료 내역 (계약서)
DROP TABLE custorderdetail;
DROP TABLE custorder; -- 고객 주문
DROP TABLE service; -- 서비스
DROP TABLE answer; -- 문의 내역
DROP TABLE question; -- 문의
DROP TABLE company; -- 업체
DROP TABLE comcategory; -- 업체 분류
DROP TABLE request; -- 고객 의뢰
DROP TABLE customer; -- 고객


-- 테이블 생성
-- comcategory
CREATE TABLE comcategory(
	comCategory_code INT NOT NULL PRIMARY KEY,
    comCategory_desc VARCHAR(45) NOT NULL
);

-- company
CREATE TABLE company(
	com_code INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
	com_email VARCHAR(45),
    com_pass VARCHAR(45),
    com_name VARCHAR(45) NOT NULL,
    com_tel VARCHAR(45),
    com_addr VARCHAR(150),
    com_img VARCHAR(150),
    com_desc VARCHAR(150),
    com_count INT,
    comCategory_code INT NOT NULL
);
ALTER TABLE company ADD constraint fk_company foreign key(comCategory_code) references comcategory(comCategory_code) on delete cascade;

-- service
CREATE TABLE service(
	service_code INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
    service_name VARCHAR(45),
    service_desc VARCHAR(45),
    service_img VARCHAR(45),
    service_tag VARCHAR(45),
    com_code INT NOT NULL
);
ALTER TABLE service ADD constraint fk_service foreign key(com_code) references company(com_code) on delete cascade;

-- customer
CREATE TABLE customer(
	cust_email VARCHAR(45) NOT NULL PRIMARY KEY,
    cust_name VARCHAR(45) NOT NULL,
    cust_pass VARCHAR(45) NOT NULL,
    cust_tel VARCHAR(45) NOT NULL,
    cust_group VARCHAR(45) NOT NULL
);

-- custorder(고객 주문)
CREATE TABLE custorder(
	order_code INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
    order_sysdate DATETIME NOT NULL,
    order_revdate VARCHAR(45) NOT NULL,
    order_place VARCHAR(45) NOT NULL,
    order_budget VARCHAR(45),
    order_require VARCHAR(45),
    order_condition VARCHAR(45),
    cust_email VARCHAR(45) NOT NULL,
    service_code INT NOT NULL,
    com_code INT NOT NULL
);
ALTER TABLE custorder ADD constraint fk_custorder_customer foreign key(cust_email) references customer(cust_email) on delete cascade;
ALTER TABLE custorder ADD constraint fk_custorder_service foreign key(service_code) references service(service_code) on delete cascade;
ALTER TABLE custorder ADD constraint fk_custorder_company foreign key(com_code) references company(com_code) on delete cascade;

-- 고객주문완료 내역(계약서)
CREATE TABLE custorderdetail(
	custdetail_code INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
	custdetail_totalprice INT,
	custdetail_desc VARCHAR(45),
	custdetail_completedate DATETIME,
	order_code INT NOT NULL,
	service_code INT NOT NULL,
	com_code INT NOT NULL,
	cust_email VARCHAR(45) NOT NULL
);
ALTER TABLE custorderdetail ADD constraint fk_custorderdetail_custorder foreign key(order_code) references custorder(order_code) on delete cascade;
ALTER TABLE custorderdetail ADD constraint fk_custorderdetail_service foreign key(service_code) references service(service_code) on delete cascade;
ALTER TABLE custorderdetail ADD constraint fk_custorderdetail_company foreign key(com_code) references company(com_code) on delete cascade;
ALTER TABLE custorderdetail ADD constraint fk_custorderdetail_customer foreign key(cust_email) references customer(cust_email) on delete cascade;

-- request(고객 의뢰)
CREATE TABLE request(
	request_code INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
	request_sysdate DATETIME NOT NULL,
	request_revdate VARCHAR(45) NOT NULL,
	request_place VARCHAR(45) NOT NULL,
	request_budget VARCHAR(45),
	request_require VARCHAR(45),
	request_fiesta VARCHAR(45) NOT NULL,
	cust_email VARCHAR(45) NOT NULL
);
ALTER TABLE request ADD constraint fk_request foreign key(cust_email) references customer(cust_email) on delete cascade;

-- orderdetail 고객 의뢰 완료 내역 (계약서)
CREATE TABLE orderdetail(
	detail_code INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
    detail_totalprice INT NOT NULL,
    detail_desc VARCHAR(45),
    detail_condition VARCHAR(45) NOT NULL,
    detail_completedate DATETIME,
    request_code INT NOT NULL,
    com_code INT NOT NULL
);
ALTER TABLE orderdetail ADD constraint fk_orderdetail_request foreign key(request_code) references request(request_code) on delete cascade;
ALTER TABLE orderdetail ADD constraint fk_orderdetail_company foreign key(com_code) references company(com_code) on delete cascade;

-- review
CREATE TABLE review(
	review_code VARCHAR(45) NOT NULL PRIMARY KEY,
    review_score INT,
    review_img VARCHAR(150),
    review_desc VARCHAR(45) NOT NULL,
    review_date DATETIME NOT NULL,
    cust_email VARCHAR(45) NOT NULL,
    service_code INT NOT NULL,
    com_code INT NOT NULL
);
ALTER TABLE review ADD constraint fk_review_customer foreign key(cust_email) references customer(cust_email) on delete cascade;
ALTER TABLE review ADD constraint fk_review_service foreign key(service_code) references service(service_code) on delete cascade;
ALTER TABLE review ADD constraint fk_review_company foreign key(com_code) references company(com_code) on delete cascade;

-- wish
CREATE TABLE wish(
	wish_code INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
	cust_email VARCHAR(45) NOT NULL,
    com_code INT NOT NULL
);
ALTER TABLE wish ADD constraint fk_wish_customer foreign key(cust_email) references customer(cust_email) on delete cascade;
ALTER TABLE wish ADD constraint fk_wish_company foreign key(com_code) references company(com_code) on delete cascade;

-- question
CREATE TABLE question(
	q_code INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
    q_date DATETIME NOT NULL,
    q_title VARCHAR(45),
    q_desc VARCHAR(45),
    q_condition VARCHAR(45) DEFAULT '답변대기',
    cust_email VARCHAR(45) NOT NULL,
    com_code INT NOT NULL
);
ALTER TABLE question ADD constraint fk_question_customer foreign key(cust_email) references customer(cust_email) on delete cascade;
ALTER TABLE question ADD constraint fk_question_company foreign key(com_code) references company(com_code) on delete cascade;

-- answer
CREATE TABLE answer(
	a_code INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
    a_date DATETIME NOT NULL,
    a_desc VARCHAR(45) NOT NULL,
    com_code INT NOT NULL,
    q_code INT NOT NULL,
    cust_email VARCHAR(45) NOT NULL
);
ALTER TABLE answer ADD constraint fk_answer_company foreign key(com_code) references company(com_code) on delete cascade;
ALTER TABLE answer ADD constraint fk_answer_question foreign key(q_code) references question(q_code) on delete cascade;
ALTER TABLE answer ADD constraint fk_answer_customer foreign key(cust_email) references customer(cust_email) on delete cascade;

-- INSERT 부분
INSERT INTO comcategory(comCategory_code, comCategory_desc) VALUES(01, '연예기획사');
INSERT INTO comcategory(comCategory_code, comCategory_desc) VALUES(02, '숙소');
INSERT INTO comcategory(comCategory_code, comCategory_desc) VALUES(03, '주류/렌탈');
INSERT INTO comcategory(comCategory_code, comCategory_desc) VALUES(04, '버스');
INSERT INTO comcategory(comCategory_code, comCategory_desc) VALUES(05, '음향/조명');
INSERT INTO comcategory(comCategory_code, comCategory_desc) VALUES(06, '보험회사');
INSERT INTO comcategory(comCategory_code, comCategory_desc) VALUES(07, '푸드트럭');
INSERT INTO comcategory(comCategory_code, comCategory_desc) VALUES(08, '의류');
INSERT INTO comcategory(comCategory_code, comCategory_desc) VALUES(09, '현수막');
INSERT INTO comcategory(comCategory_code, comCategory_desc) VALUES(10, '협찬');

-- company 가데이터 직접 입력 : 주류/렌탈, 현수막, 협찬
INSERT INTO company(com_name, com_tel, com_img, com_desc, comCategory_code)
VALUES('행복을 만드는 샘푸드', '010-4556-8302', 'resource/img/drink1.jpg', '주류 이외 생수, 과자, 통조림, 컵라면, 일회용품 등 제공', 3);
INSERT INTO company(com_name, com_tel, com_addr, com_img, com_desc, comCategory_code)
VALUES('한음기획', '02-6080-7727', '경기 수원시 팔달구 인계로124번길 43 대흥빌딩', 'resource/img/drink2.jpg', '행사기획, 기업마케팅, 행사장비 렌탈 전문 기업', 3);
INSERT INTO company(com_name, com_tel, com_addr, com_img, com_desc, comCategory_code)
VALUES('㈜ 정 유통', '010-3271-2491', '서울 성동구 마장로31길 33', 'resource/img/drink3.jpg', '행사기획, 기업마케팅, 행사장비 렌탈 전문 기업', 3);

INSERT INTO company(com_name, com_tel, com_addr, com_img, com_desc, comCategory_code)
VALUES('플랜빛', '02-556-5515', '서울특별시 서초구 신반포로45길 74, 6층(잠원동)', 'resource/img/banner1.jpg', '기획, 제작에서 설치, 부착, 배포, 홍보결과까지 통합서비스', 9);
INSERT INTO company(com_name, com_tel, com_addr, com_img, com_desc, comCategory_code)
VALUES('백산현수막', '1599-1799', '경기도 화성시 매송면 원평리 101-3', 'resource/img/banner2.jpg', '모든현수막 제작부터 시공까지 다이렉트로! 대형현수막 전문업체', 9);
INSERT INTO company(com_name, com_tel, com_addr, com_img, com_desc, comCategory_code)
VALUES('주컴퍼니', '1661-2992', '인천광역시 계양구 효성동 304-19번지 1층~4층(전층)', 'resource/img/banner3.jpg', '다양한 업종, 활용도 높은 시트지 광고까지 최신식 자체 제작 시스템', 9);

INSERT INTO company(com_name, com_tel, com_addr, com_img, com_desc, comCategory_code)
VALUES('이브코드', '031-714-3011', '경기도 성남시 분당구 성남대로343번길 10-6 (정자동) 3층 344호', 'resource/img/brand1.jpg', '건국대학교 D.O.N 행사 협찬 경력', 10);
INSERT INTO company(com_name, com_tel, com_addr, com_img, com_desc, comCategory_code)
VALUES('무백', '02-511-3060', '서울특별시 성동구 뚝섬로1길 25(성수동1가) 서울숲한라에코밸리 901호', 'resource/img/brand2.jpg', '이화여대 영상제 제품 협찬 경력', 10);
INSERT INTO company(com_name, com_tel, com_addr, com_img, com_desc, comCategory_code)
VALUES('이땅', '1544-7437', '대전광역시 유성구 테크노2로 125-5', 'resource/img/brand3.jpg', '대학 패션쇼와 예술고교 협찬 대폭 확대', 10);

SELECT comCategory_code, comCategory_desc FROM comcategory;