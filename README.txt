
Tech Stack:
This app was implemented using Android framework.
The database used was Firebase

Design pattern:
MVP(Model View Presenter):
This design pattern has been chosen because it can handle asynchronous calls to the database which firebase makes by default. The way it works is that the service layer handles the database calls, the presentation layer uses the service layer and the view. When a call is made to the database, a call to the view is done when data has been retrieved or whena failure has occured such that the reponsibility of handling user interaction is delegated to the view object