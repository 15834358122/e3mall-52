package cn.e3mall.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.e3mall.common.pojo.DataGridResult;
import cn.e3mall.common.pojo.E3Result;
import cn.e3mall.pojo.TbItem;
import cn.e3mall.service.ItemService;




@Controller
public class ItemController {
	
	@Autowired
	private ItemService itemService; 
	
	@RequestMapping("/item/{itemid}")
	@ResponseBody
	public TbItem getItemById(@PathVariable long itemid){
		TbItem item = itemService.getItemById(itemid);
		return item; 
	}
	
	@RequestMapping("/item/list")
	@ResponseBody
	public DataGridResult getItemList(Integer page,Integer rows){
		DataGridResult itemList = itemService.getItemList(page, rows);
		return itemList;
	}
	
	@RequestMapping(value="/item/save",method=RequestMethod.POST)
	@ResponseBody
	public E3Result addItem(TbItem item,String desc){
		E3Result e3Result = itemService.addItem(item, desc);
		return e3Result;
	}
}
