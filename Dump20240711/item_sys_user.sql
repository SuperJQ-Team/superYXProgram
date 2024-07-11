-- MySQL dump 10.13  Distrib 8.0.34, for Win64 (x86_64)
--
-- Host: localhost    Database: item
-- ------------------------------------------------------
-- Server version	8.0.35

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!50503 SET NAMES utf8 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `sys_user`
--

DROP TABLE IF EXISTS `sys_user`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `sys_user` (
  `id` bigint NOT NULL AUTO_INCREMENT COMMENT 'ID',
  `user_name` varchar(90) DEFAULT NULL COMMENT '用户名',
  `password` varchar(90) DEFAULT NULL COMMENT '密码',
  `role` varchar(90) DEFAULT NULL COMMENT '角色',
  `virtual_name` varchar(90) DEFAULT NULL COMMENT '昵称',
  `head_image` longblob COMMENT '头像',
  `summary` varchar(300) DEFAULT NULL COMMENT '简介',
  `del_flag` tinyint(1) DEFAULT NULL COMMENT '删除标记',
  `created_by` varchar(90) DEFAULT NULL COMMENT '创建人',
  `created_time` datetime DEFAULT NULL COMMENT '创建时间',
  `update_by` varchar(90) DEFAULT NULL COMMENT '更新人',
  `update_time` datetime DEFAULT NULL COMMENT '更新时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='用户表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `sys_user`
--

LOCK TABLES `sys_user` WRITE;
/*!40000 ALTER TABLE `sys_user` DISABLE KEYS */;
INSERT INTO `sys_user` VALUES (1,'1','1','administrator','1',NULL,NULL,0,'system','2024-07-10 00:00:00',NULL,'2024-04-09 19:32:55'),(2,'555','1','user',NULL,NULL,NULL,0,'system','2024-07-10 00:00:00',NULL,NULL),(3,'Super_YX','666','administrator','Super YX',NULL,NULL,0,'system','2024-07-10 00:00:00',NULL,'2023-12-25 06:25:12'),(4,'Super_SF','111','administrator','Super SF',NULL,NULL,0,'system','2024-07-10 00:00:00',NULL,'2024-04-09 16:41:13'),(5,'Super_JQ','114','administrator','Super_JQ',NULL,NULL,0,'system','2024-07-10 00:00:00',NULL,'2024-04-08 16:22:19'),(6,'112233','123456','user',NULL,NULL,NULL,0,'system','2024-07-10 00:00:00',NULL,NULL),(7,'yixiao_2024','yixiao@2024','user','genshin666',NULL,NULL,0,'system','2024-07-10 00:00:00',NULL,'2024-04-09 19:22:00'),(8,'yixiao_2024','114514','user',NULL,NULL,NULL,0,'system','2024-07-10 00:00:00',NULL,NULL),(9,'genshin666','114514','user',NULL,NULL,NULL,0,'system','2024-07-10 00:00:00',NULL,NULL);
/*!40000 ALTER TABLE `sys_user` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2024-07-11 23:38:06
