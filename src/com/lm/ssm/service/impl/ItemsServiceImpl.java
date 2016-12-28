package com.lm.ssm.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.lm.ssm.dao.ItemsMapper;
import com.lm.ssm.domain.Items;
import com.lm.ssm.service.IItemsService;

@Service
public class ItemsServiceImpl implements IItemsService {
	
	@Resource
	private ItemsMapper itemsMapper;

	@Override
	public List<Items> findAll() {
		return itemsMapper.findAll();
	}

	@Override
	public Items findById(Integer id) {
		return itemsMapper.findById(id);
	}

	@Override
	public void saveOrUpdate(Items item) {
		itemsMapper.updateByExample(item);
		
	}

}
