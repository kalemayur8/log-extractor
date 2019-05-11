package com.prodlogextract.util;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.StringUtils;

import com.prodlogextract.constant.ModuleConstants;

public class ExtractLogUtil {
	public static final String DEFAULT_DATE_TIME_FORMAT_WITHOUT_MILLISEC = "yyyy-MM-dd HH:mm";
	//public static final String LOCAL_PATH = "\""+"C:/Users/S799967/Work/Project/Prod Logs/M Directory Structure/folders/ProdLogs"+"\"";
	public static final String LOCAL_PATH = "\""+"Logs/ProdLogs"+"\"";
	public static final String SHARED_PATH = "\""+"M:/Automation tools/ACP/ProdLogs"+"\"";
	public static final String STAGE_SHARED_PATH = "\""+"M:/Automation tools/ACP/StaggingLogs"+"\"";
	//public static final String STAGE_PATH = "\""+"C:/Users/S799967/Work/Project/Prod Logs/M Directory Structure/folders/StaggingLogs"+"\"";
	public static final String STAGE_PATH = "\""+"Logs/StaggingLogs"+"\"";
	static Logger LOGGER = LoggerFactory.getLogger(ExtractLogUtil.class);
	public static void extractLogs(String moduleName,String rangeFrom, String rangeTo, String source, String env) {
		long hoursDiff = (ConvertStringDateToLong(rangeTo)-ConvertStringDateToLong(rangeFrom))/(60 * 60 * 1000);
		LOGGER.info("Date Difference in hour | {} ", hoursDiff);
		if (hoursDiff <= 2) {
			rangeFrom = "\"" + rangeFrom + "\""; // double quotation required for script to run.
			rangeTo = "\"" + rangeTo + "\"";
			LOGGER.info("{} | {} | {}", moduleName, rangeFrom, rangeTo);
			String serverNode = "";
			if(moduleName.contains(",")) {
			String[] moduleNames = moduleName.split(",");
			moduleName = moduleNames[0];
			serverNode = moduleNames[1];
			}
			List<String> fileNames = getFilesNameToExecute(moduleName, env, serverNode);
			LOGGER.info("total servers {} | scripts to execute | {}", fileNames.size(), fileNames);
			initializeScript(fileNames, rangeFrom, rangeTo, source, env);
		}else {
			LOGGER.error("Timestamp difference should not be more than 2 hours");
		}
	}
	
	private static void initializeScript(List<String> fileNames, String rangeFrom, String rangeTo, String source, String env) {
		LOGGER.info("file names has been passed to divide it in multiple threads");
		fileNames.forEach(file -> {
			Thread thread = new Thread(file.replace(".txt", "")) {
				@Override
				public void run() {
					executeScript(file, rangeFrom, rangeTo, source, env);
				}
			};
			thread.start();
		});
	}

	private static void executeScript(String file, String rangeFrom, String rangeTo, String source, String env) {
		try {
			LOGGER.info("file : {} execution started",  file);
			String logPath = source.equals("local") ? LOCAL_PATH : SHARED_PATH;
			if("stag".equals(env)) {
				logPath = source.equals("local") ? STAGE_PATH : STAGE_SHARED_PATH;
			}
			Process p = Runtime.getRuntime().exec(new String[]{"cmd.exe", "/c", "script.bat " + file + " " + rangeFrom + " " + rangeTo + " " + logPath});
			BufferedReader reader=new BufferedReader(new InputStreamReader(p.getInputStream())); 
			BufferedReader error =new BufferedReader(new InputStreamReader(p.getErrorStream()));
            String line;
            while((line = reader.readLine()) != null) 
            { 
            	LOGGER.info("CMD PROMPT | {}", line);
            }
            reader.close();
            while((line = error.readLine()) != null) 
            { 
            	LOGGER.error("CMD PROMPT ERROR | {}", line);
            } 
            error.close();
		} catch (Exception e) {
			LOGGER.error("exception while executing script {} ", e);
		}
	}

