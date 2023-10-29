-- 과팅 더미 데이터
insert into team (id, name, university, major, code, member_limit, member_number, gender, created_at,
                  updated_at)
values (1,
        "여기여기모여라",
        "단국대학교",
        "컴퓨터공학과",
        "여기여기모여라_-k97ip1jhn",
        3, 3,
        "FEMALE", now(), now());

insert into team (id, name, university, major, code, member_limit, member_number, gender, created_at,
                  updated_at)
values (2,
        "리얼솔라드래곤",
        "경기대학교",
        "신소재공학과",
        "리얼솔라드래곤_3k97ip1j-n",
        3, 3,
        "MALE", now(), now());

insert into team (id, name, university, major, code, member_limit, member_number, gender, created_at,
                  updated_at)
values (3,
        "보드게임러",
        "중앙대학교",
        "신소재공학과",
        "보드게임러_1234abcd-n",
        3, 1,
        "MALE", now(), now());

insert into team (id, name, university, major, code, member_limit, member_number, gender, created_at,
                  updated_at)
values (4,
        "보드게임러",
        "고려대학교",
        "철학과",
        "보드게임러_ack-12o812",
        3, 1,
        "FEMALE", now(), now());

-- 팀 멤버 더미 데이터
insert into team_member (id, team_id, member_id, role, created_at, updated_at)
values (1, 1, 1, 'LEADER', now(), now()), (2, 1, 2, 'MEMBER', now(), now()), (3, 1, 8, 'MEMBER', now(), now());

insert into team_member (id, team_id, member_id, role, created_at, updated_at)
values (4, 2, 4, 'LEADER', now(), now()), (5, 2, 7, 'MEMBER', now(), now()), (6, 2, 6, 'MEMBER', now(), now());

insert into team_member (id, team_id, member_id, role, created_at, updated_at)
values (7, 3, 7, 'LEADER', now(), now());

insert into team_member (id, team_id, member_id, role, created_at, updated_at)
values (8, 4, 8, 'LEADER', now(), now());

-- 팀 지역 더미 데이터
insert into team_region (id, team_id, region)
values (1, 1, "서울");

insert into team_region (id, team_id, region)
values (2, 1, "경기 남부");

insert into team_region (id, team_id, region)
values (3, 2, "서울");

insert into team_region (id, team_id, region)
values (4, 3, "서울");

insert into team_region (id, team_id, region)
values (5, 4, "서울");




