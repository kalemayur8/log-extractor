package com.prodlogextract.constant;

import java.util.Arrays;
import java.util.List;

public class ModuleConstants {
	public static final String EVENT="event";
	public static final String AUDIT="audit";
	public static final String CUSTOMERLIST="customerlist";
	public static final String ACP="acp";
	public static final String ACP_UI="acp-ui";
	public static final String ACP_EVENT="acp-event";
	public static final String SCHEDULAR="schedular"; 
	public static final String REPORT = "report";
	public static final String ASUI="asui";
	
	public static final List<String> EVENT_SERVERS = Arrays.asList("hq1266","hq1267","hq2081","do1436","do1511","do1512");
	public static final List<String> ACP_SERVERS = Arrays.asList("hq1266","hq1267","hq2081","hq2080","hq1265","do1436","do1511","do1512","do683","do1510");
	public static final List<String> ACP_UI_SERVERS = Arrays.asList("hq2080","hq1265","do683","do1510");
	public static final List<String> ASUI_UI_SERVERS = Arrays.asList("hq511","hq512","do1041","do1042");
	public static final List<String> ACP_EVENT_SERVERS = Arrays.asList("hq1266","hq1267","hq2081","do1436","do1511","do1512");
	public static final List<String> AUDIT_SERVERS = Arrays.asList("hq2080","hq1265","do683","do1510");
	public static final List<String> CUSTOMER_LIST_SERVERS = Arrays.asList("hq2080","hq1265","do683","do1510");
	public static final List<String> SCHEDULAR_SERVERS = Arrays.asList("hq509","do1040");
	public static final List<String> REPORT_SERVERS = Arrays.asList("hq2080","hq1265","do683","do1510");
	
	public static final List<String> EVENT_STAGE_SERVERS = Arrays.asList("do1391","do1502");
	public static final List<String> ACP_STAGE_SERVERS = Arrays.asList("do1391","do1502");
	public static final List<String> AUDIT_STAGE_SERVERS = Arrays.asList("do1391","do1502");
	public static final List<String> CUSTOMERLIST_STAGE_SERVERS = Arrays.asList("do1391","do1502");
	public static final List<String> SCHEDULAR_STAGE_SERVERS = Arrays.asList("do1391","do1502");
}
