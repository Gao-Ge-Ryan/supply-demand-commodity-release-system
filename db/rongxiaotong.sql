/*
 Navicat Premium Data Transfer

 Source Server         : mysql-8.0.20-本地docker
 Source Server Type    : MySQL
 Source Server Version : 80021 (8.0.21)
 Source Host           : localhost:3306
 Source Schema         : rongxiaotong

 Target Server Type    : MySQL
 Target Server Version : 80021 (8.0.21)
 File Encoding         : 65001

 Date: 17/05/2024 12:34:49
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for tb_address
-- ----------------------------
DROP TABLE IF EXISTS `tb_address`;
CREATE TABLE `tb_address`  (
  `id` int NOT NULL AUTO_INCREMENT,
  `own_name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '属于哪个用户的地址',
  `consignee` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '收货人',
  `phone` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `address_detail` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '收货地址',
  `is_default` tinyint NULL DEFAULT 0 COMMENT '是否默认，0，不是，默认是1',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 185 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = COMPACT;

-- ----------------------------
-- Records of tb_address
-- ----------------------------
INSERT INTO `tb_address` VALUES (121, 'zwr', '张文瑞', '15030589961', '青岛市即墨区青岛湾琴湾C区', 1);
INSERT INTO `tb_address` VALUES (129, 'zhangxukun', '张绪昆', '18354648787', '山东省青岛市', 0);
INSERT INTO `tb_address` VALUES (146, 'zhangxu', '张绪昆', '18396833008', '山东省青岛市', 0);
INSERT INTO `tb_address` VALUES (147, 'zhangxukun', '张绪昆', '18354648787', '山东省济南市', 1);
INSERT INTO `tb_address` VALUES (154, 'gaoge', '高开心', '18977771439', '山东省德州市夏津县', 1);
INSERT INTO `tb_address` VALUES (157, 'zzz123', 'zzz', '13546464646', '山东省', 1);
INSERT INTO `tb_address` VALUES (170, 'gaogege', '请输入收货人姓名...', '请输入收货人手机号...', '请输入收货人详细地址信息...', 1);
INSERT INTO `tb_address` VALUES (172, 'admin', '成吉思汗', '18766661438', '山东青岛市崂山区', 1);
INSERT INTO `tb_address` VALUES (173, 'admin', '不朽大帝', '18766661438', '山东青岛市崂山区', 0);
INSERT INTO `tb_address` VALUES (174, 'gaogle', '刘烨', '17663218818', '很多时候范德萨回复', 1);
INSERT INTO `tb_address` VALUES (176, 'angeluser', '天使', '17663218898', '山东青岛市崂山区108号', 1);
INSERT INTO `tb_address` VALUES (177, 'angeluser', '真美丽我', '17663218898', '山东青岛市崂山区108号', 0);
INSERT INTO `tb_address` VALUES (179, 'testuser', '天使', '17668888888', '山东省崂山市108号', 1);
INSERT INTO `tb_address` VALUES (181, 'testuser', 'gaogle', '17668889999', '山东省崂山市10号8单元', 0);
INSERT INTO `tb_address` VALUES (182, 'consumer', '消费者gaogle', '17663228878', '山东省济南市历下区10089胡同', 1);
INSERT INTO `tb_address` VALUES (183, 'gaoge', '高裘', '18977771439', '山东省枣庄市', 0);
INSERT INTO `tb_address` VALUES (184, 'gaoge', 'happy', '18977771439', '山东省德州市夏津县', 0);

-- ----------------------------
-- Table structure for tb_comment
-- ----------------------------
DROP TABLE IF EXISTS `tb_comment`;
CREATE TABLE `tb_comment`  (
  `id` int NOT NULL,
  `orderId` int NOT NULL,
  `targetName` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `commentorName` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `quality` int NOT NULL COMMENT '质量1,2,3,4,5',
  `attitude` int NOT NULL COMMENT '合作态度1，2，3，4，5',
  `speed` int NOT NULL COMMENT '发货速度',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = COMPACT;

-- ----------------------------
-- Records of tb_comment
-- ----------------------------

-- ----------------------------
-- Table structure for tb_knowledge
-- ----------------------------
DROP TABLE IF EXISTS `tb_knowledge`;
CREATE TABLE `tb_knowledge`  (
  `knowledge_id` int NOT NULL AUTO_INCREMENT,
  `title` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `content` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `pic_path` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `own_name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '发布者名字',
  `create_time` time NOT NULL,
  `update_time` datetime NOT NULL,
  PRIMARY KEY (`knowledge_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 38 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = COMPACT;

-- ----------------------------
-- Records of tb_knowledge
-- ----------------------------
INSERT INTO `tb_knowledge` VALUES (22, '水稻种植全过程', '水稻种植第一步：晒种\n水稻种植第二步：选种\n水稻种植第三步：整秧版\n水稻种植第四步：播种\n水稻种植第五步：插秧\n水稻种植第六步：缓苗\n水稻种植第七步：田间管理（关键）', 'http://82.157.42.25/7f2641a5dd0545b3b33aa8c5508e216c.webp', 'gaoge', '16:33:59', '2021-08-27 16:33:59');
INSERT INTO `tb_knowledge` VALUES (23, '玉米种植过程详解 ', '玉米一直都被誉为长寿食品，含有丰富的蛋白质、脂肪、维生素、微量元素、纤维素等，具有开发高营养、高生物学功能食品的巨大潜力。但由于其遗传性较为复杂，变异种类丰富，在常规的育种过程中存在着周期过长、变异系数过大、影响子代生长发育的缺点，而现代生物育种技术不但克服了上述缺点和不足，同时也提高了育种速度和质量。玉米出苗后，要及时检查出苗情况，发现缺苗断垄要及时补种、补栽。3叶期前缺苗，用饱满种子浸种催芽后浇水补种。3叶期后缺苗用带土移栽法补苗(播种时可在行间播预备苗)，另外，缺苗处也可在附近留双株补救。', 'http://82.157.42.25/e7bd0ccdb03c416799e2e8f7b1860092.jpeg', 'gaoge', '09:31:37', '2021-08-30 09:31:37');
INSERT INTO `tb_knowledge` VALUES (24, '大豆种植', '大豆可分为黄豆、青豆和黑豆。可大家都认为大豆只是黄豆。富含蛋白质,大豆磷脂由大豆提取出来的精华,大豆中提取的纯磷脂精华物质,对人体健康有着极大的帮助，并无副作用。对于黄大豆，它需要较长的生产时间，也非常得能耐寒冷，北方地区的气候条件适合种植;然而青豆的生长时间较短，适宜把', 'http://82.157.42.25/59af3144214b4dce9c2fd13d3fdab8ef.webp', 'gaoge', '09:37:43', '2021-08-30 09:37:43');
INSERT INTO `tb_knowledge` VALUES (25, '永泰李干的制作过程', '福州特色农产品，永泰李干的制作过程，100％还原！暑期在家帮父母晒李干，永泰李干虽是福建名产，但是市场占有率很低，“养在深山人未识”。我家李干的口感、品质都是不错的，欢迎大家购买品尝！', 'http://82.157.42.25/7f2641a5dd0545b3b33aa8c5508e216c.webp', 'zhangxukun', '09:39:56', '2021-08-30 09:39:56');
INSERT INTO `tb_knowledge` VALUES (26, '葡萄种植', '葡萄是我们生活中最常见的水果之一。如今，市场上出现了许多葡萄品种，一些葡萄正处于管理的关键阶段。这种葡萄管理说简单其实也简单，说复杂也复杂，会者不难，难者不会。掌握基本要点，就能实现“一年树，两年果，三年高产”的愿望。', 'http://82.157.42.25/e7bd0ccdb03c416799e2e8f7b1860092.jpeg', 'gaoge', '09:44:24', '2021-08-30 09:44:24');
INSERT INTO `tb_knowledge` VALUES (27, '【农业种植 • 园艺】《天天学农（农技知识）》', '天天学农创始团队在过去的数年中走遍中国广大农村，与农民深入交谈，了解越多就越觉得必须要去为农民做点实事。我们中很多人是农民的孩子，知道中国农民真是一群非常勤劳的人，但缺乏生产技术，往往事倍功半，他们也渴望学习。农民是朴实的，像庄稼地一样，种下什么就收获什么。视频涵盖了大棚草莓、猕猴桃、苹果树等方方面面，是农民朋友切实需要的教学视频，我们将提供大量的农业技术教学视频，方便大家学习，不断提高农业技术，创造美好生活。', 'http://82.157.42.25/1314259305d34bedb95f7800d9edaa8a.webp', 'zhangxukun', '09:46:37', '2021-08-30 09:46:37');
INSERT INTO `tb_knowledge` VALUES (28, '西瓜种植', '西瓜露地早春栽培，一般以地温稳定在15℃左右时为露地播种的适宜时间。播种的最佳时间，还应根据品种、栽培季节、栽培方式以及消费季节等条件来确定。一般月中下旬播种育苗，4月中下旬定植，6月下旬开始收获上市；秋西瓜7月上中旬播种，9月下旬开始采收上市。', 'http://82.157.42.25/e7bd0ccdb03c416799e2e8f7b1860092.jpeg', 'gaoge', '09:54:19', '2021-08-30 09:54:19');
INSERT INTO `tb_knowledge` VALUES (29, '生姜的一生｜现代农业种植和收获生姜', '两千多年来生姜一直活跃在餐桌上。一般做酱菜和小吃用嫩姜，做调料和药用以老姜为佳。传说，神农氏四处尝百草。有一次，误食毒蘑菇，吃了一株长着尖细叶子的青草，神农氏一阵腹泻，感觉死而复生。神农氏姓姜，他将这株救命的植物，叫做生姜。', 'http://82.157.42.25/59af3144214b4dce9c2fd13d3fdab8ef.webp', 'zhangxukun', '09:55:54', '2021-08-30 09:55:54');
INSERT INTO `tb_knowledge` VALUES (30, '人工种植蘑菇', '黄伞伞，白杆杆，吃完一起开厂厂！一天卖2吨蘑菇的奥地利现代种植工厂赚钱全过程', 'http://82.157.42.25/59af3144214b4dce9c2fd13d3fdab8ef.webp', 'zhangxukun', '10:00:02', '2021-08-30 10:00:02');
INSERT INTO `tb_knowledge` VALUES (36, '玉米种植知识', '玉米种植知识，玉米种植知识，玉米种植知识，玉米种植知识', 'http://82.157.42.25/5e20afc93ce243e78d573517ac7f4863.webp', 'gaoge', '22:02:40', '2024-05-12 22:02:40');
INSERT INTO `tb_knowledge` VALUES (37, '土豆种植', '土豆种植，土豆种植，土豆种植，土豆种植', 'http://82.157.42.25/c8e34b77e46b4e3b927754dcd3e4602e.jpeg', 'angeluser', '22:05:10', '2024-05-12 22:05:10');

-- ----------------------------
-- Table structure for tb_order
-- ----------------------------
DROP TABLE IF EXISTS `tb_order`;
CREATE TABLE `tb_order`  (
  `order_id` int NOT NULL AUTO_INCREMENT COMMENT '订单id',
  `title` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '需求标题',
  `price` decimal(65, 2) NOT NULL COMMENT '期望价格',
  `content` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '内容',
  `order_statu` int NOT NULL DEFAULT 0 COMMENT '订单状态，0表示待合作，1表示待发货，2表示完成，3表示完成但未评价',
  `type` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '1销售订单，2需求订单',
  `picture` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `own_name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '发起订单人',
  `cooperation_name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '合作人名字',
  `create_time` datetime NOT NULL,
  `update_time` datetime NOT NULL,
  `address` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '订单收货地址',
  PRIMARY KEY (`order_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 124 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = COMPACT;

-- ----------------------------
-- Records of tb_order
-- ----------------------------
INSERT INTO `tb_order` VALUES (66, '地瓜粉红薯淀粉', 123.00, '福建闽南泉州官桥正宗农家手工自制地瓜粉红薯淀粉番薯粉无添加剂', 1, 'goods', 'http://82.157.42.25/7f2641a5dd0545b3b33aa8c5508e216c.webp', 'gaoge', 'gaoge', '2021-08-27 16:15:00', '2024-05-12 21:18:37', '高歌 18977771439 山东省德州市夏津县');
INSERT INTO `tb_order` VALUES (67, '新疆小红杏吊干杏', 45.00, '新疆小红杏吊干杏新鲜杏子农产品应季1斤装水果特甜小白杏净重4斤', 1, 'goods', 'http://82.157.42.25/e7bd0ccdb03c416799e2e8f7b1860092.jpeg', 'gaoge', 'gaoge', '2021-08-27 16:17:10', '2024-05-12 21:18:51', '高歌 18977771439 山东省德州市夏津县');
INSERT INTO `tb_order` VALUES (68, '云南特产大香蕉', 23.00, '云南特产冬季水果大香蕉新鲜当季10斤农产品直销土特产农家孕妇水', 1, 'goods', 'http://82.157.42.25/59af3144214b4dce9c2fd13d3fdab8ef.webp', 'gaoge', 'gaoge', '2021-08-27 16:19:03', '2024-05-12 21:19:05', '高歌 18977771439 山东省德州市夏津县');
INSERT INTO `tb_order` VALUES (69, '山东大葱', 34.00, '山东大葱新鲜5斤时令蔬菜东北香葱包邮蘸酱铁杆章丘10号助农产品', 0, 'goods', 'http://82.157.42.25/1314259305d34bedb95f7800d9edaa8a.webp', 'gaoge', NULL, '2021-08-27 16:20:15', '2024-05-12 21:19:17', NULL);
INSERT INTO `tb_order` VALUES (70, '大别山野生羊肚菌', 345.00, '高端消费人群厨房食材 大别山野生羊肚菌干货煲汤菌菇类特产50克', 1, 'goods', 'http://82.157.42.25/7f2641a5dd0545b3b33aa8c5508e216c.webp', 'gaoge', 'gaoge', '2021-08-27 16:28:52', '2021-08-27 16:28:52', '高歌 18977771439 山东省德州市夏津县');
INSERT INTO `tb_order` VALUES (71, '苹果', 22.80, '东北鸡心果5斤锦绣海棠果特产玫瑰小苹果花红沙果孕妇新鲜水果。原产地直发，酸甜可口', 0, 'goods', 'http://82.157.42.25/59af3144214b4dce9c2fd13d3fdab8ef.webp', 'zhangxukun', NULL, '2021-08-27 16:30:06', '2021-08-31 14:49:40', NULL);
INSERT INTO `tb_order` VALUES (72, '白溪豆腐干香', 56.00, '白溪豆腐干香干湖南新化特产农家石磨手工风味柴火烟熏非武冈豆干', 0, 'goods', 'http://82.157.42.25/59af3144214b4dce9c2fd13d3fdab8ef.webp', 'gaoge', NULL, '2021-08-27 16:30:43', '2021-08-27 16:30:43', NULL);
INSERT INTO `tb_order` VALUES (73, '红柚', 23.80, '福建平和红心柚子9斤红肉蜜柚水果新鲜密柚当季琯溪孕妇整箱包邮', 1, 'goods', 'http://82.157.42.25/7f2641a5dd0545b3b33aa8c5508e216c.webp', 'zhangxukun', 'zhangxu', '2021-08-27 16:30:48', '2021-08-27 16:30:48', '张绪昆 18396833008 山东省青岛市');
INSERT INTO `tb_order` VALUES (74, '百香果', 14.90, '广西百香果9斤特大果新鲜水果紫皮百果香果酱白香果5一级10包邮', 0, 'goods', 'http://82.157.42.25/59af3144214b4dce9c2fd13d3fdab8ef.webp', 'zhangxukun', NULL, '2021-08-27 16:31:51', '2021-08-27 16:31:51', NULL);
INSERT INTO `tb_order` VALUES (76, '白葡萄', 79.00, '新疆无核白葡萄无籽水果新鲜当季整箱马奶提子青吐鲁番小葡萄5斤', 0, 'goods', 'http://82.157.42.25/59af3144214b4dce9c2fd13d3fdab8ef.webp', 'zhangxukun', NULL, '2021-08-27 16:33:11', '2021-08-27 16:33:11', NULL);
INSERT INTO `tb_order` VALUES (77, '榴莲', 65.80, '顺丰空运 泰国a级金枕头榴莲新鲜带壳巴掌当季进口水果整箱包邮', 0, 'goods', 'http://82.157.42.25/59af3144214b4dce9c2fd13d3fdab8ef.webp', 'zhangxukun', NULL, '2021-08-27 16:33:36', '2021-08-27 16:33:36', NULL);
INSERT INTO `tb_order` VALUES (78, '石榴', 15.80, '突尼斯软籽石榴水果新鲜10斤包邮无籽应季特大果会理甜红石榴一级', 0, 'goods', 'http://82.157.42.25/7f2641a5dd0545b3b33aa8c5508e216c.webp', 'zhangxukun', NULL, '2021-08-27 16:34:54', '2021-08-27 16:34:54', NULL);
INSERT INTO `tb_order` VALUES (79, '车厘子', 138.00, '新鲜车厘子5斤装高端进口大樱桃包邮山东整箱一箱10当季孕妇水果', 1, 'goods', 'http://82.157.42.25/7f2641a5dd0545b3b33aa8c5508e216c.webp', 'zhangxukun', 'admin', '2021-08-27 16:35:15', '2021-08-27 16:35:15', '成吉思汗 18766661438 山东青岛市崂山区');
INSERT INTO `tb_order` VALUES (80, '芋头新鲜小芋头', 324.00, '芋头新鲜小芋头江西农家蔬菜包邮毛芋仔梗芋子滑糯芋艿净重10斤', 0, 'goods', 'http://82.157.42.25/e7bd0ccdb03c416799e2e8f7b1860092.jpeg', 'gaoge', NULL, '2021-08-27 16:35:17', '2021-08-27 16:35:17', NULL);
INSERT INTO `tb_order` VALUES (81, '水蜜桃', 109.00, '正宗无锡水蜜桃湖景桃子旗舰店软桃新鲜水果礼盒装特产顺丰', 0, 'goods', 'http://82.157.42.25/e7bd0ccdb03c416799e2e8f7b1860092.jpeg', 'zhangxukun', NULL, '2021-08-27 16:35:53', '2021-08-27 16:35:53', NULL);
INSERT INTO `tb_order` VALUES (82, '西梅', 69.00, '新疆法兰西西梅甜孕妇水果新鲜当季整箱特级李子正宗喀什西梅便秘', 0, 'goods', 'http://82.157.42.25/7f2641a5dd0545b3b33aa8c5508e216c.webp', 'zhangxukun', NULL, '2021-08-27 16:37:42', '2021-08-27 16:37:42', NULL);
INSERT INTO `tb_order` VALUES (83, '求购100斤大米', 123.00, '求购100斤大米，质量佳的优先考虑', 0, 'needs', 'http://82.157.42.25/7f2641a5dd0545b3b33aa8c5508e216c.webp', 'gaogegege', NULL, '2021-08-27 16:38:29', '2021-08-27 17:27:49', NULL);
INSERT INTO `tb_order` VALUES (84, '黄桃', 27.80, '正宗锦绣黄桃9斤桃子新鲜当季水果露天脆蜜桃带毛砀山整箱10包邮。收藏下单 拍下17.8元起 精选好果 香甜多汁~', 0, 'goods', 'http://82.157.42.25/7f2641a5dd0545b3b33aa8c5508e216c.webp', 'zhangxukun', NULL, '2021-08-27 16:38:58', '2021-08-27 16:38:58', NULL);
INSERT INTO `tb_order` VALUES (86, '求购一千斤玉米', 2323.00, '急需，价低者来，请联系17826782782', 0, 'needs', 'http://82.157.42.25/7f2641a5dd0545b3b33aa8c5508e216c.webp', 'gaoge', NULL, '2021-08-27 16:40:28', '2021-08-27 16:40:28', NULL);
INSERT INTO `tb_order` VALUES (87, '潮汕蓝姜', 25.00, '新鲜南姜蓝姜潮汕姜潮州姜野生山姜满包邮免运费芦苇姜 5斤25元', 0, 'goods', 'http://82.157.42.25/e7bd0ccdb03c416799e2e8f7b1860092.jpeg', 'zwr', NULL, '2021-08-27 16:41:43', '2021-08-27 16:41:43', NULL);
INSERT INTO `tb_order` VALUES (88, '小香薯', 22.80, '求购临安天目山现挖小香薯', 0, 'needs', 'http://82.157.42.25/e7bd0ccdb03c416799e2e8f7b1860092.jpeg', 'zhangxukun', NULL, '2021-08-27 16:45:03', '2021-08-27 16:45:03', NULL);
INSERT INTO `tb_order` VALUES (89, '青龙脆瓜', 98.00, '青龙瓜脆瓜稍瓜菜瓜烧瓜酱瓜边梁烧瓜低糖水果非八棱脆瓜三斤装', 1, 'goods', 'http://82.157.42.25/7f2641a5dd0545b3b33aa8c5508e216c.webp', 'zwr', 'admin', '2021-08-27 16:45:11', '2021-08-27 16:46:16', '成吉思汗 18766661438 山东青岛市崂山区');
INSERT INTO `tb_order` VALUES (90, '求购90斤苹果', 2332.00, '甘甜可口，不要青苹果', 0, 'needs', 'http://82.157.42.25/e7bd0ccdb03c416799e2e8f7b1860092.jpeg', 'gaoge', NULL, '2021-08-27 16:45:29', '2021-08-27 16:45:29', NULL);
INSERT INTO `tb_order` VALUES (91, '安徽特产米糖小吃', 30.00, '传统农家手工花生炒米糖米花糖安徽特产米糖休闲食品老式小吃零食', 0, 'goods', 'http://82.157.42.25/e7bd0ccdb03c416799e2e8f7b1860092.jpeg', 'zwr', NULL, '2021-08-27 16:48:05', '2021-08-27 16:53:21', NULL);
INSERT INTO `tb_order` VALUES (92, '求购5斤红辣椒', 39.90, '求购5斤红辣椒，变态辣', 0, 'needs', 'http://82.157.42.25/e7bd0ccdb03c416799e2e8f7b1860092.jpeg', 'zhangxukun', NULL, '2021-08-27 16:48:34', '2021-08-27 16:48:34', NULL);
INSERT INTO `tb_order` VALUES (93, '求购新疆西瓜', 324.00, '要甜的，昼夜温差大的环境中种植的', 0, 'needs', 'http://82.157.42.25/e7bd0ccdb03c416799e2e8f7b1860092.jpeg', 'gaoge', NULL, '2021-08-27 16:51:02', '2021-08-30 13:18:15', NULL);
INSERT INTO `tb_order` VALUES (94, '求购10斤小紫薯', 48.80, '是小土豆，长不大，乒乓球大小的，但是要口感粉糯。有意者联系18396833838', 0, 'needs', 'http://82.157.42.25/e7bd0ccdb03c416799e2e8f7b1860092.jpeg', 'zhangxukun', NULL, '2021-08-27 16:51:11', '2021-08-27 16:51:11', NULL);
INSERT INTO `tb_order` VALUES (95, '来100斤茄子', 23321.00, '有的MM，价钱合理哦', 0, 'needs', 'http://82.157.42.25/59af3144214b4dce9c2fd13d3fdab8ef.webp', 'gaoge', NULL, '2021-08-27 16:53:20', '2021-08-27 16:53:20', NULL);
INSERT INTO `tb_order` VALUES (96, '甘蔗', 35.80, '广西黑皮甘蔗新鲜水果包邮当季特产脆甜杆孕妇果蔗批发整箱9-10斤', 0, 'goods', 'http://82.157.42.25/59af3144214b4dce9c2fd13d3fdab8ef.webp', 'zhangxukun', NULL, '2021-08-27 16:54:31', '2021-08-27 16:54:31', NULL);
INSERT INTO `tb_order` VALUES (97, '铁棍山药粉条', 69.00, '怀道居铁棍山药粉条河南焦作山药粉皮红薯正宗手工铁棍山药粉丝', 0, 'goods', 'http://82.157.42.25/1314259305d34bedb95f7800d9edaa8a.webp', 'zwr', NULL, '2021-08-27 16:55:10', '2021-08-27 16:55:10', NULL);
INSERT INTO `tb_order` VALUES (98, '收100斤羊肉', 2333.00, '价钱给够，带价来，咩咩咩', 0, 'needs', 'http://82.157.42.25/7f2641a5dd0545b3b33aa8c5508e216c.webp', 'gaoge', NULL, '2021-08-27 16:55:57', '2021-08-27 16:55:57', NULL);
INSERT INTO `tb_order` VALUES (99, '野生阳荷新鲜', 26.00, '湖北恩施现挖现新鲜阳荷姜现摘现发新鲜直达500克买2包邮送一野生', 0, 'goods', 'http://82.157.42.25/1314259305d34bedb95f7800d9edaa8a.webp', 'zwr', NULL, '2021-08-27 16:57:08', '2021-08-27 16:57:08', NULL);
INSERT INTO `tb_order` VALUES (100, '求购青苹果', 120.00, '求购40斤青苹果，有意者私聊', 0, 'needs', 'http://82.157.42.25/7f2641a5dd0545b3b33aa8c5508e216c.webp', 'zhangxukun', NULL, '2021-08-27 16:57:29', '2021-08-27 16:57:29', NULL);
INSERT INTO `tb_order` VALUES (101, '杨梅', 159.00, '求购浮宫杨梅新鲜当季孕妇水果应季非仙居东魁杨梅', 0, 'needs', 'http://82.157.42.25/1314259305d34bedb95f7800d9edaa8a.webp', 'zwr', NULL, '2021-08-27 16:59:45', '2021-08-27 16:59:45', NULL);
INSERT INTO `tb_order` VALUES (102, '洋芋蛋蛋', 12.40, '诚信求购新鲜洋芋蛋蛋', 0, 'needs', 'http://82.157.42.25/7f2641a5dd0545b3b33aa8c5508e216c.webp', 'zhangxukun', NULL, '2021-08-27 17:00:07', '2021-08-27 17:00:07', NULL);
INSERT INTO `tb_order` VALUES (103, '人参果', 96.00, '求购云南人参果圆果5斤精品中果应季孕妇圆水果新鲜当季', 0, 'needs', 'http://82.157.42.25/1314259305d34bedb95f7800d9edaa8a.webp', 'zwr', NULL, '2021-08-27 17:01:49', '2021-08-27 17:01:49', NULL);
INSERT INTO `tb_order` VALUES (104, '求购葡萄', 89.00, '求购福安象环葡萄巨峰产地大葡萄5斤', 0, 'needs', 'http://82.157.42.25/7f2641a5dd0545b3b33aa8c5508e216c.webp', 'zwr', NULL, '2021-08-27 17:10:07', '2021-08-27 17:10:07', NULL);
INSERT INTO `tb_order` VALUES (105, '甘蓝', 26.80, '求购羽衣甘蓝新鲜沙拉西餐蔬菜食材即食健身有机蔬菜2斤', 0, 'needs', 'http://82.157.42.25/1314259305d34bedb95f7800d9edaa8a.webp', 'zwr', NULL, '2021-08-27 17:14:26', '2021-08-27 17:14:26', NULL);
INSERT INTO `tb_order` VALUES (106, '佛手瓜', 19.90, '求购新鲜云南佛手瓜5斤', 0, 'needs', 'http://82.157.42.25/1314259305d34bedb95f7800d9edaa8a.webp', 'zwr', NULL, '2021-08-27 17:28:37', '2021-08-27 17:28:37', NULL);
INSERT INTO `tb_order` VALUES (112, 'SADASD', 0.12, 'dsadasDDSADASD', 0, 'needs', 'http://82.157.42.25/7f2641a5dd0545b3b33aa8c5508e216c.webp', 'gaoge', NULL, '2021-08-30 09:17:45', '2021-08-30 09:17:45', NULL);
INSERT INTO `tb_order` VALUES (113, '芒果', 12.00, '芒果芒果芒果芒果芒果', 0, 'goods', 'http://82.157.42.25/7f2641a5dd0545b3b33aa8c5508e216c.webp', 'zhangxu', NULL, '2021-08-31 10:18:31', '2021-08-31 10:18:31', NULL);
INSERT INTO `tb_order` VALUES (120, '阿斯蒂芬', 233.00, '对方的地方烦烦烦烦烦烦烦烦烦', 0, 'needs', 'http://82.157.42.25/7f2641a5dd0545b3b33aa8c5508e216c.webp', 'gaogle', NULL, '2024-05-12 18:30:39', '2024-05-12 18:30:50', NULL);
INSERT INTO `tb_order` VALUES (121, '香甜可口玉米棒', 128.00, '香甜可口玉米棒-美丽，香甜可口玉米棒-美丽，香甜可口玉米棒-美丽，你确定好吃吗', 1, 'goods', 'http://82.157.42.25/345c4a47c0df4a0caa4f44d9f817009b.webp', 'testuser', 'consumer', '2024-05-12 21:50:09', '2024-05-12 21:50:29', '消费者gaogle 17663228878 山东省济南市历下区10089胡同');
INSERT INTO `tb_order` VALUES (122, '求购千斤土豆', 789.00, '求购100斤新土豆，求购100斤新土豆，求购100斤新土豆，求购100斤新土豆', 0, 'needs', 'http://82.157.42.25/920fa41ae1e745208cd22f472a95dbf4.jpeg', 'testuser', NULL, '2024-05-12 21:51:54', '2024-05-12 21:52:04', NULL);

-- ----------------------------
-- Table structure for tb_question
-- ----------------------------
DROP TABLE IF EXISTS `tb_question`;
CREATE TABLE `tb_question`  (
  `id` int NOT NULL,
  `expertName` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '专家',
  `questioner` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '咨询者',
  `area` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '面积',
  `address` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '土地地址',
  `plantName` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '农作物名称',
  `soilCondition` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '土壤条件',
  `plantCondition` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '作物条件',
  `plantDetail` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '作物详细信息',
  `phone` int NOT NULL COMMENT '电话号',
  `answer` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '答案',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = COMPACT;

-- ----------------------------
-- Records of tb_question
-- ----------------------------

-- ----------------------------
-- Table structure for tb_test
-- ----------------------------
DROP TABLE IF EXISTS `tb_test`;
CREATE TABLE `tb_test`  (
  `证书` int NULL DEFAULT NULL
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = COMPACT;

-- ----------------------------
-- Records of tb_test
-- ----------------------------
INSERT INTO `tb_test` VALUES (2147483647);

-- ----------------------------
-- Table structure for tb_user
-- ----------------------------
DROP TABLE IF EXISTS `tb_user`;
CREATE TABLE `tb_user`  (
  `user_name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `password` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `nick_name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `phone` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `identity_num` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `address` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '地址',
  `role` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL DEFAULT 'user' COMMENT 'user普通用户，expert专家，admin管理员',
  `create_time` datetime NOT NULL,
  `update_time` datetime NOT NULL,
  `integral` int NOT NULL DEFAULT 500 COMMENT '积分500',
  `credit` int NOT NULL DEFAULT 5 COMMENT '信誉1，2，3，4，5',
  `avatar` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '头像',
  PRIMARY KEY (`user_name`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = COMPACT;

-- ----------------------------
-- Records of tb_user
-- ----------------------------
INSERT INTO `tb_user` VALUES ('admin', '$2a$10$73VypuKpUi0CN0TPKgwDGOOa/M.sZdtys55jciLdGXdpB/Gjv/yj.', '管理员账号示范', NULL, NULL, NULL, 'user,expert,admin', '2021-09-01 09:00:51', '2021-09-01 09:03:57', 500, 5, 'rongxiaotong.gif');
INSERT INTO `tb_user` VALUES ('angeluser', '$2a$10$sa7ZxDzQ259Fx/YEM45SZer2uLmlI5aiBlTIHG2N0UuTx3lac/uP2', '我是天使', '17663218888', NULL, '山东青岛', 'user,expert,admin', '2024-05-12 21:40:49', '2024-05-12 22:05:47', 500, 5, 'http://82.157.42.25/e16b7da853024bad9f927ae28fee2d7c.png');
INSERT INTO `tb_user` VALUES ('consumer', '$2a$10$ZD2VcX6q29nRbyCZoROUOuv1YNqjUru7890ZTHKY0LPUl/UXtRDL2', 'consumer', NULL, NULL, NULL, 'user,expert', '2024-05-12 21:54:10', '2024-05-12 22:03:45', 500, 5, 'http://82.157.42.25/29cc2feb6c7942559d1b0cf974b1a85b.png');
INSERT INTO `tb_user` VALUES ('gaoge', '$2a$10$WSkxxFHQal/5RnAaiozPjuzMsknjoPhsnnvguba0eNteA1EHoMhuG', 'gaogle', '18766661439', '231312323131231', '山东省崂山市108号胡同', 'user,expert,admin', '2021-08-27 16:05:20', '2024-05-12 22:45:49', 500, 5, 'http://82.157.42.25/40a8cb1b08e74913a02a5ee66f923773.png');
INSERT INTO `tb_user` VALUES ('gaogege', '$2a$10$RujD0/L.hSqBs1rE.VO4leBAk7grQROmnuEgXMxrykAQZQa9d1tPG', 'ggg ', NULL, NULL, NULL, 'user', '2021-09-01 08:52:08', '2021-09-01 08:52:08', 500, 5, 'rongxiaotong.gif');
INSERT INTO `tb_user` VALUES ('gaogegege', '$2a$10$q2WiiaehjVlbtJFBpCNgGuBGRR7b2d1Pkv5hVst5Pri.ZwJaQdIDW', 'hhh', '17662433654', '239032932000000', '山东省青岛市崂山区', 'user,expert', '2021-08-27 21:13:24', '2021-08-31 10:31:57', 500, 5, NULL);
INSERT INTO `tb_user` VALUES ('gaogle', '$2a$10$WSkxxFHQal/5RnAaiozPjuzMsknjoPhsnnvguba0eNteA1EHoMhuG', 'gaogle', '17663218818', '370406199801190110', 'SHANDONGHHH', 'user,expert,admin', '2024-05-12 14:57:52', '2024-05-12 21:12:55', 500, 5, 'http://82.157.42.25/05d60da7e3e44e3f952e1c6660612da5.png');
INSERT INTO `tb_user` VALUES ('testuser', '$2a$10$iTelrQmdp9/1CxS.BD/dAeAhf3c69JTZTSYa7o/rR9Zjk1xzgUSRm', '我是天使', '17668888888', NULL, '山东省崂山市108号', 'user', '2024-05-12 21:46:09', '2024-05-12 21:47:28', 500, 5, 'http://82.157.42.25/a5f9c91d600a475ca0250a7dd936ab76.png');
INSERT INTO `tb_user` VALUES ('zhangxu', '$2a$10$T9OGBbdL8DBpLZyBQmqBsOBArXEscdy.GI5k.jJR1E2y3VBkgghJm', '张绪', '13456567878', '370123200001012233', '山东省青岛市', 'user', '2021-08-31 10:13:42', '2021-09-01 09:12:19', 500, 5, 'rongxiaotong.gif');
INSERT INTO `tb_user` VALUES ('zhangxukun', '$2a$10$qQvA5bs1Mgg0NcKp3TbNku/IEYX7D6ccD8CmzdPJmxwzLrXgy6zpq', 'kelven', '13544545454', '370123200008083422', '山东省青岛市崂山区', 'user,expert,admin', '2021-08-27 16:05:25', '2021-09-01 09:18:44', 500, 5, 'rongxiaotong.gif');
INSERT INTO `tb_user` VALUES ('zwr', '$2a$10$zUaMIrMk.Gws.21Bzd.3aOeqgXpfPwKjnQdYxHK4FW5dCA.xx/6/G', '张文瑞', NULL, NULL, NULL, 'user,expert,admin', '2021-08-30 09:20:24', '2021-08-30 09:21:53', 500, 5, 'rongxiaotong.gif');

SET FOREIGN_KEY_CHECKS = 1;
