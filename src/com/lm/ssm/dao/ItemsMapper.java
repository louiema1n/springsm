package com.lm.ssm.dao;

import java.util.List;

import com.lm.ssm.domain.Items;

public interface ItemsMapper {
	public List<Items> findAll();

	public Items findById(Integer id);

	public void updateByExample(Items item);
}
