package utils;

import java.io.BufferedReader;
import java.io.IOException;
import java.text.SimpleDateFormat;

import com.fasterxml.jackson.databind.ObjectMapper;

public class HttpUtils {
	public static <T> T toModel(Class<T> tClass, BufferedReader reader) throws IOException {
		StringBuilder builder = new StringBuilder();
		String readLine = new String();
		while ((readLine = reader.readLine()) != null) {
			builder.append(readLine);
		}

		String jSon = builder.toString();
		ObjectMapper mapper = new ObjectMapper();
		mapper.setDateFormat(new SimpleDateFormat("yyyy/MM/dd"));
		return mapper.readValue(jSon, tClass);
	}
}
