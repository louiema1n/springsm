package com.lm.ssm.controller;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.lm.ssm.domain.Items;
import com.lm.ssm.service.IItemsService;

@Controller
@RequestMapping("/items")
public class ItemsController {
	
	@Resource
	private IItemsService itemsService;
	
	//查询所有
	@RequestMapping("list")
	public String list(Model model) {
		List<Items> list = itemsService.findAll();
		model.addAttribute("itemsList", list);
		return "itemsList";
	}
	
	//修改
	@RequestMapping("edit")
	public String edit(Integer id, Model model) {
		Items item = itemsService.findById(id);
		model.addAttribute("item", item);
		return "editItem";
	}
	
	//修改保存
	@RequestMapping("saveOrUpdate")
	public String saveOrUpdate(Items item) {
		itemsService.saveOrUpdate(item);
		return "redirect:list.do";
	}

}
