package cn.jasonone.service.impl;

import cn.jasonone.bean.Account;
import cn.jasonone.mapper.AccountMapper;
import cn.jasonone.model.AccountQuery;
import cn.jasonone.service.AccountService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;


/**
 * 用户表服务接口实现类
 */

@Service
public class AccountServiceImpl implements AccountService {

    @Resource
    private AccountMapper accountMapper;

    @Override
    public Account findDetail(Long id) {
        return accountMapper.findDetail(id);
    }

    @Override
    public boolean existUsernameOrEmail(String username, String email) {
        return accountMapper.existUsernameOrEmail(username, email) > 0;
    }

    @Override
    public Account selectByPrimaryKey(Long id) {
        return accountMapper.selectByPrimaryKey(id);
    }

    @Override
    public boolean register(Account account) {
        return accountMapper.insertSelective(account) > 0;
    }

    @Override
    public Account login(String username, String password) {
        Account account = new Account();
        account.setUsername(username);
        account.setPassword(password);
        return accountMapper.findOne(account);
    }

    @Override
    public Account findByEmail(String email) {
        Account account = new Account();
        account.setEmail(email);
        return accountMapper.findOne(account);
    }

    @Override
    public Account selectAllByUsername(String username) {
        return accountMapper.selectAllByUsername(username);
    }

    @Override
    public int logicDeleteById(Long id) {
        return accountMapper.logicDeleteById(id);
    }

    @Override
    public int updateByPrimaryKeySelective(Account account) {
        return accountMapper.updateByPrimaryKeySelective(account);
    }

    @Override
    public Account findDetail2(Long id) {
        return accountMapper.findDetail(id);
    }

    @Override
    public List<Account> findAll(AccountQuery accountQuery) {
        return accountMapper.findAll(accountQuery);
    }

    @Override
    public int updatePassword(Account account) {

        return accountMapper.updatePassword(account);
    }

    @Override
    public int updateNickname(Account account) {
        return accountMapper.updateNickname(account);
    }

    @Override
    public int updateEmail(Account account) {
        return accountMapper.updateEmail(account);
    }

}
