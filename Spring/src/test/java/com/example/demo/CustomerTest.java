package com.example.demo;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.ArrayList;
import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import com.taragani.model.Customer;
import com.taragani.model.Plan;
import com.taragani.service.CustomerApi;
import com.taragani.service.CustomerService;
import com.verizon.esd.TestUtil.TestUtil;

@RunWith(SpringRunner.class)

@WebMvcTest(controllers = CustomerApi.class)
public class CustomerTest {
	
	private MockMvc mockMvc;

	@Autowired
	private WebApplicationContext webApplicationContext;

	@MockBean
	private CustomerService custServiceMock;

	@Before
	public void setUp() {
		mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
	}

	@After
	public void tearDown() {
		mockMvc = null;
	}
	@Test
	public void testGetAllEmployees() throws Exception {
		assertThat(this.custServiceMock).isNotNull();

		List<Customer> clist = new ArrayList<>();
		clist.add(new Customer());

		when(custServiceMock.getAllCustomers()).thenReturn(clist);

		mockMvc.perform(get("/customers")).andExpect(status().isOk()).andDo(print());

	}
	
	@Test
	public void testGetPlan() throws Exception {
		assertThat(this.custServiceMock).isNotNull();
		int cid = 12;
	

		Customer cust = new Customer();
		
		
		
		
		cust.setCid(1);
		cust.setName("Customer1");
		cust.setpTitle("planA");

		cust.setMbno("98988980988");
		cust.setTimeSlot("60");
		
		when(custServiceMock.getCustomer(cid)).thenReturn(cust);

		mockMvc.perform(get("/customers/1")).andExpect(status().isOk()).andDo(print());

	}

	
	@Test
	public void testAddEmployee() throws Exception {
		assertThat(this.custServiceMock).isNotNull();

		Customer cust = new Customer();

		cust.setCid(1);
		cust.setName("Customer1");
		cust.setpTitle("planA");

		cust.setMbno("98988980988");
		cust.setTimeSlot("60");

		

		System.out.println(cust);

		//when(custServiceMock.addCustomer(Mockito.any(Customer.class))).thenReturn(cust);

		mockMvc.perform(post("/customers").contentType(TestUtil.APPLICATION_JSON_UTF8)
				.content(TestUtil.convertObjectToJsonBytes(cust))).andDo(print()).andExpect(status().isOk())
				.andExpect(content().contentType(TestUtil.APPLICATION_JSON_UTF8));

	}

}
