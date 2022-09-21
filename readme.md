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

## 登录功能

>  请求地址: /login

### 参数

#### Params

| 参数名 | 默认值 | 类型   | 是否必传 | 描述                                |
| ------ | ------ | ------ | -------- | ----------------------------------- |
| type   |        | number | 是       | 登录类型: 0-账号密码登录,1-邮箱登录 |

#### Body

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

### 响应

```json
{
    "code":10000,  // 响应码
    "message":"成功", // 描述
    // 返回的数据
    "data":{
        // 用户信息
        "user":{
            "username":"admin", // 用户名
            "email":"xxxx@xx.xx", // 邮箱
            "avatar":"xxxxxxx" ,//头像
            "nickName":"小王",//用户昵称
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

## 注册功能
>  请求地址: /register

### 参数

#### Body

| 参数名     | 默认值 | 类型   | 是否必传 | 描述   | 验证规则 |
| ---------- | ------ | ------ | -------- | ------ | -------- |
| account   |        | Account | 是       | 用户对象 |          |

```json
{
    "username":"admin",
    "password":"123",
    "nickname":"小王",
    "verifyCode":"4396",
    "email":"xxxx@xx.xx",
}
```
### 响应

```json
{
    "code":10010,  // 响应码
    "message":"成功", // 描述
    
}
```

### 响应码描述

| 响应码 | 描述               |
| ------ | ------------------ |
| 10010  | 表示注册成功       |
| 10011  | 验证码错误         |
| 10012  | 用户名或邮箱已存在 |
## 封禁账号功能

>  请求地址: /logicDeleteAccount

### 参数
#### Body

| 参数名   | 默认值 | 类型   | 是否必传 | 描述   | 验证规则 |
| -------- | ------ | ------ | -------- | ------ | -------- |
| id |        | Long | 是       | 用户id |          |

```json
{
    "id":"1"
}
```

### 响应

```json
{
    "code":10020,  // 响应码
    "message":"封禁成功", // 描述
    // 返回的数据
    }
}
```

### 响应码描述

| 响应码 | 描述         |
| ------ | ------------ |
| 10020  | 表示封禁成功 |
| 10021  | 表示封禁失败 |
## 查询用户功能

>  请求地址: /findDeatil

### 参数
#### Body

| 参数名   | 默认值 | 类型   | 是否必传 | 描述   | 验证规则 |
| -------- | ------ | ------ | -------- | ------ | -------- |
| id |        | Long | 是       | 用户id |          |

```json
{
    "id":"1"
}
```

### 响应

```json
{
    "code":10030,  // 响应码
    "message":"查询成功", // 描述
    // 返回的数据
    "data":{
        // 用户信息
        "user":{
        	"id":"1",//用户id
            "username":"admin", // 用户名
            "nickName":"小王",//用户昵称
            "avatar":"",//用户头像地址
            "email":"xxxx@xx.xx" // 邮箱
        }
    }
    }
}
```

### 响应码描述

| 响应码 | 描述         |
| ------ | ------------ |
| 10030  | 表示查询成功 |
| 10031  | 表示查询失败 |
## 查询用户总点赞，总关注，总粉丝功能

>  请求地址: /findDeatil

### 参数
#### Body

| 参数名   | 默认值 | 类型   | 是否必传 | 描述   | 验证规则 |
| -------- | ------ | ------ | -------- | ------ | -------- |
| id |        | Long | 是       | 用户id |          |

```json
{
    "id":"1"
}
```

### 响应

```json
{
    "code":10040,  // 响应码
    "message":"查询成功", // 描述
    // 返回的数据
    "data":{
        "numberoflikes":"200",//总点赞数
        "focus":"15",//总关注数
        "fans:"12",//总粉丝数
    }
    }
}
```

### 响应码描述

| 响应码 | 描述         |
| ------ | ------------ |
| 10040  | 表示查询成功 |
| 10041  | 表示查询失败 |
## 查询第二个用户是不是第一个用户粉丝功能

>  请求地址: /findstatus

### 参数
#### Body

| 参数名   | 默认值 | 类型   | 是否必传 | 描述   | 验证规则 |
| -------- | ------ | ------ | -------- | ------ | -------- |
| userId |        | Long | 是       | 用户id |          |
| user2Id |        | Long | 是       | 用户2id |          |

```json
{
    "userId":"1",
    "user2Id":"2"
}
```

### 响应

```json
{
    "code":10050,  // 响应码
    "message":"查询成功", // 描述
    // 返回的数据
    "data":"1" // 1：第二个用户是第一个用户粉丝 2：第二个用户不是第一个用户粉丝
    }
}
```
### 响应码描述

| 响应码 | 描述         |
| ------ | ------------ |
| 10050  | 表示查询成功 |
| 10051  | 表示查询失败 |
## 修改密码功能

>  请求地址: /updatePassword

### 参数
#### Body

| 参数名   | 默认值 | 类型   | 是否必传 | 描述   | 验证规则 |
| -------- | ------ | ------ | -------- | ------ | -------- |
| account |        | Account | 是       | 用户对象 |          |
```json
{
    "username":"admin",
    "password":"123",
    "nickname":"小王",
    "verifyCode":"4396",
    "email":"xxxx@xx.xx",
}
```
### 响应

```json
{
    "code":10060,  // 响应码
    "message":"密码修改成功", // 描述
    // 返回的数据
    
}
```
### 响应码描述

| 响应码 | 描述         |
| ------ | ------------ |
| 10060  | 密码修改成功 |
| 10061  | 密码修改失败 |
## 修改昵称功能

>  请求地址: /updateNickname

### 参数
#### Body

| 参数名   | 默认值 | 类型   | 是否必传 | 描述   | 验证规则 |
| -------- | ------ | ------ | -------- | ------ | -------- |
| account |        | Account | 是       | 用户对象 |          |

```json
{
    "username":"admin",
    "password":"123",
    "nickname":"小王",
    "verifyCode":"4396",
    "email":"xxxx@xx.xx",
}
```
### 响应

```json
{
    "code":10070,  // 响应码
    "message":"昵称修改成功", // 描述
    // 返回的数据
    
}
```
### 响应码描述

| 响应码 | 描述         |
| ------ | ------------ |
| 10070  | 密码昵称成功 |
| 10071  | 密码昵称失败 |
## 修改邮箱功能

>  请求地址: /updateEmail

### 参数
#### Body

| 参数名   | 默认值 | 类型   | 是否必传 | 描述   | 验证规则 |
| -------- | ------ | ------ | -------- | ------ | -------- |
| account |        | Account | 是       | 用户对象 |          |

```json
{
    "username":"admin",
    "password":"123",
    "nickname":"小王",
    "verifyCode":"4396",
    "email":"xxxx@xx.xx",
}
```

### 响应

```json
{
    "code":10080,  // 响应码
    "message":"邮箱修改成功", // 描述
    // 返回的数据
    
}
```
### 响应码描述

| 响应码 | 描述     |
| ------ |--------|
| 10080  | 邮箱修改成功 |
| 10081  | 邮箱修改失败 |
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
        "verificationCode":"xxxxxx"//邮箱验证码
	}
}
```

