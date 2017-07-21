package mobilelive.holidayAPI;

import static org.junit.Assert.assertEquals;

import java.io.IOException;
import java.util.List;

import org.junit.Test;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;

import custom.Holiday;
import holiday.HolidayApiClientTemp;



public class TillDateTest {
	@Test
	public void test() throws JsonParseException, JsonMappingException, IOException {
		HolidayApiClientTemp client = new HolidayApiClientTemp("32256183-b02a-41d9-9b0a-53d63d8b49e9");
		Object holidayJson = client.getHolidays("PK", 2016, 12, 01);
		@SuppressWarnings("unchecked")
		List<Holiday> holidays = (List<Holiday>)holidayJson;
		StringBuilder sb = new StringBuilder();
		for (Holiday holiday : holidays) {
			sb.append(holiday.toString());
		}
		String result = sb.toString();
		String expect = "\nname: Birthday of Nabi" + "\ndate: 2016-12-01" + "\nobserved: 2016-12-01" + "\npublic: true";
		assertEquals(expect, result);

	}
}
