curl -X POST http://54.80.216.111:8082/appointments \
-H "Content-Type: application/json" \
-d '{
  "patientId": 1,
  "doctorId": 2,
  "appointmentDate": "2025-10-23T10:30:00",
  "status": "BOOKED"
}'


curl -X POST http://54.80.216.111:8083/notifications \
-H "Content-Type: application/json" \
-d '{
  "recipient": "john.doe@example.com",
  "message": "Your appointment is confirmed for tomorrow at 10 AM",
  "type": "EMAIL"
}'



curl -X POST http://54.80.216.111:8084/patients/register \
-H "Content-Type: application/json" \
-d '{
  "name": "John Doe",
  "age": 35,
  "gender": "Male",
  "email": "john.doe@example.com",
  "password": "password123",
  "address": "123 Main Street",
  "contactNumber": "9876543210"
}'




curl -X POST http://54.80.216.111:8085/payments \
-H "Content-Type: application/json" \
-d '{
  "patientId": 1,
  "appointmentId": 101,
  "amount": 500.00,
  "paymentMethod": "CREDIT_CARD",
  "paymentStatus": "SUCCESS"
}'




curl -X POST http://54.80.216.111:8086/pharmacy/Apollo \
-H "Content-Type: application/json" \
-d '{
  "name": "Paracetamol",
  "description": "Fever and pain relief tablet",
  "price": 35.5,
  "quantity": 100,
  "manufacturer": "Cipla"
}'





curl -X POST http://54.80.216.111:8086/pharmacy/Apollo \
-H "Content-Type: application/json" \
-d '{
  "name": "Paracetamol",
  "description": "Fever and pain relief tablet",
  "price": 35.5,
  "quantity": 100,
  "manufacturer": "Cipla"
}'





