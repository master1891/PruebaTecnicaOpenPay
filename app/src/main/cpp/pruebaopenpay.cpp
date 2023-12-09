#include <jni.h>
#include <string>
// Write C++ code here.
//
// Do not forget to dynamically load the C++ library into your application.
//
// For instance,
//
// In MainActivity.java:
//    static {
//       System.loadLibrary("pruebaopenpay");
//    }
//
// Or, in MainActivity.kt:
//    companion object {
//      init {
//         System.loadLibrary("pruebaopenpay")
//      }
//    }
extern "C"
JNIEXPORT jstring JNICALL
Java_com_nels_master_pruebaopenpay_shared_ConsKt_URL_1BASE_1MOVIE(JNIEnv *cadena, jclass clazz) {
    std::string endpoint = "https://api.themoviedb.org/3/";
    return cadena->NewStringUTF(endpoint.c_str());
}
extern "C"
JNIEXPORT jstring JNICALL
Java_com_nels_master_pruebaopenpay_shared_ConsKt_KEY(JNIEnv *cadena, jclass clazz) {
    std::string endpoint = "efdb7075831d13ad101cc281333f2a03";
    return cadena->NewStringUTF(endpoint.c_str());
}
extern "C"
JNIEXPORT jstring JNICALL
Java_com_nels_master_pruebaopenpay_shared_ConsKt_ACCESS_1TOKEN(JNIEnv *cadena, jclass clazz) {
    std::string endpoint = "eyJhbGciOiJIUzI1NiJ9.eyJhdWQiOiJlZmRiNzA3NTgzMWQxM2FkMTAxY2MyODEzMzNmMmEwMyIsInN1YiI6IjY1NmZlZDdmYjMzOTAzMDIzMjdkMzg1OSIsInNjb3BlcyI6WyJhcGlfcmVhZCJdLCJ2ZXJzaW9uIjoxfQ.RO7k8g8XYNex_VBIm3Gmo_86-MjqYjJHaiGkF9o8SoQ";
    return cadena->NewStringUTF(endpoint.c_str());
}