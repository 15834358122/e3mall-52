package cn.e3mall.service.impl;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

import cn.e3mall.common.pojo.DataGridResult;
import cn.e3mall.common.pojo.E3Result;
import cn.e3mall.common.utils.IDUtils;
import cn.e3mall.mapper.TbItemDescMapper;
import cn.e3mall.mapper.TbItemMapper;
import cn.e3mall.pojo.TbItem;
import cn.e3mall.pojo.TbItemDesc;
import cn.e3mall.pojo.TbItemExample;
import cn.e3mall.service.ItemService;
@Service
public class ItemServiceImpl implements ItemService{
	
	@Autowired
	private TbItemMapper tbitemMapper;
	@Autowired
	private TbItemDescMapper tbItemDescMapper;
	
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

	@Override
	public E3Result addItem(TbItem item, String desc) {
//		1、生成商品id
		long itemId = IDUtils.genItemId();
//		2、补全TbItem中的属性
		item.setId(itemId);
		//1-正常,2-下架,3-删除
		item.setStatus((byte)1);
		item.setCreated(new Date());
		item.setUpdated(new Date());
//		3、插入到商品表。
		tbitemMapper.insert(item);
//		4、创建一个商品描述表对应的pojo
		TbItemDesc tbItemDesc = new TbItemDesc();
//		5、补全属性
		tbItemDesc.setItemId(itemId);
		tbItemDesc.setItemDesc(desc);
		tbItemDesc.setCreated(new Date());
		tbItemDesc.setUpdated(new Date());
//		6、向商品描述表插入数据
		tbItemDescMapper.insert(tbItemDesc);
//		7、返回E3Result
		return E3Result.ok();
	}
	
	
	
}
