package club.kwcoder.report.service.impl;

import club.kwcoder.report.dataobject.Account;
import club.kwcoder.report.dataobject.AccountExample;
import club.kwcoder.report.mapper.AccountDao;
import club.kwcoder.report.model.bean.ResultBean;
import club.kwcoder.report.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AccountServiceImpl implements AccountService {

    @Autowired
    private AccountDao accountDao;

    @Override
    public ResultBean<List<Account>> list() {
        List<Account> accounts = accountDao.selectByExample(new AccountExample());
        return ResultBean.ok("账号列表查询成功！", accounts);
    }
}
