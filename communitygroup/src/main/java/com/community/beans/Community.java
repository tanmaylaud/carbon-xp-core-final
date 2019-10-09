package com.community.beans;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document
public class Community{

    private Long communityId;
    private String communityName;
    private String communityType;

    public Long getCommunityId() {
        return communityId;
    }

    public Community setCommunityId(Long communityId) {
        this.communityId = communityId;
        return this;
    }

    public String getCommunityName() {
        return communityName;
    }

    public Community setCommunityName(String communityName) {
        this.communityName = communityName;
        return this;
    }

    public String getCommunityType() {
        return communityType;
    }

    public Community setCommunityType(String communityType) {
        this.communityType = communityType;
        return this;
    }
}
