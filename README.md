This will create a continuous stream of objects which client will keep receiving, it depends on client how to handle the Flux.
@StreamMeResource is the controller which will stream the StreamMe pojo with random UID and Name at an interval of 1 second. One more important note, the MediaType produces Stream of TEXT_EVENT_STREAM_VALUE. 
Fork this project and boot up the application. Then run this URL -> http://localhost:8082/rest/everything
You can also create a WebClient for this URL -> http://localhost:8082/rest/everything 
