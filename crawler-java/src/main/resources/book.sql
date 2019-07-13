/*
 Navicat Premium Data Transfer

 Source Server         : test
 Source Server Type    : MySQL
 Source Server Version : 50715
 Source Host           : localhost
 Source Database       : exchangebook

 Target Server Type    : MySQL
 Target Server Version : 50715
 File Encoding         : utf-8

 Date: 07/11/2019 20:39:50 PM
*/

SET NAMES utf8;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
--  Table structure for `book`
-- ----------------------------
DROP TABLE IF EXISTS `book`;
CREATE TABLE `book` (
  `bookId` bigint(20) NOT NULL AUTO_INCREMENT,
  `bookName` varchar(50) DEFAULT NULL,
  `bookAuthor` varchar(50) DEFAULT NULL,
  `bookOriginalName` varchar(100) DEFAULT NULL,
  `bookPublish` varchar(20) DEFAULT NULL,
  `bookPublishTime` varchar(20) DEFAULT NULL,
  `bookBind` varchar(20) DEFAULT NULL,
  `price` varchar(50) DEFAULT NULL,
  `isbn` varchar(20) DEFAULT NULL,
  `image` varchar(255) DEFAULT NULL,
  `pageNumber` varchar(50) DEFAULT NULL,
  `bookProducers` varchar(20) DEFAULT NULL,
  `bookSeries` varchar(50) DEFAULT NULL,
  `bookTranslator` varchar(30) DEFAULT NULL,
  `doubanStar` varchar(3) DEFAULT NULL,
  `bookType` varchar(255) DEFAULT NULL,
  `parentTypeId` varchar(255) DEFAULT NULL,
  `bookTypeName` varchar(255) DEFAULT NULL,
  `bookContentInfo` text,
  `bookAuthorInfo` text,
  `bookCatalogue` text,
  PRIMARY KEY (`bookId`)
) ENGINE=InnoDB  DEFAULT CHARSET=utf8;

SET FOREIGN_KEY_CHECKS = 1;