响应码

| 响应码 | 描述               |
| ------ | ------------------ |
| 10300  | 发送邮箱验证码成功 |
| 10301  | 发送邮箱验证码失败 |




# 视频模块

## 查看所有视频类型

> 请求地址:/VideoTypes/findVideoTypes

## 参数

### Params

| 参数名 | 默认值 | 类型 | 是否必传 | 描述 |
| ------ | ------ | ---- | -------- | ---- |
|        |        |      |          |      |

### Body

| 参数名 | 默认值 | 类型 | 是否必传 | 描述 | 验证规则 |
| ------ | ------ | ---- | -------- | ---- | -------- |
|        |        |      |          |      |          |

### 响应

```json
{
	"code":0,//响应码
	"message":"查询成功",//描述
	//返回的数据
	"data":{
	//视频类型id和视频类型描述数组
	["id":1,"type":"娱乐"],
	["id":2,"type":"神秘"],
	["id":3,"type":"悬疑"]
	}
}
```

响应码

| 响应码 | 描述     |
| ------ | -------- |
| 10800  | 查询成功 |
| 10801  | 查询失败 |

## 查询所有视频

> 请求地址:/video/list

## 参数

### Params

| 参数名 | 默认值 | 类型 | 是否必传 | 描述 |
| ------ | ------ | ---- | -------- | ---- |
|        |        |      |          |      |

