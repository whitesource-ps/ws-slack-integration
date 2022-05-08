# WS Slack Integration Service #  
Slack Integration Service provides pushing messaging using Slack Webhooks of the following:
1. On-demand Security alerts (getSecurityAlerts).
1. On-demand Policy Violation messages (checkPolicyViolations).
1. Scheduled Security alerts (CRON style scheduling).

## Instructions ##
1. Create a Slack application and an internal webhook for WhiteSource usage. More information here:(https://api.slack.com/messaging/webhooks).
1. Download the slack-integration.jar + application.properties config file. 
1. Fill-in the relevant parameters:
   1. server.port: the running port of the web service [default is 8081].
   1. slack.url: the created webhook URL for Slack [mandatory].
   1. slack.maxResults: number of results will be presented on Slack [default is 5]. d. ws.url: WhiteSource url [mandatory. E.g:
   https://saas.whitesourcesoftware.com].
   1. ws.orgToken: WhiteSource org token/API key [mandatory].
   1. ws.userKey: WhiteSource user key [mandatory].
   1. scheduler.getSecurityAlerts: cron pattern for scheduling the security alerts job [mandatory. e.g for every day at 8 set: _* * 8 * * *_ - ].
   1. scheduler.getSecurityAlertsEnabled: boolean for enabling/disabling the scheduled job [default is _true_].
   1. vulnerability.minScore: minimal score (0-10) for the relevant vulnerabilities [default is _0_].
   1. vulnerability.fromDate: minimal publish date of the vulnerability in _yyyy-MM-dd_ format.
1. Execute: _java -jar slack-integration.jar --spring.config.location=file:///{full_path_to_config}\application.properties_
1. Once the service is up and running, access one of the end-point operations below
   
## Operation  
   1. To manually pull Organization Security Alerts, access url: http://localhost:8081/getSecurityAlerts.
   1. To manually pull Policy Violations (based on last generated _policyRejectionSummary.txt_ by **UA** scan run), access url: http://[URL]:8081/checkPolicyViolations.
   1. To integrate with CI/CD pipeline:
      1. Enable the **checkPolicies** parameter in the **WS Unified Agent** (UA) config file.
      1. During pipeline run (common after **UA** ran), call **checkPolicyViolations** with the form variables below to see if there is a violation and to decide.
         Variables: file=<[>policyRejectionSummary.json_LOCATION>, build=, project=
      
    Examples:

      **Jenkins:** _curl http://[URL]:8081/checkPolicyViolations -v -F"file=@whitesource/policyRejectionSummary.json" -F "build=%BUILD_NUMBER%" -F "build=%BUILD_NUMBER%" -F "project=%JOB_NAME%" -F ($(Build.BuildNumber)_
         
      **AZDO**: _curl http://[URL]:8081/checkPolicyViolations -v -F"file=@whitesource/policyRejectionSummary.json" -F "build=%BUILD_NUMBER%" -F "build=%BUILD_NUMBER%" -F "project=%JOB_NAME%" -F $(Build.DefinitionName)_ 
