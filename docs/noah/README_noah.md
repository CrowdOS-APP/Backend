# 注意事项：Noah部分
## 1.有关实现

接口中给出方法的具体实现，遵循以下原则：
- 返回整数的(`int`/`long`/`Integer`/`Long`)以 `-1` 作为默认值（即找不到时的返回值）。
- 返回类型为 `Object` （和/或其派生类，除基本类型的封装类型外）的，以 `null` 作为默认值

## 2.有关数据库

- 目前 `followlist` 表设计是， `user` 表中的项被删除时，会将该表中所有相关项级连删除。如果觉得不方便我就去掉这个特性，但是去掉之后对于不存在的 `uid` 需要在前/后端处理为“已注销用户”。