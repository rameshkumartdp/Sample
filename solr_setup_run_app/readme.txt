Setup SOLR
-------------------
1. extract solr-6.1.0.zip to C drive
2. Open command prompt and run below command from bin folder to start SOLR
	C:\solr-6.1.0\bin>solr start
	By default solr starts on 8983 port
3. Create collection in Solrx
	C:\solr-6.1.0\bin>solr create_collection -c collection1
4. SOLR can be accessed by below URL
	http://localhost:8983/solr/#/
5. Post documents to SOLR (Employee_Details.json is present in this folder)
	C:\solr-6.1.0\bin>java -Dc="collection1" -Dauto=yes -Ddata=files -jar C:\Solrx\solr-6.1.0\solr-6.1.0\example\exampledocs\post.jar C:\Desktop\Employee_Details.json
	
	Above command gives output like below.
	C:\solr-6.1.0\bin>java -Dc="collection1" -Dauto=yes -Ddata=files -jar C:\solr-6.1.0\example\exampledocs\post.jar C:\Users\rk0000\Desktop\Employee_Details.json
	SimplePostTool version 5.0.0
	Posting files to [base] url http://localhost:8983/solr/collection1/update...
	Entering auto mode. File endings considered are xml,json,jsonl,csv,pdf,doc,docx,ppt,pptx,xls,xlsx,odt,odp,ods,ott,otp,ots,rtf,htm,html,txt,log
	POSTing file Employee_Details.json (application/json) to [base]/json/docs
	1 files indexed.
	COMMITting Solr index changes to http://localhost:8983/solr/collection1/update...
	Time spent: 0:00:01.087

	
Run application:
----------------------------
Run sample.jar using below command
java -jar sample.jar


Access Application:
--------------------------------
Below is the rest URL to access java application.
http://localhost:8080/sample/search?q=*:*


Projectc setup:
------------------------
common-helper-3.0.5.jar is from SEARS maven repository, which pom cannot download from outside. SO i have included here.
copy 3.0.5 folder to  C:\Users\rk0000\.m2\repository\com\sears\search\common\common-helper\  folder