package club.kwcoder.report.controller;

import club.kwcoder.report.dataobject.Account;
import club.kwcoder.report.model.bean.ResultBean;
import club.kwcoder.report.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(value = "/account/")
public class AccountController {

    @Autowired
    private AccountService accountService;

    @RequestMapping(value = "list", method = RequestMethod.GET)
    public ResultBean<List<Account>> list() {
        return accountService.list();
    }

}
