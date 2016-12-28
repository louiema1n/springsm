package com.lm.ssm.service;

import java.util.List;

import com.lm.ssm.domain.Items;

public interface IItemsService {

	List<Items> findAll();

	Items findById(Integer id);

	void saveOrUpdate(Items item);

}