	private static List<String> getFilesNameToExecute(String moduleName, String env, String serverNode) {
		List<String> fileNames = new ArrayList<>();
		switch(moduleName) {
		case ModuleConstants.EVENT : 
			if (!"stag".equalsIgnoreCase(env)) {
				List<String> serverNames = ModuleConstants.EVENT_SERVERS;
				if (!StringUtils.isEmpty(serverNode)) {
					serverNames = serverNames.stream().filter(serverName -> serverNode.equals(serverName))
							.collect(Collectors.toList());
				}
				fileNames = serverNames.stream().map(server -> "event/event_" + server + "_node1.txt")
						.collect(Collectors.toList());
				fileNames.addAll(serverNames.stream().map(server -> "event/event_" + server + "_node2.txt")
						.collect(Collectors.toList()));
			} else {
				List<String> serverNames = ModuleConstants.EVENT_STAGE_SERVERS;
				if (!StringUtils.isEmpty(serverNode)) {
					serverNames = serverNames.stream().filter(serverName -> serverNode.equals(serverName))
							.collect(Collectors.toList());
				}
				fileNames = serverNames.stream().map(server -> "event/stag/event_" + server + "_node1.txt")
						.collect(Collectors.toList());
				fileNames.addAll(serverNames.stream().map(server -> "event/stag/event_" + server + "_node2.txt")
						.collect(Collectors.toList()));
			}
			break;
		case ModuleConstants.ACP :
			if (!"stag".equalsIgnoreCase(env)) {
				List<String> serverNames = ModuleConstants.ACP_SERVERS;
				if (!StringUtils.isEmpty(serverNode)) {
					serverNames = serverNames.stream().filter(serverName -> serverNode.equals(serverName))
							.collect(Collectors.toList());
				}
				fileNames.addAll(
						serverNames.stream().map(server -> "acp/acp_" + server + ".txt").collect(Collectors.toList()));
			} else {
				List<String> serverNames = ModuleConstants.ACP_STAGE_SERVERS;
				if (!StringUtils.isEmpty(serverNode)) {
					serverNames = serverNames.stream().filter(serverName -> serverNode.equals(serverName))
							.collect(Collectors.toList());
				}
				fileNames.addAll(serverNames.stream().map(server -> "acp/stag/acp_" + server + ".txt")
						.collect(Collectors.toList()));
			}
			break;
		case ModuleConstants.ACP_UI :
			if (!"stag".equalsIgnoreCase(env)) {
				List<String> serverNames = ModuleConstants.ACP_UI_SERVERS;
				if (!StringUtils.isEmpty(serverNode)) {
					serverNames = serverNames.stream().filter(serverName -> serverNode.equals(serverName))
							.collect(Collectors.toList());
				}
				fileNames.addAll(
						serverNames.stream().map(server -> "acp/acp_" + server + ".txt").collect(Collectors.toList()));
			} else {
				List<String> serverNames = ModuleConstants.ACP_STAGE_SERVERS;
				if (!StringUtils.isEmpty(serverNode)) {
					serverNames = serverNames.stream().filter(serverName -> serverNode.equals(serverName))
							.collect(Collectors.toList());
				}
				fileNames.addAll(serverNames.stream().map(server -> "acp/stag/acp_" + server + ".txt")
						.collect(Collectors.toList()));
			}
			break;
		case ModuleConstants.ACP_EVENT :
			if (!"stag".equalsIgnoreCase(env)) {
				List<String> serverNames = ModuleConstants.ACP_EVENT_SERVERS;
				if (!StringUtils.isEmpty(serverNode)) {
					serverNames = serverNames.stream().filter(serverName -> serverNode.equals(serverName))
							.collect(Collectors.toList());
				}
				fileNames.addAll(
						serverNames.stream().map(server -> "acp/acp_" + server + ".txt").collect(Collectors.toList()));
			} else {
				List<String> serverNames = ModuleConstants.ACP_STAGE_SERVERS;
				if (!StringUtils.isEmpty(serverNode)) {
					serverNames = serverNames.stream().filter(serverName -> serverNode.equals(serverName))
							.collect(Collectors.toList());
				}
				fileNames.addAll(serverNames.stream().map(server -> "acp/stag/acp_" + server + ".txt")
						.collect(Collectors.toList()));
			}
			break;
		case ModuleConstants.AUDIT :
			if (!"stag".equalsIgnoreCase(env)) {
			fileNames.addAll(ModuleConstants.AUDIT_SERVERS.stream().map(server -> "audit/audit_"+ server + ".txt").collect(Collectors.toList()));
			}else {
			fileNames.addAll(ModuleConstants.AUDIT_STAGE_SERVERS.stream().map(server -> "audit/stag/audit_"+ server + ".txt").collect(Collectors.toList()));	
			}
			break;
		case ModuleConstants.CUSTOMERLIST :
			if (!"stag".equalsIgnoreCase(env)) {
			fileNames.addAll(ModuleConstants.CUSTOMER_LIST_SERVERS.stream().map(server -> "customerlist/customerlist_"+ server + ".txt").collect(Collectors.toList()));
			}else {
			fileNames.addAll(ModuleConstants.CUSTOMERLIST_STAGE_SERVERS.stream().map(server -> "customerlist/stag/customerlist_"+ server + ".txt").collect(Collectors.toList()));
			}
			break;
		case ModuleConstants.SCHEDULAR:
			if (!"stag".equalsIgnoreCase(env)) {
			fileNames.addAll(ModuleConstants.SCHEDULAR_SERVERS.stream().map(server -> "schedular/schedular_"+ server + ".txt").collect(Collectors.toList()));
			}else {
			fileNames.addAll(ModuleConstants.SCHEDULAR_STAGE_SERVERS.stream().map(server -> "schedular/stag/schedular_"+ server + ".txt").collect(Collectors.toList()));
			}
			break;
		/*case ModuleConstants.REPORT:
			fileNames.addAll(ModuleConstants.SCHEDULAR_SERVERS.stream().map(server -> "schedular/schedular_"+ server + ".txt").collect(Collectors.toList()));
			break;*/
		case ModuleConstants.ASUI :
			if("prod".equalsIgnoreCase(env)) {
				fileNames.addAll(ModuleConstants.ASUI_UI_SERVERS.stream().map(server -> "asui/asui_"+ server + ".txt").collect(Collectors.toList()));
			}
			break;
		default :
			break;
		}
		return fileNames;
	}
	
	 public static Long ConvertStringDateToLong(String dte) {
	        SimpleDateFormat formatter = new SimpleDateFormat(DEFAULT_DATE_TIME_FORMAT_WITHOUT_MILLISEC);
	        if (dte != null) {
	            Date date = null;
	            try {
	                date = formatter.parse(dte.toUpperCase());
	            } catch (Exception e) {
	                LOGGER.error("Invalid Date Parse Exception " ,e);
	            }
	            if (date != null) {
	                return Long.valueOf(date.getTime());
	            }
	        }
	        return null;
	    }
}
