package cn.e3mall.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

import cn.e3mall.common.pojo.DataGridResult;
import cn.e3mall.mapper.TbItemMapper;
import cn.e3mall.pojo.TbItem;
import cn.e3mall.pojo.TbItemExample;
import cn.e3mall.service.ItemService;
@Service
public class ItemServiceImpl implements ItemService{
	
	@Autowired
	private TbItemMapper tbitemMapper;
	
	@Override
	public TbItem getItemById(long itemId) {
		TbItem tbItem = tbitemMapper.selectByPrimaryKey(itemId);
		return tbItem;
	}

	@Override
	public DataGridResult getItemList(int page, int rows) {
		//设置分页信息
		PageHelper.startPage(page, rows);
		//执行查询
		TbItemExample example = new TbItemExample();
		List<TbItem> list = tbitemMapper.selectByExample(example);
		//获取查询结果
		PageInfo<TbItem> pageInfo = new PageInfo<>(list);
		//获取总记录数
		long total = pageInfo.getTotal();
		//获取商品列表
		DataGridResult dataGridResult = new DataGridResult();
		dataGridResult.setTotal(total);
		dataGridResult.setRows(list);
		return dataGridResult;
	}
	
	
	
}
