# GmailView
**A nearly accurate UI representation of the latest Gmail app when an email is being opened.**

The target is to achieve the user interface of latest Gmail app email chain when a email is opened from inbox. The latest email in an email chain is expanded while the others which were sent previously are not expanded. If you apply pinch in zoom to the email which is expanded, the other emails are like scrolling up or down to provide the email enough room to expand. Hence I tried to achieve the behaviour using `RecyclerView` using multiple `ViewType`. 

I took the content of an email as a `WebView` and hence I placed a sample HTML content in the asset directory to be loaded as a expanded email sample. I had three `ViewType`s in the adapter for the `RecyclerView` and the `RecyclerView` is set to start from the end as this is the behaviour we expect after opening an email or conversation (the lastest one to see first). I have added a function in the `Utility` class to initialize with some sample data for feeding the `RecyclerView`. For experimental purpose, I took only 6 elements for the `RecyclerView`. This is declared as `DEMO_LIST_SIZE` in the `Utility` class as well. 

The result of the overall implementation is the following. Please note that, I could not get an smooth expansion. However, I tried to pose an implementation of the idea using a `RecyclerView`. 

<img src="https://github.com/masudias/GmailView/blob/master/53053586_298787310786998_9064359346690850816_n.png" height="400" width="200">       <img src="https://github.com/masudias/GmailView/blob/master/53268299_401301570426737_4589356255822217216_n.png" height="400" width="200">       <img src="https://github.com/masudias/GmailView/blob/master/53354519_548797548943783_8023120022710779904_n.png" height="400" width="200">

The `list_item_expanded` has a `WebView` which have the pinch zoom in enabled and the wrapper of the `WebView` is set to `wrap_content`. This gives the opportunity to expand the `WebView` and take necessary space for the item itself. 

Now, about pushing the other items upwards or downwards, I added a listener for `WebView`'s scroll change, which sets the adapter position in the right place where it was before the expansion. Hence, the `scrollToPosition` function ensures the `RecyclerView` is showing the expanded view correctly even after the expansion. 

However, the pushing is not as smooth as the Gmail app. I will try to update the code if I find any other solution. 
