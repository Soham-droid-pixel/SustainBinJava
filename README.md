Project Title: Sustainable Cities and Communities.
Description: Waste Management system in Smart Cities
 A Java application for smart waste management in urban areas. The app should track waste collection schedules, optimize routes for garbage trucks, and encourage recycling, contributing to sustainable urban development and providing Data Analysis  in support of SDG 11.
Scope: One city-  Mumbai 
Andheri(E)
Kurla(W)
Bandra(W)
Chembur
Dadar
Functionality: 
Waste Collection Tracking: Allow users to view waste collection schedules by waste type.
Recycling Encouragement: Provide information and incentives for residents to recycle, including reward points and tips.
User Interface: A user-friendly interface for residents to view schedules, track recycling stats, and manage notifications.
Data Analysis: Analyze waste collection patterns of different location to offer insights on recycling trends and efficiency improvements.
Project Requirements (Non Functional):  Project includes following:
 User-friendly interface - A user friendly interface for residents and garbage   cleaning workers to keep a track of waste collection schedules and educate them about aiming zero waste and waste management.
 Secure and efficient database handling-
 Error and exception handling-Done on java file of MumbaiWasteManagementSystem
 Creation of packages-None
 Use of new libraries, packages or API included -Gson

 Technology Stack Used
 Front-End: HTML, CSS, Javascript
 Database:Mysql, PHPmyadmin
 Backend Connectivity: JDBC (Java Database Connectivity)
8. Database Schema :;WasteManagement
Purpose: This table records data on waste collection, locations, and relevant analysis for waste management in Mumbai.
Fields:
record_id (INT, Primary Key, AUTO_INCREMENT): Unique identifier for each waste management record.
area_name (VARCHAR(100)): Name of the area where waste is collected.
area_type (VARCHAR(50)): Type of area (e.g., Urban, Rural).
latitude (FLOAT): Latitude of the collection area.
longitude (FLOAT): Longitude of the collection area.
waste_type (VARCHAR(50)): Type of waste (e.g., Municipal, Organic, Hazardous).
Dumping Ground Information:
dumping_ground_name (VARCHAR(100)): Name of the dumping ground used.
dumping_ground_location (VARCHAR(200)): Location address of the dumping ground.
dumping_ground_capacity (INT): Capacity of the dumping ground.
Recycling Center Information:
recycling_center_name (VARCHAR(100)): Name of the recycling center.
recycling_center_location (VARCHAR(200)): Address of the recycling center.
accepted_waste_types (VARCHAR(200)): Types of waste the recycling center accepts.
E-Waste Station Information:
ewaste_station_name (VARCHAR(100)): Name of the e-waste station.
ewaste_station_location (VARCHAR(200)): Location address of the e-waste station.
ewaste_handling_capacity (INT): Capacity of the e-waste station.
Truck Information:
truck_id (INT): Identifier for the truck.
truck_plate_number (VARCHAR(50)): License plate number of the truck.
driver_name (VARCHAR(100)): Name of the driver.
truck_capacity (INT): Capacity of the truck.
Route and Collection Information:
current_route (VARCHAR(200)): Route the truck is assigned to.
route_description (VARCHAR(200)): Description of the route.
scheduled_time (DATETIME): Scheduled collection time.
waste_amount_collected (FLOAT): Amount of waste collected.
collection_date (DATE): Date of waste collection.
geolocation_latitude (FLOAT): Latitude recorded during collection.
geolocation_longitude (FLOAT): Longitude recorded during collection.
geolocation_timestamp (DATETIME): Timestamp for geolocation recording.
Analysis Information:
analysis_total_waste_collected (FLOAT): Total amount of waste collected, analyzed.
analysis_date (DATE): Date of analysis.
analysis_observations (VARCHAR(200)): Observations from waste analysis.
