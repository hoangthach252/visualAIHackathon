author: Thach Hoang
Git repo: https://github.com/hoangthach252/visualAIHackathon.git

================How to run tests in command line ===============
1. Run all traditional tests
mvn clean test -DsuiteXmlFile="TraditionalSuite.xml"

2. Run all Visual AI tests with App V1
mvn clean test -DsuiteXmlFile="TraditionalSuite.xml"

3. Run all Visual AI tests with App V2
mvn clean test -DsuiteXmlFile="TraditionalSuite.xml"

================How to get the report ===============
After running the suite, go to target\surefire-reports directory and check index.html.
