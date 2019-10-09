package com.community.service;

import com.community.beans.AccountCommunity;
import com.community.beans.Community;
import com.community.beans.CommunityUsers;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface CommunityService {

    ResponseEntity saveCommunity(Community community);
    ResponseEntity joinCommunity(AccountCommunity accountCommunity);
    List<Community> getAllCommunities(String type);
    List<CommunityUsers> getAllAccountsByCommunityId(Long communtiyId);
}
