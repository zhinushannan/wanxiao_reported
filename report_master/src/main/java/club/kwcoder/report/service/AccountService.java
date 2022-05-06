package club.kwcoder.report.service;

import club.kwcoder.report.dataobject.Account;
import club.kwcoder.report.model.bean.ResultBean;

import java.util.List;

public interface AccountService {
    ResultBean<List<Account>> list();
}
