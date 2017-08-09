package cn.e3mall.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.e3mall.common.pojo.TreeNode;
import cn.e3mall.mapper.TbItemCatMapper;
import cn.e3mall.pojo.TbItemCat;
import cn.e3mall.pojo.TbItemCatExample;
import cn.e3mall.pojo.TbItemCatExample.Criteria;
import cn.e3mall.service.ItemCatService;
@Service
public class ItemCatServiceImpl implements ItemCatService{
	
	@Autowired
	private TbItemCatMapper tbItemCatMapper;
	
	@Override
	public List<TreeNode> getItemCatList(long parentId) {
		TbItemCatExample tbItemCatExample = new TbItemCatExample();
		Criteria criteria = tbItemCatExample.createCriteria();
		criteria.andParentIdEqualTo(parentId);
		List<TbItemCat> list = tbItemCatMapper.selectByExample(tbItemCatExample);
		List<TreeNode> treeNodes = new ArrayList<>();
		for (TbItemCat tbItemCat : list) {
			TreeNode treeNode = new TreeNode();
			treeNode.setId(tbItemCat.getId());
			treeNode.setText(tbItemCat.getName());;
			//父节点判断
			treeNode.setState(tbItemCat.getIsParent()?"closed":"open");
			//添加到列表
			treeNodes.add(treeNode);
		}
		return treeNodes;
	}

}
