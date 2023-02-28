package ru.study.internetbank.services;

import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;

@Repository
public class BankDao {

    final private JdbcTemplate jdbcTemplate;

    public BankDao(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public BigDecimal getBalance(String userId) throws DataAccessException {
        return jdbcTemplate.queryForObject(SELECT_BALANCE, BigDecimal.class, userId);
    }

    private static final String SELECT_BALANCE = "select current_balance from ibank.balance where user_id = ?";

    public void setBalance(String userId, BigDecimal currentBalance) throws DataAccessException {
        jdbcTemplate.update(UPDATE_BALANCE, currentBalance, userId);
    }

    private static final String UPDATE_BALANCE = "update ibank.balance set current_balance = ? where user_id = ?";

}
