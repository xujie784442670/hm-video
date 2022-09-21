# 项目模块

| 序号 | 模块                        | 描述         | 作用                                           | 优先级 | 响应码范围  |
| ---- | --------------------------- | ------------ | ---------------------------------------------- | ------ | ----------- |
| 1    | video-commons               | 公共模块     | 存放所有模块都需要用的类,且不依赖任何第三方jar | 1      | 1-9999      |
| 2    | video-gateway               | 网关模块     | 整合后端所有服务的API,对前端提供统一接口调用   | 6      | 1-9999      |
| 3    | video-admin                 | 监控模块     | 对所有的服务器提供监控能力                     | 7      | 1-9999      |
| 4    | account-server              | 账号模块     | 提供账号,角色,权限的功能接口                   | 2      | 10000-10099 |
| 5    | video-interactions-server   | 视频交互模块 | 提供收藏,弹幕,评论,点赞,举报的功能接口         | 4      | 10100-10199 |
| 6    | statistics-server           | 统计模块     | 提供各项数据的统计功能接口                     | 5      | 10200-10299 |
| 7    | email-server                | 邮件服务     | 提供邮件相关的功能接口                         | 2      | 10300-10399 |
| 8    | account-interactions-server | 账号交互模块 | 提供关注的功能接口                             | 4      | 10400-10499 |
| 9    | im-server                   | 即时通讯模块 | 提供即时通讯接口                               | 5      | 10500-10599 |
| 10   | play-record-server          | 播放记录模块 | 提供播放记录功能接口                           | 4      | 10600-10699 |
| 11   | upload-server               | 文件上传模块 | 提供文件上传模块                               | 3      | 10700-10799 |
| 12   | video-server                | 视频模块     | 提供视频,视频类型查询服务                      | 3      | 10800-10899 |

# 账号模块

>  请求地址: /login

## 参数

### Params

| 参数名 | 默认值 | 类型   | 是否必传 | 描述                                |
| ------ | ------ | ------ | -------- | ----------------------------------- |
| type   |        | number | 是       | 登录类型: 0-账号密码登录,1-邮箱登录 |

### Body

| 参数名   | 默认值 | 类型   | 是否必传 | 描述   | 验证规则 |
| -------- | ------ | ------ | -------- | ------ | -------- |
| username |        | string | 是       | 用户名 |          |
| password |        | string | 是       | 密码   |          |

```json
{
    "username":"admin",
    "password":"123"
}
```

## 响应

```json
{
    "code":0,  // 响应码
    "message":"成功", // 描述
    // 返回的数据
    "data":{
        // 用户信息
        "user":{
            "username":"admin", // 用户名
            "email":"xxxx@xx.xx" // 邮箱
        },
        // 用户拥有的角色代码列表
        "roles":["account","video"],
        // 用户拥有的权限代码列表
        "permissions":["account:find"],
        // 用户登录的令牌
        "token":"xxxxxxxx"
    }
}
```

### 响应码描述

| 响应码   | 描述     |
|-------|--------|
| 0     | 表示成功   |
| 10001 | 账号密码错误 |
| 10002 | 验证码错误  |

# 邮件模块

## 发送邮件

> 请求地址:/email/addemail

## 参数

### Params

| 参数名 | 默认值 | 类型 | 是否必传 | 描述 |
| ------ | ------ | ---- | -------- | ---- |
|        |        |      |          |      |

### Body

| 参数名 | 默认值 | 类型   | 是否必传 | 描述   | 验证规则 |
| ------ | ------ | ------ | -------- | ------ | -------- |
| email  |        | String | 是       | 邮箱名 |          |

```
{
	"email":"xxx@xx.com"
}
```



### 响应

```json
{
	"code":0,//响应码
	"message":"查询成功",//描述
	//返回的数据
	"data":{
        "verificationCode":"xxxxxx"
	}
}
```

响应码

| 响应码 | 描述               |
| ------ | ------------------ |
| 10300  | 发送邮箱验证码成功 |
| 10301  | 发送邮箱验证码失败 |