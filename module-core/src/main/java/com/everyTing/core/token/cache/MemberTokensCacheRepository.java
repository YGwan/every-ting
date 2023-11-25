package com.everyTing.core.token.cache;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MemberTokensCacheRepository extends CrudRepository<MemberTokensCache, Long> {
}
