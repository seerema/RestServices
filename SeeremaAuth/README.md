## About this project (**SeeremaAuth**)

*SeeremaAuth* is a set of shared libraries for authentication layers and set or REST services for Seerema CRM

- **DemoAuthServer** is Demo Authentication Server that uses external xml file  based on Tomcat users xml file.
- **RestAuthCatalog** is a REST service for Catalog module.
- **RestAuthTask** is a REST service for Task module.
- **RestAuthCrm** is a REST service for CRM module.

## Build

### Quick build

```
mvn clean install
```

### Release Build (with tests)

```
mvn clean install -p release
```

