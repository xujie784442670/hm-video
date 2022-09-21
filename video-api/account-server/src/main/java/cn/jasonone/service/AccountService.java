package cn.jasonone.service;

import cn.jasonone.bean.Account;
import cn.jasonone.model.AccountQuery;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 用户表操作服务接口
 */
public interface AccountService {
    /**
     * 检查用户名或邮箱是否存在
     *
     * @param username,email
     * @return
     */
    boolean existUsernameOrEmail(String username, String email);

    Account selectByPrimaryKey(@Param("id") Long id);

    /**
     * 注册
     *
     * @param account
     * @return
     */
    boolean register(Account account);

    /**
     * 登录
     *
     * @param username
     * @param password
     * @return
     */
    Account login(String username, String password);

    /**
     * 根据邮箱查询用户信息
     *
     * @param email
     * @return
     */
    Account findByEmail(String email);

    Account selectAllByUsername(@Param("username") String username);


    /**
     * 通过id逻辑删除账号
     *
     * @param id 主键
     * @return 受影响行数
     */
    int logicDeleteById(Long id);

    int updateByPrimaryKeySelective(Account account);

    Account findDetail(Long id);

    Account findDetail2(Long id);


    int updatePassword(Account account);


    int updateNickname (Account account);


    int updateEmail(Account account);

    List<Account> findAll(AccountQuery accountQuery);
}
