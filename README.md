# dentalappointments
1. Clone the project using below URL
https://github.com/peddipradeep/dentalappointments.git

2. Start the Application by running SpringBoot Main class DentalAppointmentApplication

3. Application should start running on default port 8080

4. Data is persisted in H2 which is in-memory database. Once the application is started open H2 console using below url.
http://localhost:8080/h2 (Just connect without any password)

Rest API to work on dental appointments
1. To add new appointments
    POST: http://localhost:8080/dentalAppointments/
body:
{
    "patientId": 3,
    "dentistId": 2,
    "startDate": "2019-09-30T14:10:00",
    "endDate": "2019-09-30T14:40:00"
}

2. To get the list of all appointments
    GET: http://localhost:8080/dentalAppointments/


3. get the appointments by id
    GET: http://localhost:8080/dentalAppointments/1

4. get the appointments by dentist
    GET: http://localhost:8080/dentalAppointments/dentist/3

Negative cases:
1. Adding another appointment for same dentists with same start date. Below error response is returned.
    POST: http://localhost:8080/dentalAppointments/
Request body:
{
    "patientId": 3,
    "dentistId": 2,
    "startDate": "2019-09-30T14:10:00",
    "endDate": "2019-09-30T14:40:00"
}
Response:
{
    "message": "Bad data error",
    "details": [
        "Please check appointment details. Dentist already has an appointment at 2019-09-30T14:10:00. Please select another start date"
    ]
}

2. Add appointment with appointment duration less than 30 minutes

Request body:
POST: http://localhost:8080/dentalAppointments/
Request body:
{
    "patientId": 3,
    "dentistId": 2,
    "startDate": "2019-09-29T14:10:00",
    "endDate": "2019-09-29T14:39:00"
}

Response:
{
    "message": "Bad data error",
    "details": [
        "Please check appointment details. Appointment duration has to be atleast 30 minutes"
    ]
}

3. Try entering past dates for appointment. Below error response should be returned.
Request body:
POST: http://localhost:8080/dentalAppointments/
Request body:
{
    "patientId": 3,
    "dentistId": 2,
    "startDate": "2019-08-29T14:10:00",
    "endDate": "2019-08-29T14:39:00"
}

Response:
{
    "message": "Bad data error",
    "details": [
        "Please check appointment details. Appointment start and end dates must be in future"
    ]
}

4. Try entering invalid dates for appointment. Below error response should be returned.
Request body:
POST: http://localhost:8080/dentalAppointments/
Request body:
{
    "patientId": 3,
    "dentistId": 2,
    "startDate": "2019-09-31T14:10:00",
    "endDate": "2019-09-31T14:39:00"
}

Response:
{
    "message": "Bad data error",
    "details": [
        "Start and End dates have to be valid"
    ]
}