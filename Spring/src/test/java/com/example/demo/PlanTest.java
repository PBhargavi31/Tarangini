package com.example.demo;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import com.taragani.Application;
import com.taragani.model.Plan;
import com.taragani.service.PlanApi;
import com.taragani.service.PlanService;




@RunWith(SpringRunner.class)

@WebMvcTest(controllers = PlanApi.class)

public class PlanTest {
	private MockMvc mockMvc;
	
	@Autowired
	private WebApplicationContext webApplicationContext;
	
	
	@MockBean
	private PlanService planServiceMock;

	
	
	@Before
	public void setUp() {
		mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
	}

	@After
	public void tearDown() {
		mockMvc = null;
	}
	@Test
	public void testGetAllPlans() throws Exception {
		assertThat(this.planServiceMock).isNotNull();

		List<Plan> planList = new ArrayList<>();
		planList.add(new Plan());

		when(planServiceMock.getAllPlans()).thenReturn(planList);

		mockMvc.perform(get("/plans")).andExpect(status().isOk()).andDo(print());

	}
	
	@Test
	public void testGetPlan() throws Exception {
		assertThat(this.planServiceMock).isNotNull();
		String pTitle = "planA";
	

		Plan plan = new Plan();

		plan.setpTitle("plan1");
		plan.setSpeed(50);
		plan.setMaxUsage(500);
		plan.setCharge(1520.0);
		

		when(planServiceMock.getPlan(pTitle)).thenReturn(plan);

		mockMvc.perform(get("/plans/'planA'")).andExpect(status().isOk()).andDo(print());

	}
	
	
	
	
	
	
	

	
}
