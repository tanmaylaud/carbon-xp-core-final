package com.community.service;

import com.community.beans.*;
import com.community.repository.AccountCommunityRepository;
import com.community.repository.CommunityRepository;

import com.community.repository.TransactionRepository;
import com.community.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Comparator;
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
    TransactionRepository transactionRepository;

    @Autowired
    UserRepository userRepository;

    @Autowired
    MongoTemplate mongoTemplate;

    public ResponseEntity saveCommunity(Community community){
        try{
            community.setCommunityId(System.currentTimeMillis());
            Query query = new Query();
            query.addCriteria(Criteria.where("communityName").is(community.getCommunityName()));
            Long count = mongoTemplate.count(query, Community.class);
            if(count > 0){
                return new ResponseEntity("The community name already present, use some other name",
                        HttpStatus.CONFLICT);
            }
            communityRepository.save(community);
        }catch (Exception exception){
            return new ResponseEntity<>("Some error occurred"+exception, HttpStatus.INTERNAL_SERVER_ERROR);
        }
        System.out.println("******** Community created : id :"+community.getCommunityId());
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
            System.out.println("******** Community joined  : id :"+ accountCommunity.getAccountNumber() +" and Community " +
                    "Id : "+ accountCommunity.getCommunityId());
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
            System.out.println("******* Getting the findByCommunityType Type : "+type);
           return communityRepository.findByCommunityType(type);
        }
        catch(Exception exception){
            exception.printStackTrace();
        }
        System.out.println("******** returning the null");
        return null;
    }

    @Override
    public List<CommunityUsers> getAllAccountsByCommunityId(String communityId) {

        try{

           /* Query query  = new Query();
            query.addCriteria(Criteria.where("communityId").is(communityId));
            AccountCommunity accountCommunity1 = mongoTemplate.findOne(query, AccountCommunity.class);
            System.out.println(accountCommunity1)*/;

            List<CommunityUsers> communityUsersList = new ArrayList<>();
            List<AccountCommunity> accountCommunities = accountCommunityRepository.findByCommunityId(communityId);

            System.out.println("****** Account community : "+accountCommunities);
            List<User> userList = new ArrayList<>();
            for(AccountCommunity accountCommunity : accountCommunities) {
                System.out.println("****** inside if : "+accountCommunity.getAccountNumber());
                User user = userRepository.findByAccountNumber(accountCommunity.getAccountNumber());
                System.out.println("****"+user.getUserName());

                userList.add(user);
            }

            System.out.println("******* UserList : "+userList);
            for(User user : userList){
                CommunityUsers communityUser = new CommunityUsers();
                communityUser.setAccountNumber(user.getAccountNumber());
                communityUser.setName(user.getUserName());
                communityUser.setEmailId(user.getEmailId());

                List<Transaction> optionalTransaction= transactionRepository.findByAccountNumber(user.getAccountNumber());
                Optional<Transaction> transaction =
                        optionalTransaction.stream().max(Comparator.comparing(Transaction::getTransactionId));
                communityUser.setCarbonBalance(transaction.get().getCarbonBalance());
                communityUsersList.add(communityUser);
            }

            System.out.println("********* Community User List : "+communityUsersList);

            communityUsersList.stream().sorted(Comparator.comparing(CommunityUsers::getCarbonBalance).reversed());
            int i = 0;
            for(CommunityUsers communityUser : communityUsersList){
                communityUser.setRankInGroup(++i);
            }
            return communityUsersList;
        }
        catch(Exception exception){
            exception.printStackTrace();
        }
        return null;
    }

    @Override
    public ResponseEntity saveUser(User user) {
        userRepository.save(user);
        return new ResponseEntity("Saved", HttpStatus.OK);
    }

}
