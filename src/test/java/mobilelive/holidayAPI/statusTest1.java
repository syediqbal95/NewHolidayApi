package mobilelive.holidayAPI;

import static org.junit.Assert.assertEquals;

import java.io.IOException;

import org.junit.Test;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;

import holiday.HolidayApiClientTemp;



public class statusTest1 {

	@Test
	public void test() throws JsonParseException, JsonMappingException, IOException {
		HolidayApiClientTemp client = new HolidayApiClientTemp("32256183-b02a-41d9-9b0a-53d63d8b49e9");
		@SuppressWarnings("unused")
		Object holidayJson = client.getHolidays("CAA", 2016 ,12,-1);
		assertEquals(400,client.getHolidayJson2().getStatus());

	}

}
