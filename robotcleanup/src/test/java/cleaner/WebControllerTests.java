package cleaner;

import static org.hamcrest.CoreMatchers.containsString;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;

@RunWith(SpringRunner.class)
//@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@SpringBootTest
@AutoConfigureMockMvc

public class WebControllerTests {

	@Autowired
	private MockMvc mockMvc;

	@Test
	public void testCase1() throws Exception {
		String expectedResult = "{\"finalPosition\":\"[2,2]\", \"oilPatchesCleaned\":1}";

		this.mockMvc
				.perform(get("/cleaner").param("areaSize", "5,5").param("startingPosition", "0,0")
						.param("oilPatches", "1,1").param("oilPatches", "1,2").param("oilPatches", "1,3")
						.param("navigationInstructions", "ENEN"))
				.andDo(print()).andExpect(status().isOk()).andExpect(content().string(expectedResult));
	}

	@Test
	public void testCase2() throws Exception {
		String expectedResult = "{\"finalPosition\":\"[2,2]\", \"oilPatchesCleaned\":2}";

		this.mockMvc
				.perform(get("/cleaner").param("areaSize", "4,3").param("startingPosition", "1,0")
						.param("oilPatches", "2,2").param("oilPatches", "2,1").param("oilPatches", "3,1")
						.param("navigationInstructions", "ENN"))
				.andDo(print()).andExpect(status().isOk()).andExpect(content().string(expectedResult));
	}

	@Test
	public void testCase3() throws Exception {
		String expectedResult = "{\"finalPosition\":\"[2,1]\", \"oilPatchesCleaned\":1}";

		this.mockMvc
				.perform(get("/cleaner").param("areaSize", "3,2").param("startingPosition", "0,1")
						.param("oilPatches", "0,1").param("oilPatches", "1,0").param("navigationInstructions", "SEEN"))
				.andDo(print()).andExpect(status().isOk()).andExpect(content().string(expectedResult));
	}

	@Test
	public void testCase4() throws Exception {
		String expectedResult = "Direction is invalid as it will take the cleaner robot outside area";

		this.mockMvc
				.perform(get("/cleaner").param("areaSize", "5,5").param("startingPosition", "0,0")
						.param("oilPatches", "1,1").param("oilPatches", "1,2").param("oilPatches", "1,3")
						.param("navigationInstructions", "WNEN"))
				.andDo(print()).andExpect(status().isOk()).andExpect(content().string(containsString(expectedResult)));

	}
	
	@Test
	public void testCase5() throws Exception {
		String expectedResult = "Your co-ordinates out of the bounds of the area";

		this.mockMvc
				.perform(get("/cleaner").param("areaSize", "2,2").param("startingPosition", "3,3")
						.param("oilPatches", "1,1").param("oilPatches", "1,2").param("oilPatches", "2,2")
						.param("navigationInstructions", "WNEN"))
				.andDo(print()).andExpect(status().isOk()).andExpect(content().string(containsString(expectedResult)));

	}
	
	@Test
	public void testCase6() throws Exception {
		String expectedResult = "Required String parameter 'startingPosition' is not present";

		this.mockMvc
				.perform(get("/cleaner").param("areaSize", "2,2"))
				.andDo(print()).andExpect(status().isOk()).andExpect(content().string(containsString(expectedResult)));

	}
	
	@Test
	public void testCase7() throws Exception {
		String expectedResult = "Required String parameter 'areaSize' is not present";

		this.mockMvc
				.perform(get("/cleaner").param("startingPosition", "2,2"))
				.andDo(print()).andExpect(status().isOk()).andExpect(content().string(containsString(expectedResult)));

	}
		
}
