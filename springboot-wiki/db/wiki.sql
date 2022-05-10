/*
 Navicat Premium Data Transfer

 Source Server         : lixunsam
 Source Server Type    : MySQL
 Source Server Version : 80025
 Source Host           : localhost:3306
 Source Schema         : 1908rg

 Target Server Type    : MySQL
 Target Server Version : 80025
 File Encoding         : 65001

 Date: 10/05/2022 16:44:07
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for category
-- ----------------------------
DROP TABLE IF EXISTS `category`;
CREATE TABLE `category`  (
  `id` bigint(0) NOT NULL COMMENT 'id',
  `parent` bigint(0) NOT NULL DEFAULT 0 COMMENT '父id',
  `name` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '名称',
  `sort` int(0) NULL DEFAULT NULL COMMENT '顺序',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci COMMENT = '分类' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of category
-- ----------------------------
INSERT INTO `category` VALUES (100, 0, '前端开发', 100);
INSERT INTO `category` VALUES (101, 100, 'Vue', 101);
INSERT INTO `category` VALUES (102, 100, 'HTML & CSS', 102);
INSERT INTO `category` VALUES (200, 0, 'Java', 200);
INSERT INTO `category` VALUES (201, 200, '基础应用', 201);
INSERT INTO `category` VALUES (202, 200, '框架应用', 202);
INSERT INTO `category` VALUES (300, 0, 'Python', 300);
INSERT INTO `category` VALUES (301, 300, '基础应用', 301);
INSERT INTO `category` VALUES (302, 300, '进阶方向应用', 302);
INSERT INTO `category` VALUES (400, 0, '数据库', 400);
INSERT INTO `category` VALUES (401, 400, 'MySQL', 401);
INSERT INTO `category` VALUES (402, 400, 'Oracle', 100);
INSERT INTO `category` VALUES (500, 0, '其它', 500);
INSERT INTO `category` VALUES (501, 500, '服务器', 501);
INSERT INTO `category` VALUES (502, 500, '开发工具', 502);
INSERT INTO `category` VALUES (503, 500, '热门服务端语言', 503);
INSERT INTO `category` VALUES (153400594147708928, 0, '111', 333);
INSERT INTO `category` VALUES (165065615030226944, 100, 'React', 103);

-- ----------------------------
-- Table structure for content
-- ----------------------------
DROP TABLE IF EXISTS `content`;
CREATE TABLE `content`  (
  `id` bigint(0) NOT NULL COMMENT '文档id',
  `content` mediumtext CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '内容',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci COMMENT = '文档内容' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of content
-- ----------------------------
INSERT INTO `content` VALUES (1, '<div class=\"content-box\" style=\"display:;\"><div class=\"content-top\"><div class=\"left\"><a href=\"/t/8269242\" target=\"_blank\"><img src=\"//img.mukewang.com/5333a2d100019c1d02000200-100-100.jpg\" class=\"avatar\">\r\n                        Tecvan <i>·</i></a>\r\n                    更新于 2020-10-26\r\n                </div> <div class=\"right clearfix\"><!----> <a href=\"/wiki/echarts/echartsinstall.html\" class=\"next clearfix r\"><p title=\"ECharts 安装\" class=\"l name\">ECharts 安装</p> <p class=\"l point\">下一节</p> <i class=\"l imv2-drop_right\"></i></a></div></div> <div class=\"content\"><div class=\"content-item text-item\"><div class=\"cl-preview-section\"><a id=\"anchor_0\" class=\"virtual_anchor\" target=\"_blank\"></a><h1 id=\"echarts-简介\">ECharts 简介</h1>\r\n</div><div class=\"cl-preview-section\"><p><img src=\"//img.mukewang.com/wiki/5f967c8009f492c818470538.jpg\" alt=\"图片描述\" style=\"cursor: pointer;\"></p>\r\n</div><div class=\"cl-preview-section\"><center><font size=\"1\">ECharts（图片来源于网络）</font></center>\r\n</div><div class=\"cl-preview-section\"><blockquote>\r\n<p>ECharts，一个使用 JavaScript 实现的开源可视化库，可以流畅的运行在 PC 和移动设备上，兼容当前绝大部分浏览器（IE8/9/10/11，Chrome，Firefox，Safari等），底层依赖矢量图形库 ZRender，提供直观，交互丰富，可高度个性化定制的数据可视化图表。 ——ECharts 官网</p>\r\n</blockquote>\r\n</div><div class=\"cl-preview-section\"><p>现如今，数据的收集、存储、分析成本越来越低，许多企业已将大数据视作企业发展的战略资源，衍生出对数据可视化在功能层面、交互层面的巨大需求。ECharts 作为国内目前影响力最大的可视化工具库，一方面它内置了丰富的2D、3D图表，以及大量辅助交互组件，另一方面它提供了一套行之有效的图形扩展接口，方便开发者接入自定义图表，很大程度上能够覆盖大多数可视化场景。</p>\r\n</div><div class=\"cl-preview-section\"><p>另外，ECharts 社区活跃，有丰富的中文文档，学习曲线低；支持PC、移动端、小程序等场景；能兼容市面上大多数浏览器等等。非常值得入手作为常备工具库。</p>\r\n</div><div class=\"cl-preview-section\"><a id=\"anchor_1\" class=\"virtual_anchor\" target=\"_blank\"></a><h2 id=\"特点\">1. 特点</h2>\r\n</div><div class=\"cl-preview-section\"><a id=\"anchor_2\" class=\"virtual_anchor\" target=\"_blank\"></a><h3 id=\"丰富的可视化类型\">丰富的可视化类型</h3>\r\n</div><div class=\"cl-preview-section\"><p>ECharts 提供了常规的折线图、柱状图、散点图、饼图、K线图，用于统计的盒形图，用于地理数据可视化的地图、热力图、线图，用于关系数据可视化的关系图、treemap、旭日图，多维数据可视化的平行坐标，还有用于 BI 的漏斗图，仪表盘，并且支持图与图之间的混搭。</p>\r\n</div><div class=\"cl-preview-section\"><p>除了已经内置的包含了丰富功能的图表，ECharts 还提供了自定义系列，只需要传入一个_renderItem_函数，就可以从数据映射到任何你想要的图形，更棒的是这些都还能和已有的交互组件结合使用而不需要操心其它事情。</p>\r\n</div><div class=\"cl-preview-section\"><p>你可以在下载界面下载包含所有图表的构建文件，如果只是需要其中一两个图表，又嫌包含所有图表的构建文件太大，也可以在在线构建中选择需要的图表类型后自定义构建。</p>\r\n</div><div class=\"cl-preview-section\"><a id=\"anchor_3\" class=\"virtual_anchor\" target=\"_blank\"></a><h3 id=\"多种数据格式无需转换直接使用\">多种数据格式无需转换直接使用</h3>\r\n</div><div class=\"cl-preview-section\"><p>ECharts 内置的 dataset 属性（4.0+）支持直接传入包括二维表，key-value 等多种格式的数据源，通过简单的设置 encode 属性就可以完成从数据到图形的映射，这种方式更符合可视化的直觉，省去了大部分场景下数据转换的步骤，而且多个组件能够共享一份数据而不用克隆。</p>\r\n</div><div class=\"cl-preview-section\"><p>为了配合大数据量的展现，ECharts 还支持输入 TypedArray 格式的数据，TypedArray 在大数据量的存储中可以占用更少的内存，对 GC 友好等特性也可以大幅度提升可视化应用的性能。</p>\r\n</div><div class=\"cl-preview-section\"><a id=\"anchor_4\" class=\"virtual_anchor\" target=\"_blank\"></a><h3 id=\"千万数据的前端展现\">千万数据的前端展现</h3>\r\n</div><div class=\"cl-preview-section\"><p>通过增量渲染技术（4.0+），配合各种细致的优化，ECharts 能够展现千万级的数据量，并且在这个数据量级依然能够进行流畅的缩放平移等交互。</p>\r\n</div><div class=\"cl-preview-section\"><p>几千万的地理坐标数据就算使用二进制存储也要占上百 MB 的空间。因此 ECharts 同时提供了对流加载（4.0+）的支持，你可以使用 WebSocket 或者对数据分块后加载，加载多少渲染多少！不需要漫长地等待所有数据加载完再进行绘制。</p>\r\n</div><div class=\"cl-preview-section\"><a id=\"anchor_5\" class=\"virtual_anchor\" target=\"_blank\"></a><h3 id=\"移动端优化\">移动端优化</h3>\r\n</div><div class=\"cl-preview-section\"><p>ECharts 针对移动端交互做了细致的优化，例如移动端小屏上适于用手指在坐标系中进行缩放、平移。 PC 端也可以用鼠标在图中进行缩放（用鼠标滚轮）、平移等。</p>\r\n</div><div class=\"cl-preview-section\"><p>细粒度的模块化和打包机制可以让 ECharts 在移动端也拥有很小的体积，可选的 SVG 渲染模块让移动端的内存占用不再捉襟见肘。</p>\r\n</div><div class=\"cl-preview-section\"><a id=\"anchor_6\" class=\"virtual_anchor\" target=\"_blank\"></a><h3 id=\"多渲染方案，跨平台使用！\">多渲染方案，跨平台使用！</h3>\r\n</div><div class=\"cl-preview-section\"><p>ECharts 支持以 Canvas、SVG（4.0+）、VML 的形式渲染图表。VML 可以兼容低版本 IE，SVG 使得移动端不再为内存担忧，Canvas 可以轻松应对大数据量和特效的展现。不同的渲染方式提供了更多选择，使得 ECharts 在各种场景下都有更好的表现。</p>\r\n</div><div class=\"cl-preview-section\"><p>除了 PC 和移动端的浏览器，ECharts 还能在 node 上配合 node-canvas 进行高效的服务端渲染（SSR）。从 4.0 开始我们还和微信小程序的团队合作，提供了 ECharts 对小程序的适配！</p>\r\n</div><div class=\"cl-preview-section\"><p>社区热心的贡献者也为我们提供了丰富的其它语言扩展，比如 Python 的 pyecharts，R 语言的  recharts, Julia 的  ECharts.jl  等等。</p>\r\n</div><div class=\"cl-preview-section\"><p>我们希望平台和语言都不会成为大家使用 ECharts 实现可视化的限制！</p>\r\n</div><div class=\"cl-preview-section\"><a id=\"anchor_7\" class=\"virtual_anchor\" target=\"_blank\"></a><h3 id=\"深度的交互式数据探索\">深度的交互式数据探索</h3>\r\n</div><div class=\"cl-preview-section\"><p>交互是从数据中发掘信息的重要手段。“总览为先，缩放过滤按需查看细节”是数据可视化交互的基本需求。</p>\r\n</div><div class=\"cl-preview-section\"><p>ECharts 一直在_交互_的路上前进，我们提供了  图例、视觉映射、数据区域缩放、tooltip、数据刷选等开箱即用的交互组件，可以对数据进行多维度数据筛取、视图缩放、展示细节等交互操作。</p>\r\n</div><div class=\"cl-preview-section\"><a id=\"anchor_8\" class=\"virtual_anchor\" target=\"_blank\"></a><h3 id=\"多维数据的支持以及丰富的视觉编码手段\">多维数据的支持以及丰富的视觉编码手段</h3>\r\n</div><div class=\"cl-preview-section\"><p>ECharts 3 开始加强了对多维数据的支持。除了加入了平行坐标等常见的多维数据可视化工具外，对于传统的散点图等，传入的数据也可以是多个维度的。配合视觉映射组件  visualMap  提供的丰富的视觉编码，能够将不同维度的数据映射到颜色、大小、透明度、明暗度等不同的视觉通道。</p>\r\n</div><div class=\"cl-preview-section\"><a id=\"anchor_9\" class=\"virtual_anchor\" target=\"_blank\"></a><h3 id=\"动态数据\">动态数据</h3>\r\n</div><div class=\"cl-preview-section\"><p>ECharts 由数据驱动，数据的改变驱动图表展现的改变。因此动态数据的实现也变得异常简单，只需要获取数据，填入数据，ECharts 会找到两组数据之间的差异然后通过合适的动画去表现数据的变化。配合  timeline 组件能够在更高的时间维度上去表现数据的信息。</p>\r\n</div><div class=\"cl-preview-section\"><a id=\"anchor_10\" class=\"virtual_anchor\" target=\"_blank\"></a><h3 id=\"绚丽的特效\">绚丽的特效</h3>\r\n</div><div class=\"cl-preview-section\"><p>ECharts 针对线数据，点数据等地理数据的可视化提供了吸引眼球的特效。</p>\r\n</div><div class=\"cl-preview-section\"><a id=\"anchor_11\" class=\"virtual_anchor\" target=\"_blank\"></a><h3 id=\"通过-gl-实现更多更强大绚丽的三维可视化\">通过 GL 实现更多更强大绚丽的三维可视化</h3>\r\n</div><div class=\"cl-preview-section\"><p>想要在 VR，大屏场景里实现三维的可视化效果？我们提供了基于 WebGL 的 ECharts GL，你可以跟使用 ECharts 普通组件一样轻松的使用 ECharts GL 绘制出三维的地球，建筑群，人口分布的柱状图，在这基础之上我们还提供了不同层级的画面配置项，几行配置就能得到艺术化的画面！</p>\r\n</div><div class=\"cl-preview-section\"><a id=\"anchor_12\" class=\"virtual_anchor\" target=\"_blank\"></a><h3 id=\"无障碍访问（4.0）\">无障碍访问（4.0+）</h3>\r\n</div><div class=\"cl-preview-section\"><p>当我们说到“可视化”的时候，我们往往很自然地将它与“看得⻅”联系在一起，但其 实这是片面的。W3C制定了无障碍富互联网应用规范集(WAI-ARIA，the Accessible Rich Internet Applications Suite)，致力于使得网⻚内容和网⻚应 用能够被更多残障人士访问。</p>\r\n</div><div class=\"cl-preview-section\"><p>ECharts 4.0 遵从这一规范，支持自动根据图表配置项智能生成描述，使得盲人可 以在朗读设备的帮助下了解图表内容，让图表可以被更多人群访问！</p>\r\n</div><div class=\"cl-preview-section\"><!-- ## &#20027;&#27969;&#25968;&#25454;&#21487;&#35270;&#21270;&#24211;&#23545;&#27604; -->\r\n</div><div class=\"cl-preview-section\"><!-- &#27492;&#22788;&#21487;&#20197;&#21015;&#20030;&#19968;&#20123;&#20854;&#20182;&#30340;&#24211;&#65292;&#24182;&#20171;&#32461;&#19968;&#19979;&#20854;&#20248;&#32570;&#28857; -->\r\n</div><div class=\"cl-preview-section\"><a id=\"anchor_13\" class=\"virtual_anchor\" target=\"_blank\"></a><h2 id=\"教程设计\">2. 教程设计</h2>\r\n</div><div class=\"cl-preview-section\"><p>本门教程首先会对 ECharts 的简单使用以及各个配置项讲解，再对 ECharts 中视图的构成组件进行一一解读，之后会通过实例和使用对 ECharts 中所有的图表进行讲解，最后我们将在进阶部分对当下流行的库与 ECharts 的整合进行全面介绍。</p>\r\n</div><div class=\"cl-preview-section\"><a id=\"anchor_14\" class=\"virtual_anchor\" target=\"_blank\"></a><h2 id=\"你需要掌握\">3. 你需要掌握</h2>\r\n</div><div class=\"cl-preview-section\"><p>在学习 ECharts 之前你需要掌握一些基础知识以便之后的学习能够顺利进展。</p>\r\n</div><div class=\"cl-preview-section\"><ol>\r\n<li>HTML 的基本知识；</li>\r\n<li>JavaScript 的基本知识。<br>\r\n当然，在客户的进阶部分我们也会对 Vue 与 React 这两大框架 和 ECharts 的整合做一下讲解。所以还需要你根据所需的了解一些 Vue 和 React 的内容。</li>\r\n</ol>\r\n</div><div class=\"cl-preview-section\"><a id=\"anchor_15\" class=\"virtual_anchor\" target=\"_blank\"></a><h2 id=\"小结\">4. 小结</h2>\r\n</div><div class=\"cl-preview-section\"><p>该小节我们主要是了解了 ECharts 的一些基本情况，对 ECharts 能够在我们系统中起到的作用也有了一定的了解，在之后的章节中我们将持续的对 ECharts 的各个环节进一步的探索。</p>\r\n</div></div> <!----> <!----> <ul class=\"select-options\" style=\"display: none; left: 368px; top: 1206.05px; margin-left: -105px;\"><li>划线</li> <li>写笔记</li> <li>复制</li></ul> <div class=\"note-edit\" style=\"left:0;top:0;display:none;\"><span class=\"imv2-close2 close\"></span> <textarea placeholder=\"学习要认真，笔记应当先\"></textarea> <p class=\"footer\"><label><span class=\"icon imv2-checkbox\"></span> <span>公开笔记</span></label> <span class=\"number\">0/1000</span> <button class=\"submit gary\">提交\r\n                        </button></p></div> <div class=\"note-detail\" style=\"left:0;top:0;display:none;\"><div class=\"head\"><img src=\"//img.mukewang.com/545868550001f60202200220-100-100.jpg\" alt=\"\" style=\"cursor: pointer;\"> <span class=\"nick-name\">慕粉174719507</span> <span class=\"imv2-close2 close\"></span></div> <pre class=\"content\"></pre> <div class=\"footer\"><button class=\"del\">删除</button> <button class=\"edit\">编辑</button></div></div></div> <div class=\"bottom\"><!----> <a href=\"/wiki/echarts/echartsinstall.html\" class=\"next clearfix only\"><p class=\"l point\">下一节</p> <i class=\"l imv2-drop_right\"></i> <p class=\"l name\">ECharts 安装</p></a></div> <div class=\"feekback\"><a href=\"https://www.imooc.com/user/feedback?newwiki\" target=\"_blank\"><i class=\"imv2-edit1\"></i><span style=\"color:red;font-size:24px;\">我要提出意见反馈</span></a></div></div>');
INSERT INTO `content` VALUES (2, '<!DOCTYPE html>\r\n<html>\r\n<head>\r\n<meta charset=\"utf-8\">\r\n<title>菜鸟教程(runoob.com)</title>\r\n</head>\r\n\r\n<body>\r\n\r\n<h1>春晓</h1>\r\n\r\n<p>\r\n    春眠不觉晓，\r\n      处处闻啼鸟。\r\n        夜来风雨声，\r\n          花落知多少。\r\n</p>\r\n\r\n<p>注意，浏览器忽略了源代码中的排版（省略了多余的空格和换行）。</p>\r\n\r\n</body>\r\n\r\n</html>');
INSERT INTO `content` VALUES (3, '<!DOCTYPE html>\r\n<html>\r\n<head> \r\n<meta charset=\"utf-8\"> \r\n<title>菜鸟教程(runoob.com)</title> \r\n</head>\r\n<body>\r\n\r\n<h4>编号列表：</h4>\r\n<ol>\r\n <li>Apples</li>\r\n <li>Bananas</li>\r\n <li>Lemons</li>\r\n <li>Oranges</li>\r\n</ol>  \r\n\r\n<h4>大写字母列表：</h4>\r\n<ol type=\"A\">\r\n <li>Apples</li>\r\n <li>Bananas</li>\r\n <li>Lemons</li>\r\n <li>Oranges</li>\r\n</ol>  \r\n\r\n<h4>小写字母列表：</h4>\r\n<ol type=\"a\">\r\n <li>Apples</li>\r\n <li>Bananas</li>\r\n <li>Lemons</li>\r\n <li>Oranges</li>\r\n</ol>  \r\n\r\n<h4>罗马数字列表：</h4>\r\n<ol type=\"I\">\r\n <li>Apples</li>\r\n <li>Bananas</li>\r\n <li>Lemons</li>\r\n <li>Oranges</li>\r\n</ol>  \r\n\r\n<h4>小写罗马数字列表：</h4>\r\n<ol type=\"i\">\r\n <li>Apples</li>\r\n <li>Bananas</li>\r\n <li>Lemons</li>\r\n <li>Oranges</li>\r\n</ol>  \r\n\r\n</body>\r\n</html>');
INSERT INTO `content` VALUES (163940641615777792, '');
INSERT INTO `content` VALUES (163941308321370112, '');
INSERT INTO `content` VALUES (163941449166098432, '');
INSERT INTO `content` VALUES (163941550282379264, '');
INSERT INTO `content` VALUES (163941908832456704, '');
INSERT INTO `content` VALUES (163942019998289920, '');
INSERT INTO `content` VALUES (163952982713569280, '');
INSERT INTO `content` VALUES (163954097836396544, '');
INSERT INTO `content` VALUES (179027242507177984, '<p>a</p>');
INSERT INTO `content` VALUES (179027520912494592, '<p>Spring boot 优势</p>');
INSERT INTO `content` VALUES (179028006885527552, '<p>1</p>');
INSERT INTO `content` VALUES (179028618029174784, '<p>Spring boot 优势</p>');
INSERT INTO `content` VALUES (179029247220912128, '<p>dddd</p>');

-- ----------------------------
-- Table structure for demo
-- ----------------------------
DROP TABLE IF EXISTS `demo`;
CREATE TABLE `demo`  (
  `id` bigint(0) NOT NULL COMMENT 'id',
  `name` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '名称',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci COMMENT = '测试' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of demo
-- ----------------------------
INSERT INTO `demo` VALUES (1, '测试');

-- ----------------------------
-- Table structure for doc
-- ----------------------------
DROP TABLE IF EXISTS `doc`;
CREATE TABLE `doc`  (
  `id` bigint(0) NOT NULL COMMENT 'id',
  `ebook_id` bigint(0) NOT NULL DEFAULT 0 COMMENT '电子书id',
  `parent` bigint(0) NOT NULL DEFAULT 0 COMMENT '父id',
  `name` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '名称',
  `sort` int(0) NULL DEFAULT NULL COMMENT '顺序',
  `view_count` int(0) NULL DEFAULT 0 COMMENT '阅读数',
  `vote_count` int(0) NULL DEFAULT 0 COMMENT '点赞数',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci COMMENT = '文档' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of doc
-- ----------------------------
INSERT INTO `doc` VALUES (1, 1, 0, '1、SpringBoot的前世今生', 1, 23, 3);
INSERT INTO `doc` VALUES (2, 1, 0, '2、使用SpringBoot的优势', 1, 8, 1);
INSERT INTO `doc` VALUES (3, 1, 0, '3、如何快速使用SpringBoot', 1, 2, 1);
INSERT INTO `doc` VALUES (163940641615777792, 1, 1, '1.1、SSH+SSM框架的时代', 1, 15, 2);
INSERT INTO `doc` VALUES (163941308321370112, 1, 1, '1.2、SpringBoot出现之后', 2, 12, 2);
INSERT INTO `doc` VALUES (163941449166098432, 1, 2, '2.1、快速、轻巧', 1, 7, 1);
INSERT INTO `doc` VALUES (163941550282379264, 1, 1, '1.0、无框架的时代', 0, 21, 2);
INSERT INTO `doc` VALUES (163941908832456704, 1, 3, '3.1、使用官网创建', 1, 2, 1);
INSERT INTO `doc` VALUES (163942019998289920, 1, 3, '3.2、使用maven工程改造', 2, 3, 1);
INSERT INTO `doc` VALUES (163952982713569280, 1, 1, '1.3、SpringBoot和SpringCloud的关系', 3, 9, 1);
INSERT INTO `doc` VALUES (163954097836396544, 1, 3, '3.3、创建一个启动类@SpringBootApplication', 3, 2, 1);
INSERT INTO `doc` VALUES (179028618029174784, 1, 2, '2.2 优势', 1, 1, 1);
INSERT INTO `doc` VALUES (179029247220912128, 1, 2, '2.3 dddd', 1, 2, 1);

-- ----------------------------
-- Table structure for ebook
-- ----------------------------
DROP TABLE IF EXISTS `ebook`;
CREATE TABLE `ebook`  (
  `id` bigint(0) NOT NULL COMMENT 'id',
  `name` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '名称',
  `category1_id` bigint(0) NULL DEFAULT NULL COMMENT '分类1',
  `category2_id` bigint(0) NULL DEFAULT NULL COMMENT '分类2',
  `description` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '描述',
  `cover` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '封面',
  `doc_count` int(0) NOT NULL DEFAULT 0 COMMENT '文档数',
  `view_count` int(0) NOT NULL DEFAULT 0 COMMENT '阅读数',
  `vote_count` int(0) NOT NULL DEFAULT 0 COMMENT '点赞数',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci COMMENT = '电子书' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of ebook
-- ----------------------------
INSERT INTO `ebook` VALUES (1, 'Spring Boot 入门教程', 200, 202, '零基础入门 SpringBoot开发，企业级应用开发最佳首选框架', '/image/cover1.png', 13, 107, 18);
INSERT INTO `ebook` VALUES (2, 'Vue 入门教程', 100, 101, '零基础入门 Vue 开发，企业级应用开发最佳首选框架', '/image/cover2.png', 0, 0, 0);
INSERT INTO `ebook` VALUES (3, 'Python 入门教程', 300, 301, '零基础入门 Python 开发，企业级应用开发最佳首选框架', '/image/cover1.png', 0, 0, 0);
INSERT INTO `ebook` VALUES (4, 'Mysql 入门教程', 400, 401, '零基础入门 Mysql 开发，企业级应用开发最佳首选框架', '/image/cover1.png', 0, 0, 0);
INSERT INTO `ebook` VALUES (5, 'Oracle 入门教程', 400, 402, '零基础入门 Oracle 开发，企业级应用开发最佳首选框架', '/image/cover2.png', 0, 0, 0);
INSERT INTO `ebook` VALUES (6, 'Java 入门教程', 200, 201, 'java入门到精通，IT行业必备指南', '/image/cover2.png', 0, 0, 0);
INSERT INTO `ebook` VALUES (7, 'Zookeeper实战', 300, 202, '零基础学会Zookeeper，企业级应用开发最佳框架', '/image/cover1.png', 0, 0, 0);
INSERT INTO `ebook` VALUES (8, 'Dubbo实战', 300, 202, '零基础学会Dubbo，搞定企业分布式', '/image/cover1.png', 0, 0, 0);
INSERT INTO `ebook` VALUES (10, 'RabbitMQ实战', 400, 401, '大型项目实战开发，消息中间件首选', '/image/cover2.png', 0, 0, 0);
INSERT INTO `ebook` VALUES (11, 'SpringCloud入门', 200, 202, '秒杀微服务，企业级应用开发最佳首选框架', '/image/cover1.png', 0, 0, 0);
INSERT INTO `ebook` VALUES (12, 'Ract入门教程', 100, 165065615030226944, 'dsfdsf', 'sdfsd', 0, 0, 0);
INSERT INTO `ebook` VALUES (13, '呃呃呃呃呃呃', 500, 202, '大大大大大大', '她她她她她她她', 0, 0, 0);

-- ----------------------------
-- Table structure for ebook_snapshot
-- ----------------------------
DROP TABLE IF EXISTS `ebook_snapshot`;
CREATE TABLE `ebook_snapshot`  (
  `id` bigint(0) NOT NULL AUTO_INCREMENT COMMENT 'id',
  `ebook_id` bigint(0) NOT NULL DEFAULT 0 COMMENT '电子书id',
  `date` date NOT NULL COMMENT '快照日期',
  `view_count` int(0) NOT NULL DEFAULT 0 COMMENT '阅读数',
  `vote_count` int(0) NOT NULL DEFAULT 0 COMMENT '点赞数',
  `view_increase` int(0) NOT NULL DEFAULT 0 COMMENT '阅读增长',
  `vote_increase` int(0) NOT NULL DEFAULT 0 COMMENT '点赞增长',
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE INDEX `ebook_id_date_unique`(`ebook_id`, `date`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 34 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci COMMENT = '电子书快照表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of ebook_snapshot
-- ----------------------------
INSERT INTO `ebook_snapshot` VALUES (8, 1, '2022-05-09', 107, 80, 107, 80);
INSERT INTO `ebook_snapshot` VALUES (9, 2, '2022-05-08', 12, 40, 40, 40);
INSERT INTO `ebook_snapshot` VALUES (10, 3, '2022-05-07', 34, 28, 28, 28);
INSERT INTO `ebook_snapshot` VALUES (11, 4, '2022-05-06', 56, 22, 22, 22);
INSERT INTO `ebook_snapshot` VALUES (12, 5, '2022-05-05', 78, 19, 19, 19);
INSERT INTO `ebook_snapshot` VALUES (13, 6, '2022-05-04', 90, 17, 17, 17);
INSERT INTO `ebook_snapshot` VALUES (14, 7, '2022-05-03', 11, 14, 14, 14);
INSERT INTO `ebook_snapshot` VALUES (15, 8, '2022-05-02', 12, 11, 11, 11);
INSERT INTO `ebook_snapshot` VALUES (23, 1, '2022-05-10', 107, 18, 0, -62);
INSERT INTO `ebook_snapshot` VALUES (24, 2, '2022-05-10', 0, 0, 0, 0);
INSERT INTO `ebook_snapshot` VALUES (25, 3, '2022-05-10', 0, 0, 0, 0);
INSERT INTO `ebook_snapshot` VALUES (26, 4, '2022-05-10', 0, 0, 0, 0);
INSERT INTO `ebook_snapshot` VALUES (27, 5, '2022-05-10', 0, 0, 0, 0);
INSERT INTO `ebook_snapshot` VALUES (28, 6, '2022-05-10', 0, 0, 0, 0);
INSERT INTO `ebook_snapshot` VALUES (29, 7, '2022-05-10', 0, 0, 0, 0);
INSERT INTO `ebook_snapshot` VALUES (30, 8, '2022-05-10', 0, 0, 0, 0);
INSERT INTO `ebook_snapshot` VALUES (31, 10, '2022-05-10', 0, 0, 0, 0);
INSERT INTO `ebook_snapshot` VALUES (32, 11, '2022-05-10', 0, 0, 0, 0);
INSERT INTO `ebook_snapshot` VALUES (33, 12, '2022-05-10', 0, 0, 0, 0);
INSERT INTO `ebook_snapshot` VALUES (34, 13, '2022-05-10', 0, 0, 0, 0);

-- ----------------------------
-- Table structure for test
-- ----------------------------
DROP TABLE IF EXISTS `test`;
CREATE TABLE `test`  (
  `id` bigint(0) NOT NULL COMMENT 'id',
  `name` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '名称',
  `password` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '密码',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci COMMENT = '测试' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of test
-- ----------------------------
INSERT INTO `test` VALUES (1, '测试', 'password');

-- ----------------------------
-- Table structure for user
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user`  (
  `id` bigint(0) NOT NULL COMMENT 'ID',
  `login_name` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '登陆名',
  `name` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '昵称',
  `password` char(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '密码',
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE INDEX `login_name_unique`(`login_name`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci COMMENT = '用户' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of user
-- ----------------------------
INSERT INTO `user` VALUES (1, 'test', '123456', 'fb09b64e309e1c33e7cf8cfe99b07bd5');
INSERT INTO `user` VALUES (161678327608905728, 'frank', '小飞飞asd', 'admin123');
INSERT INTO `user` VALUES (161682335765499904, 'ddd', 'eeed', 'eeeeedd');
INSERT INTO `user` VALUES (162478200226713600, '111', '2222222', 'f859e9b04f5bbef089318a292539c666');
INSERT INTO `user` VALUES (162478331479068672, '5', '6', 'f859e9b04f5bbef089318a292539c666');
INSERT INTO `user` VALUES (162856244254740480, 'admin', '孤勇者', 'a36280cf624505470a88347c10391769');
INSERT INTO `user` VALUES (178711960974659584, 'a', 'a', '87b83fbb005158850201fa7e2f8fffde');

SET FOREIGN_KEY_CHECKS = 1;
