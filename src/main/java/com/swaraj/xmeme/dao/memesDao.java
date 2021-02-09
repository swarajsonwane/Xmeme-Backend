package com.swaraj.xmeme.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.swaraj.xmeme.model.memeDetails;



@Repository("memesDao")
public interface memesDao extends JpaRepository<memeDetails, Integer> {
}