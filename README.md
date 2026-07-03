# 云山味坊订餐平台 🍜

基于 Spring Boot + Vue 的在线订餐平台，为餐饮商家提供完整的外卖点餐解决方案。系统包含用户端和管理端，支持菜品浏览、购物车、在线支付、订单管理等核心功能。

## � 项目简介

云山味坊是一款面向中小型餐饮商家的订餐管理系统，旨在帮助商家快速搭建线上点餐平台，提升运营效率和用户体验。

**主要特点：**
- 🎯 前后端分离架构，便于维护和扩展
- ⚡ 实时订单通知，支持WebSocket推送
- 🔐 JWT身份认证，保障数据安全
- 📊 数据统计分析，助力经营决策
- 📱 响应式设计，支持多端访问

## �🛠️ 技术栈

### 后端技术
| 技术 | 版本 | 说明 |
|------|------|------|
| Spring Boot | 2.7.3 | 后端框架 |
| MySQL | 8.0+ | 关系型数据库 |
| Redis | 6.0+ | 缓存中间件 |
| MyBatis | 3.5.x | ORM框架 |
| Knife4j | 4.0.x | API文档生成 |
| Spring WebSocket | - | 实时消息推送 |
| 阿里云OSS | - | 文件存储服务 |
| 微信支付 | - | 在线支付功能 |

### 前端技术
| 技术 | 版本 | 说明 |
|------|------|------|
| Vue | 3.x | 前端框架 |
| TypeScript | 4.x | 类型安全 |
| Element Plus | 2.x | UI组件库 |
| Vite | 4.x | 构建工具 |
| Vuex | 4.x | 状态管理 |
| Vue Router | 4.x | 路由管理 |
| ECharts | 5.x | 图表可视化 |

## ✨ 功能特性

### 用户端功能
| 模块 | 功能描述 |
|------|----------|
| 用户认证 | 微信登录、手机号登录、注册 |
| 菜品浏览 | 分类展示、关键词搜索、菜品详情 |
| 购物车 | 添加菜品、修改数量、清空购物车 |
| 订单管理 | 提交订单、在线支付、订单状态追踪 |
| 地址管理 | 添加地址、设为默认、删除地址 |
| 营业状态 | 查看商家是否营业 |

### 管理端功能
| 模块 | 功能描述 |
|------|----------|
| 员工管理 | 新增员工、编辑信息、账号启用/禁用 |
| 分类管理 | 菜品分类增删改查、排序 |
| 菜品管理 | 菜品增删改查、上下架、口味管理 |
| 套餐管理 | 套餐增删改查、套餐菜品配置 |
| 订单管理 | 订单列表、接单、拒单、取消、完成 |
| 数据统计 | 营业额统计、订单统计、用户统计、热销菜品 |
| 营业管理 | 店铺营业状态切换 |
| 实时通知 | 新订单WebSocket推送提醒 |

## 📁 项目结构

