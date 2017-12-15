# RxRetro - RxJava with Retrofit simplified

Retrofit is an wonderful network library to deal with api's and Rx java can be used to do async task operations effectively.
Using retrofit with RxJava will results in seamless network calls in mainthread. Retrofit can be used without RxJava, though to
perform huge api calling and to do api calls in background, they pair a better combination.

## USAGE

### root build.gradle

Add it in your root build.gradle at the end of repositories:

if not found:

allprojects {
		repositories {
			maven { url 'https://jitpack.io' }
		}
	}

if found:

allprojects {
    repositories {
        google()
        jcenter()
        maven { url 'https://jitpack.io' }
    }
}

### app gradle

compile 'com.github.Periyanayagam:RxRetro:1.0'


Any suggestions on improving this library is welcomed, any doubts or comments? @ perusudroid@gmail.com

