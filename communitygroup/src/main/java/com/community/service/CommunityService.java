package com.community.service;

import com.community.beans.AccountCommunity;
import com.community.beans.Community;
import com.community.beans.CommunityUsers;
import com.community.beans.User;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface CommunityService {

    ResponseEntity saveCommunity(Community community);
    ResponseEntity joinCommunity(AccountCommunity accountCommunity);
    List<Community> getAllCommunities(String type);
    List<CommunityUsers> getAllAccountsByCommunityId(String communtiyId);

    ResponseEntity saveUser(User user);
}
