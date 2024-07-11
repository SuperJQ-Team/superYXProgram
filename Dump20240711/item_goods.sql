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
-- Table structure for table `goods`
--

DROP TABLE IF EXISTS `goods`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `goods` (
  `id` bigint NOT NULL AUTO_INCREMENT COMMENT 'ID',
  `name` varchar(90) DEFAULT NULL COMMENT '名称',
  `summary` varchar(300) DEFAULT NULL COMMENT '简介',
  `image_path` varchar(300) DEFAULT NULL COMMENT '缩略图路径',
  `price` varchar(90) DEFAULT NULL COMMENT '单价',
  `count` int DEFAULT NULL COMMENT '剩余数量',
  `del_flag` tinyint(1) DEFAULT NULL COMMENT '删除标记',
  `created_by` varchar(90) DEFAULT NULL COMMENT '创建人',
  `created_time` datetime DEFAULT NULL COMMENT '创建时间',
  `update_by` varchar(90) DEFAULT NULL COMMENT '更新人',
  `update_time` datetime DEFAULT NULL COMMENT '更新时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=22 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='商品表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `goods`
--

LOCK TABLES `goods` WRITE;
/*!40000 ALTER TABLE `goods` DISABLE KEYS */;
INSERT INTO `goods` VALUES (1,'原石','超出世界的原初晶石。黑暗的宇宙中，由无主的梦想与希望凝结而成的辉光。','1.png','114',NULL,0,'system','2024-07-10 00:00:00',NULL,NULL),(2,'至纯源石','大地被起因不明的天灾四处肆虐，经由天灾席卷过的土地上出现了大量的神秘矿物——“源石”。依赖于技术的进步，源石蕴含的能量投入工业后使得文明顺利迈入现代，与此同时，源石本身也催生出“感染者”的存在。','2.png','5140',NULL,0,'system','2024-07-10 00:00:00',NULL,NULL),(3,'D32钢','强度超群，无法击穿，顺畅传导源石技艺，将重新订立武器材料的标准。','3.png','399',NULL,0,'system','2024-07-10 00:00:00',NULL,NULL),(4,'晶体电子单元','泰拉源石科技的结晶，泰拉工业现代化的代表。源石施术单元与城际网络服务器的制造都离不开这种科技产品。','4.png','289',NULL,0,'system','2024-07-10 00:00:00',NULL,NULL),(5,'双极纳米片','对范围内源石敏感的装置。能有效提升源石附近范围内作战武器和设备对源石的敏感性，使源石技艺的存储几近可能。','5.png','286',NULL,0,'system','2024-07-10 00:00:00',NULL,NULL),(6,'聚合剂','精密穿戴着装中常用的材料。多作为隔绝涂层使用。强大的聚合粘接效果足以阻断源石的挥发。','6.png','193',NULL,0,'system','2024-07-10 00:00:00',NULL,NULL),(7,'异铁块','在极为苛刻的条件下，由多套异铁组熔铸成型的异铁块。已知异铁材料中最为稳定也最为稀少的形式，作为原材料，足以打破现有制品运用领域的疆界。','8.png','66',NULL,0,'system','2024-07-10 00:00:00',NULL,NULL),(8,'酮阵列','由工程干员严格监控的大量不稳定实验性酮制剂。是高级的工业材料之一，运用时也请多加小心。','9.png','55',NULL,0,'system','2024-07-10 00:00:00',NULL,NULL),(9,'家具零件','桌腿、扶手、坐垫，什么都有，只要稍加组合，就能变成精美的家具了。','10.png','1',NULL,0,'system','2024-07-10 00:00:00',NULL,NULL),(10,'高级加固建材','请勿对使用该标准建材维护过的房间进行无限制的稳定性测试！理论认为，目前已知的测试手段并不能破坏此房间的结构完整，但有可能导致实验者灰飞烟灭。','11.png','13',NULL,0,'system','2024-07-10 00:00:00',NULL,NULL),(11,'简化试衣间','为模特准备的试衣间。如果拍艺术照的话需要在这里更衣。','12.png','40',NULL,0,'system','2024-07-10 00:00:00',NULL,NULL),(12,'多功能支架','能够自由伸缩和悬挂装置的支架。挂一位干员上去也没问题，卡达自己试过。','13.png','75',NULL,0,'system','2024-07-10 00:00:00',NULL,NULL),(13,'“风味魔法师”','饮料机，送餐盘，餐桌吧台规整干净。店主的烹饪创意与音乐狂想都在此处诞生。小提示：只有好好吃饭的小孩子才能从罐子里拿一颗水果硬糖。','14.png','113',NULL,0,'system','2024-07-10 00:00:00',NULL,NULL),(14,'缤纷冲浪板架','\"样式繁多、色彩缤纷的冲浪板在此整齐码放着。 这些冲浪板曾是最热销的商品，如今却是最老旧的装潢。\"','15.png','97',NULL,0,'system','2024-07-10 00:00:00',NULL,NULL),(15,'风情街户外圆桌','\"风情街店铺前常见的户外圆桌，酒水单上印有最新的创意饮品。 咕噜噜的温泉，咕噜噜的火山咖啡，咕噜噜的汐斯塔假日。\"','16.png','223',NULL,0,'system','2024-07-10 00:00:00',NULL,NULL),(16,'恒温育苗箱','\"研究站的标配育苗箱，培育受天灾影响的植物以供研究。 风暴席卷荒野后，天灾信使们会将外出拾回的种子埋下。令他们欣喜的是，总有嫰苗能破土而出。\"','17.png','669',NULL,0,'system','2024-07-10 00:00:00',NULL,NULL),(17,'观测记录工作台','\"配置个人终端的工作台，拥挤的桌面仍留有小憩的空间。 风扇吹动沉眠的发丝，纸笔在天灾信使的梦中沙沙作响。\"','18.png','998',NULL,0,'system','2024-07-10 00:00:00',NULL,NULL),(18,'简易气象观测架','\"将各类气象仪器简单组装制成的观测设备，能正常运行已经是奇迹。 清洗过后，普罗旺斯习惯将尾巴搁在上面晾干。\"','19.png','876',NULL,0,'system','2024-07-10 00:00:00',NULL,NULL),(19,'环烃预制体','在合成流程中加入多种优良材料而得到的工业产品，保证透光度的同时大幅提升了强度与抗冲击性，在防护领域具有广阔应用前景。','7.png','83',NULL,0,'system','2024-07-10 00:00:00',NULL,NULL),(20,'地质样品采集器','\"能够自动采集样品的新型设备，理论上能大大提高采样效率。 刚收到时地灵对它尤为喜爱，但因其故障地灵被迫多次加班，此后地灵投向它的目光中只剩下怨恨。\"','20.png','980',NULL,0,'system','2024-07-10 00:00:00',NULL,NULL),(21,'不存在的商品','none','.jpg','0',NULL,0,'system','2024-07-10 00:00:00',NULL,NULL);
/*!40000 ALTER TABLE `goods` ENABLE KEYS */;
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
