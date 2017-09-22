package com.ximo.springbootsellmaster.repository;

import com.ximo.springbootsellmaster.domain.ProductCategory;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * 商品类目仓库
 * Created by 朱文赵
 * 2017/9/9
 */
public interface ProductCategoryRepository extends JpaRepository<ProductCategory, Integer> {

    /**
     * 根据商品类型来查询
     * @param categoryType Integer类型的集合类型
     * @return
     */
    List<ProductCategory> findByCategoryTypeIn(List<Integer> categoryType);

}
