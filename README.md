<h1>Broccoli & Co. - Invitation App<h1>

<h3> MVVM architecture with retrofit and coroutine for network layer and viewmodel and databinding for presentation layer </h3>

<h3>Application Architecture</h3>
 
I choose MVVM to create this project. This design pattern is fully supported and encouraged by google and includes all of their first party libraries. 
 
 
 
 <div>
   <table width="50%" height="50%" align="center" valign="center">
   <tr><td>
      <img src="https://user-images.githubusercontent.com/48664320/164951850-9ae1cad9-4dc2-4b4d-bbfd-0bfc432b7b62.png" alt="MVVM"/>
   </td></tr>
   </table>
</div>
 
 <ul>
 <li><b>Model</b></li>
 This is where all of our business related code will be stored. 
 Models are used exclusively as our domain objects, to provide definitions and structure to the objects we need to fetch from the apis provided. 
 
<li><b>Reporsitory</b></li>
 Repositories here are used as the trafic lanes between the provided APIs and our data models - Domain Obects. 
 
 <li><b>ViewModel</b></li>
  Most if not all of the view logic will be handled by the ViewModel. ViewModels will give views directions and instructions on what to display and govern most of the UI logic. ViewModels are the transmission lanes between the views and the business logic of our code they will grab data from the repositories and  transmit it over to the views to display. 
</ul>

<h3>Data Architecture</h3>
User model and Response.kt to post and get response to and from the server respectively.

<h3>Network Architecture</h3>
 
All network connection in this app is carried out using Retrofit inside API.kts class. It is important for all network operations to be executed in this class in order to be queued and handled properly.

<h3>Documentation</h3>

Under AppDocumentation Directory within this code there is a version control document  <a href="https://github.com/nirvikBasnet/Broccoli/blob/main/app/src/main/java/com/example/broccoli/documentation/version_history">HERE</a>. Which contains a list of all major pulls / push and commit entries to this repository. 


<h3>Libraries Used</h3>

<ul>
  <li>Retrofit - <a href="https://square.github.io/retrofit/">Retrofit</a></li>
</ul>
