授权服务（用户名+密码）：
请求授权码：
/oauth/token  body中添加如下参数
scope:all
client_id:client  固定
client_secret:123456 固定
username:admin 数据库中
pasword:Sys123 数据库中
grant_type:password  密码模式
刷新授权码：
/oauth/token  body中添加如下参数
client_id:client  固定
client_secret:123456 固定
refresh_token:xxxxx  
grant_type:refresh_token  刷新token
