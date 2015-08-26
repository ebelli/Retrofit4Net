# Retrofit4Net

This is an implementation of Square's Retrofit (http://square.github.io/retrofit/) that can be used to call ASP.NET Web Services.
It handles the Date serialization and deserialization, for the peculiar date that Microsoft uses in their JSON.

Creates and return a singleton RestAdapter to be used throughout all the application

# How to use

		RetrofitManager.setEndpoint(*Add the http of your server*); //This is mandatory
		RetrofitManager.setRequestInterceptor(); //To add a Request Inteceptor (like for adding a User Agent to http calls)
		RestAdapter.Log retrofitLog = new RestAdapter.Log() {
			@Override
			public void log(String msg) {
				Log.i("HTTP", msg);
			}
		};
		RetrofitManager.setLog(retrofitLog); //Without this nothing will be logged
		

	ApiManager mApiManager;
	       try {
            mApiManager = RetrofitManager.get();
        } catch (EndpointNotFoundException e) {
           *if you don't call setEndpoint() this exception will be thrown
        }
