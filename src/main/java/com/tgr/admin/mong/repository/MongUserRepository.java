package com.tgr.admin.mong.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.tgr.admin.bean.MongUser;


@Repository
public interface MongUserRepository extends MongoRepository<MongUser, String> {

}
