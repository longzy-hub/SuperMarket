/*
 Navicat Premium Data Transfer

 Source Server         : sql
 Source Server Type    : MySQL
 Source Server Version : 50726
 Source Host           : localhost:3306
 Source Schema         : gues_supermarket

 Target Server Type    : MySQL
 Target Server Version : 50726
 File Encoding         : 65001

 Date: 06/04/2021 16:32:33
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for back
-- ----------------------------
DROP TABLE IF EXISTS `back`;
CREATE TABLE `back`  (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键id',
  `address` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '地址',
  `back_status` int(11) NOT NULL DEFAULT 1 COMMENT '状态',
  `creator` varchar(30) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '创建人',
  `create_time` datetime(0) NULL DEFAULT NULL COMMENT '创建时间',
  `update_time` datetime(0) NULL DEFAULT NULL COMMENT '更新时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 4 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of back
-- ----------------------------
INSERT INTO `back` VALUES (1, '公二', 1, 'admin', '2021-04-02 21:01:16', '2021-04-02 21:01:18');
INSERT INTO `back` VALUES (2, '绣一', 1, 'admin', '2021-04-02 22:44:22', '2021-04-02 22:44:22');
INSERT INTO `back` VALUES (3, '绣二', 1, 'admin', '2021-04-02 22:44:57', '2021-04-02 22:44:57');

-- ----------------------------
-- Table structure for category
-- ----------------------------
DROP TABLE IF EXISTS `category`;
CREATE TABLE `category`  (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '商品分类id',
  `name` varchar(30) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '分类名称',
  `describes` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '描述',
  `category_status` int(11) NOT NULL DEFAULT 1 COMMENT '状态： 1有效 0无效 其他删除',
  `creator` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '创建人',
  `create_time` datetime(0) NULL DEFAULT NULL COMMENT '创建时间',
  `update_time` datetime(0) NULL DEFAULT NULL COMMENT '更新时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 4 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of category
-- ----------------------------
INSERT INTO `category` VALUES (1, '休闲零食', '在闲暇、休息时吃的零食', 1, 'admin', '2021-03-25 14:00:46', '2021-03-29 17:42:58');
INSERT INTO `category` VALUES (2, '生活用品', '生活中常用的用品', 1, 'admin', '2021-03-26 21:26:03', '2021-03-29 17:43:31');
INSERT INTO `category` VALUES (3, '文具用品', '用于学习之类的用品', 1, 'admin', '2021-03-30 23:40:38', '2021-03-30 23:40:38');

-- ----------------------------
-- Table structure for orderitem
-- ----------------------------
DROP TABLE IF EXISTS `orderitem`;
CREATE TABLE `orderitem`  (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '订单项id',
  `order_id` int(11) NOT NULL COMMENT '订单id',
  `product_id` int(11) NOT NULL COMMENT '商品id',
  `number` int(11) NOT NULL COMMENT '数量',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 11 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of orderitem
-- ----------------------------
INSERT INTO `orderitem` VALUES (1, 1, 8, 2);
INSERT INTO `orderitem` VALUES (2, 1, 9, 5);

-- ----------------------------
-- Table structure for orders
-- ----------------------------
DROP TABLE IF EXISTS `orders`;
CREATE TABLE `orders`  (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '订单id',
  `user_id` int(11) NOT NULL COMMENT '用户id',
  `address` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '地址',
  `price` double(10, 2) NULL DEFAULT NULL COMMENT '付款价格',
  `pay_way` varchar(30) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '付款方式',
  `pay_time` datetime(0) NULL DEFAULT NULL COMMENT '付款时间',
  `create_time` datetime(0) NULL DEFAULT NULL COMMENT '创建时间',
  `send_time` datetime(0) NULL DEFAULT NULL COMMENT '发货时间',
  `take_time` datetime(0) NULL DEFAULT NULL COMMENT '收货时间',
  `order_status` int(11) NOT NULL DEFAULT 1 COMMENT '状态：1待付款 2待发货 3待收货 4已完成',
  `remark` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '备注',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 11 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of orders
-- ----------------------------
INSERT INTO `orders` VALUES (1, 1, '公二', 96.50, '微信', '2021-04-06 15:28:25', '2021-04-06 15:24:35', '2021-04-06 15:28:39', '2021-04-06 15:29:04', 4, '4层451室');

-- ----------------------------
-- Table structure for personal
-- ----------------------------
DROP TABLE IF EXISTS `personal`;
CREATE TABLE `personal`  (
  `id` int(30) NOT NULL AUTO_INCREMENT COMMENT '主键id',
  `user_id` int(11) NOT NULL COMMENT '用户id',
  `status` int(11) NOT NULL DEFAULT 1 COMMENT '状态： 1登录成功 0登录失败',
  `time` datetime(0) NULL DEFAULT NULL COMMENT '登录时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 126 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of personal
-- ----------------------------
INSERT INTO `personal` VALUES (1, 1, 1, '2021-04-06 10:35:56');
INSERT INTO `personal` VALUES (2, 1, 1, '2021-04-06 10:35:56');
INSERT INTO `personal` VALUES (3, 1, 1, '2021-04-06 10:43:29');
INSERT INTO `personal` VALUES (4, 1, 1, '2021-04-06 11:00:42');
INSERT INTO `personal` VALUES (5, 1, 1, '2021-04-06 11:03:49');
INSERT INTO `personal` VALUES (6, 1, 1, '2021-04-06 11:05:42');
INSERT INTO `personal` VALUES (7, 1, 1, '2021-04-06 11:06:26');
INSERT INTO `personal` VALUES (8, 1, 1, '2021-04-06 11:08:35');
INSERT INTO `personal` VALUES (9, 1, 1, '2021-04-06 11:12:25');
INSERT INTO `personal` VALUES (10, 1, 1, '2021-04-06 11:16:38');
INSERT INTO `personal` VALUES (11, 1, 1, '2021-04-06 11:16:52');
INSERT INTO `personal` VALUES (12, 1, 1, '2021-04-06 11:17:08');
INSERT INTO `personal` VALUES (13, 1, 1, '2021-04-06 11:17:21');
INSERT INTO `personal` VALUES (14, 1, 1, '2021-04-06 11:19:46');
INSERT INTO `personal` VALUES (15, 1, 1, '2021-04-06 11:20:00');
INSERT INTO `personal` VALUES (16, 1, 1, '2021-04-06 11:20:59');
INSERT INTO `personal` VALUES (17, 1, 1, '2021-04-06 11:21:37');
INSERT INTO `personal` VALUES (18, 1, 1, '2021-04-06 11:22:01');
INSERT INTO `personal` VALUES (19, 1, 1, '2021-04-06 11:22:16');
INSERT INTO `personal` VALUES (20, 1, 1, '2021-04-06 11:23:10');
INSERT INTO `personal` VALUES (21, 1, 1, '2021-04-06 11:23:59');
INSERT INTO `personal` VALUES (22, 1, 1, '2021-04-06 11:24:35');
INSERT INTO `personal` VALUES (23, 1, 1, '2021-04-06 11:25:16');
INSERT INTO `personal` VALUES (24, 1, 1, '2021-04-06 11:28:42');
INSERT INTO `personal` VALUES (25, 1, 1, '2021-04-06 11:31:05');
INSERT INTO `personal` VALUES (26, 1, 1, '2021-04-06 11:32:17');
INSERT INTO `personal` VALUES (27, 1, 1, '2021-04-06 11:32:30');
INSERT INTO `personal` VALUES (28, 1, 1, '2021-04-06 11:46:40');
INSERT INTO `personal` VALUES (29, 1, 1, '2021-04-06 11:49:48');
INSERT INTO `personal` VALUES (30, 1, 1, '2021-04-06 11:51:09');
INSERT INTO `personal` VALUES (31, 1, 1, '2021-04-06 11:51:51');
INSERT INTO `personal` VALUES (32, 1, 1, '2021-04-06 11:57:54');
INSERT INTO `personal` VALUES (33, 1, 1, '2021-04-06 11:58:47');
INSERT INTO `personal` VALUES (34, 1, 1, '2021-04-06 12:00:31');
INSERT INTO `personal` VALUES (35, 1, 1, '2021-04-06 12:55:09');
INSERT INTO `personal` VALUES (36, 1, 1, '2021-04-06 13:05:22');
INSERT INTO `personal` VALUES (37, 1, 1, '2021-04-06 13:15:26');
INSERT INTO `personal` VALUES (38, 1, 1, '2021-04-06 13:15:50');
INSERT INTO `personal` VALUES (39, 1, 1, '2021-04-06 13:24:17');
INSERT INTO `personal` VALUES (40, 1, 1, '2021-04-06 13:24:31');
INSERT INTO `personal` VALUES (41, 1, 1, '2021-04-06 13:26:35');
INSERT INTO `personal` VALUES (42, 1, 1, '2021-04-06 13:32:22');
INSERT INTO `personal` VALUES (43, 1, 1, '2021-04-06 13:36:37');
INSERT INTO `personal` VALUES (44, 1, 1, '2021-04-06 13:51:23');
INSERT INTO `personal` VALUES (45, 1, 1, '2021-04-06 13:52:21');
INSERT INTO `personal` VALUES (46, 1, 1, '2021-04-06 13:56:48');
INSERT INTO `personal` VALUES (47, 1, 1, '2021-04-06 13:57:21');
INSERT INTO `personal` VALUES (48, 1, 1, '2021-04-06 13:59:33');
INSERT INTO `personal` VALUES (49, 1, 1, '2021-04-06 14:04:50');
INSERT INTO `personal` VALUES (50, 1, 1, '2021-04-06 14:15:44');
INSERT INTO `personal` VALUES (51, 1, 1, '2021-04-06 14:20:06');
INSERT INTO `personal` VALUES (52, 1, 1, '2021-04-06 14:20:22');
INSERT INTO `personal` VALUES (53, 1, 1, '2021-04-06 14:21:04');
INSERT INTO `personal` VALUES (54, 1, 1, '2021-04-06 14:24:00');
INSERT INTO `personal` VALUES (55, 1, 1, '2021-04-06 14:25:31');
INSERT INTO `personal` VALUES (56, 1, 1, '2021-04-06 14:25:43');
INSERT INTO `personal` VALUES (57, 1, 1, '2021-04-06 14:26:49');
INSERT INTO `personal` VALUES (58, 1, 1, '2021-04-06 14:26:58');
INSERT INTO `personal` VALUES (59, 1, 1, '2021-04-06 14:27:45');
INSERT INTO `personal` VALUES (60, 1, 1, '2021-04-06 14:31:34');
INSERT INTO `personal` VALUES (61, 1, 1, '2021-04-06 14:33:33');
INSERT INTO `personal` VALUES (62, 1, 1, '2021-04-06 14:34:06');
INSERT INTO `personal` VALUES (63, 1, 1, '2021-04-06 14:36:29');
INSERT INTO `personal` VALUES (64, 1, 1, '2021-04-06 14:37:39');
INSERT INTO `personal` VALUES (65, 1, 1, '2021-04-06 14:38:47');
INSERT INTO `personal` VALUES (66, 1, 1, '2021-04-06 14:47:42');
INSERT INTO `personal` VALUES (67, 1, 1, '2021-04-06 14:51:57');
INSERT INTO `personal` VALUES (68, 1, 1, '2021-04-06 14:54:16');
INSERT INTO `personal` VALUES (69, 1, 1, '2021-04-06 15:05:34');
INSERT INTO `personal` VALUES (70, 1, 1, '2021-04-06 15:08:13');
INSERT INTO `personal` VALUES (71, 1, 1, '2021-04-06 15:16:41');
INSERT INTO `personal` VALUES (72, 1, 1, '2021-04-06 15:17:17');
INSERT INTO `personal` VALUES (73, 1, 1, '2021-04-06 15:22:23');
INSERT INTO `personal` VALUES (74, 1, 1, '2021-04-06 15:24:24');
INSERT INTO `personal` VALUES (75, 1, 1, '2021-04-06 15:28:20');
INSERT INTO `personal` VALUES (76, 1, 1, '2021-04-06 15:42:57');
INSERT INTO `personal` VALUES (77, 1, 1, '2021-04-06 15:43:06');
INSERT INTO `personal` VALUES (78, 1, 1, '2021-04-06 15:44:15');
INSERT INTO `personal` VALUES (79, 1, 1, '2021-04-06 15:45:13');
INSERT INTO `personal` VALUES (80, 1, 1, '2021-04-06 15:46:51');
INSERT INTO `personal` VALUES (81, 1, 1, '2021-04-06 15:47:26');
INSERT INTO `personal` VALUES (82, 1, 1, '2021-04-06 15:49:56');
INSERT INTO `personal` VALUES (83, 1, 1, '2021-04-06 15:50:18');
INSERT INTO `personal` VALUES (84, 1, 1, '2021-04-06 15:50:41');
INSERT INTO `personal` VALUES (85, 1, 1, '2021-04-06 15:50:58');
INSERT INTO `personal` VALUES (86, 1, 1, '2021-04-06 15:51:18');
INSERT INTO `personal` VALUES (87, 1, 1, '2021-04-06 15:51:28');
INSERT INTO `personal` VALUES (88, 1, 1, '2021-04-06 15:52:17');
INSERT INTO `personal` VALUES (89, 1, 1, '2021-04-06 15:52:25');
INSERT INTO `personal` VALUES (90, 1, 1, '2021-04-06 15:52:33');
INSERT INTO `personal` VALUES (91, 1, 1, '2021-04-06 15:52:47');
INSERT INTO `personal` VALUES (92, 1, 1, '2021-04-06 15:53:03');
INSERT INTO `personal` VALUES (93, 1, 1, '2021-04-06 15:53:48');
INSERT INTO `personal` VALUES (94, 1, 1, '2021-04-06 15:54:06');
INSERT INTO `personal` VALUES (95, 1, 1, '2021-04-06 15:54:41');
INSERT INTO `personal` VALUES (96, 1, 1, '2021-04-06 15:55:11');
INSERT INTO `personal` VALUES (97, 1, 1, '2021-04-06 15:55:45');
INSERT INTO `personal` VALUES (98, 1, 1, '2021-04-06 15:56:06');
INSERT INTO `personal` VALUES (99, 1, 1, '2021-04-06 15:56:23');
INSERT INTO `personal` VALUES (100, 1, 1, '2021-04-06 15:57:03');
INSERT INTO `personal` VALUES (101, 1, 1, '2021-04-06 15:57:24');
INSERT INTO `personal` VALUES (102, 1, 1, '2021-04-06 15:57:44');
INSERT INTO `personal` VALUES (103, 1, 1, '2021-04-06 15:58:16');
INSERT INTO `personal` VALUES (104, 1, 1, '2021-04-06 15:59:02');
INSERT INTO `personal` VALUES (105, 1, 1, '2021-04-06 15:59:19');
INSERT INTO `personal` VALUES (106, 1, 1, '2021-04-06 15:59:34');
INSERT INTO `personal` VALUES (107, 1, 1, '2021-04-06 15:59:44');
INSERT INTO `personal` VALUES (108, 1, 1, '2021-04-06 15:59:53');
INSERT INTO `personal` VALUES (109, 1, 1, '2021-04-06 16:00:08');
INSERT INTO `personal` VALUES (110, 1, 1, '2021-04-06 16:00:32');
INSERT INTO `personal` VALUES (111, 1, 1, '2021-04-06 16:00:57');
INSERT INTO `personal` VALUES (112, 1, 1, '2021-04-06 16:02:10');
INSERT INTO `personal` VALUES (113, 1, 1, '2021-04-06 16:02:22');
INSERT INTO `personal` VALUES (114, 1, 1, '2021-04-06 16:02:47');
INSERT INTO `personal` VALUES (115, 1, 1, '2021-04-06 16:02:57');
INSERT INTO `personal` VALUES (116, 1, 1, '2021-04-06 16:03:18');
INSERT INTO `personal` VALUES (117, 1, 1, '2021-04-06 16:03:36');
INSERT INTO `personal` VALUES (118, 1, 1, '2021-04-06 16:04:02');
INSERT INTO `personal` VALUES (119, 1, 1, '2021-04-06 16:05:22');
INSERT INTO `personal` VALUES (120, 1, 1, '2021-04-06 16:21:49');
INSERT INTO `personal` VALUES (121, 1, 1, '2021-04-06 16:25:41');
INSERT INTO `personal` VALUES (122, 1, 1, '2021-04-06 16:27:15');
INSERT INTO `personal` VALUES (123, 1, 1, '2021-04-06 16:29:55');
INSERT INTO `personal` VALUES (124, 1, 1, '2021-04-06 16:30:26');
INSERT INTO `personal` VALUES (125, 1, 1, '2021-04-06 16:31:41');

-- ----------------------------
-- Table structure for product
-- ----------------------------
DROP TABLE IF EXISTS `product`;
CREATE TABLE `product`  (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '商品id',
  `product_name` varchar(30) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '商品名称',
  `describes` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '描述',
  `price` double(10, 2) NOT NULL COMMENT '价格',
  `category_id` int(11) NOT NULL COMMENT '分类id',
  `img_id` int(11) NOT NULL COMMENT '图片id',
  `product_status` int(11) NOT NULL DEFAULT 1 COMMENT '状态 ： 1上架 0下架 其他删除',
  `creator` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '创建人',
  `create_time` datetime(0) NULL DEFAULT NULL COMMENT '创建时间',
  `update_time` datetime(0) NULL DEFAULT NULL COMMENT '更新时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 14 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of product
-- ----------------------------
INSERT INTO `product` VALUES (1, '喜之郎', '果冻', 3.00, 1, 1, 1, 'admin', '2021-03-29 18:00:08', '2021-03-29 18:00:08');
INSERT INTO `product` VALUES (2, 'Lay\'s乐事', '薯片', 3.00, 1, 2, 1, 'admin', '2021-03-29 18:01:51', '2021-03-29 18:01:51');
INSERT INTO `product` VALUES (3, '手撕素肉', '', 1.00, 1, 3, 1, 'admin', '2021-03-29 18:05:59', '2021-03-29 18:05:59');
INSERT INTO `product` VALUES (4, '海鲜锅巴', '', 2.50, 1, 4, 1, 'admin', '2021-03-29 18:07:28', '2021-03-29 18:07:28');
INSERT INTO `product` VALUES (5, '蔓越莓曲奇', '', 5.00, 1, 5, 1, 'admin', '2021-03-29 18:08:20', '2021-03-29 18:08:20');
INSERT INTO `product` VALUES (6, '可比克', '薯片', 4.00, 1, 6, 1, 'admin', '2021-03-29 18:08:58', '2021-03-29 18:08:58');
INSERT INTO `product` VALUES (7, '碧银果', '三只松鼠', 14.50, 1, 7, 1, 'admin', '2021-03-29 18:11:12', '2021-03-29 18:11:12');
INSERT INTO `product` VALUES (8, '蔓越莓曲奇（百草味）', '', 12.00, 1, 8, 1, 'admin', '2021-03-29 18:12:02', '2021-03-29 18:12:02');
INSERT INTO `product` VALUES (9, '夏威夷果', '三只松鼠', 14.50, 1, 9, 1, 'admin', '2021-03-29 18:12:31', '2021-03-29 18:12:31');
INSERT INTO `product` VALUES (10, '小面筋', '卫龙', 1.00, 1, 10, 1, 'admin', '2021-03-29 18:13:53', '2021-03-29 18:13:53');
INSERT INTO `product` VALUES (11, '鲜在香辣肠', '', 1.00, 1, 11, 1, 'admin', '2021-03-29 18:14:58', '2021-03-29 18:14:58');
INSERT INTO `product` VALUES (12, 'Q豆腐', '豆腐类', 0.50, 1, 12, 1, 'admin', '2021-03-29 18:15:28', '2021-03-29 18:33:34');
INSERT INTO `product` VALUES (13, '掌心脆', '干脆面\r\n', 1.00, 1, 13, 1, 'admin', '2021-03-29 18:16:00', '2021-04-02 20:46:45');

-- ----------------------------
-- Table structure for productimg
-- ----------------------------
DROP TABLE IF EXISTS `productimg`;
CREATE TABLE `productimg`  (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '图片id',
  `img_name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '图片名称',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 14 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of productimg
-- ----------------------------
INSERT INTO `productimg` VALUES (1, '1617012007559.jpg');
INSERT INTO `productimg` VALUES (2, '1617012110810.jpg');
INSERT INTO `productimg` VALUES (3, '1617012358620.jpg');
INSERT INTO `productimg` VALUES (4, '1617012447695.jpg');
INSERT INTO `productimg` VALUES (5, '1617012500136.jpg');
INSERT INTO `productimg` VALUES (6, '1617012537280.jpg');
INSERT INTO `productimg` VALUES (7, '1617012671834.jpg');
INSERT INTO `productimg` VALUES (8, '1617012721502.jpg');
INSERT INTO `productimg` VALUES (9, '1617012750393.jpg');
INSERT INTO `productimg` VALUES (10, '1617012833083.jpg');
INSERT INTO `productimg` VALUES (11, '1617012897692.jpg');
INSERT INTO `productimg` VALUES (12, '1617012927411.jpg');
INSERT INTO `productimg` VALUES (13, '1617012959991.jpg');

-- ----------------------------
-- Table structure for shoppingcart
-- ----------------------------
DROP TABLE IF EXISTS `shoppingcart`;
CREATE TABLE `shoppingcart`  (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT 'id',
  `user_id` int(11) NOT NULL COMMENT '用户id',
  `product_id` int(11) NOT NULL COMMENT '商品id',
  `number` int(11) NOT NULL COMMENT '数量',
  `cart_status` int(11) NOT NULL DEFAULT 0 COMMENT '状态：1生成订单 0没有生成订单',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 7 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of shoppingcart
-- ----------------------------
INSERT INTO `shoppingcart` VALUES (1, 1, 9, 5, 1);
INSERT INTO `shoppingcart` VALUES (2, 1, 8, 2, 1);

-- ----------------------------
-- Table structure for sys_perm
-- ----------------------------
DROP TABLE IF EXISTS `sys_perm`;
CREATE TABLE `sys_perm`  (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '权限id',
  `name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '权限名称',
  `describes` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '描述',
  `role_id` int(11) NULL DEFAULT NULL COMMENT '角色id',
  `perm_status` int(11) NOT NULL DEFAULT 1 COMMENT '状态',
  `creator` varchar(30) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '创建人',
  `create_time` datetime(0) NULL DEFAULT NULL COMMENT '创建时间',
  `update_time` datetime(0) NULL DEFAULT NULL COMMENT '更新时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 11 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sys_perm
-- ----------------------------
INSERT INTO `sys_perm` VALUES (1, 'admin:*:*', '操作所有权限', 1, 1, 'admin', '2021-03-30 20:03:23', '2021-03-30 20:03:26');
INSERT INTO `sys_perm` VALUES (2, 'product:*:*', '操作商品权限', 2, 1, 'admin', '2021-03-31 11:11:37', '2021-03-31 11:11:39');
INSERT INTO `sys_perm` VALUES (3, 'order:*:*', '处理订单权限', 3, 1, 'admin', '2021-03-31 11:12:49', '2021-03-31 11:12:52');
INSERT INTO `sys_perm` VALUES (4, 'back:*:*', '配送商品', 4, 1, 'admin', '2021-03-31 12:57:19', '2021-03-31 12:57:19');
INSERT INTO `sys_perm` VALUES (6, 'perm:*:*', '分配权限', 5, 1, 'admin', '2021-03-31 13:03:12', '2021-03-31 13:03:12');
INSERT INTO `sys_perm` VALUES (7, 'product:*:*', '处理商品权限', 1, 1, '张三', '2021-03-31 13:29:53', '2021-03-31 13:29:53');
INSERT INTO `sys_perm` VALUES (8, 'order:*:*', '处理订单权限', 1, 1, '张三', '2021-03-31 13:30:31', '2021-03-31 13:30:31');
INSERT INTO `sys_perm` VALUES (9, 'back:*:*', '处理配送权限', 1, 1, '张三', '2021-03-31 13:30:58', '2021-03-31 13:30:58');
INSERT INTO `sys_perm` VALUES (10, 'perm:*:*', '处理分配权限', 1, 1, '张三', '2021-03-31 13:31:22', '2021-03-31 13:31:22');

-- ----------------------------
-- Table structure for sys_role
-- ----------------------------
DROP TABLE IF EXISTS `sys_role`;
CREATE TABLE `sys_role`  (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '角色编号',
  `name` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '角色名称',
  `describes` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '描述',
  `status` int(11) NOT NULL DEFAULT 1 COMMENT '状态：1有效 0无效 其他删除',
  `creator` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '创建人',
  `create_time` datetime(0) NULL DEFAULT NULL COMMENT '创建时间',
  `update_time` datetime(0) NULL DEFAULT NULL COMMENT '更新时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 7 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sys_role
-- ----------------------------
INSERT INTO `sys_role` VALUES (1, '超级管理员', '允许操作后台所有功能', 1, 'admin', '2021-03-21 18:04:20', '2021-03-21 20:54:53');
INSERT INTO `sys_role` VALUES (2, '商品管理员', '操作商品的分类与上下架', 1, 'admin', '2021-03-31 11:00:24', '2021-03-31 11:00:24');
INSERT INTO `sys_role` VALUES (3, '订单管理员', '处理订单', 1, 'admin', '2021-03-31 11:00:49', '2021-03-31 11:00:49');
INSERT INTO `sys_role` VALUES (4, '配送管理员', '配送商品', 1, 'admin', '2021-03-31 11:01:21', '2021-03-31 11:01:21');
INSERT INTO `sys_role` VALUES (5, '权限管理员', '分配权限', 1, 'admin', '2021-03-31 13:02:32', '2021-03-31 13:02:32');
INSERT INTO `sys_role` VALUES (6, '普通用户', '允许操作用户功能', 1, 'admin', '2021-03-18 16:22:37', '2021-03-21 20:47:57');

-- ----------------------------
-- Table structure for sys_user
-- ----------------------------
DROP TABLE IF EXISTS `sys_user`;
CREATE TABLE `sys_user`  (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '用户id',
  `username` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '用户名',
  `password` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '用户密码',
  `salt` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '加密盐值',
  `telephone` varchar(13) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '手机号',
  `mail` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '邮箱',
  `role_id` int(11) NOT NULL COMMENT '所属角色id',
  `user_status` int(11) NOT NULL DEFAULT 1 COMMENT '状态： 1有效  0无效  其他删除',
  `create_time` datetime(0) NULL DEFAULT NULL COMMENT '创建时间',
  `update_time` datetime(0) NULL DEFAULT NULL COMMENT '更新时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 5 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sys_user
-- ----------------------------
INSERT INTO `sys_user` VALUES (1, 'admin', 'a6cc61be98048354ea98d9dd76646d8f', 'd$5wo8lb', '18311829257', 'longzy1@qq.com', 1, 1, '2021-04-02 20:07:44', '2021-04-02 20:27:22');
INSERT INTO `sys_user` VALUES (2, '小米', 'c109b13f0ec4e37de29b73274f4792c6', 'AEMMYp51', '18311829250', '25872340@qq.com', 2, 1, '2021-04-02 20:12:09', '2021-04-02 20:12:09');
INSERT INTO `sys_user` VALUES (3, '小红', 'f440fb52326e34745035355313e50f33', 'JGLHi)Gi', '18612344321', '34011@qq.com', 3, 1, '2021-04-02 20:13:34', '2021-04-02 20:13:34');
INSERT INTO `sys_user` VALUES (4, '小龙', '26a38dda3e9e8a2df8fbd93d5744d6a8', '%w*h5SH5', '19110730260', '2587234011@qq.com', 6, 1, '2021-04-02 20:15:04', '2021-04-02 20:15:04');

SET FOREIGN_KEY_CHECKS = 1;
