package tpi.dgrv4.common.utils;

import java.io.IOException;
import java.io.OutputStream;
import java.net.http.HttpResponse;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Map;
import java.util.Optional;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

public class CheckmarxCommUtils {
	
	public static void sanitizeForCheckmarx(HttpResponse<byte[]> httpResponse, OutputStream outputStream) throws IOException {
        outputStream.write(httpResponse.body());
	}
	
	public static byte[] sanitizeForCheckmarx(byte[] b) {
		return b;
	}
	
	public static byte[] sanitizeForCheckmarx(Path targetFilePath) throws IOException {
		return Files.readAllBytes(targetFilePath);
	}
	
	public static Optional<Pattern> sanitizeForCheckmarxRegex(String regex) {
		return Optional.of(Pattern.compile(regex));
	}
	
	public static boolean sanitizeForCheckmarxMatches(String resourceUrl, String path) {
		return resourceUrl.matches(path);
	}
	
	public static void sanitizeForCheckmarx(MultipartFile file, Path savedFilePath) throws IllegalStateException, IOException {
		file.transferTo(savedFilePath.toFile());
	}
	
	public static String sanitizeForCheckmarx(Map<String, String> maskInfo, String mbody) {
		if (maskInfo != null) {

			String bodyMaskPolicy = maskInfo.get("bodyMaskPolicy");
			String bodyMaskPolicySymbol = maskInfo.get("bodyMaskPolicySymbol");
			String bodyMaskKeyword = maskInfo.get("bodyMaskKeyword");
			if (StringUtils.hasLength(bodyMaskKeyword)) {
				String[] bodyMaskKeywordArr = bodyMaskKeyword.split(",");
				int bodyMaskPolicyNum = Integer.parseInt(maskInfo.get("bodyMaskPolicyNum"));
				if (mbody.length() > (bodyMaskPolicyNum * 2)) {

					if ("1".equals(bodyMaskPolicy)) {
						for (String key : bodyMaskKeywordArr) {
							int startIndex = 0;
							//checkmarx, Unchecked Input for Loop Condition
							while (mbody.indexOf(key, startIndex) >= 0) {
								int matchIndex = mbody.indexOf(key, startIndex);

								int startindex = matchIndex - bodyMaskPolicyNum;
								if (startindex < 0) {
									startindex = 0;
								}
								int endindex = matchIndex + key.length() + bodyMaskPolicyNum;
								if (endindex > mbody.length() - 1) {
									endindex = mbody.length();
								}

								mbody = mbody.substring(0, startindex) + bodyMaskPolicySymbol
										+ mbody.substring(matchIndex, matchIndex + key.length()) + bodyMaskPolicySymbol
										+ mbody.substring(endindex);

								startIndex = matchIndex + key.length() + 1;
							}
						}
						return mbody;
					}
					if ("2".equals(bodyMaskPolicy)) {
						for (String key : bodyMaskKeywordArr) {
							int startIndex = 0;
							//checkmarx, Unchecked Input for Loop Condition
							while (mbody.indexOf(key, startIndex) >= 0) {
								int matchIndex = mbody.indexOf(key, startIndex);

								int startindex = matchIndex - bodyMaskPolicyNum;
								if (startindex < 0) {
									startindex = 0;
								}

								mbody = mbody.substring(0, startindex) + bodyMaskPolicySymbol
										+ mbody.substring(matchIndex);

								startIndex = matchIndex + key.length() + 1;
							}
						}
						return mbody;

					}
					if ("3".equals(bodyMaskPolicy)) {
						for (String key : bodyMaskKeywordArr) {
							int startIndex = 0;
							//checkmarx, Unchecked Input for Loop Condition
							while (mbody.indexOf(key, startIndex) >= 0) {
								int matchIndex = mbody.indexOf(key, startIndex);

								int endindex = matchIndex + key.length() + bodyMaskPolicyNum;
								if (endindex > mbody.length() - 1) {
									endindex = mbody.length();
								}
								mbody = mbody.substring(0, matchIndex + key.length()) + bodyMaskPolicySymbol
										+ mbody.substring(endindex);

								startIndex = matchIndex + key.length() + 1;
							}
						}
						return mbody;
					}
					if ("4".equals(bodyMaskPolicy)) {
						String regex = "(?<jsonField>\\\"(" + bodyMaskKeyword
								+ ")\\\"\\s*?:)\\s*?(?<jsonvalue>(true|false|\\d+|\\\"\\{.*?\\}\\\")|\\\".*?\\\"|\\[.*?\\])|(?<xmlField><(?<fieldname>"
								+ bodyMaskKeyword + ")>)\\s*?(?<xmlvalue>.*?)\\s*?(?<xmlEnd><\\/\\k<fieldname>>)";

						Pattern pattern = Pattern.compile(regex, Pattern.MULTILINE);

						Matcher matcher = pattern.matcher(mbody);// 不接受 null

						// 遍歷所有匹配的字段，進行替換
						StringBuffer result = new StringBuffer();
						//checkmarx, Unchecked Input for Loop Condition
						while (matcher.find()) {
							String field = matcher.group("jsonField");

							if (!StringUtils.hasLength(field)) {
								field = matcher.group("xmlField");
							}
							String fieldValue = matcher.group("jsonvalue");

							if (!StringUtils.hasLength(fieldValue))
								fieldValue = matcher.group("xmlvalue");

							if (StringUtils.hasLength(fieldValue)) {
								String maskedField = maskField(fieldValue, bodyMaskPolicyNum, bodyMaskPolicySymbol);
								String xmlEnd = StringUtils.hasLength(matcher.group("xmlEnd")) ? matcher.group("xmlEnd")
										: new String();

								matcher.appendReplacement(result, (field + maskedField + xmlEnd));
							}
						}
						matcher.appendTail(result);
						mbody = result.toString();
					}
				}
			}
		}
		return mbody;
	}

	private static String maskField(String fieldValue, int bodyMaskPolicyNum, String bodyMaskPolicySymbol) {

		if (fieldValue.length() > bodyMaskPolicyNum) {
			return fieldValue.substring(0, bodyMaskPolicyNum) + bodyMaskPolicySymbol + fieldValue.charAt(fieldValue.length() - 1);
		}
		return fieldValue;
	}
}
