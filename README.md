# 云山味坊订餐平台

基于 Spring Boot + Vue 的在线订餐平台，支持用户点餐、订单管理、菜品管理等功能。

## 🛠️ 技术栈

### 后端技术
- **框架**: Spring Boot 2.7.3
- **数据库**: MySQL 8.0+
- **缓存**: Redis
- **ORM**: MyBatis
- **API文档**: Knife4j
- **支付**: 微信支付
- **文件存储**: 阿里云 OSS
- **WebSocket**: 订单实时通知

### 前端技术
- **框架**: Vue 3 + TypeScript
- **UI库**: Element Plus
- **构建工具**: Vite
- **状态管理**: Vuex
- **路由**: Vue Router
- **图表**: ECharts

## ✨ 功能特性

### 用户端
- 用户注册与登录（微信登录）
- 菜品浏览与搜索
- 购物车管理
- 订单提交与支付
- 订单状态查看
- 地址管理
- 商家营业状态查看

### 管理端
- 员工管理（增删改查）
- 分类管理（菜品分类）
- 菜品管理（增删改查、上下架）
- 套餐管理（增删改查）
- 订单管理（接单、拒单、取消、完成）
- 数据统计（营业额、订单量、用户数）
- 营业状态管理
- 订单实时通知

## 📁 项目结构

```
xyf_take_out/
├── backend/                    # 后端代码
│   ├── xyf-common/            # 通用模块
│   │   └── src/main/java/com/xyf/
│   │       ├── constant/      # 常量定义
│   │       ├── context/       # 上下文工具
│   │       ├── enumeration/   # 枚举类
│   │       ├── exception/     # 自定义异常
│   │       ├── json/          # JSON配置
│   │       ├── properties/    # 配置属性类
│   │       ├── result/        # 返回结果封装
│   │       └── utils/         # 工具类
│   ├── xyf-pojo/              # 数据模型
│   │   └── src/main/java/com/xyf/
│   │       ├── dto/           # 数据传输对象
│   │       ├── entity/        # 实体类
│   │       └── vo/            # 视图对象
│   ├── xyf-server/            # 业务逻辑
│   │   └── src/main/java/com/xyf/
│   │       ├── config/        # 配置类
│   │       ├── controller/    # 控制器
│   │       │   ├── admin/     # 管理端接口
│   │       │   └── user/      # 用户端接口
│   │       ├── service/       # 服务层
│   │       ├── mapper/        # 数据访问层
│   │       ├── interceptor/   # 拦截器
│   │       ├── aspect/        # AOP切面
│   │       ├── handler/       # 异常处理
│   │       ├── websocket/     # WebSocket
│   │       ├── task/          # 定时任务
│   │       └── XyfApplication.java
│   ├── pom.xml                # Maven依赖管理
│   └── mvnw                   # Maven Wrapper
├── frontend/                  # 前端代码
│   ├── src/
│   │   ├── api/               # API接口
│   │   ├── assets/            # 静态资源
│   │   ├── components/        # 公共组件
│   │   ├── layout/            # 布局组件
│   │   ├── router/            # 路由配置
│   │   ├── store/             # 状态管理
│   │   ├── styles/            # 样式文件
│   │   ├── utils/             # 工具函数
│   │   ├── views/             # 页面视图
│   │   ├── App.vue
│   │   ├── main.ts
│   │   └── permission.ts
│   ├── public/                # 静态资源
│   ├── package.json           # 依赖配置
│   ├── vue.config.js          # Vue配置
│   └── vite.config.ts         # Vite配置
└── README.md                  # 项目说明
```

## 🚀 快速开始

### 环境要求
- JDK 21+
- Maven 3.8+
- MySQL 8.0+
- Redis 6.0+
- Node.js 16+

### 数据库配置
1. 创建数据库 `xyf_take_out`
2. 导入数据库初始化脚本（待补充）

### 后端配置
修改 `backend/xyf-server/src/main/resources/application-dev.yml`：

```yaml
xyf:
  datasource:
    host: localhost
    port: 3306
    database: xyf_take_out
    username: root
    password: your_password
  redis:
    host: localhost
    port: 6379
  alioss:
    endpoint: your_endpoint
    access-key-id: your_access_key_id
    access-key-secret: your_access_key_secret
    bucket-name: your_bucket_name
  wechat:
    appid: your_appid
    secret: your_secret
```

### 运行后端

```bash
cd backend
mvn spring-boot:run
```

### 运行前端

```bash
cd frontend
npm install
npm run dev
```

### 访问地址
- API文档: http://localhost:8080/doc.html
- 后端服务: http://localhost:8080
- 前端页面: http://localhost:8081

## 🔌 API接口

### 管理端接口
| 模块 | 接口路径 | 功能 |
|------|----------|------|
| 员工管理 | /admin/employee | 员工登录、增删改查 |
| 分类管理 | /admin/category | 分类增删改查 |
| 菜品管理 | /admin/dish | 菜品增删改查、上下架 |
| 套餐管理 | /admin/setmeal | 套餐增删改查 |
| 订单管理 | /admin/order | 订单列表、接单、拒单 |
| 数据统计 | /admin/report | 营业额、订单统计 |
| 营业管理 | /admin/shop | 营业状态管理 |

### 用户端接口
| 模块 | 接口路径 | 功能 |
|------|----------|------|
| 用户管理 | /user/user | 用户登录、注册 |
| 菜品浏览 | /user/dish | 菜品列表、搜索 |
| 购物车 | /user/shoppingCart | 添加、删除、清空 |
| 订单管理 | /user/order | 下单、支付、状态查询 |
| 地址管理 | /user/addressBook | 地址增删改查 |
| 套餐管理 | /user/setmeal | 套餐列表 |

## 📝 开发说明

### 代码规范
- 命名规范：类名采用 PascalCase，方法名和变量名采用 camelCase
- 异常处理：统一使用 GlobalExceptionHandler
- 返回结果：统一使用 Result<T> 封装
- 日志记录：使用 @Slf4j 注解

### 提交规范
- feat: 新增功能
- fix: 修复bug
- docs: 更新文档
- style: 代码格式调整
- refactor: 代码重构

## 📄 许可证

本项目仅供学习使用。

## 🤝 贡献

欢迎提交 Issue 和 Pull Request！