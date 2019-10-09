package com.community.repository;

import com.community.beans.Community;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CommunityRepository extends MongoRepository<Community, String>{

    List<Community> findByCommunityType(String type);

    void findByCommunityId(Long communtiyId);
}
