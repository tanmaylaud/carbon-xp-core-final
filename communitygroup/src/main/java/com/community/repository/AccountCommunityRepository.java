package com.community.repository;

import com.community.beans.AccountCommunity;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AccountCommunityRepository extends MongoRepository<AccountCommunity, String> {
    List<AccountCommunity> findByCommunityId(String communityId);
}
