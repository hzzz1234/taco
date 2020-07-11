package com.hzzz.data;

import com.hzzz.dto.Order;
import com.hzzz.dto.Taco;

/**
 * Created by zhen.huaz on 2020/7/8.
 */
public interface OrderRepository {
    Order save(Order order);

}
