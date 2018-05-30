# FriendsList

<i>This application uses Randomuser API in order to retrieve list of users. It gets information about randonly generated "friends".</i>

App contains 2 screens:

- <b>Friends List Screen</b>: User can see list of "friends". Each list item has photo and firstname. 
- <b>Place Details</b>: If user clicks on item of the friend list, app opens picked friend details screen. Details screen contains the same photo in the top plus additional information about friend - firstname, lastname, email and date of registration.

<h4>Additional features:</h4>

- friends are sorted by firstname;
- implemented swipe-to-refresh feature
- local caching of last retrieved friends, in order to show cache data while no internet connection; implemented using Room

<i>This project uses:</i>

- Retrofit - for retrieving JSON data from Randomuser API;
- Picasso - for downloading photos of friens;
- ButterKnife - for binding views;
- Room - for implementing local caching 
