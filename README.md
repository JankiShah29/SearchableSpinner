# SearchableSpinner
Custom spinner dialog with search functionality - allow single selection

#STEP 1: Add Jitpack Support in Project level build.gradle file

allprojects {
    repositories {
        ...
        maven { url 'https://jitpack.io' }
    }
}

#STEP 2: Add dependency in Module level build.gradle file

dependencies {
    implementation 'com.github.JankiShah29:SearchableSpinner:Tag'
}
