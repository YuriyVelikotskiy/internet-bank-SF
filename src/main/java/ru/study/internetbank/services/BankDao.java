package ru.study.internetbank.services;

import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import ru.study.internetbank.model.OperationInfo;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

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

    public List<OperationInfo> getOperationList(String userId, Date startDate, Date finishDate) throws DataAccessException {
        return jdbcTemplate.queryForList(SELECT_OPERATION, OperationInfo.class, userId, startDate, finishDate);
    }

    private static final String SELECT_OPERATION =
            "select * from ibank.operations where (user_id = ? and operation_date between ? and ?)";

    public void setBalance(String userId, BigDecimal currentBalance) throws DataAccessException {
        jdbcTemplate.update(UPDATE_BALANCE, currentBalance, userId);
    }

    public void setOperation(String userId, BigDecimal amount, int type) {
        jdbcTemplate.update(UPDATE_OPERATION, userId, amount, type);
    }

    private static final String UPDATE_BALANCE = "update ibank.balance set current_balance = ? where user_id = ?";

    private static final String UPDATE_OPERATION = "insert into ibank.operations (user_id, amount, type_of_operation) values(?,?,?)";
}