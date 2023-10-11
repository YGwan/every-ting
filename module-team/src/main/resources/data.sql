-- 과팅 더미 데이터
insert into team (id, name, university, major, code, member_limit, gender, created_at,
                  updated_at)
values (1,
        "여기여기모여라",
        "단국대학교",
        "응용 컴퓨터 공학과",
        "Abeaegs3bcq!",
        3,
        "FEMALE", now(), now());

-- 팀 지역 더미 데이터
insert into team_region (id, team_id, region)
values (1, 1, "서울");

insert into team_region (id, team_id, region)
values (2, 1, "경기 남부");

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