```
xyf_take_out/
├── backend/                    # 后端代码
│   ├── xyf-common/            # 通用模块（公共依赖）
│   │   └── src/main/java/com/xyf/
│   │       ├── constant/      # 常量定义（如JwtClaimsConstant）
│   │       ├── context/       # 线程上下文（BaseContext）
│   │       ├── enumeration/   # 枚举类（OperationType等）
│   │       ├── exception/     # 自定义异常（登录失败、业务异常等）
│   │       ├── json/          # JSON序列化配置（JacksonObjectMapper）
│   │       ├── properties/    # 配置属性类（AliOssProperties等）
│   │       ├── result/        # 返回结果封装（Result、PageResult）
│   │       └── utils/         # 工具类（JwtUtil、AliOssUtil等）
│   ├── xyf-pojo/              # 数据模型（实体、DTO、VO）
│   │   └── src/main/java/com/xyf/
│   │       ├── dto/           # 数据传输对象（请求参数）
│   │       ├── entity/        # 数据库实体类
│   │       └── vo/            # 视图对象（响应数据）
│   ├── xyf-server/            # 业务逻辑（核心模块）
│   │   └── src/main/java/com/xyf/
│   │       ├── config/        # 配置类（Oss、Redis、WebMvc等）
│   │       ├── controller/    # REST控制器
│   │       │   ├── admin/     # 管理端接口
│   │       │   └── user/      # 用户端接口
│   │       ├── service/       # 服务接口及实现类
│   │       ├── mapper/        # MyBatis Mapper接口
│   │       ├── interceptor/   # 拦截器（JWT验证）
│   │       ├── aspect/        # AOP切面（自动填充）
│   │       ├── handler/       # 全局异常处理
│   │       ├── websocket/     # WebSocket服务器
│   │       ├── task/          # 定时任务（订单超时处理）
│   │       └── XyfApplication.java  # Spring Boot启动类
│   ├── pom.xml                # Maven父工程依赖管理
│   ├── mvnw                   # Maven Wrapper（Linux/Mac）
│   └── mvnw.cmd               # Maven Wrapper（Windows）
├── frontend/                  # 前端代码
│   ├── src/
│   │   ├── api/               # API接口封装
│   │   ├── assets/            # 静态资源（图片、图标等）
│   │   ├── components/        # 公共组件
│   │   ├── layout/            # 页面布局组件
│   │   ├── router/            # 路由配置
│   │   ├── store/             # Vuex状态管理
│   │   ├── styles/            # 全局样式
│   │   ├── utils/             # 工具函数（请求封装等）
│   │   ├── views/             # 页面视图组件
│   │   ├── App.vue            # 根组件
│   │   ├── main.ts            # 入口文件
│   │   └── permission.ts      # 路由权限控制
│   ├── public/                # 静态资源目录
│   ├── package.json           # npm依赖配置
│   ├── vue.config.js          # Vue配置
│   └── vite.config.ts         # Vite配置
├── sql/                       # 数据库脚本
│   └── xyf_take_out.sql       # 数据库建表语句
├── README.md                  # 项目说明文档
└── .gitignore                 # Git忽略规则
```

## 🚀 快速开始

### 环境要求

| 环境 | 版本 | 备注 |
|------|------|------|
| JDK | 21+ | 后端运行环境 |
| Maven | 3.8+ | 项目构建工具 |
| MySQL | 8.0+ | 数据库 |
| Redis | 6.0+ | 缓存 |
| Node.js | 16+ | 前端运行环境 |

### 1. 克隆项目

```bash
git clone git@github.com:xyfxhwys666/xyf_take_out.git
cd xyf_take_out
```

### 2. 数据库配置

**创建数据库：**
```sql
CREATE DATABASE xyf_take_out CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci;
```

**导入SQL文件：**
```bash
mysql -u root -p xyf_take_out < sql/xyf_take_out.sql
```

### 3. 后端配置

修改 `backend/xyf-server/src/main/resources/application-dev.yml`：

```yaml
server:
  port: 8080

spring:
  datasource:
    url: jdbc:mysql://localhost:3306/xyf_take_out?useUnicode=true&characterEncoding=utf-8&serverTimezone=Asia/Shanghai
    username: root
    password: your_password
    driver-class-name: com.mysql.cj.jdbc.Driver

  redis:
    host: localhost
    port: 6379
    password: 

xyf:
  alioss:
    endpoint: your_endpoint
    access-key-id: your_access_key_id
    access-key-secret: your_access_key_secret
    bucket-name: your_bucket_name
  wechat:
    appid: your_appid
    secret: your_secret
```

**配置说明：**
- `spring.datasource`：数据库连接信息
- `spring.redis`：Redis连接信息
- `xyf.alioss`：阿里云OSS配置（用于菜品图片存储）
- `xyf.wechat`：微信支付配置（用于在线支付）

### 4. 运行后端

```bash
cd backend

# 使用Maven运行
mvn spring-boot:run

# 或者打包后运行
mvn clean package
java -jar xyf-server/target/xyf-server-1.0-SNAPSHOT.jar
```

**启动成功后：**
- 后端服务：http://localhost:8080
- API文档：http://localhost:8080/doc.html

