[![CodeFactor](https://www.codefactor.io/repository/github/btwarog/artis/badge)](https://www.codefactor.io/repository/github/btwarog/artis)

---
## Description
This is demo application to browse artist using GraphQL API. It contains:
* Splash screen
* Browse screen - with searching functionality
* Bookmarks screen
* Detail screen

All screens have actions to un/bookmark specific artist - beside of Bookmarks screen, which contains only list of Bookmarked artist.  
For obvious reasons it doesn't contain bookmark action - only unbookmark action.

---
## API info
API build with [GraphQL](https://graphql.org/)   
[Check it out](https://graphbrainz.herokuapp.com/) - have a great fun with it!

---
## Technical info
Android Studio `4.1.3`  
* Overall App [configuration](https://github.com/btwarog/Artis/blob/develop/buildSrc/src/main/java/pl/btwarog/AppConfig.kt)  
* Overall App [dependencies](https://github.com/btwarog/Artis/blob/develop/buildSrc/src/main/java/pl/btwarog/Dependencies.kt)

### Libraries   
* [Coroutines](https://github.com/Kotlin/kotlinx.coroutines) - thought about Rx but this time it was Coroutines time  
* [Paging](https://developer.android.com/topic/libraries/architecture/paging) - version 3, -beta02 so API is pretty stable  
* [Navigation component](https://developer.android.com/guide/navigation) - if you start using it then you can't stop 
* [Room](https://developer.android.com/training/data-storage/room) - used to cache bookmarked artists - also used in beta version   
* [Apollo](https://www.apollographql.com/docs/android/) - used to work with GraphQL, for fast implementation used with generating models functionality (but overall I recommend to write it yourself)   
* [OkHttp](https://square.github.io/okhttp/) - client for Apollo   
* [Glide](https://bumptech.github.io/glide/) - Image loading library. Selected cache strategy [ALL](https://bumptech.github.io/glide/javadocs/400/com/bumptech/glide/load/engine/DiskCacheStrategy.html#ALL).  
* [MockK](https://mockk.io/ANDROID.html) - for Unit Testing  
* [Trubine](https://github.com/cashapp/turbine) - for Flow testing




---
## Contact
Feel free to [contact me](mailto:b.twarog90@gmail.com) to discuss anything related to development in particular and life in general.
