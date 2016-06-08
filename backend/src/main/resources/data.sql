
/*!40000 ALTER TABLE `information_provider_entity` DISABLE KEYS */;
INSERT INTO `information_provider_entity` VALUES (1,NULL,'Provider A'),(2,NULL,'Provider B'),(3,NULL,'Provider C');
/*!40000 ALTER TABLE `information_provider_entity` ENABLE KEYS */;

--
-- Dumping data for table `information_requester_entity`
--

/*!40000 ALTER TABLE `information_requester_entity` DISABLE KEYS */;
INSERT INTO `information_requester_entity` VALUES (1,'http://callback.url','umbreak@gmail.com','ftpUser1','ftpPass1','ftp.test.com','$2a$10$8aG1HoBMEjYU1lg4o8YCyOoWvlE0vReB02mR7fxy7r1wIK5EVQDNa','Montero','user1'),(2,'http://callback.url','umbreak2@gmail.com','ftpUser2','ftpPass2','ftp.test.com','$2a$10$aAhiAsuisLlHWI2KAeUvV.GxI.VVwAooxLN2kU6GASY2ErCsCsAX.','Papa','user2'),(3,'http://callback.url','umbreak3@gmail.com','ftpUser2','ftpPass2','ftp.test.com','$2a$10$m.ZFtCxuj1/Ng5yxkowRp.qT5lGdDa9DTngnvheLCuShEanPWTMlS','Papaa','user3'),(4,'http://callback.url','umbreak4@gmail.com','ftpUser2','ftpPass2','ftp.test.com','$2a$10$ylxokmatpRUgfBXLWH8ugudrfPB.veilsmI3DWX6RQGeyhItfkLbq','Mendez','user4');
/*!40000 ALTER TABLE `information_requester_entity` ENABLE KEYS */;



/*!40000 ALTER TABLE `role_entity` DISABLE KEYS */;
INSERT INTO `role_entity` VALUES (1,'USER'),(2,'SUBSCRIBER'),(3,'UNLIMITED');
/*!40000 ALTER TABLE `role_entity` ENABLE KEYS */;


--
-- Dumping data for table `providers_roles`
--

/*!40000 ALTER TABLE `providers_roles` DISABLE KEYS */;
INSERT INTO `providers_roles` VALUES (1,1),(1,2),(2,2),(1,3),(2,3),(3,3);
/*!40000 ALTER TABLE `providers_roles` ENABLE KEYS */;

--
-- Dumping data for table `subscription_entity`
--

/*!40000 ALTER TABLE `subscription_entity` DISABLE KEYS */;
INSERT INTO `subscription_entity` VALUES (5,'FTP','Dayly','{\"page\":null,\"provider\":{\"id\":1,\"name\":\"Provider A\"},\"survey\":{\"target\":{\"gender\":\"Male\",\"age\":[12,60],\"income\":{\"currency\":\"EUR\",\"range\":[1000,3000]}},\"subject\":\"stud\",\"country\":null},\"subscription\":null}','2016-05-20 16:23:50',2);
/*!40000 ALTER TABLE `subscription_entity` ENABLE KEYS */;

--
-- Dumping data for table `survey_entity`
--

/*!40000 ALTER TABLE `survey_entity` DISABLE KEYS */;
INSERT INTO `survey_entity` VALUES (1,'DE',NULL,'Science studies',1),(2,'DE',NULL,'Engineering studies',1),(3,'ES',NULL,'Economics studies',2),(4,'DE',NULL,'No studies',2),(5,'DE',NULL,'Agricultural studies',3),(6,'ES',NULL,'Arts studies',1);
/*!40000 ALTER TABLE `survey_entity` ENABLE KEYS */;


--
-- Dumping data for table `target_entity`
--

/*!40000 ALTER TABLE `target_entity` DISABLE KEYS */;
INSERT INTO `target_entity` VALUES (1,41,'Male',2772,'EUR','Juan',NULL,1),(2,31,'Female',1349,'EUR','Sophie',NULL,1),(3,23,'Male',1372,'EUR','Arnau',NULL,1),(4,53,'Female',2603,'EUR','Cristina',NULL,1),(5,50,'Female',3560,'EUR','Laura',NULL,2),(6,36,'Male',1911,'EUR','Guillem',NULL,2),(7,55,'Female',2938,'EUR','Maria',NULL,3),(8,49,'Male',1767,'EUR','Fran',NULL,3),(9,52,'Female',3864,'EUR','Franzi',NULL,3),(10,24,'Female',3389,'EUR','Sara',NULL,4),(11,58,'Female',2629,'EUR','Paula',NULL,4),(12,41,'Female',1585,'EUR','Sandra',NULL,4),(13,21,'Female',1906,'EUR','Monique',NULL,5),(14,40,'Female',2829,'EUR','Anna',NULL,5),(15,45,'Male',2395,'EUR','Aaron',NULL,5),(16,58,'Male',3324,'EUR','Marcos',NULL,6),(17,52,'Male',2783,'EUR','Adam',NULL,6),(18,42,'Male',2814,'EUR','Anton',NULL,6);
/*!40000 ALTER TABLE `target_entity` ENABLE KEYS */;


--
-- Dumping data for table `users_roles`
--

/*!40000 ALTER TABLE `users_roles` DISABLE KEYS */;
INSERT INTO `users_roles` VALUES (1,1),(2,1),(3,1),(3,2),(4,3);
/*!40000 ALTER TABLE `users_roles` ENABLE KEYS */;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2016-05-20 17:14:49
