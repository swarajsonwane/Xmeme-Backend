package com.swaraj.xmeme.service;

import java.util.ArrayList;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.swaraj.xmeme.dao.memesDao;
import com.swaraj.xmeme.model.idDetails;
import com.swaraj.xmeme.model.memeDetails;


@Service("memeService")
@Transactional
public class memeService {
	
	@Autowired
	private memesDao memesrepo;
	
	public List<memeDetails> listAll()
	{
		return memesrepo.findAll();
	}

	public void save(memeDetails memes)
	{
		memesrepo.save(memes);
	}
	
	public memeDetails get(Integer id)
	{
		return memesrepo.findById(id).get();
	}
	public void delete(Integer id)
	{
		memesrepo.deleteById(id);
	}
	
	public List<memeDetails>listLatestRecords()
	{
		Pageable paging =PageRequest.of(0,100, Sort.by(Sort.Direction.DESC,"id"));
		 Page<memeDetails> pagedResult = memesrepo.findAll(paging);
		 if(pagedResult.hasContent()) {
	            return pagedResult.getContent();
	        } else {
	            return new ArrayList<memeDetails>();
	        }
	}
	
	public long count()
	{
		return memesrepo.count();
	}
	
	
}
