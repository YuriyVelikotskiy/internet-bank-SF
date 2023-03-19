package ru.study.internetbank.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import ru.study.internetbank.model.PutRequest;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@ExtendWith(SpringExtension.class)
@AutoConfigureMockMvc
class BankControllerTest {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @BeforeEach
    void setup() {
        jdbcTemplate.update(
            "insert into ibank.balance (user_id, current_balance) values ('test-1', '10000')"
        );
    }

    @AfterEach
    void cleanup() {
        jdbcTemplate.update("delete from ibank.balance where user_id = 'test-1'");
    }

    @Test
    void getBalance() throws Exception {
        mockMvc.perform(get("/api/balance/test-1"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.value").value(10000))
                .andExpect(jsonPath("$.message").isEmpty());
        mockMvc.perform(get("/api/balance/test-2"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.value").value(-1))
                .andExpect(jsonPath("$.message").value("Пользователь не найден!!!"));
    }

    @Test
    void getOperationList() throws Exception {
        // TODO getOperationList test
    }

    @Test
    void putMoney() throws Exception {
        // TODO putMoney test
    }

    @Test
    void takeMoney() throws Exception {
        PutRequest putRequest = new PutRequest(BigDecimal.valueOf(1000), "test-1");

        mockMvc.perform(
                    post("/api/take-money")
                            .contentType(MediaType.APPLICATION_JSON)
                            .content(objectMapper.writeValueAsString(putRequest))
                )
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.value").value(1))
                .andExpect(jsonPath("$.message").isEmpty());

        mockMvc.perform(
                    post("/api/take-money")
                            .contentType(MediaType.APPLICATION_JSON)
                            .content("{\"value\":\"10000\",\"userId\":\"test-1\"}")
                )
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.value").value(0))
                .andExpect(jsonPath("$.message").value("Недостаточно средств"));

        Double value = jdbcTemplate.queryForObject("select current_balance from ibank.balance where user_id='test-1'", Double.class);
        assertEquals(9000, value);
    }

    @Test
    void transferMoney() throws Exception {
        // TODO transferMoney test
    }
}