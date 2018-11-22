package jp.co.rakus.ec2018c.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import jp.co.rakus.ec2018c.domain.Item;
import jp.co.rakus.ec2018c.repository.ItemRepository;

/**
 * itemの一覧表示に関するサービスクラス.
 * 
 * @author momo.senda
 *
 */
@Service
public class ViewItemListService {
	@Autowired
	private ItemRepository itemRepository;

	public List<Item> findAll() {
		return itemRepository.findAll();
	}

}
