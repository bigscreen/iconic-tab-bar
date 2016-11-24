# IconicTabBar

[![Platform](https://img.shields.io/badge/platform-android-green.svg)](http://developer.android.com/index.html)
[![API](https://img.shields.io/badge/API-15%2B-brightgreen.svg?style=flat)](https://android-arsenal.com/api?level=15)
[![Twitter](https://img.shields.io/badge/Twitter-@gallant_pr-blue.svg?style=flat)](http://twitter.com/gallant_pr)

IconicTabBar is an Android custom TabView which its design and pattern are created based on Android BottomBar design pattern (check [this](https://material.google.com/components/bottom-navigation.html)).

<img src="/preview/iconic-tab-bar-demo.gif" alt="sample" title="sample" width="237" height="420" align="right" vspace="40" />


Usage
-----
To use IconicTabBar, you only have to add IconicTabBar in your layout XML and add IconicTabBar library in your project via Gradle:
```groovy
compile 'com.bigscreen:iconic-tab-bar:1.0.4'
```
##### Note
IconicTabBar is not available yet in maven central, so make sure you have jcenter in your repositories.
```groovy
repositories {
  jcenter()
}
```

### XML
```xml
<com.bigscreen.iconictabbar.view.IconicTabBar
    android:id="@+id/tab_bar"
    android:layout_width="match_parent"
    android:layout_height="wrap_content"
    app:barBackground="#FFFFFF"
    app:tabDefaultColor="#CC4B4B4B"
    app:tabSelectedColor="#3F51B5"/>
```
You must use the following properties in your XML to change your IconicTabBar.
##### Properties:
* `app:barBackground`       (color)     -> default #FFF
* `app:tabDefaultColor`     (color)     -> default #757575
* `app:tabSelectedColor`    (color)     -> default #03A9F4


Adding Tab
-----
You can add tab items from resource menu.
```xml
app:tabFromMenu="@menu/menu_bottom_bar"
```
But IconicTabBar doesn't support menu grouping. It's recommended to use the simplest `xml menu` code.
```xml
<menu xmlns:android="http://schemas.android.com/apk/res/android">
    <item
        android:id="@+id/bottom_chats"
        android:icon="@drawable/ic_chats"
        android:title="Chats" />
    <item
        android:id="@+id/bottom_calls"
        android:icon="@drawable/ic_calls"
        android:title="Calls" />
</menu>
```
Otherwise, you can add tabs programmatically on the Java code.
```java
iconicTabBar.addTab(R.drawable.ic_chats, "Chats");
iconicTabBar.addTab(R.drawable.ic_calls, "Calls");
```
you can specify tab's id as well.
```java
iconicTabBar.addTab(1, R.drawable.ic_chats, "Chats");
iconicTabBar.addTab(2, R.drawable.ic_calls, "Calls");
```
##### Note
*Maximum tabs to be added on IconicTabBar is only 5. Adding more than 5 tabs will make your app crashes.*


Listening Selected Tab
-----
For listen which tab is being selected, you can add this on your Java code:
```java
iconicTabBar.setOnTabSelectedListener(new IconicTabBar.OnTabSelectedListener() {
    @Override
    public void onSelected(IconicTab tab, int position) {
        Log.d(TAG, "selected tab on= " + position);
        int tabId = tab.getId();
        switch (tabId) {
            case R.id.bottom_chats:
                Toast.makeText(MainActivity.this, "Chats is selected", Toast.LENGTH_SHORT).show();
                break;
            case R.id.bottom_calls:
               Toast.makeText(MainActivity.this, "Calls is selected", Toast.LENGTH_SHORT).show();
                break;
            default:
                break;
        }
    }

    @Override
    public void onUnselected(IconicTab tab, int position) {
        Log.d(TAG, "unselected tab on= " + position);
    }
});
```

Changelog
-----
<b>1.0.4</b><br>
Fixed bugs, tab text improvement.

<b>1.0.3</b><br>
Tab transition animation, using [Transition Everywhere](https://github.com/andkulikov/Transitions-Everywhere).

<b>1.0.2</b><br>
Support resource menu id and bug fix.

<b>1.0.1</b><br>
Fixed tab width auto measurement.


License
-----
```
Licensed under the Apache License, Version 2.0 (the "License");
you may not use this file except in compliance with the License.
You may obtain a copy of the License at

   http://www.apache.org/licenses/LICENSE-2.0

Unless required by applicable law or agreed to in writing, software
distributed under the License is distributed on an "AS IS" BASIS,
WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
See the License for the specific language governing permissions and
limitations under the License.
```