### 5. 运行前端

```bash
cd frontend

# 安装依赖
npm install

# 开发模式运行
npm run dev

# 构建生产版本
npm run build
```

**启动成功后：**
- 前端页面：http://localhost:8081

## 🔌 API接口

### 管理端接口

| 模块 | 接口路径 | HTTP方法 | 功能描述 |
|------|----------|----------|----------|
| 员工登录 | /admin/employee/login | POST | 员工登录获取token |
| 员工列表 | /admin/employee/page | GET | 分页查询员工列表 |
| 新增员工 | /admin/employee | POST | 添加新员工 |
| 编辑员工 | /admin/employee | PUT | 修改员工信息 |
| 删除员工 | /admin/employee/{id} | DELETE | 删除员工 |
| 分类列表 | /admin/category/list | GET | 查询分类列表 |
| 新增分类 | /admin/category | POST | 添加分类 |
| 修改分类 | /admin/category | PUT | 修改分类 |
| 删除分类 | /admin/category/{id} | DELETE | 删除分类 |
| 菜品列表 | /admin/dish/page | GET | 分页查询菜品 |
| 新增菜品 | /admin/dish | POST | 添加菜品 |
| 修改菜品 | /admin/dish | PUT | 修改菜品 |
| 删除菜品 | /admin/dish/{id} | DELETE | 删除菜品 |
| 菜品起售/停售 | /admin/dish/status/{status} | POST | 修改菜品状态 |
| 套餐列表 | /admin/setmeal/page | GET | 分页查询套餐 |
| 新增套餐 | /admin/setmeal | POST | 添加套餐 |
| 修改套餐 | /admin/setmeal | PUT | 修改套餐 |
| 删除套餐 | /admin/setmeal/{id} | DELETE | 删除套餐 |
| 订单列表 | /admin/order/page | GET | 分页查询订单 |
| 接单 | /admin/order/confirm | POST | 确认订单 |
| 拒单 | /admin/order/rejection | POST | 拒绝订单 |
| 取消订单 | /admin/order/cancel | POST | 取消订单 |
| 完成订单 | /admin/order/complete | POST | 完成订单 |
| 营业额统计 | /admin/report/turnoverStatistics | GET | 营业额数据统计 |
| 用户统计 | /admin/report/userStatistics | GET | 用户数据统计 |
| 订单统计 | /admin/report/ordersStatistics | GET | 订单数据统计 |
| 热销菜品 | /admin/report/salesTop10 | GET | 热销菜品TOP10 |
| 营业状态 | /admin/shop/status | GET | 获取营业状态 |
| 设置营业状态 | /admin/shop/status | PUT | 修改营业状态 |

### 用户端接口

| 模块 | 接口路径 | HTTP方法 | 功能描述 |
|------|----------|----------|----------|
| 用户登录 | /user/user/login | POST | 用户登录（微信） |
| 用户注册 | /user/user/register | POST | 用户注册 |
| 分类列表 | /user/category/list | GET | 查询分类及菜品 |
| 菜品列表 | /user/dish/list | GET | 查询菜品列表 |
| 菜品搜索 | /user/dish/search | GET | 搜索菜品 |
| 购物车列表 | /user/shoppingCart/list | GET | 查询购物车 |
| 添加购物车 | /user/shoppingCart | POST | 添加菜品到购物车 |
| 修改购物车 | /user/shoppingCart | PUT | 修改购物车数量 |
| 删除购物车 | /user/shoppingCart/{id} | DELETE | 删除购物车项 |
| 清空购物车 | /user/shoppingCart/clean | DELETE | 清空购物车 |
| 提交订单 | /user/order/submit | POST | 提交订单 |
| 订单列表 | /user/order/page | GET | 查询订单列表 |
| 订单详情 | /user/order/{id} | GET | 查询订单详情 |
| 订单支付 | /user/order/payment | POST | 订单支付 |
| 取消订单 | /user/order/cancel | PUT | 取消订单 |
| 再来一单 | /user/order/repetition | POST | 重复下单 |
| 地址列表 | /user/addressBook/list | GET | 查询地址列表 |
| 添加地址 | /user/addressBook | POST | 添加地址 |
| 修改地址 | /user/addressBook | PUT | 修改地址 |
| 删除地址 | /user/addressBook/{id} | DELETE | 删除地址 |
| 默认地址 | /user/addressBook/default | GET | 获取默认地址 |
| 设置默认地址 | /user/addressBook/default | PUT | 设置默认地址 |
| 套餐列表 | /user/setmeal/list | GET | 查询套餐列表 |
| 套餐详情 | /user/setmeal/{id} | GET | 查询套餐详情 |
| 营业状态 | /user/shop/status | GET | 获取营业状态 |

