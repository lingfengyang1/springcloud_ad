/*
 Navicat Premium Dump SQL

 Source Server         : localhost
 Source Server Type    : MySQL
 Source Server Version : 80036 (8.0.36)
 Source Host           : localhost:3306
 Source Schema         : ad_system

 Target Server Type    : MySQL
 Target Server Version : 80036 (8.0.36)
 File Encoding         : 65001

 Date: 20/09/2025 16:30:53
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for ad
-- ----------------------------
DROP TABLE IF EXISTS `ad`;
CREATE TABLE `ad`  (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `name` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '广告名称',
  `category_id` bigint NOT NULL COMMENT '分类ID',
  `image_url` varchar(500) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '图片URL',
  `description` text CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL COMMENT '广告描述',
  `status` tinyint NULL DEFAULT 1 COMMENT '状态：0-禁用，1-启用',
  `create_time` datetime NULL DEFAULT CURRENT_TIMESTAMP,
  `update_time` datetime NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `category_id`(`category_id` ASC) USING BTREE,
  CONSTRAINT `ad_ibfk_1` FOREIGN KEY (`category_id`) REFERENCES `ad_category` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB AUTO_INCREMENT = 6 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci COMMENT = '广告表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of ad
-- ----------------------------
INSERT INTO `ad` VALUES (1, '111', 1, '/upload/files/2025/09/20/b8d2010c88c7473e99678e9c81c2e2fd.jpg', '222', 1, '2025-09-20 15:48:40', '2025-09-20 15:48:40');
INSERT INTO `ad` VALUES (3, '222', 1, '/upload/files/2025/09/20/50300ed31a1d476e86f2baf9b364cfc3.jpg', '222', 1, '2025-09-20 16:13:28', '2025-09-20 16:13:28');
INSERT INTO `ad` VALUES (4, '222', 2, '/upload/files/2025/09/20/a4652c7a793b410faccf53d1d377418a.jpg', '222', 1, '2025-09-20 16:13:36', '2025-09-20 16:13:36');
INSERT INTO `ad` VALUES (5, '33444', 4, '/upload/files/2025/09/20/68ba02c11b5241f7bcf9b8ca80a4855d.jpg', '111', 1, '2025-09-20 16:29:57', '2025-09-20 16:29:57');

-- ----------------------------
-- Table structure for ad_category
-- ----------------------------
DROP TABLE IF EXISTS `ad_category`;
CREATE TABLE `ad_category`  (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `name` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '分类名称',
  `sort` int NULL DEFAULT 0 COMMENT '排序',
  `create_time` datetime NULL DEFAULT CURRENT_TIMESTAMP,
  `update_time` datetime NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 7 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci COMMENT = '广告分类表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of ad_category
-- ----------------------------
INSERT INTO `ad_category` VALUES (1, '商业', 0, '2025-09-20 14:52:25', '2025-09-20 14:52:25');
INSERT INTO `ad_category` VALUES (2, '首页轮播', 1, '2025-09-20 14:52:59', '2025-09-20 14:52:59');
INSERT INTO `ad_category` VALUES (3, '弹窗广告', 2, '2025-09-20 14:52:59', '2025-09-20 14:52:59');
INSERT INTO `ad_category` VALUES (4, '侧边栏广告', 3, '2025-09-20 14:52:59', '2025-09-20 14:52:59');
INSERT INTO `ad_category` VALUES (5, '信息流广告', 4, '2025-09-20 14:52:59', '2025-09-20 14:52:59');
INSERT INTO `ad_category` VALUES (6, '底部横幅', 5, '2025-09-20 14:52:59', '2025-09-20 14:52:59');

SET FOREIGN_KEY_CHECKS = 1;
