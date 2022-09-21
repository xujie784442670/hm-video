package cn.jasonone.mapper;

import cn.jasonone.bean.Account;
import cn.jasonone.model.AccountQuery;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 用户表数据库操作mapper
* @author
针对表用户表的数据库操作Mapper
*
* @createDate 2022-08-24 10:18:51
*/
public interface AccountMapper {
    /**
     * 通过id删除用户
     * @param id 用户id
     * @return 受影响行数
     */
    int deleteByPrimaryKey(@Param("id")Long id);

    /**
     * 添加用户
     * @param account  用户信息
     * @return 受影响行数
     */
    int insert(Account account);

    /**
     * 选择性添加用户
     * @param account 用户信息
     * @return 受影响行数
     */
    int insertSelective(Account account);

    /**
     * 通过主键查询用户
     * @param id 用户id
     * @return 受影响行数
     */
    Account selectByPrimaryKey(@Param("id")Long id);

    /**
     * 查询所有用户
     * @return 用户集合
     */
    List<Account> selectAll();

    /**
     * 用户表查询对象
     * @param accountQuery 用户表查询对象
     * @return 用户集合
     */
    List<Account> findAll(AccountQuery accountQuery);

    /**
     * 通过username查询对象
     * @param username
     * @return Account
     */
    Account selectAllByUsername(@Param("username") String username);

    /**
     * 选择性更新用户
     * @param account 用户信息
     * @return 受影响行数
     */
    int updateByPrimaryKeySelective(Account account);

    /**
     *
     * 更新密码
     * @param account
     * @return 受影响行数
     */

    int updatePassword(Account account);


    /**
     *
     *更新昵称
     *
     * @param account
     * @return
     */
    int updateNickname(Account account);

    /**
     * 更新邮箱
     * @param account
     * @return
     */

    int     updateEmail (Account account);
    /**
     * 更新用户
     * @param account 用户信息
     * @return 受影响行数
     */
    int updateByPrimaryKey(Account account);

    /**
     * 检查用户名或邮箱是否存在
     * @param username
     * @param email
     * @return
     */
    int existUsernameOrEmail(@Param("username") String username,@Param("email")  String email);


    Account findOne(Account account);

    /**
     * 通过用户id查找用户昵称
     */
        String selectByAccountId(@Param("id")Long id);

    /**
     * 通过用户昵称查找用户ID
     */
    Long selectByNickName(String name);

    /**
     * 通过id逻辑删除账号
     * @param id 主键
     * @return 受影响行数
     */
    int logicDeleteById(@Param("id") Long id);

    Account findDetail(Long id);




}