### Body

| 参数名   | 默认值 | 类型       | 是否必传 | 描述           | 验证规则 |
| -------- | ------ | ---------- | -------- | -------------- | -------- |
| query    |        | VideoQuery | 是       | 视频表查询对象 |          |
| page     | 1      | int        | 是       | 当前页数       |          |
| pageSize | 10     | int        | 是       | 一页多少条数据 |          |

```
{
	//视频表查询对象
	query{
		"tetle":"奥特曼之我的兄弟叫顺溜",//视频标题
		"type":"喜剧",//视频类型
		//发布时间
		"createTime":{
				["createTime":"2022-12-05 23:59:59"],//在这个时间之后发布的视频
				["createTime":"2022-12-05 23:59:59"]//在这个时间之前发布的视频
					  },
		"views":4566852,//视频播放量
		"likes":852456,//视频点赞量
	}
	page:1,//当前页
	pageSize:10//一页多少条数据
}
```



### 响应

```json
{
	"code":0,//响应码
	"message":"查询成功",//响应码描述
    //返回的结果数组
	"data":{
        //视频表对象
        "Video":["id":1,//视频id
                 "title":"奥特曼之我的兄弟叫顺溜",//视频标题
                 "typeId":0,//视频类型id
                 "type":"喜剧",//视频类型
                 "createTime":"2002-12-05 12:59:59",//视频创建时间
                 "videoUrl":"http:/dsa/dsa.com",//视频地址
                 "coverUrl":"http:/dsa/dsadsda",//视频封面地址
                 "accountId":1,//视频发布者id
                 "nickName":"老刘",//视频发布者昵称
                 //视频发布者信息
                 "account":{
                 	"id":0,//用户id
                 	"username":"老老刘",//用户名;用户名
                 	"nickName":"老刘",//昵称
                 	"avatar":"xxxxx",//头像;头像
                 	"password":"dasdsa"，//密码;密码
                 	"email":"xxx@xx.com",//邮箱;邮箱
                    "createdTime":"2002-12-06 12:59:59",//创建时间;创建时间
                 	"updatedTime":"2002-12-06 12:59:59"，//更新时间;最后更新时间
                 	"status":0,//状态;状态,1正常0禁用
                 	"logicDelete":0,//逻辑删除;0正常，1封禁
                 	"fans":456,//粉丝数
                 	"focus":4685//关注数
                 },
    			 "barrages":1235,
    			 "collections":852,
    			 "status":0,
    			 "comments":852,
    			 "likes":85,
    			 "logicalDelete":0,
    			 "views":852
                ],								
    			["id":1,
                 "title":"奥特曼之我的兄弟叫顺溜",
                 "typeId":0,"type":"喜剧",
                 "createTime":"2002-12-05 12:59:59",
                 "videoUrl":"http:/dsa/dsa.com",
                 "coverUrl":"http:/dsa/dsadsda",
                 "accountId":1,
                 "nickName":"老刘",
                 "account":{
                 	"id":0,
                 	"username":"老老刘",
                 	"nickName":"老刘",
                 	"avatar":"xxxxx",
                 	"password":"dasdsa"，
                 	"email":"xxx@xx.com",
                    "createdTime":"2002-12-06 12:59:59",
                 	"updatedTime":"2002-12-06 12:59:59"，
                 	"status":0,
                 	"logicDelete":0,
                 	"fans":456,
                 	"focus":4685
                 },
    			 "barrages":1235,
    			 "collections":852,
    			 "status":0,
    			 "comments":852,
    			 "likes":85,
    			 "logicalDelete":0,
    			 "views":852
                ]
    }
}
```

响应码

| 响应码 | 描述     |
| ------ | -------- |
| 10802  | 查询成功 |
| 10803  | 查询失败 |
