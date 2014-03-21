MyUtils
=======

MyUtils -> some tools or demo or test in java



- beanutils
- dbutils
- mongodb
- redis(jedis client demo)
- thread
- shiro example
- some other utils
- configuration load
- common.io,lan3,beanutils,configuration,stringutils,dateutils..
- apache poi for operation xls file
- other some test
- fasejson and jacson and dom4j
- slf4j and logback
- zxing for qrcode
- snappy for compress and uncompress

##mvn package to jar file 

see file pom_.xml

> $ cat ./bin/package.sh 

	echo [INFO] Package the jar in target dir.
	
	cd ..
	
	mvn clean package -Dmaven.test.skip=true
	
	cd target
	echo jar file builed found
	find ./ -maxdepth 1 -name '*.jar' -type f
	echo rename jar file with parckage date...
	find ./ -maxdepth 1 -name '*.jar' -type f -exec mv {} {}`date +%F"-"%H%M%S`'.jar' \;
	echo rename jar end...

then packaged jar file in 

	./target/ProjectName-1.0.jar2014-03-21-153956.jar
	./target/ProjectName-1.0-sources.jar2014-03-21-153956.jar
	

##mvn package  tree
package tree like this:

	|-- config
	|   |-- datasource.xml
	|-- lib
	|   |-- commons-dbcp-1.4.jar
	|   |-- commons-dbutils-1.5.jar
	|   |-- commons-lang3-3.2.1.jar
	|   |-- commons-pool-1.5.4.jar
	|   |-- dom4j-1.6.1.jar
	|   |-- fastjson-1.1.35.jar
	|   |-- hamcrest-core-1.3.jar
	|   |-- jaxen-1.1.4.jar
	|   |-- junit-4.11.jar
	|   |-- logback-access-1.0.13.jar
	|   |-- logback-classic-1.0.13.jar
	|   |-- logback-core-1.0.13.jar
	|   |-- mysql-connector-java-5.1.26.jar
	|   |-- slf4j-api-1.7.5.jar
	|   `-- xml-apis-1.0.b2.jar
	|-- logback.xml
	|-- ProjectName-1.0.jar2014-02-28-104832.jar
	|-- ProjectName-1.0-sources.jar2014-02-28-104832.jar