## 📊 数据库表结构

### 核心表说明

| 表名 | 说明 | 主要字段 |
|------|------|----------|
| employee | 员工表 | id, name, username, password, phone, status |
| user | 用户表 | id, openid, name, phone, avatar |
| category | 分类表 | id, name, type, sort, status |
| dish | 菜品表 | id, name, category_id, price, image, description, status |
| dish_flavor | 菜品口味表 | id, dish_id, name, value |
| setmeal | 套餐表 | id, name, category_id, price, image, description, status |
| setmeal_dish | 套餐菜品关系表 | id, setmeal_id, dish_id, name, price, copies |
| address_book | 地址簿表 | id, user_id, consignee, phone, province_code, city_code, district_code, detail, is_default |
| shopping_cart | 购物车表 | id, user_id, dish_id, setmeal_id, dish_flavor, amount, create_time |
| orders | 订单表 | id, number, status, user_id, address_book_id, total_amount, pay_status, checkout_time, pay_time |
| order_detail | 订单明细表 | id, order_id, dish_id, setmeal_id, name, price, amount |

## 🧪 测试数据

### 默认账号

| 角色 | 用户名 | 密码 | 说明 |
|------|--------|------|------|
| 管理员 | admin | 123456 | 管理端登录 |

### 测试用户
- 用户端使用微信登录，测试环境可使用测试openid

## � 开发说明

### 代码规范

**命名规范：**
- 类名：PascalCase（如 `EmployeeController`）
- 方法名：camelCase（如 `listPage`）
- 变量名：camelCase（如 `pageNum`）
- 常量名：UPPER_SNAKE_CASE（如 `JWT_SECRET`）

**异常处理：**
- 自定义异常继承 `BaseException`
- 使用 `GlobalExceptionHandler` 统一处理异常
- 返回结果使用 `Result<T>` 封装

**日志记录：**
- 使用 Lombok 的 `@Slf4j` 注解
- 关键业务逻辑添加日志记录

### 提交规范

```
feat: 新增功能（如新增菜品管理功能）
fix: 修复bug（如修复订单状态更新错误）
docs: 更新文档（如更新README）
style: 代码格式调整（如调整缩进）
refactor: 代码重构（如重构service层）
test: 添加测试（如新增单元测试）
chore: 构建/工具更新（如更新依赖版本）
```

### 开发流程

1. 创建功能分支（如 `feature/dish-management`）
2. 开发功能代码
3. 编写单元测试
4. 提交代码并创建Pull Request
5. 代码审查通过后合并到main分支

## ❓ 常见问题

**Q: 后端启动失败？**
- 检查数据库连接配置是否正确
- 确认MySQL和Redis服务已启动
- 检查端口8080是否被占用

**Q: 前端页面无法访问？**
- 检查Node.js版本是否符合要求
- 确认依赖已正确安装（`npm install`）
- 检查端口8081是否被占用

**Q: 图片上传失败？**
- 检查阿里云OSS配置是否正确
- 确认OSS Bucket权限设置正确
- 检查网络连接是否正常

**Q: 订单状态没有实时更新？**
- 检查WebSocket连接是否正常
- 确认后端WebSocket服务已启动

## 📄 许可证

本项目仅供学习使用。

## 🤝 贡献

欢迎提交 Issue 和 Pull Request！

## 📧 联系方式

如有问题或建议，欢迎联系：xyfxhwys666@github.com