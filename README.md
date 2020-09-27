# PopularMovies
 
Android application that shows a list of most popular movies and their details

Min API Level 21


## Tech-stack
* Retrofit
* RxJava2
* Hilt for dependency injection
* Coil 
* Espresso UI Testing
* Mockito
* Android Jetpack 

   - Navigation 
   - ViewModel
   - LiveData
   - Room
   - Test

## Architecture
It implements a Clean Architecture:

3 modules


* Presentation  
  MVVM pattern 
  - Screens(Activity/Fragments/Views)
  - ViewModels
  

* Domain  
  Executes Usecases and defines domain models
  
* Data  
  Repository Pattern  
  Define data models and mappers  
  Datasources:  
    - API: get data from The Movie Database API when data are not on cache  
    - Room DB: save and get data from here
  

