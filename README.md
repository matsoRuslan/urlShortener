# urlShortener
urlShortener is a test project created by Matso Ruslan.
# Project setup
To run this project after pulling it you need to:
- Set project SDK to java 1.8.
- Configure project as Web one (add Web framework support
- Enable Maven on this project
- Change user name and password of your mysql db connection in com/matso/config/AppConfig.java:
  * dataSource.setUsername("userName");
  * dataSource.setPassword("password").
- Set run configuration to Tomcat, and add urlShortener:war as a deployment, example for Intellij IDEA:
  * Go to “Run” menu and click “Edit Configurations…”, click “+” icon at the top left and select “Tomcat”
  * Click deloyment -> "+" -> "artifact..." -> urlShortener:war.
  * OPTIONAL: set "http://localhost:8080/home" in tomcat server settings to open this page automatically after project run.
- Now project can be accessed at: http://localhost:8080/home/ 
