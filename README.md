step 1. **mvn clean install -U
**

step 2. **mvn clean package
**

step 3 
**docker build -t ms-customer-service .
docker run -p 8081:8081 ms-customer-service**
