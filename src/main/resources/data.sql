
/*
商品信息表
*/

CREATE TABLE `product_info`(

  `product_id` VARCHAR(32) NOT NULL,
  `product_name` VARCHAR(64) NOT NULL COMMENT '商品名称' ,
  `product_price` DECIMAL(8,2) NOT NULL COMMENT '商品单价',
  `product_stock` INT NOT NULL COMMENT '库存',
  `product_description` VARCHAR(64) COMMENT '商品描述',
  `product_icon` VARCHAR(512) COMMENT '商品图片',
  `product_status` TINYINT(3) DEFAULT '0' COMMENT '商品状态,0正常1下架',
  `category_type` INT NOT NULL COMMENT '商品类目编号',
  `create_time` TIMESTAMP NOT NULL DEFAULT current_timestamp COMMENT '创建时间',
  `update_time` TIMESTAMP NOT NULL DEFAULT current_timestamp ON UPDATE current_timestamp COMMENT '修改时间',
  PRIMARY KEY (`product_id`)

) COMMENT '商品表';

/*
商品类目表
*/

CREATE TABLE `product_category` (

  `category_id` INT NOT NULL AUTO_INCREMENT,
  `category_name` VARCHAR(64) NOT NULL COMMENT '类目名字',
  `category_type` INT NOT NULL COMMENT '类目编号',
  `create_time` TIMESTAMP NOT NULL DEFAULT current_timestamp COMMENT '创建时间',
  `update_time` TIMESTAMP NOT NULL DEFAULT current_timestamp ON UPDATE current_timestamp COMMENT '修改时间',
  PRIMARY KEY (`category_id`),
  UNIQUE KEY `uqe_category_type` (`category_type`)

) COMMENT '商品类目表';

/*
订单表
*/

CREATE TABLE `order_master` (

  `order_id` VARCHAR(32) NOT NULL,
  `buyer_name` VARCHAR(32) NOT NULL COMMENT '买家名字',
  `buyer_phone` VARCHAR(32) NOT NULL COMMENT '买家手机号',
  `buyer_address` VARCHAR(128) NOT NULL COMMENT '买家地址',
  `buyer_openid` VARCHAR(64) NOT NULL COMMENT '买家微信id',
  `buyer_amount` DECIMAL(8, 2) NOT NULL COMMENT '订单总额',
  `order_status` TINYINT(3) NOT NULL DEFAULT '0' COMMENT '订单状态, 默认0为新下单',
  `pay_status` TINYINT NOT NULL DEFAULT '0' COMMENT '支付状态，默认0为未支付',
  `create_time` TIMESTAMP NOT NULL DEFAULT current_timestamp COMMENT '创建时间',
  `update_time` TIMESTAMP NOT NULL DEFAULT current_timestamp ON UPDATE current_timestamp COMMENT '修改时间',
  PRIMARY KEY (`order_id`),
  KEY `idx_buyer_openid` (`buyer_openid`) /*添加索引*/

) COMMENT '订单主表';

/*
  订单详情表
*/
CREATE TABLE `order_detail` (

  `detail_id` VARCHAR(32) NOT NULL ,
  `order_id` VARCHAR(32) NOT NULL ,
  `product_id` VARCHAR(32) NOT NULL ,
  `product_name` VARCHAR(64) NOT NULL COMMENT '商品名称',
  `product_price` DECIMAL(8, 2) NOT NULL COMMENT '商品价格',
  `product_icon` VARCHAR(512) COMMENT '商品图片',
  `product_quantity`INT NOT NULL COMMENT '商品数量',
  `create_time` TIMESTAMP NOT NULL DEFAULT current_timestamp COMMENT '创建时间',
  `update_time` TIMESTAMP NOT NULL DEFAULT current_timestamp ON UPDATE current_timestamp COMMENT '修改时间',
  PRIMARY KEY (`detail_id`),
  KEY `idx_order_id` (`order_id`) /*订单id为索引*/

)COMMENT '订单详情表';

CREATE TABLE `seller_info`(
  `seller_id`  VARCHAR(32) NOT NULL ,
  `username` VARCHAR(32) NOT NULL ,
  `password` VARCHAR(32) NOT NULL ,
  `openid` VARCHAR(64) NOT NULL COMMENT '微信openid',
  `create_time` TIMESTAMP NOT NULL DEFAULT current_timestamp COMMENT '创建时间',
  `update_time` TIMESTAMP NOT NULL DEFAULT current_timestamp ON UPDATE current_timestamp COMMENT '修改时间',
  PRIMARY KEY (`seller_id`)
)COMMENT '卖家信息表'