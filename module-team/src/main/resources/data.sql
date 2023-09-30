-- 과팅 더미 데이터
insert into team (id, name, region, university, major, code, member_limit, gender, created_at,
                  updated_at)
values (1,
        "여기여기모여라",
        "경기 남부",
        "단국대학교",
        "응용 컴퓨터 공학과",
        "Abeaegs3bcq!",
        3,
        "FEMALE", now(), now());

-- 팀 해시태그 더미 데이터
insert into team_hashtag (id, team_id, content, created_at)
values (1,
        1,
        "모두E", now());

insert into team_hashtag (id, team_id, content, created_at)
values (2,
        1,
        "보드게임", now());
