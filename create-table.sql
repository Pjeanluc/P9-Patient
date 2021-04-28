CREATE TABLE `user` (
   `id` bigint NOT NULL,
   `password` varchar(100) DEFAULT NULL,
   `pseudo` varchar(100) DEFAULT NULL,
   `username` varchar(100) DEFAULT NULL,
   `role` varchar(255) DEFAULT NULL,
   PRIMARY KEY (`id`)
 ) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

 CREATE TABLE `patient` (
   `id` bigint NOT NULL,
   `address` varchar(100) DEFAULT NULL,
   `birth_date` datetime DEFAULT NULL,
   `firstname` varchar(100) DEFAULT NULL,
   `genre` varchar(255) DEFAULT NULL,
   `lastname` varchar(100) DEFAULT NULL,
   `phone_number` varchar(100) DEFAULT NULL,
   PRIMARY KEY (`id`)
 ) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

 INSERT INTO aber.user VALUES (1,'{bcrypt}$2a$10$2.ioFYaJlqI0NDE3sueqlO/3gPPvPl81pw.9qoi.Xd7ANfDyEzsZq','admin','admin','ADMIN');

 INSERT INTO aber.user VALUES (2,'{bcrypt}$2a$10$2.ioFYaJlqI0NDE3sueqlO/3gPPvPl81pw.9qoi.Xd7ANfDyEzsZq','user','user','USER');

