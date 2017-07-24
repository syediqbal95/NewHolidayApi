package holiday;
//import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import custom.HolidayJson;
import custom.HolidayJson2;


/**
 * Created by Jason on 29/06/2017.
 */
public class HolidayApiClientTemp  {

	private String apiKey=null;

	private HolidayJson holidayJson;
	private HolidayJson2 holidayJson2;

	public HolidayApiClientTemp(String apiKey) {
		this.apiKey = apiKey;
	}
	public HolidayApiClientTemp() {
	}
	

	public void setApiKey(String apiKey) {
		this.apiKey = apiKey;
	}


	public Object getHolidays(String country, int year, int month, int day)
			throws JsonParseException, JsonMappingException, IOException {

			String response = callRemoteHost("https://holidayapi.com/v1/holidays", country, year, month, day);

			ObjectMapper mapper = new ObjectMapper();
			if (response.contains("200")) {
				HolidayJson holidayJson = mapper.readValue(response, HolidayJson.class);
				this.holidayJson = holidayJson;
				return holidayJson.getHolidays();

			} else {
				HolidayJson2 holidayJson = mapper.readValue(response, HolidayJson2.class);
				this.holidayJson2 = holidayJson;
				return holidayJson.getStatus();
			}

	}

	private String callRemoteHost(String baseUrl, String country, int year, int month, int day) {

		HttpURLConnection conn = null;
		StringBuilder outputCollect = new StringBuilder();

		try {
			StringBuilder callBuilder = new StringBuilder().append(baseUrl).append("?").append("key=").append(apiKey)
					.append("&country=").append(country).append("&year=").append(year);

			if (month != -1) {
				callBuilder.append("&month=").append(month);
				if (day != -1) {
					callBuilder.append("&day=").append(day);
				}
			}

			String call = callBuilder.toString();

			URL url = new URL(call);

			conn = (HttpURLConnection) url.openConnection();
			conn.setRequestMethod("GET");
			conn.setRequestProperty("Accept", "application/json");

			if (conn.getResponseCode() != 200) {
				return "{\"status\":" + conn.getResponseCode() + ", \"error\": \"" + conn.getResponseMessage() + "\"}";
				// throw new RuntimeException("Failed : HTTP error code : " +
				// conn.getResponseCode());

			}

			BufferedReader br = new BufferedReader(new InputStreamReader((conn.getInputStream())));

			String line;

			while ((line = br.readLine()) != null) {
				outputCollect.append(line);
			}

		} catch (MalformedURLException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			conn.disconnect();
		}

		return outputCollect.toString();
	}

	public HolidayJson getHolidayJson() {
		return this.holidayJson;
	}

	public HolidayJson2 getHolidayJson2() {
		return this.holidayJson2;
	}
}
