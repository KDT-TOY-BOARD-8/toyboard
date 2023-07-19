-- 테스트용 회원 추가
insert into board_user_tb (user_id, created_at, updated_at, password, email, nickname, username)
values (1, now(), now(), '$2a$10$ZmEhAXrz68geKq1d24yXqOldesyWfZwkH1du49ARgR5JoOxwG7E0y', 'teset1@test.com',
        'test1nickname', 'test1'),
       (2, now(), now(), '$2a$10$.SBX6wFcmBEbloFLXEIvjuAKO2V9U2WKRIqdbz7k64U1r4K0hy14u', 'teset2@test.com',
        'test2nickname', 'test2'),
       (3, now(), now(), '$2a$10$tik0Pjku4CmVz6Nn5SWb6OvpPanMZjAtiCLqMH8uGinkMGf8p6dAC', 'teset3@test.com',
        'test3nickname', 'test3'),
       (4, now(), now(), '$2a$10$q3sdSR5kjWuCp6f31lSJPuDHurej82Zsk4BpUs0QlupprBrTfdaDK', 'admin@admin.com', 'admin',
        'admin')
;

-- 1번 - 새싹회원
-- 2번 - 우수회원
-- 3번 - 블랙회원
-- 4번 - 관리자
insert into board_authority_tb (user_id, authority)
values ((select user_id from board_user_tb where username = 'test1'), 'SPROUT'),
       ((select user_id from board_user_tb where username = 'test2'), 'GREAT'),
       ((select user_id from board_user_tb where username = 'test3'), 'BLACK'),
       ((select user_id from board_user_tb where username = 'admin'), 'ADMIN')
;

-- Test 게시글
insert into board_tb (board_id, content, title, user_id)
values (1, 'Sprout Board User Test Content Loren zcvklerwnnkljzcxv eqwr', 'Test Title', 1),
       (2, 'Sprout Board User Test Content Loren zcvklerwnnkljzcxv eqwr', 'Test Title', 1),
       (3, 'Sprout Board User Test Content Loren zcvklerwnnkljzcxv eqwr', 'Test Title', 1),
       (4, 'Sprout Board User Test Content Loren zcvklerwnnkljzcxv eqwr', 'Test Title', 1),
       (5, 'Sprout Board User Test Content Loren zcvklerwnnkljzcxv eqwr', 'Test Title', 1),
       (6, 'Sprout Board User Test Content Loren zcvklerwnnkljzcxv eqwr', 'Test Title', 1),
       (7, 'Sprout Board User Test Content Loren zcvklerwnnkljzcxv eqwr', 'Test Title', 1),
       (8, 'Sprout Board User Test Content Loren zcvklerwnnkljzcxv eqwr', 'Test Title', 1),
       (9, 'Sprout Board User Test Content Loren zcvklerwnnkljzcxv eqwr', 'Test Title', 1),
       (10, 'Sprout Board User Test Content Loren zcvklerwnnkljzcxv eqwr', 'Test Title', 1),
       (11, 'Great Board User Test Content Loren zcvklerwnnkljzcxv eqwr', 'Test Title', 2),
       (12, 'Great Board User Test Content Loren zcvklerwnnkljzcxv eqwr', 'Test Title', 2),
       (13, 'Great Board User Test Content Loren zcvklerwnnkljzcxv eqwr', 'Test Title', 2),
       (14, 'Great Board User Test Content Loren zcvklerwnnkljzcxv eqwr', 'Test Title', 2),
       (15, 'Great Board User Test Content Loren zcvklerwnnkljzcxv eqwr', 'Test Title', 2),
       (16, 'Great Board User Test Content Loren zcvklerwnnkljzcxv eqwr', 'Test Title', 2),
       (17, 'Great Board User Test Content Loren zcvklerwnnkljzcxv eqwr', 'Test Title', 2),
       (18, 'Great Board User Test Content Loren zcvklerwnnkljzcxv eqwr', 'Test Title', 2),
       (19, 'Great Board User Test Content Loren zcvklerwnnkljzcxv eqwr', 'Test Title', 2),
       (20, 'Great Board User Test Content Loren zcvklerwnnkljzcxv eqwr', 'Test Title', 2),
       (21, 'Sprout Board User Test Content Loren zcvklerwnnkljzcxv eqwr', 'Test Title', 1),
       (22, 'Sprout Board User Test Content Loren zcvklerwnnkljzcxv eqwr', 'Test Title', 1),
       (23, 'Sprout Board User Test Content Loren zcvklerwnnkljzcxv eqwr', 'Test Title', 1),
       (24, 'Sprout Board User Test Content Loren zcvklerwnnkljzcxv eqwr', 'Test Title', 1),
       (25, 'Sprout Board User Test Content Loren zcvklerwnnkljzcxv eqwr', 'Test Title', 1),
       (26, 'Sprout Board User Test Content Loren zcvklerwnnkljzcxv eqwr', 'Test Title', 1),
       (27, 'Sprout Board User Test Content Loren zcvklerwnnkljzcxv eqwr', 'Test Title', 1),
       (28, 'Sprout Board User Test Content Loren zcvklerwnnkljzcxv eqwr', 'Test Title', 1),
       (29, 'Sprout Board User Test Content Loren zcvklerwnnkljzcxv eqwr', 'Test Title', 1),
       (30, 'Sprout Board User Test Content Loren zcvklerwnnkljzcxv eqwr', 'Test Title', 1),
       (31, 'Great Board User Test Content Loren zcvklerwnnkljzcxv eqwr', 'Test Title', 2),
       (32, 'Great Board User Test Content Loren zcvklerwnnkljzcxv eqwr', 'Test Title', 2),
       (33, 'Great Board User Test Content Loren zcvklerwnnkljzcxv eqwr', 'Test Title', 2),
       (34, 'Great Board User Test Content Loren zcvklerwnnkljzcxv eqwr', 'Test Title', 2),
       (35, 'Great Board User Test Content Loren zcvklerwnnkljzcxv eqwr', 'Test Title', 2),
       (36, 'Great Board User Test Content Loren zcvklerwnnkljzcxv eqwr', 'Test Title', 2),
       (37, 'Great Board User Test Content Loren zcvklerwnnkljzcxv eqwr', 'Test Title', 2),
       (38, 'Great Board User Test Content Loren zcvklerwnnkljzcxv eqwr', 'Test Title', 2),
       (39, 'Great Board User Test Content Loren zcvklerwnnkljzcxv eqwr', 'Test Title', 2)
;