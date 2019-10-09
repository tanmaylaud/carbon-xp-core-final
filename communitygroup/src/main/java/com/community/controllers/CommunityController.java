package com.community.controllers;


import com.community.beans.AccountCommunity;
import com.community.beans.Community;
import com.community.beans.CommunityUsers;
import com.community.beans.User;
import com.community.service.CommunityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/communities")
public class CommunityController {

    @Autowired
    CommunityService communityService;

    @PostMapping
    public ResponseEntity createCommunity(@RequestBody Community community){
        return communityService.saveCommunity(community);
    }

    @PostMapping("/saveUser")
    public ResponseEntity saveUser(@RequestBody User user){
        return communityService.saveUser(user);
    }

    /**
     * Mapping of Account Id and Community Id
     */
    @PostMapping("/joinCommunity")
    public ResponseEntity joinCommunity(@RequestBody AccountCommunity accountCommunity){
        return communityService.joinCommunity(accountCommunity);
    }

    @GetMapping("/{communityType}")
    public List<Community> getCommunitiesByType(@PathVariable("communityType") String communityType){
        System.out.println("********** getCommunitiesByType:"+communityType);
        return communityService.getAllCommunities(communityType);
    }

    @GetMapping("/allAccountsByCommunityId/{communityId}")
    public List<CommunityUsers> getAllAccountsByCommunityId(@PathVariable("communityId") String communityId){
        System.out.println("******** Community : "+communityId);
        return communityService.getAllAccountsByCommunityId(communityId);
    }

}
