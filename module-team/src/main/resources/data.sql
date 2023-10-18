-- 과팅 더미 데이터
insert into team (id, name, university, major, code, member_limit, member_number, gender, created_at,
                  updated_at)
values (1,
        "여기여기모여라",
        "단국대학교",
        "응용 컴퓨터 공학과",
        "Abeaegs3bcq4!",
        3, 2,
        "FEMALE", now(), now());

insert into team (id, name, university, major, code, member_limit, member_number, gender, created_at,
                  updated_at)
values (2,
        "리얼솔라드래곤",
        "경기대학교",
        "신소재 공학과",
        "Abeaegs3bcq1!",
        3, 2,
        "MALE", now(), now());

insert into team (id, name, university, major, code, member_limit, member_number, gender, created_at,
                  updated_at)
values (3,
        "보드게임러",
        "중앙대학교",
        "신소재 공학과",
        "Abeaegs3bcq!2",
        3, 1,
        "MALE", now(), now());

insert into team (id, name, university, major, code, member_limit, member_number, gender, created_at,
                  updated_at)
values (4,
        "보드게임러",
        "중앙대학교",
        "신소재 공학과",
        "Abeaegs3bcq!12",
        3, 1,
        "FEMALE", now(), now());

-- 팀 지역 더미 데이터
insert into team_region (id, team_id, region)
values (1, 1, "서울");

insert into team_region (id, team_id, region)
values (2, 1, "경기 남부");

insert into team_region (id, team_id, region)
values (3, 2, "서울");

-- 팀 해시태그 더미 데이터
insert into team_hashtag (id, team_id, content, created_at)
values (1,
        1,
        "모두E", now());

insert into team_hashtag (id, team_id, content, created_at)
values (2,
        1,
        "보드게임", now());

-- 팀 멤버 더미 데이터
insert into team_member (id, team_id, member_id, role, created_at, updated_at)
values (1,
        1,
        1,
        'LEADER', now(), now());

insert into team_member (id, team_id, member_id, role, created_at, updated_at)
values (2,
        1,
        2,
        'MEMBER', now(), now());

insert into team_member (id, team_id, member_id, role, created_at, updated_at)
values (3,
        2,
        3,
        'LEADER', now(), now());

insert into team_member (id, team_id, member_id, role, created_at, updated_at)
values (4,
        2,
        4,
        'MEMBER', now(), now());

insert into team_member (id, team_id, member_id, role, created_at, updated_at)
values (5,
        4,
        2,
        'LEADER', now(), now());

insert into team_member (id, team_id, member_id, role, created_at, updated_at)
values (6,
        3,
        5,
        'LEADER', now(), now());

-- 팀 좋아요 더미 데이터
insert into team_like (id, from_team_member_id, from_team_id, to_team_id, created_at)
values (
        1,
        1, 1, 2, now()
       );

insert into team_like (id, from_team_member_id, from_team_id, to_team_id, created_at)
values (
       2,
       2, 1, 2, now()
       );
