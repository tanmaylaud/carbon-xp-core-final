package com.community.service;

import com.community.beans.AccountCommunity;
import com.community.beans.Community;
import com.community.beans.CommunityUsers;
import com.community.beans.User;
import com.community.repository.AccountCommunityRepository;
import com.community.repository.CommunityRepository;

import com.community.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class CommunityServiceImpl implements CommunityService{


    @Autowired
    CommunityRepository communityRepository;

    @Autowired
    AccountCommunityRepository accountCommunityRepository;

    @Autowired
    UserRepository userRepository;

    @Autowired
    MongoTemplate mongoTemplate;

    public ResponseEntity saveCommunity(Community community){
        try{
            community.setCommunityId(System.currentTimeMillis());
            communityRepository.save(community);
        }catch (Exception exception){
            return new ResponseEntity<>("Some error occurred"+exception, HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return new ResponseEntity<>("Community:"+ community.getCommunityName()+" Added", HttpStatus.CREATED);
    }

    @Override
    public ResponseEntity joinCommunity(AccountCommunity accountCommunity){

        try{
            Query query  = new Query();
            query.addCriteria(Criteria.where("accountNumber").is(accountCommunity.getAccountNumber()))
                    .addCriteria(Criteria.where("communityId").is(accountCommunity.getCommunityId()));
            Long count = mongoTemplate.count(query, AccountCommunity.class);
            if(count > 0){
                return new ResponseEntity<>("The account is already a member of the community", HttpStatus.CONFLICT);
            }
            accountCommunityRepository.save(accountCommunity);
            return new ResponseEntity<>("The account is added to the community : "+accountCommunity.getCommunityId(),
                    HttpStatus.OK);
        }
        catch(Exception exception){
            return new ResponseEntity<>("Some error occorred"+exception, HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }

    @Override
    public List<Community> getAllCommunities(String type) {

        try{
           /* Query query  = new Query();
            query.addCriteria(Criteria.where("communityType").is(type));
            return mongoTemplate.findAll(Community.class);*/
            System.out.println("******* Getting the findByCommunityType");
           return communityRepository.findByCommunityType(type);
        }
        catch(Exception exception){
            exception.printStackTrace();
        }
        return null;
    }

    @Override
    public List<CommunityUsers> getAllAccountsByCommunityId(Long communtiyId) {

        try{

            List<CommunityUsers> communityUsersList = new ArrayList<>();
            List<AccountCommunity> accountCommunities = accountCommunityRepository.findByCommunityId(communtiyId);

            List<User> userList = new ArrayList<>();
            for(AccountCommunity accountCommunity : accountCommunities) {
                Optional<User> userOptional = userRepository.findByAccountNumber(accountCommunity.getAccountNumber());
                userOptional.ifPresent(x -> userList.add(x));
            }
            for(User user : userList){
                CommunityUsers communityUser = new CommunityUsers();
                communityUser.setAccountNumber(user.getAccountNumber());
                communityUser.setName(user.getUserName());
                communityUsersList.add(communityUser);
            }
            return communityUsersList;
        }
        catch(Exception exception){
            exception.printStackTrace();
        }
        return null;
    }

}
