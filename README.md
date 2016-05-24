# Simple Document Scanner

A simple Android app which allows for scanning of documents similar to apps like [GeniusScan](http://thegrizzlylabs.com).

## Screenshots

![Screenshot](https://raw.githubusercontent.com/jbttn/SimpleDocumentScanner-Android/master/screenshots/1.png)
![Screenshot](https://raw.githubusercontent.com/jbttn/SimpleDocumentScanner-Android/master/screenshots/4.png)
![Screenshot](https://raw.githubusercontent.com/jbttn/SimpleDocumentScanner-Android/master/screenshots/2.png)
![Screenshot](https://raw.githubusercontent.com/jbttn/SimpleDocumentScanner-Android/master/screenshots/3.png)

## Details

I needed this type of functionality inside of an app I was building recently and I was unable to find exactly what I was looking for anywhere online, so I put something together myself.

I should note that this is not a library.  This is just an example meant to demonstrate how to implement a feature like this in your own app.

### OpenCV

This app relies on OpenCV for image processing, but integrating it was not a simple task.  For this project, I followed [these steps](http://stackoverflow.com/a/35135495).  This works fine, but having an extra module in your app may be undesirable.  If that's the case, simply build the app and add the compiled AAR into your own app as a dependency.

By default, the app will use OpenCV Manager (availiable on Google Play) in order to link to the OpenCV binary.  This is the recommended way to use OpenCV on Android by the OpenCV developers, but I find this a clunky approach and prefer to statically link OpenCV at build time.  To do this you will need to follow the following steps:

1. Download the [OpenCV SDK](http://sourceforge.net/projects/opencvlibrary/files/opencv-android/3.1.0/OpenCV-3.1.0-android-sdk.zip/download)
2. Unzip and then copy all folders from *OpenCV SDK Dir*/sdk/native/libs to *this project*/opencv/src/main/jniLibs (You may need to create the jniLibs folder).
3. Rebuild

At this point, OpenCV Manager shouldn't be necessary anymore.

### TODO

I leave these as an exercise for the reader.

* Camera support
* Save the resulting bitmap

## Resources
1. [Integrating OpenCV with Android Studio](http://stackoverflow.com/a/35135495)
2. [OpenCV Manager](http://stackoverflow.com/a/20259621)
3. [OpenCV as an AAR](http://steveliles.github.io/building_opencv_as_an_aar_for_android.html)
4. [OpenCV Perspective Transform](http://www.pyimagesearch.com/2014/08/25/4-point-opencv-getperspective-transform-example/)
5. [Document Scanning Algorithm](http://www.pyimagesearch.com/2014/09/01/build-kick-ass-mobile-document-scanner-just-5-minutes/)
6. [If you attempt to include the generated AAR from a module your app depends on, this might be helpful](http://stackoverflow.com/a/31558348)
