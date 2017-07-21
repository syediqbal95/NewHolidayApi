package mobilelive.holidayAPI;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

import java.io.IOException;

import org.junit.Before;
import org.junit.Test;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;

import custom.HolidayJson2;
import holiday.HolidayApiClientTemp;



public class Error {
	
	HolidayApiClientTemp holiday;
	Object holidayJson;
	
	@Before
	public void setup() throws JsonParseException, JsonMappingException, IOException{
		holiday = mock(HolidayApiClientTemp.class);
	
		holiday.setApiKey("32256183-b02a-41d9-9b0a-53d63d8b49e9");
		holidayJson = holiday.getHolidays("CA", 2018, 12, -1);
		when(holiday.getHolidayJson2()).thenReturn(new HolidayJson2(500, "failure error message!"));
	}
	
	@Test
	public void test() {
		assertEquals(500,holiday.getHolidayJson2().getStatus());
	}

}
